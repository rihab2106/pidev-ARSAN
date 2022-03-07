/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.trophy.Controller;

import com.trophy.dao.CommentDao;
import com.trophy.dao.NewsDao;
import com.trophy.entity.Comment;
import com.trophy.entity.News;
import com.trophy.utils.ConnexionSingleton;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Mouelhi
 */
public class UserViewController implements Initializable {

    @FXML
    private Text hl1;
    @FXML
    private ImageView imageview1;
    @FXML
    private TextArea desc1;
    @FXML
    private Text hl2;
    @FXML
    private ImageView imageview2;
    @FXML
    private TextArea desc2;
    @FXML
    private Button CS1;
    @FXML
    private Button CS3;
    @FXML
    private Button CS2;

    /**
     * Initializes the controller class.
     */
   
    NewsDao nd = new NewsDao();
    CommentDao cd = new CommentDao();
    int idn;
    int cdn;
    @FXML
    private Text hl3;
    @FXML
    private TextArea desc3;
    @FXML
    private ImageView imageview11;
    @FXML
    private ImageView imageview3;
    @FXML
    private Button loginbtn;
    @FXML
    private Button signupbtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {

            ObservableList<News> LN = nd.getAllNews();
            List<News> tail = LN.subList(Math.max(LN.size() - 3, 0), LN.size());
            hl1.setText(tail.get(0).getHeadline());
            hl2.setText(tail.get(1).getHeadline());
            hl3.setText(tail.get(2).getHeadline());
            desc1.setText(tail.get(0).getContent());
            desc2.setText(tail.get(1).getContent());
            desc3.setText(tail.get(2).getContent());
            String imageSource = tail.get(0).getImgurl();
            String imageSource1 = tail.get(1).getImgurl();
            String imageSource2 = tail.get(2).getImgurl();

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://videogames-news2.p.rapidapi.com/videogames_news/recent")
                    .get()
                    .addHeader("x-rapidapi-host", "videogames-news2.p.rapidapi.com")
                    .addHeader("x-rapidapi-key", "6d531ea0a8msh0c931fde5d065d2p186c68jsn5f2255d1d3b0")
                    .build();
            

            URLConnection uc = new URL(imageSource).openConnection();
            URLConnection uc1 = new URL(imageSource1).openConnection();
            URLConnection uc2 = new URL(imageSource2).openConnection();

            client.newCall(request).execute();
            uc.setAllowUserInteraction(true);
            uc1.setAllowUserInteraction(true);
            uc2.setAllowUserInteraction(true);
            uc.addRequestProperty("User-Agent", "Chrome/98.0.4758.102");
            uc1.addRequestProperty("User-Agent", "Chrome/98.0.4758.102");
            uc2.addRequestProperty("User-Agent", "Chrome/98.0.4758.102");
            InputStream is = uc.getInputStream();
            InputStream is1 = uc1.getInputStream();
            InputStream is2 = uc2.getInputStream();

            try {

                BufferedImage bufferedImage = ImageIO.read(is);
                BufferedImage bufferedImage1 = ImageIO.read(is1);
                BufferedImage bufferedImage2 = ImageIO.read(is2);

                WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
                WritableImage image1 = SwingFXUtils.toFXImage(bufferedImage1, null);
                WritableImage image2 = SwingFXUtils.toFXImage(bufferedImage2, null);
                imageview1.setImage(image);
                imageview2.setImage(image1);
                imageview3.setImage(image2);
               
            } catch (MalformedURLException mu) {
                System.out.println("url khayeb");
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(UserViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private void gotocs1(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/trophy/view/CommentSection.fxml"));
        Parent root=loader.load();
        Scene scene=new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        CommentSectionController controller= loader.getController();
        ObservableList<News> LN = nd.getAllNews();
        List<News> tail = LN.subList(Math.max(LN.size() - 3, 0), LN.size());
        ObservableList<Comment> LC = cd.getAllComments(tail.get(0));
        controller.InitData(tail.get(0), LC);
        
        
    }

    @FXML
    private void gotocs3(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/trophy/view/CommentSection.fxml"));
        Parent root=loader.load();
        Scene scene=new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        CommentSectionController controller= loader.getController();
        ObservableList<News> LN = nd.getAllNews();
        List<News> tail = LN.subList(Math.max(LN.size() - 3, 0), LN.size());
        ObservableList<Comment> LC = cd.getAllComments(tail.get(0));
        controller.InitData(tail.get(2), LC);
    }

    @FXML
    private void gotocs2(ActionEvent event) throws IOException {
     FXMLLoader loader= new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/trophy/view/CommentSection.fxml"));
        Parent root=loader.load();
        Scene scene=new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        CommentSectionController controller= loader.getController();
        ObservableList<News> LN = nd.getAllNews();
        List<News> tail = LN.subList(Math.max(LN.size() - 3, 0), LN.size());
        ObservableList<Comment> LC = cd.getAllComments(tail.get(0));
        controller.InitData(tail.get(1), LC);
    }
    
    

    @FXML
    private void login(ActionEvent event) {
    }

    @FXML
    private void signup(ActionEvent event) {
    }

}
