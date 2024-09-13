package com.workpal.service;

import com.workpal.model.Space;
import com.workpal.repository.implementInterfaces.SpaceRepository;

import java.sql.SQLException;
import java.util.List;

public class SpaceService {
    private SpaceRepository spaceRepository = new SpaceRepository();
    public boolean addSpace(String name, String description, String policies, int manager_id, String type) throws SQLException {
        spaceRepository.saveSpace(new Space(0, name, description, policies, manager_id, type));
        return true;
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
