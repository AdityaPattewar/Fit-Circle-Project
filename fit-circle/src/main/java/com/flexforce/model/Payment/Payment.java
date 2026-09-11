package com.flexforce.model.Payment;

public class Payment {
    
private String paymentId;
    private String orderId;
    private String userId;
    private String clubId;
    private String planId;

    private double amount;
    private String currency;
    private String status;

    private long createdAt;

    // Required empty constructor for Firebase
    public Payment() {
    }

    public Payment(
            String paymentId,
            String orderId,
            String userId,
            String clubId,
            String planId,
            double amount,
            String currency,
            String status,
            long createdAt) {

        this.paymentId = paymentId;
        this.orderId = orderId;
        this.userId = userId;
        this.clubId = clubId;
        this.planId = planId;
        this.amount = amount;
        this.currency = currency;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}
