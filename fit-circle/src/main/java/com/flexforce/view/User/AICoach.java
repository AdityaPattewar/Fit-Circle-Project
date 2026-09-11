package com.flexforce.view.User;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AICoach extends Application {

    
      
    private TextArea chatArea;
    private TextField questionField;

     @Override
    public void start(Stage stage) throws Exception {
       
    




        // =====================================================
        // MAIN ROOT
        // =====================================================

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color:#0B0F14;"
        );


        // =====================================================
        // TOP HEADER
        // =====================================================

        Label title = new Label("✨ AI Fitness Coach");

        title.setStyle(
                "-fx-font-size:26px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:white;"
        );

        Label subtitle = new Label(
                "Your personal AI fitness assistant"
        );

        subtitle.setStyle(
                "-fx-font-size:13px;" +
                "-fx-text-fill:#8F9BA8;"
        );

        VBox header = new VBox(
                5,
                title,
                subtitle
        );

        header.setPadding(
                new Insets(25, 30, 20, 30)
        );


        // =====================================================
        // AI CHAT AREA
        // =====================================================

        chatArea = new TextArea();

        chatArea.setEditable(false);

        chatArea.setWrapText(true);

        chatArea.setText(
                "🤖 AI Coach\n\n" +
                "Hello! 👋 I'm your AI Fitness Coach.\n\n" +
                "I can help you with:\n" +
                "• Workout suggestions\n" +
                "• Fitness routines\n" +
                "• Exercise ideas\n" +
                "• Training plans\n" +
                "• General fitness guidance\n\n" +
                "Ask me anything about your workout!"
        );

        chatArea.setStyle(
                "-fx-control-inner-background:#151C24;" +
                "-fx-text-fill:#FFFFFF;" +
                "-fx-font-size:14px;" +
                "-fx-border-color:#2A3541;" +
                "-fx-border-radius:12px;" +
                "-fx-background-radius:12px;"
        );

        chatArea.setPrefHeight(400);

        chatArea.setWrapText(true);


        // =====================================================
        // QUESTION FIELD
        // =====================================================

        questionField = new TextField();

        questionField.setPromptText(
                "Ask your fitness question..."
        );

        questionField.setStyle(
                "-fx-background-color:#1C252E;" +
                "-fx-text-fill:white;" +
                "-fx-prompt-text-fill:#8F9BA8;" +
                "-fx-font-size:14px;" +
                "-fx-background-radius:10px;" +
                "-fx-border-color:#2A3541;" +
                "-fx-border-radius:10px;"
        );

        questionField.setPrefHeight(45);


        // =====================================================
        // ASK AI BUTTON
        // =====================================================

        Button askAI = new Button("✨ Ask AI");

        askAI.setPrefHeight(45);

        askAI.setPrefWidth(120);

        askAI.setStyle(
                "-fx-background-color:#C6FF00;" +
                "-fx-text-fill:#0B0F14;" +
                "-fx-font-weight:bold;" +
                "-fx-font-size:14px;" +
                "-fx-background-radius:10px;" +
                "-fx-cursor:hand;"
        );

        Button clearChat = new Button("🗑️ Clear");

        clearChat.setPrefHeight(45);
        clearChat.setPrefWidth(100);

        clearChat.setStyle(
                "-fx-background-color:#2A3541;" +
                "-fx-text-fill:white;" +
                "-fx-font-weight:bold;" +
                "-fx-font-size:14px;" +
                "-fx-background-radius:10px;" +
                "-fx-cursor:hand;"
        );


        // =====================================================
        // BUTTON ACTION
        // =====================================================

        clearChat.setOnAction(e -> {
            chatArea.setText(
                "🤖 AI Coach\n\n" +
                "Hello! 👋 I'm your AI Fitness Coach.\n\n" +
                "I can help you with:\n" +
                "• Workout suggestions\n" +
                "• Fitness routines\n" +
                "• Exercise ideas\n" +
                "• Training plans\n" +
                "• General fitness guidance\n\n" +
                "Ask me anything about your workout!"
            );
        });

        askAI.setOnAction(e -> {

            String question =
                    questionField.getText().trim();

            if (!question.isEmpty()) {

                chatArea.appendText(
                        "\n\n👤 You:\n" +
                        question
                );

                chatArea.appendText(
                        "\n\n🤖 AI Coach:\n" +
                        "Thinking..."
                );

                questionField.clear();
                askAI.setDisable(true);
                questionField.setDisable(true);

                javafx.concurrent.Task<String> task = new javafx.concurrent.Task<String>() {
                    @Override
                    protected String call() throws Exception {
                        com.flexforce.Services.GroqService service = new com.flexforce.Services.GroqService();
                        return service.askQuestion(question);
                    }
                };

                task.setOnSucceeded(event -> {
                    String response = task.getValue();
                    String currentText = chatArea.getText();
                    if (currentText.endsWith("Thinking...")) {
                        chatArea.setText(currentText.substring(0, currentText.length() - 11) + response);
                    } else {
                        chatArea.appendText("\n\n🤖 AI Coach:\n" + response);
                    }
                    chatArea.positionCaret(chatArea.getText().length());
                    askAI.setDisable(false);
                    questionField.setDisable(false);
                    questionField.requestFocus();
                });

                task.setOnFailed(event -> {
                    Throwable ex = task.getException();
                    if (ex != null) ex.printStackTrace();
                    String currentText = chatArea.getText();
                    if (currentText.endsWith("Thinking...")) {
                        chatArea.setText(currentText.substring(0, currentText.length() - 11) + "Sorry, an error occurred while connecting to the AI service. " + (ex != null ? ex.getMessage() : ""));
                    } else {
                        chatArea.appendText("\n\n🤖 AI Coach:\nSorry, an error occurred while connecting to the AI service. " + (ex != null ? ex.getMessage() : ""));
                    }
                    chatArea.positionCaret(chatArea.getText().length());
                    askAI.setDisable(false);
                    questionField.setDisable(false);
                    questionField.requestFocus();
                });

                new Thread(task).start();
            }
        });


        // =====================================================
        // ENTER KEY
        // =====================================================

        questionField.setOnAction(e ->
                askAI.fire()
        );


        // =====================================================
        // INPUT BOX
        // =====================================================

        HBox inputBox = new HBox(
                10,
                questionField,
                askAI,
                clearChat
        );

        inputBox.setAlignment(
                Pos.CENTER
        );

        HBox.setHgrow(
                questionField,
                Priority.ALWAYS
        );


        // =====================================================
        // CENTER CONTENT
        // =====================================================

        VBox content = new VBox(
                15,
                chatArea,
                inputBox
        );

        content.setPadding(
                new Insets(0, 30, 30, 30)
        );

        VBox.setVgrow(
                chatArea,
                Priority.ALWAYS
        );


        root.setTop(header);

        root.setCenter(content);


        // =====================================================
        // SCENE
        // =====================================================

        Scene scene = new Scene(
                root,
                1000,
                700
        );

        stage.setScene(scene);

        stage.setTitle("AI Fitness Coach");

        stage.show();
    }
}