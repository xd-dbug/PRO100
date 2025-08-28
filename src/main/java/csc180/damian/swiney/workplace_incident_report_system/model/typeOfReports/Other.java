package csc180.damian.swiney.workplace_incident_report_system.model.typeOfReports;

import csc180.damian.swiney.workplace_incident_report_system.model.Report;
import csc180.damian.swiney.workplace_incident_report_system.model.TypeOfReport;

import java.time.LocalDate;

public class Other extends Report {

    public Other(int reportID, String fileName, int employeeID, String description, String actionTaken, String status, LocalDate dateOccurred) {
        super(reportID, fileName, employeeID, description, actionTaken, status, dateOccurred);
        setTypeOfReport(TypeOfReport.OTHER);
    }
}
