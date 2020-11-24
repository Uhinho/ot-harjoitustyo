
package atracker;

import database.Database;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
import org.jsoup.Connection;


public class UiLogic {
    Database db = new Database();
        
    public void getResults(String search) throws SQLException {
        
        if (!db.cityExists(search)) {
        
            // Classes of the <div> elements on the page where the apartment listings are found
            String listingDiv = "flexboxgrid__col-xs-12__1I1LS flexboxgrid__col-sm-7__1EzZq flexboxgrid__col-md-9__2kjy7 flexboxgrid__col-lg-9__M7bfm styles__infoArea__2yhEL";
            String priceDiv = "flexboxgrid__col-xs-5__1-5sb flexboxgrid__col-md-4__2DYW-";
            String addressDiv = "flexboxgrid__col-xs-12__1I1LS";
            String sizeDiv = "flexboxgrid__col-xs__26GXk flexboxgrid__col-md-4__2DYW-";
            String yearBuiltDiv = "flexboxgrid__col-xs-3__3Kf8r flexboxgrid__col-md-4__2DYW-";
            String listDiv = "announcement-list";
            String listPageDiv = "ListPage__items__3n9Bd flexboxgrid__row__wfmuy";
            String pageNumberDiv = "theme__button__1YqFK theme__flat__13aFK theme__button__1YqFK theme__squared__17Uvn theme__neutral__1F1Jf Button__button__3K-jn Pagination__button__3H2wX";

            // Url for the website takes city name as parameter for correct search
            String url = "https://www.etuovi.com/myytavat-asunnot/" + search;

            Document document = this.getPage(url);
            int counter = 0;
            Elements pageNums = document.getElementsByClass(pageNumberDiv); // How many pages of listings are on the site
            int lastpage = Integer.valueOf(pageNums.last().text().trim()); // last page number


            // Iterate through the listings and populate the list with Apartment objects
            ArrayList<Apartment> list = new ArrayList<>();  // An empty arraylist for the Apartment objects

            for (int page = 1; page < lastpage; page++) {
                url = "https://www.etuovi.com/myytavat-asunnot/" + search + "?page=" + page;
                document = this.getPage(url);
                Elements elements = document.getElementsByClass(listingDiv);

                for (int i = 0; i < elements.size(); i++) {

                    Element listingElement = elements.get(i);
                    Elements priceElement = listingElement.getElementsByClass(priceDiv);
                    Elements addressElement = listingElement.getElementsByClass(addressDiv);
                    Elements sizeElement = listingElement.getElementsByClass(sizeDiv);
                    Elements yearBuiltElement = listingElement.getElementsByClass(yearBuiltDiv);

                    String address = listingElement.select("h4").text();
                    double price;
                    String priceInnerText = priceElement.select("h6 + span").text();
                    if (priceInnerText.isEmpty()) {
                        continue; // if no price is mentioned in the listing, skip the apartment
                    } else {
                        price = Double.valueOf(priceElement.select("h6 + span").text().replace("€", "").replace(",", ".").replaceAll("\\s", ""));
                    }    
                    String sizeInnerText = sizeElement.select("h6 + span").text().trim();
                    double size;
                    if (sizeInnerText.isEmpty()) {
                        size = 0; // If square meters are not mentioned in the listing, value 0 assigned
                    } else {
                        size = Double.valueOf(sizeElement.select("h6 + span").text().replace("m²", "").replace(",", ".").replaceAll("\\s", ""));
                    }
                    int yearBuilt;
                    String yearBuiltInnerText = yearBuiltElement.select("h6 + span").text().trim();
                    if (yearBuiltInnerText.isEmpty() || yearBuiltInnerText.equals("-")) {
                        yearBuilt = 0; // If square meters are not mentioned in the listing, value 0 assigned
                    } else {
                        yearBuilt = Integer.valueOf(yearBuiltElement.select("h6 + span").text().trim());
                    }
                    // Address of the apartments on the page is "Street name, Part of town, City name"
                    String[] spliced = address.split(",");
                    String street = spliced[0].trim();
                    String part;
                    String city;
                    if (spliced.length < 3) {  // In more rural areas the there's no part of town included
                        part = "-";
                        city = spliced[1].trim();
                    } else {
                        part = spliced[1].trim();
                        city = spliced[2].trim();   
                    }
                    counter++;
                    if (counter % 50 == 0) {
                        System.out.println("Collecting data... " + counter + " listings already fetched!");
                    }

                    Apartment apartment = new Apartment(city, part, street, price, size, yearBuilt);
                    db.insertToTable(city, part, address, price, size, yearBuilt);

                }
            }
        }
    }
    
    public double getAvgPrice(String search) throws SQLException {
        
        
        double avg = 0;
        double sumOfPrice = 0;
        double sumOfM = 0;
        
        
        ArrayList<Apartment> list = db.getCityFromDb(search);
        
        for (Apartment ap : list) {
            sumOfPrice += ap.getPrice();
            sumOfM += ap.getSize();
        }
        
        avg = sumOfPrice / sumOfM;
                        
        
        
        return avg;
    }
    
    
    private Document getPage(String url) {
        
        Document document;
        
        
        try {
            document = Jsoup.connect(url).get(); // Connect to the url

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return document;
    }
    
    
    
}