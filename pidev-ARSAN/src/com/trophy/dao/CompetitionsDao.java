/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.dao;

import com.trophy.entity.Competitions;
import com.trophy.utils.ConnexionSingleton;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

/**
 *
 * @author Syrine
 */
public class CompetitionsDao  implements CompetitionsInterface<Competitions> {

    private static CompetitionsDao instance;
    private Statement st;
    private ResultSet rs;

    public CompetitionsDao() {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CompetitionsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static CompetitionsDao getInstance(){
        if(instance==null) 
            instance=new CompetitionsDao();
        return instance;
    }
    
    @Override
    public void insert(Competitions c) {
       try {
          st = ConnexionSingleton.openConnection().createStatement();
          st.executeUpdate("INSERT INTO Competitions (ID_COMPETION,GAME_NAME,DATEOFCOMP) VALUES ('"+c.getId_competion()+"','"+c.getGame_name()+"','"+c.getDateofcomp()+"')");
          
          //ConnexionSingleton.closeConnection();
          }catch (SQLException ex){
          //ConnexionSingleton.closeConnection();
          Logger.getLogger(CompetitionsDao.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @Override
    public void delete(int id) {
          try{
            st = ConnexionSingleton.openConnection().createStatement();
            st.executeUpdate("DELETE FROM `Competitions` WHERE 	ID_COMPETION = " + id);
            // ConnexionSingleton.closeConnection();
        } catch (SQLException ex) {
            //ConnexionSingleton.closeConnection();
            Logger.getLogger(CompetitionsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Competitions> displayAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Competitions displayById(int Id_competion) {
          Competitions co =new Competitions();
       try{
       st=ConnexionSingleton.openConnection().createStatement();
        rs  =  st.executeQuery("SELECT * FROM competitions where ID_COMPETION="+String.valueOf(Id_competion));
        
        while (rs.next()){
            
         co.setId_competion(rs.getInt(1));
         co.setGame_name(rs.getString(2));
         co.setDateofcomp(rs.getDate(3).toLocalDate());
        
         
       
         
        
        }
       // ConnexionSingleton.closeConnection();
       }catch (SQLException ex) {
            Logger.getLogger(CompetitionsDao.class.getName()).log(Level.SEVERE, null, ex);
           // ConnexionSingleton.closeConnection();
        }
       return co;
    }
    
        @Override
        public void update(Competitions c) {
        try{
        st = ConnexionSingleton.openConnection().createStatement();
       st.executeUpdate("UPDATE `Competitions` SET `GAME_NAME`="+"'"+c.getGame_name()+"'"+",`DATEOFCOMP`="+"'"+c.getDateofcomp()+"'"+" WHERE ID_COMPETION = "+c.getId_competion());
      // ConnexionSingleton.closeConnection();
        }catch (SQLException ex) {
            Logger.getLogger(CompetitionsDao.class.getName()).log(Level.SEVERE, null, ex);
           // ConnexionSingleton.closeConnection();
        }
    }
    
    
    public ObservableList<Competitions> getAllCompetitions()
   {
       ObservableList<Competitions> c =FXCollections.observableArrayList();
       try{
       //st=ConnexionSingleton.openConnection().createStatement();
        rs  =  st.executeQuery("SELECT * FROM competitions");
        
        while (rs.next()){
            
         Competitions co  = new Competitions();
         co.setId_competion(rs.getInt(1));
         co.setGame_name(rs.getString(2));
         co.setDateofcomp(rs.getDate(3).toLocalDate());
        
         
         c.add(co);
         
        
        }
      //  ConnexionSingleton.closeConnection();
       }catch (SQLException ex) {
            Logger.getLogger(CompetitionsDao.class.getName()).log(Level.SEVERE, null, ex);
           // ConnexionSingleton.closeConnection();
        }
       return c;
   }
    
    public ObservableList<Competitions> getSearchCompetitions(String name)
   {
   ObservableList<Competitions> competitions =FXCollections.observableArrayList();
   try {
   st=ConnexionSingleton.openConnection().createStatement();
   rs = st.executeQuery("SELECT * FROM Competitions WHERE GAME_NAME LIKE '%"+name+"%'");
   
   while(rs.next()){
       Competitions co = new Competitions();
       co.setId_competion(rs.getInt(1));
       co.setGame_name(rs.getString(2));
       co.setDateofcomp(rs.getDate(3).toLocalDate());
 
       competitions.add(co);
    
   }//ConnexionSingleton.closeConnection();
   }catch (SQLException ex) {
            System.out.println(ex.getMessage());
           // ConnexionSingleton.closeConnection();
        }
   return competitions;
   }

   
   

    
    
}
