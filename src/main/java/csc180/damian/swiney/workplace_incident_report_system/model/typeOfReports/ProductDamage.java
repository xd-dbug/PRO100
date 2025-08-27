package csc180.damian.swiney.workplace_incident_report_system.model.typeOfReports;

import csc180.damian.swiney.workplace_incident_report_system.model.Report;
import csc180.damian.swiney.workplace_incident_report_system.model.TypeOfReport;
import javafx.beans.property.SimpleIntegerProperty;

import java.time.LocalDate;

public class ProductDamage extends Report {
    private final SimpleIntegerProperty productDamage = new SimpleIntegerProperty();

    public ProductDamage(int reportID, String fileName, int employeeID, String description,
                         String actionTaken, int productDamage, String status, LocalDate dateOccured) {
        super(reportID, fileName, employeeID, description, actionTaken, status, dateOccured);
        this.productDamage.set(productDamage);
        setTypeOfReport(TypeOfReport.PRODUCT_DAMAGE);
    }

    public int getProductDamage() { return productDamage.get(); }
    public void setProductDamage(int value) { productDamage.set(value); }
    public SimpleIntegerProperty productDamageProperty() { return productDamage; }
}
