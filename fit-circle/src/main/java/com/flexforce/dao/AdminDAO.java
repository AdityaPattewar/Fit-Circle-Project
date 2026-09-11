package com.flexforce.dao;

import java.util.ArrayList;
import java.util.List;

import com.flexforce.config.FirebaseConfig;
import com.flexforce.model.admin.Admin;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

public class AdminDAO {

    private Firestore db =
            FirebaseConfig.getFirebaseConfig();


    // ============================================================
    // SAVE ADMIN
    // ============================================================

    public void saveAdmin(Admin admin) {

        try {

            db.collection("Admins")
              .document(String.valueOf(admin.getAdminId()))
              .create(admin);

            System.out.println(
                    "Admin Data Inserted"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // ============================================================
    // GET ADMIN BY ID
    // ============================================================

    public Admin getAdmin(String adminId) {

        try {

            ApiFuture<DocumentSnapshot> future =
                    db.collection("Admins")
                      .document(String.valueOf(adminId))
                      .get();

            DocumentSnapshot document =
                    future.get();

            if (document.exists()) {

                return document.toObject(Admin.class);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }


    // ============================================================
    // GET ADMIN BY EMAIL
    // ============================================================

    public Admin getAdminByEmail(String email) {

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection("Admins")
                      .whereEqualTo("email", email)
                      .get();

            QuerySnapshot snapshot =
                    future.get();

            if (!snapshot.isEmpty()) {

                DocumentSnapshot document =
                        snapshot.getDocuments().get(0);

                return document.toObject(Admin.class);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }


    // ============================================================
    // UPDATE ADMIN
    // ============================================================

    public void updateAdmin(Admin admin) {

        try {

            db.collection("Admins")
              .document(String.valueOf(admin.getAdminId()))
              .update(
                  "name", admin.getName(),
                  "email", admin.getEmail(),
                  "profileImage", admin.getProfileImage(),
                  "role", admin.getRole(),
                  "status", admin.getStatus()
              );

            System.out.println(
                    "Admin Data Updated"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // ============================================================
    // DELETE ADMIN
    // ============================================================

    public void deleteAdmin(String adminId) {

        try {

            db.collection("Admins")
              .document(String.valueOf(adminId))
              .delete();

            System.out.println(
                    "Admin Data Deleted"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // ============================================================
    // GET ALL ADMINS
    // ============================================================

    public List<Admin> getAdmins() {

        List<Admin> list =
                new ArrayList<>();

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection("Admins")
                      .get();

            QuerySnapshot snapshot =
                    future.get();

            for (DocumentSnapshot doc :
                    snapshot.getDocuments()) {

                Admin admin =
                        doc.toObject(Admin.class);

                list.add(admin);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }
}