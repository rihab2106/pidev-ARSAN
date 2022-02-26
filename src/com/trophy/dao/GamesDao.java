package com.trophy.Dao;

import com.trophy.entity.Games;
import com.trophy.utils.SingletonConnexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GamesDao implements Idoa<Games>{
    private static GamesDao games;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement ps;

    public static GamesDao getGames() {
        return games;
    }
    private GamesDao(){
        SingletonConnexion cn=SingletonConnexion.getInstance();
        try {
            st=cn.getCnx().createStatement();
        }catch (SQLException es){
            Logger.getLogger(GamesDao.class.getName()).log(Level.SEVERE,null,es);
        }
    }
    public static GamesDao getInstance(){
        if (games ==null)
            games=new GamesDao();
        return games;
    }

    @Override
    public void insert(Games o) {

        try{
            ps=SingletonConnexion.getInstance().getCnx().prepareStatement(
                    "insert into games ( ID_CATEGORY, NAME, DESCRIPTION, RATE) VALUES(" +
                            "?,?,?,?)"
            );
            ps.setInt(1,o.getCategory().getId_category());
            ps.setString(2,o.getName());
            ps.setString(3,o.getDescription());
            ps.setFloat(4,o.getRate());
            ps.executeUpdate();

        }catch(SQLException ex){
            Logger.getLogger(o.getClass().getName()).log(Level.SEVERE,null,ex);
        }
    }

    @Override
    public void delete(Games o) {
        try{
            ps=SingletonConnexion.getInstance().getCnx().prepareStatement("" +
                    "delete from games where (ID_GAME=?)");
            ps.setInt(1,o.getId_game());
            ps.executeUpdate();
        }catch (SQLException ex){
            Logger.getLogger(Games.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @Override
    public List<Games> DisplayAllList() {
        List<Games> list=new ArrayList<>();
        try{
            rs=st.executeQuery("select * from games");
            while (rs.next()){
                Games g =new Games();
                g.setId_game(rs.getInt(1));
                g.setCategory(CategoryDao.getInstance().displayById(rs.getInt(2)));
                g.setName(rs.getString(3));
                g.setDescription(rs.getString(4));
                g.setRate(rs.getFloat(5));
                list.add(g);
            }
        }catch (SQLException ex){
            Logger.getLogger(Games.class.getName()).log(Level.SEVERE,null,ex);

        }
        return list;
    }
    
    public ObservableList<Games> DisplayObservableList() {
        ObservableList<Games> list=FXCollections.observableArrayList();
        try{
            rs=st.executeQuery("select * from games");
            while (rs.next()){
                Games g =new Games();
                g.setId_game(rs.getInt(1));
                g.setCategory(CategoryDao.getInstance().displayById(rs.getInt(2)));
                g.setName(rs.getString(3));
                g.setDescription(rs.getString(4));
                g.setRate(rs.getFloat(5));
                list.add(g);
            }
        }catch (SQLException ex){
            Logger.getLogger(Games.class.getName()).log(Level.SEVERE,null,ex);

        }
        return list;
    }
    
    @Override
    public Games displayById(int id) {
        Games g=new Games();
        try {
            ps = SingletonConnexion.getInstance().getCnx().prepareStatement("" +
                    "select * from games where (id_game=?)");
            ps.setInt(1,id);
            rs=ps.executeQuery();
            if (rs.next()) {
                g.setId_game(rs.getInt(1));
                g.setCategory(CategoryDao.getInstance().displayById(rs.getInt(2)));
                g.setName(rs.getString(3));
                g.setDescription(rs.getString(4));
                g.setRate(rs.getFloat(5));
            }
        }catch(SQLException ex){
            Logger.getLogger(Games.class.getName()).log(Level.SEVERE,null,ex);

        }
        return g;
    }

    @Override
    public boolean update(Games o) {
        try{
            ps=SingletonConnexion.getInstance().getCnx().prepareStatement("" +
                    "update games set " +
                    "ID_CATEGORY=?, NAME=?," +
                    "DESCRIPTION=?, RATE=? " +
                    "where (ID_GAME=?)");
            ps.setInt(1,o.getCategory().getId_category());
            ps.setString(2,o.getName());
            ps.setString(3,o.getDescription());
            ps.setFloat(4,o.getRate());
            ps.setInt(5,o.getId_game());
            if (ps.executeUpdate()>1)
                return true;
        }catch (SQLException ex){
            Logger.getLogger(Games.class.getName()).log(Level.SEVERE,null,ex);

        }
        return false;
    }
    
    public ObservableList searchByName(String n){
       ObservableList<Games> list=FXCollections.observableArrayList();
        try {
            ps=SingletonConnexion.getInstance().getCnx().prepareStatement("select * from GAMES where (name like ?)");
            ps.setString(1, n+"%");
            rs=ps.executeQuery();
            while(rs.next()){
                Games g =new Games();
                g.setId_game(rs.getInt(1));
                g.setCategory(CategoryDao.getInstance().displayById(rs.getInt(2)));
                g.setName(rs.getString(3));
                g.setDescription(rs.getString(4));
                g.setRate(rs.getFloat(5));
                list.add(g);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Games.class.getName()).log(Level.SEVERE,null,ex);
        }
        return list;
    }
    public ObservableList<Games> Sort(){
        ObservableList<Games> list=FXCollections.observableArrayList();
        try {
            ps=SingletonConnexion.getInstance().getCnx().prepareStatement("select * from GAMES order by name");
           
            rs=ps.executeQuery();
            while(rs.next()){
                Games g =new Games();
                g.setId_game(rs.getInt(1));
                g.setCategory(CategoryDao.getInstance().displayById(rs.getInt(2)));
                g.setName(rs.getString(3));
                g.setDescription(rs.getString(4));
                g.setRate(rs.getFloat(5));
                list.add(g);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Games.class.getName()).log(Level.SEVERE,null,ex);
        }
        return list;
    }
    
}
