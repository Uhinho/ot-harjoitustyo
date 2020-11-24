
package database;

import atracker.Apartment;
import java.sql.*;
import java.util.ArrayList;
import org.sqlite.util.StringUtils;


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
    
    public void init() throws ClassNotFoundException {

        try {
            Class.forName("org.sqlite.JDBC");
            conn = this.connect();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        
        this.createTable(url); 
    }
    
    private void createTable(String url) {
        if (!created) {
            String sql = "CREATE TABLE IF NOT EXISTS apartments ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "city TEXT,"
                    + "part TEXT,"
                    + "address TEXT,"
                    + "price REAL,"
                    + "size REAL,"
                    + "yearbuilt INTEGER"
                    + ");";

            this.createStatement(sql);
            this.created = true;
        }
        
    }
    
    public void dropTable(String tablename) {
        String sql = "DROP TABLE IF EXISTS " + tablename + ";";
        
        this.createStatement(sql);
        
        
    }
    
    public void insertToTable(String city, String part, String address, double price, double size, int yearBuilt) throws SQLException {
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
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }    
    }
    
    public void createStatement(String statement) {
        
        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            stmt.execute(statement); 
        } catch (Exception e){
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    public ArrayList<Apartment> getCityFromDb(String city) {
        String sql = "SELECT * FROM apartments WHERE city = ?";
        ArrayList<Apartment> list = new ArrayList<>();
       
       
        try {
            Connection conn = this.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, city);
            ResultSet rs = stmt.executeQuery();
           
            while(rs.next()) {
                Apartment ap = new Apartment(rs.getString("city"), rs.getString("part"), rs.getString("address"), rs.getDouble("price"), rs.getDouble("size"), rs.getInt("yearbuilt"));
                list.add(ap);
            }
           
        } catch (SQLException e) {
           System.out.println(e.getMessage());
        }
       
        return list;
    }
    
    public boolean cityExists(String city) {
        boolean exists = false;
        String sql = "SELECT * FROM apartments WHERE city = ?";
        
        try {
            Connection conn = this.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, city);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() == true) {
                exists = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return exists;
    }
        
        
   
        
    
    
    
    
    

}
