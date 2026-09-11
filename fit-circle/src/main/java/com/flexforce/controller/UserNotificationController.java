package com.flexforce.controller;

import java.util.List;

import com.flexforce.dao.UserNotificationDAO;
import com.flexforce.model.user.UserNotification;

public class UserNotificationController {

    private UserNotificationDAO notificationDAO;

    public UserNotificationController() {

        this.notificationDAO =
                new UserNotificationDAO();
    }

    // =========================================================
    // ADD
    // =========================================================

    public void addNotification(
            UserNotification notification) {

        notificationDAO.saveNotification(
                notification
        );
    }

    // =========================================================
    // GET ONE
    // =========================================================

    public UserNotification getNotification(
            String notificationId) {

        return notificationDAO.getNotification(
                notificationId
        );
    }

    // =========================================================
    // UPDATE
    // =========================================================

    public void updateNotification(
            UserNotification notification) {

        notificationDAO.updateNotification(
                notification
        );
    }

    // =========================================================
    // DELETE
    // =========================================================

    public void deleteNotification(
            String notificationId) {

        notificationDAO.deleteNotification(
                notificationId
        );
    }

    // =========================================================
    // GET ALL
    // =========================================================

    public List<UserNotification> getAllNotifications() {

        return notificationDAO.getNotifications();
    }

    // =========================================================
    // GET USER NOTIFICATIONS
    // =========================================================

    public List<UserNotification> getUserNotifications(
            String userId) {

        return notificationDAO
                .getNotificationsByUser(userId);
    }

    // =========================================================
    // MARK ONE AS READ
    // =========================================================

    public void markAsRead(
            UserNotification notification) {

        notification.setRead(true);

        notificationDAO.updateNotification(
                notification
        );
    }

    // =========================================================
    // MARK ALL AS READ
    // =========================================================

    public void markAllAsRead(
            List<UserNotification> notifications) {

        if (notifications == null) {
            return;
        }

        for (UserNotification notification :
                notifications) {

            if (!notification.isRead()) {

                notification.setRead(true);

                notificationDAO.updateNotification(
                        notification
                );
            }
        }
    }

    // =========================================================
    // DELETE ALL
    // =========================================================

    public void deleteAllNotifications(
            List<UserNotification> notifications) {

        if (notifications == null) {
            return;
        }

        for (UserNotification notification :
                notifications) {

            deleteNotification(
                    notification.getNotificationId()
            );
        }
    }
}