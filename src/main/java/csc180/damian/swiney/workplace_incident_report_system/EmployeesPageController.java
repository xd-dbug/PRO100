package csc180.damian.swiney.workplace_incident_report_system;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import csc180.damian.swiney.workplace_incident_report_system.model.DataBaseManager;
import csc180.damian.swiney.workplace_incident_report_system.model.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    @FXML
    public TextField searchField;
    public TextField deleteIdField;


    private ObservableList<Employee> employees = FXCollections.observableArrayList();
    private final DataBaseManager db = new DataBaseManager();

    FilteredList<Employee> filteredData = new FilteredList<>(employees, p -> true);


    @FXML
    public void initialize() throws SQLException {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));

        loadDataFromDatabase();
        employeeTable.setItems(employees);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(employee -> {
                // If no filter text, display all employees
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (employee.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (employee.getLastName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (employee.getDepartment().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<Employee> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(employeeTable.comparatorProperty());

        employeeTable.setItems(sortedData);
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
        employees.add(employee);
    }



    public void onDelClick() throws SQLException {
        String idText = deleteIdField.getText();
        if (idText == null || idText.trim().isEmpty()) {
            System.out.println("Please enter an Employee ID.");
            return;
        }
        try {
            int employeeId = Integer.parseInt(idText);
            DataBaseManager.deleteEmployee(employeeId);
            System.out.println("Deleting employee with ID: " + employeeId);

            employeeTable.setItems(FXCollections.observableArrayList(db.getAllEmployees()));

            deleteIdField.clear();
        } catch (NumberFormatException e) {
            System.out.println("Invalid Employee ID. Must be a number.");
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            deleteErrorAlert();
        }
    }

    private void deleteErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Employee Deletion Error");
        alert.setHeaderText("Could not delete employee. Please try again.");
        alert.setContentText("Ensure employee is not tied to an existing report.");
        alert.showAndWait();
    }


}