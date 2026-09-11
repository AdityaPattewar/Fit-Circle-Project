package com.flexforce.controller;

import org.json.JSONObject;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;

import com.flexforce.config.RazorpayConfig;
//import com.google.firestore.v1.StructuredQuery.Order;

public class RazorpayController {
    
 // =========================================================
    // CREATE RAZORPAY TEST ORDER
    // =========================================================

    public String createOrder(
            double amount,
            String userId,
            String clubId,
            String planId) {

        try {

            if (amount <= 0) {
                System.out.println("Amount must be greater than zero.");
                return null;
            }

            if (userId == null || userId.isEmpty()) {
                System.out.println("User ID cannot be empty.");
                return null;
            }

            if (clubId == null || clubId.isEmpty()) {
                System.out.println("Club ID cannot be empty.");
                return null;
            }

            if (planId == null || planId.isEmpty()) {
                System.out.println("Plan ID cannot be empty.");
                return null;
            }

            // Razorpay amount is in paise
            int amountInPaise = (int) Math.round(amount * 100);

            RazorpayClient razorpayClient =
                    new RazorpayClient(
                            RazorpayConfig.getKeyId(),
                            RazorpayConfig.getKeySecret()
                    );

            JSONObject orderRequest = new JSONObject();

            orderRequest.put(
                    "amount",
                    amountInPaise
            );

            orderRequest.put(
                    "currency",
                    "INR"
            );

            orderRequest.put(
                    "receipt",
                    "fitcircle_" + System.currentTimeMillis()
            );

            JSONObject notes = new JSONObject();

            notes.put("userId", userId);
            notes.put("clubId", clubId);
            notes.put("planId", planId);

            orderRequest.put(
                    "notes",
                    notes
            );

            Order order =
                    razorpayClient.orders.create(
                            orderRequest
                    );

            String orderId =
                    order.get("id");

            System.out.println(
                    "================================"
            );

            System.out.println(
                    "RAZORPAY TEST ORDER CREATED"
            );

            System.out.println(
                    "Order ID : " + orderId
            );

            System.out.println(
                    "Amount   : ₹" + amount
            );

            System.out.println(
                    "User ID  : " + userId
            );

            System.out.println(
                    "Club ID  : " + clubId
            );

            System.out.println(
                    "Plan ID  : " + planId
            );

            System.out.println(
                    "================================"
            );

            return orderId;

        } catch (Exception e) {

            System.out.println(
                    "Error creating Razorpay order."
            );

            e.printStackTrace();

            return null;
        }
    }
    
    // =========================================================
    // CREATE RAZORPAY PAYMENT LINK
    // =========================================================

    public String createPaymentLink(
            double amount,
            String userId,
            String clubId,
            String planId) {

        try {

            if (amount <= 0) {
                System.out.println("Amount must be greater than zero.");
                return null;
            }

            int amountInPaise = (int) Math.round(amount * 100);

            RazorpayClient razorpayClient =
                    new RazorpayClient(
                            RazorpayConfig.getKeyId(),
                            RazorpayConfig.getKeySecret()
                    );

            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount", amountInPaise);
            paymentLinkRequest.put("currency", "INR");
            paymentLinkRequest.put("accept_partial", false);
            paymentLinkRequest.put("description", "FitCircle Club Join - " + clubId);
            
            JSONObject customer = new JSONObject();
            customer.put("name", userId);
            customer.put("email", "user@example.com");
            paymentLinkRequest.put("customer", customer);
            
            JSONObject notify = new JSONObject();
            notify.put("sms", false);
            notify.put("email", false);
            paymentLinkRequest.put("notify", notify);
            
            paymentLinkRequest.put("reminder_enable", false);
            
            JSONObject notes = new JSONObject();
            notes.put("userId", userId);
            notes.put("clubId", clubId);
            notes.put("planId", planId);
            paymentLinkRequest.put("notes", notes);

            com.razorpay.PaymentLink paymentLink = razorpayClient.paymentLink.create(paymentLinkRequest);

            String paymentLinkUrl = paymentLink.get("short_url");

            System.out.println("================================");
            System.out.println("RAZORPAY PAYMENT LINK CREATED");
            System.out.println("URL : " + paymentLinkUrl);
            System.out.println("================================");

            return paymentLinkUrl;

        } catch (Exception e) {
            System.out.println("Error creating Razorpay payment link.");
            e.printStackTrace();
            return null;
        }
    }
}
