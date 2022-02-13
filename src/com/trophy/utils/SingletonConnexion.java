package com.trophy.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SingletonConnexion {
    private String url="jdbc:mysql://127.0.0.1:3306/games";
    private String login="root",pwd="";
    private Connection cnx;
    private static SingletonConnexion instance;

    public Connection getCnx() {
        return cnx;
    }
    private SingletonConnexion(){
        try {
            cnx= DriverManager.getConnection(url,login,pwd);
        }catch (SQLException e){
            Logger.getLogger(SingletonConnexion.class.getName())
                    .log(Level.SEVERE,null,e);
        }
    }
    public static SingletonConnexion getInstance(){
        if (instance==null)
            instance=new SingletonConnexion();
        return instance;
    }
}
