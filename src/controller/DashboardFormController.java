package controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

/**
 * author  Yasith C Bandara
 * created 7/12/2022 - 9:38 AM
 * project DBP-Final
 */

public class DashboardFormController {
    public JFXButton btnStudent;
    public AnchorPane apnMain;

    public void initialize() {
        btnStudent.setOnMouseClicked(event -> {
            try {
                changeUi(apnMain, "StudentForm");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void changeUi(AnchorPane apn, String location) throws IOException {
        Stage stage = (Stage) apn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"));
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }
}
