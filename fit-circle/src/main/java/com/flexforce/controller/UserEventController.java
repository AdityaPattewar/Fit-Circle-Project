package com.flexforce.controller;

import java.util.List;

import com.flexforce.dao.UserEventDAO;
import com.flexforce.model.common_for_user_clubowner.UserEvent;

public class UserEventController {

    private UserEventDAO eventDAO;

    public UserEventController() {
       UserEventDAO eventDAO = new UserEventDAO();
    }

    // CREATE
    public void addEvent(UserEvent event) {

        eventDAO.saveEvent(event);
    }

    // READ ONE
    public UserEvent getEvent(String eventId) {

        return eventDAO.getEvent(eventId);
    }

    // UPDATE
    public void updateEvent(UserEvent event) {

        eventDAO.updateEvent(event);
    }

    // DELETE
    public void deleteEvent(String eventId) {

        eventDAO.deleteEvent(eventId);
    }

    // READ ALL
    public List<UserEvent> getAllEvents() {

        return eventDAO.getEvents();
    }
}
