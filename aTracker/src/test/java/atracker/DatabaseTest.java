package atracker;

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
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void dataBaseIsCreated() throws ClassNotFoundException{
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
        }
        
        String sql = "SELECT name FROM sqlite_master WHERE type='table' AND name='apartments";
        
        
        assertTrue(exists);
    }
    
    @Test
    public void dataIsAdded() throws SQLException{
        UiLogic ul = new UiLogic();
        ul.getResults("Kemi");
        
        assertTrue(db.cityExists("Kemi"));
        
    }
    
}