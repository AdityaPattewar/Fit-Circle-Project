package com.flexforce.controller;

import java.util.List;

import com.flexforce.dao.ClubOwnerAnnouncementDAO;
import com.flexforce.model.club_owner.ClubOwnerAnnouncement;

public class ClubownerAnnouncementController {

    private final ClubOwnerAnnouncementDAO announcementDAO;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public ClubownerAnnouncementController() {

        announcementDAO =
                new ClubOwnerAnnouncementDAO();
    }

    // =========================================================
    // SAVE ANNOUNCEMENT
    // =========================================================

    public boolean saveAnnouncement(
            ClubOwnerAnnouncement announcement) {

        if (announcement == null) {

            System.out.println(
                    "Announcement cannot be null."
            );

            return false;
        }

        return announcementDAO
                .saveAnnouncement(
                        announcement
                );
    }

    // =========================================================
    // GET ONE ANNOUNCEMENT
    // =========================================================

    public ClubOwnerAnnouncement getAnnouncement(
            String announcementId) {

        if (announcementId == null ||
                announcementId.isEmpty()) {

            System.out.println(
                    "Announcement ID cannot be empty."
            );

            return null;
        }

        return announcementDAO
                .getAnnouncement(
                        announcementId
                );
    }

    // =========================================================
    // UPDATE ANNOUNCEMENT
    // =========================================================

    public boolean updateAnnouncement(
            ClubOwnerAnnouncement announcement) {

        if (announcement == null) {

            System.out.println(
                    "Announcement cannot be null."
            );

            return false;
        }

        return announcementDAO
                .updateAnnouncement(
                        announcement
                );
    }

    // =========================================================
    // DELETE ANNOUNCEMENT
    // =========================================================

    public boolean deleteAnnouncement(
            String announcementId) {

        if (announcementId == null ||
                announcementId.isEmpty()) {

            System.out.println(
                    "Announcement ID cannot be empty."
            );

            return false;
        }

        return announcementDAO
                .deleteAnnouncement(
                        announcementId
                );
    }

    // =========================================================
    // GET ALL ANNOUNCEMENTS
    // =========================================================

    public List<ClubOwnerAnnouncement>
    getAnnouncements() {

        return announcementDAO
                .getAnnouncements();
    }

    // =========================================================
    // GET ANNOUNCEMENTS OF CURRENT OWNER
    // =========================================================

    public List<ClubOwnerAnnouncement>
    getAnnouncementsByOwner(
            String ownerId) {

        if (ownerId == null ||
                ownerId.isEmpty()) {

            System.out.println(
                    "Owner ID cannot be empty."
            );

            return List.of();
        }

        return announcementDAO
                .getAnnouncementsByOwner(
                        ownerId
                );
    }
}