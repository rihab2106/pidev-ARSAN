/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.trophy.Control;

import com.trophy.dao.CommentDao;
import com.trophy.dao.NewsDao;
import com.trophy.entity.Comment;
import com.trophy.entity.News;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.imageio.ImageIO;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * FXML Controller class
 *
 * @author Mouelhi
 */
public class CommentSectionController implements Initializable {

    NewsDao nd = new NewsDao();
    CommentDao cd = new CommentDao();
    int idn;
    int cdn;
    private News selectedNews;
    private ObservableList<Comment> selectedComments;
    @FXML
    private Button loginbtn;
    @FXML
    private Button signupbtn;
    @FXML
    private Text hl1;
    @FXML
    private TextArea desc1;
    @FXML
    private ImageView imageview11;
    @FXML
    private ImageView imageview3;
    @FXML
    private ImageView imageview1;
    @FXML
    private TableView commentview;
    @FXML
    private TableColumn LIKES;
    
    @FXML
    private TableColumn content;
    private TableColumn<Comment, Void> likeanddislike;
    @FXML
    private Button previous;
    private TableColumn<Comment, Void> DISLIKE1;
    @FXML
    private Button Addcombtn;
    @FXML
    private TextField commentfield;
    @FXML
    private Button Addcombtn1;
    @FXML
    private Button DONEBTN;
    @FXML
    private ContextMenu CM;

    public void InitData(News news, ObservableList<Comment> comments) throws MalformedURLException, IOException {
        selectedNews = news;
        selectedComments = comments;
        desc1.setText(selectedNews.getContent());
        hl1.setText(selectedNews.getHeadline());
        commentview.setItems(cd.getAllComments(selectedNews));
        String URL = selectedNews.getImgurl();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://videogames-news2.p.rapidapi.com/videogames_news/recent")
                .get()
                .addHeader("x-rapidapi-host", "videogames-news2.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "6d531ea0a8msh0c931fde5d065d2p186c68jsn5f2255d1d3b0")
                .build();

        URL url = new URL(URL);
        System.out.println("i tried2");

        URLConnection uc = new URL(URL).openConnection();
        client.newCall(request).execute();
        uc.setAllowUserInteraction(true);
        uc.addRequestProperty("User-Agent", "Chrome/98.0.4758.102");
        InputStream is = uc.getInputStream();

        try {

            BufferedImage bufferedImage2 = ImageIO.read(is);
            ImageIO.write(bufferedImage2, "jpg", new File("C:\\Users\\Mouelhi\\Desktop\\Ayoub\\Java\\haja.png"));

            WritableImage image2 = SwingFXUtils.toFXImage(bufferedImage2, null);
            imageview1.setImage(image2);
        } catch (IOException io) {
            System.out.println("catchy error fl comment section fl image");
        }

        content.setCellValueFactory(new PropertyValueFactory<>("comcontent"));
        LIKES.setCellValueFactory(new PropertyValueFactory<>("likes"));
        commentview.setBackground(Background.EMPTY);
    }

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       
       MenuItem mi=new MenuItem("Like");
       MenuItem mi2=new MenuItem("Dislike");
       
       //Comment c=new Comment();
       mi.setOnAction(evt->{
       Comment comment = (Comment) commentview.getSelectionModel().getSelectedItem();

       //c.setComcontent(comment.getComcontent());
       //c.setLikes(comment.getLikes());
       comment.setLikes(comment.getLikes()+1);
       cd.update(comment, selectedNews);
       
       commentview.setItems(cd.getAllComments(selectedNews));
      
       CM.getItems().add(mi2);
       CM.getItems().remove(mi);
       
       
       
       
       });
       mi2.setOnAction(evt->{
       Comment comment = (Comment) commentview.getSelectionModel().getSelectedItem();

       comment.setLikes(comment.getLikes()-1);
       cd.update(comment, selectedNews);
       commentview.setItems(cd.getAllComments(selectedNews));
              CM.getItems().add(mi);
              CM.getItems().remove(mi2);
       
       
       });
       
       CM.getItems().add(mi);
       
       
       
       
                

{     
       
    }}
            
            
            
        

    

    @FXML
    private void login(ActionEvent event) {
    }

    @FXML
    private void signup(ActionEvent event) {
    }

    private Stage stage;

    @FXML
    private void previous(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/trophy/view/UserView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

   

    

    @FXML
    private void ADDNEWITEM(MouseEvent event) {
       /* AddCom.setVisible(true);
        AddCom.setCellFactory(col -> new TableCell<Comment, String>() {
     //   private final Button button2;
        private final Button button;

        {  
            button = new Button("Add");
            button.setOnAction(evt -> {
               
                Comment item = (Comment) getTableRow().getItem();
                item.setLikes(0);
                item.setDislikes(0);
                item.setNews(selectedNews);
                item.setComcontent(item.getComcontent());
                cd.insert(item, selectedNews);
                
                
                
                commentview.setItems(cd.getAllComments(selectedNews));
    });
                    }});*/}


    @FXML
    private void ADDCOMMENT(KeyEvent event) {
    }

    @FXML
    private void Addcomment(ActionEvent event) {
       
        
        if (!commentfield.getText().isEmpty())
        { Comment comment = new Comment();
          comment.setComcontent(commentfield.getText());
          cd.insert(comment, selectedNews);
          commentview.setItems(cd.getAllComments(selectedNews));
          commentfield.setText("");
        }
        else {
    Alert a=new Alert(Alert.AlertType.WARNING,"Field is Empty",ButtonType.OK);
              
              a.setContentText("Field is empty");
              a.setTitle("Input Error");
              a.setGraphic(null);
              a.setHeaderText("Can't insert an empty comment");
              a.show();
              refresh();
              
        }
    }

    @FXML
    private void displayfield(ActionEvent event) {
        commentfield.setVisible(true);
        Addcombtn1.setVisible(true);
        DONEBTN.setVisible(true);
        
    }

    @FXML
    private void hidefield(ActionEvent event) {
        Addcombtn1.setVisible(false);
        commentfield.setVisible(false);
        DONEBTN.setVisible(false);
    }

    @FXML
    private void ClickTable(MouseEvent event) {
    }

    
}
