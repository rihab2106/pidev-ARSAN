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
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
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
    private TableColumn<Games, ChoiceBox> game_category;
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
        
        
        
        
        //tableview_game.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        ObservableList<Games> list= GamesDao.getInstance().DisplayObservableList();
        tableview_game.setItems(list);
        game_name.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
        game_description.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDescription()));
        game_category.setCellValueFactory(new PropertyValueFactory<>("dummy"));
         //game_category.setCellValueFactory(new PropertyValueFactory<>("dummy"));
        games_rate.setCellValueFactory(new PropertyValueFactory<>("Rate"));
        game_trophies.setCellValueFactory(new PropertyValueFactory<>("dummy"));
        
        
       Callback<TableColumn<Games, String>, TableCell<Games, String>> cellFactory
            =       (final TableColumn<Games, String> param) -> {
    final TableCell<Games, String> cell = new TableCell<Games, String>() {
        //final Button btn = new Button("Goto Trophies");
        ChoiceBox btn=new ChoiceBox();
        @Override
        public void updateItem(String item, boolean empty) {
            btn.setItems(FXCollections.observableArrayList(CategoryDao.getInstance().DisplayAllList()));
            tableview_game.getItems().forEach(g ->{
            btn.setValue(g.getCategory());
            });
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
                setText(null);
            } else {
                btn.setOnAction(event -> {
                    Games g=tableview_game.getSelectionModel().getSelectedItem();
                  //  g.setCategory((Category)btn.getValue());
                    GamesDao.getInstance().update(g);
                    
                });
                setGraphic(btn);
                setText(null);
            }
        }
    };
    return cell;
        };
       game_trophies.setCellFactory(cellFactory);
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
                    
                    comboBox = new ChoiceBox();
                    comboBox.getItems().addAll(FXCollections.observableArrayList(CategoryDao.getInstance().DisplayAllList()));
                   comboBox.setValue(FXCollections.observableArrayList(CategoryDao.getInstance().DisplayAllList()).get(0));
                     setGraphic(comboBox);
                     super.updateTableView(tableview_game);
                   if(!isEmpty())
                    {
                        super.startEdit();

                        

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
        
        
        add_game.setOnAction(event -> {
        
            //ObservableList<Games> t=FXCollections.observableArrayList();
           // Games g=new Games();
           // list.add(idx,);
            //tableview_game.getSelectionModel().select(idx);
            Games g=new Games(CategoryDao.getInstance().DisplayAllList().get(0),"<put name>","<put Description>",5f);
            GamesDao.getInstance().insert(g);
            tableview_game.setItems(GamesDao.getInstance().DisplayObservableList());
            tableview_game.layout();
            int idx=GamesDao.getInstance().DisplayObservableList().size()-1;
            tableview_game.edit(idx, game_name);
        });
        
        delete_game.setOnAction(event ->{
        List<Games> l=GamesDao.getInstance().DisplayAllList();
        int ind=tableview_game.getSelectionModel().getSelectedIndex();
        GamesDao.getInstance().delete(l.get(ind));
        tableview_game.setItems(GamesDao.getInstance().DisplayObservableList());
        tableview_game.refresh();
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Game Deleted !!",ButtonType.OK);
        a.show();
        });
        
        tableview_game.getSelectionModel().selectedItemProperty().addListener((obj, olds,newc) -> {
           
             game_acc.getPanes().clear();
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
            Trophies t=new Trophies(g,"Nothing","Nothing","Nothing","Nothing");
            TrophiesDao.getInstance().insert(t);
            game_acc.getPanes().add(new TitledPane("Title :"+t.getTitle(),DisplayTrophies(t)));
            //tableview_game.getSelectionModel().select(g);
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
        
        TextField tplat=new TextField("nothing");
        TextField ttit=new TextField("nothing");
        TextField tdiff=new TextField("nothing");
        TextArea tdisc=new TextArea("nothing");
        
        gp.setPadding(new Insets(10,10,10,10));
        GridPane.setMargin(gp, new Insets(20));
        tplat.setText(t.getPlatform());
        tdisc.setText(t.getDescription());
        ttit.setText(t.getTitle());
        tdiff.setText(t.getDifficulty());
        
        tplat.textProperty().addListener((obj,old,ne)-> {
            
        t.setPlatform(ne);
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
        tdiff.textProperty().addListener((obj,old,ne)-> {
            
        t.setDifficulty(ne);
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
    
}
