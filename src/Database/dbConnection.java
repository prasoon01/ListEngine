/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Andreas
 */
public class dbConnection {
    
    public static Connection dbConnector(){
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:warhammer.sqlite");
            //JOptionPane.showMessageDialog(null, "connected!");
            return conn;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}