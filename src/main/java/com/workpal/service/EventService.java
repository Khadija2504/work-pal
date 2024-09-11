package com.workpal.service;

import com.workpal.model.Event;
import com.workpal.repository.EventRepository;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class EventService {
    private EventRepository eventRepository = new EventRepository();
    public boolean addEvent(String name, String description, String location, Date date, String type, String policies, int places_num, int manager_id) throws SQLException {
        eventRepository.saveEvent(new Event( name, description, location, date, type, policies, places_num, manager_id, 0));
        return true;
    }
    public List<Event> getAllEvents() throws SQLException {
        return eventRepository.getAllEvents();
    }

    public boolean updateEvent(Event event) throws SQLException {
        return eventRepository.updateEvent(event);
    }

    public boolean deleteEvent(int id) throws SQLException {
        return eventRepository.deleteEvent(id);
    }
}
