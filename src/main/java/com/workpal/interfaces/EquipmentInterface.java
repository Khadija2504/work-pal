package com.workpal.interfaces;

import com.workpal.model.Equipment;
import com.workpal.model.Event;

import java.sql.SQLException;
import java.util.List;

public interface EquipmentInterface {
    void saveEquipment(Equipment equipment) throws SQLException;
    List<Equipment> getAllEquipments() throws SQLException;
    boolean updateEquipment(Equipment equipment) throws SQLException;
    boolean deleteEquipment(int id) throws SQLException;
}
