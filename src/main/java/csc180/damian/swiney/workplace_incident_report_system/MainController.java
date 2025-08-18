package csc180.damian.swiney.workplace_incident_report_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private StackPane contentPane;

    @FXML
    public void onEmployeesPageButtonPress(ActionEvent event) throws Exception {
        loadPage("employees-view.fxml");

    }

    @FXML
    public void onReportsPageButtonPress(ActionEvent event) throws Exception {
        loadPage("reports-view.fxml");

    }


    private void loadPage(String pageName) {
        try {
            Parent page = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(pageName)));
            contentPane.getChildren().setAll(page);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadPage("employees-view.fxml");
    }
}
