package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Student;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

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
    public Label lblId;

    public void initialize(){
        loadTable();

        btnNew.setOnMouseClicked(event -> {
            deleteStudent();
        });

        btnSaveUpdate.setOnMouseClicked(event -> {
            if (btnSaveUpdate.getText().equals("Save"))
                saveStudent();
            else
                updateStudent();
        });

        btnDelete.setOnMouseClicked(event -> {

        });
    }

    private void deleteStudent() {
        try {
            if (CrudUtil.execute("DELETE FROM Student WHERE student_id=?",lblId.getText()))
                new Alert(Alert.AlertType.INFORMATION,"Deleted!").show();
            else
                new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void loadTable() {
        btnDelete.setDisable(true);
        tbnStudent.getColumns().clear();
        tbnStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tbnStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tbnStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("email"));
        tbnStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contact"));
        tbnStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("address"));
        tbnStudent.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("nic"));

        ObservableList<Student> students = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM Student");
            while (resultSet.next()) {
                students.add(new Student(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6)
                ));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        tbnStudent.setItems(students);
    }

    private void updateStudent() {
        try {
            if (CrudUtil.execute("UPDATE Student SET student_id=?, student_name=?, email=?, contact=?, address=?, nic=?",
                    lblId.getText(),
                    txtName.getText(),
                    txtEmail.getText(),
                    txtContact.getText(),
                    txtAddress.getText(),
                    txtNic.getText())){
                new Alert(Alert.AlertType.INFORMATION,"Updated!").show();
            }else
                new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void saveStudent() {
        try {
            if (CrudUtil.execute("INSERT INTO Student VALUES (?,?,?,?,?,?)",
                    lblId.getText(),
                    txtName.getText(),
                    txtEmail.getText(),
                    txtContact.getText(),
                    txtAddress.getText(),
                    txtNic.getText())){
                new Alert(Alert.AlertType.INFORMATION,"Saved!").show();
            }else
                new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }


}
