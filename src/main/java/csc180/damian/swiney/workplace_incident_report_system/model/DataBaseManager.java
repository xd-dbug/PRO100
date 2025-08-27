package csc180.damian.swiney.workplace_incident_report_system.model;

import csc180.damian.swiney.workplace_incident_report_system.model.typeOfReports.Injury;
import csc180.damian.swiney.workplace_incident_report_system.model.typeOfReports.Other;

import java.sql.*;
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


    public static void addReport() {


    }

    public static Report addInjury(String description,String Title,String IncidentType,String ActionTaken,String Status, boolean Hospitalized, String InjuryType, int employeeID) {
        String InjurySql = "INSERT INTO Injury (InjuryID, Hospitalized, InjuryType) VALUES (?,?,?)";

        addToMainTable(description,Title,IncidentType,ActionTaken,Status,employeeID);

        try {
            PreparedStatement injuryStmt = connect().prepareStatement(InjurySql);
            injuryStmt.setInt(1, reportID);
            injuryStmt.setBoolean(2, Hospitalized);
            injuryStmt.setString(3, InjuryType);
            injuryStmt.executeUpdate();


        } catch (SQLException e) {
            System.out.println("Unable to add Injury");
        }

        Report injuryReport = new Injury(Title,reportID,ActionTaken,description,InjuryType,Hospitalized,Status);

        return injuryReport;

    }

    public static void addNearMiss(String description,String Title,String IncidentType,String ActionTaken,String Status, int employeeID){
        String missSql = "INSERT INTO NearMiss (NearMissID, Description) VALUES (?,?)";
        addToMainTable(description,Title,IncidentType,ActionTaken,Status,employeeID);
        try{
            PreparedStatement stmt = connect().prepareStatement(missSql);
            stmt.setInt(1, reportID);
            stmt.setString(2, description);
            stmt.executeUpdate();

        }catch(SQLException e){
            System.out.println("Unable to add NearMiss");
        }

    }

    public static void addProductDamage(String description,String Title,String IncidentType,String ActionTaken,String Status, int productDamage, int employeeID) {
        String productSql = "INSERT INTO ProductDamage (ProductDamageID, Description, ProductDamage) VALUES (?,?,?)";
        addToMainTable(description,Title,IncidentType,ActionTaken,Status,employeeID);
        try{
            PreparedStatement stmt = connect().prepareStatement(productSql);
            stmt.setInt(1, reportID);
            stmt.setString(2, description);
            stmt.setInt(3, productDamage);
            stmt.executeUpdate();

        }catch (SQLException e){
            System.out.println("Unable to add ProductDamage");
        }
    }

    public static void addPropertyDamage(String description,String Title,String IncidentType,String ActionTaken,String Status, int propertyDamage, int employeeID) {
        String propertySql = "INSERT INTO PropertyDamage (PropertyDamageID, Description, PropertyDamage) VALUES (?,?,?)";
        addToMainTable(description,Title,IncidentType,ActionTaken,Status, employeeID);
        try{
            PreparedStatement stmt = connect().prepareStatement(propertySql);
            stmt.setInt(1, reportID);
            stmt.setString(2, description);
            stmt.setInt(3, propertyDamage);
            stmt.executeUpdate();

        }catch(SQLException e){
            System.out.println("Unable to add PropertyDamage");
        }
    }



    public static void addToMainTable(String description,String Title,String IncidentType,String ActionTaken,String Status, int EmployeeID) {

        try {
            String MainSql = "INSERT INTO MainTable(Title,EmployeeID,DataOccured,IncidentType,Description,ActionTaken,Status) VALUES (?,?,?,?,?)";
            PreparedStatement stmt = connect().prepareStatement(MainSql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, Title);
            stmt.setInt(2, EmployeeID);
            stmt.setDate(3, new java.sql.Date(System.currentTimeMillis()));
            stmt.setString(4, IncidentType);
            stmt.setString(5, description);
            stmt.setString(4, ActionTaken);
            stmt.setString(5, Status);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                reportID = rs.getInt(1);
            } else {
                throw new SQLException("Unable to get ReportID");
            }
        } catch (SQLException e) {
            System.out.println("Unable to add To MainTable");
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


                switch(incidentType){
                    case "INJURY":
                        break;
                    case "NEAR_MISS":

                        break;
                    case "PRODUCT_DAMAGE":
                        break;
                    case "PROPERTY_DAMAGE":
                        break;


                }



                reports.add(new Other("N/A", 0, "N/A", description, "Open"));
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
