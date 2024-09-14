package com.workpal.repository.interfaces;

import com.workpal.model.Space;

import java.sql.SQLException;
import java.util.List;

public interface SpaceInterface {
    void saveSpace(Space space) throws SQLException;
    List<Space> getAllSpaces() throws SQLException;
    boolean updateSpace(Space space) throws SQLException;
    boolean deleteSpace(int id) throws SQLException;
    Space getSpace(int id) throws SQLException;
    List<Space> findByName(String name);
    List<Space> findByType(String type);
    List<Space> findByPrice(int price);
}

