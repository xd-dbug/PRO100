package csc180.damian.swiney.workplace_incident_report_system.model;

import java.sql.*;

public class DataBaseManager {
    private static final String HOST = System.getenv().getOrDefault("DB_HOST", "127.0.0.1");
    private static final String PORT = System.getenv().getOrDefault("DB_PORT", "11433"); // your compose port
    private static final String DB   = System.getenv().getOrDefault("DB_NAME", "WIRMS");
    private static final String USER = System.getenv().getOrDefault("DB_USER", "sa");         // or wirms_user
    private static final String PASS = System.getenv().getOrDefault("DB_PASSWORD", "WIRMS101!!"); // or WirmsStrong!1

    // SQL Server JDBC URL uses semicolons for properties (not ?& style)
    private static final String URL = String.format(
            "jdbc:sqlserver://%s:%s;databaseName=%s;encrypt=true;trustServerCertificate=true",
            HOST, PORT, DB
    );

    /** Open a new connection. Remember to close it in a try-with-resources. */
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public static boolean verifyUser(String username, String password){
        String sql = "SELECT * FROM users WHERE Username=? AND Password=?";
        try{
            PreparedStatement stmt = connect().prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }catch (SQLException e){
            System.out.println("Unable to verify user please check login information");
        }
        return false;

    }



}
