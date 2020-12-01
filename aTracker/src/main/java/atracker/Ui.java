package atracker;

import logic.UiLogic;
import database.*;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Ui extends UiLogic {
    private Scanner scanner;
    private UiLogic logic;
    private List<String> commands;
    
    public Ui() {
        this.scanner = new Scanner(System.in);
        this.logic = new UiLogic();
        String[] commands = {   "1. New Search" , 
                                "2. View Database", 
                                "3. Reset Database", 
                                "0. Exit"};
        this.commands = Arrays.asList(commands);
    }
    
    public void start() throws ClassNotFoundException, SQLException {
        
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
    
    private void handleCommand(int command) throws SQLException, ClassNotFoundException {
        
        if (command > this.commands.size() - 1 || command < 0) {
            System.out.println("Invalid command.");
        } else {
            switch (command) {
                case 1:
                    System.out.println("PLEASE ENTER CITY: ");
                    String input = scanner.nextLine().trim();
                    //Format the input to first letter uppercase and rest lowercase
                    String city = input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
                    logic.getResults(city);
                    logic.printAvgPrice(city);
                    break;
                case 2:
                    db.printResults();
                    break;
                case 3:
                    db.dropTable("apartments");
                    db.dropTable("results");
                    db.init();
                    break;
            }
        }
    }
    
    
   
}
    
    
    
