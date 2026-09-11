package com.flexforce.config;

import java.io.FileInputStream;
import java.io.InputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class FirebaseConfig {
  static {
        initializeFirebase();
    }

    private static void initializeFirebase() {

        try {

            InputStream serviceAccount =
                    FirebaseConfig.class
                            .getClassLoader()
                            .getResourceAsStream("firebase-demo.json");

            if (serviceAccount == null) {

                throw new RuntimeException(
                        "firebase-demo.json not found in resources folder"
                );
            }

            FirebaseOptions options =
                    FirebaseOptions.builder()
                            .setCredentials(
                                    GoogleCredentials.fromStream(
                                            serviceAccount
                                    )
                            )
                            .setProjectId("fit-circle-16e3f")
                            .build();

            if (FirebaseApp.getApps().isEmpty()) {

                FirebaseApp.initializeApp(options);

                System.out.println(
                        "Firebase initialized successfully"
                );

                System.out.println(
                        "Firebase Project ID = "
                                + options.getProjectId()
                );

            } else {

                System.out.println(
                        "Firebase already initialized"
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Firebase initialization failed"
            );

            e.printStackTrace();
        }
    }

    public static Firestore getFirebaseConfig() {

        System.out.println(
                ">>> FirebaseConfig.getFirebaseConfig() called"
        );

        try {

            if (FirebaseApp.getApps().isEmpty()) {

                System.out.println(
                        ">>> FirebaseApp is NOT initialized"
                );

                return null;
            }

            Firestore db =
                    FirestoreClient.getFirestore();

            System.out.println(
                    ">>> Firestore connection obtained successfully"
            );

            return db;

        } catch (Exception e) {

            System.out.println(
                    ">>> FIRESTORE CONNECTION FAILED"
            );

            e.printStackTrace();

            return null;
        }
    }
}