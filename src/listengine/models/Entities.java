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
public interface Entities {
    
    public void setName(String name);
    public String getName();
    
    public void setCost(int cost);
    public int getCost();
    
    public void setType(String value); //rare,special osv
    public String getType();
    
    
}
