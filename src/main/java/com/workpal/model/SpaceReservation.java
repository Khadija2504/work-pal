package com.workpal.model;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class SpaceReservation {
    private int id;
    private int member_id;
    private int space_id;
    private String status;

    public SpaceReservation(int id, int member_id, int space_id, String status) {
        this.id = id;
        this.member_id = member_id;
        this.space_id = space_id;
        this.status = status;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getMember_id() {
        return member_id;
    }
    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }
    public int getSpace_id() {
        return space_id;
    }
    public void setSpace_id(int space_id) {
        this.space_id = space_id;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

}
