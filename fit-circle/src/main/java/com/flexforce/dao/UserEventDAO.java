
package com.flexforce.dao;

import java.util.ArrayList;
import java.util.List;

import com.flexforce.config.FirebaseConfig;
import com.flexforce.model.common_for_user_clubowner.UserEvent;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;

public class UserEventDAO {

    private final Firestore db;

    public UserEventDAO() {
        db = FirebaseConfig.getFirebaseConfig();
    }

    // =========================================================
    // SAVE EVENT
    // =========================================================

    public void saveEvent(UserEvent event) {

        if (event == null || event.getEventId() == null
                || event.getEventId().trim().isEmpty()) {

            System.out.println("Invalid event data.");
            return;
        }

        try {

            db.collection("UserEvent")
              .document(event.getEventId())
              .create(event)
              .get();

            System.out.println("Event inserted successfully.");

        } catch (Exception e) {

            System.out.println("Error inserting event.");
            e.printStackTrace();
        }
    }

    // =========================================================
    // GET EVENT BY ID
    // =========================================================

    public UserEvent getEvent(String eventId) {

        if (eventId == null || eventId.trim().isEmpty()) {
            return null;
        }

        try {

            ApiFuture<DocumentSnapshot> future =
                    db.collection("UserEvent")
                      .document(eventId)
                      .get();

            DocumentSnapshot document = future.get();

            if (document.exists()) {

                return document.toObject(UserEvent.class);
            }

        } catch (Exception e) {

            System.out.println("Error getting event.");
            e.printStackTrace();
        }

        return null;
    }

    // =========================================================
    // UPDATE EVENT
    // =========================================================

    public void updateEvent(UserEvent event) {

        if (event == null || event.getEventId() == null) {
            System.out.println("Invalid event data.");
            return;
        }

        try {

            db.collection("UserEvent")
              .document(event.getEventId())
              .update(
                      "clubId", event.getClubId(),
                      "createdBy", event.getCreatedBy(),
                      "title", event.getTitle(),
                      "description", event.getDescription(),
                      "date", event.getDate(),
                      "time", event.getTime(),
                      "location", event.getLocation(),
                      "image", event.getImage()
              )
              .get();

            System.out.println("Event updated successfully.");

        } catch (Exception e) {

            System.out.println("Error updating event.");
            e.printStackTrace();
        }
    }

    // =========================================================
    // DELETE EVENT
    // =========================================================

    public void deleteEvent(String eventId) {

        if (eventId == null || eventId.trim().isEmpty()) {
            return;
        }

        try {

            db.collection("UserEvent")
              .document(eventId)
              .delete()
              .get();

            System.out.println("Event deleted successfully.");

        } catch (Exception e) {

            System.out.println("Error deleting event.");
            e.printStackTrace();
        }
    }

    // =========================================================
    // GET ALL EVENTS
    // =========================================================

    public List<UserEvent> getEvents() {

        List<UserEvent> list = new ArrayList<>();

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection("UserEvent").get();

            QuerySnapshot snapshot = future.get();

            for (DocumentSnapshot doc : snapshot.getDocuments()) {

                UserEvent event =
                        doc.toObject(UserEvent.class);

                if (event != null) {
                    list.add(event);
                }
            }

        } catch (Exception e) {

            System.out.println("Error getting events.");
            e.printStackTrace();
        }

        return list;
    }

    // =========================================================
    // GET EVENTS BY CLUB
    // =========================================================

    public List<UserEvent> getEventsByClub(String clubId) {

        List<UserEvent> list = new ArrayList<>();

        if (clubId == null || clubId.trim().isEmpty()) {
            return list;
        }

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection("UserEvent")
                      .whereEqualTo("clubId", clubId)
                      .get();

            QuerySnapshot snapshot = future.get();

            for (DocumentSnapshot doc : snapshot.getDocuments()) {

                UserEvent event =
                        doc.toObject(UserEvent.class);

                if (event != null) {
                    list.add(event);
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Error getting club events.");

            e.printStackTrace();
        }

        return list;
    }

    // =========================================================
    // GET EVENTS BY OWNER
    // =========================================================

    public List<UserEvent> getEventsByOwner(String ownerId) {

        List<UserEvent> list = new ArrayList<>();

        if (ownerId == null || ownerId.trim().isEmpty()) {
            return list;
        }

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection("UserEvent")
                      .whereEqualTo("createdBy", ownerId)
                      .get();

            QuerySnapshot snapshot = future.get();

            for (DocumentSnapshot doc : snapshot.getDocuments()) {

                UserEvent event =
                        doc.toObject(UserEvent.class);

                if (event != null) {
                    list.add(event);
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Error getting owner events.");

            e.printStackTrace();
        }

        return list;
    }
    // =========================================================
    // GET CLUB ID BY OWNER
    // =========================================================

    public String getClubIdByOwner(String ownerId) {

        if (ownerId == null || ownerId.trim().isEmpty()) {
            return null;
        }

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection("UserEvent")
                    .whereEqualTo("createdBy", ownerId)
                    .limit(1)
                    .get();

            QuerySnapshot snapshot = future.get();

            if (!snapshot.isEmpty()) {

                DocumentSnapshot document =
                        snapshot.getDocuments().get(0);

                UserEvent event =
                        document.toObject(UserEvent.class);

                if (event != null) {
                    return event.getClubId();
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Error getting club ID by owner.");

            e.printStackTrace();
        }

        return null;
    }
}