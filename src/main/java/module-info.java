module csc180.damian.swiney.workplace_incident_report_system {
    requires javafx.controls;
    requires javafx.fxml;


    opens csc180.damian.swiney.workplace_incident_report_system to javafx.fxml;
    exports csc180.damian.swiney.workplace_incident_report_system;
}