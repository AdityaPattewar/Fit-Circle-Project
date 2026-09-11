package com.flexforce.model.user;

public class UserNotification {

    private String notificationId;
    private String userId;
    private String title;
    private String message;
    private String date;
    private boolean isRead;
    private String category;

    public UserNotification() {
    }

    public UserNotification(
            String notificationId,
            String userId,
            String title,
            String message,
            String date,
            boolean isRead,
            String category) {

        this.notificationId = notificationId;
        this.userId = userId;
        this.title = title;
        this.message = message;
        this.date = date;
        this.isRead = isRead;
        this.category = category;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}