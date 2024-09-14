package com.workpal.service;

import com.workpal.model.Space;
import com.workpal.repository.implementInterfaces.SpaceRepository;

import java.sql.SQLException;
import java.util.List;

public class SpaceService {
    private SpaceRepository spaceRepository = new SpaceRepository();
    public boolean addSpace(String name, String description, String policies, int manager_id, String type, int price, int tail) throws SQLException {
        spaceRepository.saveSpace(new Space(0, name, description, policies, manager_id, type, price, tail));
        return true;
    }

    public List<Space> searchByName(String name) {
        return spaceRepository.findByName(name);
    }

    public List<Space> searchByType(String type) {
        return spaceRepository.findByType(type);
    }

    public List<Space> searchByPrice(int price) {
        return spaceRepository.findByPrice(price);
    }

    public List<Space> getAllSpaces() throws SQLException {
        return spaceRepository.getAllSpaces();
    }

    public boolean updateSpace(Space space) throws SQLException {
        return spaceRepository.updateSpace(space);
    }

    public boolean deleteSpace(int id) throws SQLException {
        return spaceRepository.deleteSpace(id);
    }

    public Space getSpace(int id) throws SQLException {
        return spaceRepository.getSpace(id);
    }
}
