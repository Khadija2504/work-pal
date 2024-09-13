package com.workpal.repository.interfaces;

import com.workpal.model.Equipment;

import java.sql.SQLException;
import java.util.List;

public interface EquipmentInterface {
    void saveEquipment(Equipment equipment) throws SQLException;
    List<Equipment> getAllEquipments() throws SQLException;
    boolean updateEquipment(Equipment equipment) throws SQLException;
    boolean deleteEquipment(int id) throws SQLException;
}
