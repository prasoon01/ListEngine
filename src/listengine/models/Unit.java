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
public class Unit {
    
    private ArrayList<Item> items; //list med items unit har
    private Model model;    //modell referens
    private int unit_size;  //storleken på unit
    private int total_cost; //totala kostnaden
    private String name;    //namn på unit, ex: dwarf longbeard
    boolean champion = false,musican = false,standard= false;
    //private boolean champion,standard,musican;//om uniten innehåller command
    
    /***
     * 
     * @param model referens till en modell, ex dwarf thunderer 
     */
    public Unit(Model model){
        this.model = model;
        this.name = model.getName();
        items = new ArrayList<>();
    }
    
    private void calcTotalPrize(){
        
        total_cost = 0;//nollställer
        int modelcost = 0;
        int itemcost = 0;
        int commandcost = 0;
        
        modelcost = unit_size * model.getCost();
        //kollar om vi har en kommandgrupp i modellen vi skall räkna med på totalkostnaden.
        if(champion){
            commandcost += model.getChampCost();
        }
        if(musican){
            commandcost += model.getMusicCost();
        }
        if(standard){
            commandcost += model.getStandardCost();
        }
        
        if(!items.isEmpty()){
            for(Item i : items){
                itemcost += i.getCost();
            }
        }
        modelcost += commandcost;
        total_cost = modelcost + (itemcost*unit_size);
    }
    
    public int getTotal(){
        return total_cost;
    }
    /**
     * kalla denna när förändring sker
     */
    public void calc(){
        calcTotalPrize();
    }
    
    public void setUnitSize(int s){
        unit_size = s;
        calcTotalPrize();
    }
    public int getUnitSize(){
        return unit_size;
    }
    
    public void addItem(Item item){
        items.add(item);
        calcTotalPrize();
    }
    
    public Item getItem(int id){
        return items.get(id);
    }
    
    public void deleteItem(int id){
        items.remove(id);
        calcTotalPrize();
    }
    
    public String getName(){
        return name;
    }
    
    /**
     * uppdaterar units commandgrupp
     */
    public void uppdateUnitCommand(boolean champ, boolean music, boolean standard){
        this.champion = champ;
        this.musican = music;
        this.standard = standard;
        calcTotalPrize();
    }
    
    public boolean isChamp(){return this.champion;}
    public boolean isStandard(){return this.standard;}
    public boolean isMusican(){return this.musican;}
    
    //test
    public String getchamp(){
        String s= "";
        if(champion){s+=" chamoion";}
        if(musican){s+=" musican";}
        if(standard){s+=" standard";}
        return s;
    }
}
