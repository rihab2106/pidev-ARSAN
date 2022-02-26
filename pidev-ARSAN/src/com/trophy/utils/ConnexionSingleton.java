/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rihab bns
 */
public class ConnexionSingleton {
    private static String url="jdbc:mysql://127.0.0.1:3306/games";
    private String login="root";
    private String pwd="";
    private static Connection cnx;
    private static ConnexionSingleton instance;

    public Connection getCnx() {
        return cnx;
    }
    
    
    private ConnexionSingleton() {
        try {
            cnx=DriverManager.getConnection(url, login, pwd);
            System.out.println("conx etqblie");
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   public static ConnexionSingleton getInstance(){
       
       if(instance==null)
           instance=new ConnexionSingleton();
       return instance;
   }
   public static Connection openConnection()
    {
       if(cnx == null){
         try {          
            cnx = (Connection) DriverManager.getConnection(url,"root","");                   
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
        return cnx;
    }
   
   //public static void closeConnection()
    //{       
      //  if(cnx != null)
        //  cnx = null;
    //}
    
    
}
