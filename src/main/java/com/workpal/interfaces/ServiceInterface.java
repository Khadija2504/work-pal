package com.workpal.interfaces;

import com.workpal.model.Event;
import com.workpal.model.Service;

import java.sql.SQLException;
import java.util.List;

public interface ServiceInterface {
    void saveService(Service service) throws SQLException;
    List<Service> getAllServices() throws SQLException;
    boolean updateService(Service service) throws SQLException;
    boolean deleteService(int id) throws SQLException;
}
