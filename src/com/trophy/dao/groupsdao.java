/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.dao;

import com.trophy.entity.Groups;
import com.trophy.utils.ConnexionSingleton;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
         req =  "UPDATE users SET ID_GROUP = Null where ID_GROUP ="+o.getID_GROUP();
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
        
        
        String qry;
         qry = "UPDATE Groups SET  NAME= '"+os.getNAME()+"',IMG='"+os.getIMG()+"', where ID_USER ="+os.getID_GROUP();
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            //Logger.getLogger(groupsdao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Groups> displayAll() {
       String req="select * from groups";
        List<Groups> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Groups p=new Groups();
                p.setID_GROUP(rs.getInt(1));
                p.setNAME(rs.getString(2));
                p.setIMG(rs.getString(3));
               
                
                list.add(p);
            }
            
        } catch (SQLException ex) {
            //Logger.getLogger(userdao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        
        
    }

    
        
        
    }

    

   
    
    
  
    

