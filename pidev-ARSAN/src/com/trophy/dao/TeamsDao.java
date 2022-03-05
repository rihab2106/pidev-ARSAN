/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.dao;

import com.trophy.entity.Teams;
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
 * @author Syrine
 */
public class TeamsDao implements TeamsInterface<Teams> {
    
    private static TeamsDao instance;
    private Statement st;
    private ResultSet rs;

    public TeamsDao() {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
            System.out.println(st.toString());
        } catch (SQLException ex) {
            Logger.getLogger(TeamsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static TeamsDao getInstance(){
        if(instance==null) 
            instance=new TeamsDao();
        return instance;
    }
    
    

    @Override
    public void insert(Teams t) {
            try {
          st = ConnexionSingleton.openConnection().createStatement();
          st.executeUpdate("INSERT INTO `Teams`(`ID_TEAM`,`ID_COMPETION`,`TEAM_NAME`) VALUES ('"+t.getId_team()+"','"+t.getCompetitions().getId_competion()+"','"+t.getTeam_name()+"')");
          
          //ConnexionSingleton.closeConnection();
          }catch (SQLException ex){
         /// ConnexionSingleton.closeConnection();
          Logger.getLogger(TeamsDao.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @Override
    public void delete(int id) {
                 try{
            st = ConnexionSingleton.openConnection().createStatement();
            st.executeUpdate("DELETE FROM `Teams` WHERE ID_TEAM  = " + id);
          //  ConnexionSingleton.closeConnection();
        } catch (SQLException ex) {
            //ConnexionSingleton.closeConnection();
            Logger.getLogger(TeamsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Teams> displayAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Teams displayById(int ID_TEAM) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Teams t) {
        try{
        st = ConnexionSingleton.openConnection().createStatement();
       st.executeUpdate("UPDATE `Teams` SET `ID_COMPETION`="+"'"+t.getCompetitions().getId_competion()+"'"+",`TEAM_NAME`="+"'"+t.getTeam_name()+"'"+" WHERE ID_TEAM  = "+t.getId_team());
      // ConnexionSingleton.closeConnection();
        }catch (SQLException ex) {
            Logger.getLogger(TeamsDao.class.getName()).log(Level.SEVERE, null, ex);
           // ConnexionSingleton.closeConnection();
        }
    }
    
    
    public ObservableList<Teams> getAllTeams()
   {
       ObservableList<Teams> t =FXCollections.observableArrayList();
       try{
       //st=ConnexionSingleton.openConnection().createStatement();
        rs  =  st.executeQuery("SELECT * FROM Teams");
        
        while (rs.next()){
         Teams te = new Teams();
         te.setId_team(rs.getInt(1));
         te.setCompetitions(CompetitionsDao.getInstance().displayById(rs.getInt(2)));
         te.setTeam_name(rs.getString(3));
         //te.setCreator(rs.getString(4));
         t.add(te);
        
        }
       // ConnexionSingleton.closeConnection();
       }catch (SQLException ex) {
           System.out.println(ex.getMessage());
           // ConnexionSingleton.closeConnection();
        }
       
   return t;
   }
    
    
      public ObservableList<Teams> getSearchTeams(String name)
   {
   ObservableList<Teams> teams =FXCollections.observableArrayList();
   try {
   st=ConnexionSingleton.openConnection().createStatement();
   rs = st.executeQuery("SELECT * FROM Teams WHERE TEAM_NAME LIKE '%"+name+"%'");
   
   while(rs.next()){
       Teams te = new Teams();
       te.setId_team(rs.getInt(1));
       te.setCompetitions(CompetitionsDao.getInstance().displayById(rs.getInt(2)));
       te.setTeam_name(rs.getString(3));
       //te.setCreator(rs.getString(4));
       teams.add(te);
    
   }//ConnexionSingleton.closeConnection();
   }catch (SQLException ex) {
            Logger.getLogger(TeamsDao.class.getName()).log(Level.SEVERE, null, ex);
            // ConnexionSingleton.closeConnection();
        }
   return teams;
   }
    
    
    
}
