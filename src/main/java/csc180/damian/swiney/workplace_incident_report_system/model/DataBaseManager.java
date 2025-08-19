package csc180.damian.swiney.workplace_incident_report_system.model;

import java.sql.Connection;

public class DataBaseManager {
    public DataBaseManager() {
        String connectionUrl =
                "jdbc:sqlserver://localhost:1433;"
                        + "database=WIRMS;"
                        + "user=sa;"
                        + "password=WIRMS101!!;"
                        + "loginTimeout=30;";
    }

    public static Connection connect(){

    }



}
