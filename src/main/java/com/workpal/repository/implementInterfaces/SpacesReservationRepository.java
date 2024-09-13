package com.workpal.repository.implementInterfaces;

import com.workpal.model.Space;
import com.workpal.model.SpaceReservation;
import com.workpal.model.User;
import com.workpal.repository.interfaces.SpacesReservationInterface;
import com.workpal.service.SessionUser;
import com.workpal.util.JdcbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public List<SpaceReservation> getAllSpaceReservations() throws SQLException {
        return List.of();
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
