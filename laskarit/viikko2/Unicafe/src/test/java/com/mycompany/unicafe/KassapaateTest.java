
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class KassapaateTest {
    Kassapaate kassa = new Kassapaate();
    Maksukortti kortti = new Maksukortti(1000);
    
    
    public KassapaateTest() {
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
    public void oikeaRahamaara(){
     
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void myytyjenMaaraOikea(){
        assertEquals(0, kassa.edullisiaLounaitaMyyty() + kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kassaKasvaaLounaanHinnalla(){
        int rahaa = kassa.kassassaRahaa();
        kassa.syoEdullisesti(240);
        assertEquals(rahaa+240, kassa.kassassaRahaa());
        rahaa = kassa.kassassaRahaa();
        kassa.syoMaukkaasti(400);
        assertEquals(rahaa+400, kassa.kassassaRahaa());
    }
    
    @Test
    public void vaihtorahaOikein(){
        assertEquals(200, kassa.syoMaukkaasti(600));
        assertEquals(60, kassa.syoEdullisesti(300));
    }
    
    @Test
    public void myytyjenMaaraKasvaa(){
        kassa.syoEdullisesti(240);
        kassa.syoMaukkaasti(400);
        assertEquals(2, kassa.edullisiaLounaitaMyyty() + kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maksuEiRiittava(){
        kassa.syoEdullisesti(100);
        kassa.syoMaukkaasti(100);
        assertEquals(100, kassa.syoEdullisesti(100));
        assertEquals(100, kassa.syoMaukkaasti(100));
        assertEquals(0, kassa.edullisiaLounaitaMyyty() + kassa.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void summaVeloitetaanKortilta(){
        assertTrue(kassa.syoEdullisesti(kortti));
        assertTrue(kassa.syoMaukkaasti(kortti));
    }
    
    @Test
    public void myytyjenMaaraKasvaaKunKortillaRahaa(){
        kassa.syoEdullisesti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertEquals(2, kassa.edullisiaLounaitaMyyty() + kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortillaEiRahaa(){
        Maksukortti k = new Maksukortti(100);
        assertFalse(kassa.syoEdullisesti(k));
        assertFalse(kassa.syoMaukkaasti(k));
        assertEquals(0, kassa.maukkaitaLounaitaMyyty() + kassa.edullisiaLounaitaMyyty());
        assertEquals(100, k.saldo());
        
    }
    
    @Test
    public void kassaEiMuutuKortiltaOstettaessa(){
        kassa.syoEdullisesti(kortti);
        kassa.syoMaukkaasti(kortti);
        
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void saldoJaKassaMuuttuuLadattaessa(){
        kassa.lataaRahaaKortille(kortti, 1000);
        assertEquals(2000, kortti.saldo());
        assertEquals(101000, kassa.kassassaRahaa());
    }
    
    @Test
    public void negatiivinenLatausKortille(){
        kassa.lataaRahaaKortille(kortti, -1);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    
    
    
}
