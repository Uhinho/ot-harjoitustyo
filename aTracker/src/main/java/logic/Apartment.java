
package logic;

import java.text.DecimalFormat;


public class Apartment {
    private String city;
    private String part;
    private String address;
    private double price;
    private double size;
    private int yearBuilt;
    
    /**
     *Yksittäisen asuntoilmoituksen olio.
     * 
     *     * @param city   kaupunki
     * @param part   kaupunginosa
     * @param address    tarkka osoite
     * @param price  hinta euroina
     * @param size   koko neliömetreinä
     * @param yearBuilt  rakennusvuosi
    **/ 

    public Apartment(String city, String part, String address, double price, double size, int yearBuilt) {
        this.city = city;
        this.part = part;
        this.address = address;
        this.price = price;
        this.size = size;
        this.yearBuilt = yearBuilt;
    }


    public String getCity() {
        return city;
    }

    public String getPart() {
        return part;
    }

    public String getAddress() {
        return address;
    }

    public double getPrice() {
        
        return price;
    }

    public double getSize() {
        return size;
    }
    
    public int getYearBuilt() {
        return yearBuilt;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        return city + ", " + part + ", " + address + ", " + df.format(size) + "m2, " + df.format(price) + "€, " + this.yearBuilt;
    }


    
    
}
