/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.trophy.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trophy.dao.CommentDao;
import com.trophy.dao.NewsDao;
import com.trophy.entity.Comment;
import com.trophy.entity.News;
import com.trophy.entity.Product;
import com.trophy.utils.ConnexionSingleton;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.swing.JTextArea;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Mouelhi
 */
public class NewssController implements Initializable {

    @FXML
    private TextArea txtcontent;
    @FXML
    private TextField txtheadline;
    @FXML
    private TableView commentview;
    private TableColumn CONTENTCOL;
    private TableColumn comlike;
    private TableColumn comdislike;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView tablev;
    @FXML
    private TextField txtheadline1;
    @FXML
    private TextField txtheadline11;
    @FXML
    private TextField txtheadline111;
    int idn;
    int idc;
    @FXML
    private TableColumn ID;
    @FXML
    private TableColumn Content;
    @FXML
    private TableColumn Headline;
    @FXML
    private TableColumn imgurl;
    @FXML
    private Button btnaddcom;
    @FXML
    private Button btndelcom;
    @FXML
    private Button btnupdatecom;
    @FXML
    private TextField AddComment;
    @FXML
    private TableColumn<?, ?> CONTENT;
    @FXML
    private TableColumn<?, ?> LIKES;
    @FXML
    private TableColumn<?, ?> DISLIKES;
    @FXML
    private Button addimg;
    @FXML
    private ImageView IMGVIEW1;
    @FXML
    private ImageView IMGVIEW2;
    @FXML
    private Button REFRESH;
    @FXML
    private Button userview;
    @FXML
    private TextField searchhl;
    @FXML
    private TextField searchdesc;
    @FXML
    private Button btnHome;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tablev.setItems(nd.getAllNews());
        ID.setCellValueFactory(new PropertyValueFactory<>("newsid"));
        Headline.setCellValueFactory(new PropertyValueFactory<>("Headline"));
        Content.setCellValueFactory(new PropertyValueFactory<>("Content"));
        imgurl.setCellValueFactory(new PropertyValueFactory<>("imgurl"));
        System.out.println(nd.getAllNews());

    }

    NewsDao nd = new NewsDao();
    CommentDao cd = new CommentDao();

    @FXML
    private void Add(ActionEvent event) {
        News n = new News();
        if (!txtheadline1.getText().equals("") && !txtheadline111.getText().equals("") && !txtheadline11.getText().equals("")) {
            n.setNewsid(idn);
            n.setContent(txtheadline111.getText());
            n.setHeadline(txtheadline1.getText());
            n.setImgurl(txtheadline11.getText());
        }        else {
    Alert a=new Alert(Alert.AlertType.WARNING,"Field is Empty",ButtonType.OK);
              
              a.setContentText("Field is empty");
              a.setTitle("Input Error");
              a.setGraphic(null);
              a.setHeaderText("Can't insert an empty News");
              a.show();
              refresh();
              
        }
        nd.insert(n);
        System.out.println(n.toString());
        txtheadline11.setText("");
        txtheadline111.setText("");
        txtheadline1.setText("");
        tablev.setItems(nd.getAllNews());

        System.out.println(nd.getAllNews());
    }

    @FXML
    private void Update(ActionEvent event) {
        News n = new News();
        if (!txtheadline1.getText().equals("") && !txtheadline111.getText().equals("") && !txtheadline11.getText().equals("")) {
            n.setHeadline(txtheadline1.getText());
            n.setContent(txtheadline111.getText());
            n.setImgurl(txtheadline11.getText());
            n.setNewsid(idn);

            nd.update(n);

            txtheadline1.setText("");
            txtheadline111.setText("");
            txtheadline11.setText("");

            tablev.setItems(nd.getAllNews());
        }

    }

    @FXML
    private void Delete(ActionEvent event) {
        News n = new News();
        if (!txtheadline1.getText().equals("") && !txtheadline111.getText().equals("") && !txtheadline11.getText().equals("")) {
            n.setHeadline(txtheadline1.getText());
            n.setContent(txtheadline111.getText());
            n.setImgurl(txtheadline11.getText());
            n.setNewsid(idn);
            nd.delete(n);
            txtheadline1.setText("");
            txtheadline111.setText("");
            txtheadline11.setText("");

            tablev.setItems(nd.getAllNews());
        }

    }

    @FXML
    private void ClickTable(MouseEvent event) throws MalformedURLException, IOException {
        News n = (News) tablev.getSelectionModel().getSelectedItem();
        txtheadline1.setText(n.getHeadline());
        txtheadline11.setText(n.getImgurl() + "");
        txtheadline111.setText(n.getContent() + "");
        txtheadline.setText(n.getHeadline());
        txtcontent.setWrapText(true);
        txtcontent.setText(n.getContent());

        btnaddcom.setVisible(true);
        btndelcom.setVisible(true);
        btnupdatecom.setVisible(true);
        AddComment.setVisible(true);
        AddComment.setText("YOUR COMMENT");
        String imageSource = n.getImgurl();
        System.out.println(imageSource);
        File file = new File(imageSource);
        System.out.println(imageSource.charAt(0) == 'C');
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://videogames-news2.p.rapidapi.com/videogames_news/recent")
                .get()
                .addHeader("x-rapidapi-host", "videogames-news2.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "6d531ea0a8msh0c931fde5d065d2p186c68jsn5f2255d1d3b0")
                .build();
        System.out.println("i tried1");
        System.out.println("SAUCE: " + imageSource);
        URL url = new URL(imageSource);
        System.out.println("i tried2");

        URLConnection uc = new URL(imageSource).openConnection();
        client.newCall(request).execute();
        
        System.out.println(uc.getURL());
        System.out.println("i tried3");
        uc.setAllowUserInteraction(true);
        uc.addRequestProperty("User-Agent", "Chrome/98.0.4758.102");
        InputStream is = uc.getInputStream();
        System.out.println("i tried4");
        System.out.println(url);

        try {

            BufferedImage bufferedImage2 = ImageIO.read(is);
            //ImageIO.write(bufferedImage2, "jpg", new File("C:\\Users\\Mouelhi\\Desktop\\Ayoub\\Java\\haja.png"));

            WritableImage image2 = SwingFXUtils.toFXImage(bufferedImage2, null);
            IMGVIEW1.setImage(image2);
            Notifications notificationbuilder = Notifications.create()
                    .title("File Input")
                    .text("Image Inserted Successfully!")
                    .darkStyle()
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notificationbuilder.showConfirm();
        } catch (MalformedURLException mu) {
            System.out.println("url khayeb");
        }

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            IMGVIEW1.setImage(image);
            Notifications notificationbuilder = Notifications.create()
                    .title("File Input")
                    .text("Image Inserted Successfully!")
                    .darkStyle()
                    .graphic(null)
                    .hideAfter(Duration.seconds(2))
                    .position(Pos.BOTTOM_RIGHT);
            notificationbuilder.showConfirm();
        } catch (IOException io) {
            System.out.println("file khayeb");
        }
        System.out.println(file.getAbsolutePath());

        /*if (imageSource.charAt(0) == 'C') {
                System.out.println("hani fl if");
                
            } else {
                IMGVIEW1.setImage(image2);
                Notifications notificationbuilder = Notifications.create()
                        .title("File Input")
                        .text("Image Inserted Successfully!")
                        .darkStyle()
                        .graphic(null)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.BOTTOM_RIGHT);
                notificationbuilder.showConfirm();
            }*/
        idn = n.getNewsid();
        commentview.setItems(cd.getAllComments(n));
        System.out.println(cd.getAllComments(n));
        CONTENT.setCellValueFactory(new PropertyValueFactory<>("comcontent"));
        LIKES.setCellValueFactory(new PropertyValueFactory<>("likes"));
        DISLIKES.setCellValueFactory(new PropertyValueFactory<>("dislikes"));
    }

    @FXML
    private void AddCom(ActionEvent event) {
        Comment cm = new Comment();
        News n = (News) tablev.getSelectionModel().getSelectedItem();
        if (!AddComment.getText().equals("")) {
            cm.setIdcomm(idc);
            cm.setComcontent(AddComment.getText());
        }        else {
    Alert a=new Alert(Alert.AlertType.WARNING,"Field is Empty",ButtonType.OK);
              
              a.setContentText("Field is empty");
              a.setTitle("Input Error");
              a.setGraphic(null);
              a.setHeaderText("Can't insert an empty comment");
              a.show();
              refresh();
              
        }
        cd.insert(cm, n);
        AddComment.setText("");
        commentview.setItems(cd.getAllComments(n));

    }

    @FXML
    private void DeleteCom(ActionEvent event) {
        News n = (News) tablev.getSelectionModel().getSelectedItem();
        Comment c = (Comment) commentview.getSelectionModel().getSelectedItem();

        Comment C = new Comment();
        if (!AddComment.getText().equals("")) {
            c.setComcontent(AddComment.getText());
            c.setIdcomm(c.getIdcomm());

            cd.delete(c, n);

            AddComment.setText("");
            commentview.setItems(cd.getAllComments(n));
        }
    }

    @FXML
    private void UpdateCom(ActionEvent event) {
        News n = (News) tablev.getSelectionModel().getSelectedItem();
        Comment c = (Comment) commentview.getSelectionModel().getSelectedItem();
        if (!AddComment.getText().equals("")) {
            c.setComcontent(AddComment.getText());

            cd.update(c, n);
            commentview.setItems(cd.getAllComments(n));
            AddComment.setText("");

        }

    }

    @FXML
    private String AddIMG(ActionEvent event) throws IOException {
        final FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        Window stage = null;
        fileChooser.showOpenDialog(stage);
        File file = fileChooser.showOpenDialog(null);
        BufferedImage bufferedImage = ImageIO.read(file);
        System.out.println(file.getAbsolutePath());
        WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
        IMGVIEW1.setImage(image);

        return file.getAbsolutePath();
    }

    @FXML
    private void InsertImage(MouseEvent event) throws MalformedURLException, IOException {

        String imageSource = txtheadline11.getText();
        File file = new File(imageSource);
        try {

            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            System.out.println(file.getAbsolutePath());
            if (file.getAbsolutePath() != null) {

                IMGVIEW1.setImage(image);
                Notifications notificationbuilder = Notifications.create()
                        .title("File Input")
                        .text("Image Inserted Successfully!")
                        .darkStyle()
                        .graphic(null)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.BOTTOM_RIGHT);
                notificationbuilder.showConfirm();
            } else {
                System.out.println("hani fl notif");
            }

        } catch (IOException io) {
            System.out.println("hani catchyt error");
            Notifications notificationbuilder = Notifications.create()
                    .title("File Input")
                    .text("Image does not exist within the system!")
                    .darkStyle()
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notificationbuilder.showError();
        }
        try {
            URL url = new URL(imageSource);
            System.out.println(url);
            BufferedImage bufferedImage2 = ImageIO.read(url);
            WritableImage image2 = SwingFXUtils.toFXImage(bufferedImage2, null);
            IMGVIEW1.setImage(image2);
            Notifications notificationbuilder = Notifications.create()
                    .title("File Input")
                    .text("Image Inserted Successfully!")
                    .darkStyle()
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notificationbuilder.showConfirm();
        } catch (MalformedURLException mu) {
            System.out.println("url khayeb");
        }

    }

    @FXML
    private void refreshnewsapi(ActionEvent event) throws IOException {
        try {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://videogames-news2.p.rapidapi.com/videogames_news/recent")
                    .get()
                    .addHeader("x-rapidapi-host", "videogames-news2.p.rapidapi.com")
                    .addHeader("x-rapidapi-key", "0180b95259msh0f063eaab3962ecp1d38e7jsnfa35693d7d18")
                    .build();

            Response response = client.newCall(request).execute();
            System.out.println(response.isSuccessful());

            ObjectMapper obj = new ObjectMapper();
            JsonNode jn = obj.readTree(response.body().string());
            System.out.println(jn.size());
            //System.out.println(jn.get(0).get("date").asText());
            System.out.println("bruh");
            int i;
            News n = new News();
            for (i = 0; i <= jn.size(); i++) {
                System.out.println("hani fl for");
                n.setHeadline(jn.get(i).get("title").asText().replace("'", ""));
                n.setImgurl(jn.get(i).get("image").asText().replace("'", ""));
                n.setContent(jn.get(i).get("description").asText().replace("'", ""));
                nd.insert(n);
                tablev.setItems(nd.getAllNews());
            }        

        } catch (IOException io) {
            System.out.println("io exception");
        }
    }

    private Stage stage;
    private Scene scene;
    private Parent root;
    

    @FXML
    private void search(KeyEvent event) {
        tablev.setItems(nd.SearchNews(searchhl.getText(), searchdesc.getText()));
        
        if (searchhl.getText().isEmpty() && searchdesc.getText().isEmpty())    
        {
            tablev.setItems(nd.getAllNews());
            
        }
        
    }

    @FXML
    private void switchtouserview(ActionEvent event) throws IOException, SQLException {
        //NewsDao.getInstance().
       // DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/games", "root", "").close();
        root = FXMLLoader.load(getClass().getResource("/com/trophy/view/UserView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    private void HomeAdmin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/trophy/view/HomeAdmin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
