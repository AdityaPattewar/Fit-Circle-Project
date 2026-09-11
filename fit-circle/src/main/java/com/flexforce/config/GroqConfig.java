package com.flexforce.config;

import io.github.cdimascio.dotenv.Dotenv;

public class GroqConfig {
    
 private static Dotenv dotenv;

    static {
        try {
            // Try loading from current directory first
            dotenv = Dotenv.configure().load();
        } catch (Exception e1) {
            try {
                // If the IDE is running from the parent directory, try looking in fit-circle
                dotenv = Dotenv.configure().directory("./fit-circle").load();
            } catch (Exception e2) {
                // Fallback (might just load system env variables)
                dotenv = Dotenv.configure().ignoreIfMissing().load();
            }
        }
    }

    private GroqConfig() {
        // Prevent instantiation
    }

    public static String getApiKey() {
        String apiKey = dotenv.get("GROQ_API_KEY");

        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException(
                    "GROQ_API_KEY is not set. We could not find your .env file in the current directory: " + 
                    System.getProperty("user.dir")
            );
        }

        return apiKey;
    }
}

