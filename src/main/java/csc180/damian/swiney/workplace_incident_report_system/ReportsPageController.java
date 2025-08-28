package csc180.damian.swiney.workplace_incident_report_system;

import csc180.damian.swiney.workplace_incident_report_system.model.DataBaseManager;
import csc180.damian.swiney.workplace_incident_report_system.model.Report;
import csc180.damian.swiney.workplace_incident_report_system.model.TypeOfReport;
import csc180.damian.swiney.workplace_incident_report_system.model.typeOfReports.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Date;
import java.sql.SQLException;

public class ReportsPageController
{
    public Label ReportsPaneTitle;
    public TabPane reportTabs;

    public TableView<Report> allReportsTable;
    public TableColumn<Report, Integer> allReportsIdColumn;
    public TableColumn<Report, String> allReportsTitleColumn;
    public TableColumn<Report, TypeOfReport> allReportsIncidentTypeColumn;
    public TableColumn<Report, Integer> allReportsEmployeeIDColumn;
    public TableColumn<Report, Date> allReportsDateColumn;
    public TableColumn<Report, String> allReportsDescriptionColumn;
    public TableColumn<Report, String> allReportsActionTakenColumn;
    public TableColumn<Report, String> allReportsStatusColumn;

    public TableView<Injury> injuriesTable;
    public TableColumn<Injury, Integer> injuriesIdColumn;
    public TableColumn<Injury, String> injuriesTitleColumn;
    public TableColumn<Injury, TypeOfReport> injuriesIncidentTypeColumn;
    public TableColumn<Injury, Integer>injuriesEmployeeIDColumn;
    public TableColumn<Injury, Date> injuriesDateColumn;
    public TableColumn<Injury, String>injuriesInjuryTypeColumn;
    public TableColumn<Injury, Boolean> injuriesHospitalizedColumn;
    public TableColumn<Injury, String> injuriesDescriptionColumn;
    public TableColumn<Injury, String> injuriesActionTakenColumn;
    public TableColumn<Injury, String> injuriesStatusColumn;

    public TableView<NearMiss> nearMissesTable;
    public TableColumn<NearMiss, Integer> nearMissesIdColumn;
    public TableColumn<NearMiss, String> nearMissesTitleColumn;
    public TableColumn<NearMiss, TypeOfReport> nearMissesIncidentTypeColumn;
    public TableColumn<NearMiss, Integer> nearMissesEmployeeIDColumn;
    public TableColumn<NearMiss, Date> nearMissesDateColumn;
    public TableColumn<NearMiss, String> nearMissesDescriptionColumn;
    public TableColumn<NearMiss, String> nearMissesActionTakenColumn;
    public TableColumn<NearMiss, String> nearMissesStatusColumn;

    public TableView<ProductDamage> productDamageTable;
    public TableColumn<ProductDamage, Integer> productDamageIdColumn;
    public TableColumn<ProductDamage, String> productDamageTitleColumn;
    public TableColumn<ProductDamage, TypeOfReport> productDamageIncidentTypeColumn;
    public TableColumn<ProductDamage, Integer> productDamageEmployeeIDColumn;
    public TableColumn<ProductDamage, Date> productDamageDateColumn;
    public TableColumn<ProductDamage, Integer> productDamageProdDamageColumn;
    public TableColumn<ProductDamage, String> productDamageDescriptionColumn;
    public TableColumn<ProductDamage, String> productDamageActionTakenColumn;
    public TableColumn<ProductDamage, String> productDamageStatusColumn;

    public TableView<PropertyDamage> propertyDamageTable;
    public TableColumn<PropertyDamage, Integer> propertyDamageIdColumn;
    public TableColumn<PropertyDamage, String> propertyDamageTitleColumn;
    public TableColumn<PropertyDamage, TypeOfReport> propertyDamageIncidentTypeColumn;
    public TableColumn<PropertyDamage, Integer> propertyDamageEmployeeIDColumn;
    public TableColumn<PropertyDamage, Date> propertyDamageDateColumn;
    public TableColumn<PropertyDamage, Integer> propertyDamagePropDamageColumn;
    public TableColumn<PropertyDamage, String> propertyDamageDescriptionColumn;
    public TableColumn<PropertyDamage, String> propertyDamageActionTakenColumn;
    public TableColumn<PropertyDamage, String> propertyDamageStatusColumn;

    public TableView<Other> otherTable;
    public TableColumn<Other, Integer> otherIdColumn;
    public TableColumn<Other, String> otherTitleColumn;
    public TableColumn<Other, TypeOfReport> otherIncidentTypeColumn;
    public TableColumn<Other, Integer> otherEmployeeIDColumn;
    public TableColumn<Other, Date> otherDateColumn;
    public TableColumn<Other, String> otherDescriptionColumn;
    public TableColumn<Other, String> otherActionTakenColumn;
    public TableColumn<Other, String> otherStatusColumn;


