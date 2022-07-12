package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Student;
import util.CrudUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * author  Yasith C Bandara
 * created 7/12/2022 - 9:50 AM
 * project DBP-Final
 */

public class StudentFormController {
    public JFXButton btnSaveUpdate;
    public TableView<Student> tblStudent;
    public JFXTextField txtName;
    public JFXTextField txtEmail;
    public JFXTextField txtContact;
    public JFXTextField txtAddress;
    public JFXTextField txtNic;
    public JFXButton btnNew;
    public JFXButton btnDelete;
    public Label lblId;
    public ImageView imgIjse;
    public AnchorPane apnStudent;
    public JFXTextField txtSearch;

    public void initialize() {
        generateId();
        autoFillData();
        loadTable();

        btnNew.setOnMouseClicked(event -> {
            clearData();
        });

        btnSaveUpdate.setOnMouseClicked(event -> {
            if (btnSaveUpdate.getText().equals("Save"))
                saveStudent();
            else
                updateStudent();
        });

        btnDelete.setOnMouseClicked(event -> {
            deleteStudent();
        });

        imgIjse.setOnMouseClicked(event -> {
            Stage stage = (Stage) apnStudent.getScene().getWindow();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("../view/DashboardForm.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.show();
        });
    }

    private void autoFillData() {
        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                btnDelete.setDisable(false);
                btnSaveUpdate.setText("Update");

                lblId.setText(newValue.getId());
                txtName.setText(newValue.getName());
                txtAddress.setText(newValue.getAddress());
                txtEmail.setText(newValue.getEmail());
                txtContact.setText(newValue.getContact());
                txtNic.setText(newValue.getNic());

            } else {
                btnDelete.setDisable(true);
                btnSaveUpdate.setText("Save");
                generateId();
            }
        });
    }

    private void clearData() {
        tblStudent.getSelectionModel().clearSelection();
        generateId();
        txtName.setText("");
        txtNic.setText("");
        txtAddress.setText("");
        txtContact.setText("");
        txtEmail.setText("");
    }

    private void generateId() {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT student_id FROM Student ORDER BY student_id DESC Limit 1");
            resultSet.next();
            int lastId = Integer.parseInt(resultSet.getString(1).replace("S", "")) + 1;
            lblId.setText(String.format("S%03d", lastId));
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void deleteStudent() {
        try {
            if (CrudUtil.execute("DELETE FROM Student WHERE student_id=?", lblId.getText())) {
                new Alert(Alert.AlertType.INFORMATION, "Deleted!").show();
                clearData();
                loadTable();
                generateId();
            } else
                new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        clearData();
        loadTable();
    }

    private void loadTable() {
        btnDelete.setDisable(true);
        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("email"));
        tblStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblStudent.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("nic"));

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

        tblStudent.setItems(students);
    }

    private void updateStudent() {
        try {
            if (CrudUtil.execute("UPDATE Student SET student_name=?, email=?, contact=?, address=?, nic=? WHERE student_id=?",
                    txtName.getText(),
                    txtEmail.getText(),
                    txtContact.getText(),
                    txtAddress.getText(),
                    txtNic.getText(),
                    lblId.getText())) {
                new Alert(Alert.AlertType.INFORMATION, "Updated!").show();
                loadTable();
                clearData();
            } else
                new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
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
                    txtNic.getText())) {
                new Alert(Alert.AlertType.INFORMATION, "Saved!").show();

                clearData();
                loadTable();
                generateId();
            } else
                new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void txtSearchOKR(KeyEvent keyEvent) {
        if (!txtSearch.getText().equals("")) {
            tblStudent.getItems().clear();
            try {
                ArrayList<Student> students = new ArrayList<>();
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
                for (Student student : students) {
                    if (student.getName().toLowerCase().startsWith(txtSearch.getText().toLowerCase())) {
                        tblStudent.getItems().add(student);
                    }
                }
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        } else
            loadTable();
    }
}
