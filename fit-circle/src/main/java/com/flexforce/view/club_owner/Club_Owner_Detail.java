
package com.flexforce.view.club_owner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

import java.util.Locale;
import javafx.scene.layout.FlowPane;

import com.flexforce.config.FirebaseConfig;
import com.flexforce.controller.AuthControllerClub;
import com.flexforce.controller.ClubownerController;
import com.flexforce.controller.CloudinaryImageUploadController;
import com.flexforce.model.club_owner.ClubOwner;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javafx.stage.FileChooser;

public class Club_Owner_Detail {

        // =========================================================
        // FIREBASE PROFILE DATA
        // =========================================================

        private final Firestore db = FirebaseConfig.getFirebaseConfig();

        private TextField clubNameField;
        private TextField categoryField;
        private TextArea descriptionField;
        private TextField contactField;
        private TextArea addressField;
        private TextField emailField;
        private TextField websiteField;
        private Label capacityNumberLabel;
        private TextField priceField;
        private TextField addAmenityField;

        private ImageView gymView;
        private File selectedProfileImage;
        private String profileImageUrl;

        private Button lockerButton;
        private Button showersButton;
        private Button wifiButton;
        private Button saunaButton;
        private Button juiceButton;

        // Custom amenities entered by the club owner
        private FlowPane customAmenitiesPane;
        private final List<String> customAmenityNames = new ArrayList<>();

        // =========================================================
        // FITCIRCLE THEME COLORS
        // =========================================================

        private static final String BACKGROUND = "#0B0F14";
        private static final String CARD = "#151C24";
        private static final String INNER_CARD = "#1C252E";
        private static final String BORDER = "#2A3541";

        private static final String GREEN = "#C6FF00";

        private static final String WHITE = "#FFFFFF";
        private static final String TEXT_GRAY = "#B8C1CC";
        private static final String DARK_TEXT = "#7F8A97";

        // =========================================================
        // MAIN DETAILS PAGE
        // =========================================================

