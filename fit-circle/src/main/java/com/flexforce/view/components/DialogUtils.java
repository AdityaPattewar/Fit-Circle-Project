package com.flexforce.view.components;

import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.net.URL;

public class DialogUtils {

    /**
     * Applies the global application dark theme to the provided dialog.
     */
    public static void applyTheme(Dialog<?> dialog) {
        if (dialog == null) return;
        
        try {
            DialogPane dialogPane = dialog.getDialogPane();
            
            // Add CSS Stylesheet
            URL cssUrl = DialogUtils.class.getResource("/assets/css/dialog.css");
            if (cssUrl != null) {
                dialogPane.getStylesheets().add(cssUrl.toExternalForm());
            } else {
                System.out.println("Warning: dialog.css not found.");
            }
            
            // Remove the default transparent style from the stage if we want standard windows
            // Or set style to UNDECORATED if we prefer fully custom windows
            // Stage stage = (Stage) dialogPane.getScene().getWindow();
            // stage.initStyle(StageStyle.UNDECORATED);
            
        } catch (Exception e) {
            System.out.println("Failed to apply theme to dialog.");
            e.printStackTrace();
        }
    }
}
