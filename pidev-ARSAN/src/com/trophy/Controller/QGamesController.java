/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.trophy.Controller;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.speech.freetts.VoiceManager;

import com.trophy.dao.CategoryDao;
import com.trophy.dao.GamesDao; 
import com.trophy.dao.TrophiesDao;
import com.trophy.entity.Category;
import com.trophy.entity.ChatBot;
import com.trophy.entity.Games;
import com.trophy.entity.Trophies;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;

import java.util.List;
import java.util.ResourceBundle;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.animation.FadeTransition;
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
import javafx.scene.Node;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import javafx.scene.transform.Rotate;
import javafx.scene.web.WebView;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.converter.FloatStringConverter;
import org.controlsfx.control.NotificationPane;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.PopOver;
import org.controlsfx.dialog.FontSelectorDialog;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Locale;
import java.util.regex.Pattern;
import javafx.scene.control.TextFormatter;
import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.EngineStateError;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;

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
    @FXML
    private MenuItem fun;
    @FXML
    private MenuItem game_sort;
    @FXML
    private Circle game_cercle;
    
   // private Synthesizer syn;
    private com.sun.speech.freetts.Voice v;
    @FXML
    private Button btnhome;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        try {
            //Media m=new Media(new File("../../../../Resources/Mouse-Click-00-c-FesliyanStudios.com.mp3").toURI().toString());
            
            
            game_cercle.setFill(new ImagePattern(new Image(new FileInputStream("png-clipart-computer-icons-desktop-chatbot-icon-blue-angle.png"))));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(QGamesController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
         
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
        File f=new File("src/com/trophy/Resources/"+l.get(ind).getName()+".jpg");
        if (f.exists()) f.delete();
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Game Deleted !!",ButtonType.OK);
        a.showAndWait();
        });
        tableview_game.getSelectionModel().selectedItemProperty().addListener((obj, olds,newc) -> {
           
             game_acc.getPanes().clear();
             
             if (tableview_game.isPressed() )
            TrophiesDao.getInstance().DisplayAllList().stream().sorted((Trophies t1,Trophies t2)->{
                List<String> diff=Arrays.asList("Very easy","Easy","Medium","Hard","Impossible","Nothing");
            int tr1,tr2;
            tr1=diff.indexOf(t1.getDifficulty());
            tr2=diff.indexOf(t2.getDifficulty());
            return tr1-tr2;
            })
                    .forEach((Trophies t) -> {
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
            Games g=tableview_game.getSelectionModel().getSelectedItem();
             StackPane sp=new StackPane();    
            Pane root=SetUpForm(g,sp);
           
            File file=new File("src/com/trophy/Resources/"+
                        g.getName()+".jpg");
            InputStream f;
            Image img=null;
            if (file.exists()){
            f=new FileInputStream(file);
           
            
            if (f!=null)
               img=new Image(f);
            }
                        
               
                ImageView i=new ImageView(img);
                i.setOpacity(0.2);
                i.setPreserveRatio(true);
                 i.setFitHeight(game_paine.getHeight());
                 i.setFitWidth(game_paine.getWidth());
               sp.getChildren().addAll(i,root);
               
                
                //root.setBackground(b);
                
                SceneTransition(sp);
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
                
                Games g1=FetchGamesOnline(g.getName());
                if (g1!=null){
                g.setDescription(g1.getDescription());
                g.setRate(g1.getRate());
                g.setName(g1.getName());
                GamesDao.getInstance().update(g);}
                
            tableview_game.setItems(GamesDao.getInstance().DisplayObservableList());
            } catch (IOException ex) {
                Logger.getLogger(QGamesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
        /*PopOver p=new PopOver();
        p.setTitle("Description");
    p.setFadeOutDuration(Duration.ONE);
    p.setFadeInDuration(Duration.ONE);
     p.setAnimated(true);
     p.setAutoHide(true);
        tableview_game.setRowFactory(tableView -> {
    final TableRow<Games> row = new TableRow<>();
    row.hoverProperty().addListener((observable) -> {
        final Games g = row.getItem();
        
        if (row.isHover() && g != null && !row.isEditing()) {
            TextArea t=new TextArea(g.getDescription());
            t.setEditable(false);
            t.setWrapText(true);
            p.setContentNode(t);
           
            p.setWidth(600);
            
            p.show(row);
        } else {
            p.hide(Duration.ONE);
        }
    });

    return row;
});*/
        
        
        
        MenuItem lookFurther=new MenuItem("Look Online");
        lookFurther.setOnAction(e -> {
            String n=tableview_game.getSelectionModel().getSelectedItem()
                    .getName();
           
            try {
                
                Desktop.getDesktop().browse(new URI("https://www.google.com/search?q="+URLEncoder.encode(n, "UTF-8")));
            } catch (Exception ex) {
                Logger.getLogger(QGamesController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        MenuItem toFrench=new MenuItem("To french");
        
         toFrench.setOnAction(e -> {
            try {
                Games g =g=tableview_game.getSelectionModel().getSelectedItem();
                Document d=Jsoup.connect("https://api.nlpcloud.io/v1/opus-mt-en-fr/translation")
                        .header("Authorization", "Token 27e8cb58e8a53c25f1bf61e5d32d8fa1b9b6d8a0")
                        .ignoreContentType(true)
                        .userAgent("Mozilla")
                        //.data("'text'","'John Doe has been working for Microsoft in Seattle since 1999.'")
                        .ignoreHttpErrors(true)
                        .requestBody("{\"text\":\""+g.getDescription().replaceAll("\n", "")
                                .replaceAll("\r", "")+"\"}")
                        .post();
                ObjectMapper obj=new ObjectMapper();
                JsonNode jn=obj.readTree(d.body().text());
                g.setDescription(jn.get("translation_text").asText());
                GamesDao.getInstance().update(g);
                tableview_game.setItems(GamesDao.getInstance().DisplayObservableList());
                
            } catch (IOException ex) {
                Logger.getLogger(QGamesController.class.getName()).log(Level.SEVERE, null, ex);
            }
         
        

         });
        
        MenuItem fontSelector=new MenuItem("Fonts");
        fontSelector.setOnAction(ee -> {
            FontSelectorDialog s=new FontSelectorDialog(null);
            s.showAndWait();
            tableview_game.getParent().setStyle(""
                    + ".root{"
                    + "-fx-font-size: "+s.getResult().getSize()+"pt;" +
                    "    -fx-font-family: \""+s.getResult().getFamily()+"\";}");
            
        });
        game_con.getItems().addAll(fetchInfo,lookFurther,toFrench,fontSelector);
        /*youtube =YouTube.Builder(new NetHttpTransport(),new Jackson(),e -> {})
                .setApplicationName("youtube-cmdline-search-sample").build();
        YouTube.Search.List search = youtube.search().list("id,snippet");*/
        System.setProperty("freetts.voices",
                "com.sun.speech.freetts.en.us"
                    + ".cmu_us_kal.KevinVoiceDirectory");
        
            /*Central.registerEngineCentral("com.sun.speech.freetts"
                    + ".jsapi.FreeTTSEngineCentral");
             syn=Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
            syn.allocate();
            syn.resume();*/
            v=VoiceManager.getInstance().getVoice("kevin16");
            v.allocate();
        
  
        
      
        
              
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
        Pattern p=Pattern.compile("\\w");
        
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
            
        //if (p.matcher(ne).matches())
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
        Button vtuto=new Button("watch the Tutoial");
        vtuto.setOnAction(ev -> {
            String q=t.getGame().getName()+" "+t.getTitle()+" walkthrough";
            PopOver pop=new PopOver();
            pop.setTitle("Tutorial video");
            
            try {
                q=URLEncoder.encode(q,"UTF-8");
                Response d=Jsoup.connect("https://www.googleapis.com/youtube/v3/search?q="+q+"&key=AIzaSyAv2wjYsP23B90uykgf3jpX8Dtpwsmze3U")
                               .ignoreContentType(true)
                               .execute();
                ObjectMapper obj=new ObjectMapper();
                JsonNode jn=obj.readTree(d.body());
                String vidId=jn.get("items").get(1).get("id").get("videoId").asText();
                //Media m=new Media("https://www.youtube.com/watch?v=CzVOzkgX65M");
                WebView w=new WebView();
                w.getEngine().load("https://www.youtube.com/embed/"+vidId);
                //MediaPlayer mp=new MediaPlayer(m);
                //mp.play();
                //MediaView mv=new MediaView(mp);
                pop.setContentNode(w);
                pop.setDetached(true);
                pop.setArrowLocation(PopOver.ArrowLocation.LEFT_TOP);
                pop.show(vtuto);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(QGamesController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(QGamesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        });
        b.setSpacing(10);
        HBox hbox=new HBox(delete,vtuto);
        hbox.setSpacing(20);
        b.getChildren().addAll(gp,hbox);
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
    
    private Pane SetUpForm(Games g ,Pane p){
    VBox box=new VBox();
  // box.setStyle("-fx-opacity: 20%");
    box.setMinWidth(game_paine.getWidth()-20);
    box.setMinHeight(game_paine.getHeight()-20);
    TextField tname=new TextField();
    TextArea tdesc=new TextArea();
    ChoiceBox cb=new ChoiceBox(FXCollections.observableArrayList(CategoryDao.getInstance().DisplayAllList()));
    TextField trate =new TextField();
    
    trate.setTextFormatter(new TextFormatter<>(ch ->{
    Pattern pp=Pattern.compile("[0-9.]+");
    if (pp.matcher(ch.getControlNewText()).matches())
        return ch;
    return null;
    }));
    tname.setTextFormatter(new TextFormatter<>(ch -> {
    Pattern pp=Pattern.compile("[A-Za-z-0-9]+");
    if (pp.matcher(ch.getControlNewText()).matches())
        return ch;
    return null;
    }));
    
    tdesc.setWrapText(true);
    
    Button ok=new Button("OK");
    Button cancel=new Button("Cancel");
    
    cancel.setOnAction(event -> {
    game_paine.getChildren().remove(box);
    game_paine.getChildren().remove(p);
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
    game_paine.getChildren().remove(p);
    game_paine.getChildren().addAll(Title,Hbox_games_button,game_hbox_view,game_menu_bar);
    });
    
    tname.setText(g.getName());
    tdesc.setText(g.getDescription());
    cb.setValue(g.getCategory());
    trate.setText(String.valueOf(g.getRate()));
    
    ok.setMinSize(200, 50);
    cancel.setMinSize(200, 50);
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
    
    KeyFrame kff=new KeyFrame(Duration.seconds(1), 
            new KeyValue(root.opacityProperty(),1,Interpolator.EASE_IN));
    KeyFrame kfff=new KeyFrame(Duration.seconds(0), 
            new KeyValue(root.opacityProperty(),0,Interpolator.EASE_IN));
    
    
    t.getKeyFrames().addAll(kf,kfff,kff);
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
    
    private Games FetchGamesOnline(String name) throws IOException {
        
        name=name.toLowerCase();
        name=name.replaceAll("[<>]","" );
        name=name.replaceAll(" ", "-");
        
    
    Response d =Jsoup.connect("https://rawg-video-games-database.p.rapidapi.com/games/"+name+"?key=0b1f1156cb1546dabef544af6d565b4b")
            .header("x-rapidapi-host", "rawg-video-games-database.p.rapidapi.com")
            .header("x-rapidapi-key", "64097372bemsh474baf260df44b5p187695jsn0d1f86c219e8")
            .method(Connection.Method.GET)
            .ignoreContentType(true).ignoreHttpErrors(true)
            .execute();
    if (d.statusCode()>400) {
         Notifications.create()
                 .darkStyle()
                 .graphic(new Rectangle(300,200,Color.DARKGREY))
                 .hideAfter(Duration.seconds(10))
                 .title("Not Found")
                 .text("Maybe or surely you've written the game name wrong you ape!")
                 .showInformation();
         return null;
    }
    
    ObjectMapper obj=new ObjectMapper();
     JsonNode jn=obj.readTree(d.body());
     Games g=new Games();
     String desc=jn.get("description").asText();
     
        g.setDescription(Jsoup.parse(desc).wholeText());
        g.setRate((float)jn.get("metacritic").asInt());
        g.setName(jn.get("slug").asText());
        
        Response img_res=Jsoup.connect(jn.get("background_image").asText())
             .ignoreContentType(true).execute();
        int len=jn.get("background_image").asText().length();
     FileOutputStream img=new FileOutputStream("src/com/trophy/Resources/"+g.getName()+jn.get("background_image").asText()
                        .substring(len-4, len));
     img.write(img_res.bodyAsBytes());
     img.close();
     String genre=jn.get("genres").get(0).get("name").asText();
     if (CategoryDao.getInstance().DisplayAllList()
             .stream().noneMatch(e -> e.getCategory().equals(genre)))
         CategoryDao.getInstance().insert(new Category(genre));
     Category c=CategoryDao.getInstance().DisplayAllList().stream()
             .filter(e ->e.getCategory().equals(genre))
             .findFirst()
             .orElse(null);
     g.setCategory(c);
                    
        
    return g;
    
    }
    
    public String FunFact() throws IOException{
    Response d =Jsoup.connect("https://api.api-ninjas.com/v1/facts")
            .header("X-Api-Key", "F5coi8YxdewL4rDKo0FVVw==mM5geLpb1Ki9o5P0")
            .ignoreContentType(true).execute();
    String json =d.body();
    ObjectMapper obj=new ObjectMapper();
       
    JsonNode jn=obj.readTree(json);
        
    return jn.get(0).get("fact").asText();
    
    
    
    }
    
    /*public searchGamesImg(){
    
        Response d=Jsoup
    }*/

    @FXML
    private void ShowFunFact(ActionEvent event) {
        Alert ff;
        try {
            ff = new Alert(Alert.AlertType.INFORMATION,FunFact(),ButtonType.CLOSE);

            ff.show();
        } catch (IOException ex) {
            Logger.getLogger(QGamesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void SortGames(ActionEvent event) {
        tableview_game.setItems(GamesDao.getInstance().Sort());
    }

    @FXML
    private void showFromWeb(MouseEvent event) {
            PopOver pop=new PopOver();
            TextField tx=new TextField();
            pop.setTitle("Leonardo");
            pop.setAnimated(true);
            pop.setAutoFix(true);
            pop.setArrowLocation(PopOver.ArrowLocation.RIGHT_TOP);
            VBox box=new VBox(tx);
            box.setStyle("-fx-background-color: #373e43");
            tx.setOnAction(e -> {
            Label rb=new Label();
            
                try {
                    System.out.println(tx.getText());
                    String cat=ChatBot.BotRespond(tx.getText());
                    String res=ChatBot.questionAnswer.get(cat);
                   if (cat.contains("add-game")){
                       Category c=CategoryDao.getInstance().displayById(1);
                   GamesDao.getInstance().insert(new Games(c,"<put name>","<put name>",0.0f));
                   tableview_game.setItems(GamesDao.getInstance().DisplayObservableList());
                   }
                   else if (cat.contains("modify-game")){
                   
                   }
                   else if (cat.contains("delete-game")){
                   String name=tx.getText();
                   name=name.substring(name.lastIndexOf(" "));
                    
                   
                   Games g=(Games)((GamesDao.getInstance().searchByName(name)
                           .isEmpty()) ? new Games():GamesDao.getInstance().searchByName(name)
                           .get(0));
                   GamesDao.getInstance().delete(g);
                   res+=" "+g.getName();
                   }
                   else if (cat.contains("show-stat")){
                       show_stat(e);
                   }
                    rb.setText("Bot : "+res);
                   v.speak(res);
                    tx.clear();
                } catch (IOException ex) {
                    Logger.getLogger(QGamesController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(QGamesController.class.getName()).log(Level.SEVERE, null, ex);
                }
                box.getChildren().addAll(new Label("You : "+tx.getText()),rb);
            });
            
            pop.setContentNode(box);
            pop.show(game_cercle);
    }

    @FXML
    private void showHome(ActionEvent event) {
    }
    
    
}
