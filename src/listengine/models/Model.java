/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listengine.models;

/**
 *
 * @author Andreas
 */
public class Model implements Entities{
    
    private String name;
    private int cost,champ_cost=0,standard_cost=0,music_cost=0;
    private String type;
    
    public Model(String name, int cost){
        this.name = name;
        this.cost = cost;
    }
    
    public Model(String name, int cost, int champ_cost, int standard_cost, int music_cost){
        this.name = name;
        this.cost = cost;
        this.champ_cost = champ_cost;
        this.music_cost = music_cost;
        this.standard_cost = standard_cost;
    }
    
    public Model(String name, int cost, String type, int champ_cost, int standard_cost, int music_cost){
        this.name = name;
        this.cost = cost;
        this.champ_cost = champ_cost;
        this.music_cost = music_cost;
        this.standard_cost = standard_cost;
        this.type = type;
    }
    
    public void setChampCost(int cost){
        this.champ_cost=cost;
    }
    public void setMusicCost(int cost) {
        this.music_cost=cost;
    }
    public void setStandardCost(int cost) {
        this.standard_cost=cost;
    }
    public int getChampCost(){return this.champ_cost;}
    public int getMusicCost(){return this.music_cost;}
    public int getStandardCost(){return this.standard_cost;}
   
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public void setType(String value) {
        type = value;
    }

    @Override
    public String getType() {
        return type;
    }
    
}
