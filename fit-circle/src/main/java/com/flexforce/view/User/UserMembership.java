package com.flexforce.view.User;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
//import javafx.scene.layout.Region;
//import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
//import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class UserMembership extends Application {


    private final String GREEN = "#fcfcfc";
    //private final String DARK = "#050505";
    private final String CARD = "#530956";
    private final String CARD2 = "#530956";

    @Override
    public void start(Stage stage) throws Exception {

        BorderPane root = new BorderPane();

         root.setStyle(
                "-fx-background-color: #0000000 ;" );

                 // LEFT NAVIGATION BAR
      //  root.setLeft(createNavigationBar());

        // MAIN CONTENT
       root.setCenter(createMainContent());
        
    // ==============================================================================
       // Scrollepane content
    //==============================================================================
        ScrollPane scrollPane = new ScrollPane(createMainContent());

        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(false);

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
         scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        // scrollPane.setStyle(
       // "-fx-background: #171616;" +
        //"-fx-background-color: #f8f5f5;" );

        root.setCenter(scrollPane);
    
        Scene scene = new Scene(root,1200,750);
        stage.setTitle("My Membership - FitCircle");
        stage.setScene(scene);
        stage.show();
        
    }


       
    

        

   

        // =====================================================
    // MAIN CONTENT
    // =====================================================

    private VBox createMainContent() {

      

        VBox main = new VBox(20);

        main.setPadding(
                new Insets(25, 30, 25, 30)
        );

        // HEADER
        VBox header = new VBox(3);

        Label title =
                new Label("My Membership");

        title.setTextFill(
                Color.PURPLE
        );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        24
                )
        );

        Label subtitle = new Label("View current membership and its details" );

        subtitle.setTextFill(
                Color.web("#2b2424")
        );

        subtitle.setFont(
                Font.font("Arial", 15)
        );

        header.getChildren().addAll(
                title,
                subtitle
        );

        // MEMBERSHIP CARD
        HBox membershipCard =
                createMembershipCard();

       //====================================================================================
        // LOWER CONTENT
        //==================================================================================
      
        HBox lower = new HBox(20);

        VBox left = new VBox(16);

        Label benefitsTitle = sectionTitle("Membership Benefits" );
        benefitsTitle.setTextFill(
                Color.PURPLE);
               
        benefitsTitle.setFont(
                Font.font(24));

        benefitsTitle.setStyle("-fx-font-weight:bold;");

        GridPane benefits =createBenefitsGrid();
                

        Label paymentTitle =  sectionTitle(  "Payment History");
        paymentTitle.setTextFill(
                Color.PURPLE);
                        
        paymentTitle.setFont(
                Font.font(24));

        paymentTitle.setStyle("-fx-font-weight:bold;");

        VBox payment =createPaymentHistory();
               

        left.getChildren().addAll(
                benefitsTitle,
                benefits,
                paymentTitle,
                payment
        );

        VBox right = createRightSection();
               

        HBox.setHgrow(
                left,
                Priority.ALWAYS
        );

        lower.getChildren().addAll(
                left,
                right
        );

        main.getChildren().addAll(
                header,
                membershipCard,
                lower
        );

        return main;
    }

    // =====================================================
    // MEMBERSHIP CARD
    // =====================================================

    private HBox createMembershipCard() {

        HBox card = new HBox(20);

        card.setPadding(
                new Insets(20)
        );

        card.setPrefHeight(170);

        card.setStyle(
                "-fx-background-color: #530956;" +
                "-fx-background-radius: 20;" +
                "-fx-border-color: #242424;" +
                "-fx-border-radius: 20;"
        );

        VBox information =
                new VBox(7);

        HBox titleBox =
                new HBox(10);

        titleBox.setAlignment(
                Pos.CENTER_LEFT
        );

        Label icon =
                new Label("♙");

        icon.setTextFill(
                Color.web(GREEN)
        );

        icon.setFont(
                Font.font(24)
        );

        Label name =
                new Label(
                        "Premium Membership"
                );

        name.setTextFill(
                Color.WHITE
        );

        name.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        Label active =
                new Label("● Active");

        active.setTextFill(
                Color.web(GREEN)
        );

        active.setStyle(
                "-fx-background-color: #f487ec;" +
                "-fx-padding: 3 8 3 8;" +
                "-fx-background-radius: 10;"
        );

        titleBox.getChildren().addAll(
                icon,
                name,
                active
        );

        Label price =
                new Label("₹999 / Year");

        price.setTextFill(
                Color.web(GREEN)
        );

        price.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        Label description =
                new Label(
                        "Enjoy premium access to FitCircle features, " +
                        "exclusive clubs, challenges and community benefits."
                );

        description.setTextFill(
                Color.web("#AAAAAA")
        );

        description.setFont(
                Font.font("Arial", 16)
        );

        description.setWrapText(true);

        HBox dates =
                new HBox(30);

        dates.getChildren().addAll(

                info(
                        "STARTED",
                        "01 Aug 2026"
                ),

                info(
                        "EXPIRES",
                        "31 Jul 2027"
                ),

                info(
                        "AUTO RENEWAL",
                        "● Enabled"
                )
        );

        information.getChildren().addAll(

                titleBox,

                price,

                description,

                dates
        );

        HBox.setHgrow(
                information,
                Priority.ALWAYS
        );

        // RIGHT SIDE BUTTONS
        VBox actions =
                new VBox(8);

        actions.setAlignment(
                Pos.TOP_RIGHT
        );

        Button renew = new Button("Renew Membership");

         renew.setOnAction(e ->{
            System.out.println("Manage Membership Button Clicked");
        });
        


        renew.setPrefWidth(160);

        renew.setPrefHeight(35);

        renew.setStyle(
                "-fx-background-color: " + GREEN + ";" +
                "-fx-text-fill: black;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 6;"
        );

        Button manage = new Button("Manage Membership" );

        manage.setOnAction(e ->{
            System.out.println("Manage Membership Button Clicked");
        });

         manage.setPrefWidth(160);

        manage.setPrefHeight(35);

        manage.setStyle(
                 "-fx-background-color: " + GREEN + ";" +
                "-fx-text-fill: black;" +
                "-fx-font-weight: bold;"+
                 "-fx-background-radius: 6;"
                
        );

        actions.getChildren().addAll(
                renew,
                manage
        );

        card.getChildren().addAll(
                information,
                actions
        );

        return card;
    }

    // =====================================================
    // INFO
    // =====================================================

    private VBox info(
            String heading,
            String value
    ) {

        VBox box = new VBox(3);
       

        Label h =
                new Label(heading);

        h.setTextFill(
                Color.web("#bbc1f3")
        );

        h.setFont(
                Font.font(13)
        );

        Label v =
                new Label(value);

        v.setTextFill(
                Color.BLANCHEDALMOND
        );

        v.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        box.getChildren().addAll(
                h,
                v
        );

        return box;
    }

    // =====================================================
    // BENEFITS
    // =====================================================

    private GridPane createBenefitsGrid() {

        GridPane grid =
                new GridPane();

        grid.setHgap(12);

        grid.setVgap(12);

        String[][] benefits = {

                {
                        "∞",
                        "Unlimited Club Access",
                        "Join any FitCircle club without restrictions."
                },

                {
                        "♜",
                        "Exclusive Challenges",
                        "Participate in pro-tier fitness challenges."
                },

                {
                        "▣",
                        "Premium Events",
                        "VIP access to local and virtual fitness events."
                },

                {
                        "▥",
                        "Advanced Tracking",
                        "Deep analytics on your workout performance."
                },

                {
                        "✚",
                        "AI Fitness Coach",
                        "Personalized routines generated by AI."
                },

                {
                        "★",
                        "Priority Community",
                        "Highlighted profile in the FitCircle network."
                }
        };

        for (int i = 0; i < benefits.length; i++) {

            VBox card =
                    benefitCard(
                            benefits[i][0],
                            benefits[i][1],
                            benefits[i][2]
                    );

            grid.add(
                    card,
                    i % 2,
                    i / 2
            );
        }

        return grid;
    }

    // =====================================================
    // BENEFIT CARD
    // =====================================================

    private VBox benefitCard(
            String icon,
            String title,
            String description
    ) {

        VBox card =
                new VBox(4);

        card.setPadding(
                new Insets(12)
        );

        card.setPrefHeight(75);

        card.setStyle(
                "-fx-background-color: " + CARD +";" +
                "-fx-background-radius: 8;" +
                "-fx-border-color: #292929;" +
                "-fx-border-radius: 8;"
        );

        HBox heading =
                new HBox(10);

        heading.setAlignment(
                Pos.CENTER_LEFT
        );

        Label iconLabel =
                new Label(icon);

        iconLabel.setTextFill(
                Color.WHITE
        );

        iconLabel.setFont(
                Font.font(20)
        );

        Label titleLabel =
                new Label(title);

        titleLabel.setTextFill(
                Color.WHITESMOKE
        );

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        heading.getChildren().addAll(
                iconLabel,
                titleLabel
        );

        Label descriptionLabel =
                new Label(description);

        descriptionLabel.setTextFill(
                Color.web("#888888")
        );

        descriptionLabel.setFont(
                Font.font(13)
        );

        descriptionLabel.setWrapText(true);

        card.getChildren().addAll(
                heading,
                descriptionLabel
        );

        return card;
    }

    // =====================================================
    // PAYMENT HISTORY
    // =====================================================

    private VBox createPaymentHistory() {

        VBox table =
                new VBox(4);

        table.setPadding(
                new Insets(10)
        );

        table.setStyle(
                "-fx-background-color: #530956;" +
                "-fx-background-radius: 8;" +
                "-fx-border-color: #f8dfdf;" +
                "-fx-border-radius: 8;"
        );

        table.getChildren().addAll(

                paymentRow(
                        "Date",
                        "Transaction ID",
                        "Plan",
                        "Amount",
                        "Status"
                ),

                paymentRow(
                        "01 Aug 2026",
                        "FC20260801",
                        "Premium Membership",
                        "₹999",
                        "Paid"
                ),

                paymentRow(
                        "01 Aug 2025",
                        "FC20250801",
                        "Premium Membership",
                        "₹999",
                        "Paid"
                )
        );

        return table;
    }

    // =====================================================
    // PAYMENT ROW
    // =====================================================

    private HBox paymentRow(
            String date,
            String transaction,
            String plan,
            String amount,
            String status
    ) {

        HBox row =
                new HBox(10);

        Label l1 =
                tableLabel(date);

        Label l2 =
                tableLabel(transaction);

        Label l3 =
                tableLabel(plan);

        Label l4 =
                tableLabel(amount);

        Label l5 =
                tableLabel(status);

        l1.setPrefWidth(90);

        l2.setPrefWidth(110);

        l3.setPrefWidth(145);

        l4.setPrefWidth(70);

        l5.setPrefWidth(60);

        if (status.equals("Paid")) {

            l5.setTextFill(
                    Color.web(GREEN)
            );
        }

        row.getChildren().addAll(
                l1,
                l2,
                l3,
                l4,
                l5
        );

        return row;
    }

    private Label tableLabel(
            String text
    ) {

        Label label =
                new Label(text);

        label.setTextFill(
                Color.web("#f0e9e6")
        );

        label.setFont(
                Font.font(14)
        );

        return label;
    }

    // =====================================================
    // RIGHT SECTION
    // =====================================================

    private VBox createRightSection() {

        VBox right =
                new VBox(15);

        right.setPrefWidth(270);

        VBox details =
                new VBox(12);

        details.setPadding(
                new Insets(15)
        );

        details.setStyle(
                "-fx-background-color:  "+ CARD2 +";" +
                "-fx-background-radius: 8;" +
                "-fx-border-color: #f4e0e0;" +
                "-fx-border-radius: 8;"
        );

        Label title =
                new Label("ⓘ  Details");

        title.setTextFill(
                Color.WHITE
        );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        details.getChildren().addAll(

                title,

                detailRow(
                        "Membership ID",
                        "FC-12345678"
                ),

                detailRow(
                        "Plan Type",
                        "Annual Premium"
                ),

                detailRow(
                        "Billing Cycle",
                        "Yearly"
                ),

                detailRow(
                        "Payment Method",
                        "Visa ending 4455"
                ),

                detailRow(
                        "Next Billing",
                        "01 Aug 2027"
                ),

                detailRow(
                        "Status",
                        "● Active"
                )
        );

        VBox help =
                new VBox(8);

        help.setPadding(
                new Insets(15)
        );

        help.setStyle(
                "-fx-background-color: #530956;" +
                "-fx-background-radius: 8;"
        );

        Label helpTitle =
                new Label("◔  Need Help?");

        helpTitle.setTextFill(
                Color.WHITE
        );

        helpTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        Label helpText =
                new Label(
                        "Have questions about your billing or plan " +
                        "features? Our support team is here to help."
                );

        helpText.setTextFill(
                Color.web("#999999")
        );

        helpText.setWrapText(true);

        helpText.setFont(
                Font.font(15)
        );

        Button contact = new Button("Contact Support →");

        contact.setOnAction(e ->{
            System.out.println("Contact Button Clicked");
        });
        contact.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;"
        );

        help.getChildren().addAll(
                helpTitle,
                helpText,
                contact
        );

        right.getChildren().addAll(
                details,
                help
        );

        return right;
    }

    // =====================================================
    // DETAIL ROW
    // =====================================================

    private VBox detailRow(
            String name,
            String value
    ) {

        VBox box =
                new VBox(3);

        Label nameLabel =
                new Label(name);

        nameLabel.setTextFill(
                Color.web("#777777")
        );

        nameLabel.setFont(Font.font(15));
     

        Label valueLabel =
                new Label(value);

        valueLabel.setTextFill(
                Color.web("#DDDDDD")
        );

        valueLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        if (value.equals("● Active")) {

            valueLabel.setTextFill(
                    Color.web(GREEN)
            );
        }

        box.getChildren().addAll(
                nameLabel,
                valueLabel
        );

        return box;
    }

    // =====================================================
    // SECTION TITLE
    // =====================================================

    private Label sectionTitle(
            String text
    ) {

        Label label =
                new Label(text);

        label.setTextFill(
                Color.WHITE
        );

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        return label;
    }
    
}