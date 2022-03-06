/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.dao;

import com.trophy.Control.javaMail;
import com.trophy.entity.Groups;
import com.trophy.entity.users;
import com.trophy.entity.users_groups;
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
public class user_groupsdao implements  user_groupsinterface <users_groups,users,Groups> {
    
        
     private static user_groupsdao instance;
    private Statement st;
    private ResultSet rs;
    private ResultSet rs2;
     private Statement st2;
    

    public user_groupsdao(){
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        System.out.print(cs);
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
           // Logger.getLogger(userdao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
         public  static user_groupsdao getInstance(){
        if(instance==null) 
            instance=new user_groupsdao();
        return instance;
    }
    
    /**
     *
     * @param o
     * @param v
     */
    @Override
    public void insert( users_groups o) {
         
        //String req="insert into user_groups (ID_USER,ID_GROUP,status ) " + "select users.ID_USER ,groups.ID_GROUP,'1'" + "from users,groups"+ "where users.ID_USER='"+o.getID_USER()+"' and groups.ID_GROUP='"+v.getID_GROUP()+"'";
       String req="insert into user_groups(ID_USER,ID_GROUP,status) values ('"+o.getID_USER()+"','"+o.getID_GROUP()+"','friend')";
                    
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        
    }

    @Override
    public void deleteuser(users_groups o) {
         //String req="delete from user_groups where ID_USERS_GPS="+o.getID_USERS_GPS();
         String req="DELETE FROM user_groups WHERE (user_groups.ID_USER = '"+o.getID_USER()+"' and user_groups.ID_GROUP = '"+o.getID_GROUP()+"')";
       //users_groups p=displayById(o.getID_USERS_GPS());
        
          //if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
                  System.out.println(ex);
            
        }//else System.out.println("n'existe pas");
          
        
        
        
    }    
    public ObservableList<users_groups> displayAllusers(int grp) {
       
       String req="select user_groups.ID_GROUP ,user_groups.ID_USER, user_groups.status, users.FULL_NAME from user_groups,users where users.ID_USER=user_groups.ID_USER and user_groups.id_group="+grp;
       ObservableList<users_groups> us =FXCollections.observableArrayList();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                users_groups u=new users_groups();
                u.setID_USER(rs.getInt("ID_USER"));
                u.setStatus(rs.getString("status"));
                 u.setFULL_NAME(rs.getString("FULL_NAME"));
                 u.setID_GROUP(rs.getInt("ID_GROUP"));
                
                us.add(u);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return us;
        
        
    }

    
    public String displayemail(users_groups os) {
       String req1="select ID_USERS_GPS from user_groups where ID_USER ="+os.getID_USER() ;
      
       users_groups u=new users_groups();
        
        
         try {
             
             
             
            rs=st.executeQuery(req1);
           
            if(rs.next()){
         
            u.setID_USERS_GPS(rs.getInt("ID_USERS_GPS"));}
         
         }
         
           catch (SQLException ex) {
            System.out.println(ex);
        } 
         
          String req="select EMAIL from user_groups,users where  users.ID_USER=user_groups.ID_USER  and ID_USERS_GPS ="+u.getID_USERS_GPS() ;
                 
                try{
                
                 
                rs=st.executeQuery(req);
           if(rs.next()){
               
               
                 u.setMail(rs.getString("EMAIL"));
           }
}
            
         catch (SQLException ex) {
            System.out.println(ex);
        }
   
       String m =u.getMail();
        return m;
        
       
        
        
    }

    
   
   

    
    public boolean updateuser( users_groups os)  {
         String qry;
          
         //qry2 = "select EMAIL from users where users.ID_USER=user_groups.ID_USER and user_groups.id_group='"+os.getID_GROUP();
           qry="UPDATE user_groups SET status= 'blocked' WHERE (user_groups.ID_USER = '"+os.getID_USER()+"' and user_groups.ID_GROUP = '"+os.getID_GROUP()+"')";
   
        try {
             
     
             
            if (st.executeUpdate(qry) > 0 ) {
                
          
                return true;
                
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
      return false;}
    
     public boolean updateuser2( users_groups os) {
         String qry;
         //qry = "UPDATE user_groups SET status= '0' WHERE ID_USERS_GPS="+os.getID_USERS_GPS();
           qry="UPDATE user_groups SET status= 'friend' WHERE (user_groups.ID_USER = '"+os.getID_USER()+"' and user_groups.ID_GROUP = '"+os.getID_GROUP()+"')";
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            //Logger.getLogger(userdao.class.getName()).log(Level.SEVERE, null, ex);
        }
      return false;}
    
        
   
    
    
    
    
     /*public ObservableList<Users> getSearchuser(String name)
   {
   ObservableList<Users> users =FXCollections.observableArrayList();
   try {
   //select * from users inner join user_groups on users.id_user=user_groups.id_user and user_groups.id_group="+g.getID_GROUP();//
   rs = st.executeQuery("SELECT  ID_USER,FULL_NAME  FROM users  inner join user_groups on users.id_user=user_groups.id_user and FULL_NAME="+name);
  
   while(rs.next()){
       users_groups p=new users_groups();
       Users u =new Users();
       p.setID_USER(rs.getInt(1));
       u.setFULL_NAME(rs.getString(2));
      
      
       users.add(u);
    
   }
   }catch (SQLException ex) {
            System.out.println(ex);
        }
   return users;
   }*/
public void mail() {

         try {
             javaMail.sendMail("nermine.benamara@esprit.tn");
         } catch (Exception ex) {
             System.out.println(ex);
         }
}


}
   
    
    
