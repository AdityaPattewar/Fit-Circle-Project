package com.flexforce.config;

import io.github.cdimascio.dotenv.Dotenv;

public class RazorpayConfig {

    private static final Dotenv dotenv =
        Dotenv.configure()
              .directory("fit-circle")
              .ignoreIfMissing()
              .load();
              
    private RazorpayConfig() {
        // Prevent object creation
    }

    public static String getKeyId() {

       
        String keyId = dotenv.get("RAZORPAY_KEY_ID");


        System.out.println("Razorpay Key ID loaded: " +
                (keyId != null && !keyId.isBlank()));

        if (keyId == null || keyId.isBlank()) {
            throw new IllegalStateException(
                    "RAZORPAY_KEY_ID is not set in .env file."
            );

            
        
        }
         System.out.println("Key ID prefix: " +
        keyId.substring(0, Math.min(12, keyId.length())));


        return keyId;

        
    }

    public static String getKeySecret() {

      /*   String keySecret = dotenv.get("RAZORPAY_KEY_SECRET");

        System.out.println("Razorpay Secret loaded: " +
                (keySecret != null && !keySecret.isBlank()));

        if (keySecret == null || keySecret.isBlank()) {
            throw new IllegalStateException(
                    "RAZORPAY_KEY_SECRET is not set in .env file."
            );
        }

        return keySecret;*/
        
    String keySecret = dotenv.get("RAZORPAY_KEY_SECRET");

    if (keySecret != null) {
    keySecret = keySecret.trim();
}

    System.out.println("Secret loaded: " +
            (keySecret != null && !keySecret.isBlank()));

    if (keySecret == null || keySecret.isBlank()) {
        throw new IllegalStateException(
                "RAZORPAY_KEY_SECRET is not set in .env file."
        );
    }

    System.out.println("Secret length: " + keySecret.length());
    System.out.println("Secret has leading/trailing spaces: " +
            !keySecret.equals(keySecret.trim()));

    return keySecret;
}
    

    public static void testCredentials() {
    String keyId = getKeyId();
    String keySecret = getKeySecret();

    try {
        com.razorpay.RazorpayClient client =
                new com.razorpay.RazorpayClient(keyId, keySecret);

        client.paymentLink.fetchAll();

        System.out.println("================================");
        System.out.println("RAZORPAY AUTHENTICATION SUCCESS");
        System.out.println("================================");

    } catch (Exception e) {
        System.out.println("================================");
        System.out.println("RAZORPAY AUTHENTICATION FAILED");
        System.out.println(e.getMessage());
        System.out.println("================================");
    }
}
}
