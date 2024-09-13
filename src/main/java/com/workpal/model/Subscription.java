package com.workpal.model;

import java.util.ArrayList;
import java.util.List;

public class Subscription {
    private int id;
    private String name;
    private String description;
    private String type;
    private int price;
    private int manager_id;
    private List<Service> services;

    public Subscription(int id, String name, String description, String type, int price, int manager_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.price = price;
        this.manager_id = manager_id;
        this.services = new ArrayList<>();
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getManager_id() {
        return manager_id;
    }
    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }
    public List<Service> getServices() {
        return services;
    }

    public void addService(Service service) {
        this.services.add(service);
    }
}
