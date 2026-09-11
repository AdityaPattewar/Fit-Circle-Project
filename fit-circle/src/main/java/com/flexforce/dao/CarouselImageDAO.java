package com.flexforce.dao;

import java.util.ArrayList;
import java.util.List;

import com.flexforce.config.FirebaseConfig;
import com.flexforce.model.admin.CarouselImage;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.Query;

public class CarouselImageDAO {

    private Firestore db;

    public CarouselImageDAO() {
        this.db = FirebaseConfig.getFirebaseConfig();
    }

    public void addCarouselImage(CarouselImage image) {
        try {
            DocumentReference docRef = db.collection("CarouselImages").document(image.getImageId());
            docRef.set(image).get();
            System.out.println("CarouselImage added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<CarouselImage> getAllImages() {
        List<CarouselImage> images = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> future = db.collection("CarouselImages")
                    .orderBy("uploadedAt", Query.Direction.DESCENDING)
                    .get();
            QuerySnapshot snapshot = future.get();
            for (DocumentSnapshot doc : snapshot.getDocuments()) {
                CarouselImage image = doc.toObject(CarouselImage.class);
                if (image != null) {
                    images.add(image);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return images;
    }

    public void deleteImage(String imageId) {
        try {
            db.collection("CarouselImages").document(imageId).delete().get();
            System.out.println("CarouselImage deleted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
