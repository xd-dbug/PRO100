package csc180.damian.swiney.workplace_incident_report_system;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ReportsPageController {
    @FXML
    public Label ReportsPaneTitle;


    @FXML
    public void onPlusClicked() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("add-report-view.fxml"));
        Parent root = loader.load();

        Stage owner = (Stage) ReportsPaneTitle.getScene().getWindow();
        Stage dialog = new Stage();
        dialog.setTitle("Add New Report");
        dialog.initOwner(owner);
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.setScene(new Scene(root, 300, 400));
        dialog.showAndWait();
    }

}
