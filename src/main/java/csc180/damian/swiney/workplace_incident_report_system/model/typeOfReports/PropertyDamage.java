package csc180.damian.swiney.workplace_incident_report_system.model.typeOfReports;

import csc180.damian.swiney.workplace_incident_report_system.model.Report;
import csc180.damian.swiney.workplace_incident_report_system.model.TypeOfReport;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class PropertyDamage extends Report {
    private SimpleIntegerProperty propertyDamage;

    public PropertyDamage(String fileName, int employeeID, String description, String actionTaken, String status, SimpleIntegerProperty propertyDamage) {
        super(fileName, employeeID, description, actionTaken, status);
        setPropertyDamage(propertyDamage);
        setTypeOfReport(TypeOfReport.PROPERTY_DAMAGE);
    }

    public SimpleIntegerProperty getPropertyDamage() {
        return propertyDamage;
    }
    public void setPropertyDamage(SimpleIntegerProperty propertyDamage) {
        this.propertyDamage = propertyDamage;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Report Type: ").append(getTypeOfReport()).append("\n");
        sb.append("Property Damage Amount: ").append(getPropertyDamage()).append("$").append("\n");
        sb.append(super.toString());
        return sb.toString();
    }
}
