package csc180.damian.swiney.workplace_incident_report_system;

import csc180.damian.swiney.workplace_incident_report_system.model.DataBaseManager;
import csc180.damian.swiney.workplace_incident_report_system.model.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.*;

public class EmployeesPageController {

    @FXML
    public Label EmployeePaneTitle;
    @FXML
    public TableView<Employee> employeeTable;
    @FXML
    public TableColumn<Employee, Integer> idColumn;
    @FXML
    public TableColumn<Employee, String> firstNameColumn;
    @FXML
    public TableColumn<Employee, String> lastNameColumn;
    @FXML
    public TableColumn<Employee, String> departmentColumn;

    private ObservableList<Employee> employees = FXCollections.observableArrayList();
    private final DataBaseManager db = new DataBaseManager();



    @FXML
    public void initialize() throws SQLException {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));

        loadDataFromDatabase();
        employeeTable.setItems(employees);

    }

    @FXML
    public void onPlusClicked() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("add-employee-view.fxml"));
        Parent root = loader.load();

        AddEmployeeController controller = loader.getController();
        controller.setMainController(this);

        Stage owner = (Stage) EmployeePaneTitle.getScene().getWindow();
        Stage dialog = new Stage();
        dialog.setTitle("Add New Employee");
        dialog.initOwner(owner);
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.setScene(new Scene(root, 300, 400));
        dialog.showAndWait();
    }



    public void loadDataFromDatabase() throws SQLException {
        employees.clear();
        employees.addAll(db.getAllEmployees());

    }

    public void addEmployeeToTable(Employee employee) {
        employeeTable.getItems().add(employee);
    }

}