package atracker;

import logic.UiLogic;
import database.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import logic.Scraper;


public class Ui extends UiLogic {
    private Scanner scanner;
    private UiLogic logic;
    private Scraper scraper;
    private List<String> commands;
    
    public Ui() {
        this.scanner = new Scanner(System.in);
        this.logic = new UiLogic();
        this.scraper = new Scraper();
        String[] commands = {   "1. New Search" , 
                                "2. Previous searches", 
                                "3. Clean search history", 
                                "0. Exit"};
        this.commands = Arrays.asList(commands);
    }
    
    public void start() throws ClassNotFoundException, SQLException, IOException {
        
        Database db = new Database();
        db.init();
        System.out.println("WELCOME TO THE APARTMENT TRACKER \n");
        
        while (true) {
            this.printCommands();
            try {
                int cmd = Integer.parseInt(scanner.nextLine());
                if (cmd == 0) {
                    break;
                } else {
                    this.handleCommand(cmd);
                }
            } catch (NumberFormatException e) {
                System.out.println("Not a valid command.");
            }
            
        }
    }
    
    private void printCommands() {
        System.out.println("");
        for (String command : commands) {
            System.out.println(command);
        }
        
        System.out.println("\nEnter command: ");
    }
    
    private void handleCommand(int command) throws SQLException, ClassNotFoundException, IOException {      
        if (command > this.commands.size() - 1 || command < 0) {
            System.out.println("Invalid command.");
        } else {
            switch (command) {
                case 1:
                    if (this.getYorN("Do you want to filter by part of town?")) {
                        this.includePart();
                    } else {
                        String city = this.userCityInput(1);
                        scraper.getResults(city);
                        logic.printAvgPrice(city, "");  
                    }
                    
                    break;
                case 2:
                    if (!db.emptyTable("results")) {
                        db.printResults();
                    } else {
                        System.out.println("No previous searches...");
                    }
                    break;
                case 3:
                    this.cleanSearchHistory();
                    break;
            }
        }
    }
    
    private void cleanSearchHistory() throws SQLException, ClassNotFoundException {
        if (this.getYorN("Are you sure")) {
            db.dropTable("apartments");
            db.dropTable("results");
            db.init();
        } else {
            System.out.println("Returning to main menu...");
        }
    }
    
    private void includePart() throws SQLException, IOException {

        String c = this.userCityInput(1); 
        String p = this.userCityInput(2);
        if (!c.isEmpty() || !p.isEmpty()) {
            if (!db.cityExists("results", c)) {
                this.scraper.getResults(c);
                this.logic.getAvgPrice(c, "");
            }
            this.logic.printAvgPrice(c, p);
        }
    }
        
    private boolean getYorN(String question) {
        System.out.println("");
        System.out.println(question + "? Y/N");
        String cmd = this.scanner.nextLine().toUpperCase().trim();
        if (!cmd.equals("Y") && !cmd.equals("N")) {
            System.out.println("Invalid command.");
            return false;
        } else if (cmd.equals("Y")) {
            return true;
        } else { 
            return false;
        }
    }
    
    //Selection 1 for city name, 2 for part of town
    private String userCityInput(int selection) throws UnsupportedEncodingException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in, "x-ISO-2022-CN-GB"));

        
        switch (selection) {
            case 1:
                System.out.println("Please enter city name: ");
                break;
            case 2:
                System.out.println("Please enter part of city: ");
                break;
        }
        
        String input = in.readLine().trim();
        //Format the input to first letter uppercase and rest lowercase
        if (!input.isBlank()) {
            String output = input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
            return output;
        } else {
            return null;
        }
        
        
    }    
    
   
}
    
    
    
