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

/**
 *
 * @author Mouelhi
 */

public class CommentDao implements CommentInterface<Comment,News> {
 private static CommentDao instance;
    private Statement st;
    private ResultSet rs;
    
    private CommentDao() {
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
                + "where ID_NEWS="+n.getNewsid()+"and ID_NEWS="+n.getNewsid();
        Comment cc=displayById(c.getIdcomm(),n.getNewsid());
        
          if(cc!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(NewsDao.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("This comment doesn't exist");
    }

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

    
    
    

    