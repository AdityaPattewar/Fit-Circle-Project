package com.flexforce.controller;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

public class AuthControllerlogin {

    private static final String API_KEY =
            "AIzaSyC2-ktfIIgp62zRq7wA3sxI00JILCHUfX0";

    // Stores the Firebase UID of the currently logged-in user
    private static String currentUserId;

    // Get currently logged-in user's Firebase UID
    public static String getCurrentUserId() {
        return currentUserId;
    }

    // =====================================================
    // SIGN UP
    // =====================================================

    public static boolean signUp(String email, String password) {

        JSONObject payload =
                new JSONObject()
                        .put("email", email)
                        .put("password", password)
                        .put("returnSecureToken", true);

        try {

            HttpClient client =
                    HttpClient.newHttpClient();

            URI uri =
                    URI.create(
                            "https://identitytoolkit.googleapis.com/v1/accounts:signUp?key="
                                    + API_KEY
                    );

            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(uri)
                            .header(
                                    "Content-Type",
                                    "application/json"
                            )
                            .POST(
                                    HttpRequest.BodyPublishers
                                            .ofString(payload.toString())
                            )
                            .build();

            HttpResponse<String> response =
                    client.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );

            System.out.println(
                    "SIGN UP STATUS = "
                            + response.statusCode()
            );

            System.out.println(
                    "SIGN UP RESPONSE = "
                            + response.body()
            );

            if (response.statusCode() == 200) {

                JSONObject responseJson =
                        new JSONObject(response.body());

                currentUserId =
                        responseJson.getString("localId");

                System.out.println(
                        "CURRENT USER ID = "
                                + currentUserId
                );

                return true;
            }

            return false;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =====================================================
    // SIGN IN
    // =====================================================

    public boolean signIn(
            String email,
            String password) {

        JSONObject payload =
                new JSONObject()
                        .put("email", email)
                        .put("password", password)
                        .put("returnSecureToken", true);

        try {

            HttpClient client =
                    HttpClient.newHttpClient();

            URI uri =
                    URI.create(
                            "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key="
                                    + API_KEY
                    );

            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(uri)
                            .header(
                                    "Content-Type",
                                    "application/json"
                            )
                            .POST(
                                    HttpRequest.BodyPublishers
                                            .ofString(payload.toString())
                            )
                            .build();

            HttpResponse<String> response =
                    client.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );

            System.out.println(
                    "LOGIN STATUS = "
                            + response.statusCode()
            );

            System.out.println(
                    "LOGIN RESPONSE = "
                            + response.body()
            );

            // Login successful
            if (response.statusCode() == 200) {

                JSONObject responseJson =
                        new JSONObject(response.body());

                // Firebase UID of logged-in user
                currentUserId =
                        responseJson.getString("localId");

                System.out.println(
                        "CURRENT USER ID = "
                                + currentUserId
                );

                return true;
            }

            return false;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }
}