// package com.flexforce.view;

// import com.flexforce.controller.RazorpayController;
// import com.flexforce.dao.PaymentDao;
// import com.flexforce.model.Payment.Payment;

// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.ProgressIndicator;
// import javafx.scene.layout.VBox;
// import javafx.stage.Modality;
// import javafx.stage.Stage;



// public class PaymentDialog {
    
// public static void showPaymentDialog(String userId, String clubId, String clubName, String amountString) {
        
//         Stage window = new Stage();
//         window.initModality(Modality.APPLICATION_MODAL);
//         window.setTitle("Join Club - Secure Payment");
//         window.setMinWidth(350);
        
//         String bg = "#191C1A";
//         String white = "#FFFFFF";
//         String lime = "#B6FF00";
//         String gray = "#AEB5BC";

//         Label titleLabel = new Label("Complete Your Payment");
//         titleLabel.setStyle("-fx-text-fill:" + white + "; -fx-font-size: 20px; -fx-font-weight: bold;");

//         Label clubLabel = new Label("Club: " + clubName);
//         clubLabel.setStyle("-fx-text-fill:" + gray + "; -fx-font-size: 14px;");

//         Label amountLabel = new Label("Amount to Pay: ₹" + amountString);
//         amountLabel.setStyle("-fx-text-fill:" + lime + "; -fx-font-size: 18px; -fx-font-weight: bold;");

//         Label statusLabel = new Label();
//         statusLabel.setStyle("-fx-text-fill:" + white + "; -fx-font-size: 13px;");

//         ProgressIndicator loader = new ProgressIndicator();
//         loader.setVisible(false);
//         loader.setManaged(false);

//         Button payButton = new Button("PAY NOW");
//         payButton.setStyle("-fx-background-color:" + lime + "; -fx-text-fill: black; -fx-font-weight: bold; -fx-background-radius: 5;");
//         payButton.setPrefWidth(150);

//         Button closeButton = new Button("Cancel");
//         closeButton.setStyle("-fx-background-color: transparent; -fx-text-fill:" + gray + "; -fx-border-color:" + gray + "; -fx-border-radius: 5;");
//         closeButton.setPrefWidth(150);
//         closeButton.setOnAction(e -> window.close());

//         Button confirmButton = new Button("I've Completed Payment");
//         confirmButton.setStyle("-fx-background-color:" + lime + "; -fx-text-fill: black; -fx-font-weight: bold; -fx-background-radius: 5;");
//         confirmButton.setPrefWidth(180);
//         confirmButton.setVisible(false);
//         confirmButton.setManaged(false);

//         payButton.setOnAction(e -> {
//             loader.setVisible(true);
//             loader.setManaged(true);
//             statusLabel.setText("Generating Secure Razorpay Checkout...");
//             payButton.setDisable(true);
//             closeButton.setDisable(true);

//             new Thread(() -> {
//                 try {
//                     RazorpayController razorpayController = new RazorpayController();
//                     double amount = 500.0;
//                     try {
//                         amount = Double.parseDouble(amountString);
//                     } catch (Exception ex) {}

//                     String paymentLinkUrl = razorpayController.createPaymentLink(amount, userId, clubId, "CLUB_JOIN");

//                     if (paymentLinkUrl != null && !paymentLinkUrl.isEmpty()) {
//                         java.awt.Desktop.getDesktop().browse(new java.net.URI(paymentLinkUrl));
                        
//                         javafx.application.Platform.runLater(() -> {
//                             loader.setVisible(false);
//                             loader.setManaged(false);
//                             statusLabel.setText("A browser window has been opened for Razorpay Checkout.\nPlease complete the payment there.");
//                             payButton.setVisible(false);
//                             payButton.setManaged(false);
//                             confirmButton.setVisible(true);
//                             confirmButton.setManaged(true);
//                             closeButton.setDisable(false);
//                         });
//                     } else {
//                         throw new Exception("Failed to generate payment link.");
//                     }
//                 } catch (Exception ex) {
//                     ex.printStackTrace();
//                     javafx.application.Platform.runLater(() -> {
//                         loader.setVisible(false);
//                         loader.setManaged(false);
//                         statusLabel.setText("Failed to connect to Razorpay. Please try again.");
//                         statusLabel.setStyle("-fx-text-fill: #ff5555; -fx-font-size: 13px;");
//                         payButton.setDisable(false);
//                         closeButton.setDisable(false);
//                     });
//                 }
//             }).start();
//         });

