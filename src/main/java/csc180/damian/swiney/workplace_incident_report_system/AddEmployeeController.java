package csc180.damian.swiney.workplace_incident_report_system;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddEmployeeController {
    @FXML
    public TextField firstNameInputField;
    @FXML
    public TextField lastNameInputField;


    private String firstName;
    private String lastName;

    @FXML
    private void onOk(){
        firstName=firstNameInputField.getText();
        lastName=lastNameInputField.getText();
        close();
    }

    @FXML
    private void onCancel(){
        firstName=null;
        lastName=null;
        close();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    private void close(){
        Stage stage = (Stage) lastNameInputField.getScene().getWindow();
        stage.close();
    }
}
