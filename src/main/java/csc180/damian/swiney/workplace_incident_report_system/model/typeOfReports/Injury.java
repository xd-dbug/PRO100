package csc180.damian.swiney.workplace_incident_report_system.model.typeOfReports;


import csc180.damian.swiney.workplace_incident_report_system.model.Report;
import csc180.damian.swiney.workplace_incident_report_system.model.TypeOfReport;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class Injury extends Report {
    private SimpleStringProperty injuryType;
    private SimpleBooleanProperty isHospitalized;

    public Injury(String fileName, int employeeID, String actionTaken, String description, SimpleStringProperty injuryType, SimpleBooleanProperty isHospitalized, String status) {
        super(fileName, employeeID , actionTaken, description, status);
        setInjuryType(injuryType);
        setHospitalized(isHospitalized);
        setTypeOfReport(TypeOfReport.INJURY);
    }

    public SimpleStringProperty getInjuryType() {
        return injuryType;
    }
    public void setInjuryType(SimpleStringProperty injuryType) {
        this.injuryType = injuryType;
    }
    public SimpleBooleanProperty getIsHospitalized() {
        return isHospitalized;
    }
    public void setHospitalized(SimpleBooleanProperty hospitalized) {
        isHospitalized = hospitalized;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Report Type: ").append(getTypeOfReport()).append("\n");
        sb.append("Injury Type: ").append(getInjuryType()).append("\n");
        sb.append("Hospitalized: ").append(getIsHospitalized()).append("\n");
        sb.append(super.toString());
        return sb.toString();
    }

}
