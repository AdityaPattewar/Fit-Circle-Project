package com.flexforce.dao;

import java.util.ArrayList;
import java.util.List;

import com.flexforce.config.FirebaseConfig;
import com.flexforce.model.club_owner.ClubOwnerAnnouncement;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;

public class ClubOwnerAnnouncementDAO {

    private Firestore db = FirebaseConfig.getFirebaseConfig();

    // =========================================================
    // SAVE ANNOUNCEMENT
    // =========================================================

    public boolean saveAnnouncement(
            ClubOwnerAnnouncement announcement) {

        try {

            if (announcement == null) {

                System.out.println(
                        "Announcement is null."
                );

                return false;
            }

            if (announcement.getAnnouncementId() == null ||
                    announcement.getAnnouncementId().isEmpty()) {

                System.out.println(
                        "Announcement ID is empty."
                );

                return false;
            }

            db.collection("ClubOwnerAnnouncements")
                    .document(
                            announcement.getAnnouncementId()
                    )
                    .set(announcement)
                    .get();

            System.out.println(
                    "Club Owner Announcement Data Inserted Successfully"
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error while saving announcement"
            );

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // GET ONE ANNOUNCEMENT
    // =========================================================

    public ClubOwnerAnnouncement getAnnouncement(
            String announcementId) {

        try {

            if (announcementId == null ||
                    announcementId.isEmpty()) {

                System.out.println(
                        "Announcement ID is empty."
                );

                return null;
            }

            ApiFuture<DocumentSnapshot> future =
                    db.collection("ClubOwnerAnnouncements")
                            .document(announcementId)
                            .get();

            DocumentSnapshot document =
                    future.get();

            if (document.exists()) {

                return document.toObject(
                        ClubOwnerAnnouncement.class
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Error while getting announcement"
            );

            e.printStackTrace();
        }

        return null;
    }

    // =========================================================
    // UPDATE ANNOUNCEMENT
    // =========================================================

    public boolean updateAnnouncement(
            ClubOwnerAnnouncement announcement) {

        try {

            if (announcement == null) {

                System.out.println(
                        "Announcement is null."
                );

                return false;
            }

            if (announcement.getAnnouncementId() == null ||
                    announcement.getAnnouncementId().isEmpty()) {

                System.out.println(
                        "Announcement ID is empty."
                );

                return false;
            }

            db.collection("ClubOwnerAnnouncements")
                    .document(
                            announcement.getAnnouncementId()
                    )
                    .update(

                            "clubId",
                            announcement.getClubId(),

                            "createdBy",
                            announcement.getCreatedBy(),

                            "title",
                            announcement.getTitle(),

                            "message",
                            announcement.getMessage(),

                            "date",
                            announcement.getDate(),

                            "status",
                            announcement.getStatus()

                    )
                    .get();

            System.out.println(
                    "Club Owner Announcement Data Updated Successfully"
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error while updating announcement"
            );

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // DELETE ANNOUNCEMENT
    // =========================================================

    public boolean deleteAnnouncement(
            String announcementId) {

        try {

            if (announcementId == null ||
                    announcementId.isEmpty()) {

                System.out.println(
                        "Announcement ID is empty."
                );

                return false;
            }

            db.collection("ClubOwnerAnnouncements")
                    .document(announcementId)
                    .delete()
                    .get();

            System.out.println(
                    "Club Owner Announcement Data Deleted Successfully"
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error while deleting announcement"
            );

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // GET ALL ANNOUNCEMENTS
    // =========================================================

    public List<ClubOwnerAnnouncement>
    getAnnouncements() {

        List<ClubOwnerAnnouncement> list =
                new ArrayList<>();

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection("ClubOwnerAnnouncements")
                            .get();

            QuerySnapshot snapshot =
                    future.get();

            for (DocumentSnapshot doc :
                    snapshot.getDocuments()) {

                ClubOwnerAnnouncement announcement =
                        doc.toObject(
                                ClubOwnerAnnouncement.class
                        );

                if (announcement != null) {

                    list.add(announcement);
                }
            }

            System.out.println(
                    "Total announcements found : "
                    + list.size()
            );

        } catch (Exception e) {

            System.out.println(
                    "Error while getting all announcements"
            );

            e.printStackTrace();
        }

        return list;
    }

    // =========================================================
    // GET ANNOUNCEMENTS OF CURRENT CLUB OWNER
    // =========================================================

    public List<ClubOwnerAnnouncement>
    getAnnouncementsByOwner(
            String ownerId) {

        List<ClubOwnerAnnouncement> list =
                new ArrayList<>();

        try {

            if (ownerId == null ||
                    ownerId.isEmpty()) {

                System.out.println(
                        "Owner ID is empty."
                );

                return list;
            }

            ApiFuture<QuerySnapshot> future =
                    db.collection("ClubOwnerAnnouncements")
                            .whereEqualTo(
                                    "createdBy",
                                    ownerId
                            )
                            .get();

            QuerySnapshot snapshot =
                    future.get();

            for (DocumentSnapshot doc :
                    snapshot.getDocuments()) {

                ClubOwnerAnnouncement announcement =
                        doc.toObject(
                                ClubOwnerAnnouncement.class
                        );

                if (announcement != null) {

                    list.add(announcement);
                }
            }

            System.out.println(
                    "Announcements found for owner "
                    + ownerId
                    + " : "
                    + list.size()
            );

        } catch (Exception e) {

            System.out.println(
                    "Error while getting owner announcements"
            );

            e.printStackTrace();
        }

        return list;
    }
}