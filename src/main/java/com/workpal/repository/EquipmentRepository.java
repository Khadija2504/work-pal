package com.workpal.repository;

import com.workpal.interfaces.EquipmentInterface;
import com.workpal.model.Equipment;
import com.workpal.model.Event;
import com.workpal.model.User;
import com.workpal.service.SessionManager;
import com.workpal.util.JdcbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentRepository implements EquipmentInterface {

    @Override
    public void saveEquipment(Equipment equipment) throws SQLException {
        Connection connection = JdcbConnection.getConnection();
        String query = "INSERT INTO equipments (name, space_id, manager_id) VALUES (?, ?, ?) ";
        PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, equipment.getName());
        statement.setInt(2, equipment.getSpace_id());
        statement.setInt(3, equipment.getManager_id());
        statement.executeUpdate();
    }
    @Override
    public List<Equipment> getAllEquipments() throws SQLException {
        Connection connection = JdcbConnection.getConnection();
        User loggedInUser = SessionManager.getLoggedInUser();
        String sql = "SELECT * FROM equipments WHERE manager_id = ?";
        List<Equipment> equipments = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, loggedInUser.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    equipments.add(new Equipment(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("space_id"),
                            rs.getInt("manager_id")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error fetching equipments", e);
        }

        return equipments;
    }

    @Override
    public boolean updateEquipment(Equipment equipment) throws SQLException {
        Connection connection = JdcbConnection.getConnection();
        String sql = "UPDATE equipments SET name = ?, space_id = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, equipment.getName());
            pstmt.setInt(2, equipment.getSpace_id());
            pstmt.setInt(3, equipment.getId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    @Override
    public boolean deleteEquipment(int id) throws SQLException {
        Connection connection = JdcbConnection.getConnection();
        String sql = "DELETE FROM equipments WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}
