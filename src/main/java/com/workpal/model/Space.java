package com.workpal.model;

public class Space {
    private int id;
    private String name;
    private String description;
    private String policies;
    private int manager_id;
    private String type;

    public Space(int id, String name, String description, String policies, int manager_id, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.manager_id = manager_id;
        this.type = type;
        this.policies = policies;
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
    public String getPolicies() {
        return policies;
    }
    public void setPolicies(String policies) {
        this.policies = policies;
    }
    public int getManager_id() {
        return manager_id;
    }
    public void setManager_id(int capacity) {
        this.manager_id = capacity;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
