package com.flexforce.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ExplorerPage extends Application {

    // =====================================================
    // COLORS
    // =====================================================

    private final String BACKGROUND = "#0B0F14";
    private final String CARD = "#252E38";
    private final String BORDER = "#394552";
    private final String GREEN = "#20B86B";
    private final String WHITE = "#F5F7FA";
    private final String GRAY = "#B8C1CC";


    // =====================================================
    // CREATE PAGE
    // =====================================================

    public VBox createPage() {


        // =================================================
        // TITLE
        // =================================================

        Label title =
                new Label(
                        "Club Explorer"
                );


        title.setStyle(

                "-fx-font-size:30px;" +

                "-fx-font-weight:bold;" +

                "-fx-text-fill:" +
                WHITE + ";"
        );


        Label subtitle =
                new Label(
                        "Explore fitness clubs and find the right one for you."
                );


        subtitle.setStyle(

                "-fx-font-size:14px;" +

                "-fx-text-fill:" +
                GRAY + ";"
        );


        // =================================================
        // SEARCH
        // =================================================

        TextField searchBox =
                new TextField();


        searchBox.setPromptText(
                "Search clubs by name or location..."
        );


        searchBox.setPrefWidth(
                600
        );


        searchBox.setPrefHeight(
                40
        );


        searchBox.setStyle(

                "-fx-background-color:#18212B;" +

                "-fx-text-fill:white;" +

                "-fx-prompt-text-fill:#7F8A97;" +

                "-fx-background-radius:8px;" +

                "-fx-border-color:" +
                BORDER + ";" +

                "-fx-border-radius:8px;" +

                "-fx-padding:0 12 0 12;"
        );


        Button filterButton =
                new Button(
                        "Filter"
                );


        filterButton.setPrefHeight(
                40
        );


        filterButton.setStyle(

                "-fx-background-color:" +
                GREEN + ";" +

                "-fx-text-fill:white;" +

                "-fx-font-weight:bold;" +

                "-fx-background-radius:8px;" +

                "-fx-cursor:hand;"
        );


        HBox searchRow =
                new HBox(
                        10,
                        searchBox,
                        filterButton
                );


        searchRow.setAlignment(
                Pos.CENTER_LEFT
        );


        // =================================================
        // CLUB COUNT
        // =================================================

        Label clubCount =
                new Label(
                        "All Clubs (24 clubs available)"
                );


        clubCount.setStyle(

                "-fx-font-size:14px;" +

                "-fx-font-weight:bold;" +

                "-fx-text-fill:" +
                WHITE + ";"
        );


        // =================================================
        // SORT
        // =================================================

        Label sortLabel =
                new Label(
                        "SORT BY:"
                );


        sortLabel.setStyle(

                "-fx-font-size:12px;" +

                "-fx-font-weight:bold;" +

                "-fx-text-fill:" +
                GRAY + ";"
        );


        Button sortButton =
                new Button(
                        "Recommended"
                );


        sortButton.setStyle(

                "-fx-background-color:#18212B;" +

                "-fx-text-fill:white;" +

                "-fx-background-radius:8px;" +

                "-fx-border-color:" +
                BORDER + ";" +

                "-fx-border-radius:8px;" +

                "-fx-cursor:hand;"
        );


        HBox sortRow =
                new HBox(
                        10,
                        sortLabel,
                        sortButton
                );


        sortRow.setAlignment(
                Pos.CENTER_LEFT
        );


        // =================================================
        // CLUB 1
        // =================================================

        VBox club1 =
                createClubCard(

                        "/assets/images/download (1).jpg",

                        "FitZone Fitness Club",

                        "📍 Pune",

                        "State-of-the-art equipment and personal training.",

                        "Gym • Cardio • Strength",

                        "Starting at ₹1,499/mo"
                );


        // =================================================
        // CLUB 2
        // =================================================

        VBox club2 =
                createClubCard(

                        "/assets/images/gym.jpg",

                        "PowerHouse Club",

                        "📍 Pune",

                        "High-intensity workouts and community vibes.",

                        "Gym • CrossFit • Zumba",

                        "Starting at ₹1,299/mo"
                );


        // =================================================
        // CLUB 3
        // =================================================

        VBox club3 =
                createClubCard(

                        "/assets/images/meditation.jpg",

                        "Flex & Flow Studio",

                        "📍 Pune",

                        "Holistic wellness and mindfulness sessions.",

                        "Yoga • Pilates • Meditation",

                        "Starting at ₹999/mo"
                );


        // =================================================
        // CLUB ROW
        // =================================================

        HBox clubRow =
                new HBox(
                        20,
                        club1,
                        club2,
                        club3
                );


        clubRow.setAlignment(
                Pos.TOP_LEFT
        );


        // =================================================
        // MAIN PAGE
        // =================================================

        VBox main =
                new VBox(
                        18,
                        title,
                        subtitle,
                        searchRow,
                        clubCount,
                        sortRow,
                        clubRow
                );


        main.setPadding(
                new Insets(30)
        );


        main.setStyle(

                "-fx-background-color:" +
                BACKGROUND + ";"
        );


        return main;
    }


    // =====================================================
    // CLUB CARD
    // =====================================================

    private VBox createClubCard(

            String imagePath,

            String clubName,

            String location,

            String description,

            String activities,

            String price) {


        // =================================================
        // IMAGE
        // =================================================

        ImageView image =
                createImage(
                        imagePath,
                        285,
                        140
                );


        // =================================================
        // NAME
        // =================================================

        Label name =
                new Label(
                        clubName
                );


        name.setStyle(

                "-fx-font-size:18px;" +

                "-fx-font-weight:bold;" +

                "-fx-text-fill:white;"
        );


        // =================================================
        // LOCATION
        // =================================================

        Label loc =
                new Label(
                        location
                );


        loc.setStyle(

                "-fx-font-size:12px;" +

                "-fx-text-fill:" +
                GRAY + ";"
        );


        // =================================================
        // DESCRIPTION
        // =================================================

        Label desc =
                new Label(
                        description
                );


        desc.setWrapText(
                true
        );


        desc.setStyle(

                "-fx-font-size:12px;" +

                "-fx-text-fill:" +
                GRAY + ";"
        );


        // =================================================
        // ACTIVITIES
        // =================================================

        Label act =
                new Label(
                        activities
                );


        act.setStyle(

                "-fx-font-size:12px;" +

                "-fx-text-fill:" +
                GRAY + ";"
        );


        // =================================================
        // PRICE
        // =================================================

        Label priceLabel =
                new Label(
                        price
                );


        priceLabel.setStyle(

                "-fx-font-size:13px;" +

                "-fx-font-weight:bold;" +

                "-fx-text-fill:" +
                GREEN + ";"
        );


        // =================================================
        // VIEW CLUB
        // =================================================

        Button view =
                new Button(
                        "View Club"
                );


        view.setMaxWidth(
                Double.MAX_VALUE
        );


        view.setPrefHeight(
                38
        );


        view.setStyle(

                "-fx-background-color:" +
                GREEN + ";" +

                "-fx-text-fill:white;" +

                "-fx-font-weight:bold;" +

                "-fx-background-radius:8px;" +

                "-fx-cursor:hand;"
        );


        view.setOnAction(e -> {

            System.out.println(
                    clubName +
                    " View Club clicked"
            );

        });


        // =================================================
        // CARD
        // =================================================

        VBox card =
                new VBox(
                        10
                );


        if (image != null) {

            card.getChildren().add(
                    image
            );
        }


        card.getChildren().addAll(

                name,

                loc,

                desc,

                act,

                priceLabel,

                view
        );


        card.setPadding(
                new Insets(15)
        );


        card.setPrefWidth(
                315
        );


        card.setMinHeight(
                380
        );


        card.setStyle(

                "-fx-background-color:" +
                CARD + ";" +

                "-fx-background-radius:15px;" +

                "-fx-border-color:" +
                BORDER + ";" +

                "-fx-border-radius:15px;"
        );


        return card;
    }


    // =====================================================
    // IMAGE METHOD
    // =====================================================

    private ImageView createImage(

            String imagePath,

            double width,

            double height) {


        try {


            java.net.URL imageURL =
                    getClass().getResource(
                            imagePath
                    );


            if (imageURL == null) {

                System.out.println(
                        "IMAGE NOT FOUND: " +
                        imagePath
                );

                return null;
            }


            Image image =
                    new Image(
                            imageURL.toExternalForm()
                    );


            ImageView imageView =
                    new ImageView(
                            image
                    );


            imageView.setFitWidth(
                    width
            );


            imageView.setFitHeight(
                    height
            );


            imageView.setPreserveRatio(
                    false
            );


            imageView.setSmooth(
                    true
            );


            return imageView;


        } catch (Exception e) {

            System.out.println(
                    "IMAGE ERROR: " +
                    imagePath
            );

            e.printStackTrace();

            return null;
        }
    }


    @Override
    public void start(Stage arg0) throws Exception {

    }
}
    
    

