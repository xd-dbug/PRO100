package csc180.damian.swiney.workplace_incident_report_system.model.typeOfReports;

import csc180.damian.swiney.workplace_incident_report_system.model.Report;
import csc180.damian.swiney.workplace_incident_report_system.model.TypeOfReport;

public class PropertyDamage extends Report {
    private int propertyDamage;

    public PropertyDamage(String fileName, String associateName, String description, String actionTaken, String status, int propertyDamage) {
        super(fileName, associateName, description, actionTaken, status);
        setPropertyDamage(propertyDamage);
        setTypeOfReport(TypeOfReport.PROPERTY_DAMAGE);
    }

    public int getPropertyDamage() {
        return propertyDamage;
    }
    public void setPropertyDamage(int propertyDamage) {
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
