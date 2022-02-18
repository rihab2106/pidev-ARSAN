/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.dao;

import com.trophy.entity.Users;
import com.trophy.utils.ConnexionSingleton;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;






/**
 *
 * @author lenovo
 */
public class userdao implements userinterface <Users> {
    
     private static userdao instance;
    private Statement st;
    private ResultSet rs;

    public userdao(){
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        System.out.print(cs);
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
           // Logger.getLogger(userdao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
         public  static userdao getInstance(){
        if(instance==null) 
            instance=new userdao();
        return instance;
    }

    @Override
    public void insert(Users o) {
        
        String req="insert into users (ID_USER,FULL_NAME,IMG,EMAIL,PASSWORD) "
                + "values ('"+o.getID_USER()+"','"+o.getFULL_NAME()+"','"+o.getIMG()+"','"+o.getEMAIL()+"','"+o.getPASSWORD()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            //Logger.getLogger(userdao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void delete(Users o) {
       
      String req="delete from users where id="+o.getID_USER();
        Users p=displayById(o.getID_USER());
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            //Logger.getLogger(userdao.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
          
        
        
        
    }

    
    
    
    @Override
    public List<Users> displayAll() {
        
        String req="select * from users";
        List<Users> list=new ArrayList<>();
        
        
        try {
            
            rs=st.executeQuery(req);
            while(rs.next()){
                Users p=new Users();
                p.setID_USER(rs.getInt(1));
                p.setFULL_NAME(rs.getString(3));
                p.setIMG(rs.getString(4));
                p.setEMAIL(rs.getString(5));
                p.setPASSWORD(rs.getString(6));
                p.setISACTIVE(rs.getInt(7));
                p.setPRIVILEGE_(rs.getInt(8));
                
                list.add(p);
            }
            
        } catch (SQLException ex) {
            //Logger.getLogger(userdao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    
    @Override
    public Users displayById(int id) {
         String req="select * from users where ID_USER  ="+id;
           Users p=new Users();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
              p.setID_USER(rs.getInt(1));
                p.setFULL_NAME(rs.getString(3));
                p.setIMG(rs.getString(4));
                p.setEMAIL(rs.getString(5));
                p.setPASSWORD(rs.getString(6));
                p.setISACTIVE(rs.getInt(7));
                p.setPRIVILEGE_(rs.getInt(8));
                
            //}  
        } catch (SQLException ex) {
            //Logger.getLogger(userdao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;  
        
        
        
    }

    @Override
    public boolean update(Users p) {
        
        String qry;
         qry = "UPDATE users SET FULL_NAME = '"+p.getFULL_NAME()+"', IMG = '"+p.getIMG()+"', EMAIL = '"+p.getEMAIL()+"',PASSWORD ='"+p.getPASSWORD()+"' WHERE ID_USER  = "+p.getID_USER();
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            //Logger.getLogger(userdao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
   

    @Override
    public Users displayacount() {
        Users p=new Users();
       String req="select * from users where ID_USER  ="+p.getID_USER();
           
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
              p.setID_USER(rs.getInt(1));
                p.setFULL_NAME(rs.getString(2));
                p.setIMG(rs.getString(3));
                p.setEMAIL(rs.getString(4));
                p.setPASSWORD(rs.getString(5));
                
                
            //}  
        } catch (SQLException ex) {
            //Logger.getLogger(userdao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;  
        
        
    }
   
    
   

    @Override
    public void acceptuser(Users o) {
 
        Scanner sc= new Scanner(System.in);  
         String req="insert into users_GROUPS (ID_USER, ID_GROUP,JOINING_DATE) values ('"+o.getID_USER()+"','"+sc.nextInt()+"', SYSDATE)";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            //Logger.getLogger(userdao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

   
    
    
    
    }

         
         
         
         
         
    
    
    
    
    
    
    
    
    
    
    

   
    

