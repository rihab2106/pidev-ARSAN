/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.trophy.Controller;

import com.trophy.Dao.GamesDao;
import com.trophy.entity.Games;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author anasb
 */
public class GamesController implements Initializable {

    @FXML
    private GridPane GamesGrid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        // TODO
        AnchorPane p=new AnchorPane();
        FileInputStream f;
        try {
                f=new FileInputStream("C:\\Users\\anasb\\OneDrive\\Study\\Project 4\\Trophy Hunter\\TF\\Resources\\wp7489322.jpg");
                    
       
       ImageView img=new ImageView(new Image(f));
       img.setFitWidth(80);
       img.setFitHeight(120);
      // img.setPreserveRatio(true);
        //ColumnConstraints col=new ColumnConstraints(120);
       // ColumnConstraints row=new ColumnConstraints(130);
       // GamesGrid.getColumnConstraints().addAll(col,col);
      // Group group=new Group();
      //group.getChildren().add(img);
      VBox box=new VBox() ;
      box.getChildren().add(img);
      box.prefHeightProperty().bind(img.fitHeightProperty());
      box.prefWidthProperty().bind(img.fitWidthProperty());
      box.setAlignment(Pos.CENTER);
      
      GamesGrid.getChildren();
      HBox hb=new HBox();
      VBox vb=new VBox();
      GridPane.setMargin(GamesGrid,new Insets(20));
        for (Games g : GamesDao.getInstance().DisplayAllList()){
            Button b=new Button(g.getName());
           // group.getChildren().add(b);
           box.getChildren().add(b);
           p.getChildren().add(box);
          
            GamesGrid.add(p, 0, 0);
            //GamesGrid.add(p, 1, 0);
        }
         } catch (FileNotFoundException ex) {
            Logger.getLogger(GamesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void click(MouseEvent event) {
        
    }
    
}
