package com.workpal.model;

import java.util.ArrayList;
import java.util.List;

public class SubsMember {
    private int id;
    private int subscription_id;
    private int member_id;
    private String availability;

    public SubsMember(int id, int subscription_id, int member_id, String availability) {
        this.id = id;
        this.subscription_id = subscription_id;
        this.member_id = member_id;
        this.availability = availability;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getSubscription_id() {
        return subscription_id;
    }
    public void setSubscription_id(int subscription_id) {
        this.subscription_id = subscription_id;
    }
    public int getMember_id() {
        return member_id;
    }
    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }
    public String getAvailability() {
        return availability;
    }
    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
