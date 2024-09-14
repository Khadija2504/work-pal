package com.workpal.model;

public class SubsPayment {
    private int id;
    private int member_id;
    private String card_info;
    private int subs_id;
    private String payment_type;

    public SubsPayment(int id, int member_id, String card_info, int subs_id, String payment_type) {
        this.id = id;
        this.member_id = member_id;
        this.card_info = card_info;
        this.payment_type = payment_type;
        this.subs_id = subs_id;
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
    public String getCard_info() {
        return card_info;
    }
    public void setCard_info(String card_info) {
        this.card_info = card_info;
    }
    public String getPayment_type() {
        return payment_type;
    }
    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }
    public int setSubs_id() {
        return subs_id;
    }
    public void setSubs_id(int subs_id) {
        this.subs_id = subs_id;
    }
}
