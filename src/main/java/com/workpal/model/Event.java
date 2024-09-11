package com.workpal.model;

import java.sql.Date;

public class Event {
    private int id;
    private String name;
    private String description;
    private String location;
    private Date date;
    private String type;
    private String policies;
    private int places_num;
    private int manager_id;

    public Event(int id, String name, String description, String location, Date date, String type, String policies, int places_num, int manager_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.date = date;
        this.type = type;
        this.policies = policies;
        this.places_num = places_num;
        this.manager_id = manager_id;
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
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getPolicies() {
        return policies;
    }
    public void setPolicies(String policies) {
        this.policies = policies;
    }
    public int getPlaces_num() {
        return places_num;
    }
    public void setPlaces_num(int places_num) {
        this.places_num = places_num;
    }
    public int getManager_id() {
        return manager_id;
    }
    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }
}
