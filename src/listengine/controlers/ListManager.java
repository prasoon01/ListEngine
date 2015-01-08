/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listengine.controlers;

import Database.SQLiteHelper;
import java.util.ArrayList;
import listengine.models.Entities;

/**
 *Core klassen för listhantering
 * @author Andreas
 */
public class ListManager {
    
    private String race;//vilken ras vi skall hämta data om och hantera.(senare verision..)
    private ArrayList<Entities> available_items;    //tillgängliga items(genereras från databas)
    private ArrayList<Entities> available_models;   //tillgängliga modeller(genereras från databas)
    private SQLiteHelper db;
    
    public ListManager(SQLiteHelper db){
        this.db = db;
        set_available_items();
        set_available_models();
    }
    
    private void set_available_items(){
        this.available_items = db.get_All_items("items");
    }
    
    private void set_available_models(){
        this.available_models = db.get_All_units("models");
    }
    
    public String race(){
        return race;
    }
    public ArrayList<Entities> available_models(){
        return available_models;
    }
    public ArrayList<Entities> available_items(){
        return available_items;
    }
}
