package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoAlussaOikein(){
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void lataaminenKasvattaaSaldoaOikein(){
        kortti.lataaRahaa(200);
        assertEquals(210,kortti.saldo());
    }
    
    @Test
    public void saldoVaheneeOikeinJosRahaaTarpeeksi(){
        kortti.otaRahaa(10);
        assertEquals(0,kortti.saldo());
    }
    
    public void saldoEiMuutuJosRahaaEiTarpeeksi(){
        kortti.otaRahaa(20);
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void rahatRiittääTrueTaiEiRiitäFalse(){
        assertTrue(kortti.otaRahaa(5));
        assertFalse(kortti.otaRahaa(20));
    }
    
    @Test
    public void toStringToimiiOikein(){
        assertEquals("saldo: 0.10", kortti.toString());
    }
}
