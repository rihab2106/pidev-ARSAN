package com.trophy.Dao;

import com.trophy.entity.Games;
import com.trophy.entity.Trophies;
import com.trophy.utils.SingletonConnexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrophiesDao implements Idoa<Trophies> {
    private static TrophiesDao trophies;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement ps;

    public static TrophiesDao getTrophies() {
        return trophies;
    }
    private TrophiesDao(){
        SingletonConnexion cn=SingletonConnexion.getInstance();
        try {
            st=cn.getCnx().createStatement();
        }catch (SQLException es){
            Logger.getLogger(GamesDao.class.getName()).log(Level.SEVERE,null,es);
        }
    }
    public static TrophiesDao getInstance(){
        if (trophies==null)
            trophies=new TrophiesDao();
        return trophies;
    }
    @Override
    public void insert(Trophies o) {
        try{
            ps=SingletonConnexion.getInstance().getCnx().prepareStatement(
                    "insert into trophies ( ID_GAME,TITLE, DESCRIPTION, PLATFORM, DIFFICULITY) VALUES(" +
                            "?,?,?,?,?)"
            );
            ps.setInt(1,o.getGame().getId_game());
            ps.setString(2,o.getTitle());
            ps.setString(3,o.getDescription());
            ps.setString(4,o.getPlatform());
            ps.setString(5,o.getDifficulty());
            ps.executeUpdate();

        }catch(SQLException ex){
            Logger.getLogger(o.getClass().getName()).log(Level.SEVERE,null,ex);
        }

    }

    @Override
    public void delete(Trophies o) {
        try{
            ps=SingletonConnexion.getInstance().getCnx().prepareStatement("" +
                    "delete from trophies where (ID_TROPHY=?)");
            ps.setInt(1,o.getId_trophy());
            ps.executeUpdate();
        }catch (SQLException ex){
            Logger.getLogger(Games.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @Override
    public List<Trophies> DisplayAllList() {
        List<Trophies> list=new ArrayList<>();
        try{
            rs=st.executeQuery("select * from trophies");
            while (rs.next()){
                Trophies t =new Trophies();
                t.setId_trophy(rs.getInt(1));
                t.setGame(GamesDao.getInstance().displayById(rs.getInt(2)));
                t.setTitle(rs.getString(3));
                t.setDescription(rs.getString(4));
                t.setPlatform(rs.getString(5));
                t.setDifficulty(rs.getString(6));
                list.add(t);
            }
        }catch (SQLException ex){
            Logger.getLogger(Games.class.getName()).log(Level.SEVERE,null,ex);

        }
        return list;
    }

    @Override
    public Trophies displayById(int id) {
        Trophies t=new Trophies();
        try {
            ps = SingletonConnexion.getInstance().getCnx().prepareStatement("" +
                    "select * from trophies where (ID_TROPHY=?)");
            ps.setInt(1,id);
            rs=ps.executeQuery();
            if (rs.next()) {
                t.setId_trophy(rs.getInt(1));
                t.setGame(GamesDao.getInstance().displayById(rs.getInt(2)));
                t.setTitle(rs.getString(3));
                t.setDescription(rs.getString(4));
                t.setPlatform(rs.getString(5));
                t.setDifficulty(rs.getString(6));
            }
        }catch(SQLException ex){
            Logger.getLogger(Games.class.getName()).log(Level.SEVERE,null,ex);

        }
        return t;
    }

    @Override
    public boolean update(Trophies o) {
        try{
            ps=SingletonConnexion.getInstance().getCnx().prepareStatement("" +
                    "update trophies set " +
                    "ID_GAME=?, TITLE=?," +
                    "DESCRIPTION=?, PLATFORM=?," +
                    "DIFFICULITY=? " +
                    "where (ID_TROPHY=?)");
            ps.setInt(1,o.getGame().getId_game());
            ps.setString(2,o.getTitle());
            ps.setString(3,o.getDescription());
            ps.setString(4,o.getPlatform());
            ps.setString(5,o.getDifficulty());
            ps.setInt(6,o.getId_trophy());
            if (ps.executeUpdate()>=1)
                return true;
        }catch (SQLException ex){
            Logger.getLogger(Games.class.getName()).log(Level.SEVERE,null,ex);

        }
        return false;
    }
}
