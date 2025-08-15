package csc180.damian.swiney.workplace_incident_report_system;

import csc180.damian.swiney.workplace_incident_report_system.model.Report;
import csc180.damian.swiney.workplace_incident_report_system.model.TypeOfReport;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Date;

public class ReportsPageController {
    @FXML
    public Label ReportsPaneTitle;
    @FXML
    public TableView<Report> reportsTable;
    @FXML
    public TableColumn<Report, Integer> idColumn;
    @FXML
    public TableColumn<Report, String> titleColumn;
    @FXML
    public TableColumn<Report, TypeOfReport> incidentTypeColumn;
    @FXML
    public TableColumn<Report, Integer> employeeIDColumn;
    @FXML
    public TableColumn<Report, Date> dateColumn;
    @FXML
    public TableColumn<Report, String> descriptionColumn;
    @FXML
    public TableColumn<Report, String> actionTakenColumn;
    @FXML
    public TableColumn<Report, String> statusColumn;



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
