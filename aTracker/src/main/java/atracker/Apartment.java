
package atracker;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Apartment {
    private String city;
    private String part;
    private String address;
    private double price;
    private double size;

    public Apartment(String city, String part, String address, double price, double size) {
        this.city = city;
        this.part = part;
        this.address = address;
        this.price = price;
        this.size = size;
        
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

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        return city + ", " + part + ", " + address + ", " + df.format(size) + "m2, " + df.format(price) + "€";
    }


    
    
}
