package com.flexforce.dao;



import java.util.ArrayList;
import java.util.List;

import com.flexforce.config.FirebaseConfig;
import com.flexforce.model.club_owner.ClubMember;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

public class ClubMemberDAO {

    private Firestore db =
            FirebaseConfig.getFirebaseConfig();


    // ============================================================
    // SAVE MEMBER
    // ============================================================

    public void saveMember(ClubMember member) {

        try {

            db.collection("ClubMembers")
              .document(member.getMemberId())
              .create(member);

            System.out.println(
                    "Member Data Inserted"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // ============================================================
    // GET MEMBER BY ID
    // ============================================================

    public ClubMember getMember(String memberId) {

        try {

            ApiFuture<DocumentSnapshot> future =
                    db.collection("ClubMembers")
                      .document(memberId)
                      .get();

            DocumentSnapshot document =
                    future.get();

            if (document.exists()) {

                return document.toObject(
                        ClubMember.class
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }


    // ============================================================
    // GET MEMBERS BY CLUB ID
    // ============================================================

    public List<ClubMember> getMembersByClubId(
            String clubId) {

        List<ClubMember> list =
                new ArrayList<>();

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection("ClubMembers")
                      .whereEqualTo(
                              "clubId",
                              clubId
                      )
                      .get();

            QuerySnapshot snapshot =
                    future.get();

            for (
                    DocumentSnapshot doc :
                    snapshot.getDocuments()
            ) {

                ClubMember member =
                        doc.toObject(
                                ClubMember.class
                        );

                if (member != null) {

                    list.add(member);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }


    // ============================================================
    // GET ALL MEMBERS
    // ============================================================

    public List<ClubMember> getMembers() {

        List<ClubMember> list =
                new ArrayList<>();

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection("ClubMembers")
                      .get();

            QuerySnapshot snapshot =
                    future.get();

            for (
                    DocumentSnapshot doc :
                    snapshot.getDocuments()
            ) {

                ClubMember member =
                        doc.toObject(
                                ClubMember.class
                        );

                if (member != null) {

                    list.add(member);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }


    // ============================================================
    // UPDATE MEMBER
    // ============================================================

    public void updateMember(
            ClubMember member) {

        try {

            db.collection("ClubMembers")
              .document(member.getMemberId())
              .update(

                  "name",
                  member.getName(),

                  "email",
                  member.getEmail(),

                  "phone",
                  member.getPhone(),

                  "membership",
                  member.getMembership(),

                  "joinDate",
                  member.getJoinDate(),

                  "status",
                  member.getStatus(),

                  "clubId",
                  member.getClubId()
              );

            System.out.println(
                    "Member Data Updated"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // ============================================================
    // DELETE MEMBER
    // ============================================================

    public void deleteMember(
            String memberId) {

        try {

            db.collection("ClubMembers")
              .document(memberId)
              .delete();

            System.out.println(
                    "Member Data Deleted"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
