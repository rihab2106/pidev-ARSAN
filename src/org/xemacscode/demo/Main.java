/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xemacscode.demo;

import com.trophy.Dao.GamesDao;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

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
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

/**
 *
 * @author rihab bns
 */
public class Main extends Application {
     private Stage primaryStage;
    private Parent parentPage;
    
    @Override
    public void start(Stage primaryStage) throws IOException{
        
        this.primaryStage=primaryStage;
         parentPage = FXMLLoader.load(getClass().getResource("/com/trophy/view/QGames.fxml"));
        
         Scene scene = new Scene(parentPage);
        
         this.primaryStage.setTitle("TF");
        this.primaryStage.setScene(scene);
        
        this.primaryStage.show();
        
        
    }
   
    
   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
