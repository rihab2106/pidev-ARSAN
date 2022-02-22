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

import java.io.IOException;
import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;

import javafx.beans.property.SimpleStringProperty;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;

import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;

import javafx.scene.transform.Rotate;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.converter.FloatStringConverter;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

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
    @FXML
    private AnchorPane game_paine;
    @FXML
    private HBox game_hbox_view;
    @FXML
    private Button game_stat;
    @FXML
    private MenuBar game_menu_bar;
   

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
          /*game_category.setCellFactory(column -> new TableCell()
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
          );*/
          
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
        MenuItem updateGame=new MenuItem("Modify");
        updateGame.setOnAction(event -> {
        try {
                SceneTransition(SetUpForm(tableview_game.getSelectionModel().getSelectedItem()));
            } catch (IOException ex) {
                Logger.getLogger(QGamesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        game_con.getItems().addAll(addtrophy,updateGame);
        
        
       
       /* game_in_search.textProperty().addListener((obj,old,n)->{
        tableview_game.setItems(GamesDao.getInstance().sreachByName(n));
        });*/
       game_in_search.textProperty().addListener((obj,old,ne) -> {
           if (!game_in_search.getText().isEmpty())
       tableview_game.setItems(GamesDao.getInstance().searchByName(ne));
           else 
               tableview_game.setItems(GamesDao.getInstance().DisplayObservableList());
           
       });
       
        game_trophies.setVisible(false);
        
        
        
        MenuItem fetchInfo=new MenuItem("Fetch Online");
        fetchInfo.setOnAction(e-> {
            try {
                
                Games g=tableview_game.getSelectionModel().getSelectedItem();
                g.setDescription(FetchGamesOnline("God Of War"));
            GamesDao.getInstance().update(g);
            tableview_game.setItems(GamesDao.getInstance().DisplayObservableList());
            } catch (IOException ex) {
                Logger.getLogger(QGamesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        game_con.getItems().add(fetchInfo);
        
              
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
    
    private Pane SetUpForm(Games g ){
    VBox box=new VBox();
   box.setStyle("-fx-background-color: #373e43");
    box.setMinWidth(game_paine.getWidth()-20);
    box.setMinHeight(game_paine.getHeight()-20);
    TextField tname=new TextField();
    TextArea tdesc=new TextArea();
    ChoiceBox cb=new ChoiceBox(FXCollections.observableArrayList(CategoryDao.getInstance().DisplayAllList()));
    TextField trate =new TextField();
    Button ok=new Button("OK");
    Button cancel=new Button("Cancel");
    
    cancel.setOnAction(event -> {
    game_paine.getChildren().remove(box);
    game_paine.getChildren().addAll(Title,Hbox_games_button,game_hbox_view,game_menu_bar);
    });
    
    ok.setOnAction(e -> {
    g.setName(tname.getText());
    g.setDescription(tdesc.getText());
    g.setCategory((Category)cb.getValue());
    g.setRate(Float.valueOf(trate.getText()));
    GamesDao.getInstance().update(g);
    tableview_game.setItems(GamesDao.getInstance().DisplayObservableList());
    game_paine.getChildren().remove(box);
    game_paine.getChildren().addAll(Title,Hbox_games_button,game_hbox_view,game_menu_bar);
    });
    
    tname.setText(g.getName());
    tdesc.setText(g.getDescription());
    cb.setValue(g.getCategory());
    trate.setText(String.valueOf(g.getRate()));
    
    ok.setMinSize(200, 30);
    cancel.setMinSize(200, 30);
    HBox h=new HBox(ok,cancel);
    h.setAlignment(Pos.CENTER);
    h.setSpacing(20);
    box.getChildren().addAll(new Label("Game Name"),tname,
                             new Label("Description"),tdesc,
                             new Label("Category"),cb,
                             new Label("Rate"),trate,
                             h);
    box.setSpacing(20);
   
    return box;
    
    }
    
    private void SceneTransition(Parent root) throws IOException{
    Timeline t=new Timeline();
    
    Scene s=add_game.getScene();
    root.translateYProperty().set(s.getHeight());
    
    root.setLayoutX(10);
    root.setLayoutY(10);
    game_paine.getChildren().add(root);
    
    KeyValue kv=new KeyValue(root.translateYProperty(), 0,Interpolator.EASE_IN);
    KeyFrame kf=new KeyFrame(Duration.seconds(1),kv);
    
    KeyFrame kff=new KeyFrame(Duration.seconds(0), new KeyValue(game_hbox_view.translateYProperty(),0,Interpolator.EASE_OUT));
            
    t.getKeyFrames().add(kf);
    //t.getKeyFrames().add(kff);
    t.setOnFinished(event -> {
    game_paine.getChildren().removeAll(Title,Hbox_games_button,game_hbox_view,game_menu_bar);
    });
    t.play();
    
      /*((Button)((HBox)((VBox)root).getChildren().get(8)).getChildren().get(1)).setOnAction(event -> {
         
   t.getKeyFrames().clear();
   t.getKeyFrames().add(kff);
    t.setOnFinished(e -> {
    game_paine.getChildren().remove(root);
    game_paine.getChildren().addAll(Title,Hbox_games_button,game_hbox_view);
    });
    t.play();
      });*/
    
    
    
    }

    @FXML
    private void show_stat(ActionEvent event) throws IOException {
       String[] diff={"Very easy","Easy","Medium","Hard","Impossible"};
        CategoryAxis xAxis=new CategoryAxis(FXCollections.observableArrayList(GamesDao.getInstance().DisplayObservableList()
                                            .stream().map(g -> g.getName()).distinct().collect(Collectors.toList())));
       
        xAxis.setLabel("Games");
        xAxis.setGapStartAndEnd(true);
        
        NumberAxis yAxis=new NumberAxis();
        yAxis.setLabel("Count");
        BarChart<String,Number> bchart=new BarChart(xAxis,yAxis);
        
        bchart.setTitle("Comparison between games by the number of trophies' difficulity");
        
        ObservableList<Games> listg=GamesDao.getInstance().DisplayObservableList();
        for (String d: diff){
            XYChart.Series<String, Number> s=new XYChart.Series<>();
            s.setName(d);
            for (Games g : listg){
            s.getData().add(new XYChart.Data<>(g.getName(),countTrophy(g, d)));   
            }
           
            bchart.getData().add(s);
        }
         VBox box=new VBox();
   box.setStyle("-fx-background-color: #373e43");
    box.setMinWidth(game_paine.getWidth()-20);
    box.setMinHeight(game_paine.getHeight()-20);
    Button rt=new Button("Return");
    rt.setMaxSize(120, 40);
    box.setSpacing(20);
     rt.setOnAction(e -> {
    game_paine.getChildren().remove(box);
    game_paine.getChildren().addAll(Title,Hbox_games_button,game_hbox_view,game_menu_bar);
    });
    bchart.setAnimated(true);
    box.getChildren().addAll(rt,bchart);
        SceneTransition(box);
        
        
    }
    
    private Long countTrophy(Games g,String s){
    ObservableList<Trophies> list=FXCollections.observableArrayList(TrophiesDao.getInstance().DisplayAllList());
    return list.stream().filter(t -> t.getGame().getId_game()==g.getId_game())
                .map(t -> t.getDifficulty())
                .filter(t -> t.equals(s))
                .count();
    }
    
    private String FetchGamesOnline(String name) throws IOException {
    
    
    /*params = {
    'action': 'query',
    'format': 'json',
    'titles': subject,
    'prop': 'links',
    'pllimit': 'max',
    'redirects':''
}*/ 
    String jsonBody = "{\"action\":query,\"format\":\"json\"titles\":"+name+"\"prop\":links\"pllimit\":max\"redirects\":''}";
    String url="https://en.wikipedia.org/wiki/"+"Doom_(2016_video_game)";
    Document d=Jsoup.connect(url).get();
    String desc=d.getElementsByClass("infobox-data").eachText().stream().reduce("", (t1,t2)-> t1+System.lineSeparator()+t2);
    
    /*Connection.Response r=Jsoup.connect("https://en.wikipedia.org/").header("Content-Type", "application/json")
        .header("Accept", "application/json").method(Connection.Method.POST)
            .requestBody(jsonBody).execute();*/
        
    
    return desc;
    
    }
    
    
}
