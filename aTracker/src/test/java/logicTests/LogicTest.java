/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicTests;

import database.Database;
import java.sql.SQLException;
import logic.Scraper;
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
    Scraper scr = new Scraper();
    
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
        db.insertToMainTable("test", "testpart", "testaddress", 100, 50, 1000);
        db.insertToMainTable("test", "testpart", "testaddress", 100, 50, 1000);
        
        double avg = l.getAvgPrice("test", "");
        
        db.createStatement("DELETE FROM apartments WHERE city = 'test'");
        db.createStatement("DELETE FROM results WHERE city = 'test'");

        assertEquals(2.0, avg, 0.01);
    }
    
    @Test
    public void getResultsAddsDataToDb() throws SQLException {
        boolean exists = false;
        if (db.cityExists("apartments", "Raahe")) {
            exists = true;
            db.createStatement("DELETE FROM apartments WHERE city LIKE '%Raahe%'");
            db.createStatement("DELETE FROM results WHERE city LIKE '%Raahe%'");
        }
        
        assertFalse(db.cityExists("apartments", "Raahe"));
        
        scr.getResults("Raahe");
        
        assertTrue(db.cityExists("apartments", "Raahe"));
        
        if (!exists) {
            db.createStatement("DELETE FROM apartments WHERE city LIKE '%Raahe%'");
            db.createStatement("DELETE FROM results WHERE city LIKE '%Raahe%'");
        }
        
       
        
    }
}
