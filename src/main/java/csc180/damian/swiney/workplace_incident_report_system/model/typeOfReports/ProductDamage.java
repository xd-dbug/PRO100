package csc180.damian.swiney.workplace_incident_report_system.model.typeOfReports;

import model.Report;

public class ProductDamage extends Report {
    private int damage;
    public ProductDamage(String fileName, String associateName, String description, String actionTaken, String status, int damage) {
        super(fileName, associateName, description, actionTaken, status);
        setProductDamage(damage);
    }

    public int getProductDamage() {
        return damage;
    }
    public void setProductDamage(int propertyDamage) {
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
