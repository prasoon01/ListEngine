/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listengine.models;

/*
 * information strukt med data 
 * @author Andreas
 */
public class DataObj{
    public String name;
    public String type;
    public int cost,champion_cost,musicna_cost,standard_cost;
    public String entitie;//model elr item
    
    public DataObj(String n, String t, int c, String e, int champion_cost, int musican_cost, int standard_cost){
        name = n;
        type = t;
        cost = c;
        entitie = e;
        this.champion_cost = champion_cost;
        this.musicna_cost = musican_cost;
        this.standard_cost = standard_cost;
    }
    
    public DataObj(String n, String t, int c, String e){
        name = n;
        type = t;
        cost = c;
        entitie = e;
        this.champion_cost = 0;
        this.musicna_cost = 0;
        this.standard_cost = 0;
    }
    
    public void setCcost(int cost){
        this.champion_cost = cost;
    }
    public void setMcost(int cost){
        this.musicna_cost =cost;
    }
    public void setScost(int cost){
        this.standard_cost =cost;
    }
    @Override
    public String toString(){
        return name+" "+type+" "+cost+" "+entitie;
    }
}