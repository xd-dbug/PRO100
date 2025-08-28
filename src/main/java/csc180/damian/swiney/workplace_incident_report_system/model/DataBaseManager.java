package csc180.damian.swiney.workplace_incident_report_system.model;

import csc180.damian.swiney.workplace_incident_report_system.model.typeOfReports.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataBaseManager {
    private static final String HOST = System.getenv().getOrDefault("DB_HOST", "127.0.0.1");
    private static final String PORT = System.getenv().getOrDefault("DB_PORT", "11433"); // your compose port
    private static final String DB = System.getenv().getOrDefault("DB_NAME", "WIRMS");
    private static final String USER = System.getenv().getOrDefault("DB_USER", "sa");         // or wirms_user
    private static final String PASS = System.getenv().getOrDefault("DB_PASSWORD", "WIRMS101!!"); // or WirmsStrong!1
    static int reportID;

    // SQL Server JDBC URL uses semicolons for properties (not ?& style)
    private static final String URL = String.format(
            "jdbc:sqlserver://%s:%s;databaseName=%s;encrypt=true;trustServerCertificate=true",
            HOST, PORT, DB
    );

    /**
     * Open a new connection. Remember to close it in a try-with-resources.
     */
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public static boolean verifyUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE Username=? AND Password=?";
        try {
            PreparedStatement stmt = connect().prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Unable to verify user please check login information");
        }
        return false;

    }




    public static Report addInjury(String description,String Title,String IncidentType,String ActionTaken,String Status, boolean Hospitalized, String InjuryType, int employeeID, LocalDate dateOccurred) {
        String InjurySql = "INSERT INTO Injury (InjuryID, Hospitalized, InjuryType) VALUES (?,?,?)";

        addToMainTable(description, Title, IncidentType, ActionTaken, Status, employeeID, dateOccurred);

        try {
            PreparedStatement injuryStmt = connect().prepareStatement(InjurySql);
            injuryStmt.setInt(1, reportID);
            injuryStmt.setBoolean(2, Hospitalized);
            injuryStmt.setString(3, InjuryType);
            injuryStmt.executeUpdate();


        } catch (SQLException e) {
            System.out.println("Unable to add Injury");
        }


        Report injuryReport = new Injury(reportID,Title,employeeID,ActionTaken,description,InjuryType,Hospitalized,Status,dateOccurred);

        return injuryReport;

    }

    public static Report addNearMiss(String description, String Title, String IncidentType, String ActionTaken, String Status, int employeeID, LocalDate dateOccurred){
        String missSql = "INSERT INTO NearMiss (MissID) VALUES (?)";
        addToMainTable(description, Title, IncidentType, ActionTaken, Status, employeeID, dateOccurred);
        try{
            PreparedStatement stmt = connect().prepareStatement(missSql);
            stmt.setInt(1, reportID);
            stmt.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }

        Report nearMissReport = new NearMiss(reportID,Title,employeeID,ActionTaken,description,Status,dateOccurred);

        return nearMissReport;

    }

    public static Report addProductDamage(String description, String Title, String IncidentType, String ActionTaken, String Status, int productDamage, int employeeID, LocalDate dateOccurred) {
        String productSql = "INSERT INTO ProductDamage (ProductID, ProductDamage) VALUES (?,?)";
        addToMainTable(description, Title, IncidentType, ActionTaken, Status, employeeID, dateOccurred);
        try{
            PreparedStatement stmt = connect().prepareStatement(productSql);
            stmt.setInt(1, reportID);
            stmt.setInt(2, productDamage);
            stmt.executeUpdate();

        }catch (SQLException e){
            System.out.println("Unable to add ProductDamage");
        }

        Report productReport = new ProductDamage(reportID,Title,employeeID, description, ActionTaken, productDamage, Status,dateOccurred);
        return productReport;
    }

    public static Report addPropertyDamage(String description,String Title,String IncidentType,String ActionTaken,String Status, int propertyDamage, int employeeID, LocalDate dateOccurred) {
        String propertySql = "INSERT INTO PropertyDamage (PropertyID, PropertyDamage) VALUES (?,?)";
        addToMainTable(description, Title, IncidentType, ActionTaken, Status, employeeID, dateOccurred);
        try{
            PreparedStatement stmt = connect().prepareStatement(propertySql);
            stmt.setInt(1, reportID);
            stmt.setInt(2, propertyDamage);
            stmt.executeUpdate();

        }catch(SQLException e){
            System.out.println("Unable to add PropertyDamage");
        }
        Report propertyReport = new PropertyDamage(reportID,Title,employeeID, description, ActionTaken, propertyDamage, Status,dateOccurred);
        return propertyReport;
    }

    public static Report addOther(String description, String Title, String IncidentType, String ActionTaken, String Status, int employeeID, LocalDate dateOccurred) {
        String missSql = "INSERT INTO Other (OtherID) VALUES (?)";
        addToMainTable(description, Title, IncidentType, ActionTaken, Status, employeeID, dateOccurred);
        try {
            PreparedStatement stmt = connect().prepareStatement(missSql);
            stmt.setInt(1, reportID);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Unable to add Other");
        }

        Report otherReport = new Other(reportID, Title, employeeID, description, ActionTaken, Status, dateOccurred);

        return otherReport;
    }




    public static void addToMainTable(String description, String Title, String IncidentType, String ActionTaken, String Status, int EmployeeID, LocalDate dateOccurred) {
        String MainSql = "INSERT INTO MainTable(Title, EmployeeID, Date, IncidentType, Description, ActionTaken, Status) VALUES (?,?,?,?,?,?,?)";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(MainSql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, Title);
            stmt.setInt(2, EmployeeID);
            stmt.setDate(3, Date.valueOf(dateOccurred));
            stmt.setString(4, IncidentType);
            stmt.setString(5, description);
            stmt.setString(6, ActionTaken);
            stmt.setString(7, Status);

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    reportID = rs.getInt(1);
                } else {
                    throw new SQLException("Unable to get ReportID");
                }
            }

        } catch (SQLException e) {
            System.out.println("Unable to add To MainTable: " + e.getMessage());
        }
    }


    public Employee addEmployee(String firstname, String lastname, String department) throws SQLException {
        String sql = "INSERT INTO employees VALUES (?,?,?)";

        try(Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, firstname);
            stmt.setString(2, lastname);
            stmt.setString(3, department);

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int id = generatedKeys.getInt(1);
                        return new Employee(id, firstname, lastname, department);
                    }
                }
            }
        }
        return null;
    }

    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        try{
            connect();
            PreparedStatement stmt = connect().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("employeeID");
                String firstname = rs.getString("FirstName");
                String lastname = rs.getString("LastName");
                String department = rs.getString("Department");

                employees.add(new Employee(id, firstname, lastname, department));
            }

        }catch (SQLException e){
            System.out.println("Unable to get all employees");
        }
        return employees;
    }

    public static List<Report> getAllReports()
    {
        List<Report> reports = new ArrayList<>();
        String sql = "SELECT * FROM MainTable";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int reportID = rs.getInt("ReportID");
                String description = rs.getString("Description");
                String incidentType = rs.getString("IncidentType");
                String actionTaken = rs.getString("ActionTaken");
                String status = rs.getString("Status");
                String title = rs.getString("Title");
                int employeeID = rs.getInt("employeeID");
                LocalDate dateOccurred = rs.getDate("Date").toLocalDate();


                switch(incidentType){
                    case "INJURY": {
                        String injurySql = "SELECT InjuryType, Hospitalized FROM Injury WHERE InjuryID = ?";
                        try (PreparedStatement injuryStmt = conn.prepareStatement(injurySql)) {
                            injuryStmt.setInt(1, reportID);
                            try (ResultSet injuryRs = injuryStmt.executeQuery()) {
                                if (injuryRs.next()) {
                                    String injuryType = injuryRs.getString("InjuryType");
                                    boolean hospitalized = injuryRs.getBoolean("Hospitalized");
                                    reports.add(new Injury(reportID, title, employeeID, actionTaken, description, injuryType, hospitalized, status, dateOccurred));
                                }
                            }
                        }
                        break;
                    }
                    case "NEAR_MISS":
                        String nearMissSQL = "SELECT * from NearMiss WHERE MissID = ?";
                        try (PreparedStatement nearMissStmt = conn.prepareStatement(nearMissSQL)) {
                            nearMissStmt.setInt(1, reportID);
                            try (ResultSet nearMissRs = nearMissStmt.executeQuery()) {
                                if (nearMissRs.next()) {
                                    reports.add(new NearMiss(reportID, title, employeeID, actionTaken, description, status, dateOccurred));
                                }
                            }
                        }

                        break;
                    case "PRODUCT_DAMAGE":
                        String productDamageSQL = "SELECT * FROM ProductDamage WHERE ProductID = ?";
                        try (PreparedStatement productDamageStmt = conn.prepareStatement(productDamageSQL)) {
                            productDamageStmt.setInt(1, reportID);
                            try (ResultSet productDamageRs = productDamageStmt.executeQuery()) {
                                if (productDamageRs.next()) {
                                    int productDamage = productDamageRs.getInt("ProductDamage");
                                    reports.add(new ProductDamage(reportID, title,employeeID,description, actionTaken,productDamage, status, dateOccurred));
                                }
                            }
                        }
                        break;
                    case "PROPERTY_DAMAGE":
                        String propertyDamageSQL = "SELECT * FROM PropertyDamage WHERE PropertyID = ?";
                        try (PreparedStatement propertyDamageStmt = conn.prepareStatement(propertyDamageSQL)) {
                            propertyDamageStmt.setInt(1, reportID);
                            try (ResultSet propertyDamageRs = propertyDamageStmt.executeQuery()) {
                                if (propertyDamageRs.next()) {
                                    int propertyDamage = propertyDamageRs.getInt("PropertyDamage");
                                    reports.add(new PropertyDamage(reportID, title, employeeID, description, actionTaken,propertyDamage, status, dateOccurred));
                                }
                            }
                        }
                        break;
                    case "OTHER":
                        String otherSQL = "SELECT * FROM Other WHERE OtherID = ?";
                        try (PreparedStatement otherStmt = conn.prepareStatement(otherSQL)) {
                            otherStmt.setInt(1, reportID);
                            try (ResultSet otherRs = otherStmt.executeQuery()) {
                                if (otherRs.next()) {
                                    reports.add(new Other(reportID,title,employeeID,description,actionTaken,status,dateOccurred));
                                }
                            }
                        }
                        break;


                }




            }
        }
        catch (SQLException e)
        {
            System.out.println("Unable to fetch reports: " + e.getMessage());
        }
        return reports;
    }


    public static void deleteEmployee(int employeeID) throws SQLException {
        String sql = "DELETE FROM employees WHERE EmployeeID = ?";

        try(Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeID);
            stmt.executeUpdate();
        }
    }




}
