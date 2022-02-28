/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demo;

import com.trophy.dao.CompetitionsDao;
import com.trophy.entity.Competitions;
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author rihab bns
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) {
        try {
             Parent root = FXMLLoader.load(getClass().getResource("/com/trophy/view/Competitions.fxml"));
             //Parent root = FXMLLoader.load(getClass().getResource("/com/trophy/view/Teams.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       launch(args);
       
       //Competitions co =new Competitions(12,"helloworld");
       //co.setDateofcomp(LocalDate.now());
         //  co.setGame_name("fffffff");
       //CompetitionsDao.getInstance().insert(co);
       //CompetitionsDao.getInstance().update(co);
        //CompetitionsDao.getInstance().delete(12);
        //System.out.println(CompetitionsDao.getInstance().getAllCompetitions());
        
          
    }
    
}
