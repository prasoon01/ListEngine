/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import listengine.models.DataObj;
import listengine.models.Entities;
import listengine.models.Item;
import listengine.models.Model;

/**
 *
 * @author Andreas
 */
public class SQLiteHelper {

    
    Connection conn = null;
    
    private final String DB_URL = "jdbc:sqlite:warhammer.sqlite";
    private final String DB_ITEM = "ITEMS";
    private final String DB_MODELS = "MODELS";
    private final String NAME = "NAME";
    private final String TYPE = "TYPE";
    private final String COST = "COST";
    
    private final String CHAMP_COST = "CCOST";
    private final String MUSIC_COST = "MCOST";
    private final String STANDARD_COST = "SCOST";
    
    
    public SQLiteHelper(){
        connect();
        createDB();
    }
    
    private void connect(){
        conn = dbConnection.dbConnector();
    }
    
    public void closeDB(){
        try {
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ":  " + e.getMessage());
        }
    }
    
    /**
     * Returnera rätt tabell vi skall hantera
     * @param table
     * @return 
     */
    private String get_tab(String table){
        String tab = "";
        if(table.equals("models")){
            tab = DB_MODELS;
        }else if(table.equals("items")){
            tab = DB_ITEM;
        }
        return tab;
    }
    
    
    /**
     * sätter in ett objekt
     * @param name
     * @param type
     * @param cost 
     * @param table lowercase flagga för vilken tabell det gäller (models,items)
     */
    public void insert(String name, String type, int cost, String table, int champ, int music, int standard){
        
        String tab = get_tab(table);
        
        connect();
        //kikar först om namnet förekommer i databasen, inga dubletter är tillåtna.
        if(check_if_exist(name,tab)){
            JOptionPane.showMessageDialog(null,name+" Allredy exists");
            return;
        }
        
        Statement stmt = null;
        try{
            stmt = conn.createStatement();
            String sql = "INSERT INTO "+tab+" (NAME,COST,TYPE,CCOST,MCOST,SCOST) "
                    +"VALUES ('"+name+"',"+cost+", '"+type+"', "+champ+", "+music+", "+standard+");";
            stmt.executeUpdate(sql);
            stmt.close();
            //conn.commit();
            conn.close();
            
            JOptionPane.showMessageDialog(null,name+" Added");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ":  " + e.getMessage());
        }
    }
    
    /**
     * Insert mot items
     * @param name
     * @param type
     * @param cost
     * @param table 
     */
    public void insert(String name, String type, int cost, String table){
        
        String tab = get_tab(table);
        
        connect();
        //kikar först om namnet förekommer i databasen, inga dubletter är tillåtna.
        if(check_if_exist(name,tab)){
            JOptionPane.showMessageDialog(null,name+" Allredy exists");
            return;
        }
        
        Statement stmt = null;
        try{
            stmt = conn.createStatement();
            String sql = "INSERT INTO "+tab+" (NAME,COST,TYPE) "
                    +"VALUES ('"+name+"',"+cost+", '"+type+"');";
            stmt.executeUpdate(sql);
            stmt.close();
            //conn.commit();
            conn.close();
            
            JOptionPane.showMessageDialog(null,name+" Added");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ":  " + e.getMessage());
        }
    }
    
    /**
     * Kikar av om namnet förekommer i en tabell
     * @param name
     * @param tab
     * @return 
     */
    private boolean check_if_exist(String name, String tab){
        Statement stmt = null;
        try{
            stmt = conn.createStatement();
            String sql = "SELECT * FROM "+tab+" ;";
            ResultSet rs = stmt.executeQuery(sql);//hämtar in alla rader från tabell
            while(rs.next()){
                String n = rs.getString("NAME");
                if(n.equals(name)){
                    rs.close();
                    stmt.close();
                    return true;
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ":  " + e.getMessage());
        }
        return false;
    }
    
    //public void update(String name, String table){}
    
    /**
     * Tar bort en rad från databasen med angivet namn
     * @param name 
     * @param table (models,items)
     */
    public void delete(String name, String table){
        
        String tab = get_tab(table);
        connect();
        Statement stmt = null;
        if(!check_if_exist(name,tab)){
            JOptionPane.showMessageDialog(null,name+" Not Exists!");
            return;
        }
        try{
            stmt = conn.createStatement();
            String sql = "DELETE from "+tab+" where NAME='"+name+"';";
            stmt.executeUpdate(sql);
            stmt.close();
            //conn.commit();
            conn.close();
            JOptionPane.showMessageDialog(null,name+" Deleted!");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ":  " + e.getMessage());
        }
    }
    
    /***
     * Hämtar ut ett informationsobjekt ur databasen
     * @param name på önskad entitet
     * @param table vilken tabell den tycks vara i
     * @return DataObj med information från databasen
     */
    public Entities get_row_model(String name, String table){
        Entities obj = null;
        String tab = get_tab(table);
        connect();
        Statement stmt = null;
        try{
            stmt = conn.createStatement();
            String sql = "SELECT * FROM "+tab+" where NAME='"+name+"';";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                obj = new Model(rs.getString("NAME"),
                                  rs.getInt("COST"),
                                  tab,
                                  rs.getInt("CCOST"),
                                  rs.getInt("MCOST"),
                                  rs.getInt("SCOST"));
            }
            rs.close();
            stmt.close();
            conn.close();
            return obj;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ":  " + e.getMessage());
        }
        return null;
    }
    
    /**
     * Hämtar rad i items
     * @param name
     * @param table
     * @return 
     */
    public Entities get_row_items(String name, String table){
        Entities obj = null;
        String tab = get_tab(table);
        connect();
        Statement stmt = null;
        try{
            stmt = conn.createStatement();
            String sql = "SELECT * FROM "+tab+" where NAME='"+name+"';";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                obj = new Item(rs.getString("NAME"),
                                  rs.getInt("COST"),
                                  tab
                                  );
            }
            rs.close();
            stmt.close();
            conn.close();
            return obj;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ":  " + e.getMessage());
        }
        return null;
    }
    
    /**
     * Hämtar ut all data ur en tabell, returnerar en objektsamlig av DataObj
     * @param table
     * @return 
     */
    public ArrayList<Entities> get_All_units(String table){
        ArrayList<Entities> list = new ArrayList<Entities>();
        String tab = get_tab(table);
        connect();
        Statement stmt = null;
        try{
            stmt = conn.createStatement();
            String sql = "SELECT * FROM "+tab+";";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                
                list.add(new Model(rs.getString("NAME"),
                                  rs.getInt("COST"),
                                  tab,
                                  rs.getInt("CCOST"),
                                  rs.getInt("MCOST"),
                                  rs.getInt("SCOST")));
            }
            rs.close();
            stmt.close();
            conn.close();
            return list;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": -get all-  " + e.getMessage());
        }
        return null;
    }
    
    /**
     * hämtar från items
     * @param table
     * @return 
     */
    public ArrayList<Entities> get_All_items(String table){
        ArrayList<Entities> list = new ArrayList<Entities>();
        String tab = get_tab(table);
        connect();
        Statement stmt = null;
        try{
            stmt = conn.createStatement();
            String sql = "SELECT * FROM "+tab+";";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                
                list.add(new Item(rs.getString("NAME"),        
                                  rs.getInt("COST"),
                                  tab));
            }
            rs.close();
            stmt.close();
            conn.close();
            return list;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ":  - get_All_items - " + e.getMessage());
        }
        return null;
    }
    
    private void createDB(){
        Statement stmt1 = null;
        Statement stmt2 = null;
        try {
            stmt1 = conn.createStatement();
            stmt2 = conn.createStatement();
            String st1 = "CREATE TABLE IF NOT EXISTS " + DB_ITEM
                    + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "NAME TEXT NOT NULL,"
                    + "COST INT NOT NULL,"
                    + "TYPE TEXT)";
            stmt1.executeUpdate(st1);
            stmt1.close();
            
            String st2 = "CREATE TABLE IF NOT EXISTS " + DB_MODELS
                    + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "NAME TEXT NOT NULL,"
                    + "COST INT NOT NULL,"
                    + "TYPE TEXT,"
                    + "CCOST INT,"
                    + "MCOST INT,"
                    + "SCOST INT)";
            stmt2.executeUpdate(st2);
            stmt2.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ":  " + e.getMessage());
        }
    }
}
