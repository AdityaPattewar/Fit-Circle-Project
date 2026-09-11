
package com.flexforce.controller;

import java.util.List;

import com.flexforce.dao.ClubOwnerDAO;
import com.flexforce.model.club_owner.ClubOwner;

public class ClubownerController {

    private ClubOwnerDAO clubOwnerDAO;

    public ClubownerController() {
        clubOwnerDAO = new ClubOwnerDAO();
    }

    // =========================================================
    // CREATE
    // =========================================================

    public void addClubOwner(ClubOwner owner) {
        clubOwnerDAO.saveClubOwner(owner);
    }

    // =========================================================
    // READ - ONE
    // =========================================================

    public ClubOwner getClubOwner(String ownerId) {
        return clubOwnerDAO.getClubOwner(ownerId);
    }

    // =========================================================
    // READ - ALL
    // =========================================================

    public List<ClubOwner> getAllClubOwners() {
        return clubOwnerDAO.getClubOwners();
    }

    // =========================================================
    // UPDATE
    // =========================================================

    public boolean updateClubOwner(ClubOwner owner) {

        try {

            clubOwnerDAO.updateClubOwner(owner);

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // DELETE
    // =========================================================

    public void deleteClubOwner(String ownerId) {
        clubOwnerDAO.deleteClubOwner(ownerId);
    }
}

