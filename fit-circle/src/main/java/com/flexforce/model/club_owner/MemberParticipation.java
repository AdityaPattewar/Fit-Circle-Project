
package com.flexforce.model.club_owner;

public class MemberParticipation {

    private String participationId;
    private String clubId;
    private String day;
    private int participants;

    // =========================================================
    // DEFAULT CONSTRUCTOR
    // Required for Firebase Firestore
    // =========================================================

    public MemberParticipation() {
    }

    // =========================================================
    // PARAMETERIZED CONSTRUCTOR
    // =========================================================

    public MemberParticipation(
            String participationId,
            String clubId,
            String day,
            int participants) {

        this.participationId = participationId;
        this.clubId = clubId;
        this.day = day;
        this.participants = participants;
    }

    // =========================================================
    // GET PARTICIPATION ID
    // =========================================================

    public String getParticipationId() {
        return participationId;
    }

    // =========================================================
    // SET PARTICIPATION ID
    // =========================================================

    public void setParticipationId(String participationId) {
        this.participationId = participationId;
    }

    // =========================================================
    // GET CLUB ID
    // =========================================================

    public String getClubId() {
        return clubId;
    }

    // =========================================================
    // SET CLUB ID
    // =========================================================

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    // =========================================================
    // GET DAY
    // =========================================================

    public String getDay() {
        return day;
    }

    // =========================================================
    // SET DAY
    // =========================================================

    public void setDay(String day) {
        this.day = day;
    }

    // =========================================================
    // GET PARTICIPANTS
    // =========================================================

    public int getParticipants() {
        return participants;
    }

    // =========================================================
    // SET PARTICIPANTS
    // =========================================================

    public void setParticipants(int participants) {
        this.participants = participants;
    }
}

