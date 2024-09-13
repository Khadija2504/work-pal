package com.workpal.service;

import com.workpal.model.Equipment;
import com.workpal.repository.implementInterfaces.EquipmentRepository;

import java.sql.SQLException;
import java.util.List;

public class EquipmentService {
    EquipmentRepository equipmentRepository = new EquipmentRepository();
    public boolean addEquipment(String name, int space_id, int manager_id) throws SQLException {
        equipmentRepository.saveEquipment(new Equipment(0, name, space_id, manager_id));
        return true;
    }

    public List<Equipment> getAllEquipments() throws SQLException {
        return equipmentRepository.getAllEquipments();
    }

    public boolean updateEquipment(Equipment equipment) throws SQLException {
        return equipmentRepository.updateEquipment(equipment);
    }

    public boolean deleteEquipment(int id) throws SQLException {
        return equipmentRepository.deleteEquipment(id);
    }
}
