package csc180.damian.swiney.workplace_incident_report_system.controller;



import csc180.damian.swiney.workplace_incident_report_system.model.Report;
import csc180.damian.swiney.workplace_incident_report_system.views.Console;
import csc180.damian.swiney.workplace_incident_report_system.views.View;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Runner {
    List<Report> reports = new ArrayList<>();
    View view = new View(reports);

    public void run(){
        boolean running = true;
        try{
            while (running) {
                    File reportFile = new File("reports.txt");
                    if (!reportFile.exists()) {
                        reportFile.createNewFile();
                    }

                int choice = Console.getIntInput("""
                        1. Add new report
                        2. Show all reports
                        3. Find report by ID
                        4. Update status in report
                        5. Save report to file
                        6. Load File (DOES NOT WORK)
                        7. Exit
                        """);
                switch (choice) {
                    case 1:
                        int reportChoice = Console.getIntInput("""
                                1. Injury
                                2. NearMiss
                                3. ProductDamage
                                4. PropertyDamage
                                """);
                        addReport(makeReport(reportChoice));
                        break;
                    case 2:
                        printAllReports();
                        break;
                    case 3:
                        printByID();
                        break;
                    case 4:
                        updateStatus();
                        break;
                    case 5:
                        boolean saveChoice = Console.getBooleanInput("Would you like to save?", "Y", "N");
                        if (saveChoice) {
                            writeToFile(reports.toString(), reportFile);
                        } else {
                            break;
                        }
                    case 6:
                        break;
                    case 7:
                        running = false;
                }

            }

        }catch(IOException e){
            System.out.println("An error occurred while creating the file");
            e.printStackTrace();
        }
    }

    private void printAllReports() {
        view.printAll();
    }

    private void addReport(Report report) {
        reports.add(report);
    }

    private void printByID() {
        view.printReport();
    }

    private void updateStatus() {
        view.updateStatus();
    }

    private Report makeReport(int choice) {
        return view.generateReport(choice);
    }

    private void writeToFile(String content, File file){
        try{
            FileWriter writer = new FileWriter(file);
            writer.write(content);
            writer.close();
        }catch (IOException e){
            System.out.println("An error occured");
            e.printStackTrace();
        }
    }
    //region
    private String readFromFile(File file){
        try{
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                return scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    //endregion

}
