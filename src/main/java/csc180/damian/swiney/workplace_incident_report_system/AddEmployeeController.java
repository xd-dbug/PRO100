package csc180.damian.swiney.workplace_incident_report_system;

import csc180.damian.swiney.workplace_incident_report_system.model.DataBaseManager;
import csc180.damian.swiney.workplace_incident_report_system.model.Employee;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;

public class AddEmployeeController {
    @FXML
    public TextField firstNameInputField;
    @FXML
    public TextField lastNameInputField;
    @FXML
    public TextField departmentInputField;


    private String firstName;
    private String lastName;
    private String department;

    private final DataBaseManager dataBaseManager = new DataBaseManager();

    private EmployeesPageController employeesPageController;

    public void setMainController(EmployeesPageController employeesPageController) {
        this.employeesPageController = employeesPageController;
    }



    @FXML
    private void onOk() throws SQLException {
        firstName=firstNameInputField.getText().strip();
        lastName=lastNameInputField.getText().strip();
        department=departmentInputField.getText().strip();

        if (firstName.isEmpty() || lastName.isEmpty() || department.isEmpty()) {
            showEmptyFieldsAlert();
        } else {
            Employee newEmployee = dataBaseManager.addEmployee(firstName,lastName,department);
            if (newEmployee != null && employeesPageController != null) {
                employeesPageController.addEmployeeToTable(newEmployee);
            }
            close();
        }

    }

    @FXML
    private void onCancel(){
        firstName=null;
        lastName=null;
        department=null;
        close();
    }


    private void showEmptyFieldsAlert() {
        Alert alert  = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Submission Error");
        alert.setHeaderText("Empty Fields Detected");
        alert.setContentText("Please fill out all the fields");
        alert.showAndWait();
    }


    private void close(){
        Stage stage = (Stage) lastNameInputField.getScene().getWindow();
        stage.close();
    }
}
