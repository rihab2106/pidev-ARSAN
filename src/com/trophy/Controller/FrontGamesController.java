/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.trophy.Controller;

import com.trophy.Dao.GamesDao;
import com.trophy.Dao.TrophiesDao;
import com.trophy.entity.Games;
import com.trophy.entity.Trophies;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

/**
 * FXML Controller class
 *
 * @author anasb
 */
public class FrontGamesController implements Initializable {

    
    @FXML
    private ScrollPane game_scroll;
    @FXML
    private AnchorPane game_paine;
    @FXML
    private AnchorPane game_paine2;
    @FXML
    private Button game_return;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       float j=0;
       GridPane game_grid =new GridPane();
       game_return.setVisible(false);
       
       
       
       
       game_grid.setPadding(new Insets(10,10,10,10));
       game_grid.setHgap(5);
       game_grid.setVgap(5);
        ObservableList<Games> list=GamesDao.getInstance().DisplayObservableList();
        for(Games g:list){
            VBox vbox=new VBox();
          File f=new File("src/com/trophy/Resources/"+g.getName()+".jpg");
               if (!f.exists()) f=new File("src/com/trophy/Resources/wp7489322.jpg");
            try {
                ImageView img=new ImageView(new Image(new FileInputStream(f)));
                img.setFitWidth(426);
                img.setFitHeight(240);
                img.setSmooth(true);
                img.setOnMouseClicked(e -> {
                   game_paine2.getChildren().remove(game_scroll);
                    //game_scroll.setVisible(false);
                   game_return.setVisible(true);
                   
                    
                    try {
                        game_paine2.getChildren().add(TrophiesNode(g));
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(FrontGamesController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                Label l=new Label(g.getName());
        l.setLabelFor(img);
        vbox.getChildren().addAll(img,l);
        vbox.setAlignment(Pos.CENTER);
        
        game_grid.addRow((int)j,vbox);
         j+=0.5;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FrontGamesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        game_scroll.setContent(game_grid);
        
        game_return.setOnAction(e ->{
           game_paine2.getChildren().clear();
           game_paine2.getChildren().add(game_scroll);

           game_scroll.setVisible(true);
       
       game_return.setVisible(false);
       });
        
    }    
    
    private Node TrophiesNode(Games g) throws FileNotFoundException{
        HBox hbox=new HBox();
        hbox.setSpacing(5);
        
        File f=new File("src/com/trophy/Resources/"+g.getName()+".jpg");
        if (!f.exists()) f=new File("src/com/trophy/Resources/wp7489322.jpg");
        ImageView img=new ImageView(new Image(new FileInputStream(f)));
        img.setFitWidth(426);
                img.setFitHeight(240);
                img.setSmooth(true);
        Text desc=new Text(g.getDescription());
        desc.setBoundsType(TextBoundsType.LOGICAL);
        desc.setWrappingWidth(490);
        
        Label rate=new Label("Rate: "+g.getRate().toString());
        Separator hs=new Separator(Orientation.VERTICAL);
        hbox.getChildren().addAll(img,rate,hs,desc);
        Accordion acc=new Accordion();
       
        
        TrophiesDao.getInstance().DisplayAllList()
                .stream()
                .filter(t -> t.getGame().getId_game()==g.getId_game())
                .forEach((Trophies t) ->{
                     HBox tro=new HBox();
                     tro.setSpacing(5);
                    tro.getChildren().addAll(new Label("Platform : "+t.getPlatform()),
                        new Label("Difficulity: "+t.getDifficulty()));
                acc.getPanes().add(new TitledPane(t.getTitle()+": "+
                        t.getDescription(),tro));
                });
        VBox vbox=new VBox(hbox,acc);
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(5);
        
        
        ScrollPane s=new ScrollPane(vbox);
        s.minHeight(588);
        s.minWidth(895);
        return s;
                
    }

    
    
}
