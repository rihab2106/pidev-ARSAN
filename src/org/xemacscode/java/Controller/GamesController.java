package Controller;

import entity.Games;
import utils.SingletonConnexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GamesController implements Idoa<Games>{
    private static GamesController games;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement ps;

    public static GamesController getGames() {
        return games;
    }
    private GamesController(){
        SingletonConnexion cn=SingletonConnexion.getInstance();
        try {
            st=cn.getCnx().createStatement();
        }catch (SQLException es){
            Logger.getLogger(GamesController.class.getName()).log(Level.SEVERE,null,es);
        }
    }
    public static GamesController getInstance(){
        if (games ==null)
            games=new GamesController();
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
                g.setCategory(CategoryController.getInstance().displayById(rs.getInt(2)));
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
                g.setCategory(CategoryController.getInstance().displayById(rs.getInt(2)));
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
}
