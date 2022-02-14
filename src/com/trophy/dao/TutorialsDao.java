package com.trophy.Dao;

import com.trophy.entity.Games;
import com.trophy.entity.Tutorials;
import com.trophy.utils.SingletonConnexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TutorialsDao implements Idoa<Tutorials> {
    private static TutorialsDao tutorials;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement ps;

    public static TutorialsDao getTutorials() {
        return tutorials;
    }
    private TutorialsDao(){
        SingletonConnexion cn=SingletonConnexion.getInstance();
        try {
            st=cn.getCnx().createStatement();
        }catch (SQLException es){
            Logger.getLogger(GamesDao.class.getName()).log(Level.SEVERE,null,es);
        }
    }
    public static TutorialsDao getInstance(){
        if (tutorials==null)
            tutorials=new TutorialsDao();
        return tutorials;
    }

    @Override
    public void insert(Tutorials o) {
        try{
            ps=SingletonConnexion.getInstance().getCnx().prepareStatement(
                    "insert into tutorials (ID_USER, ID_TROPHY, PATH, CONTENT) VALUES(" +
                            "?,?,?,?)"
            );
            //ps.setInt(1,o.getUser());
            ps.setInt(2,o.getTrophy().getId_trophy());
            ps.setString(3,o.getPath());
            ps.setString(4,o.getContent());
            ps.executeUpdate();

        }catch(SQLException ex){
            Logger.getLogger(o.getClass().getName()).log(Level.SEVERE,null,ex);
        }
    }

    @Override
    public void delete(Tutorials o) {
        try{
            ps=SingletonConnexion.getInstance().getCnx().prepareStatement("" +
                    "delete from tutorials where (ID_TUTORIAL=?)");
            ps.setInt(1,o.getId_tutorial());
            ps.executeUpdate();
        }catch (SQLException ex){
            Logger.getLogger(Games.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @Override
    public List<Tutorials> DisplayAllList() {
        List<Tutorials> list=new ArrayList<>();
        try{
            rs=st.executeQuery("select * from tutorials");
            while (rs.next()){
                Tutorials t =new Tutorials();
                t.setId_tutorial(rs.getInt(1));
                //t.setUser();
                t.setTrophy(TrophiesDao.getInstance().displayById(rs.getInt("id_trophy")));
                t.setPath(rs.getString("path"));
                t.setContent(rs.getString("content"));
                list.add(t);
            }
        }catch (SQLException ex){
            Logger.getLogger(Games.class.getName()).log(Level.SEVERE,null,ex);

        }
        return list;
    }

    @Override
    public Tutorials displayById(int id) {
        Tutorials t=new Tutorials();
        try {
            ps = SingletonConnexion.getInstance().getCnx().prepareStatement("" +
                    "select * from tutorials where (ID_TUTORIAL=?)");
            ps.setInt(1,id);
            rs=ps.executeQuery();
            if (rs.next()) {
                t.setId_tutorial(rs.getInt(1));
                //t.setUser();
                t.setTrophy(TrophiesDao.getInstance().displayById(rs.getInt("id_trophy")));
                t.setPath(rs.getString("path"));
                t.setContent(rs.getString("content"));
            }
        }catch(SQLException ex){
            Logger.getLogger(Games.class.getName()).log(Level.SEVERE,null,ex);

        }
        return t;
    }

    @Override
    public boolean update(Tutorials o) {
        try{
            ps=SingletonConnexion.getInstance().getCnx().prepareStatement("" +
                    "update tutorials set " +
                    "ID_USER=?, ID_TROPHY=?," +
                    "path=?, CONTENT=?");
            //ps.setInt(1,o.getUser());
            ps.setInt(2,o.getTrophy().getId_trophy());
            ps.setString(3,o.getPath());
            ps.setString(4,o.getContent());
            if (ps.executeUpdate()>1)
                return true;
        }catch (SQLException ex){
            Logger.getLogger(Games.class.getName()).log(Level.SEVERE,null,ex);

        }
        return false;
    }
}
