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
    public void tearDown() throws SQLException {
        db.createStatement("DELETE FROM results WHERE city LIKE '%test%'");
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
            conn.close();
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        assertTrue(exists);
    }
    
    @Test
    public void isEmptyTable() throws ClassNotFoundException, SQLException {
        db.init();
        
        db.insertToMainTable("test", "test", "test", 0, 0, 0);
        boolean empty = db.emptyTable("apartments");
        db.createStatement("DELETE FROM apartments WHERE city = 'test'");
        
        assertFalse(empty);
    }
    
    @Test
    public void getResultListReturnsList() throws SQLException, ClassNotFoundException {
        db.init();
        db.insertToResultTable("test1", 100);
        db.insertToResultTable("test2", 150);
        
        assertTrue(db.getResultsList().contains("test2: 150 €/m2"));
        
        
        
    }
    
  
}