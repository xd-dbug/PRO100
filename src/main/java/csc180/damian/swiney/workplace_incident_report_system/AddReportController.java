package csc180.damian.swiney.workplace_incident_report_system;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddReportController {

    @FXML
    private TextField inputField;
    @FXML
    public DatePicker incidentDatePicker;

    private String value;

    @FXML
    private void onOk(){
        value=inputField.getText();
        close();
    }

    @FXML
    private void onCancel(){
        value=null;
        close();
    }

    public String getValue(){
        return value;
    }

    private void close(){
        Stage stage = (Stage) inputField.getScene().getWindow();
        stage.close();
    }
}
