package csc180.damian.swiney.workplace_incident_report_system.model.typeOfReports;

import csc180.damian.swiney.workplace_incident_report_system.model.Report;
import csc180.damian.swiney.workplace_incident_report_system.model.TypeOfReport;
import javafx.beans.property.SimpleIntegerProperty;

import java.time.LocalDate;

public class PropertyDamage extends Report {
    private final SimpleIntegerProperty propertyDamage = new SimpleIntegerProperty();

    public PropertyDamage(int reportID, String fileName, int employeeID, String description,
                          String actionTaken, int propertyDamage, String status, LocalDate dateOccurred) {
        super(reportID, fileName, employeeID, description, actionTaken, status, dateOccurred);
        this.propertyDamage.set(propertyDamage);
        setTypeOfReport(TypeOfReport.PROPERTY_DAMAGE);
    }

    public int getPropertyDamage() { return propertyDamage.get(); }
    public void setPropertyDamage(int value) { propertyDamage.set(value); }
    public SimpleIntegerProperty propertyDamageProperty() { return propertyDamage; }
}
