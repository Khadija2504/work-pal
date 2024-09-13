package com.workpal.repository.implementInterfaces;

import com.workpal.model.*;
import com.workpal.repository.interfaces.SpacesReservationInterface;
import com.workpal.service.SessionUser;
import com.workpal.util.JdcbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpacesReservationRepository implements SpacesReservationInterface {
    @Override
    public void saveReservation(SpaceReservation spaceReservation) throws SQLException {
        Connection connection = JdcbConnection.getConnection();
        String query = "INSERT INTO reservations_space (member_id, space_id) VALUES (?, ?) ";
        PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setInt(1, spaceReservation.getMember_id());
        statement.setInt(2, spaceReservation.getSpace_id());
        statement.executeUpdate();
    }

    @Override
    public List<Space> getAllSpaceReservations() throws SQLException {
        Connection connection = JdcbConnection.getConnection();
        User loggedInUser = SessionUser.getLoggedInUser();

        String sql = "SELECT s.id AS space_id, s.name, s.description, s.policies, s.manager_id, s.type, " +
                "rs.member_id, rs.status " +
                "FROM spaces s " +
                "JOIN reservations_space rs ON s.id = rs.space_id , rs.status = 'not_yet'" +
                "WHERE rs.member_id = ?";

        List<Space> spaces = new ArrayList<>();
        Map<Integer, Space> spaceMap = new HashMap<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, loggedInUser.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int spaceId = rs.getInt("space_id");

                    Space space = spaceMap.get(spaceId);
                    if (space == null) {
                        space = new Space(
                                spaceId,
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getString("policies"),
                                rs.getInt("manager_id"),
                                rs.getString("type")
                        );
                        spaceMap.put(spaceId, space);
                        spaces.add(space);
                    }

                    SpaceReservation spaceReservation = new SpaceReservation(
                            loggedInUser.getId(),
                            rs.getInt("member_id"),
                            rs.getInt("space_id"),
                            rs.getString("status")
                    );
                    space.getSpaceReservations().add(spaceReservation);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error fetching spaces with reservations", e);
        }

        return spaces;
    }


    @Override
    public boolean updateSpaceReservation(SpaceReservation spaceReservation) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteSpaceReservation(int id) throws SQLException {
        return false;
    }


    @Override
    public List<Space> getAllSpaces() throws SQLException {
        Connection connection = JdcbConnection.getConnection();
        String sql = "SELECT * FROM spaces ORDER BY id ASC ";
        List<Space> spaces = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    spaces.add(new Space(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getString("policies"),
                            rs.getInt("manager_id"),
                            rs.getString("type")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error fetching spaces", e);
        }

        return spaces;
    }
}