//         confirmButton.setOnAction(e -> {
//             // Save Payment record locally
//             new Thread(() -> {
//                 try {
//                     double amount = 500.0;
//                     try { amount = Double.parseDouble(amountString); } catch (Exception ex) {}
                    
//                     Payment payment = new Payment();
//                     payment.setPaymentId("PAY_" + System.currentTimeMillis()); 
//                     payment.setUserId(userId);
//                     payment.setClubId(clubId);
//                     payment.setAmount(amount);
//                     payment.setStatus("SUCCESS");
//                     payment.setCreatedAt(System.currentTimeMillis());

//                     PaymentDao paymentDao = new PaymentDao();
//                     paymentDao.savePayment(payment);
                    
//                     // Enroll the user in the club
//                     com.flexforce.model.common_for_user_clubowner.UserClubMembership membership = 
//                         new com.flexforce.model.common_for_user_clubowner.UserClubMembership();
//                     membership.setUserId(userId);
//                     membership.setClubId(clubId);
//                     membership.setJoinedDate(java.time.LocalDate.now().toString());
//                     membership.setStatus("ACTIVE");

//                     com.flexforce.dao.UserClubMembershipDAO membershipDao = new com.flexforce.dao.UserClubMembershipDAO();
//                     // If membership already exists, we might want to update, but saveMembership calls create().
//                     // To be safe against crashes if the user is already enrolled but paying again, we'll try/catch it locally.
//                     try {
//                         com.flexforce.model.common_for_user_clubowner.UserClubMembership existing = membershipDao.getMembership(userId, clubId);
//                         if (existing != null) {
//                             membershipDao.updateMembership(membership);
//                         } else {
//                             membershipDao.saveMembership(membership);
//                         }
//                     } catch (Exception membershipEx) {
//                         membershipEx.printStackTrace();
//                     }

//                     // Add Notification
//                     try {
//                         com.flexforce.model.user.UserNotification notif = new com.flexforce.model.user.UserNotification();
//                         notif.setNotificationId("NOTIF_" + System.currentTimeMillis());
//                         notif.setUserId(userId);
//                         notif.setTitle("Welcome to " + clubName + "!");
//                         notif.setMessage("You have successfully joined " + clubName + ". Get ready to start your journey!");
//                         notif.setDate(java.time.LocalDate.now().toString());
//                         notif.setRead(false);
//                         notif.setCategory("Clubs");

//                         com.flexforce.dao.UserNotificationDAO notifDao = new com.flexforce.dao.UserNotificationDAO();
//                         notifDao.saveNotification(notif);
//                     } catch (Exception notifEx) {
//                         notifEx.printStackTrace();
//                     }
                    
//                     javafx.application.Platform.runLater(() -> {
//                         statusLabel.setText("Payment Confirmed! You have joined the club.");
//                         statusLabel.setStyle("-fx-text-fill:" + lime + "; -fx-font-size: 13px;");
//                         confirmButton.setDisable(true);
//                         closeButton.setText("Close");
//                     });
//                 } catch (Exception ex) {
//                     ex.printStackTrace();
//                 }
//             }).start();
//         });

//         VBox layout = new VBox(15);
//         layout.setPadding(new Insets(30));
//         layout.setAlignment(Pos.CENTER);
//         layout.setStyle("-fx-background-color:" + bg + ";");
//         layout.getChildren().addAll(titleLabel, clubLabel, amountLabel, loader, statusLabel, payButton, confirmButton, closeButton);

//         Scene scene = new Scene(layout);
//         window.setScene(scene);
//         window.showAndWait();
//     }
// }

package com.flexforce.view;

