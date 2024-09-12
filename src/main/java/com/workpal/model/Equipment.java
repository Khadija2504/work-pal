package com.workpal.model;

public class Equipment {
    private int id;
    private String name;
    private int space_id;
    private int manager_id;
    public Equipment(int id, String name, int space_id, int manager_id) {
        this.id = id;
        this.name = name;
        this.space_id = space_id;
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
    public int getSpace_id() {
        return space_id;
    }
    public void setSpace_id(int space_id) {
        this.space_id = space_id;
    }
    public int getManager_id() {
        return manager_id;
    }
    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }
}
