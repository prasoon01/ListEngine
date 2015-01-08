/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listengine.controlers;

import Database.DBManager;
import Database.SQLiteHelper;
import java.util.ArrayList;

/**
 *Controller till användargränsnittet, hanterar input/output till och från användaren
 * @author Andreas
 */
public class ViewControler {
    
    private ListManager core; //referens till applikationens kärna
    private SQLiteHelper db;
    
    public ViewControler(){
        //starta databasen
        db = new SQLiteHelper();
        //startar list motorn
        core = new ListManager(db);
    }
    
    public void uppdate(){
        
    }
    
    public void init(){
        
    }
    
    public ArrayList get_items(){
        return core.available_items();
    }
    
    public ArrayList get_units(){
        return core.available_models();
    }
    
    //--------------------------------------------------------------------------
    //DB avfyrare
    public void start_db_input_model(){
        DBManager.add_unit(db);
    }
    public void start_db_input_item(){
        DBManager.add_item(db);
    }
    public void start_db_del_model(){
        DBManager.del_unit(db);
    }
    public void start_db_del_item(){
        DBManager.del_item(db);
    }
    //--------------------------------------------------------------------------
    //övrigt
    public void start_help(){
        
    }
}
