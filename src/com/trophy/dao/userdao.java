/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.dao;

import com.trophy.entity.Groups;
import com.trophy.entity.users;
import com.trophy.utils.ConnexionSingleton;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author lenovo
 */
public class userdao implements userinterface<users, Groups> {

    private static userdao instance;
    private Statement st;
    private ResultSet rs;

    public userdao() {
        ConnexionSingleton cs = ConnexionSingleton.getInstance();
        System.out.print(cs);
        try {
            st = cs.getCnx().createStatement();
        } catch (SQLException ex) {
            // Logger.getLogger(userdao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static userdao getInstance() {
        if (instance == null) {
            instance = new userdao();
        }
        return instance;
    }

    @Override
    public void insert(users o) {
        

      
      String req="insert into users (ID_USER,FULL_NAME,EMAIL,PASSWORD,ISACTIVE,PRIVILEGE_) values ('"+o.getID_USER()+"','"+o.getFULL_NAME()+"','"+o.getEMAIL()+"','"+cryptWithMD5(o.getPASSWORD())+"','1','0')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.err.println(ex);

        }

    }

    @Override
    public void delete(users o) {

        String req = "delete from users where ID_USER=" + o.getID_USER();
        users p = displayById(o.getID_USER());

        if (p != null) {
            try {

                st.executeUpdate(req);

            } catch (SQLException ex) {
                 System.err.println(ex);
            }
        } else {
            System.out.println("n'existe pas");
        }

    }
     @Override
     public ObservableList<users> displayAll() {
       String req="select * from users";
       ObservableList<users> ps =FXCollections.observableArrayList();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                users p=new users();
                p.setID_USER(rs.getInt(1));
                p.setFULL_NAME(rs.getString(2));
                p.setEMAIL(rs.getString(3));
                p.setISACTIVE(rs.getInt(5));
               
                
                ps.add(p);
            }
            
        } catch (SQLException ex) {
            //Logger.getLogger(userdao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ps;
        
        
    }
    @Override
    public users displayById(int id) {
        String req = "select * from users where ID_USER  =" + id;
        users p = new users();
        try {
            rs = st.executeQuery(req);
            // while(rs.next()){
            rs.next();
            p.setID_USER(rs.getInt(1));
            p.setFULL_NAME(rs.getString(2));
        
            p.setEMAIL(rs.getString(3));
           

            //}  
        } catch (SQLException ex) {
            //Logger.getLogger(userdao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;

    }

    @Override
    public boolean updateuser(users p) {

        String qry;
        qry = "UPDATE `users` SET FULL_NAME = '" + p.getFULL_NAME() + "',  EMAIL = '" + p.getEMAIL() + "',PASSWORD ='" +cryptWithMD5(p.getPASSWORD())+ "' WHERE ID_USER="+p.getID_USER();

        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }

        } catch (SQLException ex) {
             System.err.println(ex);
        }
        return false;
    }

    
    public users displayacount(users p) {
       
        String req = "select * from users where EMAIL = "+p.getEMAIL();

        try {
            rs = st.executeQuery(req);
            // while(rs.next()){
            rs.next();
            p.setID_USER(rs.getInt(1));
            p.setFULL_NAME(rs.getString(2));
          
            p.setEMAIL(rs.getString(3));
            p.setPASSWORD(rs.getString(4));

            //}  
        } catch (SQLException ex) {
            //Logger.getLogger(userdao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;

    }

    

    /**
     *
     * @param o
     * @param g
     */
    /**@Override
    public void acceptuser(Users o, Groups g) {

        Scanner sc = new Scanner(System.in);
        String req = "insert into user_GROUPS (ID_USER, ID_GROUP,status) values ('" + o.getID_USER() + "','" + g.getID_GROUP() + "','1')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            //Logger.getLogger(userdao.class.getName()).log(Level.SEVERE, null, ex);
        }

   }*/
    
     private static MessageDigest md;
     public static String cryptWithMD5(String pass){
    try {
        md = MessageDigest.getInstance("MD5");
        byte[] passBytes = pass.getBytes();
        md.reset();
        byte[] digested = md.digest(passBytes);
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<digested.length;i++){
            sb.append(Integer.toHexString(0xff & digested[i]));
        }
        return sb.toString();
    } catch (NoSuchAlgorithmException ex) {
        //Logger.getLogger(UsersService.class.getName()).log(Level.SEVERE, null, ex);
    }
        return null;


   }
     
     
    
     public boolean checklogin(String fullname, String email,String pwd){
        try {
           
            String request="SELECT * FROM users WHERE (FULL_NAME = '"+fullname+"' and EMAIL = '"+email+"' and PASSWORD = '"+cryptWithMD5(pwd)+"' and ISACTIVE= 1 )";
            
                   rs = st.executeQuery(request);
            
            return rs.next();
        } catch (SQLException ex) {
            //Logger.getLogger(UsersService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
     
     }

   
      public boolean block(users p) {

        String qry;
        qry = "UPDATE users SET ISACTIVE = '0' WHERE ID_USER  = " + p.getID_USER();

        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }

        } catch (SQLException ex) {
             System.err.println(ex);
        }
        return false;
    }

      public boolean unblock(users p) {

        String qry;
        qry = "UPDATE users SET ISACTIVE = '1' WHERE ID_USER  = " + p.getID_USER();

        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }

        } catch (SQLException ex) {
             System.err.println(ex);
        }
        return false;
    }
      
      
       public ObservableList<users> getSearchuser(String name)
   {
   ObservableList<users> group =FXCollections.observableArrayList();
   try {
   
   rs = st.executeQuery("SELECT * FROM users WHERE FULL_NAME LIKE '%"+name+"%'");
   
   while(rs.next()){
       users p = new users();
        p.setID_USER(rs.getInt(1));
        p.setFULL_NAME(rs.getString(2));
        p.setEMAIL(rs.getString(3));
        p.setISACTIVE(rs.getInt(5));
               
       group.add(p);
    
   }
   }catch (SQLException ex) {
            System.out.println(ex);
        }
   return group;
   }
    
        

}