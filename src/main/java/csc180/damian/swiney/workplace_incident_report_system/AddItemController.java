package csc180.damian.swiney.workplace_incident_report_system;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddItemController {
    @FXML
    private TextField inputField;
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
