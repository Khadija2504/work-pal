package com.workpal.repository.interfaces;

import com.workpal.model.SubsMember;
import com.workpal.model.SubsPayment;
import com.workpal.model.Subscription;

import java.sql.SQLException;
import java.util.List;

public interface SubsMemberInterface {
    void saveReservation(SubsMember subsMember, SubsPayment payment) throws SQLException;
    List<Subscription> getAllSubsMembers() throws SQLException;
    boolean deleteSubsMember(int id) throws SQLException;
    List<Subscription> getAllSubs() throws SQLException;
}
