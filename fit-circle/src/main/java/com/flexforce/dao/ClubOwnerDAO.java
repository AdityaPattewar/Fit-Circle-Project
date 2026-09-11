
package com.flexforce.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.flexforce.config.FirebaseConfig;
import com.flexforce.model.club_owner.ClubOwner;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.SetOptions;

public class ClubOwnerDAO {

    private Firestore db =
            FirebaseConfig.getFirebaseConfig();

    // =========================================================
    // SAVE CLUB OWNER
    // =========================================================

    public boolean saveClubOwner(ClubOwner owner) {

        try {

            if (owner == null) {
                return false;
            }

            // Every newly registered club owner
            // must wait for admin approval.
            owner.setStatus("PENDING");

            db.collection("ClubOwners")
                    .document(owner.getOwnerId())
                    .set(owner)
                    .get();

            System.out.println(
                    "Club Owner Registration Saved - PENDING"
            );

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // GET CLUB OWNER
    // =========================================================

    public ClubOwner getClubOwner(
            String ownerId) {

        try {

            if (ownerId == null ||
                    ownerId.trim().isEmpty()) {

                return null;
            }

            ApiFuture<DocumentSnapshot> future =
                    db.collection("ClubOwners")
                            .document(ownerId)
                            .get();

            DocumentSnapshot document =
                    future.get();

            if (document.exists()) {

                ClubOwner owner =
                        document.toObject(
                                ClubOwner.class
                        );

                if (owner != null) {

                    // Set document ID
                    owner.setOwnerId(
                            document.getId()
                    );

                    // Existing Firebase field mapping
                    owner.setAddress(
                            getString(
                                    document,
                                    "clubAddress"
                            )
                    );

                    Object capacity =
                            document.get("capacity");

                    if (capacity instanceof Number) {

                        owner.setMaxCapacity(
                                ((Number) capacity)
                                        .intValue()
                        );
                    }

                    owner.setPrice(
                            getString(
                                    document,
                                    "startingPrice"
                            )
                    );

                    // profileImage is automatically
                    // mapped by Firestore because
                    // ClubOwner has get/setProfileImage().
                }

                return owner;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }


    // =========================================================
    // UPDATE CLUB OWNER
    // =========================================================

    public boolean updateClubOwner(
            ClubOwner owner) {

        try {

            if (owner == null) {

                return false;
            }

            if (owner.getOwnerId() == null ||
                    owner.getOwnerId()
                            .trim()
                            .isEmpty()) {

                System.out.println(
                        "Owner ID is required."
                );

                return false;
            }

            Map<String, Object> data =
                    new HashMap<>();


            // =====================================================
            // CLUB NAME
            // =====================================================

            data.put(
                    "clubName",
                    owner.getClubName()
            );


            // =====================================================
            // CATEGORY
            // =====================================================

            data.put(
                    "category",
                    owner.getCategory()
            );


            // =====================================================
            // DESCRIPTION
            // =====================================================

            data.put(
                    "description",
                    owner.getDescription()
            );


            // =====================================================
            // PHONE
            // =====================================================

            data.put(
                    "phone",
                    owner.getPhone()
            );


            // =====================================================
            // CLUB ADDRESS
            // =====================================================

            data.put(
                    "clubAddress",
                    owner.getAddress()
            );


            // =====================================================
            // WEBSITE
            // =====================================================

            data.put(
                    "website",
                    owner.getWebsite()
            );


            // =====================================================
            // CAPACITY
            // =====================================================

            data.put(
                    "capacity",
                    owner.getMaxCapacity()
            );


            // =====================================================
            // STARTING PRICE
            // =====================================================

            data.put(
                    "startingPrice",
                    owner.getPrice()
            );


            // =====================================================
            // AMENITIES
            // =====================================================

            data.put(
                    "amenities",
                    owner.getAmenities()
            );


            // =====================================================
            // PROFILE IMAGE
            //
            // This contains the Cloudinary secure URL.
            // =====================================================

            data.put(
                    "profileImage",
                    owner.getProfileImage()
            );


            // =====================================================
            // UPDATE FIRESTORE
            // =====================================================

            db.collection("ClubOwners")
                    .document(owner.getOwnerId())
                    .set(
                            data,
                            SetOptions.merge()
                    )
                    .get();

            System.out.println(
                    "Club Owner Data Updated"
            );

            System.out.println(
                    "Profile Image URL = "
                            + owner.getProfileImage()
            );

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // DELETE CLUB OWNER
    // =========================================================

    public boolean deleteClubOwner(
            String ownerId) {

        try {

            if (ownerId == null ||
                    ownerId.trim().isEmpty()) {

                return false;
            }

            db.collection("ClubOwners")
                    .document(ownerId)
                    .delete()
                    .get();

            System.out.println(
                    "Club Owner Data Deleted"
            );

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // GET ALL CLUB OWNERS
    // =========================================================

    public List<ClubOwner> getClubOwners() {

        List<ClubOwner> list =
                new ArrayList<>();

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection("ClubOwners")
                            .get();

            QuerySnapshot snapshot =
                    future.get();

            for (DocumentSnapshot doc :
                    snapshot.getDocuments()) {

                ClubOwner owner =
                        doc.toObject(
                                ClubOwner.class
                        );

                if (owner != null) {

                    owner.setOwnerId(
                            doc.getId()
                    );

                    owner.setAddress(
                            getString(
                                    doc,
                                    "clubAddress"
                            )
                    );

                    Object capacity =
                            doc.get("capacity");

                    if (capacity instanceof Number) {

                        owner.setMaxCapacity(
                                ((Number) capacity)
                                        .intValue()
                        );
                    }

                    owner.setPrice(
                            getString(
                                    doc,
                                    "startingPrice"
                            )
                    );

                    list.add(owner);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }


    // =========================================================
    // GET PENDING CLUB OWNERS
    // =========================================================

    public List<ClubOwner> getPendingClubOwners() {

        List<ClubOwner> list =
                new ArrayList<>();

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection("ClubOwners")
                            .whereEqualTo(
                                    "status",
                                    "PENDING"
                            )
                            .get();

            QuerySnapshot snapshot =
                    future.get();

            for (DocumentSnapshot doc :
                    snapshot.getDocuments()) {

                ClubOwner owner =
                        doc.toObject(
                                ClubOwner.class
                        );

                if (owner != null) {

                    owner.setOwnerId(
                            doc.getId()
                    );

                    owner.setAddress(
                            getString(
                                    doc,
                                    "clubAddress"
                            )
                    );

                    Object capacity =
                            doc.get("capacity");

                    if (capacity instanceof Number) {

                        owner.setMaxCapacity(
                                ((Number) capacity)
                                        .intValue()
                        );
                    }

                    owner.setPrice(
                            getString(
                                    doc,
                                    "startingPrice"
                            )
                    );

                    list.add(owner);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }


    // =========================================================
    // APPROVE CLUB OWNER
    // =========================================================

    public boolean approveClubOwner(
            String ownerId) {

        try {

            if (ownerId == null ||
                    ownerId.trim().isEmpty()) {

                return false;
            }

            db.collection("ClubOwners")
                    .document(ownerId)
                    .update(
                            "status",
                            "ACTIVE"
                    )
                    .get();

            System.out.println(
                    "Club Owner Approved: "
                            + ownerId
            );

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // REJECT CLUB OWNER
    // =========================================================

    public boolean rejectClubOwner(
            String ownerId) {

        try {

            if (ownerId == null ||
                    ownerId.trim().isEmpty()) {

                return false;
            }

            db.collection("ClubOwners")
                    .document(ownerId)
                    .update(
                            "status",
                            "REJECTED"
                    )
                    .get();

            System.out.println(
                    "Club Owner Rejected: "
                            + ownerId
            );

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // HELPER
    // =========================================================

    private String getString(
            DocumentSnapshot document,
            String field) {

        Object value =
                document.get(field);

        if (value == null) {
            return "";
        }

        return String.valueOf(value);
    }
}

