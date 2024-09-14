package com.workpal.repository.interfaces;

import com.workpal.model.Space;
import com.workpal.model.SpacePayment;
import com.workpal.model.SpaceReservation;

import java.sql.SQLException;
import java.util.List;

public interface SpacesReservationInterface {
    void saveReservation(SpaceReservation spaceReservation, SpacePayment payment) throws SQLException;
    List<Space> getAllSpaceReservations() throws SQLException;
    boolean deleteSpaceReservation(int id) throws SQLException;
    List<Space> getAllSpaces() throws SQLException;
}