    ObservableList<Report> reportList = FXCollections.observableArrayList(DataBaseManager.getAllReports());
    ObservableList<Injury> injuryList = FXCollections.observableArrayList();
    ObservableList<NearMiss> nearMissList = FXCollections.observableArrayList();
    ObservableList<ProductDamage> productDamageList = FXCollections.observableArrayList();
    ObservableList<PropertyDamage> propertyDamageList = FXCollections.observableArrayList();
    ObservableList<Other> otherList = FXCollections.observableArrayList();
    DataBaseManager db = new DataBaseManager();


    @FXML
    public void initialize() throws SQLException {
        allReportsIdColumn.setCellValueFactory(new PropertyValueFactory<>("reportID"));
        allReportsTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        allReportsIncidentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("typeOfReport"));
        allReportsEmployeeIDColumn.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        allReportsDateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        allReportsDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        allReportsActionTakenColumn.setCellValueFactory(new PropertyValueFactory<>("actionTaken"));
        allReportsStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        injuriesIdColumn.setCellValueFactory(new PropertyValueFactory<>("reportID"));
        injuriesTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        injuriesIncidentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("typeOfReport"));
        injuriesEmployeeIDColumn.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        injuriesDateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        injuriesInjuryTypeColumn.setCellValueFactory(new PropertyValueFactory<>("injuryType"));
        injuriesHospitalizedColumn.setCellValueFactory(new PropertyValueFactory<>("hospitalized"));
        injuriesDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        injuriesActionTakenColumn.setCellValueFactory(new PropertyValueFactory<>("actionTaken"));
        injuriesStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        nearMissesIdColumn.setCellValueFactory(new PropertyValueFactory<>("reportID"));
        nearMissesTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        nearMissesIncidentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("typeOfReport"));
        nearMissesEmployeeIDColumn.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        nearMissesDateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        nearMissesDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        nearMissesActionTakenColumn.setCellValueFactory(new PropertyValueFactory<>("actionTaken"));
        nearMissesStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        productDamageIdColumn.setCellValueFactory(new PropertyValueFactory<>("reportID"));
        productDamageTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        productDamageIncidentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("typeOfReport"));
        productDamageEmployeeIDColumn.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        productDamageDateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        productDamageProdDamageColumn.setCellValueFactory(new PropertyValueFactory<>("ProductDamage"));
        productDamageDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        productDamageActionTakenColumn.setCellValueFactory(new PropertyValueFactory<>("actionTaken"));
        productDamageStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        propertyDamageIdColumn.setCellValueFactory(new PropertyValueFactory<>("reportID"));
        propertyDamageTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        propertyDamageIncidentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("typeOfReport"));
        propertyDamageEmployeeIDColumn.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        propertyDamageDateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        propertyDamagePropDamageColumn.setCellValueFactory(new PropertyValueFactory<>("PropertyDamage"));
        propertyDamageDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        propertyDamageActionTakenColumn.setCellValueFactory(new PropertyValueFactory<>("actionTaken"));
        propertyDamageStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        otherIdColumn.setCellValueFactory(new PropertyValueFactory<>("reportID"));
        otherTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        otherIncidentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("typeOfReport"));
        otherEmployeeIDColumn.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        otherDateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        otherDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        otherActionTakenColumn.setCellValueFactory(new PropertyValueFactory<>("actionTaken"));
        otherStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadReports();
        allReportsTable.setItems(reportList);
        injuriesTable.setItems(injuryList);
        nearMissesTable.setItems(nearMissList);
        productDamageTable.setItems(productDamageList);
        propertyDamageTable.setItems(propertyDamageList);
        otherTable.setItems(otherList);
    }



    public void loadReports()
    {
        reportList.clear();
        reportList.addAll(DataBaseManager.getAllReports());

        injuryList.clear();
        nearMissList.clear();
        productDamageList.clear();
        propertyDamageList.clear();
        otherList.clear();

        for (Report report : reportList) {
            if (report instanceof Injury) {
                injuryList.add((Injury) report);
            } else if (report instanceof NearMiss) {
                nearMissList.add((NearMiss) report);
            } else if (report instanceof ProductDamage) {
                productDamageList.add((ProductDamage) report);
            } else if (report instanceof PropertyDamage) {
                propertyDamageList.add((PropertyDamage) report);
            } else if (report instanceof Other) {
                otherList.add((Other) report);
            }
        }
    }

    public void addReportToTable(Report report){
        reportList.add(report);
    }

    @FXML
    public void onPlusClicked() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("add-report-view.fxml"));
        Parent root = loader.load();

        AddReportController controller = loader.getController();
        controller.setController(this);

        Stage owner = (Stage) ReportsPaneTitle.getScene().getWindow();
        Stage dialog = new Stage();
        dialog.setTitle("Add New Report");
        dialog.initOwner(owner);
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.setScene(new Scene(root, 500, 600));
        dialog.showAndWait();

        loadReports();
    }


}