import com.flexforce.controller.RazorpayController;
import com.flexforce.dao.PaymentDao;
import com.flexforce.model.Payment.Payment;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PaymentDialog {

    public static void showPaymentDialog(
            String userId,
            String clubId,
            String clubName,
            String amountString) {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Join Club - Secure Payment");
        window.setMinWidth(350);

        String bg = "#191C1A";
        String white = "#FFFFFF";
        String lime = "#B6FF00";
        String gray = "#AEB5BC";

        // =====================================================
        // VALIDATE MEMBERSHIP AMOUNT
        // =====================================================

        double amount;

        try {
            amount = Double.parseDouble(
                    amountString.replace("₹", "")
                               .replace(",", "")
                               .trim()
            );

            if (amount <= 0) {
                throw new NumberFormatException(
                        "Amount must be greater than zero"
                );
            }

        } catch (Exception ex) {

            ex.printStackTrace();

            Label errorLabel = new Label(
                    "Invalid membership amount."
            );

            errorLabel.setStyle(
                    "-fx-text-fill: #ff5555;" +
                    "-fx-font-size: 14px;"
            );

            Button closeButton = new Button("Close");

            closeButton.setOnAction(
                    e -> window.close()
            );

            VBox errorLayout = new VBox(
                    15,
                    errorLabel,
                    closeButton
            );

            errorLayout.setAlignment(Pos.CENTER);
            errorLayout.setPadding(
                    new Insets(30)
            );

            errorLayout.setStyle(
                    "-fx-background-color:" + bg + ";"
            );

            Scene errorScene = new Scene(
                    errorLayout
            );

            window.setScene(errorScene);
            window.showAndWait();

            return;
        }

        // =====================================================
        // UI
        // =====================================================

        Label titleLabel =
                new Label("Complete Your Payment");

        titleLabel.setStyle(
                "-fx-text-fill:" + white + ";" +
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;"
        );

        Label clubLabel =
                new Label("Club: " + clubName);

        clubLabel.setStyle(
                "-fx-text-fill:" + gray + ";" +
                "-fx-font-size: 14px;"
        );

        Label amountLabel =
                new Label(
                        "Amount to Pay: ₹" +
                        String.format("%.2f", amount)
                );

        amountLabel.setStyle(
                "-fx-text-fill:" + lime + ";" +
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;"
        );

        Label statusLabel =
                new Label();

        statusLabel.setStyle(
                "-fx-text-fill:" + white + ";" +
                "-fx-font-size: 13px;"
        );

        ProgressIndicator loader =
                new ProgressIndicator();

        loader.setVisible(false);
        loader.setManaged(false);

        Button payButton =
                new Button("PAY NOW");

        payButton.setStyle(
                "-fx-background-color:" + lime + ";" +
                "-fx-text-fill: black;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 5;"
        );

        payButton.setPrefWidth(150);

        Button closeButton =
                new Button("Cancel");

        closeButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill:" + gray + ";" +
                "-fx-border-color:" + gray + ";" +
                "-fx-border-radius: 5;"
        );

        closeButton.setPrefWidth(150);

        closeButton.setOnAction(
                e -> window.close()
        );

        Button confirmButton =
                new Button("I've Completed Payment");

        confirmButton.setStyle(
                "-fx-background-color:" + lime + ";" +
                "-fx-text-fill: black;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 5;"
        );

        confirmButton.setPrefWidth(180);

        confirmButton.setVisible(false);
        confirmButton.setManaged(false);

        // =====================================================
        // PAY NOW
        // =====================================================

        payButton.setOnAction(e -> {

            loader.setVisible(true);
            loader.setManaged(true);

            statusLabel.setText(
                    "Generating Secure Razorpay Checkout..."
            );

            payButton.setDisable(true);
            closeButton.setDisable(true);

            new Thread(() -> {

                try {

                    RazorpayController razorpayController =
                            new RazorpayController();

                    /*
                     * IMPORTANT:
                     * Use the membership amount selected by the user.
                     *
                     * No hard-coded ₹500 here.
                     */
                    String paymentLinkUrl =
                            razorpayController.createPaymentLink(
                                    amount,
                                    userId,
                                    clubId,
                                    "CLUB_JOIN"
                            );

                    if (paymentLinkUrl != null &&
                        !paymentLinkUrl.isEmpty()) {

                        java.awt.Desktop
                                .getDesktop()
                                .browse(
                                        new java.net.URI(
                                                paymentLinkUrl
                                        )
                                );

                        javafx.application.Platform.runLater(() -> {

                            loader.setVisible(false);
                            loader.setManaged(false);

                            statusLabel.setText(
                                    "A browser window has been opened " +
                                    "for Razorpay Checkout.\n" +
                                    "Please complete the payment there."
                            );

                            payButton.setVisible(false);
                            payButton.setManaged(false);

                            confirmButton.setVisible(true);
                            confirmButton.setManaged(true);

                            closeButton.setDisable(false);
                        });

                    } else {

                        throw new Exception(
                                "Failed to generate payment link."
                        );
                    }

                } catch (Exception ex) {

                    ex.printStackTrace();

                    javafx.application.Platform.runLater(() -> {

                        loader.setVisible(false);
                        loader.setManaged(false);

                        statusLabel.setText(
                                "Failed to connect to Razorpay. " +
                                "Please try again."
                        );

                        statusLabel.setStyle(
                                "-fx-text-fill: #ff5555;" +
                                "-fx-font-size: 13px;"
                        );

                        payButton.setDisable(false);
                        closeButton.setDisable(false);
                    });
                }

            }).start();
        });

        // =====================================================
        // CONFIRM PAYMENT
        // =====================================================

        confirmButton.setOnAction(e -> {

            new Thread(() -> {

                try {

                    // =================================================
                    // SAVE PAYMENT
                    // =================================================

                    Payment payment =
                            new Payment();

                    payment.setPaymentId(
                            "PAY_" +
                            System.currentTimeMillis()
                    );

                    payment.setUserId(userId);
                    payment.setClubId(clubId);

                    /*
                     * IMPORTANT:
                     * Save the ACTUAL membership amount.
                     *
                     * Previously this was always 500.
                     */
                    payment.setAmount(amount);

                    payment.setStatus("SUCCESS");

                    payment.setCreatedAt(
                            System.currentTimeMillis()
                    );

                    PaymentDao paymentDao =
                            new PaymentDao();

                    paymentDao.savePayment(payment);

                    // =================================================
                    // ENROLL USER IN CLUB
                    // =================================================

                    com.flexforce.model.common_for_user_clubowner
                            .UserClubMembership membership =
                            new com.flexforce.model.common_for_user_clubowner
                                    .UserClubMembership();

                    membership.setUserId(userId);

                    membership.setClubId(clubId);

                    membership.setJoinedDate(
                            java.time.LocalDate
                                    .now()
                                    .toString()
                    );

                    membership.setStatus("ACTIVE");

                    com.flexforce.dao
                            .UserClubMembershipDAO membershipDao =
                            new com.flexforce.dao
                                    .UserClubMembershipDAO();

                    /*
                     * Check whether membership already exists.
                     */

                    try {

                        com.flexforce.model
                                .common_for_user_clubowner
                                .UserClubMembership existing =
                                membershipDao.getMembership(
                                        userId,
                                        clubId
                                );

                        if (existing != null) {

                            membershipDao.updateMembership(
                                    membership
                            );

                        } else {

                            membershipDao.saveMembership(
                                    membership
                            );
                        }

                    } catch (Exception membershipEx) {

                        membershipEx.printStackTrace();
                    }

                    // =================================================
                    // ADD NOTIFICATION
                    // =================================================

                    try {

                        com.flexforce.model.user
                                .UserNotification notif =
                                new com.flexforce.model.user
                                        .UserNotification();

                        notif.setNotificationId(
                                "NOTIF_" +
                                System.currentTimeMillis()
                        );

                        notif.setUserId(userId);

                        notif.setTitle(
                                "Welcome to " +
                                clubName +
                                "!"
                        );

                        notif.setMessage(
                                "You have successfully joined " +
                                clubName +
                                ". Get ready to start your journey!"
                        );

                        notif.setDate(
                                java.time.LocalDate
                                        .now()
                                        .toString()
                        );

                        notif.setRead(false);

                        notif.setCategory(
                                "Clubs"
                        );

                        com.flexforce.dao
                                .UserNotificationDAO notifDao =
                                new com.flexforce.dao
                                        .UserNotificationDAO();

                        notifDao.saveNotification(
                                notif
                        );

                    } catch (Exception notifEx) {

                        notifEx.printStackTrace();
                    }

                    // =================================================
                    // UPDATE UI
                    // =================================================

                    javafx.application.Platform.runLater(() -> {

                        statusLabel.setText(
                                "Payment Confirmed! " +
                                "You have joined the club."
                        );

                        statusLabel.setStyle(
                                "-fx-text-fill:" + lime + ";" +
                                "-fx-font-size: 13px;"
                        );

                        confirmButton.setDisable(true);

                        closeButton.setText(
                                "Close"
                        );
                    });

                } catch (Exception ex) {

                    ex.printStackTrace();

                    javafx.application.Platform.runLater(() -> {

                        statusLabel.setText(
                                "Payment processing failed. " +
                                "Please try again."
                        );

                        statusLabel.setStyle(
                                "-fx-text-fill: #ff5555;" +
                                "-fx-font-size: 13px;"
                        );
                    });
                }

            }).start();
        });

        // =====================================================
        // LAYOUT
        // =====================================================

        VBox layout =
                new VBox(15);

        layout.setPadding(
                new Insets(30)
        );

        layout.setAlignment(
                Pos.CENTER
        );

        layout.setStyle(
                "-fx-background-color:" +
                bg +
                ";"
        );

        layout.getChildren().addAll(
                titleLabel,
                clubLabel,
                amountLabel,
                loader,
                statusLabel,
                payButton,
                confirmButton,
                closeButton
        );

        Scene scene =
                new Scene(layout);

        window.setScene(scene);

        window.showAndWait();
    }
}