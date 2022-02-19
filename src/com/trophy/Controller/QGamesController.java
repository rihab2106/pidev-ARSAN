/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.trophy.Controller;

import com.trophy.Dao.CategoryDao;
import com.trophy.Dao.GamesDao;
import com.trophy.Dao.TrophiesDao;
import com.trophy.entity.Category;
import com.trophy.entity.Games;
import com.trophy.entity.Trophies;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.Observable;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.transform.Rotate;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.converter.FloatStringConverter;
import sun.plugin2.jvm.RemoteJVMLauncher;
import sun.plugin2.jvm.RemoteJVMLauncher.CallBack;

/**
 * FXML Controller class
 *
 * @author anasb
 */
public class QGamesController implements  Initializable {

    @FXML
    private HBox Hbox_games_button;
    @FXML
    private Button add_game;
    @FXML
    private Button delete_game;
    @FXML
    private Label Title;
    @FXML
    private TableView<Games> tableview_game;
    @FXML
    private TableColumn<Games, String> game_name;
    @FXML
    private TableColumn<Games, String> game_description;
    @FXML
    private TableColumn<Games, Category> game_category;
    @FXML
    private TableColumn<Games, Float> games_rate;
    @FXML
    private TableColumn<Games, String> game_trophies;
    @FXML
    private Accordion game_acc;
    @FXML
    private ContextMenu game_con;
    @FXML
    private TextField game_in_search;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        //Media m=new Media(new File("../../../../Resources/Mouse-Click-00-c-FesliyanStudios.com.mp3").toURI().toString());
        
       
        //tableview_game.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        ObservableList<Games> list= GamesDao.getInstance().DisplayObservableList();
        tableview_game.setItems(list);
        game_name.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
        game_description.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDescription()));
        game_category.setCellValueFactory(new PropertyValueFactory<>("Category"));
         //game_category.setCellValueFactory(new PropertyValueFactory<>("dummy"));
        games_rate.setCellValueFactory(new PropertyValueFactory<>("Rate"));
        game_trophies.setCellValueFactory(new PropertyValueFactory<>("dummy"));
        
        
       Callback<TableColumn<Games, Category>, TableCell<Games, Category>> cellFactory
            =       (final TableColumn<Games, Category> param) -> {
    final TableCell<Games, Category> cell = new TableCell<Games, Category>() {
        //final Button btn = new Button("Goto Trophies");
        ChoiceBox btn=new ChoiceBox();
        @Override
        public void updateItem(Category item, boolean empty) {
            btn.setItems(FXCollections.observableArrayList(CategoryDao.getInstance().DisplayAllList()));
            
            super.updateItem(item, empty);
            
            
            if (empty) {
                setGraphic(null);
                setText(null);
            } else {
                btn.setOnAction(event -> {
                    
                  //Games g=tableview_game.getSelectionModel().getSelectedItem();
                 // Games g=new Games();
                  // g.setCategory((Category)btn.getValue());
                        //System.out.println(GamesDao.getInstance().update(g));
                    
                    
                });
             
               btn.setValue(btn.getItems().get(1));
                setGraphic(btn);
                setText(null);
                
            }
        }
    };
    return cell;
        };
       //game_trophies.setCellFactory(cellFactory);
       //game_category.setCellFactory(cellFactory);
        tableview_game.setEditable(true);
        game_name.setCellFactory(TextFieldTableCell.forTableColumn());
        game_name.setOnEditCommit((CellEditEvent<Games, String> event) -> {
            Games g =event.getRowValue();
            g.setName(event.getNewValue());
            GamesDao.getInstance().update(g);
        });
         game_description.setCellFactory(TextFieldTableCell.forTableColumn());
         game_description.setOnEditCommit(event -> {
         Games g =event.getRowValue();
            g.setDescription(event.getNewValue());
            GamesDao.getInstance().update(g);
         });
          game_category.setCellFactory(column -> new TableCell()
                  {
                private ChoiceBox comboBox;
                

                @Override
                public void startEdit()
                {
                    
                   
                   if(!isEmpty())
                    {
                        super.startEdit();

                         comboBox = new ChoiceBox();
                    comboBox.getItems().addAll(FXCollections.observableArrayList(CategoryDao.getInstance().DisplayAllList()));
                   comboBox.setValue(tableview_game.getSelectionModel().getSelectedItem().getCategory());
                     setGraphic(comboBox);
                     super.updateTableView(tableview_game);
                     super.updateItem(comboBox, true);

                        comboBox.setPrefWidth(getWidth());
                        

                       

                        comboBox.setOnAction(e ->
                        {
                            Category c=(Category)comboBox.getSelectionModel().getSelectedItem();
                            Games g=tableview_game.getSelectionModel().getSelectedItem();
                            g.setCategory(c);
                            GamesDao.getInstance().update(g);
                        });
                    }
                }
            }
          );
          
          //game_category.
          games_rate.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
          games_rate.setOnEditCommit(event ->{
          Games g=event.getRowValue();
          g.setRate(event.getNewValue());
          GamesDao.getInstance().update(g);
          });
        
        AudioClip mp=new AudioClip(getClass().getResource("Mouse-Click-00-c-FesliyanStudios.com.mp3").toExternalForm());
        
        add_game.setOnAction(event -> {
        
            //ObservableList<Games> t=FXCollections.observableArrayList();
           // Games g=new Games();
           // list.add(idx,);
            //tableview_game.getSelectionModel().select(idx);
            Games g=new Games(CategoryDao.getInstance().DisplayAllList().get(0),"<put name>","<put Description>",5f);
            GamesDao.getInstance().insert(g);
            mp.play();
            tableview_game.setItems(GamesDao.getInstance().DisplayObservableList());
            tableview_game.layout();
            int idx=GamesDao.getInstance().DisplayObservableList().size()-1;
            AnimateButton(add_game);
            tableview_game.edit(idx, game_name);
        });
        
        delete_game.setOnAction(event ->{
            mp.play();
        List<Games> l=GamesDao.getInstance().DisplayAllList();
        int ind=tableview_game.getSelectionModel().getSelectedIndex();
        GamesDao.getInstance().delete(l.get(ind));
        tableview_game.setItems(GamesDao.getInstance().DisplayObservableList());
        tableview_game.refresh();
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Game Deleted !!",ButtonType.OK);
        a.showAndWait();
        });
        
        tableview_game.getSelectionModel().selectedItemProperty().addListener((obj, olds,newc) -> {
           
             game_acc.getPanes().clear();
             if (tableview_game.isPressed() )
            TrophiesDao.getInstance().DisplayAllList().forEach((Trophies t) -> {
                if (t.getGame().getId_game()==obj.getValue().getId_game()){
                    game_acc.getPanes().add(new TitledPane("Title "+t.getTitle(),DisplayTrophies(t)));
                }
            });
            
        });        
        game_con.getItems().clear();
        MenuItem addtrophy=new MenuItem("Add Trophy");
        addtrophy.setOnAction(event ->{
            Games g=tableview_game.getSelectionModel().getSelectedItem();
            Trophies tro=new Trophies(g,"Nothing","Nothing","Nothing","Nothing");
            TrophiesDao.getInstance().insert(tro);
            game_acc.getPanes().clear();
            TrophiesDao.getInstance().DisplayAllList().forEach((Trophies t) -> {
                if (t.getGame().getId_game()==g.getId_game()){
                    game_acc.getPanes().add(new TitledPane("Title "+t.getTitle(),DisplayTrophies(t)));
                }});
        });
        game_con.getItems().add(addtrophy);
        
       
       /* game_in_search.textProperty().addListener((obj,old,n)->{
        tableview_game.setItems(GamesDao.getInstance().sreachByName(n));
        });*/
       game_in_search.setOnAction(event -> {
           if (!game_in_search.getText().isEmpty())
       tableview_game.setItems(GamesDao.getInstance().searchByName(game_in_search.getText()));
           else 
               tableview_game.setItems(GamesDao.getInstance().DisplayObservableList());
           
       });
       
        
        
              
    }    
    
    private Pane DisplayTrophies(Trophies t){
    
        VBox b=new VBox();
        GridPane gp=new GridPane();
        
        Label ldisc=new Label("Description");
        Label lplat=new Label("Platform");
        Label ltit=new Label("Title");
        Label ldiff=new Label("Difficulitiy");
        
       // TextField tplat=new TextField("nothing");
        ChoiceBox tplat=new ChoiceBox();
        tplat.getItems().addAll("PS5","XBox sereis X","Nintendo","PC");
        TextField ttit=new TextField("nothing");
        //TextField tdiff=new TextField("nothing");
        ChoiceBox tdiff=new ChoiceBox(FXCollections.observableArrayList("Very easy","Easy","Medium","Hard","Impossible"));
        TextArea tdisc=new TextArea("nothing");
        
        gp.setPadding(new Insets(10,10,10,10));
        GridPane.setMargin(gp, new Insets(20));
        tplat.setValue(t.getPlatform());
        tdisc.setText(t.getDescription());
        ttit.setText(t.getTitle());
        tdiff.setValue(t.getDifficulty());
        
        tplat.setOnAction((event)-> {
            
            //System.out.println(event);
            t.setPlatform((String)tplat.getSelectionModel().getSelectedItem());
            TrophiesDao.getInstance().update(t);
        });
        
        tdisc.textProperty().addListener((obj,old,ne)-> {
            
        t.setDescription(ne);
            TrophiesDao.getInstance().update(t);
        });
        
        ttit.textProperty().addListener((obj,old,ne)-> {
            
        t.setTitle(ne);
            TrophiesDao.getInstance().update(t);
        });
        tdiff.setOnAction(event-> {
            
        t.setDifficulty((String)tdiff.getSelectionModel().getSelectedItem());
            TrophiesDao.getInstance().update(t);
        });
        
        
        tdisc.setPrefColumnCount(25);
        tdisc.setPrefRowCount(10);
        
        
        gp.add(ltit,0,0);
        gp.add(ldisc, 0, 1);
        gp.add(lplat,0,2);
        gp.add(ldiff, 0, 3);
        gp.add(ttit,1, 0);
        gp.add(tdisc,1,1);
        gp.add(tplat,1,2);
        gp.add(tdiff,1,3);
        
       // gp.addRow(4, new Button("Delete"));
        gp.setAlignment(Pos.BASELINE_LEFT);
        gp.autosize();
        Button delete=new Button("Delete");
        delete.setOnAction(event -> {
        TrophiesDao.getInstance().delete(t);
        game_acc.getPanes().remove(game_acc.getExpandedPane());
        });
        
        
        b.getChildren().addAll(gp,delete);
        b.setAlignment(Pos.CENTER);
        
        return b;
    }
    
    
    public void AnimateButton(Button n){
       n.setRotationAxis(Rotate.Z_AXIS);
       Timeline t=new Timeline();
        t.autoReverseProperty().set(true);
        t.setCycleCount(4);
       t.getKeyFrames().addAll(
        new KeyFrame(Duration.ZERO, 
            new KeyValue(n.translateXProperty(), 0)
        ),
        new KeyFrame(new Duration(1000), 
            new KeyValue(n.translateXProperty(), 20)
        )
        ,new KeyFrame(Duration.millis(1000), new KeyValue(n.translateXProperty(), -50))
    );
       
        t.play();
      
    
    }
    
    
}
