module csc180.damian.swiney.workplace_incident_report_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;



    requires java.desktop;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;


    opens csc180.damian.swiney.workplace_incident_report_system to javafx.fxml;
    exports csc180.damian.swiney.workplace_incident_report_system;
    exports  csc180.damian.swiney.workplace_incident_report_system.model;
    exports  csc180.damian.swiney.workplace_incident_report_system.model.typeOfReports;
}