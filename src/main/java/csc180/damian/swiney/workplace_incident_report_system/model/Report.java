package csc180.damian.swiney.workplace_incident_report_system.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public abstract class Report {
    private final SimpleIntegerProperty reportID = new SimpleIntegerProperty();
    private final SimpleStringProperty title = new SimpleStringProperty();
    private final SimpleIntegerProperty employeeID = new SimpleIntegerProperty();
    private SimpleObjectProperty<TypeOfReport> typeOfReport = new SimpleObjectProperty<>();
    private final SimpleStringProperty description = new SimpleStringProperty();
    private final SimpleStringProperty actionTaken = new SimpleStringProperty();
    private final SimpleStringProperty status = new SimpleStringProperty();
    private final SimpleObjectProperty<LocalDate> dateOccured = new SimpleObjectProperty<>();


    public Report(int reportID, String title, int employeeID, String description, String actionTaken, String status, LocalDate dateOccured) {
        this.reportID.set(reportID);
        this.title.set(title);
        this.employeeID.set(employeeID);
        this.description.set(description);
        this.actionTaken.set(actionTaken);
        this.status.set(status);
        this.dateOccured.set(dateOccured);
    }

    // Getters
    public int getReportID() { return reportID.get(); }
    public String getTitle() { return title.get(); }
    public int getEmployeeID() { return employeeID.get(); }
    public TypeOfReport getTypeOfReport() { return typeOfReport.get(); }
    public String getDescription() { return description.get(); }
    public String getActionTaken() { return actionTaken.get(); }
    public String getStatus() { return status.get(); }
    public LocalDate getDateOccured() {
        return dateOccured.get();
    }

    // Setters
    public void setReportID(int id) { this.reportID.set(id); }
    protected void setTypeOfReport(TypeOfReport typeOfReport) { this.typeOfReport.set(typeOfReport); }
    public void setDateOccured(LocalDate date) {
        this.dateOccured.set(date);
    }

    // Properties for JavaFX binding
    public SimpleIntegerProperty reportIDProperty() { return reportID; }
    public SimpleStringProperty titleProperty() { return title; }
    public SimpleIntegerProperty employeeIDProperty() { return employeeID; }
    public SimpleStringProperty descriptionProperty() { return description; }
    public SimpleStringProperty actionTakenProperty() { return actionTaken; }
    public SimpleStringProperty statusProperty() { return status; }
    public SimpleObjectProperty<TypeOfReport> typeOfReportProperty() { return typeOfReport; }
    public SimpleObjectProperty<LocalDate> DateProperty() {return dateOccured;}

}
