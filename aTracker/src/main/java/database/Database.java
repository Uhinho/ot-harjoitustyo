
package database;

import logic.Apartment;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Database {
    
    private static Connection conn;
    private String url;
    private boolean created;
    
    public Database() {
        this.url = "jdbc:sqlite:data.db";
        this.created = false;
    }
    
    private Connection connect() {
        Connection conn = null;
        
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return conn;
    }
    
    public void init() throws ClassNotFoundException, SQLException {
        String mainTable = "CREATE TABLE IF NOT EXISTS apartments ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "city TEXT,"
                    + "part TEXT,"
                    + "address TEXT,"
                    + "price REAL,"
                    + "size REAL,"
                    + "yearbuilt INTEGER"
                    + ");";
        
        String resultTable = "CREATE TABLE IF NOT EXISTS results ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "city TEXT,"
                    + "mprice REAL"
                    + ");";
        try {
            Class.forName("org.sqlite.JDBC");
            conn = this.connect();
            this.createStatement(mainTable);
            this.createStatement(resultTable);
            conn.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }   
    }
    
    public void dropTable(String tablename) throws SQLException {
        String sql = "DROP TABLE IF EXISTS " + tablename + ";";
        
        this.createStatement(sql);
    }
    
    public void insertToMainTable(String city, String part, String address, double price, double size, int yearBuilt) throws SQLException {
        String sql = "INSERT INTO apartments(city,part,address,price,size,yearbuilt) VALUES (?,?,?,?,?,?)";
        
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, city);
            pstmt.setString(2, part);
            pstmt.setString(3, address);
            pstmt.setDouble(4, price);
            pstmt.setDouble(5, size);
            pstmt.setInt(6, yearBuilt);
            
            pstmt.executeUpdate();
            conn.close();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }   
        
    }
    
    public void insertToResultTable(String city, double price) throws SQLException {
        String sql = "INSERT INTO results(city,mprice) VALUES (?,?)";
        
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, city);
            pstmt.setDouble(2, price);
            pstmt.executeUpdate();
            conn.close();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    public void createStatement(String statement) throws SQLException {
        
        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            stmt.execute(statement);
            conn.close();   
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }  
    }
    
    public ArrayList<Apartment> getCityFromDb(String city) throws SQLException {
        String sql = "SELECT * FROM apartments WHERE city = ?";
        ArrayList<Apartment> list = new ArrayList<>();
        try {
            Connection conn = this.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, city);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Apartment ap = new Apartment(rs.getString("city"), rs.getString("part"), rs.getString("address"), rs.getDouble("price"), rs.getDouble("size"), rs.getInt("yearbuilt"));
                list.add(ap);
            }
            conn.close();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return list;
    }
    
    public void printResults() throws SQLException {
        String sql = "SELECT * FROM results ORDER BY city";
        DecimalFormat df = new DecimalFormat("#.##");
        
        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
                  
            System.out.println("\nYour previous searches:\n");
            while (rs.next()) {
                System.out.println(rs.getString(2) + ": " + df.format(rs.getDouble(3)) + " €/m2");
            }
            conn.close();
                
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }  
    }
    
    public boolean cityExists(String table, String city) {
        boolean exists = false;
        String sql = String.format("SELECT * FROM %s WHERE city = ?", table);
        
        try {
            Connection conn = this.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, city);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                exists = true;
                
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return exists;
    }
    
    public boolean emptyTable(String tablename) throws SQLException {
        String sql = String.format("SELECT COUNT (*) AS total FROM %s", tablename);
        
        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int count = rs.getInt("total");
            conn.close();
            if (count == 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        
        return false;
    }
        
}
