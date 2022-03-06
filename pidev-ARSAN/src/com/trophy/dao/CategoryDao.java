package com.trophy.dao;

import com.trophy.entity.Category;
import com.trophy.entity.Games;
import com.trophy.utils.ConnexionSingleton;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryDao   {
    private static CategoryDao categories;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement ps;

    public static CategoryDao getCategories() {
        return categories;
    }
    private CategoryDao(){
        ConnexionSingleton cn=ConnexionSingleton.getInstance();
        try {
            st=cn.getCnx().createStatement();
        }catch (SQLException es){
            Logger.getLogger(GamesDao.class.getName()).log(Level.SEVERE,null,es);
        }
    }
    public static CategoryDao getInstance(){
        if (categories==null)
            categories=new CategoryDao();
        return categories;
    }

    
    public void insert(Category o) {
        try{
            ps=ConnexionSingleton.getInstance().getCnx().prepareStatement(
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

    
    public void delete(Category o) {
        try{
            ps=ConnexionSingleton.getInstance().getCnx().prepareStatement("" +
                    "delete from category where (ID_CATEGORY=?)");
            ps.setInt(1,o.getId_category());
            ps.executeUpdate();
        }catch (SQLException ex){
            Logger.getLogger(Games.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    
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

    
    public Category displayById(int id) {
        Category c =new Category();
        try {
            ps = ConnexionSingleton.getInstance().getCnx().prepareStatement(
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

    
    public boolean update(Category o) {
        try {
            ps = ConnexionSingleton.getInstance().getCnx().prepareStatement("" +
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
