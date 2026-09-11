package com.flexforce.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.flexforce.config.FirebaseConfig;
import com.flexforce.model.club_owner.MemberActivity;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

public class MemberActivityDAO {

    private final Firestore db;
    private final String COLLECTION_NAME = "MemberActivities";

    public MemberActivityDAO() {
        this.db = FirebaseConfig.getFirebaseConfig();
    }

    public void addActivity(MemberActivity activity) {
        try {
            if (activity.getActivityId() == null || activity.getActivityId().isEmpty()) {
                DocumentReference newRef = db.collection(COLLECTION_NAME).document();
                activity.setActivityId(newRef.getId());
                ApiFuture<WriteResult> result = newRef.set(activity);
                System.out.println("Added activity at: " + result.get().getUpdateTime());
            } else {
                ApiFuture<WriteResult> result = db.collection(COLLECTION_NAME).document(activity.getActivityId()).set(activity);
                System.out.println("Updated activity at: " + result.get().getUpdateTime());
            }
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error adding/updating member activity: " + e.getMessage());
        }
    }

    public List<MemberActivity> getActivitiesByMember(String memberId) {
        List<MemberActivity> activities = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).whereEqualTo("memberId", memberId).get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                activities.add(document.toObject(MemberActivity.class));
            }
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error fetching member activities: " + e.getMessage());
        }
        return activities;
    }
}
