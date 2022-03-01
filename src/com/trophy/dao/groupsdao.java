/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.dao;

import com.trophy.entity.Groups;
import com.trophy.entity.Product;
import com.trophy.utils.ConnexionSingleton;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author lenovo
 */
public class groupsdao implements groupinterface<Groups>{
   
     private static groupsdao instance;
    private Statement st;
    private ResultSet rs;

    public groupsdao() {
        
         ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
           // Logger.getLogger(groupsdao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public static groupsdao getInstance(){
        if(instance==null) 
            instance=new groupsdao();
        return instance;
    }

    /**
     *
     * @param o
     */
    @Override
    public void delete(Groups o) {
         String req;
         req =  "delete  from groups  where ID_GROUP ="+o.getID_GROUP();
        Groups p= displayById(o.getID_GROUP());
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            //Logger.getLogger(groupsdao.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
          
       
    }

    /**
     * 
     * @param o
     */


    @Override
    public void insert(Groups o) {

 String req="insert into groups (ID_GROUP,NAME,IMG) values ('"+o.getID_GROUP()+"','"+o.getNAME()+"','"+o.getIMG()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
           // Logger.getLogger(groupsdao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public Groups displayById(int id) {
    String req="select * from groups where ID_GROUP  ="+id;
           Groups p=new Groups();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
               p.setID_GROUP(rs.getInt(1));
               
                p.setNAME(rs.getString(2));
                p.setIMG(rs.getString(3));
            //}  
        } catch (SQLException ex) {
            //Logger.getLogger(groupsdao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;  
                
        
    }

    @Override
    public boolean update(Groups os) {
         
        String qry = "UPDATE groups SET NAME = '"+os.getNAME()+"', IMG = '"+os.getIMG()+"' WHERE ID_GROUP = "+os.getID_GROUP();
        try{ if (st.executeUpdate(qry) > 0) {
                return true;
     
        }}catch (SQLException ex) {
            System.out.println(ex);
            
        }
        return false;
    }
 

    @Override
    public ObservableList<Groups> displayAll() {
       String req="select * from groups";
       ObservableList<Groups> ps =FXCollections.observableArrayList();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Groups p=new Groups();
                p.setID_GROUP(rs.getInt(1));
                p.setNAME(rs.getString(2));
                p.setIMG(rs.getString(3));
               
                
                ps.add(p);
            }
            
        } catch (SQLException ex) {
            //Logger.getLogger(userdao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ps;
        
        
    }

    
     public ObservableList<Groups> getSearchGroup(String name)
   {
   ObservableList<Groups> group =FXCollections.observableArrayList();
   try {
   
   rs = st.executeQuery("SELECT * FROM groups WHERE NAME LIKE '%"+name+"%'");
   
   while(rs.next()){
       Groups p = new Groups();
       p.setID_GROUP(rs.getInt(1));
       p.setNAME(rs.getString(2));
       p.setIMG(rs.getString(3));
      
       group.add(p);
    
   }
   }catch (SQLException ex) {
            System.out.println(ex);
        }
   return group;
   }
    
        
        
    }

    

   
    
    
  
    

