
package logic;

import database.Database;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
        if (!db.cityExists("results", city)) {
            db.insertToResultTable(city, avg);
        }
              
        return avg;
    }
    
    public void printAvgPrice(String city, String part) throws SQLException {
        if (db.cityExists("apartments", city)) {
            DecimalFormat df = new DecimalFormat("#.##");
            String print;
            if (part.isBlank()) {
                print = "\nAVERAGE PRICE FOR " + city + ": " + df.format(this.getAvgPrice(city, part)) + "€";
            } else {
                print = "\nAVERAGE PRICE FOR " + city + ", " + part + ": " + df.format(this.getAvgPrice(city, part)) + "€";
            }
            
            System.out.println(print);
        }
    }
    
    
    
    
    
    
}