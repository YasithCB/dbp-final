package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.TableView;
import model.Student;

/**
 * author  Yasith C Bandara
 * created 7/12/2022 - 9:50 AM
 * project DBP-Final
 */

public class StudentFormController {
    public JFXButton btnSaveUpdate;
    public TableView<Student> tbnStudent;
    public JFXTextField txtName;
    public JFXTextField txtEmail;
    public JFXTextField txtContact;
    public JFXTextField txtAddress;
    public JFXTextField txtNic;
    public JFXButton btnNew;
    public JFXButton btnDelete;

    public void initialize(){
        btnNew.setOnMouseClicked(event -> {

        });

        btnSaveUpdate.setOnMouseClicked(event -> {
            saveStudent();
        });

        btnDelete.setOnMouseClicked(event -> {

        });
    }

    private void saveStudent() {

    }


}
