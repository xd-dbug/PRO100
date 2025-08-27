package csc180.damian.swiney.workplace_incident_report_system.model.typeOfReports;

import csc180.damian.swiney.workplace_incident_report_system.model.Report;
import csc180.damian.swiney.workplace_incident_report_system.model.TypeOfReport;

import java.time.LocalDate;

public class NearMiss extends Report {

    public NearMiss(int reportID, String fileName, int employeeID, String description, String actionTaken, String status, LocalDate dateOccured) {
        super(reportID, fileName, employeeID, description, actionTaken, status, dateOccured);
        setTypeOfReport(TypeOfReport.NEAR_MISS);
    }
}
