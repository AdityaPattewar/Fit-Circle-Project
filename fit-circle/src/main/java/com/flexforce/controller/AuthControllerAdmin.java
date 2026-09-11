package com.flexforce.controller;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

import io.github.cdimascio.dotenv.Dotenv;

public class AuthControllerAdmin {

    private static final Dotenv dotenv =
        Dotenv.configure()
              .directory("fit-circle")
              .load();

private static final String API_KEY = dotenv.get("GOOGLE_API_KEY");

    public static boolean login(String email, String password) {

        try {

            JSONObject payload = new JSONObject()
                    .put("email", email)
                    .put("password", password)
                    .put("returnSecureToken", true);

            String url ="https://identitytoolkit.googleapis.com/v1/accounts:signUp?key="+ API_KEY;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(payload.toString()))
                    .build();

            HttpClient client = HttpClient.newHttpClient();

            HttpResponse<String> response =
                    client.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );

            System.out.println("Firebase Status Code: " + response.statusCode());
            System.out.println("Firebase Response: " + response.body());

            if (response.statusCode() == 200) {

                JSONObject result =
                        new JSONObject(response.body());

                if (result.has("idToken")) {

                    System.out.println("Admin Login Successful");

                    return true;
                }
            }

            System.out.println("Admin Login Failed");

            return false;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }
}