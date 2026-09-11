package com.flexforce.view.login;

import com.flexforce.view.User.UserDashboard;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class User_Login extends Application {

     private static Stage mainStage;
    private static Scene mainScene;

    @Override
    public void start(Stage stage) {

        mainStage = stage;

        Label logo = new Label("FitCircle");

        logo.setStyle(
                "-fx-text-fill:#a09628;" +
                "-fx-font-size:35px;" +
                "-fx-font-weight:bold;"
        );

        Label leftTitle =
                new Label(
                        "Connect. Compete.Grow Together."
                );

        leftTitle.setStyle(
                "-fx-text-fill:#ebeaf1;" +
                "-fx-font-size:20px;" +
                "-fx-font-weight:bold;"
        );

        Label description =
                new Label(
                        "Your community for fitness," +
                        "challenges, events and healthy" +
                        "competition."
                );

        description.setStyle(
                "-fx-text-fill:#a89b9b;" +
                "-fx-font-size:15px;"
        );

        description.setAlignment(Pos.CENTER);

        VBox vbleft =
                new VBox(
                        12,
                        logo,
                        leftTitle,
                        description
                );

        vbleft.setAlignment(Pos.CENTER);

        vbleft.setPrefWidth(300);
        vbleft.setPrefHeight(500);

        vbleft.setPadding(
                new Insets(
                        25,
                        20,
                        25,
                        20
                )
        );

        vbleft.setStyle(
                "-fx-background-color:#0c0c0c;" +
                "-fx-border-color:#363434;"
        );
        
        Label formTitle =
                new Label(
                        "Welcome Back"
                );

        formTitle.setStyle(
                "-fx-text-fill:white;" +
                "-fx-font-size:28px;" +
                "-fx-font-weight:bold;"
        );

        formTitle.setMaxWidth(
                Double.MAX_VALUE
        );

        formTitle.setAlignment(
                Pos.CENTER
        );

        Label formDescription =
                new Label(
                        "Sign in to continue your FitCircle journey"
                );

        formDescription.setStyle(
                "-fx-text-fill:#898d8b;" +
                "-fx-font-size:12px;"
        );

        Label emailLabel =
                new Label(
                        "EMAIL ADDRESS"
                );

        emailLabel.setStyle(
                "-fx-text-fill:#9c9797;" +
                "-fx-font-size:10px;" +
                "-fx-font-weight:bold;"
        );

        TextField emailField =
                new TextField();

        emailField.setPromptText(
                "✉️  Enter your email"
        );

        emailField.setStyle(
                "-fx-text-fill:#a29e9e;" +
                "-fx-font-size:12px;" +
                "-fx-background-color:#1A1A1A;" +
                "-fx-border-color:#363636;" +
                "-fx-border-radius:7px;" +
                "-fx-background-radius:7px;"
        );

        Label passwordLabel =
                new Label(
                        "PASSWORD"
                );

        passwordLabel.setStyle(
                "-fx-text-fill:#a59e9e;" +
                "-fx-font-size:10px;" +
                "-fx-font-weight:bold;"
        );

        PasswordField passwordField =
                new PasswordField();

        passwordField.setPromptText(
                "🔒  Enter your password"
        );

        passwordField.setStyle(
                "-fx-text-fill:#8a8383;" +
                "-fx-font-size:12px;" +
                "-fx-background-color:#1A1A1A;" +
                "-fx-border-color:#363636;" +
                "-fx-border-radius:7px;" +
                "-fx-background-radius:7px;"
        );

        CheckBox checkbox =
                new CheckBox(
                        "Remember me"
                );

        checkbox.setStyle(
                "-fx-text-fill:#7c7a7a;" +
                "-fx-font-size:12px;"
        );

        Button loginButton =
                new Button(
                        "Login  →"
                );

        loginButton.setMaxWidth(
                Double.MAX_VALUE
        );

        loginButton.setPrefHeight(42);

        loginButton.setStyle(
                "-fx-background-color:#488f55;" +
                "-fx-text-fill:white;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:8px;" +
                "-fx-cursor:hand;"
        );

        loginButton.setOnAction(e -> {

            System.out.println("Login");

            UserDashboard userdashboard =
                    new UserDashboard();

            Runnable callbackAction =
                    () -> {

                        backToLoginPage();

                    };

            mainStage.setScene(
                    userdashboard.mainScene(
                            callbackAction
                    )
            );
        });

        VBox vbright =
                new VBox(
                        12,
                        formTitle,
                        formDescription,
                        emailLabel,
                        emailField,
                        passwordLabel,
                        passwordField,
                        checkbox,
                        loginButton
                );

        vbright.setPadding(
                new Insets(25)
        );

        vbright.setAlignment(
                Pos.CENTER
        );

        vbright.setPrefWidth(300);
        vbright.setPrefHeight(400);

        vbright.setStyle(
                "-fx-background-color:#090909;" +
                "-fx-border-color:#282727;" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;"
        );

        HBox main =
                new HBox(
                        vbleft,
                        vbright
                );

        main.setAlignment(
                Pos.CENTER
        );

        main.setPadding(
                new Insets(30)
        );

        mainScene =
                new Scene(
                        main,
                        1250,
                        700
                );

        mainStage.setScene(
                mainScene
        );

        mainStage.setTitle(
                "FitCircle - Login"
        );

        mainStage.show();
    }

    public void backToLoginPage() {

        mainStage.setScene(
                mainScene
        );

        mainStage.setTitle(
                "FitCircle - Login"
        );
    }
}
