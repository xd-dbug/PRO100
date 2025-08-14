package csc180.damian.swiney.workplace_incident_report_system.views;

import csc180.damian.swiney.workplace_incident_report_system.model.Employee;
import csc180.damian.swiney.workplace_incident_report_system.model.Report;
import csc180.damian.swiney.workplace_incident_report_system.model.typeOfReports.*;
import csc180.damian.swiney.workplace_incident_report_system.model.Report;
import csc180.damian.swiney.workplace_incident_report_system.model.typeOfReports.Injury;
import csc180.damian.swiney.workplace_incident_report_system.model.typeOfReports.NearMiss;
import csc180.damian.swiney.workplace_incident_report_system.model.typeOfReports.ProductDamage;
import csc180.damian.swiney.workplace_incident_report_system.model.typeOfReports.PropertyDamage;


import java.util.List;

public class View {
    private List<Report> reports;
    private List<Employee> employees;

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

            case 5:
                return new Other(fileName, associateName, actionTaken, description, status);
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

    public Employee addEmployee(){
        int id = Console.getIntInput("Enter Employee ID: ");
        String firstName = Console.getStringInput("Enter Employee First Name: ");
        String lastName = Console.getStringInput("Enter Employee Last Name: ");
        return new Employee(id, firstName, lastName);

    }




}
