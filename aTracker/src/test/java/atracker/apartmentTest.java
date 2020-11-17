
package atracker;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class apartmentTest {
    
    public apartmentTest() {
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
    public void apartmentConstructor(){
        Apartment randomApartment = new Apartment("New York", "Brooklyn", "Brooklyn Drive 1", 200, 200);
        assertEquals("New York, Brooklyn, Brooklyn Drive 1, 200m2, 200€", randomApartment.toString());
    }
}
