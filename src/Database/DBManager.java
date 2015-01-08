/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import javax.swing.*;
/**
 * Create unit skapar entiteter och lagrar i en databas.
 * @author Andreas
 */
public class DBManager {
    
   
    /**
     * Addera entiteter till databasen med hjälp av dialog
     */
    public static void add_unit(SQLiteHelper db){
        
        JTextField name_f = new JTextField(3);
        JTextField cost_f = new JTextField(3);
        JTextField type_f = new JTextField(3);
        JTextField cost_champ = new JTextField(3);
        JTextField cost_music = new JTextField(3);
        JTextField cost_standard = new JTextField(3);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Model Name (Dwarf Thunderer etc..)"));
        panel.add(name_f);
        panel.add(new JLabel("Model cost (integers)"));
        panel.add(cost_f);
        panel.add(new JLabel("Model type (special, core, rare, hero etc..)"));
        panel.add(type_f);
        panel.add(new JLabel("champion cost (integers)"));
        panel.add(cost_champ);
        panel.add(new JLabel("musican cost (integers)"));
        panel.add(cost_music);
        panel.add(new JLabel("standard cost (integers)"));
        panel.add(cost_standard);
        
        int result = JOptionPane.showConfirmDialog(null, panel, "Creat and add unit to database",JOptionPane.OK_CANCEL_OPTION);
        
        if(result == JOptionPane.OK_OPTION){
            //kontrollera att allt är ifyllt annars avbryt
            String name = name_f.getText();
            String type = type_f.getText();
            String cost = cost_f.getText();
            String costChamp = cost_champ.getText();
            String costMusic = cost_music.getText();
            String costStandard = cost_standard.getText();
            
            int cost_i, costC, costM, costS;
            if(name.isEmpty() || type.isEmpty() || cost.isEmpty() || costChamp.isEmpty() || costMusic.isEmpty() || costStandard.isEmpty()){
                JOptionPane.showMessageDialog(null, "All fields require! Even if champion/standard/musican is = 0");
                return;
            }else{
                //gör om kostnadsvärden till integer
                try {
                    cost_i = Integer.valueOf(cost);
                    costC = Integer.valueOf(costChamp);
                    costM = Integer.valueOf(costMusic);
                    costS = Integer.valueOf(costStandard);
                    
                    //lägger in värden i databasen
                    db.insert(name, type, cost_i, "models",costC,costM,costS);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Input error on cost. Only integers!");
                }
            }
        }
    }
    
    
    public static void del_unit(SQLiteHelper db){
        
        JTextField name_f = new JTextField();
        JPanel panel = new JPanel();
       
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Model Name to delete (Dwarf Thunderer etc..)"));
        panel.add(name_f);
        
        
        int result = JOptionPane.showConfirmDialog(null, panel, "Delete an unit from database",JOptionPane.OK_CANCEL_OPTION);
        
        if(result == JOptionPane.OK_OPTION){
            String name = name_f.getText();
            
            if(name.isEmpty()){
                JOptionPane.showMessageDialog(null, "All fields require!");
                return;
            }else{
                try{
                    db.delete(name, "models");
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Input error!");
                }
            }
        }
    }
    
    
    public static void add_item(SQLiteHelper db){
        JTextField name_f = new JTextField();
        JTextField cost_f = new JTextField();
        JTextField type_f = new JTextField();
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Item Name (Great Axe of snorre..)"));
        panel.add(name_f);
        panel.add(new JLabel("Item cost (integers)"));
        panel.add(cost_f);
        panel.add(new JLabel("Item type (Weapon, Armor, Talisman, Banner etc..)"));
        panel.add(type_f);
        
        int result = JOptionPane.showConfirmDialog(null, panel, "Creat and add an Item to database",JOptionPane.OK_CANCEL_OPTION);
        
        if(result == JOptionPane.OK_OPTION){
            //kontrollera att allt är ifyllt annars avbryt
            String name = name_f.getText();
            String type = type_f.getText();
            String cost = cost_f.getText();
            
            int cost_i;
            if(name.isEmpty() || type.isEmpty() || cost.isEmpty()){
                JOptionPane.showMessageDialog(null, "All fields require!");
                return;
            }else{
                //gör om kostnadsvärden till integer
                try {
                    cost_i = Integer.parseInt(cost);

                    //lägger in värden i databasen
                    db.insert(name, type, cost_i, "items");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Input error on cost. Only integers!");
                }
            }
        }
    }
    
    
    public static void del_item(SQLiteHelper db) {
        JTextField name_f = new JTextField();
        
        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Item Name to delete (Dwarf Thunderer etc..)"));
        panel.add(name_f);
        int result = JOptionPane.showConfirmDialog(null, panel, "Delete an Item from database", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String name = name_f.getText();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields require!");
                return;
            } else {
                try {
                    db.delete(name, "items");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Input error!");
                }
            }
        }
    }

}
