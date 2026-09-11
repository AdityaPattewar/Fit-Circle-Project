package com.flexforce.view.User;

import com.flexforce.controller.UserNotificationController;
import com.flexforce.model.user.UserNotification;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class User_Notification extends Application {

    // =========================================================
    // COLORS
    // =========================================================

    String BACKGROUND = "#0B0F12";
    String CARD = "#181C1B";
    String CARD_LIGHT = "#202522";
    String GREEN = "#B6FF00";
    String WHITE = "#FFFFFF";
    String GRAY = "#AEB5BC";
    String BORDER = "#2C322F";

    // =========================================================
    // CONTROLLER
    // =========================================================

    private UserNotificationController controller;

    private VBox notificationList;

    private List<UserNotification> notifications;

    // =========================================================
    // START
    // =========================================================

    @Override
    public void start(Stage stage) {

        controller =
                new UserNotificationController();

        VBox root =
                createNotificationPage();

        Scene scene =
                new Scene(root, 1300, 800);

        stage.setTitle(
                "FitCircle - Notifications"
        );

        stage.setScene(scene);

        stage.setMaximized(true);

        stage.show();
    }

    // =========================================================
    // CREATE PAGE
    // =========================================================

    public VBox createNotificationPage() {

        // -----------------------------------------------------
        // TITLE
        // -----------------------------------------------------

        Label title =
                new Label("Notifications");

        title.setStyle(
                "-fx-text-fill:" + WHITE + ";" +
                "-fx-font-size:32px;" +
                "-fx-font-weight:bold;"
        );

        Label subtitle =
                new Label(
                        "Stay updated with your clubs, bookings and fitness activities."
                );

        subtitle.setStyle(
                "-fx-text-fill:" + GRAY + ";" +
                "-fx-font-size:15px;"
        );

        VBox titleBox =
                new VBox(6);

        titleBox.getChildren().addAll(
                title,
                subtitle
        );

        // -----------------------------------------------------
        // MARK ALL READ
        // -----------------------------------------------------

        Button markRead =
                new Button("Mark All as Read");

        markRead.setPrefWidth(170);

        markRead.setPrefHeight(40);

        markRead.setStyle(
                "-fx-background-color:" + GREEN + ";" +
                "-fx-text-fill:#101410;" +
                "-fx-font-size:14px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:6px;"
        );

        // -----------------------------------------------------
        // DELETE ALL
        // -----------------------------------------------------

        Button deleteAll =
                new Button("Delete All");

        deleteAll.setPrefWidth(120);

        deleteAll.setPrefHeight(40);

        deleteAll.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-text-fill:" + WHITE + ";" +
                "-fx-font-size:14px;" +
                "-fx-font-weight:bold;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:6px;" +
                "-fx-background-radius:6px;"
        );

        HBox topButtons =
                new HBox(
                        10,
                        markRead,
                        deleteAll
                );

        topButtons.setAlignment(
                Pos.CENTER_RIGHT
        );

        // -----------------------------------------------------
        // CATEGORY BUTTONS
        // -----------------------------------------------------

        Button all =
                createCategoryButton(
                        "All",
                        true
                );

        Button clubs =
                createCategoryButton(
                        "Clubs",
                        false
                );

        Button events =
                createCategoryButton(
                        "Events",
                        false
                );

        Button bookings =
                createCategoryButton(
                        "Bookings",
                        false
                );

        Button challenges =
                createCategoryButton(
                        "Challenges",
                        false
                );

        Button payments =
                createCategoryButton(
                        "Payments",
                        false
                );

        Button system =
                createCategoryButton(
                        "System",
                        false
                );

        HBox categories =
                new HBox(
                        10,
                        all,
                        clubs,
                        events,
                        bookings,
                        challenges,
                        payments,
                        system
                );

        categories.setAlignment(
                Pos.CENTER_LEFT
        );

        // -----------------------------------------------------
        // NOTIFICATION LIST
        // -----------------------------------------------------

        notificationList =
                new VBox(12);

        notificationList.setPadding(
                new Insets(5)
        );

        // -----------------------------------------------------
        // LOAD FIREBASE DATA
        // -----------------------------------------------------

        loadNotifications();

        // -----------------------------------------------------
        // SCROLL PANE
        // -----------------------------------------------------

        ScrollPane scrollPane =
                new ScrollPane();

        scrollPane.setContent(
                notificationList
        );

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setPannable(true);

        scrollPane.setStyle(
                "-fx-background:" + BACKGROUND + ";" +
                "-fx-background-color:" + BACKGROUND + ";" +
                "-fx-border-color:transparent;"
        );

        VBox.setVgrow(
                scrollPane,
                Priority.ALWAYS
        );

        // -----------------------------------------------------
        // ROOT
        // -----------------------------------------------------

        VBox root =
                new VBox(
                        20,
                        titleBox,
                        topButtons,
                        categories,
                        scrollPane
                );

        root.setPadding(
                new Insets(
                        30,
                        35,
                        30,
                        35
                )
        );

        root.setStyle(
                "-fx-background-color:" +
                BACKGROUND + ";"
        );

        // =====================================================
        // ALL
        // =====================================================

        all.setOnAction(e -> {

            loadNotifications();

        });

        // =====================================================
        // CLUBS
        // =====================================================

        clubs.setOnAction(e -> {

            filterByCategory("Clubs");

        });

        // =====================================================
        // EVENTS
        // =====================================================

        events.setOnAction(e -> {

            filterByCategory("Events");

        });

        // =====================================================
        // BOOKINGS
        // =====================================================

        bookings.setOnAction(e -> {

            filterByCategory("Bookings");

        });

        // =====================================================
        // CHALLENGES
        // =====================================================

        challenges.setOnAction(e -> {

            filterByCategory("Challenges");

        });

        // =====================================================
        // PAYMENTS
        // =====================================================

        payments.setOnAction(e -> {

            filterByCategory("Payments");

        });

        // =====================================================
        // SYSTEM
        // =====================================================

        system.setOnAction(e -> {

            filterByCategory("System");

        });

        // =====================================================
        // MARK ALL READ
        // =====================================================

        markRead.setOnAction(e -> {

            controller.markAllAsRead(
                    notifications
            );

            loadNotifications();

        });

        // =====================================================
        // DELETE ALL
        // =====================================================

        deleteAll.setOnAction(e -> {

            controller.deleteAllNotifications(
                    notifications
            );

            loadNotifications();

        });

        return root;
    }

    // =========================================================
    // LOAD NOTIFICATIONS
    // =========================================================

    private void loadNotifications() {

        notificationList
                .getChildren()
                .clear();

        try {

            notifications =
                    controller.getUserNotifications(com.flexforce.controller.AuthControllerlogin.getCurrentUserId());

            if (notifications == null ||
                    notifications.isEmpty()) {

                showEmptyMessage();

                return;
            }

            for (UserNotification notification :
                    notifications) {

                HBox card =
                        createNotificationCard(
                                notification
                        );

                notificationList
                        .getChildren()
                        .add(card);
            }

        } catch (Exception e) {

            e.printStackTrace();

            showEmptyMessage();
        }
    }

    // =========================================================
    // FILTER
    // =========================================================

    private void filterByCategory(
            String category) {

        notificationList
                .getChildren()
                .clear();

        if (notifications == null ||
                notifications.isEmpty()) {

            showEmptyMessage();

            return;
        }

        boolean found = false;

        for (UserNotification notification :
                notifications) {

            String notificationCategory =
                    notification.getCategory();

            if (notificationCategory != null &&
                    notificationCategory.equalsIgnoreCase(
                            category
                    )) {

                HBox card =
                        createNotificationCard(
                                notification
                        );

                notificationList
                        .getChildren()
                        .add(card);

                found = true;
            }
        }

        if (!found) {

            showEmptyMessage();
        }
    }

    // =========================================================
    // CREATE NOTIFICATION CARD
    // =========================================================

    private HBox createNotificationCard(
            UserNotification notification) {

        // -----------------------------------------------------
        // ICON
        // -----------------------------------------------------

        Label icon =
                new Label(
                        getIcon(
                                notification.getCategory()
                        )
                );

        icon.setStyle(
                "-fx-text-fill:" + GREEN + ";" +
                "-fx-font-size:26px;" +
                "-fx-font-weight:bold;"
        );

        // -----------------------------------------------------
        // TITLE
        // -----------------------------------------------------

        Label heading =
                new Label(
                        safeValue(
                                notification.getTitle()
                        )
                );

        String titleColor;

        if (notification.isRead()) {

            titleColor = GRAY;

        } else {

            titleColor = WHITE;
        }

        heading.setStyle(
                "-fx-text-fill:" + titleColor + ";" +
                "-fx-font-size:19px;" +
                "-fx-font-weight:bold;"
        );

        // -----------------------------------------------------
        // MESSAGE
        // -----------------------------------------------------

        Label text =
                new Label(
                        safeValue(
                                notification.getMessage()
                        )
                );

        text.setStyle(
                "-fx-text-fill:" + GRAY + ";" +
                "-fx-font-size:14px;"
        );

        // -----------------------------------------------------
        // DATE
        // -----------------------------------------------------

        Label time =
                new Label(
                        safeValue(
                                notification.getDate()
                        )
                );

        time.setStyle(
                "-fx-text-fill:#777F7A;" +
                "-fx-font-size:12px;"
        );

        // -----------------------------------------------------
        // INFORMATION
        // -----------------------------------------------------

        VBox info =
                new VBox(
                        6,
                        heading,
                        text,
                        time
                );

        HBox.setHgrow(
                info,
                Priority.ALWAYS
        );

        // -----------------------------------------------------
        // CARD
        // -----------------------------------------------------

        HBox card;

        if (!notification.isRead()) {

            Label dot =
                    new Label("●");

            dot.setStyle(
                    "-fx-text-fill:" + GREEN + ";" +
                    "-fx-font-size:13px;"
            );

            card =
                    new HBox(
                            20,
                            icon,
                            info,
                            dot
                    );

        } else {

            card =
                    new HBox(
                            20,
                            icon,
                            info
                    );
        }

        card.setAlignment(
                Pos.CENTER_LEFT
        );

        card.setPadding(
                new Insets(20)
        );

        card.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;"
        );

        // -----------------------------------------------------
        // CLICK = MARK AS READ
        // -----------------------------------------------------

        card.setOnMouseClicked(e -> {

            if (!notification.isRead()) {

                notification.setRead(true);

                controller.updateNotification(
                        notification
                );

                loadNotifications();
            }
        });

        return card;
    }

    // =========================================================
    // ICON
    // =========================================================

    private String getIcon(
            String category) {

        if (category == null) {
            return "●";
        }

        switch (category.toLowerCase()) {

            case "bookings":
                return "✓";

            case "clubs":
                return "⚑";

            case "events":
                return "★";

            case "challenges":
                return "★";

            case "payments":
                return "₹";

            case "system":
                return "⚙";

            default:
                return "●";
        }
    }

    // =========================================================
    // EMPTY MESSAGE
    // =========================================================

    private void showEmptyMessage() {

        Label empty =
                new Label(
                        "No notifications available."
                );

        empty.setStyle(
                "-fx-text-fill:" + GRAY + ";" +
                "-fx-font-size:16px;"
        );

        VBox box =
                new VBox(empty);

        box.setAlignment(
                Pos.CENTER
        );

        box.setPadding(
                new Insets(50)
        );

        notificationList
                .getChildren()
                .add(box);
    }

    // =========================================================
    // CATEGORY BUTTON
    // =========================================================

    private Button createCategoryButton(
            String text,
            boolean selected) {

        Button button =
                new Button(text);

        if (selected) {

            button.setStyle(
                    "-fx-background-color:" + GREEN + ";" +
                    "-fx-text-fill:#101410;" +
                    "-fx-font-weight:bold;" +
                    "-fx-background-radius:20px;"
            );

        } else {

            button.setStyle(
                    "-fx-background-color:" + CARD + ";" +
                    "-fx-text-fill:" + WHITE + ";" +
                    "-fx-border-color:" + BORDER + ";" +
                    "-fx-border-radius:20px;" +
                    "-fx-background-radius:20px;"
            );
        }

        return button;
    }

    // =========================================================
    // SAFE VALUE
    // =========================================================

    private String safeValue(String value) {

        if (value == null ||
                value.trim().isEmpty()) {

            return "";
        }

        return value;
    }
}