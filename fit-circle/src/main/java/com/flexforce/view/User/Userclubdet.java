package com.flexforce.view.User;

import com.flexforce.controller.AuthControllerlogin;
import com.flexforce.dao.UserClubMembershipDAO;
import com.flexforce.model.common_for_user_clubowner.UserClub;
import com.flexforce.model.common_for_user_clubowner.UserClubMembership;
import com.flexforce.view.ExplorerClubPage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Userclubdet {

         private static final String BG = "#080808";
    private static final String CARD = "#181818";
    private static final String INNER = "#222222";
    private static final String BORDER = "#333333";
    private static final String LIME = "#BFFF00";
    private static final String WHITE = "#FFFFFF";
    private static final String GRAY = "#AAAAAA";

    // =========================================================
    // OPEN CLUB DETAILS
    // =========================================================

    public static void openClubDetails(UserClub club) {

        if (club == null) {
            return;
        }

        Stage stage = new Stage();

        stage.setTitle("FitCircle - " + getText(
                club.getClubName(),
                "Club Details"
        ));

        BorderPane root = createPage(club, () -> stage.close());

        Scene scene = new Scene(root, 950, 700);

        stage.setScene(scene);
        stage.show();
    }

    // =========================================================
    // CREATE PAGE
    // =========================================================

    public static BorderPane createPage(
            UserClub club,
            Runnable onBack) {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color:" + BG + ";"
        );

        // =====================================================
        // MAIN CONTENT
        // =====================================================

        VBox content = new VBox(20);

        content.setPadding(new Insets(20));

        content.setStyle(
                "-fx-background-color:" + BG + ";"
        );

        // =====================================================
        // BACK BUTTON
        // =====================================================

        Button backButton = new Button("← Back to Explore Clubs");

        backButton.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-text-fill:" + LIME + ";" +
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;"
        );

        backButton.setOnAction(event -> {
            if (onBack != null) {
                onBack.run();
            }
        });

        content.getChildren().add(backButton);

        // =====================================================
        // CLUB IMAGE
        // =====================================================

        ImageView clubImage = createClubImage(
                club.getImage()
        );

        VBox imageBox = new VBox(clubImage);

        imageBox.setAlignment(Pos.CENTER);

        imageBox.setPrefHeight(300);

        imageBox.setStyle(
                "-fx-background-color:" + INNER + ";" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:5;" +
                "-fx-background-radius:5;"
        );

        // =====================================================
        // CLUB NAME
        // =====================================================

        Label clubName = new Label(
                getText(
                        club.getClubName(),
                        "Unnamed Club"
                )
        );

        clubName.setWrapText(true);

        clubName.setStyle(
                "-fx-text-fill:" + WHITE + ";" +
                "-fx-font-size:27px;" +
                "-fx-font-weight:bold;"
        );

        // =====================================================
        // LOCATION
        // =====================================================

        Label location = new Label(
                "📍 " +
                getText(
                        club.getClubAddress(),
                        "Location not available"
                )
        );

        location.setWrapText(true);

        location.setStyle(
                "-fx-text-fill:" + LIME + ";" +
                "-fx-font-size:13px;"
        );

        // =====================================================
        // CATEGORY
        // =====================================================

        Label category = new Label(
                "Category: " +
                getText(
                        club.getCategory(),
                        "Not available"
                )
        );

        category.setStyle(
                "-fx-text-fill:" + GRAY + ";" +
                "-fx-font-size:12px;"
        );

        // =====================================================
        // STATUS
        // =====================================================

        Label status = new Label(
                "Status: " +
                getText(
                        club.getStatus(),
                        "Not available"
                )
        );

        status.setStyle(
                "-fx-text-fill:" + LIME + ";" +
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;"
        );

        // =====================================================
        // HERO INFORMATION
        // =====================================================

        VBox heroInfo = new VBox(
                8,
                clubName,
                location,
                category,
                status
        );

        heroInfo.setPadding(new Insets(5));

        // =====================================================
        // HERO
        // =====================================================

        VBox hero = new VBox(
                15,
                imageBox,
                heroInfo
        );

        hero.setPadding(new Insets(15));

        hero.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:7;" +
                "-fx-background-radius:7;"
        );

        // =====================================================
        // ABOUT
        // =====================================================

        Label aboutTitle = new Label("About the Club");

        aboutTitle.setStyle(
                "-fx-text-fill:" + WHITE + ";" +
                "-fx-font-size:17px;" +
                "-fx-font-weight:bold;"
        );

        Label aboutText = new Label(
                getText(
                        club.getDescription(),
                        "No description available."
                )
        );

        aboutText.setWrapText(true);

        aboutText.setStyle(
                "-fx-text-fill:#DDDDDD;" +
                "-fx-font-size:12px;"
        );

        VBox aboutBox = new VBox(
                12,
                aboutTitle,
                aboutText
        );

        aboutBox.setPadding(new Insets(18));

        aboutBox.setPrefWidth(600);

        aboutBox.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-background-radius:5;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:5;"
        );

        // =====================================================
        // CONTACT
        // =====================================================

        Label contactTitle = new Label("CONTACT");

        contactTitle.setStyle(
                "-fx-text-fill:#888888;" +
                "-fx-font-size:10px;"
        );

        Label contact = new Label(
                getText(
                        club.getPhone(),
                        "Phone not available"
                )
        );

        contact.setStyle(
                "-fx-text-fill:" + LIME + ";" +
                "-fx-font-size:12px;"
        );

        VBox contactBox = new VBox(
                5,
                contactTitle,
                contact
        );

        // =====================================================
        // WEBSITE
        // =====================================================

        Label websiteTitle = new Label("WEBSITE");

        websiteTitle.setStyle(
                "-fx-text-fill:#888888;" +
                "-fx-font-size:10px;"
        );

        Label website = new Label(
                getText(
                        club.getWebsite(),
                        "Website not available"
                )
        );

        website.setWrapText(true);

        website.setStyle(
                "-fx-text-fill:" + LIME + ";" +
                "-fx-font-size:12px;"
        );

        VBox websiteBox = new VBox(
                5,
                websiteTitle,
                website
        );

        // =====================================================
        // CAPACITY
        // =====================================================

        Label capacityTitle = new Label("CAPACITY");

        capacityTitle.setStyle(
                "-fx-text-fill:#888888;" +
                "-fx-font-size:10px;"
        );

        String capacityText;

        if (club.getCapacity() > 0) {

            capacityText =
                    club.getCapacity() +
                    " members";

        } else {

            capacityText =
                    "Not available";
        }

        Label capacity = new Label(
                capacityText
        );

        capacity.setStyle(
                "-fx-text-fill:" + LIME + ";" +
                "-fx-font-size:12px;"
        );

        VBox capacityBox = new VBox(
                5,
                capacityTitle,
                capacity
        );

        // =====================================================
        // PRICE
        // =====================================================

        Label priceTitle = new Label("STARTING PRICE");

        priceTitle.setStyle(
                "-fx-text-fill:#888888;" +
                "-fx-font-size:10px;"
        );

        Label price = new Label(
                getPrice(
                        club.getStartingPrice()
                )
        );

        price.setStyle(
                "-fx-text-fill:" + LIME + ";" +
                "-fx-font-size:17px;" +
                "-fx-font-weight:bold;"
        );

        VBox priceBox = new VBox(
                5,
                priceTitle,
                price
        );

        // =====================================================
        // CLUB INFORMATION ROW
        // =====================================================

        HBox informationRow = new HBox(
                45,
                contactBox,
                websiteBox,
                capacityBox,
                priceBox
        );

        informationRow.setPadding(
                new Insets(5, 0, 0, 0)
        );

        VBox informationCard = new VBox(
                15,
                informationRow
        );

        informationCard.setPadding(
                new Insets(18)
        );

        informationCard.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-background-radius:5;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:5;"
        );

        // =====================================================
        // AMENITIES / FACILITIES
        // =====================================================

        Label facilitiesTitle = new Label(
                "Facilities & Amenities"
        );

        facilitiesTitle.setStyle(
                "-fx-text-fill:" + WHITE + ";" +
                "-fx-font-size:17px;" +
                "-fx-font-weight:bold;"
        );

        HBox facilities = createAmenities(
                club.getAmenities()
        );

        VBox facilitiesBox = new VBox(
                15,
                facilitiesTitle,
                facilities
        );

        facilitiesBox.setPadding(
                new Insets(18)
        );

        facilitiesBox.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-background-radius:5;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:5;"
        );

        // =====================================================
        // MEMBERSHIP
        // =====================================================

        Label membershipTitle = new Label(
                "Membership"
        );

        membershipTitle.setStyle(
                "-fx-text-fill:" + WHITE + ";" +
                "-fx-font-size:17px;" +
                "-fx-font-weight:bold;"
        );

        Label membershipName = new Label(
                "Club Membership"
        );

        membershipName.setStyle(
                "-fx-text-fill:" + WHITE + ";" +
                "-fx-font-size:14px;" +
                "-fx-font-weight:bold;"
        );

        Label membershipPrice = new Label(
                getPrice(
                        club.getStartingPrice()
                )
        );

        membershipPrice.setStyle(
                "-fx-text-fill:" + LIME + ";" +
                "-fx-font-size:20px;" +
                "-fx-font-weight:bold;"
        );

        Label membershipText = new Label(
                "Starting membership price. " +
                "Contact the club for complete plan details."
        );

        membershipText.setWrapText(true);

        membershipText.setStyle(
                "-fx-text-fill:#888888;" +
                "-fx-font-size:10px;"
        );

        Button enrollButton = new Button(
                "ENROLL NOW"
        );

        enrollButton.setPrefWidth(150);

        enrollButton.setPrefHeight(38);

        enrollButton.setStyle(
                "-fx-background-color:" + LIME + ";" +
                "-fx-text-fill:black;" +
                "-fx-font-size:11px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:4;"
        );

        enrollButton.setOnAction(event -> {

            System.out.println(
                    "Enroll clicked for club: " +
                    club.getClubId()
            );

            String currentUserId = com.flexforce.controller.AuthControllerlogin.getCurrentUserId();
            if (currentUserId == null || currentUserId.isEmpty()) {
                System.out.println("User is not logged in!");
                return;
            }

            // Determine amount (defaulting to 500 if parsing fails)
            String startingPriceStr = club.getStartingPrice();
            if (startingPriceStr == null || startingPriceStr.trim().isEmpty()) {
                startingPriceStr = "500";
            }
            
            com.flexforce.view.PaymentDialog.showPaymentDialog(
                currentUserId, 
                club.getClubId(), 
                club.getClubName(), 
                startingPriceStr
            );

        });

        VBox membershipCard = new VBox(
                10,
                membershipName,
                membershipPrice,
                membershipText,
                enrollButton
        );

        membershipCard.setPadding(
                new Insets(15)
        );

        membershipCard.setStyle(
                "-fx-background-color:#1D1D1D;" +
                "-fx-border-color:" + LIME + ";" +
                "-fx-border-radius:5;" +
                "-fx-background-radius:5;"
        );

        VBox membershipBox = new VBox(
                15,
                membershipTitle,
                membershipCard
        );

        membershipBox.setPadding(
                new Insets(18)
        );

        membershipBox.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-background-radius:5;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:5;"
        );

        // =====================================================
        // ABOUT + MEMBERSHIP
        // =====================================================

        HBox aboutAndMembership = new HBox(
                20,
                aboutBox,
                membershipBox
        );

        // =====================================================
        // FINAL CONTENT
        // =====================================================

        content.getChildren().addAll(
                hero,
                aboutAndMembership,
                informationCard,
                facilitiesBox
        );

        // =====================================================
        // SCROLL
        // =====================================================

        ScrollPane scrollPane =
                new ScrollPane(content);

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setPannable(true);

        scrollPane.setStyle(
                "-fx-background-color:" + BG + ";" +
                "-fx-border-color:transparent;"
        );

        root.setCenter(scrollPane);

        return root;
    }

    // =========================================================
    // CREATE CLUB IMAGE
    // =========================================================

    private static ImageView createClubImage(
            String imageUrl) {

        ImageView imageView =
                new ImageView();

        imageView.setFitWidth(850);

        imageView.setFitHeight(300);

        imageView.setPreserveRatio(false);

        if (imageUrl != null &&
                !imageUrl.trim().isEmpty()) {

            try {

                Image image =
                        new Image(
                                imageUrl,
                                true
                        );

                imageView.setImage(image);

            } catch (Exception e) {

                System.out.println(
                        "Unable to load club image."
                );
            }
        }

        return imageView;
    }

    // =========================================================
    // CREATE AMENITIES
    // =========================================================

    private static HBox createAmenities(
            java.util.List<String> amenities) {

        HBox row = new HBox(12);

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        if (amenities == null ||
                amenities.isEmpty()) {

            Label noAmenities =
                    new Label(
                            "No amenities available."
                    );

            noAmenities.setStyle(
                    "-fx-text-fill:" + GRAY + ";" +
                    "-fx-font-size:11px;"
            );

            row.getChildren().add(
                    noAmenities
            );

            return row;
        }

        for (String amenity : amenities) {

            if (amenity == null ||
                    amenity.trim().isEmpty()) {

                continue;
            }

            Label label =
                    new Label(
                            "• " + amenity
                    );

            label.setWrapText(true);

            label.setStyle(
                    "-fx-text-fill:" + WHITE + ";" +
                    "-fx-font-size:11px;"
            );

            VBox box =
                    new VBox(label);

            box.setAlignment(
                    Pos.CENTER
            );

            box.setPadding(
                    new Insets(12)
            );

            box.setStyle(
                    "-fx-background-color:" + INNER + ";" +
                    "-fx-background-radius:5;" +
                    "-fx-border-color:" + BORDER + ";" +
                    "-fx-border-radius:5;"
            );

            row.getChildren().add(box);
        }

        return row;
    }

    // =========================================================
    // GET TEXT
    // =========================================================

    private static String getText(
            String value,
            String defaultValue) {

        if (value == null ||
                value.trim().isEmpty()) {

            return defaultValue;
        }

        return value;
    }

    // =========================================================
    // GET PRICE
    // =========================================================

    private static String getPrice(
            String startingPrice) {

        if (startingPrice == null ||
                startingPrice.trim().isEmpty()) {

            return "Price not available";
        }

        return "Starting at ₹ " +
                startingPrice +
                "/mo";
    }
}