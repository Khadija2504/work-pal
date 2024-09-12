package com.workpal.model;

public class SubsService {
    private int id;
    private int service_id;
    private int manager_id;
    private int subs_id;

    public SubsService(int id, int service_id, int manager_id, int subs_id) {
        this.id = id;
        this.service_id = service_id;
        this.manager_id = manager_id;
        this.subs_id = subs_id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getService_id() {
        return service_id;
    }
    public void setService_id(int service_id) {
        this.service_id = service_id;
    }
    public int getManager_id() {
        return manager_id;
    }
    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }
    public int getSubs_id() {
        return subs_id;
    }
    public void setSubs_id(int subs_id) {
        this.subs_id = subs_id;
    }
}
