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
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
    private TableColumn DISLIKES;
    @FXML
    private TableColumn content;
    @FXML
    private TableColumn<Comment, Void> likeanddislike;
    @FXML
    private Button previous;
    @FXML
    private TableColumn<Comment, Void> DISLIKE1;

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
        DISLIKES.setCellValueFactory(new PropertyValueFactory<>("dislikes"));
        commentview.setBackground(Background.EMPTY);
    }

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       DISLIKE1.setVisible(false);

{     
      likeanddislike.setCellFactory(col -> new TableCell<Comment, Void>() {
     //   private final Button button2;
        private final Button button;

        {  
            button = new Button("Like");
            button.setOnAction(evt -> {
               
                Comment item = (Comment) getTableRow().getItem();
                item.setLikes(item.getLikes()+1);
                
                
                cd.update(item, selectedNews);
                likeanddislike.setVisible(false);
                DISLIKE1.setVisible(true);
                commentview.setItems(cd.getAllComments(selectedNews));
                System.out.println(cd.getAllComments(selectedNews));
               /*button2.setOnAction(evt2 -> {
            Comment item2 = (Comment) getTableRow().getItem();
                item2.setDislikes(item2.getDislikes()+1);
                button2.setVisible(false);
                button.setVisible(true);
                cd.update(item2, selectedNews);
                commentview.setItems(cd.getAllComments(selectedNews));
                System.out.println(cd.getAllComments(selectedNews));



});




*/
            });
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            setGraphic(empty ? null : button);
        }
        
    });

            DISLIKE1.setCellFactory(col -> new TableCell<Comment, Void>() {
            private final Button button2;
                { button2=new Button("Dislike");
               button2.setOnAction(evt->{ button2.getText();
                Comment item2 = (Comment) getTableRow().getItem();
                item2.setDislikes(item2.getDislikes()+1);
                likeanddislike.setVisible(true);
                DISLIKE1.setVisible(false);
                cd.update(item2, selectedNews);
                commentview.setItems(cd.getAllComments(selectedNews));
                System.out.println(cd.getAllComments(selectedNews));
                
                });}
                
                
                
        @Override
        protected void updateItem(Void item2, boolean empty) {
            super.updateItem(item2, empty);
            setGraphic(empty ? null : button2);
            
        }
        
        
        
        });       
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

}
