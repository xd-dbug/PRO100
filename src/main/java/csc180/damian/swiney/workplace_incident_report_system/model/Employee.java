package csc180.damian.swiney.workplace_incident_report_system.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Employee {

    private SimpleIntegerProperty employeeId;
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty department;


    public Employee(int employeeId, String firstName, String lastName, String department) {
        this.employeeId = new SimpleIntegerProperty(employeeId);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.department = new SimpleStringProperty(department);
    }

    // Getters and Setters
    public int getEmployeeId() {
        return employeeId.get();
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId.set(employeeId);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getDepartment() {
        return department.get();
    }

    public void setDepartment(String department) {
        this.department.set(department);
    }
}