package com.flexforce.controller;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

public class AuthControllerClub {

   private static final String API_KEY =
            "AIzaSyC2-ktfIIgp62zRq7wA3sxI00JILCHUfX0";

    // =========================================================
    // CURRENT CLUB OWNER ID
    // =========================================================

    private static String currentOwnerId;

    // Get currently logged-in Club Owner UID
    public static String getCurrentOwnerId() {
        return currentOwnerId;
    }

    // =========================================================
    // CLUB OWNER SIGN UP
    // =========================================================

    public static boolean signUp(String email, String password) {

        try {

            JSONObject payload = new JSONObject();

            payload.put("email", email);
            payload.put("password", password);
            payload.put("returnSecureToken", true);

            HttpClient client =
                    HttpClient.newHttpClient();

            URI uri = URI.create(
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

            System.out.println("================================");
            System.out.println("CLUB SIGN UP RESPONSE");
            System.out.println("Status : " + response.statusCode());
            System.out.println("Body   : " + response.body());
            System.out.println("================================");

            if (response.statusCode() == 200) {

                JSONObject json =
                        new JSONObject(response.body());

                // Firebase UID
                currentOwnerId =
                        json.getString("localId");

                System.out.println(
                        "Club Owner Account Created Successfully"
                );

                System.out.println(
                        "Club Owner ID : " + currentOwnerId
                );

                return true;

            } else {

                JSONObject error =
                        new JSONObject(response.body());

                if (error.has("error")) {

                    JSONObject errorObject =
                            error.getJSONObject("error");

                    System.out.println(
                            "Firebase Error : "
                                    + errorObject.toString()
                    );
                }

                return false;
            }

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // CLUB OWNER SIGN IN
    // =========================================================

    public boolean signIn(
            String email,
            String password) {

        try {

            JSONObject payload =
                    new JSONObject();

            payload.put("email", email);
            payload.put("password", password);
            payload.put("returnSecureToken", true);

            HttpClient client =
                    HttpClient.newHttpClient();

            URI uri = URI.create(
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

            System.out.println("================================");
            System.out.println("CLUB LOGIN RESPONSE");
            System.out.println("Status : " + response.statusCode());
            System.out.println("Body   : " + response.body());
            System.out.println("================================");

            if (response.statusCode() == 200) {

                JSONObject json =
                        new JSONObject(response.body());

                // Firebase UID
                currentOwnerId =
                        json.getString("localId");

                System.out.println(
                        "Club Owner Login Successful"
                );

                System.out.println(
                        "Club Owner ID : " + currentOwnerId
                );

                return true;

            } else {

                System.out.println(
                        "Club Owner Login Failed"
                );

                return false;
            }

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }
}