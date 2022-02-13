package com.trophy.Controller;

import com.trophy.entity.Category;
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

public class CategoryController implements Idoa<Category> {
    private static CategoryController categories;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement ps;

    public static CategoryController getCategories() {
        return categories;
    }
    private CategoryController(){
        SingletonConnexion cn=SingletonConnexion.getInstance();
        try {
            st=cn.getCnx().createStatement();
        }catch (SQLException es){
            Logger.getLogger(GamesController.class.getName()).log(Level.SEVERE,null,es);
        }
    }
    public static CategoryController getInstance(){
        if (categories==null)
            categories=new CategoryController();
        return categories;
    }

    @Override
    public void insert(Category o) {
        try{
            ps=SingletonConnexion.getInstance().getCnx().prepareStatement(
                    "insert into category ( ID_CATEGORY,CATEGORY) VALUES(" +
                            "?,?)"
            );
            ps.setInt(1,o.getId_category());
            ps.setString(2,o.getCategory());
            ps.executeUpdate();
        }catch(SQLException ex){
            Logger.getLogger(o.getClass().getName()).log(Level.SEVERE,null,ex);
        }
    }

    @Override
    public void delete(Category o) {
        try{
            ps=SingletonConnexion.getInstance().getCnx().prepareStatement("" +
                    "delete from category where (ID_CATEGORY=?)");
            ps.setInt(1,o.getId_category());
            ps.executeUpdate();
        }catch (SQLException ex){
            Logger.getLogger(Games.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @Override
    public List<Category> DisplayAllList() {
        List<Category> list=new ArrayList<>();
        try{
            rs=st.executeQuery("select * from category");
            while (rs.next()){
                Category s =new Category();
                s.setId_category(rs.getInt(1));
                s.setCategory(rs.getString(2));
                list.add(s);
            }
        }catch (SQLException ex){
            Logger.getLogger(Games.class.getName()).log(Level.SEVERE,null,ex);

        }
        return list;
    }

    @Override
    public Category displayById(int id) {
        Category c =new Category();
        try {
            ps = SingletonConnexion.getInstance().getCnx().prepareStatement(
                    "select * from category where (id_Category=?);");

            ps.setInt(1,id);
            rs=ps.executeQuery();
            if (rs.next()) {
                c.setId_category(rs.getInt(1));
                c.setCategory(rs.getString(2));
            }
        }catch(SQLException ex){
            Logger.getLogger(Games.class.getName()).log(Level.SEVERE,null,ex);

        }
        return c;
    }

    @Override
    public boolean update(Category o) {
        try {
            ps = SingletonConnexion.getInstance().getCnx().prepareStatement("" +
                    "update category set " +
                    "CATEGORY=? " +
                    "where (ID_CATEGORY=?)");
            ps.setString(1, o.getCategory());
            ps.setInt(2, o.getId_category());
            if (ps.executeUpdate() > 1)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(Games.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }
}
