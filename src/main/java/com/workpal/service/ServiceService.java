package com.workpal.service;

import com.workpal.model.Service;
import com.workpal.repository.implementInterfaces.ServiceRepository;

import java.sql.SQLException;
import java.util.List;

public class ServiceService {
    private ServiceRepository serviceRepository = new ServiceRepository();
    public boolean addService(String name, String description, int manager_id) throws SQLException {
        serviceRepository.saveService(new Service( 0, name, description, manager_id));
        return true;
    }
    public List<Service> getAllServices() throws SQLException {
        return serviceRepository.getAllServices();
    }

    public boolean updateService(Service service) throws SQLException {
        return serviceRepository.updateService(service);
    }

    public boolean deleteService(int id) throws SQLException {
        return serviceRepository.deleteService(id);
    }
}
