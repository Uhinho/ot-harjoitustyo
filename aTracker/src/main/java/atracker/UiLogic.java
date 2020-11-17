
package atracker;

import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import org.jsoup.Connection;


public class UiLogic {
    
    public ArrayList<Apartment> getResults(String search){
        
        String url = "https://www.etuovi.com/myytavat-asunnot/" + search;
        
        String listingDiv = "flexboxgrid__col-xs-12__1I1LS flexboxgrid__col-sm-7__1EzZq flexboxgrid__col-md-9__2kjy7 flexboxgrid__col-lg-9__M7bfm styles__infoArea__2yhEL";
        String priceDiv = "flexboxgrid__col-xs-5__1-5sb flexboxgrid__col-md-4__2DYW-";
        String addressDiv = "flexboxgrid__col-xs-12__1I1LS";
        String sizeDiv = "flexboxgrid__col-xs__26GXk flexboxgrid__col-md-4__2DYW-";
        String listDiv = "announcement-list";
        String listPageDiv = "ListPage__items__3n9Bd flexboxgrid__row__wfmuy";
        String pageNumberDiv = "theme__button__1YqFK theme__flat__13aFK theme__button__1YqFK theme__squared__17Uvn theme__neutral__1F1Jf Button__button__3K-jn Pagination__button__3H2wX";
        
        ArrayList<Apartment> list = new ArrayList<>();
        
        Document document;
        
            try {
                document = Jsoup.connect(url).get();

            }
            catch (Exception e){
                e.printStackTrace();
                return null;
            }
            
            
        Elements pageNums = document.getElementsByClass(pageNumberDiv);
        int lastpage = Integer.valueOf(pageNums.last().text().trim());
        
            
            
        Elements elements = document.getElementsByClass(listingDiv);

        for(int i = 0; i < elements.size(); i++){
        
            Element listingElement = elements.get(i);
            Elements priceElement = listingElement.getElementsByClass(priceDiv);
            Elements addressElement = listingElement.getElementsByClass(addressDiv);
            Elements sizeElement = listingElement.getElementsByClass(sizeDiv);
            
           
            //int price = Integer.valueOf(priceElement.text().replace("Hinta", "").replace("€", "").replace(" ", "").trim());
            
            String address = listingElement.select("h4").text();
            double price = Double.valueOf(priceElement.select("h6 + span").text().replace("€", "").replace(",", ".").replaceAll("\\s", ""));
            double size = Double.valueOf(sizeElement.select("h6 + span").text().replace("m²", "").replace(",", ".").replaceAll("\\s", ""));
       
            String[] spliced = address.split(", ");
            String street = spliced[0];
            String part = spliced[1];
            String city = spliced[2];
            
            
            Apartment apartment = new Apartment(city,part,street,price,size);
            list.add(apartment);
        }
        
        return list;
    }
    
    public double getAvgPrice(String search){
        ArrayList<Apartment> results = this.getResults(search);
        
        
        double sumOfPrice = 0;
        double sumOfM = 0;
        
        
        for(Apartment a: results){
            double price = a.getPrice();
            double size = a.getSize();
            
            sumOfPrice += price;
            sumOfM += size;
        }
        
        double avg = sumOfPrice / sumOfM;
        
        
        return avg;
    }
    
    
    
}