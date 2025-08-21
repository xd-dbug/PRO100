package csc180.damian.swiney.workplace_incident_report_system.model;

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

    public static void addInjury(String description, boolean Hospitalized, String InjuryType) {
        String InjurySql = "INSERT INTO Injury (InjuryID, Hospitalized, InjuryType) VALUES (?,?,?)";

        addToMainTable(description);
//

        try {
            PreparedStatement injuryStmt = connect().prepareStatement(InjurySql);
            injuryStmt.setInt(1, reportID);
            injuryStmt.setBoolean(2, Hospitalized);
            injuryStmt.setString(3, InjuryType);
            injuryStmt.executeUpdate();


        } catch (SQLException e) {
            System.out.println("Unable to add Injury");
        }


    }

    public static void addNearMiss(String description){
        String missSql = "INSERT INTO NearMiss (NearMissID, Description) VALUES (?,?)";
        addToMainTable(description);
        try{
            PreparedStatement stmt = connect().prepareStatement(missSql);
            stmt.setInt(1, reportID);
            stmt.setString(2, description);
            stmt.executeUpdate();

        }catch(SQLException e){
            System.out.println("Unable to add NearMiss");
        }

    }

    public static void addProductDamage(String description, int productDamage) {
        String productSql = "INSERT INTO ProductDamage (ProductDamageID, Description, ProductDamage) VALUES (?,?,?)";
        addToMainTable(description);
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

    public static void addPropertyDamage(String description, int propertyDamage) {
        String propertySql = "INSERT INTO PropertyDamage (PropertyDamageID, Description, PropertyDamage) VALUES (?,?,?)";
        addToMainTable(description);
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



    public static void addToMainTable(String description) {

        try {
            String MainSql = "INSERT INTO MainTable(Description) VALUES (?)";
            PreparedStatement stmt = connect().prepareStatement(MainSql);
            stmt.setString(1, description);
            stmt.executeUpdate();
            ResultSet rs = stmt.executeQuery();
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






}
