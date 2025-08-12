package views;

import model.Report;
import model.typeOfReports.Injury;
import model.typeOfReports.NearMiss;
import model.typeOfReports.ProductDamage;
import model.typeOfReports.PropertyDamage;

import java.util.List;

public class View {
    private List<Report> reports;

    public View(List<Report> reports) {
        this.reports = reports;
    }

    public void printAll() {
        for (Object o : reports) {
            Console.write(o.toString());
        }
    }

    public void printReport() {
        int id = Console.getIntInput("Enter ID of Report you would like to see?: ");
        for (Report report : reports) {
            if (report.getId() == id) {
                Console.write(report.toString());
            } else {
                throw new IndexOutOfBoundsException();
            }
        }
    }

    public Report generateReport(int choice) {
        String fileName = Console.getStringInput("Enter Report Filename: ");
        String associateName = Console.getStringInput("Enter Report Associate Name: ");
        String actionTaken = Console.getStringInput("Enter Countermeasure implemented: ");
        String description = Console.getStringInput("Enter Report Description: ");
        String status = Console.getStringInput("Enter Report Status: ");
        switch(choice){
            case 1:
                String injuryType = Console.getStringInput("Enter Type of Injury: ");
                boolean inHospital = Console.getBooleanInput("Is Associate in the hospital?: ", "Yes", "No");
                return new Injury(fileName, associateName, actionTaken, description, injuryType, inHospital, status);
            case 2:
                return new NearMiss(fileName, associateName, actionTaken, description, status);

            case 3:
                int productDamage = Console.getIntInput("Enter Product Damage: ");
                return new ProductDamage(fileName, associateName, description, actionTaken, status, productDamage);

            case 4:
                int propertyDamage = Console.getIntInput("Enter Property Damage: ");
                return new PropertyDamage(fileName, associateName, description, actionTaken, status, propertyDamage);
        }
        return null;
    }

    public void updateStatus(){
        int id = Console.getIntInput("Enter Report ID: ");
        String update = Console.getStringInput("Enter New Status: ");
        for (Report report : reports) {
            if (report.getId() == id) {
                report.updateStatus(update);
            }
        }
    }


}