        public ScrollPane createDetailsPage() {

                // =====================================================
                // PAGE TITLE
                // =====================================================

                Label title = new Label("Club Owner Details");

                title.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                28));

                title.setTextFill(Color.WHITE);

                Label subtitle = new Label(
                                "Manage your club profile, facilities, and operational information.");

                subtitle.setFont(
                                Font.font(
                                                "Arial",
                                                14));

                subtitle.setTextFill(
                                Color.web(TEXT_GRAY));

                // =====================================================
                // ACTIVE STATUS
                // =====================================================

                Label active = new Label("●  ACTIVE");

                active.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                12));

                active.setTextFill(
                                Color.web(GREEN));

                active.setStyle(
                                "-fx-background-color:#172400;" +
                                                "-fx-border-color:" + GREEN + ";" +
                                                "-fx-border-radius:18;" +
                                                "-fx-background-radius:18;" +
                                                "-fx-padding:7 14 7 14;");

                VBox titleBox = new VBox(
                                7,
                                title,
                                subtitle,
                                active);

                // =====================================================
                // TOP BUTTONS
                // =====================================================

                Button preview = new Button("Preview Profile");

                preview.setPrefWidth(125);
                preview.setPrefHeight(42);

                preview.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-text-fill:" + GREEN + ";" +
                                                "-fx-border-color:" + GREEN + ";" +
                                                "-fx-border-radius:8;" +
                                                "-fx-background-radius:8;" +
                                                "-fx-font-size:13px;" +
                                                "-fx-cursor:hand;");

                Button saveTop = new Button("Save Changes");

                saveTop.setPrefWidth(125);
                saveTop.setPrefHeight(42);

                saveTop.setStyle(
                                "-fx-background-color:" + GREEN + ";" +
                                                "-fx-text-fill:#101510;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-background-radius:8;" +
                                                "-fx-font-size:13px;" +
                                                "-fx-cursor:hand;");

                HBox titleButtons = new HBox(
                                10,
                                preview,
                                saveTop);

                titleButtons.setAlignment(
                                Pos.CENTER_RIGHT);

                BorderPane titleArea = new BorderPane();

                titleArea.setLeft(titleBox);
                titleArea.setRight(titleButtons);

                titleArea.setPadding(
                                new Insets(
                                                0,
                                                0,
                                                5,
                                                0));

                // =====================================================
                // CLUB PROFILE
                // =====================================================

                Image gymImage = new Image(
                                "assets\\images\\gym background.jpg");

                this.gymView = new ImageView(gymImage);

                gymView.setFitHeight(170);
                gymView.setFitWidth(650);

                gymView.setPreserveRatio(false);

                // =====================================================
                // CLUB LOGO
                // =====================================================

                Label clubLogo = new Label(
                                "FIT\nCIRCLE");

                clubLogo.setPrefSize(
                                70,
                                70);

                clubLogo.setAlignment(
                                Pos.CENTER);

                clubLogo.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                12));

                clubLogo.setTextFill(
                                Color.web("#101510"));

                clubLogo.setStyle(
                                "-fx-background-color:" + GREEN + ";" +
                                                "-fx-background-radius:40;");

                // =====================================================
                // CLUB NAME
                // =====================================================

                TextField clubName = new TextField(
                                "Titan Fitness - Downtown");

                this.clubNameField = clubName;

                clubName.setPrefWidth(230);

                clubName.setStyle(
                                "-fx-background-color:" + INNER_CARD + ";" +
                                                "-fx-text-fill:white;" +
                                                "-fx-border-color:" + BORDER + ";" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;" +
                                                "-fx-padding:8;");

                // =====================================================
                // CATEGORY
                // =====================================================

                TextField category = new TextField(
                                "Premium Gym");

                this.categoryField = category;

                category.setPrefWidth(150);

                category.setStyle(
                                "-fx-background-color:" + INNER_CARD + ";" +
                                                "-fx-text-fill:white;" +
                                                "-fx-border-color:" + BORDER + ";" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;" +
                                                "-fx-padding:8;");

                HBox clubInfo = new HBox(
                                10,
                                clubLogo,
                                clubName,
                                category);

                clubInfo.setPadding(
                                new Insets(10));

                clubInfo.setAlignment(
                                Pos.CENTER_LEFT);

                VBox profileCard = new VBox(
                                gymView,
                                clubInfo);

                profileCard.setStyle(
                                "-fx-background-color:" + CARD + ";" +
                                                "-fx-border-color:" + BORDER + ";" +
                                                "-fx-border-radius:12;" +
                                                "-fx-background-radius:12;");

                // =====================================================
                // GENERAL INFORMATION
                // =====================================================

                Label generalTitle = new Label(
                                "General Information");

                generalTitle.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                21));

                generalTitle.setTextFill(
                                Color.WHITE);

                Label descriptionLabel = new Label(
                                "Club Description");

                descriptionLabel.setTextFill(
                                Color.web(TEXT_GRAY));

                TextArea description = new TextArea(
                                "Experience fitness at its peak at Titan Fitness Downtown. " +
                                                "Our 20,000 sq ft facility features state-of-the-art equipment, " +
                                                "elite personal trainers, and over 50 group classes weekly designed to push your limits.");

                this.descriptionField = description;

                description.setPrefHeight(110);
                description.setWrapText(true);

                description.setStyle(
                                "-fx-control-inner-background:" + INNER_CARD + ";" +
                                                "-fx-text-fill:white;" +
                                                "-fx-highlight-fill:" + GREEN + ";" +
                                                "-fx-highlight-text-fill:black;" +
                                                "-fx-border-color:" + BORDER + ";" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;");

                // =====================================================
                // CONTACT
                // =====================================================

                Label contactLabel = new Label(
                                "Contact Number");

                contactLabel.setTextFill(
                                Color.web(TEXT_GRAY));

                TextField contact = new TextField(
                                "+1 (555) 123-4567");

                this.contactField = contact;

                styleTextField(contact);

                VBox contactBox = new VBox(
                                6,
                                contactLabel,
                                contact);

                // =====================================================
                // ADDRESS
                // =====================================================

                Label addressLabel = new Label(
                                "Physical Address");

                addressLabel.setTextFill(
                                Color.web(TEXT_GRAY));

                TextArea address = new TextArea(
                                "1200 Energy Ave, Suite 100\nMetropolis, NY 10001");

                this.addressField = address;

                address.setPrefHeight(70);
                address.setWrapText(true);

                address.setStyle(
                                "-fx-control-inner-background:" + INNER_CARD + ";" +
                                                "-fx-text-fill:white;" +
                                                "-fx-border-color:" + BORDER + ";" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;");

                VBox addressBox = new VBox(
                                6,
                                addressLabel,
                                address);

                HBox informationRow1 = new HBox(
                                15,
                                contactBox,
                                addressBox);

                HBox.setHgrow(
                                contactBox,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                addressBox,
                                Priority.ALWAYS);

                // =====================================================
                // EMAIL
                // =====================================================

                Label emailLabel = new Label(
                                "Email Address");

                emailLabel.setTextFill(
                                Color.web(TEXT_GRAY));

                TextField email = new TextField(
                                "hello@titanfitness.com");

                this.emailField = email;
                email.setEditable(false);
                email.setFocusTraversable(false);

                styleTextField(email);

                VBox emailBox = new VBox(
                                6,
                                emailLabel,
                                email);

                // =====================================================
                // WEBSITE
                // =====================================================

                Label websiteLabel = new Label(
                                "Website");

                websiteLabel.setTextFill(
                                Color.web(TEXT_GRAY));

                TextField website = new TextField(
                                "https://titanfitness.com");

                this.websiteField = website;

                styleTextField(website);

                VBox websiteBox = new VBox(
                                6,
                                websiteLabel,
                                website);

                HBox informationRow2 = new HBox(
                                15,
                                emailBox,
                                websiteBox);

                HBox.setHgrow(
                                emailBox,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                websiteBox,
                                Priority.ALWAYS);

                VBox informationBox = new VBox(
                                12,
                                informationRow1,
                                informationRow2);

                VBox generalCard = new VBox(
                                12,
                                generalTitle,
                                descriptionLabel,
                                description,
                                informationBox);

                generalCard.setPadding(
                                new Insets(20));

                generalCard.setStyle(
                                "-fx-background-color:" + CARD + ";" +
                                                "-fx-border-color:" + BORDER + ";" +
                                                "-fx-border-radius:12;" +
                                                "-fx-background-radius:12;");

                // =====================================================
                // FACILITY GALLERY
                // =====================================================

                Label galleryTitle = new Label(
                                "Facility Gallery");

                galleryTitle.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                21));

                galleryTitle.setTextFill(
                                Color.WHITE);

                Button addPhotos = new Button(
                                "+ Add Photos");

                addPhotos.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-text-fill:" + GREEN + ";" +
                                                "-fx-font-size:13px;" +
                                                "-fx-cursor:hand;");

                addPhotos.setOnAction(e -> chooseProfileImage(addPhotos));


                BorderPane galleryHeading = new BorderPane();

                galleryHeading.setLeft(
                                galleryTitle);

                galleryHeading.setRight(
                                addPhotos);

                // =====================================================
                // GALLERY IMAGE 1
                // =====================================================

                Image photo1 = new Image(
                                "assets\\images\\img 1.jpg");

                ImageView photo1View = new ImageView(photo1);

                photo1View.setFitHeight(100);
                photo1View.setFitWidth(110);
                photo1View.setPreserveRatio(false);

                // =====================================================
                // GALLERY IMAGE 2
                // =====================================================

                Image photo2 = new Image(
                                "assets\\images\\img 2.jpg");

                ImageView photo2View = new ImageView(photo2);

                photo2View.setFitHeight(100);
                photo2View.setFitWidth(110);
                photo2View.setPreserveRatio(false);

                // =====================================================
                // GALLERY IMAGE 3
                // =====================================================

                Image photo3 = new Image(
                                "assets\\images\\img 3.jpg");

                ImageView photo3View = new ImageView(photo3);

                photo3View.setFitHeight(100);
                photo3View.setFitWidth(110);
                photo3View.setPreserveRatio(false);

                // =====================================================
                // UPLOAD BUTTON
                // =====================================================

                Button upload = new Button(
                                "↑\nUpload");

                upload.setPrefSize(
                                110,
                                100);

                upload.setTextFill(
                                Color.web(TEXT_GRAY));

                upload.setStyle(
                                "-fx-background-color:" + INNER_CARD + ";" +
                                                "-fx-border-color:" + BORDER + ";" +
                                                "-fx-border-radius:8;" +
                                                "-fx-background-radius:8;" +
                                                "-fx-cursor:hand;");

                upload.setOnAction(e -> chooseProfileImage(upload));


                HBox photos = new HBox(
                                12,
                                photo1View,
                                photo2View,
                                photo3View,
                                upload);

                VBox galleryCard = new VBox(
                                12,
                                galleryHeading,
                                photos);

                galleryCard.setPadding(
                                new Insets(20));

                galleryCard.setStyle(
                                "-fx-background-color:" + CARD + ";" +
                                                "-fx-border-color:" + BORDER + ";" +
                                                "-fx-border-radius:12;" +
                                                "-fx-background-radius:12;");

                // =====================================================
                // STATISTICS - MAX CAPACITY
                // =====================================================

                Label capacityIcon = new Label("♟");

                capacityIcon.setTextFill(
                                Color.web(GREEN));

                capacityIcon.setStyle(
                                "-fx-font-size:18px;");

                Label capacityNumber = new Label("1,246");

                // IMPORTANT: keep a class-level reference so Firebase load
                // can update the same label without NullPointerException.
                this.capacityNumberLabel = capacityNumber;

                // Keep the existing statistics UI unchanged.

                capacityNumber.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                25));

                capacityNumber.setTextFill(
                                Color.WHITE);

                Label capacityText = new Label(
                                "MAX CAPACITY");

                capacityText.setFont(
                                Font.font(10));

                capacityText.setTextFill(
                                Color.web(TEXT_GRAY));

                VBox capacity = new VBox(
                                6,
                                capacityIcon,
                                capacityNumber,
                                capacityText);

                capacity.setAlignment(
                                Pos.CENTER);

                capacity.setPadding(
                                new Insets(18));

                capacity.setStyle(
                                "-fx-background-color:" + CARD + ";" +
                                                "-fx-border-color:" + BORDER + ";" +
                                                "-fx-border-radius:12;" +
                                                "-fx-background-radius:12;");

                // =====================================================
                // ACTIVE TRAINERS
                // =====================================================

                Label trainerIcon = new Label("♙");

                trainerIcon.setTextFill(
                                Color.web(GREEN));

                trainerIcon.setStyle(
                                "-fx-font-size:18px;");

                Label trainerNumber = new Label("24");

                trainerNumber.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                25));

                trainerNumber.setTextFill(
                                Color.WHITE);

                Label trainerText = new Label(
                                "ACTIVE TRAINERS");

                trainerText.setFont(
                                Font.font(10));

                trainerText.setTextFill(
                                Color.web(TEXT_GRAY));

                VBox trainerCard = new VBox(
                                6,
                                trainerIcon,
                                trainerNumber,
                                trainerText);

                trainerCard.setAlignment(
                                Pos.CENTER);

                trainerCard.setPadding(
                                new Insets(18));

                trainerCard.setStyle(
                                "-fx-background-color:" + CARD + ";" +
                                                "-fx-border-color:" + BORDER + ";" +
                                                "-fx-border-radius:12;" +
                                                "-fx-background-radius:12;");

                HBox statistics = new HBox(
                                12,
                                capacity,
                                trainerCard);

                HBox.setHgrow(
                                capacity,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                trainerCard,
                                Priority.ALWAYS);

                // =====================================================
                // AMENITIES
                // =====================================================

                Label amenitiesTitle = new Label(
                                "Amenities & Facilities");

                amenitiesTitle.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                21));

                amenitiesTitle.setTextFill(
                                Color.WHITE);

                Button locker = createAmenityButton(
                                "Locker Rooms ×");
                this.lockerButton = locker;

                Button showers = createAmenityButton(
                                "Showers ×");
                this.showersButton = showers;

                Button wifi = createAmenityButton(
                                "Free Wi-Fi ×");
                this.wifiButton = wifi;

                Button sauna = createAmenityButton(
                                "Sauna ×");
                this.saunaButton = sauna;

                Button juice = createAmenityButton(
                                "Juice Bar ×");
                this.juiceButton = juice;

                HBox amenityRow1 = new HBox(
                                7,
                                locker,
                                showers);

                HBox amenityRow2 = new HBox(
                                7,
                                wifi,
                                sauna);

                HBox amenityRow3 = new HBox(
                                7,
                                juice);

                // =====================================================
                // CUSTOM AMENITIES AREA
                // =====================================================

                customAmenitiesPane = new FlowPane();

                customAmenitiesPane.setHgap(7);
                customAmenitiesPane.setVgap(7);
                customAmenitiesPane.setPrefWrapLength(300);
                customAmenitiesPane.setAlignment(Pos.CENTER_LEFT);

                // =====================================================
                // ADD AMENITY TEXT FIELD
                // =====================================================

                TextField addAmenity = new TextField();

                this.addAmenityField = addAmenity;

                addAmenity.setPromptText(
                                "+ Add amenity...");

                addAmenity.setStyle(
                                "-fx-background-color:" + INNER_CARD + ";" +
                                                "-fx-text-fill:white;" +
                                                "-fx-prompt-text-fill:#6F7984;" +
                                                "-fx-border-color:" + BORDER + ";" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;");

                // Press ENTER to add the custom amenity.
                addAmenity.setOnAction(event -> addCustomAmenity());

                VBox amenities = new VBox(
                                12,
                                amenitiesTitle,
                                amenityRow1,
                                amenityRow2,
                                amenityRow3,
                                customAmenitiesPane,
                                addAmenity);

                amenities.setPadding(
                                new Insets(20));

                amenities.setStyle(
                                "-fx-background-color:" + CARD + ";" +
                                                "-fx-border-color:" + BORDER + ";" +
                                                "-fx-border-radius:12;" +
                                                "-fx-background-radius:12;");

                // =====================================================
                // OPERATIONAL DETAILS
                // =====================================================

                Label operationTitle = new Label(
                                "Operational Details");

                operationTitle.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                21));

                operationTitle.setTextFill(
                                Color.WHITE);

                Label hoursTitle = new Label(
                                "Operating Hours");

                hoursTitle.setTextFill(
                                Color.web(TEXT_GRAY));

                Label hours = new Label(
                                "Mon - Fri       5:00 AM - 11:00 PM\n\n" +
                                                "Sat - Sun       7:00 AM - 9:00 PM");

                hours.setTextFill(
                                Color.WHITE);

                hours.setPadding(
                                new Insets(12));

                hours.setMaxWidth(
                                Double.MAX_VALUE);

                hours.setStyle(
                                "-fx-background-color:" + INNER_CARD + ";" +
                                                "-fx-background-radius:7;" +
                                                "-fx-border-color:" + BORDER + ";" +
                                                "-fx-border-radius:7;");

                Label priceTitle = new Label(
                                "Starting Price (Monthly)");

                priceTitle.setTextFill(
                                Color.web(TEXT_GRAY));

                TextField price = new TextField(
                                "₹  89");

                this.priceField = price;

                styleTextField(price);

                Label ratingTitle = new Label(
                                "Public Rating");

                ratingTitle.setTextFill(
                                Color.web(TEXT_GRAY));

                Label rating = new Label(
                                "★★★★★  4.8   (342 Reviews)");

                rating.setFont(
                                Font.font(
                                                16));

                rating.setTextFill(
                                Color.web(GREEN));

                VBox operations = new VBox(
                                12,
                                operationTitle,
                                hoursTitle,
                                hours,
                                priceTitle,
                                price,
                                ratingTitle,
                                rating);

                operations.setPadding(
                                new Insets(20));

                operations.setStyle(
                                "-fx-background-color:" + CARD + ";" +
                                                "-fx-border-color:" + BORDER + ";" +
                                                "-fx-border-radius:12;" +
                                                "-fx-background-radius:12;");

                // =====================================================
                // TWO COLUMN LAYOUT
                // =====================================================

                VBox leftColumn = new VBox(
                                18,
                                profileCard,
                                generalCard,
                                galleryCard);

                VBox rightColumn = new VBox(
                                18,
                                statistics,
                                amenities,
                                operations);

                HBox mainArea = new HBox(
                                18,
                                leftColumn,
                                rightColumn);

                HBox.setHgrow(
                                leftColumn,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                rightColumn,
                                Priority.ALWAYS);

                // =====================================================
                // BOTTOM BAR
                // =====================================================

                Label updated = new Label(
                                "Last updated: Today at 10:42 AM");

                updated.setTextFill(
                                Color.web(TEXT_GRAY));

                Button cancel = new Button(
                                "Cancel");

                cancel.setPrefHeight(36);

                cancel.setStyle(
                                "-fx-background-color:" + INNER_CARD + ";" +
                                                "-fx-text-fill:white;" +
                                                "-fx-border-color:" + BORDER + ";" +
                                                "-fx-border-radius:7;" +
                                                "-fx-background-radius:7;" +
                                                "-fx-cursor:hand;");

                Button reset = new Button(
                                "Reset");

                reset.setPrefHeight(36);

                reset.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-border-color:" + GREEN + ";" +
                                                "-fx-text-fill:" + GREEN + ";" +
                                                "-fx-border-radius:7;" +
                                                "-fx-background-radius:7;" +
                                                "-fx-cursor:hand;");

                Button saveBottom = new Button(
                                "Save Changes");

                saveBottom.setPrefHeight(36);

                saveBottom.setStyle(
                                "-fx-background-color:" + GREEN + ";" +
                                                "-fx-text-fill:#101510;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-background-radius:7;" +
                                                "-fx-cursor:hand;");

                HBox bottomButtons = new HBox(
                                10,
                                cancel,
                                reset,
                                saveBottom);

                bottomButtons.setAlignment(
                                Pos.CENTER_RIGHT);

                BorderPane bottomBar = new BorderPane();

                bottomBar.setLeft(
                                updated);

                bottomBar.setRight(
                                bottomButtons);

                bottomBar.setPadding(
                                new Insets(15));

                bottomBar.setStyle(
                                "-fx-background-color:" + CARD + ";" +
                                                "-fx-border-color:" + BORDER + ";" +
                                                "-fx-border-radius:10;" +
                                                "-fx-background-radius:10;");

                // =====================================================
                // FINAL PAGE CONTENT
                // =====================================================

                VBox clubPage = new VBox(
                                18,
                                titleArea,
                                mainArea,
                                bottomBar);

                clubPage.setPadding(
                                new Insets(
                                                25,
                                                30,
                                                30,
                                                30));

                clubPage.setStyle(
                                "-fx-background-color:" + BACKGROUND + ";");

                // =====================================================
                // SCROLL PANE
                // =====================================================

                ScrollPane scrollPane = new ScrollPane(
                                clubPage);

                scrollPane.setFitToWidth(
                                true);

                scrollPane.setFitToHeight(
                                false);

                scrollPane.setPannable(
                                true);

                scrollPane.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                scrollPane.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                scrollPane.setStyle(
                                "-fx-background-color:" + BACKGROUND + ";" +
                                                "-fx-border-color:transparent;");

                // =====================================================
                // FIREBASE LOAD / SAVE
                // =====================================================

                setupProfileActions(
                                saveTop,
                                saveBottom,
                                cancel,
                                reset,
                                preview,
                                capacityNumber,
                                active);

                loadClubOwnerProfile();

                return scrollPane;
        }

        // =========================================================
        // PROFILE ACTIONS
        // =========================================================

        private void setupProfileActions(
                        Button saveTop,
                        Button saveBottom,
                        Button cancel,
                        Button reset,
                        Button preview,
                        Label capacityNumber,
                        Label active) {

                saveTop.setOnAction(event -> saveClubOwnerProfile(capacityNumber));
                saveBottom.setOnAction(event -> saveClubOwnerProfile(capacityNumber));

                cancel.setOnAction(event -> loadClubOwnerProfile());

                reset.setOnAction(event -> loadClubOwnerProfile());

                // Keep the existing Preview button/UI behaviour unchanged.
                preview.setOnAction(event -> System.out.println("Club Owner profile preview clicked."));

                setupAmenityToggle(lockerButton);
                setupAmenityToggle(showersButton);
                setupAmenityToggle(wifiButton);
                setupAmenityToggle(saunaButton);
                setupAmenityToggle(juiceButton);

                // The ACTIVE label remains part of the existing UI.
                if (active != null) {
                        active.setText("●  ACTIVE");
                }
        }

        private void setupAmenityToggle(Button button) {

                if (button == null) {
                        return;
                }

                button.setOnAction(event -> {

                        String text = button.getText();

                        if (text.endsWith("×")) {
                                button.setText(text.substring(0, text.length() - 1).trim());
                        } else {
                                button.setText(text.trim() + " ×");
                        }
                });
        }

        // =========================================================
        // LOAD CLUB OWNER PROFILE
        // =========================================================

        private void loadClubOwnerProfile() {

                String ownerId = AuthControllerClub.getCurrentOwnerId();

                if (ownerId == null || ownerId.trim().isEmpty()) {

                        System.out.println(
                                        "Club Owner ID is missing. Profile cannot be loaded.");

                        return;
                }

                try {

                        ApiFuture<DocumentSnapshot> future = db.collection("ClubOwners")
                                        .document(ownerId)
                                        .get();

                        DocumentSnapshot document = future.get();

                        if (!document.exists()) {

                                System.out.println(
                                                "Club Owner profile document not found.");

                                return;
                        }

                        setIfPresent(
                                        document,
                                        "clubName",
                                        value -> clubNameField.setText(value));

                        setIfPresent(
                                        document,
                                        "category",
                                        value -> categoryField.setText(value));

                        setIfPresent(
                                        document,
                                        "description",
                                        value -> descriptionField.setText(value));

                        setIfPresent(
                                        document,
                                        "phone",
                                        value -> contactField.setText(value));

                        setIfPresent(
                                        document,
                                        "clubAddress",
                                        value -> addressField.setText(value));

                        // Email is loaded from Firebase but is NEVER editable.
                        setIfPresent(
                                        document,
                                        "email",
                                        value -> emailField.setText(value));

                        setIfPresent(
                                        document,
                                        "website",
                                        value -> websiteField.setText(value));

                        setIfPresent(
                                        document,
                                        "capacity",
                                        value -> capacityNumberLabel.setText(value));

                        setIfPresent(
                                        document,
                                        "startingPrice",
                                        value -> priceField.setText(value));

                        // Load saved Cloudinary profile image, if available.
                        setIfPresent(
                                        document,
                                        "profileImage",
                                        value -> {
                                                profileImageUrl = value;
                                                loadProfileImageFromUrl(value);
                                        });

                        applyAmenitiesFromFirebase(document);

                        System.out.println(
                                        "Club Owner profile loaded successfully.");

                } catch (Exception e) {

                        System.out.println(
                                        "Error loading Club Owner profile.");

                        e.printStackTrace();
                }
        }

        private interface StringConsumer {
                void accept(String value);
        }

        private void setIfPresent(
                        DocumentSnapshot document,
                        String field,
                        StringConsumer consumer) {

                if (!document.contains(field)) {
                        return;
                }

                Object value = document.get(field);

                if (value != null) {
                        consumer.accept(String.valueOf(value));
                }
        }

        // =========================================================
        // SAVE CLUB OWNER PROFILE
        // EMAIL IS INTENTIONALLY NOT UPDATED
        // =========================================================

        private void saveClubOwnerProfile(Label capacityNumber) {

                String ownerId = AuthControllerClub.getCurrentOwnerId();

                if (ownerId == null || ownerId.trim().isEmpty()) {

                        System.out.println(
                                        "Club Owner ID is missing. Profile cannot be saved.");

                        return;
                }

                try {

                        // Upload newly selected image to Cloudinary first.
                        if (selectedProfileImage != null) {

                                CloudinaryImageUploadController uploadController =
                                                new CloudinaryImageUploadController();

                                String uploadedUrl =
                                                uploadController.imageUpload(
                                                                selectedProfileImage);

                                if (uploadedUrl == null || uploadedUrl.trim().isEmpty()) {

                                        showError("Image Upload Failed",
                                                        "The image could not be uploaded to Cloudinary.");
                                        return;
                                }

                                profileImageUrl = uploadedUrl;
                        }

                        Map<String, Object> data = new HashMap<>();

                        data.put(
                                        "clubName",
                                        clubNameField.getText().trim());

                        data.put(
                                        "category",
                                        categoryField.getText().trim());

                        data.put(
                                        "description",
                                        descriptionField.getText().trim());

                        data.put(
                                        "phone",
                                        contactField.getText().trim());

                        data.put(
                                        "clubAddress",
                                        addressField.getText().trim());

                        data.put(
                                        "website",
                                        websiteField.getText().trim());

                        data.put(
                                        "capacity",
                                        cleanNumber(capacityNumber.getText()));

                        data.put(
                                        "startingPrice",
                                        priceField.getText().trim());

                        data.put(
                                        "amenities",
                                        getAmenities());

                        if (profileImageUrl != null && !profileImageUrl.trim().isEmpty()) {
                                data.put(
                                                "profileImage",
                                                profileImageUrl);
                        }

                        /*
                         * IMPORTANT:
                         *
                         * There is NO:
                         *
                         * data.put("email", ...);
                         *
                         * here.
                         *
                         * Therefore the email stored during registration
                         * remains unchanged.
                         */

                        db.collection("ClubOwners")
                                        .document(ownerId)
                                        .set(
                                                        data,
                                                        SetOptions.merge())
                                        .get();

                        selectedProfileImage = null;

                        System.out.println(
                                        "Club Owner profile updated successfully.");

                } catch (Exception e) {

                        System.out.println(
                                        "Error updating Club Owner profile.");

                        e.printStackTrace();
                }
        }

        // =========================================================
        // CHOOSE PROFILE IMAGE
        // =========================================================

        private void chooseProfileImage(Button sourceButton) {

                FileChooser fileChooser = new FileChooser();

                fileChooser.setTitle("Select Club Profile Image");

                fileChooser.getExtensionFilters().add(
                                new FileChooser.ExtensionFilter(
                                                "Image Files",
                                                "*.png",
                                                "*.jpg",
                                                "*.jpeg",
                                                "*.webp"));

                javafx.stage.Window window =
                                sourceButton.getScene() != null
                                                ? sourceButton.getScene().getWindow()
                                                : null;

                File file = fileChooser.showOpenDialog(window);

                if (file == null) {
                        System.out.println("No image selected.");
                        return;
                }

                selectedProfileImage = file;

                try {
                        Image selectedImage = new Image(
                                        file.toURI().toString(),
                                        650,
                                        170,
                                        false,
                                        true);

                        if (selectedImage.isError()) {
                                selectedProfileImage = null;
                                showError("Invalid Image",
                                                "The selected image could not be loaded.");
                                return;
                        }

                        gymView.setImage(selectedImage);

                        System.out.println(
                                        "Image selected: " + file.getAbsolutePath());

                } catch (Exception e) {

                        selectedProfileImage = null;
                        e.printStackTrace();
                        showError("Image Error",
                                        "Unable to open the selected image.");
                }
        }

        // =========================================================
        // LOAD CLOUDINARY IMAGE
        // =========================================================

        private void loadProfileImageFromUrl(String url) {

                if (url == null || url.trim().isEmpty() || gymView == null) {
                        return;
                }

                try {
                        Image image = new Image(
                                        url,
                                        650,
                                        170,
                                        false,
                                        true,
                                        true);

                        if (!image.isError()) {
                                gymView.setImage(image);
                        } else {
                                System.out.println(
                                                "Saved profile image could not be loaded.");
                        }

                } catch (Exception e) {
                        System.out.println(
                                        "Error loading profile image from Cloudinary.");
                        e.printStackTrace();
                }
        }

        // =========================================================
        // ERROR ALERT
        // =========================================================

        private void showError(String title, String message) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(title);
                alert.setHeaderText(null);
                alert.setContentText(message);
                com.flexforce.view.components.DialogUtils.applyTheme(alert);
                alert.showAndWait();
        }

        private Object cleanNumber(String value) {

                if (value == null || value.trim().isEmpty()) {
                        return 0;
                }

                try {

                        return Integer.parseInt(
                                        value.replace(",", "").trim());

                } catch (NumberFormatException e) {

                        return value.trim();
                }
        }

        // =========================================================
        // AMENITIES
        // =========================================================

        private List<String> getAmenities() {

                List<String> amenities = new ArrayList<>();

                addAmenityIfSelected(
                                amenities,
                                lockerButton,
                                "Locker Rooms");

                addAmenityIfSelected(
                                amenities,
                                showersButton,
                                "Showers");

                addAmenityIfSelected(
                                amenities,
                                wifiButton,
                                "Free Wi-Fi");

                addAmenityIfSelected(
                                amenities,
                                saunaButton,
                                "Sauna");

                addAmenityIfSelected(
                                amenities,
                                juiceButton,
                                "Juice Bar");

                // Save all custom amenities already added as buttons.
                for (String amenity : customAmenityNames) {
                        if (amenity != null && !amenity.trim().isEmpty()
                                        && !containsIgnoreCase(amenities, amenity)) {
                                amenities.add(amenity.trim());
                        }
                }

                // Also save text that is currently typed but not yet added.
                String pendingAmenity = addAmenityField.getText().trim();

                if (!pendingAmenity.isEmpty()
                                && !containsIgnoreCase(amenities, pendingAmenity)) {
                        amenities.add(pendingAmenity);
                }

                return amenities;
        }

        private boolean containsIgnoreCase(
                        List<String> list,
                        String value) {

                for (String item : list) {
                        if (item != null && item.equalsIgnoreCase(value)) {
                                return true;
                        }
                }

                return false;
        }

        // =========================================================
        // ADD CUSTOM AMENITY
        // =========================================================

        private void addCustomAmenity() {

                if (addAmenityField == null) {
                        return;
                }

                String amenity = addAmenityField.getText().trim();

                if (amenity.isEmpty()) {
                        return;
                }

                if (containsIgnoreCase(customAmenityNames, amenity)
                                || isFixedAmenity(amenity)) {
                        addAmenityField.clear();
                        return;
                }

                customAmenityNames.add(amenity);

                if (customAmenitiesPane != null) {
                        customAmenitiesPane.getChildren().add(
                                        createCustomAmenityButton(amenity));
                }

                addAmenityField.clear();
        }

        private boolean isFixedAmenity(String value) {
                return value.equalsIgnoreCase("Locker Rooms")
                                || value.equalsIgnoreCase("Showers")
                                || value.equalsIgnoreCase("Free Wi-Fi")
                                || value.equalsIgnoreCase("Sauna")
                                || value.equalsIgnoreCase("Juice Bar");
        }

        // =========================================================
        // CREATE CUSTOM AMENITY BUTTON
        // =========================================================

        private Button createCustomAmenityButton(
                        String amenity) {

                Button button = createAmenityButton(amenity + " ×");

                button.setOnAction(event -> {

                        customAmenityNames.removeIf(
                                        item -> item.equalsIgnoreCase(amenity));

                        if (customAmenitiesPane != null) {
                                customAmenitiesPane.getChildren().remove(button);
                        }
                });

                return button;
        }

        private void addAmenityIfSelected(
                        List<String> list,
                        Button button,
                        String name) {

                if (button == null) {
                        return;
                }

                if (button.getText().contains("×")) {
                        list.add(name);
                }
        }

        private void applyAmenitiesFromFirebase(
                        DocumentSnapshot document) {

                if (!document.contains("amenities")) {
                        // No saved amenities: reset everything to empty/unselected.
                        resetAmenityButton(lockerButton, new ArrayList<>(), "Locker Rooms");
                        resetAmenityButton(showersButton, new ArrayList<>(), "Showers");
                        resetAmenityButton(wifiButton, new ArrayList<>(), "Free Wi-Fi");
                        resetAmenityButton(saunaButton, new ArrayList<>(), "Sauna");
                        resetAmenityButton(juiceButton, new ArrayList<>(), "Juice Bar");
                        clearCustomAmenities();
                        return;
                }

                Object raw = document.get("amenities");

                if (!(raw instanceof List<?>)) {
                        clearCustomAmenities();
                        return;
                }

                List<?> savedAmenities = (List<?>) raw;

                resetAmenityButton(
                                lockerButton,
                                savedAmenities,
                                "Locker Rooms");

                resetAmenityButton(
                                showersButton,
                                savedAmenities,
                                "Showers");

                resetAmenityButton(
                                wifiButton,
                                savedAmenities,
                                "Free Wi-Fi");

                resetAmenityButton(
                                saunaButton,
                                savedAmenities,
                                "Sauna");

                resetAmenityButton(
                                juiceButton,
                                savedAmenities,
                                "Juice Bar");

                // Rebuild custom amenities from Firebase.
                clearCustomAmenities();

                for (Object value : savedAmenities) {

                        if (value == null) {
                                continue;
                        }

                        String amenity = String.valueOf(value).trim();

                        if (amenity.isEmpty() || isFixedAmenity(amenity)) {
                                continue;
                        }

                        if (!containsIgnoreCase(
                                        customAmenityNames,
                                        amenity)) {

                                customAmenityNames.add(amenity);

                                if (customAmenitiesPane != null) {
                                        customAmenitiesPane.getChildren().add(
                                                        createCustomAmenityButton(amenity));
                                }
                        }
                }

                if (addAmenityField != null) {
                        addAmenityField.clear();
                }
        }

        private void clearCustomAmenities() {

                customAmenityNames.clear();

                if (customAmenitiesPane != null) {
                        customAmenitiesPane.getChildren().clear();
                }

                if (addAmenityField != null) {
                        addAmenityField.clear();
                }
        }

        private void resetAmenityButton(
                        Button button,
                        List<?> savedAmenities,
                        String name) {

                if (button == null) {
                        return;
                }

                boolean selected = savedAmenities.contains(name);

                if (selected) {

                        if (!button.getText().contains("×")) {
                                button.setText(
                                                name + " ×");
                        }

                } else {

                        button.setText(name);
                }
        }

        // =========================================================
        // TEXT FIELD STYLE
        // =========================================================

        private void styleTextField(
                        TextField field) {

                field.setMaxWidth(
                                Double.MAX_VALUE);

                field.setStyle(
                                "-fx-background-color:" + INNER_CARD + ";" +
                                                "-fx-text-fill:white;" +
                                                "-fx-border-color:" + BORDER + ";" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;" +
                                                "-fx-padding:8;");
        }

        // =========================================================
        // AMENITY BUTTON
        // =========================================================

        private Button createAmenityButton(
                        String text) {

                Button button = new Button(text);

                button.setStyle(
                                "-fx-background-color:" + INNER_CARD + ";" +
                                                "-fx-text-fill:#D8DEE5;" +
                                                "-fx-border-color:" + BORDER + ";" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;" +
                                                "-fx-cursor:hand;");

                return button;
        }
}