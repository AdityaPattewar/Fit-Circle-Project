package com.flexforce;

import com.flexforce.dao.UserInfoDAO;
import com.flexforce.model.user.UserInfo;

import java.util.List;

public class TestDB {
    public static void main(String[] args) {
        System.out.println("Fetching all UserInfo...");
        UserInfoDAO dao = new UserInfoDAO();
        List<UserInfo> list = dao.getUserInfos();
        System.out.println("Found " + list.size() + " UserInfo documents.");
        for (UserInfo u : list) {
            System.out.println(" - " + u.getUserId() + ": " + u.getName());
        }
        
        System.out.println("Fetching all Users...");
        com.flexforce.dao.UserProfileDAO profileDao = new com.flexforce.dao.UserProfileDAO();
        try {
            com.google.api.core.ApiFuture<com.google.cloud.firestore.QuerySnapshot> future = com.flexforce.config.FirebaseConfig.getFirebaseConfig().collection("Users").get();
            java.util.List<com.google.cloud.firestore.QueryDocumentSnapshot> docs = future.get().getDocuments();
            System.out.println("Found " + docs.size() + " Users documents.");
            for (com.google.cloud.firestore.QueryDocumentSnapshot doc : docs) {
                System.out.println(" - " + doc.getId() + ": " + doc.getString("fullName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
