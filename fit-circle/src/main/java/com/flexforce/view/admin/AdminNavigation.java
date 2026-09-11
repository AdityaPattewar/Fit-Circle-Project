package com.flexforce.view.admin;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class AdminNavigation extends Application {

        private BorderPane root;

        @Override
        public void start(Stage stage) {

                root = new BorderPane();
                root.setStyle("-fx-background-color: #0d0f12");

                VBox sidebar = createSidebar(stage);
                root.setLeft(sidebar);

                showDashboard();

                Scene scene = new Scene(root, 850, 600);

                stage.setTitle("FitCircle - Admin Dashboard");
                stage.setScene(scene);
                stage.setMinWidth(850);
                stage.setMinHeight(600);
                stage.show();
        }

        private VBox createSidebar(Stage stage) {

                Label logo = new Label("FITCIRCLE");
                logo.setStyle("-fx-text-fill: #5cff00;-fx-font-size: 24px;-fx-font-weight: bold");

                Button dashboard = new Button("▥ Dashboard");
                dashboard.setMaxWidth(Double.MAX_VALUE);
                dashboard.setPrefHeight(42);
                dashboard.setAlignment(Pos.CENTER_LEFT);
                dashboard.setStyle(
                                "-fx-background-color: transparent;-fx-text-fill: #d5d8dc;-fx-font-size: 14px;-fx-font-weight: bold;-fx-background-radius: 7px;-fx-padding: 10px 15px");

                Button clubs = new Button("⚑ Manage Clubs");
                clubs.setMaxWidth(Double.MAX_VALUE);
                clubs.setPrefHeight(42);
                clubs.setAlignment(Pos.CENTER_LEFT);
                clubs.setStyle("-fx-background-color: transparent;-fx-text-fill: #d5d8dc;-fx-font-size: 14px;-fx-font-weight: bold;-fx-background-radius: 7px;-fx-padding: 10px 15px");

                Button users = new Button("♟ Users");
                users.setMaxWidth(Double.MAX_VALUE);
                users.setPrefHeight(42);
                users.setAlignment(Pos.CENTER_LEFT);
                users.setStyle("-fx-background-color: transparent;-fx-text-fill: #d5d8dc;-fx-font-size: 14px;-fx-font-weight: bold;-fx-background-radius: 7px;-fx-padding: 10px 15px");

                Button bookings = new Button("▦ Bookings");
                bookings.setMaxWidth(Double.MAX_VALUE);
                bookings.setPrefHeight(42);
                bookings.setAlignment(Pos.CENTER_LEFT);
                bookings.setStyle(
                                "-fx-background-color: transparent;-fx-text-fill: #d5d8dc;-fx-font-size: 14px;-fx-font-weight: bold;-fx-background-radius: 7px;-fx-padding: 10px 15px");

                Button reports = new Button("▰ Reports");
                reports.setMaxWidth(Double.MAX_VALUE);
                reports.setPrefHeight(42);
                reports.setAlignment(Pos.CENTER_LEFT);
                reports.setStyle(
                                "-fx-background-color: transparent;-fx-text-fill: #d5d8dc;-fx-font-size: 14px;-fx-font-weight: bold;-fx-background-radius: 7px;-fx-padding: 10px 15px");

                VBox menu = new VBox(10, dashboard, clubs, users, bookings, reports);
                menu.setAlignment(Pos.CENTER_LEFT);

                Button settings = new Button("⚙ Settings");
                settings.setMaxWidth(Double.MAX_VALUE);
                settings.setPrefHeight(42);
                settings.setAlignment(Pos.CENTER_LEFT);
                settings.setStyle(
                                "-fx-background-color: transparent;-fx-text-fill: #d5d8dc;-fx-font-size: 14px;-fx-font-weight: bold;-fx-background-radius: 7px;-fx-padding: 10px 15px");

                Button logout = new Button("→ Logout");
                logout.setMaxWidth(Double.MAX_VALUE);
                logout.setPrefHeight(42);
                logout.setAlignment(Pos.CENTER_LEFT);
                logout.setStyle("-fx-background-color: transparent;-fx-text-fill: #d5d8dc;-fx-font-size: 14px;-fx-font-weight: bold;-fx-background-radius: 7px;-fx-padding: 10px 15px");

                Region sideSpace = new Region();
                sideSpace.setPrefHeight(300);

                VBox sidebar = new VBox(22, logo, menu, sideSpace, settings, logout);
                sidebar.setPadding(new Insets(35, 20, 20, 22));
                sidebar.setPrefWidth(240);
                sidebar.setStyle("-fx-background-color: #15181e");

                dashboard.setOnAction(e -> showDashboard());
                clubs.setOnAction(e -> showClubs());
                users.setOnAction(e -> showUsers());
                bookings.setOnAction(e -> showBookings());
                reports.setOnAction(e -> showReports());
                settings.setOnAction(e -> showSettings());

                logout.setOnAction(e -> stage.close());

                return sidebar;
        }

        private void setPage(VBox page) {

                ScrollPane scrollPane = new ScrollPane(page);
                scrollPane.setFitToWidth(true);
                scrollPane.setStyle("-fx-background: #0d0f12;-fx-background-color: #0d0f12");

                root.setCenter(scrollPane);
        }

        private void showDashboard() {

                Label heading = new Label("Admin Dashboard");
                heading.setStyle("-fx-text-fill: white;-fx-font-size: 34px;-fx-font-weight: bold");

                Region headerSpace = new Region();
                headerSpace.setPrefWidth(300);

                Label date = new Label("▦ Today - 14 Aug 2026");
                date.setStyle("-fx-text-fill: #5cff00;-fx-font-size: 15px;-fx-font-weight: bold");

                Label admin = new Label("Admin");
                admin.setStyle("-fx-text-fill: white;-fx-font-size: 15px;-fx-font-weight: bold");

                HBox headerRight = new HBox(15, date, admin);
                headerRight.setAlignment(Pos.CENTER_RIGHT);

                HBox header = new HBox(20, heading, headerSpace, headerRight);
                header.setAlignment(Pos.CENTER_LEFT);

                VBox card1 = new VBox(15, new Label("●"), new Label("Total Clubs"), new Label("48"));
                VBox card2 = new VBox(15, new Label("♟"), new Label("Active Users"), new Label("2,340"));
                VBox card3 = new VBox(15, new Label("▦"), new Label("Total Bookings"), new Label("12,450"));
                VBox card4 = new VBox(15, new Label("₹"), new Label("Revenue"), new Label("₹45.2L"));
                HBox cards = new HBox(22, card1, card2, card3, card4);

                VBox analytics = createAnalytics();
                VBox activity = createActivity();

                HBox middle = new HBox(20, analytics, activity);

                VBox clubManagement = createManagementCard(
                                "Club Management",
                                "Add New Club",
                                "Edit Club",
                                "Delete Club");

                VBox userManagement = createManagementCard(
                                "User Management",
                                "View Users",
                                "Send Notification",
                                "Block User");

                VBox bookingManagement = createManagementCard(
                                "Booking Management",
                                "View Bookings",
                                "Cancel Booking",
                                "Issue Refund");

                HBox management = new HBox(20, clubManagement, userManagement, bookingManagement);

                VBox main = new VBox(20, header, cards, middle, management);
                main.setPadding(new Insets(35, 30, 30, 45));
                main.setStyle("-fx-background-color: #0d0f12");

                setPage(main);
        }

        private VBox createDashboardCard(String iconText, String name, String value) {

                Label icon = new Label(iconText);
                icon.setStyle("-fx-text-fill: #5cff00;-fx-font-size: 25px");

                Label nameLabel = new Label(name);
                nameLabel.setStyle("-fx-text-fill: #9299a5;-fx-font-size: 14px");

                Label valueLabel = new Label(value);
                valueLabel.setStyle("-fx-text-fill: #5cff00;-fx-font-size: 27px;-fx-font-weight: bold");

                VBox card = new VBox(15, icon, nameLabel, valueLabel);
                card.setPadding(new Insets(25));
                card.setPrefWidth(275);
                card.setPrefHeight(170);
                card.setStyle("-fx-background-color: #181c22;-fx-border-color: #292f38;-fx-border-radius: 12;-fx-background-radius: 12");

                return card;
        }

        private VBox createAnalytics() {

                Label title = new Label("Bookings Analytics");
                title.setStyle("-fx-text-fill: white;-fx-font-size: 17px;-fx-font-weight: bold");

                VBox jan = createBar("Jan", 70);
                VBox feb = createBar("Feb", 130);
                VBox mar = createBar("Mar", 85);
                VBox apr = createBar("Apr", 120);
                VBox may = createBar("May", 75);
                VBox jun = createBar("Jun", 85);

                HBox chart = new HBox(28, jan, feb, mar, apr, may, jun);
                chart.setAlignment(Pos.BOTTOM_CENTER);
                chart.setPadding(new Insets(10, 20, 15, 20));
                chart.setPrefHeight(150);
                chart.setStyle("-fx-background-color: #0d0f12;-fx-background-radius: 8");

                VBox analytics = new VBox(15, title, chart);
                analytics.setPadding(new Insets(25));
                analytics.setStyle(
                                "-fx-background-color: #181c22;-fx-border-color: #292f38;-fx-border-radius: 12;-fx-background-radius: 12");

                return analytics;
        }

        private VBox createBar(String month, double height) {

                Rectangle bar = new Rectangle(45, height);
                bar.setFill(Color.web("#5cff00"));

                Label label = new Label(month);
                label.setStyle("-fx-text-fill: #8c929c;-fx-font-size: 12px");

                VBox box = new VBox(7, bar, label);
                box.setAlignment(Pos.BOTTOM_CENTER);

                return box;
        }

        private VBox createActivity() {

                Label title = new Label("Recent Activity");
                title.setStyle("-fx-text-fill: white;-fx-font-size: 17px;-fx-font-weight: bold");

                HBox row1 = createActivityRow("New Club Added", "Zenfit Stud...", "2 min ago");
                HBox row2 = createActivityRow("Booking Confirmed", "User: Raj...", "15 min ago");
                HBox row3 = createActivityRow("Payment Received", "₹15,999", "1 hour ago");
                HBox row4 = createActivityRow("New Members", "3 new memb...", "2 hours ago");

                VBox list = new VBox(10, row1, row2, row3, row4);

                ScrollPane scroll = new ScrollPane(list);
                scroll.setFitToWidth(true);
                scroll.setPrefHeight(180);

                VBox activity = new VBox(15, title, scroll);
                activity.setPadding(new Insets(25));
                activity.setPrefWidth(330);
                activity.setStyle(
                                "-fx-background-color: #181c22;-fx-border-color: #292f38;-fx-border-radius: 12;-fx-background-radius: 12");

                return activity;
        }

        private HBox createActivityRow(String text, String value, String time) {

                Label a = new Label(text);
                a.setStyle("-fx-text-fill: white;-fx-font-weight: bold");

                Label b = new Label(value);
                b.setStyle("-fx-text-fill: #5cff00");

                Label c = new Label(time);
                c.setStyle("-fx-text-fill: #777f8b");

                HBox row = new HBox(10, a, b, c);
                row.setPadding(new Insets(12));
                row.setStyle("-fx-background-color: #0d0f12;-fx-background-radius: 7");

                return row;
        }

        private VBox createManagementCard(String titleText, String button1Text, String button2Text,
                        String button3Text) {

                Label title = new Label(titleText);
                title.setStyle("-fx-text-fill: white;-fx-font-size: 16px;-fx-font-weight: bold");

                Button button1 = new Button(button1Text);
                button1.setMaxWidth(Double.MAX_VALUE);
                button1.setPrefHeight(40);
                button1.setStyle("-fx-background-color: #252b32;-fx-text-fill: white;-fx-background-radius: 6px");

                Button button2 = new Button(button2Text);
                button2.setMaxWidth(Double.MAX_VALUE);
                button2.setPrefHeight(40);
                button2.setStyle("-fx-background-color: #252b32;-fx-text-fill: white;-fx-background-radius: 6px");

                Button button3 = new Button(button3Text);
                button3.setMaxWidth(Double.MAX_VALUE);
                button3.setPrefHeight(40);
                button3.setStyle("-fx-background-color: #252b32;-fx-text-fill: white;-fx-background-radius: 6px");

                VBox box = new VBox(12, title, button1, button2, button3);
                box.setPadding(new Insets(22));
                box.setPrefWidth(330);
                box.setStyle("-fx-background-color: #181c22;-fx-border-color: #292f38;-fx-border-radius: 12;-fx-background-radius: 12");

                return box;
        }

        private void showClubs() {

                TextField search = new TextField();
                search.setPromptText("🔍  Search...");
                search.setPrefWidth(250);
                search.setStyle("-fx-background-color: #080808;-fx-text-fill: white;-fx-prompt-text-fill: #aaaaaa;-fx-background-radius: 5px;-fx-border-color: #222222;-fx-border-radius: 5px;-fx-padding: 7px");

                Region space = new Region();
                space.setPrefWidth(300);

                Label bell = new Label("♧");
                bell.setStyle("-fx-text-fill: lightgray;-fx-font-size: 20px");

                Label question = new Label("?");
                question.setStyle(
                                "-fx-text-fill: lightgray;-fx-font-size: 15px;-fx-border-color: #aaaaaa;-fx-border-radius: 50%;-fx-padding: 1px 5px");

                Label profile = new Label("●");
                profile.setStyle(
                                "-fx-text-fill: white;-fx-font-size: 20px;-fx-background-color: #202b32;-fx-background-radius: 50%;-fx-padding: 5px 9px");

                HBox right = new HBox(25, bell, question, profile);

                HBox topBar = new HBox(20, search, space, right);
                topBar.setAlignment(Pos.CENTER_LEFT);
                topBar.setPadding(new Insets(5, 25, 5, 25));
                topBar.setStyle("-fx-background-color: #171e26;-fx-border-color: #28313a;-fx-border-width: 0 0 1px 0");

                Label title = new Label("Manage Clubs");
                title.setStyle("-fx-text-fill: white;-fx-font-size: 28px;-fx-font-weight: bold");

                Label subtitle = new Label("View and manage all fitness clubs registered on FitCircle.");
                subtitle.setStyle("-fx-text-fill: #aaaaaa;-fx-font-size: 13px");

                VBox titleBox = new VBox(5, title, subtitle);

                Button addClub = new Button("+  Add Club");
                addClub.setPrefWidth(110);
                addClub.setPrefHeight(35);
                addClub.setStyle(
                                "-fx-background-color: #82d000;-fx-text-fill: black;-fx-background-radius: 6px;-fx-font-size: 12px");
                addClub.setOnAction(e -> showAddClubDialog());

                Region headingSpace = new Region();
                headingSpace.setPrefWidth(250);

                HBox heading = new HBox(20, titleBox, headingSpace, addClub);
                heading.setAlignment(Pos.CENTER_LEFT);

                HBox card1 = createClubStat("▦", "Total Clubs", "125", "#82d000");
                HBox card2 = createClubStat("✓", "Active", "98", "#82d000");
                HBox card3 = createClubStat("•••", "Pending Approval", "17", "#ffcc00");
                HBox card4 = createClubStat("⊘", "Inactive", "10", "#aaaaaa");

                HBox cards = new HBox(17, card1, card2, card3, card4);

                HBox filters = createFilters();

                VBox table = createClubTable();

                VBox main = new VBox(20, topBar, heading, cards, filters, table);
                main.setPadding(new Insets(25));
                main.setStyle("-fx-background-color: #0c141d");

                setPage(main);
        }

        private HBox createClubStat(String iconText, String title, String number, String color) {

                Label icon = new Label(iconText);
                icon.setStyle("-fx-text-fill: " + color
                                + ";-fx-font-size: 18px;-fx-background-color: #29323c;-fx-background-radius: 50%;-fx-padding: 8px");

                Label text = new Label(title);
                text.setStyle("-fx-text-fill: lightgray;-fx-font-size: 12px");

                Label value = new Label(number);
                value.setStyle("-fx-text-fill: white;-fx-font-size: 22px;-fx-font-weight: bold");

                VBox info = new VBox(2, text, value);

                HBox card = new HBox(15, icon, info);
                card.setAlignment(Pos.CENTER_LEFT);
                card.setPrefWidth(180);
                card.setPrefHeight(100);
                card.setPadding(new Insets(15));
                card.setStyle("-fx-background-color: #151515;-fx-border-color: #252525;-fx-border-radius: 8px;-fx-background-radius: 8px");

                return card;
        }

        private HBox createFilters() {

                TextField search = new TextField();
                search.setPromptText("🔍  Search clubs...");
                search.setPrefWidth(200);

                Button location = new Button("Location (All) ⌄");
                location.setStyle(
                                "-fx-background-color: #080808;-fx-text-fill: white;-fx-border-color: #252525;-fx-border-radius: 5px");

                Button status = new Button("Status (All) ⌄");
                status.setStyle("-fx-background-color: #080808;-fx-text-fill: white;-fx-border-color: #252525;-fx-border-radius: 5px");

                Label sort = new Label("Sort by:");
                sort.setStyle("-fx-text-fill: lightgray;-fx-font-size: 11px");

                Button newest = new Button("Newest First   ⌄");
                newest.setStyle("-fx-background-color: #080808;-fx-text-fill: white;-fx-border-color: #252525;-fx-border-radius: 5px");

                HBox filters = new HBox(12, search, location, status, sort, newest);
                filters.setAlignment(Pos.CENTER_LEFT);
                filters.setPadding(new Insets(10));
                filters.setStyle(
                                "-fx-background-color: #151515;-fx-border-color: #252525;-fx-border-radius: 8px;-fx-background-radius: 8px");

                return filters;
        }

        private VBox createClubTable() {

                Label clubHeader = new Label("CLUB");
                clubHeader.setPrefWidth(100);
                clubHeader.setStyle("-fx-text-fill: #c6d88a;-fx-font-size: 9px;-fx-font-weight: bold");

                Label ownerHeader = new Label("OWNER");
                ownerHeader.setPrefWidth(100);
                ownerHeader.setStyle("-fx-text-fill: #c6d88a;-fx-font-size: 9px;-fx-font-weight: bold");

                Label locationHeader = new Label("LOCATION");
                locationHeader.setPrefWidth(110);
                locationHeader.setStyle("-fx-text-fill: #c6d88a;-fx-font-size: 9px;-fx-font-weight: bold");

                Label membersHeader = new Label("MEMBERS");
                membersHeader.setPrefWidth(70);
                membersHeader.setStyle("-fx-text-fill: #c6d88a;-fx-font-size: 9px;-fx-font-weight: bold");

                Label joinedHeader = new Label("JOINED DATE");
                joinedHeader.setPrefWidth(90);
                joinedHeader.setStyle("-fx-text-fill: #c6d88a;-fx-font-size: 9px;-fx-font-weight: bold");

                Label statusHeader = new Label("STATUS");
                statusHeader.setPrefWidth(70);
                statusHeader.setStyle("-fx-text-fill: #c6d88a;-fx-font-size: 9px;-fx-font-weight: bold");

                Label actionHeader = new Label("ACTIONS");
                actionHeader.setPrefWidth(70);
                actionHeader.setStyle("-fx-text-fill: #c6d88a;-fx-font-size: 9px;-fx-font-weight: bold");

                HBox header = new HBox(20, clubHeader, ownerHeader, locationHeader, membersHeader, joinedHeader,
                                statusHeader, actionHeader);
                header.setPadding(new Insets(15));
                header.setStyle("-fx-background-color: #080808");

                HBox row1 = createClubRow("FitZone Fitness Club", "Rahul Sharma", "Pune, Maharashtra", "1,250",
                                "12 Aug 2026", "Active", "#82d000");

                HBox row2 = createClubRow("PowerFit Gym", "Priya Patil", "Mumbai, Maharashtra", "850", "08 Aug 2026",
                                "Pending", "#ffcc00");

                HBox row3 = createClubRow("IronCore Studio", "Amit Singh", "Delhi, NCR", "320", "01 Jul 2026",
                                "Inactive", "#aaaaaa");

                VBox table = new VBox(0, header, row1, row2, row3);
                table.setStyle("-fx-background-color: #151515;-fx-border-color: #252525;-fx-border-radius: 8px;-fx-background-radius: 8px");

                return table;
        }

        private HBox createClubRow(String club, String owner, String location, String members, String joined,
                        String status, String color) {

                Label clubLabel = new Label(club);
                clubLabel.setPrefWidth(100);
                clubLabel.setStyle("-fx-text-fill: white;-fx-font-size: 11px;-fx-font-weight: bold");

                Label ownerLabel = new Label(owner);
                ownerLabel.setPrefWidth(100);
                ownerLabel.setStyle("-fx-text-fill: lightgray;-fx-font-size: 10px");

                Label locationLabel = new Label(location);
                locationLabel.setPrefWidth(110);
                locationLabel.setStyle("-fx-text-fill: lightgray;-fx-font-size: 10px");

                Label membersLabel = new Label(members);
                membersLabel.setPrefWidth(70);
                membersLabel.setStyle("-fx-text-fill: white;-fx-font-size: 10px");

                Label joinedLabel = new Label(joined);
                joinedLabel.setPrefWidth(90);
                joinedLabel.setStyle("-fx-text-fill: lightgray;-fx-font-size: 10px");

                Label statusLabel = new Label(status);
                statusLabel.setPrefWidth(70);
                statusLabel.setStyle("-fx-text-fill: " + color
                                + ";-fx-font-size: 9px;-fx-background-color: #303943;-fx-background-radius: 10px;-fx-padding: 3px 8px");

                Label action = new Label("⋮");
                action.setStyle("-fx-text-fill: lightgray;-fx-font-size: 18px");

                HBox row = new HBox(20, clubLabel, ownerLabel, locationLabel, membersLabel, joinedLabel, statusLabel,
                                action);
                row.setAlignment(Pos.CENTER_LEFT);
                row.setPadding(new Insets(12));
                row.setStyle("-fx-background-color: #151515;-fx-border-color: #222222;-fx-border-width: 0 0 1px 0");

                return row;
        }

        private void showUsers() {

                VBox page = createSimplePage("Users", "Manage all FitCircle users.");

                HBox card1 = createSimpleCard("Total Users", "2,340");
                HBox card2 = createSimpleCard("Active Users", "2,180");
                HBox card3 = createSimpleCard("Blocked Users", "160");

                HBox cards = new HBox(20, card1, card2, card3);

                page = new VBox(20, page.getChildren().get(0), page.getChildren().get(1), cards);

                setPage(page);
        }

        private void showBookings() {

                Label title = new Label("Bookings");
                title.setStyle("-fx-text-fill: white;-fx-font-size: 30px;-fx-font-weight: bold");

                Label subtitle = new Label("View and manage all bookings.");
                subtitle.setStyle("-fx-text-fill: #999999;-fx-font-size: 14px");

                HBox card1 = createSimpleCard("Total Bookings", "12,450");
                HBox card2 = createSimpleCard("Today's Bookings", "245");
                HBox card3 = createSimpleCard("Cancelled", "32");

                HBox cards = new HBox(20, card1, card2, card3);

                VBox page = new VBox(20, title, subtitle, cards);
                page.setPadding(new Insets(40));
                page.setStyle("-fx-background-color: #0d0f12");

                setPage(page);
        }

        private void showReports() {

                Label title = new Label("Reports");
                title.setStyle("-fx-text-fill: white;-fx-font-size: 30px;-fx-font-weight: bold");

                Label subtitle = new Label("View FitCircle reports and statistics.");
                subtitle.setStyle("-fx-text-fill: #999999;-fx-font-size: 14px");

                HBox card1 = createSimpleCard("Monthly Revenue", "₹45.2L");
                HBox card2 = createSimpleCard("Monthly Bookings", "12,450");
                HBox card3 = createSimpleCard("New Clubs", "48");

                HBox cards = new HBox(20, card1, card2, card3);

                VBox page = new VBox(20, title, subtitle, cards);
                page.setPadding(new Insets(40));
                page.setStyle("-fx-background-color: #0d0f12");

                setPage(page);
        }

        private void showSettings() {

                Label title = new Label("Settings");
                title.setStyle("-fx-text-fill: white;-fx-font-size: 30px;-fx-font-weight: bold");

                Label subtitle = new Label("Manage administrator settings.");
                subtitle.setStyle("-fx-text-fill: #999999;-fx-font-size: 14px");

                Button account = new Button("Account Settings");
                account.setMaxWidth(Double.MAX_VALUE);
                account.setPrefHeight(45);
                account.setStyle("-fx-background-color: #252b32;-fx-text-fill: white;-fx-background-radius: 6px");

                Button notifications = new Button("Notification Settings");
                notifications.setMaxWidth(Double.MAX_VALUE);
                notifications.setPrefHeight(45);
                notifications.setStyle("-fx-background-color: #252b32;-fx-text-fill: white;-fx-background-radius: 6px");

                Button security = new Button("Security Settings");
                security.setMaxWidth(Double.MAX_VALUE);
                security.setPrefHeight(45);
                security.setStyle("-fx-background-color: #252b32;-fx-text-fill: white;-fx-background-radius: 6px");

                VBox page = new VBox(20, title, subtitle, account, notifications, security);
                page.setPadding(new Insets(40));
                page.setStyle("-fx-background-color: #0d0f12");

                setPage(page);
        }

        private VBox createSimplePage(String titleText, String subtitleText) {

                Label title = new Label(titleText);
                title.setStyle("-fx-text-fill: white;-fx-font-size: 30px;-fx-font-weight: bold");

                Label subtitle = new Label(subtitleText);
                subtitle.setStyle("-fx-text-fill: #999999;-fx-font-size: 14px");

                VBox page = new VBox(20, title, subtitle);
                page.setPadding(new Insets(40));
                page.setStyle("-fx-background-color: #0d0f12");

                return page;
        }

        private HBox createSimpleCard(String title, String value) {

                Label titleLabel = new Label(title);
                titleLabel.setStyle("-fx-text-fill: #999999;-fx-font-size: 14px");

                Label valueLabel = new Label(value);
                valueLabel.setStyle("-fx-text-fill: #5cff00;-fx-font-size: 25px;-fx-font-weight: bold");

                VBox info = new VBox(8, titleLabel, valueLabel);

                HBox card = new HBox(info);
                card.setPadding(new Insets(20));
                card.setPrefHeight(100);
                card.setPrefWidth(250);
                card.setStyle("-fx-background-color: #181c22;-fx-border-color: #292f38;-fx-border-radius: 10px;-fx-background-radius: 10px");

                return card;
        }

        private void showAddClubDialog() {

                Dialog<ButtonType> dialog = new Dialog<>();

                dialog.setTitle("Add New Club");
                dialog.setHeaderText("Enter New Club Details");

                Label clubNameLabel = new Label("Club Name:");

                TextField clubName = new TextField();
                clubName.setPromptText("Enter club name");

                HBox clubNameBox = new HBox(10, clubNameLabel, clubName);
                clubNameBox.setAlignment(Pos.CENTER_LEFT);

                Label categoryLabel = new Label("Category:");

                TextField category = new TextField();
                category.setPromptText("Enter category");

                HBox categoryBox = new HBox(10, categoryLabel, category);
                categoryBox.setAlignment(Pos.CENTER_LEFT);

                Label locationLabel = new Label("Location:");

                TextField location = new TextField();
                location.setPromptText("Enter location");

                HBox locationBox = new HBox(10, locationLabel, location);
                locationBox.setAlignment(Pos.CENTER_LEFT);

                Label ownerLabel = new Label("Owner Name:");

                TextField owner = new TextField();
                owner.setPromptText("Enter owner name");

                HBox ownerBox = new HBox(10, ownerLabel, owner);
                ownerBox.setAlignment(Pos.CENTER_LEFT);

                Label phoneLabel = new Label("Phone Number:");

                TextField phone = new TextField();
                phone.setPromptText("Enter phone number");

                HBox phoneBox = new HBox(10, phoneLabel, phone);
                phoneBox.setAlignment(Pos.CENTER_LEFT);

                Label membersLabel = new Label("Expected Members:");

                TextField members = new TextField();
                members.setPromptText("Example: 100+");

                HBox membersBox = new HBox(10, membersLabel, members);
                membersBox.setAlignment(Pos.CENTER_LEFT);

                Label facilityLabel = new Label("Facility Size:");

                TextField facility = new TextField();
                facility.setPromptText("Example: 5000 sq ft");

                HBox facilityBox = new HBox(10, facilityLabel, facility);
                facilityBox.setAlignment(Pos.CENTER_LEFT);

                Label descriptionLabel = new Label("Description:");

                TextArea description = new TextArea();
                description.setPromptText("Enter club description");
                description.setPrefRowCount(4);

                VBox descriptionBox = new VBox(5, descriptionLabel, description);

                VBox form = new VBox(12, clubNameBox, categoryBox, locationBox, ownerBox, phoneBox, membersBox,
                                facilityBox, descriptionBox);
                form.setPadding(new Insets(20));

                ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                ButtonType add = new ButtonType("Add Club", ButtonBar.ButtonData.OK_DONE);

                dialog.getDialogPane().getButtonTypes().addAll(cancel, add);

                dialog.getDialogPane().setContent(form);

                Button addButton = (Button) dialog.getDialogPane().lookupButton(add);

                addButton.setDisable(true);

                clubName.textProperty().addListener((obs, oldValue, newValue) -> {
                        addButton.setDisable(newValue.trim().isEmpty());
                });

                com.flexforce.view.components.DialogUtils.applyTheme(dialog);
                dialog.showAndWait().ifPresent(result -> {

                        if (result == add) {

                                Alert success = new Alert(Alert.AlertType.INFORMATION);

                                success.setTitle("Club Added");
                                success.setHeaderText("Club Added Successfully");
                                success.setContentText(clubName.getText() + " has been added.");

                                com.flexforce.view.components.DialogUtils.applyTheme(success);
                                success.showAndWait();
                        }
                });
        }
}