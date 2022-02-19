/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trophy.dao;

import com.trophy.entity.News;
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
 * @author Mouelhi
 */
public class NewsDao implements NewsInterface<News>{
    private static NewsDao instance;
    private Statement st;
    private ResultSet rs;
    public NewsDao() {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(NewsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static NewsDao getInstance(){
        if(instance==null) 
            instance=new NewsDao();
        return instance;
    }

    @Override
    public void insert(News n) {
    String req=
    "insert into news (HEADLINE,CONTENT,IMG) values "
   + "('"+n.getHeadline()+"','"+n.getContent()+"','"+n.getImgurl()+"')";
    
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(NewsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ObservableList<News> getAllNews()
   {
       ObservableList<News> n =FXCollections.observableArrayList();
       try{
       st=ConnexionSingleton.openConnection().createStatement();
        rs  =  st.executeQuery("SELECT * FROM news");
        
        while (rs.next()){
         News ns = new News();
         ns.setNewsid(rs.getInt(1));
         ns.setHeadline(rs.getString(2));
         ns.setContent(rs.getString(4));
         ns.setImgurl(rs.getString(3));
         n.add(ns);
        
        }
        ConnexionSingleton.closeConnection();
       }catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
            ConnexionSingleton.closeConnection();
        }
       
   return n;
   }
    
    

    @Override
    public void delete(News n) {
        String req="delete from news where news_id="+n.getNewsid();
        News ns=displayById(n.getNewsid());
        
          if(ns!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(NewsDao.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("This news don't exist");

    }

    @Override
    public List<News> displayAll() {
        String req="select * from news";
        ObservableList<News> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                News n=new News();
                n.setNewsid(rs.getInt(1));
                n.setContent(rs.getString("CONTENT"));
                n.setHeadline(rs.getString("HEADLINE"));
                n.setImgurl(rs.getString("IMG"));
                list.add(n);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(NewsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public News displayById(int idnews) {
        String req="select * from news where id ="+idnews;
           News n=new News();
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
            rs.next();
                n.setNewsid(rs.getInt("ID_NEWS"));
                n.setHeadline(rs.getString("HEADLINE"));
                n.setContent(rs.getString("CONTENT"));
                n.setImgurl(rs.getString("IMG"));
            }  
        } catch (SQLException ex) {
            Logger.getLogger(NewsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return n;
    }

    @Override
    public boolean update(News n)
    {  String qry = "UPDATE news SET CONTENT = '"+n.getContent()+"', IMG = '"+n.getImgurl()+"',HEADLINE='"+n.getHeadline()+"' WHERE ID_NEWS = "+n.getNewsid();
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(NewsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
}
