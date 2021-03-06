
package logic;

import database.Database;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UiLogic {
    public Database db = new Database();
        
    public double getAvgPrice(String city, String part) throws SQLException {
        
        double avg = 0;
        double sumOfPrice = 0;
        double sumOfM = 0;
        List<Apartment> list;
        
        if (!part.isBlank()) {
            try {
                list = db.getCityFromDb(city).stream()
                    .filter(ap -> ap.getPart().equals(part))
                    .collect(Collectors.toList());
            } catch (Exception e) {
                System.out.println("No listings in " + part + ", )" + city);
                return 0;
            }
        } else {
            list = db.getCityFromDb(city);
        }
        
        
        for (Apartment ap : list) {
            sumOfPrice += ap.getPrice();
            sumOfM += ap.getSize();
        }
        
        avg = sumOfPrice / sumOfM;
        if (part.isBlank()) {
            if (!db.cityExists("results", city)) {
                db.insertToResultTable(city, avg);
            }
        } else {
            if (!db.cityExists("results", city + "," + part)) {
                db.insertToResultTable(city + "," + part, avg);
            }
        }
        return avg;
    }
    
    
    public ArrayList<Apartment> getListingsUnderAvg(String city, String part) throws SQLException {
        ArrayList<Apartment> apps = db.getCityFromDb(city);
        double avg = this.getAvgPrice(city, part);
        ArrayList<Apartment> underAvg = new ArrayList<>();
        
        for (Apartment ap: apps) {
            double pricePerM = ap.getPrice() / ap.getSize();
            if (ap.getPart().equals(part) && pricePerM < avg) {        
                underAvg.add(ap);
            }
        }

        return underAvg;
    }
    
    

}