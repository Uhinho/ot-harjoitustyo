
package atracker;


import java.text.DecimalFormat;
import java.util.Scanner;


public class Ui extends UiLogic{
    private Scanner scanner;
    private UiLogic logic;

    public Ui() {
        this.scanner = new Scanner(System.in);
        this.logic = new UiLogic();
    }
    
    public void start(){
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("WELCOME TO THE APARTMENT TRACKER");
        System.out.println("PLEASE ENTER CITY: ");
        String city = scanner.nextLine();
        
        System.out.println("AVERAGE PRICE: " + df.format(logic.getAvgPrice(city)) + "€");
        
    }
    
}
    
    
    
