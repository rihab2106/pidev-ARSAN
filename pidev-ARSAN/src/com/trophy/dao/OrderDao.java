/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.dao;

import com.trophy.entity.Order;
import com.trophy.utils.ConnexionSingleton;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rihab bns
 */
public class OrderDao {
    private static OrderDao instance;
    private Statement st;
    private ResultSet rs;
    
    
    
    public void insert(Order O) {
          try {
          st = ConnexionSingleton.openConnection().createStatement();
          st.executeUpdate("INSERT INTO `order`(`OrderID`, `CardNumber`, `CardPassword`, `Name`, `Month`, `Year`) VALUES ('"+O.getOrderID()+"','"+O.getCardNumber()+"','"+O.getCardNumber()+"','"+O.getName()+"','"+O.getMonth()+"','"+O.getYear()+"')");
          ConnexionSingleton.closeConnection();
          }catch (SQLException ex){
          ConnexionSingleton.closeConnection();
          Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    
    
}
