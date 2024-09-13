package com.workpal.service;

import com.workpal.model.Space;
import com.workpal.model.SpaceReservation;
import com.workpal.repository.implementInterfaces.SpaceRepository;
import com.workpal.repository.implementInterfaces.SpacesReservationRepository;

import java.sql.SQLException;
import java.util.List;

public class SpacesReservationService {
    private SpaceRepository spaceRepository = new SpaceRepository();
    private SpacesReservationRepository spacesReservationRepository = new SpacesReservationRepository();
    public boolean addSpaceReservation(int member_id, int space_id) throws SQLException {
        spacesReservationRepository.saveReservation(new SpaceReservation( 0, member_id, space_id, "not_yet"));
        return true;
    }
    public List<Space> getAllSpaceReservations() throws SQLException {
        return spacesReservationRepository.getAllSpaceReservations();
    }

    public boolean updateReservation(SpaceReservation service) throws SQLException {
        return spacesReservationRepository.updateSpaceReservation(service);
    }

    public boolean deleteReservation(int id) throws SQLException {
        return spacesReservationRepository.deleteSpaceReservation(id);
    }

    public List<Space> getAllSpaces() throws SQLException {
        return spacesReservationRepository.getAllSpaces();
    }
}
