package csc180.damian.swiney.workplace_incident_report_system.model;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public abstract class Report {
    private SimpleStringProperty fileName;
    private SimpleIntegerProperty employeeID;
    private TypeOfReport typeOfReport;
    private SimpleStringProperty description;
    private SimpleStringProperty actionTaken;
    private SimpleStringProperty status;


    public Report(String fileName, int employeeID, String description, String actionTaken, String status) {
        setFileName(fileName);
        setEmployeeID(employeeID);
        setDescription(description);
        setActionTaken(actionTaken);
        setStatus(status);
    }


    //region
    public String getFileName() {
        return fileName.get();
    }
    protected void setFileName(String fileName) {this.fileName.set(fileName);}
    public int getEmployee() {
        return employeeID.get();
    }
    protected void setEmployeeID(int employeeID) {
        this.employeeID.set(employeeID);
    }
    public TypeOfReport getTypeOfReport() {
        return typeOfReport;
    }
    protected void setTypeOfReport(TypeOfReport typeOfReport) {
        this.typeOfReport = typeOfReport;
    }
    public String getDescription() {
        return description.get();
    }
    protected void setDescription(String description) {
        this.description.set(description);
    }
    protected void setActionTaken(String actionTaken) {
        this.actionTaken.set(actionTaken);
    }
    public String getActionTaken() {
        return actionTaken.get();
    }
    private void setStatus(String status) {
        this.status.set(status);
    }
    public String getStatus() {
        return status.get();
    }


}
