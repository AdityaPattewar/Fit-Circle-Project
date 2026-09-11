package com.flexforce.controller;


import java.util.List;

import com.flexforce.dao.UserClubDao;
import com.flexforce.model.common_for_user_clubowner.UserClub;

public class UserClubController {

    private final UserClubDao clubDAO;

    public UserClubController() {
        clubDAO = new UserClubDao();
    }

    // Save club data
    public void saveClub(UserClub club) {

        if (club == null) {
            System.out.println("Club data cannot be null.");
            return;
        }

        clubDAO.saveClub(club);
    }

    // Get club data of one club
    public UserClub getClub(String clubId) {

        if (clubId == null || clubId.isEmpty()) {
            System.out.println("Club ID cannot be empty.");
            return null;
        }

        return clubDAO.getClub(clubId);
    }

    // Update club data
    public void updateClub(UserClub club) {

        if (club == null) {
            System.out.println("Club data cannot be null.");
            return;
        }

        clubDAO.updateClub(club);
    }

    // Delete club
    public void deleteClub(String clubId) {

        if (clubId == null || clubId.isEmpty()) {
            System.out.println("Club ID cannot be empty.");
            return;
        }

        clubDAO.deleteClub(clubId);
    }

    // Get all clubs
    public List<UserClub> getAllClubs() {

        return clubDAO.getClubs();
    }
}
