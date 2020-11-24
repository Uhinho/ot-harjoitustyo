
package atracker;
import database.Database;
import java.sql.SQLException;




import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;


public class Ui extends UiLogic {
    private Scanner scanner;
    private UiLogic logic;
    

    public Ui() {
        this.scanner = new Scanner(System.in);
        this.logic = new UiLogic();
    }
    
    public void start() throws ClassNotFoundException, SQLException {
        DecimalFormat df = new DecimalFormat("#.##");
        Database db = new Database();
        db.init();
        //db.dropTable("apartments");
        System.out.println("WELCOME TO THE APARTMENT TRACKER");
        System.out.println("PLEASE ENTER CITY: ");
        String city = scanner.nextLine().trim();
        
        logic.getResults(city);
        System.out.println("AVERAGE PRICE FOR " + city + ": " + df.format(logic.getAvgPrice(city)) + "€");
        
        
        
    }
        
    
}
    
    
    
