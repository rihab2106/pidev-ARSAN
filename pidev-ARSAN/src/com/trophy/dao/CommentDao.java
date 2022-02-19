/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trophy.dao;

import com.trophy.entity.Comment;
import com.trophy.entity.News;
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

public class CommentDao implements CommentInterface<Comment,News> {
 private static CommentDao instance;
    private Statement st;
    private ResultSet rs;
    
    public CommentDao() {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
        }}

    @Override
    public void insert(Comment c, News n) {
       
        String req=
    "insert into comments (COMMENT,LIKES,DISLIKES,ID_NEWS) values "
   + "('"+c.getComcontent()+"','"+0+"','"+0+"','"+n.getNewsid()+"')";
    
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
    
    
    }

    @Override
    public void delete(Comment c, News n) {
        String req="delete from comments "
                + "where ID_COMMENT="+c.getIdcomm();
//        Comment cc=displayById(c.getIdcomm(),n.getNewsid());
        
         // if(cc!=null)
              try {
           
            st.executeUpdate(req);
             
       } catch (SQLException ex) {
            Logger.getLogger(NewsDao.class.getName()).log(Level.SEVERE, null, ex);
       // }else System.out.println("This comment doesn't exist");
    }}

    @Override
    public List<Comment> displayAll(News n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Comment displayById(int ID_COMMENT, int ID_NEWS) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Comment p, News n) {
        String qry = "UPDATE comments SET COMMENT = '"+p.getComcontent()+"'"
            + ", LIKES = '"+p.getLikes()+"',DISLIKES='"+p.getDislikes()+"' WHERE ID_NEWS = "+n.getNewsid()+"AND ID_COMMENT= "+p.getIdcomm();
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(NewsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }
    public ObservableList<Comment> getAllComments(News n)
   {
       ObservableList<Comment> c =FXCollections.observableArrayList();
       try{
       st=ConnexionSingleton.openConnection().createStatement();
        rs  =  st.executeQuery
                ("SELECT * "
                + "FROM comments "
                + "where ID_NEWS="+n.getNewsid());
     
        while (rs.next()){
         
         Comment cm=new Comment();
         cm.setIdcomm(rs.getInt(1));
         cm.setComcontent(rs.getString(3));
         cm.setLikes(rs.getInt(4));
         cm.setDislikes(rs.getInt(5));
         c.add(cm);
        }
        ConnexionSingleton.closeConnection();
       }catch (SQLException ex) {
            //Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
            ConnexionSingleton.closeConnection();
        }
       
   return c;
   }

}

    
    
    

    