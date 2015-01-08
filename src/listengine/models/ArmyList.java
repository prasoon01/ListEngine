/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listengine.models;

import java.util.ArrayList;

/**
 *
 * @author Andreas
 */
public class ArmyList {
    
    private ArrayList<Unit> units; //en samling med units
    private int total;             //totala kostnaden
    private String name;           //listans namn
    
    public ArmyList(){
        units = new ArrayList<>();
        name = "";
    }
    
    private void calc(){
        total = 0;
        int tot = 0;
        for(Unit u : units){
            tot += u.getTotal();
        }
        total = tot;
    }
    
    
    /**
     * kalla denna när någon ändring har skett för att få rätt kostnad osv.
     */
    public void update(){
        calc();
    }
    
    public int get_total(){
        return total;
    }
    
    public void setListName(String name){
        if(!name.isEmpty()){
            this.name = name;
        }
    }
    
    public String getListName(){
        return name;
    }
    
    public void addUnit(Unit unit){
        units.add(unit);
        update();
    }
    
    public Unit getUnit(int id){
        return units.get(id);
    }
    
    public ArrayList getAllUnit(){
        return units;
    }
    
    public void deleteUnit(int id){
        units.remove(id);
    }
    
    public void clearAllUnit(){
        units.clear();
    }
}
