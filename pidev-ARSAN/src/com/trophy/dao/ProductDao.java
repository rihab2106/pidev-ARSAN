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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;

/**
 *
 * @author rihab bns
 * 
 */
public class ProductDao implements ProductInterface<Product> {

    private static ProductDao instance;
    private Statement st;
    private ResultSet rs;

    public ProductDao() {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ProductDao getInstance(){
        if(instance==null) 
            instance=new ProductDao();
        return instance;
    }
    
    
    
    
    
    
    
    
    @Override
    public void insert(Product p) {
          try {
          st = ConnexionSingleton.openConnection().createStatement();
          st.executeUpdate("INSERT INTO `product`(`ID_PRODUCT`, `PROD_NAME`, `PRICE`, `Category`, `DISCOUNT`) VALUES ('"+p.getID_Product()+"','"+p.getPROD_Name()+"','"+p.getPrice()+"','"+p.getCategory()+"','"+p.getDiscount()+"')");
          
          ConnexionSingleton.closeConnection();
          }catch (SQLException ex){
          ConnexionSingleton.closeConnection();
          Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
          }
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
        try{
        st = ConnexionSingleton.openConnection().createStatement();
       st.executeUpdate("UPDATE `product` SET `PROD_NAME`="+"'"+p.getPROD_Name()+"'"+",`PRICE`="+"'"+p.getPrice()+"'"+",`Category`="+"'"+p.getCategory()+"'"+",`DISCOUNT`="+"'"+p.getDiscount()+"'"+" WHERE ID_PRODUCT = "+p.getID_Product());
       ConnexionSingleton.closeConnection();
        }catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
            ConnexionSingleton.closeConnection();
        }
    }
 
   public ObservableList<Product> getAllProduct()
   {
       ObservableList<Product> p =FXCollections.observableArrayList();
       try{
       st=ConnexionSingleton.openConnection().createStatement();
        rs  =  st.executeQuery("SELECT * FROM Product");
        
        while (rs.next()){
         Product pr = new Product();
         pr.setID_Product(rs.getInt(1));
         pr.setPROD_Name(rs.getString(2));
         pr.setPrice(rs.getFloat(3));
         pr.setDiscount(rs.getInt(5));
         pr.setCategory(rs.getString(4));
         p.add(pr);
        
        }
        ConnexionSingleton.closeConnection();
       }catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
            ConnexionSingleton.closeConnection();
        }
       
   return p;
   }
   
   public ObservableList<Product> getSearchProduct(String name)
   {
   ObservableList<Product> product =FXCollections.observableArrayList();
   try {
   st=ConnexionSingleton.openConnection().createStatement();
   rs = st.executeQuery("SELECT * FROM Product WHERE PROD_NAME LIKE '%"+name+"%'");
   
   while(rs.next()){
       Product p = new Product();
       p.setID_Product(rs.getInt(1));
       p.setPROD_Name(rs.getString(2));
       p.setPrice(rs.getFloat(3));
       p.setDiscount(rs.getInt(5));
       p.setCategory(rs.getString(5));
       product.add(p);
    
   }ConnexionSingleton.closeConnection();
   }catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
            ConnexionSingleton.closeConnection();
        }
   return product;
   }
   @Override
    public void delete(int id) {
         /*try{
            st = ConnexionSingleton.openConnection().createStatement();
            st.executeUpdate("DELETE FROM `product` WHERE ID_PRODUCT = " + id);
            ConnexionSingleton.closeConnection();
        } catch (SQLException ex) {
            ConnexionSingleton.closeConnection();
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
   } 
    
    

