
package com.flexforce.dao;

import java.util.ArrayList;
import java.util.List;

import com.flexforce.config.FirebaseConfig;
import com.flexforce.model.club_owner.Staff;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;

public class StaffDAO {

    // ============================================================
    // FIRESTORE
    // ============================================================

    private final Firestore db =
            FirebaseConfig.getFirebaseConfig();

    private static final String COLLECTION = "Staff";


    // ============================================================
    // ADD STAFF
    // ============================================================

    public String addStaff(Staff staff) throws Exception {

        if (staff == null) {
            throw new IllegalArgumentException(
                    "Staff data cannot be null."
            );
        }

        String staffId = String.valueOf(
                System.currentTimeMillis()
        );

        staff.setStaffId(staffId);

        db.collection(COLLECTION)
          .document(staffId)
          .set(staff)
          .get();

        System.out.println(
                "Staff Added Successfully: " + staffId
        );

        return staffId;
    }


    // ============================================================
    // SAVE STAFF
    // ============================================================

    public void saveStaff(Staff staff) {

        try {

            if (staff == null) {
                return;
            }

            String staffId = staff.getStaffId();

            if (staffId == null ||
                    staffId.trim().isEmpty()) {

                staffId = String.valueOf(
                        System.currentTimeMillis()
                );

                staff.setStaffId(staffId);
            }

            db.collection(COLLECTION)
              .document(staffId)
              .set(staff)
              .get();

            System.out.println(
                    "Staff Data Saved Successfully"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // ============================================================
    // GET STAFF BY ID
    // ============================================================

    public Staff getStaff(String staffId) {

        try {

            if (staffId == null ||
                    staffId.trim().isEmpty()) {

                return null;
            }

            ApiFuture<DocumentSnapshot> future =
                    db.collection(COLLECTION)
                      .document(staffId.trim())
                      .get();

            DocumentSnapshot document =
                    future.get();

            if (document.exists()) {

                return document.toObject(
                        Staff.class
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }


    // ============================================================
    // GET STAFF BY ID
    // ============================================================

    public Staff getStaffById(String staffId) {

        return getStaff(staffId);
    }


    // ============================================================
    // GET STAFF BY EMAIL
    // ============================================================

    public Staff getStaffByEmail(String email) {

        try {

            if (email == null ||
                    email.trim().isEmpty()) {

                return null;
            }

            ApiFuture<QuerySnapshot> future =
                    db.collection(COLLECTION)
                      .whereEqualTo(
                              "email",
                              email.trim()
                      )
                      .get();

            QuerySnapshot snapshot =
                    future.get();

            if (!snapshot.isEmpty()) {

                DocumentSnapshot document =
                        snapshot.getDocuments().get(0);

                return document.toObject(
                        Staff.class
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }


    // ============================================================
    // GET ALL STAFF
    // IMPORTANT: Controller uses this method
    // ============================================================

    public List<Staff> getStaff() {

        List<Staff> list =
                new ArrayList<>();

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection(COLLECTION)
                      .get();

            QuerySnapshot snapshot =
                    future.get();

            for (DocumentSnapshot document :
                    snapshot.getDocuments()) {

                Staff staff =
                        document.toObject(
                                Staff.class
                        );

                if (staff != null) {

                    list.add(staff);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }


    // ============================================================
    // GET ALL STAFF - ALIAS
    // ============================================================

    public List<Staff> getAllStaff() {

        return getStaff();
    }


    // ============================================================
    // GET STAFF BY CLUB ID
    // ============================================================

    public List<Staff> getStaffByClubId(
            String clubId
    ) {

        List<Staff> list =
                new ArrayList<>();

        try {

            if (clubId == null ||
                    clubId.trim().isEmpty()) {

                return list;
            }

            ApiFuture<QuerySnapshot> future =
                    db.collection(COLLECTION)
                      .whereEqualTo(
                              "clubId",
                              clubId.trim()
                      )
                      .get();

            QuerySnapshot snapshot =
                    future.get();

            for (DocumentSnapshot document :
                    snapshot.getDocuments()) {

                Staff staff =
                        document.toObject(
                                Staff.class
                        );

                if (staff != null) {

                    list.add(staff);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }


    // ============================================================
    // UPDATE STAFF
    // IMPORTANT: Returns boolean for Controller
    // ============================================================

    public boolean updateStaff(
            Staff staff
    ) throws Exception {

        if (staff == null) {
            return false;
        }

        if (staff.getStaffId() == null ||
                staff.getStaffId()
                    .trim()
                    .isEmpty()) {

            return false;
        }

        try {

            db.collection(COLLECTION)
              .document(
                      staff.getStaffId().trim()
              )
              .update(

                      "name",
                      safe(staff.getName()),

                      "role",
                      safe(staff.getRole()),

                      "email",
                      safe(staff.getEmail()),

                      "phone",
                      safe(staff.getPhone()),

                      "status",
                      safe(staff.getStatus()),

                      "clubId",
                      safe(staff.getClubId())
              )
              .get();

            System.out.println(
                    "Staff Data Updated Successfully"
            );

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }


    // ============================================================
    // DELETE STAFF
    // IMPORTANT: Returns boolean for Controller
    // ============================================================

    public boolean deleteStaff(
            String staffId
    ) throws Exception {

        if (staffId == null ||
                staffId.trim().isEmpty()) {

            return false;
        }

        try {

            db.collection(COLLECTION)
              .document(staffId.trim())
              .delete()
              .get();

            System.out.println(
                    "Staff Data Deleted Successfully"
            );

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }


    // ============================================================
    // GET STAFF BY ROLE
    // ============================================================

    public List<Staff> getStaffByRole(
            String role
    ) {

        List<Staff> list =
                new ArrayList<>();

        try {

            if (role == null ||
                    role.trim().isEmpty()) {

                return getStaff();
            }

            ApiFuture<QuerySnapshot> future =
                    db.collection(COLLECTION)
                      .whereEqualTo(
                              "role",
                              role.trim()
                      )
                      .get();

            QuerySnapshot snapshot =
                    future.get();

            for (DocumentSnapshot document :
                    snapshot.getDocuments()) {

                Staff staff =
                        document.toObject(
                                Staff.class
                        );

                if (staff != null) {

                    list.add(staff);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }


    // ============================================================
    // GET ACTIVE STAFF
    // ============================================================

    public List<Staff> getActiveStaff(
            String clubId
    ) {

        List<Staff> list =
                new ArrayList<>();

        try {

            if (clubId == null ||
                    clubId.trim().isEmpty()) {

                for (Staff staff : getStaff()) {

                    if (staff != null &&
                            "Active".equalsIgnoreCase(
                                    staff.getStatus()
                            )) {

                        list.add(staff);
                    }
                }

                return list;
            }

            ApiFuture<QuerySnapshot> future =
                    db.collection(COLLECTION)
                      .whereEqualTo(
                              "clubId",
                              clubId.trim()
                      )
                      .whereEqualTo(
                              "status",
                              "Active"
                      )
                      .get();

            QuerySnapshot snapshot =
                    future.get();

            for (DocumentSnapshot document :
                    snapshot.getDocuments()) {

                Staff staff =
                        document.toObject(
                                Staff.class
                        );

                if (staff != null) {

                    list.add(staff);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }


    // ============================================================
    // GET STAFF ON LEAVE
    // ============================================================

    public List<Staff> getStaffOnLeave(
            String clubId
    ) {

        List<Staff> list =
                new ArrayList<>();

        try {

            if (clubId == null ||
                    clubId.trim().isEmpty()) {

                for (Staff staff : getStaff()) {

                    if (staff != null &&
                            "On Leave".equalsIgnoreCase(
                                    staff.getStatus()
                            )) {

                        list.add(staff);
                    }
                }

                return list;
            }

            ApiFuture<QuerySnapshot> future =
                    db.collection(COLLECTION)
                      .whereEqualTo(
                              "clubId",
                              clubId.trim()
                      )
                      .whereEqualTo(
                              "status",
                              "On Leave"
                      )
                      .get();

            QuerySnapshot snapshot =
                    future.get();

            for (DocumentSnapshot document :
                    snapshot.getDocuments()) {

                Staff staff =
                        document.toObject(
                                Staff.class
                        );

                if (staff != null) {

                    list.add(staff);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }


    // ============================================================
    // TOTAL STAFF COUNT
    // ============================================================

    public int getTotalStaffCount(
            String clubId
    ) {

        if (clubId == null ||
                clubId.trim().isEmpty()) {

            return getStaff().size();
        }

        return getStaffByClubId(clubId).size();
    }


    // ============================================================
    // ACTIVE STAFF COUNT
    // ============================================================

    public int getActiveStaffCount(
            String clubId
    ) {

        return getActiveStaff(clubId).size();
    }


    // ============================================================
    // ON LEAVE STAFF COUNT
    // ============================================================

    public int getStaffOnLeaveCount(
            String clubId
    ) {

        return getStaffOnLeave(clubId).size();
    }


    // ============================================================
    // SAFE STRING
    // ============================================================

    private String safe(String value) {

        return value == null
                ? ""
                : value.trim();
    }
}