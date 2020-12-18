package databaseTests;

import database.Database;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class DatabaseTest {
    Database db = new Database();
    
    public DatabaseTest() { 
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws ClassNotFoundException, SQLException {
        db.init();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void dataBaseIsCreated() throws ClassNotFoundException, SQLException{
        db.init();
        boolean exists = false;
        
       Connection conn = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:data.db");
            DatabaseMetaData md = conn.getMetaData();
            ResultSet rs = md.getTables(null,null, "apartments", null);
            if(rs.next()){
                exists = true;
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        } finally {
            conn.close();
        }
        
        assertTrue(exists);
    }
    
    
}