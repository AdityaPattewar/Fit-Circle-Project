package com.flexforce.view;
import java.io.File;
import com.flexforce.view.User.Userclubdet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.flexforce.controller.ExplorerClubController;
import com.flexforce.model.common_for_user_clubowner.UserClub;
import com.flexforce.view.User.Userclubdet;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ExplorerClubPage extends Application {
        private ExplorerClubController controller = new ExplorerClubController();
        // =====================================================
        // COLORS
        // =====================================================

        String bg = "#0B0F12";
        String card = "#191C1A";
        String inner = "#222622";
        String border = "#292C2A";
        String lime = "#B6FF00";
        String white = "#FFFFFF";
        String gray = "#AEB5BC";

        // =====================================================
        // CONTROLLER
        // =====================================================

        private ExplorerClubController clubController;

        // =====================================================
        // DYNAMIC DATA
        // =====================================================

        private List<UserClub> allClubs = new ArrayList<>();

        // =====================================================
        // CALLBACK
        // =====================================================

        private java.util.function.Consumer<UserClub> onClubSelected;

        public void setOnClubSelected(java.util.function.Consumer<UserClub> onClubSelected) {
            this.onClubSelected = onClubSelected;
        }

        // =====================================================
        // UI COMPONENTS
        // =====================================================

        private TextField searchBox;

        private Label clubCount;

        private FlowPane clubRow;

        private HBox categoryRow;

        private VBox recommendations;

        // =====================================================
        // FILTER STATE
        // =====================================================

        private boolean activeFilter = false;

        // =====================================================
        // START
        // =====================================================

        @Override
        public void start(Stage stage) {

                clubController = new ExplorerClubController();

                VBox content = createContent();

                content.setMinHeight(1000);

                ScrollPane scrollPane = new ScrollPane(content);

                scrollPane.setFitToWidth(true);

                scrollPane.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                scrollPane.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                scrollPane.setPannable(true);

                scrollPane.setStyle(
                                "-fx-background-color:" + bg + ";" +
                                                "-fx-border-color:transparent;");

                Scene scene = new Scene(
                                scrollPane,
                                1050,
                                600);

                stage.setTitle(
                                "FitCircle - Club Explorer");

                stage.setScene(scene);

                stage.show();
        }

        // =====================================================
        // SCROLLABLE CONTENT
        // =====================================================

        public ScrollPane createScrollableContent() {

                if (clubController == null) {

                        clubController = new ExplorerClubController();
                }

                VBox content = createContent();

                ScrollPane scrollPane = new ScrollPane(content);

                scrollPane.setFitToWidth(true);

                scrollPane.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                scrollPane.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                scrollPane.setPannable(true);

                scrollPane.setStyle(
                                "-fx-background-color:" + bg + ";" +
                                                "-fx-border-color:transparent;");

                return scrollPane;
        }

        // =====================================================
        // CREATE MAIN CONTENT
        // =====================================================

        public VBox createContent() {

                if (clubController == null) {

                        clubController = new ExplorerClubController();
                }

                // =================================================
                // TITLE
                // =================================================

                Label title = new Label("Club Explorer");

                title.setStyle(
                                "-fx-font-size:30px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-text-fill:" + white + ";");

                // =================================================
                // SUBTITLE
                // =================================================

                Label subtitle = new Label(
                                "Explore fitness clubs and find the right one for you.");

                subtitle.setStyle(
                                "-fx-font-size:14px;" +
                                                "-fx-text-fill:" + white + ";");

                // =================================================
                // SEARCH BOX
                // =================================================

                searchBox = new TextField();

                searchBox.setPromptText(
                                "Search clubs by name or location...");

                searchBox.setPrefHeight(40);

                searchBox.setStyle(
                                "-fx-background-color:" + card + ";" +
                                                "-fx-text-fill:" + white + ";" +
                                                "-fx-prompt-text-fill:#777777;" +
                                                "-fx-border-color:" + border + ";" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;");

                // =================================================
                // FILTER BUTTON
                // =================================================

                Button filterButton = new Button("Filter");

                filterButton.setPrefHeight(40);

                filterButton.setPrefWidth(90);

                filterButton.setStyle(
                                "-fx-background-color:" + lime + ";" +
                                                "-fx-text-fill:black;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-background-radius:5;");

                // =================================================
                // SEARCH ROW
                // =================================================

                HBox searchRow = new HBox(
                                10,
                                searchBox,
                                filterButton);

                searchRow.setAlignment(
                                Pos.CENTER_LEFT);

                HBox.setHgrow(
                                searchBox,
                                javafx.scene.layout.Priority.ALWAYS);

                // =================================================
                // CLUB COUNT
                // =================================================

                clubCount = new Label(
                                "All Clubs (0 clubs available)");

                clubCount.setStyle(
                                "-fx-font-size:14px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-text-fill:" + white + ";");

                // =================================================
                // SORT LABEL
                // =================================================

                Label sortLabel = new Label("SORT BY:");

                sortLabel.setStyle(
                                "-fx-font-size:12px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-text-fill:" + gray + ";");

                // =================================================
                // SORT BUTTON
                // =================================================

                Button sortButton = new Button("Recommended");

                sortButton.setStyle(
                                "-fx-background-color:" + card + ";" +
                                                "-fx-text-fill:" + white + ";" +
                                                "-fx-border-color:" + border + ";" +
                                                "-fx-border-radius:5;" +
                                                "-fx-background-radius:5;");

                // =================================================
                // SORT ROW
                // =================================================

                HBox sortRow = new HBox(
                                10,
                                sortLabel,
                                sortButton);

                sortRow.setAlignment(
                                Pos.CENTER_LEFT);

                // =================================================
                // CLUB ROW
                // =================================================

               clubRow = new FlowPane();

                clubRow.setHgap(18);

                clubRow.setVgap(18);

                clubRow.setAlignment(
                        Pos.TOP_LEFT);
                // =================================================
                // CATEGORY TITLE
                // =================================================

                Label popularTitle = new Label(
                                "Popular Fitness Categories");

                popularTitle.setStyle(
                                "-fx-font-size:20px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-text-fill:" + white + ";");

                // =================================================
                // CATEGORY ROW
                // =================================================

                categoryRow = new HBox(15);

                categoryRow.setAlignment(
                                Pos.TOP_LEFT);

                // =================================================
                // RECOMMENDATION TITLE
                // =================================================

                Label nearbyTitle = new Label(
                                "Recommended Near You");

                nearbyTitle.setStyle(
                                "-fx-font-size:20px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-text-fill:" + white + ";");

                // =================================================
                // RECOMMENDATIONS
                // =================================================

                recommendations = new VBox(12);

                // =================================================
                // BOTTOM TEXT
                // =================================================

                Label bottomText = new Label(
                                "Find your community. Stay consistent. Become stronger.");

                bottomText.setStyle(
                                "-fx-font-size:14px;" +
                                                "-fx-text-fill:" + gray + ";");

                // =================================================
                // LOAD FIREBASE DATA
                // =================================================

                loadClubs();

                // =================================================
                // SEARCH DYNAMICALLY
                // =================================================

                searchBox.textProperty().addListener(
                                (observable, oldValue, newValue) -> {

                                        if (activeFilter) {

                                                updateActiveSearchResults(
                                                                newValue);

                                        } else {

                                                updateClubCards(
                                                                newValue);
                                        }
                                });

                // =================================================
                // FILTER BUTTON
                // =================================================

                filterButton.setOnAction(event -> {

                        activeFilter = !activeFilter;

                        if (activeFilter) {

                                filterButton.setText(
                                                "Active");

                                updateActiveSearchResults(
                                                searchBox.getText());

                        } else {

                                filterButton.setText(
                                                "Filter");

                                updateClubCards(
                                                searchBox.getText());
                        }
                });

                // =================================================
                // SORT BUTTON
                // =================================================

                sortButton.setOnAction(event -> {

                        List<UserClub> sortedClubs = new ArrayList<>(
                                        allClubs);

                        sortedClubs.sort(
                                        Comparator.comparing(
                                                        club -> {

                                                                if (club == null ||
                                                                                club.getClubName() == null) {

                                                                        return "";
                                                                }

                                                                return club
                                                                                .getClubName()
                                                                                .toLowerCase();
                                                        }));

                        allClubs = sortedClubs;

                        updateClubCards(
                                        searchBox.getText());

                        sortButton.setText(
                                        "Name A-Z");
                });

                // =================================================
                // MAIN BOX
                // =================================================

                VBox mainBox = new VBox(
                                22,
                                title,
                                subtitle,
                                searchRow,
                                clubCount,
                                sortRow,
                                clubRow,
                                popularTitle,
                                categoryRow,
                                nearbyTitle,
                                recommendations,
                                bottomText);

                mainBox.setPadding(
                                new Insets(30));

                mainBox.setFillWidth(true);

                mainBox.setStyle(
                                "-fx-background-color:" + bg + ";");

                return mainBox;
        }

        // =====================================================
        // LOAD CLUBS FROM FIREBASE
        // =====================================================

        private void loadClubs() {

                try {

                        allClubs = clubController.getAllClubs();

                        if (allClubs == null) {

                                allClubs = new ArrayList<>();
                        }

                        System.out.println(
                                        "Total clubs loaded: " +
                                                        allClubs.size());

                        // Dynamic club cards
                        updateClubCards("");

                        // Dynamic categories
                        updateCategories();

                        // Dynamic recommendations
                        updateRecommendations();

                } catch (Exception e) {

                        e.printStackTrace();

                        allClubs = new ArrayList<>();

                        updateClubCards("");

                        updateCategories();

                        updateRecommendations();
                }
        }

        // =====================================================
        // UPDATE CLUB CARDS
        // =====================================================

        private void updateClubCards(
                        String searchText) {

                List<UserClub> clubs;

                try {

                        clubs = clubController.searchClubs(
                                        searchText);

                } catch (Exception e) {

                        e.printStackTrace();

                        clubs = new ArrayList<>();
                }

                updateClubCardsFromList(
                                clubs,
                                false);
        }

        // =====================================================
        // UPDATE ACTIVE + SEARCH RESULTS
        // =====================================================

        private void updateActiveSearchResults(
                        String searchText) {

                List<UserClub> searchedClubs;

                try {

                        searchedClubs = clubController.searchClubs(
                                        searchText);

                } catch (Exception e) {

                        e.printStackTrace();

                        searchedClubs = new ArrayList<>();
                }

                List<UserClub> activeClubs = new ArrayList<>();

                if (searchedClubs != null) {

                        for (UserClub club : searchedClubs) {

                                if (club == null) {
                                        continue;
                                }

                                if ("ACTIVE".equalsIgnoreCase(
                                                club.getStatus())) {

                                        activeClubs.add(club);
                                }
                        }
                }

                updateClubCardsFromList(
                                activeClubs,
                                true);
        }

        // =====================================================
        // UPDATE CLUB CARDS FROM LIST
        // =====================================================

        private void updateClubCardsFromList(
                        List<UserClub> clubs,
                        boolean activeMode) {

                clubRow.getChildren().clear();

                if (clubs == null ||
                                clubs.isEmpty()) {

                        Label noClubs = new Label(
                                        activeMode
                                                        ? "No active clubs found."
                                                        : "No clubs found.");

                        noClubs.setStyle(
                                        "-fx-font-size:15px;" +
                                                        "-fx-text-fill:" + gray + ";");

                        clubRow.getChildren().add(
                                        noClubs);

                        if (activeMode) {

                                clubCount.setText(
                                                "Active Clubs (0)");

                        } else {

                                clubCount.setText(
                                                "All Clubs (0 clubs available)");
                        }

                        return;
                }

                // =================================================
                // CREATE DYNAMIC CARDS
                // =================================================

                for (UserClub club : clubs) {

                        if (club == null) {
                                continue;
                        }

                        VBox clubCard = createClub(club);

                        clubRow.getChildren().add(
                                        clubCard);
                }

                // =================================================
                // DYNAMIC COUNT
                // =================================================

                if (activeMode) {

                        clubCount.setText(
                                        "Active Clubs (" +
                                                        clubs.size() +
                                                        ")");

                } else {

                        clubCount.setText(
                                        "All Clubs (" +
                                                        clubs.size() +
                                                        " clubs available)");
                }
        }

        // =====================================================
        // CREATE DYNAMIC CLUB CARD
        // =====================================================

        public VBox createClub(
                        UserClub club) {

                // =================================================
                // IMAGE
                // =================================================

                Image image = loadClubImage(
                                club.getImage(), club.getClubName());

                ImageView imageView = new ImageView();

                if (image != null) {

                        imageView.setImage(image);
                }

                imageView.setFitWidth(300);

                imageView.setFitHeight(150);

                imageView.setPreserveRatio(false);

                // =================================================
                // CLUB NAME
                // =================================================

                String name = safeString(
                                club.getClubName(),
                                "Unnamed Club");

                Label clubName = new Label(name);

                clubName.setStyle(
                                "-fx-font-size:18px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-text-fill:" + white + ";");

                // =================================================
                // LOCATION
                // =================================================

                String location = safeString(
                                club.getClubAddress(),
                                "Location not available");

                Label clubLocation = new Label(
                                "⌖ " + location);

                clubLocation.setWrapText(true);

                clubLocation.setStyle(
                                "-fx-text-fill:" + gray + ";" +
                                                "-fx-font-size:13px;");

                // =================================================
                // DESCRIPTION
                // =================================================

                String description = safeString(
                                club.getDescription(),
                                "No description available.");

                Label descriptionLabel = new Label(description);

                descriptionLabel.setWrapText(true);

                descriptionLabel.setStyle(
                                "-fx-text-fill:" + gray + ";" +
                                                "-fx-font-size:13px;");

                // =================================================
                // CATEGORY + AMENITIES
                // =================================================

                String activities = buildAmenitiesText(
                                club);

                Label activitiesLabel = new Label(activities);

                activitiesLabel.setWrapText(true);

                activitiesLabel.setStyle(
                                "-fx-text-fill:" + white + ";" +
                                                "-fx-font-size:13px;");

                // =================================================
                // PRICE
                // =================================================

                String startingPrice = safeString(
                                club.getStartingPrice(),
                                "");

                String priceText;

                if (startingPrice.isEmpty()) {

                        priceText = "Price not available";

                } else {

                        priceText = "Starting at ₹" +
                                        startingPrice +
                                        "/mo";
                }

                Label priceLabel = new Label(priceText);

                priceLabel.setStyle(
                                "-fx-text-fill:" + lime + ";" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-font-size:13px;");

                // =================================================
                // VIEW CLUB BUTTON
                // =================================================

                Button viewButton = new Button("VIEW CLUB");

                viewButton.setMaxWidth(
                                Double.MAX_VALUE);

                viewButton.setPrefHeight(38);

                viewButton.setStyle(
                                "-fx-background-color:" + lime + ";" +
                                                "-fx-text-fill:black;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-background-radius:4;");

                // =================================================
                // VIEW CLUB ACTION
                // =================================================

               /*  viewButton.setOnAction(event -> {

                        Stage stage =
                                (Stage) viewButton.getScene().getWindow();

                        Scene previousScene =
                                viewButton.getScene();
                        double width = stage.getWidth();
                        double height = stage.getHeight();
                        double x = stage.getX();
                        double y = stage.getY();

                        boolean maximized = stage.isMaximized();

                        stage.setScene(
                                Userclubdet.createClubDetailsScene(
                                        club,
                                        stage,
                                        previousScene
                                )
                        );
                        if (!maximized) {
                        stage.setWidth(width);
                        stage.setHeight(height);
                        stage.setX(x);
                        stage.setY(y);
                        } else {
                        stage.setMaximized(true);
                        }
                });*/
                  viewButton.setOnAction(event -> {

                        System.out.println(
                                        "Selected Club: " +
                                                        club.getClubName());

                        if (onClubSelected != null) {
                                onClubSelected.accept(club);
                        } else {
                                com.flexforce.view.User.Userclubdet.openClubDetails(club);
                        }
                });

                // =================================================
                // SPACER TO PUSH BUTTON TO BOTTOM
                // =================================================
                
                javafx.scene.layout.Region spacer = new javafx.scene.layout.Region();
                javafx.scene.layout.VBox.setVgrow(spacer, javafx.scene.layout.Priority.ALWAYS);

                // =================================================
                // CLUB CARD
                // =================================================

                VBox club1 = new VBox(
                                10,
                                imageView,
                                clubName,
                                clubLocation,
                                descriptionLabel,
                                activitiesLabel,
                                spacer,
                                priceLabel,
                                viewButton);

                club1.setPadding(
                                new Insets(14));

                club1.setPrefWidth(330);

                club1.setMinWidth(330);
                
                club1.setMinHeight(420); // Give it a minimum height
                
                // We remove the fixed setPrefHeight(400) so it can expand if text is long

                club1.setStyle(
                                "-fx-background-color:" + card + ";" +
                                                "-fx-border-color:" + border + ";" +
                                                "-fx-border-radius:7;" +
                                                "-fx-background-radius:7;");

                return club1;
        }

        // =====================================================
        // LOAD CLUB IMAGE
        // =====================================================

        private Image loadClubImage(
                        String imagePath, String clubName) {

                try {

                        // No image stored in Firebase
                        if (imagePath == null ||
                                        imagePath.trim().isEmpty()) {

                                return loadDefaultImage(clubName);
                        }

                        String path = imagePath.trim();

                        // =================================================
                        // CLOUDINARY / INTERNET URL
                        // =================================================

                        if (path.startsWith("http://") ||
                                        path.startsWith("https://")) {

                                return new Image(
                                                path,
                                                300,
                                                150,
                                                false,
                                                true);
                        }

                        // =================================================
                        // LOCAL FILE
                        // =================================================

                        File file = new File(path);

                        if (file.exists()) {

                                return new Image(
                                                file.toURI().toString(),
                                                300,
                                                150,
                                                false,
                                                true);
                        }

                        // =================================================
                        // JAVA RESOURCE
                        // =================================================

                        String resourcePath = path.replace(
                                        "\\",
                                        "/");

                        var resource = getClass()
                                        .getClassLoader()
                                        .getResource(
                                                        resourcePath);

                        if (resource != null) {

                                return new Image(
                                                resource.toExternalForm(),
                                                300,
                                                150,
                                                false,
                                                true);
                        }

                } catch (Exception e) {

                        System.out.println(
                                        "Unable to load club image: " +
                                                        imagePath);
                }

                return loadDefaultImage(clubName);
        }

        // =====================================================
        // DEFAULT IMAGE
        // =====================================================

        private Image loadDefaultImage(String clubName) {

                try {

                        String[] possibleImages = {

                                        "assets/images/gym.jpg",

                                        "assets/images/download (1).jpg",

                                        "assets/images/meditation.jpg"
                        };
                        
                        int index = 0;
                        if (clubName != null && !clubName.isEmpty()) {
                                index = Math.abs(clubName.hashCode()) % possibleImages.length;
                        }
                        
                        String imagePath = possibleImages[index];

                        var resource = getClass()
                                        .getClassLoader()
                                        .getResource(
                                                        imagePath);

                        if (resource != null) {

                                return new Image(
                                                resource.toExternalForm(),
                                                300,
                                                150,
                                                false,
                                                true);
                        }
                        
                        // Fallback loop if the hashed image doesn't exist
                        for (String fallbackPath : possibleImages) {

                                var fallbackResource = getClass()
                                                .getClassLoader()
                                                .getResource(
                                                                fallbackPath);

                                if (fallbackResource != null) {

                                        return new Image(
                                                        fallbackResource.toExternalForm(),
                                                        300,
                                                        150,
                                                        false,
                                                        true);
                                }
                        }

                } catch (Exception e) {

                        e.printStackTrace();
                }

                return null;
        }

        // =====================================================
        // BUILD CATEGORY + AMENITIES
        // =====================================================

        private String buildAmenitiesText(
                        UserClub club) {

                String category = safeString(
                                club.getCategory(),
                                "");

                List<String> amenities = club.getAmenities();

                StringBuilder text = new StringBuilder();

                // =================================================
                // CATEGORY
                // =================================================

                if (!category.isEmpty()) {

                        text.append(
                                        category);
                }

                // =================================================
                // AMENITIES
                // =================================================

                if (amenities != null &&
                                !amenities.isEmpty()) {

                        for (String amenity : amenities) {

                                if (amenity == null ||
                                                amenity.trim().isEmpty()) {

                                        continue;
                                }

                                if (text.length() > 0) {

                                        text.append(
                                                        "  •  ");
                                }

                                text.append(
                                                amenity.trim());
                        }
                }

                // =================================================
                // DEFAULT
                // =================================================

                if (text.length() == 0) {

                        return "Fitness & Wellness";
                }

                return text.toString();
        }

        // =====================================================
        // UPDATE CATEGORIES
        // =====================================================

        private void updateCategories() {

                categoryRow.getChildren().clear();

                Map<String, Integer> categories = new LinkedHashMap<>();

                // =================================================
                // COUNT EACH CATEGORY
                // =================================================

                for (UserClub club : allClubs) {

                        if (club == null) {
                                continue;
                        }

                        String category = club.getCategory();

                        if (category == null ||
                                        category.trim().isEmpty()) {

                                continue;
                        }

                        category = category.trim();

                        categories.put(
                                        category,
                                        categories.getOrDefault(
                                                        category,
                                                        0) + 1);
                }

                // =================================================
                // NO CATEGORY
                // =================================================

                if (categories.isEmpty()) {

                        Label noCategory = new Label(
                                        "No categories available");

                        noCategory.setStyle(
                                        "-fx-font-size:13px;" +
                                                        "-fx-text-fill:" + gray + ";");

                        categoryRow.getChildren().add(
                                        noCategory);

                        return;
                }

                // =================================================
                // CREATE CATEGORY CARDS
                // =================================================

                for (Map.Entry<String, Integer> entry : categories.entrySet()) {

                        String category = entry.getKey();

                        Integer count = entry.getValue();

                        categoryRow.getChildren().add(
                                        createCategory(
                                                        category,
                                                        count + " Clubs"));
                }
        }

        // =====================================================
        // CATEGORY CARD
        // =====================================================

        public VBox createCategory(
                        String name,
                        String count) {

                Label categoryName = new Label(name);

                categoryName.setWrapText(true);

                categoryName.setStyle(
                                "-fx-font-size:15px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-text-fill:" + lime + ";");

                Label categoryCount = new Label(count);

                categoryCount.setStyle(
                                "-fx-font-size:12px;" +
                                                "-fx-text-fill:" + gray + ";");

                VBox box = new VBox(
                                8,
                                categoryName,
                                categoryCount);

                box.setPadding(
                                new Insets(18));

                box.setPrefWidth(150);

                box.setStyle(
                                "-fx-background-color:" + card + ";" +
                                                "-fx-border-color:" + border + ";" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;");

                return box;
        }

        // =====================================================
        // UPDATE RECOMMENDATIONS
        // =====================================================

        private void updateRecommendations() {

                recommendations.getChildren().clear();

                if (allClubs == null ||
                                allClubs.isEmpty()) {

                        Label noClubs = new Label(
                                        "No clubs available.");

                        noClubs.setStyle(
                                        "-fx-font-size:13px;" +
                                                        "-fx-text-fill:" + gray + ";");

                        recommendations.getChildren().add(
                                        noClubs);

                        return;
                }

                // =================================================
                // COPY CLUB LIST
                // =================================================

                List<UserClub> recommendedClubs = new ArrayList<>(
                                allClubs);

                // =================================================
                // ACTIVE CLUBS FIRST
                // =================================================

                recommendedClubs.sort(
                                (club1, club2) -> {

                                        boolean active1 = club1 != null &&
                                                        "ACTIVE".equalsIgnoreCase(
                                                                        club1.getStatus());

                                        boolean active2 = club2 != null &&
                                                        "ACTIVE".equalsIgnoreCase(
                                                                        club2.getStatus());

                                        if (active1 && !active2) {
                                                return -1;
                                        }

                                        if (!active1 && active2) {
                                                return 1;
                                        }

                                        return 0;
                                });

                // =================================================
                // MAXIMUM 5
                // =================================================

                int maximum = Math.min(
                                5,
                                recommendedClubs.size());

                // =================================================
                // CREATE RECOMMENDATIONS
                // =================================================

                for (int i = 0; i < maximum; i++) {

                        UserClub club = recommendedClubs.get(i);

                        if (club == null) {
                                continue;
                        }

                        String name = safeString(
                                        club.getClubName(),
                                        "Unnamed Club");

                        String location = safeString(
                                        club.getClubAddress(),
                                        "Location unavailable");

                        String category = safeString(
                                        club.getCategory(),
                                        "Fitness");

                        String price = safeString(
                                        club.getStartingPrice(),
                                        "");

                        if (!price.isEmpty()) {

                                price = "Starting at ₹" +
                                                price +
                                                "/mo";

                        } else {

                                price = "Price unavailable";
                        }

                        VBox recommendation = createRecommendation(
                                        name,
                                        location +
                                                        " • " +
                                                        category,
                                        "",
                                        price);

                        recommendations.getChildren().add(
                                        recommendation);
                }
        }

        // =====================================================
        // CREATE RECOMMENDATION
        // =====================================================

        public VBox createRecommendation(
                        String name,
                        String details,
                        String rating,
                        String price) {

                Label nameLabel = new Label(name);

                nameLabel.setStyle(
                                "-fx-font-size:16px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-text-fill:" + white + ";");

                Label detailsLabel = new Label(details);

                detailsLabel.setWrapText(true);

                detailsLabel.setStyle(
                                "-fx-font-size:13px;" +
                                                "-fx-text-fill:" + gray + ";");

                Label ratingLabel = new Label(rating);

                ratingLabel.setStyle(
                                "-fx-text-fill:" + lime + ";" +
                                                "-fx-font-weight:bold;");

                Label priceLabel = new Label(price);

                priceLabel.setStyle(
                                "-fx-text-fill:" + gray + ";");

                Button viewButton = new Button("VIEW");

                viewButton.setPrefWidth(80);

                viewButton.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-text-fill:" + lime + ";" +
                                                "-fx-border-color:" + lime + ";" +
                                                "-fx-border-radius:4;");

                VBox information = new VBox(
                                5,
                                nameLabel,
                                detailsLabel,
                                ratingLabel,
                                priceLabel);

                HBox row = new HBox(
                                15,
                                information,
                                viewButton);

                row.setAlignment(
                                Pos.CENTER_LEFT);

                HBox.setHgrow(
                                information,
                                javafx.scene.layout.Priority.ALWAYS);

                VBox box = new VBox(row);

                box.setPadding(
                                new Insets(15));

                box.setStyle(
                                "-fx-background-color:" + card + ";" +
                                                "-fx-border-color:" + border + ";" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;");

                return box;
        }

        // =====================================================
        // SAFE STRING
        // =====================================================

        private String safeString(
                        String value,
                        String defaultValue) {

                if (value == null ||
                                value.trim().isEmpty()) {

                        return defaultValue;
                }

                return value.trim();
        }

        // =====================================================
        // MAIN
        // =====================================================

        public static void main(String[] args) {

                launch(args);
        }
}