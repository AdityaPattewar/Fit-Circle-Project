package com.flexforce.model.club_owner;


public class ClubOwnerAnnouncement {

    private String announcementId;
    private String clubId;
    private String createdBy;
    private String title;
    private String message;
    private String date;
    private String status;

    public ClubOwnerAnnouncement() {
    }

    public ClubOwnerAnnouncement(String announcementId, String clubId,
                                 String createdBy, String title,
                                 String message, String date,
                                 String status) {
        this.announcementId = announcementId;
        this.clubId = clubId;
        this.createdBy = createdBy;
        this.title = title;
        this.message = message;
        this.date = date;
        this.status = status;
    }

    public String getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(String announcementId) {
        this.announcementId = announcementId;
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}