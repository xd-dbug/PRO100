package csc180.damian.swiney.workplace_incident_report_system.model.typeOfReports;


import csc180.damian.swiney.workplace_incident_report_system.model.Report;
import javafx.beans.property.SimpleIntegerProperty;

public class ProductDamage extends Report {

    private SimpleIntegerProperty damage;

    public ProductDamage(String fileName, int employeeID, String description, String actionTaken, String status, SimpleIntegerProperty damage) {
        super(fileName, employeeID, description, actionTaken, status);
        setProductDamage(damage);
    }

    public SimpleIntegerProperty getProductDamage() {
        return damage;
    }
    public void setProductDamage(SimpleIntegerProperty propertyDamage) {
        this.damage = propertyDamage;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Report Type: ").append(getTypeOfReport()).append("\n");
        sb.append("Product Damage Amount: ").append(getProductDamage()).append("$").append("\n");
        sb.append(super.toString());
        return sb.toString();
    }
}
