/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atracker;

import database.Database;
import java.sql.SQLException;
import logic.UiLogic;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juho
 */
public class LogicTest {
    UiLogic l = new UiLogic();
    Database db = new Database();
    
    public LogicTest() {
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
    public void avgPriceTest() throws SQLException {
        
        
        db.insertToMainTable("test", "test", "test", 1000, 500, 1950);
        db.insertToMainTable("test", "test", "test", 1000, 500, 1950);
        double avg = l.getAvgPrice("test");
        
        db.createStatement("DELETE FROM apartments WHERE city = 'test'");
        db.createStatement("DELETE FROM results WHERE city = 'test'");
        
        assertEquals(2.0, avg, 0.001);
    }
    
    @Test
    public void getResultsAddsDataToDb() throws SQLException {
        l.getResults("Raahe");
        assertTrue(db.cityExists("apartments", "Raahe"));
        db.createStatement("DELETE FROM apartments WHERE city LIKE '%Raahe%'");
    }
}
