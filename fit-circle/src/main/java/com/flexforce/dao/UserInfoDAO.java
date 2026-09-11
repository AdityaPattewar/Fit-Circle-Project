package com.flexforce.dao;

import java.util.ArrayList;
import java.util.List;

import com.flexforce.config.FirebaseConfig;
import com.flexforce.model.user.UserInfo;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

public class UserInfoDAO {

    private Firestore db = FirebaseConfig.getFirebaseConfig();


        public boolean saveUserInfo(UserInfo userInfo) {
            try {
                db.collection("UserInfo")
                .document(userInfo.getUserId())
                .set(userInfo)
                .get();

                System.out.println("User Info Data Saved");

                return true;

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

    public UserInfo getUserInfo(String userId) {
        try {
            ApiFuture<DocumentSnapshot> future =
                    db.collection("UserInfo")
                      .document(String.valueOf(userId))
                      .get();

            DocumentSnapshot document = future.get();

            if (document.exists()) {
                return document.toObject(UserInfo.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }



    public void updateUserInfo(UserInfo userInfo) {
        try {
            db.collection("UserInfo")
              .document(String.valueOf(userInfo.getUserId()))
              .update(
                  "name", userInfo.getName(),
                  "email", userInfo.getEmail(),
                  "phone", userInfo.getPhone(),
                  "profileImage", userInfo.getProfileImage(),
                  "dateOfBirth", userInfo.getDateOfBirth(),
                  "gender", userInfo.getGender(),
                  "address", userInfo.getAddress(),
                  "height", userInfo.getHeight(),
                  "weight", userInfo.getWeight(),
                  "goal", userInfo.getGoal(),
                  "workoutDays", userInfo.getWorkoutDays(),
                  "role", userInfo.getRole(),
                  "status", userInfo.getStatus(),
                  "plan", userInfo.getPlan(),
                  "membershipStartDate", userInfo.getmembershipStartDate()
              );

            System.out.println("User Info Data Updated");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   

    public void deleteUserInfo(String userId) {
        try {
            db.collection("UserInfo")
              .document(String.valueOf(userId))
              .delete();

            System.out.println("User Info Data Deleted");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public List<UserInfo> getUserInfos() {

        List<UserInfo> list = new ArrayList<>();

        try {
            ApiFuture<QuerySnapshot> future =
                    db.collection("UserInfo").get();

            QuerySnapshot snapshot = future.get();

            for (DocumentSnapshot doc : snapshot.getDocuments()) {

                UserInfo userInfo = doc.toObject(UserInfo.class);

                list.add(userInfo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
