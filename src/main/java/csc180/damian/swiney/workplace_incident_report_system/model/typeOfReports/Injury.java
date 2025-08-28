package csc180.damian.swiney.workplace_incident_report_system.model.typeOfReports;

import csc180.damian.swiney.workplace_incident_report_system.model.Report;
import csc180.damian.swiney.workplace_incident_report_system.model.TypeOfReport;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class Injury extends Report {
    private final SimpleStringProperty injuryType = new SimpleStringProperty();
    private final SimpleBooleanProperty hospitalized = new SimpleBooleanProperty();

    public Injury(int reportID, String fileName, int employeeID, String description, String actionTaken, String injuryType, boolean hospitalized, String status, LocalDate dateOccurred) {
        super(reportID, fileName, employeeID, description, actionTaken, status, dateOccurred);
        setInjuryType(injuryType);
        setHospitalized(hospitalized);
        setTypeOfReport(TypeOfReport.INJURY);
    }

    // Getters
    public String getInjuryType() { return injuryType.get(); }
    public boolean isHospitalized() { return hospitalized.get(); }

    // Setters
    public void setInjuryType(String injuryType) { this.injuryType.set(injuryType); }
    public void setHospitalized(boolean hospitalized) { this.hospitalized.set(hospitalized); }

    // Properties for JavaFX binding
    public SimpleStringProperty injuryTypeProperty() { return injuryType; }
    public SimpleBooleanProperty hospitalizedProperty() { return hospitalized; }
}
