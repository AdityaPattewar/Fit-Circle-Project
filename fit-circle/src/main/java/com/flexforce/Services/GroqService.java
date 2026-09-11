package com.flexforce.Services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import org.json.JSONArray;
import org.json.JSONObject;

import com.flexforce.config.GroqConfig;

public class GroqService {
    
 private static final String GROQ_API_URL = "https://api.groq.com/openai/v1/chat/completions";
    private static final String MODEL_NAME = "qwen/qwen3.6-27b"; 

    private final HttpClient httpClient;

    public GroqService() {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(15))
                .build();
    }

    public String askQuestion(String question) {
        try {
            String apiKey = GroqConfig.getApiKey();

            JSONObject systemMessage = new JSONObject();
            systemMessage.put("role", "system");
            systemMessage.put("content", "You are a helpful AI fitness coach for the app FitCircle. Provide concise, encouraging, and accurate fitness advice. Keep your responses structured and easy to read.");

            JSONObject userMessage = new JSONObject();
            userMessage.put("role", "user");
            userMessage.put("content", question);

            JSONArray messages = new JSONArray();
            messages.put(systemMessage);
            messages.put(userMessage);

            JSONObject requestBody = new JSONObject();
            requestBody.put("model", MODEL_NAME);
            requestBody.put("messages", messages);
            requestBody.put("temperature", 0.7);
            requestBody.put("max_tokens", 800);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(GROQ_API_URL))
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JSONObject jsonResponse = new JSONObject(response.body());
                JSONArray choices = jsonResponse.getJSONArray("choices");
                if (choices.length() > 0) {
                    JSONObject message = choices.getJSONObject(0).getJSONObject("message");
                    String raw = message.getString("content").trim();
                    return raw.replaceAll("[*#~`]", "");
                }
            } else {
                System.err.println("Groq API Error: " + response.statusCode() + " - " + response.body());
                
                // Try to extract a meaningful error message if possible
                try {
                    JSONObject errorObj = new JSONObject(response.body()).optJSONObject("error");
                    if (errorObj != null && errorObj.has("message")) {
                        return "Sorry, there was an issue: " + errorObj.getString("message");
                    }
                } catch (Exception e) {}
                
                return "Sorry, I am having trouble connecting to the fitness network right now. (Error: " + response.statusCode() + " - " + response.body() + ")";
            }

            return "Sorry, I couldn't generate a response.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred: " + e.getMessage();
        }
    }
}

