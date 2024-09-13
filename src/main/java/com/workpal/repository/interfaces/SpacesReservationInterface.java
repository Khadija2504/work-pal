package com.workpal.repository.interfaces;

import com.workpal.model.Space;
import com.workpal.model.SpaceReservation;

import java.sql.SQLException;
import java.util.List;

public interface SpacesReservationInterface {
    void saveReservation(SpaceReservation spaceReservation) throws SQLException;
    List<SpaceReservation> getAllSpaceReservations() throws SQLException;
    boolean updateSpaceReservation(SpaceReservation spaceReservation) throws SQLException;
    boolean deleteSpaceReservation(int id) throws SQLException;
    List<Space> getAllSpaces() throws SQLException;
}
