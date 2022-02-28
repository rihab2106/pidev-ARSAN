/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.dao;

import com.trophy.entity.Product;
import com.trophy.utils.ConnexionSingleton;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author rihab bns
 */
public class ShopDao implements ProductInterface<Product> {
    private static ShopDao instance;
    private Statement st;
    private ResultSet rs;

    
    
    
    
    public ObservableList<Product> getProducttoShop()
   {
       ObservableList<Product> pl =FXCollections.observableArrayList();
       try{
       st=ConnexionSingleton.openConnection().createStatement();
        rs  =  st.executeQuery("SELECT `PROD_NAME`, `PRICE`, `Category`, `DISCOUNT` FROM Product");
        
        while (rs.next()){
         Product pr = new Product();
         
         pr.setPROD_Name(rs.getString(1));
         pr.setPrice(rs.getFloat(2));
         pr.setDiscount(rs.getFloat(4));
         pr.setCategory(rs.getString(3));
         
         pl.add(pr);
        
        }
        ConnexionSingleton.closeConnection();
       }catch (SQLException ex) {
            Logger.getLogger(ShopDao.class.getName()).log(Level.SEVERE, null, ex);
            ConnexionSingleton.closeConnection();
        }
       
   return pl;
   }

    @Override
    public void insert(Product p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Product> displayAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product displayById(int ID_Product) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Product p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
