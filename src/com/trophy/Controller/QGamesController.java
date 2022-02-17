/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.trophy.Controller;

import com.trophy.Dao.CategoryDao;
import com.trophy.Dao.GamesDao;
import com.trophy.entity.Category;
import com.trophy.entity.Games;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
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
    private Button delte_game;
    @FXML
    private Label Title;
    @FXML
    private TableView<Games> tableview_game;
    @FXML
    private TableColumn<Games, String> game_name;
    @FXML
    private TableColumn<Games, String> game_description;
    @FXML
    private TableColumn<Games, String> game_category;
    @FXML
    private TableColumn<Games, Float> games_rate;
    @FXML
    private TableColumn<Games, String> game_trophies;

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
        game_category.setCellValueFactory(cell-> new SimpleStringProperty(cell.getValue().getCategory().getCategory()));
        //game_rate.setCellValueFactory(cell -> new SimpleObjectProperty(cell.getValue().getRate().toString()));
        game_trophies.setCellValueFactory(new PropertyValueFactory<>("dummy"));
        
        
       Callback<TableColumn<Games, String>, TableCell<Games, String>> cellFactory
            =       (final TableColumn<Games, String> param) -> {
    final TableCell<Games, String> cell = new TableCell<Games, String>() {
        final Button btn = new Button("Goto Trophies");
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
                setText(null);
            } else {
                btn.setOnAction(event -> {
                    
                });
                setGraphic(btn);
                setText(null);
            }
        }
    };
    return cell;
        };
       game_trophies.setCellFactory(cellFactory);
        
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
          game_category.setCellFactory(TextFieldTableCell.forTableColumn());
          game_category.setOnEditCommit(event -> {
              
          Games g =event.getRowValue();
            g.setCategory(new Category(g.getCategory().getId_category(),event.getNewValue()));
            GamesDao.getInstance().update(g);
          });
          //game_rate.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        
        
        add_game.setOnAction(event -> {
        int idx=tableview_game.getSelectionModel().getSelectedIndex()+1;
            //ObservableList<Games> t=FXCollections.observableArrayList();
           // Games g=new Games();
            list.add(idx,new Games(CategoryDao.getInstance().DisplayAllList().get(0),"<put name>","<put Description>",5f));
            tableview_game.getSelectionModel().select(idx);
            GamesDao.getInstance().insert(tableview_game.getSelectionModel().getSelectedItem());
            tableview_game.setItems(GamesDao.getInstance().DisplayObservableList());
            tableview_game.layout();
            tableview_game.edit(idx, game_name);
        });
        
        delte_game.setOnAction(event ->{
        List<Games> l=GamesDao.getInstance().DisplayAllList();
        int ind=tableview_game.getSelectionModel().getSelectedIndex();
        GamesDao.getInstance().delete(l.get(ind-1));
        tableview_game.setItems(GamesDao.getInstance().DisplayObservableList());
        tableview_game.refresh();
        });
        
        
              
    }    
    
}
