package com.workpal.service;

import com.workpal.model.SubsMember;
import com.workpal.model.SubsPayment;
import com.workpal.model.Subscription;
import com.workpal.repository.implementInterfaces.SubsMemberRepository;

import java.sql.SQLException;
import java.util.List;

public class SubsMemberService {
    SubsMemberRepository subsMemberRepository =new SubsMemberRepository();
    public boolean addSubsMember(int member_id, int subs_id, String card_info) throws SQLException {
        subsMemberRepository.saveReservation(new SubsMember( 0, subs_id, member_id, "not_yet"), new SubsPayment(0, member_id, card_info, subs_id,"subscription"));
        return true;
    }

    public List<Subscription> getAllSubsMembers() throws SQLException {
        return subsMemberRepository.getAllSubsMembers();
    }

    public boolean deleteReservation(int id) throws SQLException {
        return subsMemberRepository.deleteSubsMember(id);
    }

    public List<Subscription> getAllSubscriptions() throws SQLException {
        return subsMemberRepository.getAllSubs();
    }
}
