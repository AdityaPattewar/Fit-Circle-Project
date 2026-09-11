package com.flexforce.dao;

import java.util.ArrayList;
import java.util.List;

import com.flexforce.config.FirebaseConfig;
import com.flexforce.model.Payment.Payment;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;

public class PaymentDao {
    
 private Firestore db = FirebaseConfig.getFirebaseConfig();

    // =========================================================
    // SAVE PAYMENT
    // =========================================================

    public void savePayment(Payment payment) {

        try {

            if (payment == null) {
                System.out.println("Payment data cannot be null.");
                return;
            }

            String paymentId = payment.getPaymentId();

            if (paymentId == null || paymentId.isEmpty()) {
                System.out.println("Payment ID cannot be empty.");
                return;
            }

            db.collection("Payments")
              .document(paymentId)
              .set(payment);

            System.out.println("Payment Data Inserted Successfully");

        } catch (Exception e) {

            System.out.println("Error saving payment data.");
            e.printStackTrace();
        }
    }

    // =========================================================
    // GET PAYMENT
    // =========================================================

    public Payment getPayment(String paymentId) {

        try {

            if (paymentId == null || paymentId.isEmpty()) {
                System.out.println("Payment ID cannot be empty.");
                return null;
            }

            ApiFuture<DocumentSnapshot> future =
                    db.collection("Payments")
                      .document(paymentId)
                      .get();

            DocumentSnapshot document = future.get();

            if (document.exists()) {

                return document.toObject(Payment.class);
            }

        } catch (Exception e) {

            System.out.println("Error getting payment data.");
            e.printStackTrace();
        }

        return null;
    }

    // =========================================================
    // UPDATE PAYMENT
    // =========================================================

    public void updatePayment(
            String paymentId,
            Payment payment) {

        try {

            if (paymentId == null || paymentId.isEmpty()) {
                System.out.println("Payment ID cannot be empty.");
                return;
            }

            if (payment == null) {
                System.out.println("Payment data cannot be null.");
                return;
            }

            db.collection("Payments")
              .document(paymentId)
              .update(
                  "orderId", payment.getOrderId(),
                  "userId", payment.getUserId(),
                  "clubId", payment.getClubId(),
                  "planId", payment.getPlanId(),
                  "amount", payment.getAmount(),
                  "currency", payment.getCurrency(),
                  "status", payment.getStatus(),
                  "createdAt", payment.getCreatedAt()
              );

            System.out.println("Payment Data Updated Successfully");

        } catch (Exception e) {

            System.out.println("Error updating payment data.");
            e.printStackTrace();
        }
    }

    // =========================================================
    // DELETE PAYMENT
    // =========================================================

    public void deletePayment(String paymentId) {

        try {

            if (paymentId == null || paymentId.isEmpty()) {
                System.out.println("Payment ID cannot be empty.");
                return;
            }

            db.collection("Payments")
              .document(paymentId)
              .delete();

            System.out.println("Payment Data Deleted Successfully");

        } catch (Exception e) {

            System.out.println("Error deleting payment data.");
            e.printStackTrace();
        }
    }

    // =========================================================
    // GET ALL PAYMENTS
    // =========================================================

    public List<Payment> getPayments() {

        List<Payment> list = new ArrayList<>();

        try {

            ApiFuture<QuerySnapshot> future =
                    db.collection("Payments").get();

            QuerySnapshot snapshot = future.get();

            for (DocumentSnapshot doc :
                    snapshot.getDocuments()) {

                Payment payment =
                        doc.toObject(Payment.class);

                if (payment != null) {
                    list.add(payment);
                }
            }

        } catch (Exception e) {

            System.out.println("Error getting payments.");
            e.printStackTrace();
        }

        return list;
    }

    // =========================================================
    // GET PAYMENTS OF ONE USER
    // =========================================================

    public List<Payment> getPaymentsByUser(
            String userId) {

        List<Payment> list = new ArrayList<>();

        try {

            if (userId == null || userId.isEmpty()) {
                System.out.println("User ID cannot be empty.");
                return list;
            }

            ApiFuture<QuerySnapshot> future =
                    db.collection("Payments")
                      .whereEqualTo("userId", userId)
                      .get();

            QuerySnapshot snapshot = future.get();

            for (DocumentSnapshot doc :
                    snapshot.getDocuments()) {

                Payment payment =
                        doc.toObject(Payment.class);

                if (payment != null) {
                    list.add(payment);
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Error getting user payment data."
            );

            e.printStackTrace();
        }

        return list;
    }

    // =========================================================
    // GET PAYMENTS OF ONE CLUB
    // =========================================================

    public List<Payment> getPaymentsByClub(
            String clubId) {

        List<Payment> list = new ArrayList<>();

        try {

            if (clubId == null || clubId.isEmpty()) {
                System.out.println("Club ID cannot be empty.");
                return list;
            }

            ApiFuture<QuerySnapshot> future =
                    db.collection("Payments")
                      .whereEqualTo("clubId", clubId)
                      .get();

            QuerySnapshot snapshot = future.get();

            for (DocumentSnapshot doc :
                    snapshot.getDocuments()) {

                Payment payment =
                        doc.toObject(Payment.class);

                if (payment != null) {
                    list.add(payment);
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Error getting club payment data."
            );

            e.printStackTrace();
        }

        return list;
    }
}
