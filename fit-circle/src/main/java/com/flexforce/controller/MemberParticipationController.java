
package com.flexforce.controller;

import java.util.List;

import com.flexforce.dao.MemberParticipationDAO;
import com.flexforce.model.club_owner.MemberParticipation;

public class MemberParticipationController {

    // =========================================================
    // DAO
    // =========================================================

    private final MemberParticipationDAO participationDAO;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public MemberParticipationController() {
        participationDAO = new MemberParticipationDAO();
    }

    // =========================================================
    // SAVE PARTICIPATION
    // =========================================================

    public void saveParticipation(
            MemberParticipation participation) {

        if (participation == null) {

            System.out.println(
                    "Participation data is null.");

            return;
        }

        participationDAO.saveParticipation(
                participation);
    }

    // =========================================================
    // GET PARTICIPATION BY ID
    // =========================================================

    public MemberParticipation getParticipation(
            String participationId) {

        if (participationId == null ||
                participationId.trim().isEmpty()) {

            System.out.println(
                    "Participation ID is missing.");

            return null;
        }

        return participationDAO.getParticipation(
                participationId);
    }

    // =========================================================
    // UPDATE PARTICIPATION
    // =========================================================

    public void updateParticipation(
            MemberParticipation participation) {

        if (participation == null) {

            System.out.println(
                    "Participation data is null.");

            return;
        }

        participationDAO.updateParticipation(
                participation);
    }

    // =========================================================
    // DELETE PARTICIPATION
    // =========================================================

    public void deleteParticipation(
            String participationId) {

        if (participationId == null ||
                participationId.trim().isEmpty()) {

            System.out.println(
                    "Participation ID is missing.");

            return;
        }

        participationDAO.deleteParticipation(
                participationId);
    }

    // =========================================================
    // GET ALL PARTICIPATION
    // =========================================================

    public List<MemberParticipation>
            getAllParticipations() {

        return participationDAO
                .getParticipations();
    }

    // =========================================================
    // GET PARTICIPATION BY CLUB
    // =========================================================

    public List<MemberParticipation>
            getParticipationsByClub(
                    String clubId) {

        if (clubId == null ||
                clubId.trim().isEmpty()) {

            System.out.println(
                    "Club ID is missing.");

            return List.of();
        }

        return participationDAO
                .getParticipationsByClub(clubId);
    }
}



