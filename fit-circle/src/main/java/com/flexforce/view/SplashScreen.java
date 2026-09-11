package com.flexforce.view;

import com.flexforce.view.login.Login;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class SplashScreen extends Application {

    private static final String BG_COLOR = "#0f1010";
    private static final String BRAND_COLOR = "#c6ff00";

    @Override
    public void start(Stage primaryStage) {
        
        // Remove window decorations for splash screen
        primaryStage.initStyle(StageStyle.UNDECORATED);

        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: " + BG_COLOR + ";");

        // App Logo or Name
        Label logo = new Label("FitCircle");
        logo.setTextFill(Color.web(BRAND_COLOR));
        logo.setStyle("-fx-font-size: 48px; -fx-font-weight: bold; -fx-font-style: italic;");
        
        // Tagline
        Label tagline = new Label("Empower Your Fitness Journey");
        tagline.setTextFill(Color.WHITE);
        tagline.setStyle("-fx-font-size: 16px;");
        
        // Loader
        ProgressIndicator loader = new ProgressIndicator();
        loader.setStyle("-fx-progress-color: " + BRAND_COLOR + ";");
        loader.setPrefSize(40, 40);

        root.getChildren().addAll(logo, tagline, loader);

        Scene scene = new Scene(root, 1550, 800);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Fade in transition
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), root);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();

        // Pause for 3 seconds, then move to Login
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> {
            
            // Fade out transition
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), root);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setOnFinished(e -> {
                primaryStage.close();
                try {
                    new Login().start(new Stage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            fadeOut.play();
            
        });
        delay.play();
    }
}
