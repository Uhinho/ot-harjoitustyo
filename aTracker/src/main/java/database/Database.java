
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Database {
    
    private static Connection conn;
    private static boolean hasData = false;
    

    
    private void getConnection() throws ClassNotFoundException, SQLException{
        
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:data.db");
        init();
    }
    
    private void init() throws SQLException{
        if(!hasData){
            hasData = true;
            
            Statement state = conn.createStatement();
            ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='apartments'");
            if(!res.next()){
                System.out.println("Building the table for apartments");
                Statement state2 = conn.createStatement();
                state2.execute("CREATE TABLE apartments(primary key(id),city TEXT,part TEXT,"
                                +"address TEXT,size REAL,price REAL,yearbuilt INTEGER;");
                
                
            }
        }
        
    }
    
}
