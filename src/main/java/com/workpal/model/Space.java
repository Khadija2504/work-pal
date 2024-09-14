package com.workpal.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Space {
    private int id;
    private String name;
    private String description;
    private String policies;
    private int manager_id;
    private String type;
    private int price;
    private int tail;
    private List<SpaceReservation> spaceReservation;

    public Space(int id, String name, String description, String policies, int manager_id, String type, int price, int tail) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.manager_id = manager_id;
        this.type = type;
        this.price = price;
        this.tail = tail;
        this.policies = policies;
        this.spaceReservation = new ArrayList<>();
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
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    public int getTail() { return tail; }
    public void setTail(int tail) { this.tail = tail; }
    public List<SpaceReservation> getSpaceReservations() {
        return spaceReservation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Space space = (Space) o;
        return id == space.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
