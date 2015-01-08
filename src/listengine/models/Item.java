/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listengine.models;

/**
 *Beskriver ett item
 * @author Andreas
 */
public class Item implements Entities{

    private String name;
    private int cost;
    private String type;
    
    public Item(String name, int cost, String type){
        this.name = name;
        this.cost = cost;
        this.type = type;
    }
    
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
