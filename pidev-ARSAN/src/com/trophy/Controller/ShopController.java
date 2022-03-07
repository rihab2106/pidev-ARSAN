/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.Controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.trophy.dao.ProductDao;
import com.trophy.dao.ShopDao;
import com.trophy.entity.Product;
import com.trophy.utils.ConnexionSingleton;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;



/**
 * FXML Controller class
 *
 * @author rihab bns
 */
public class ShopController implements Initializable {

    @FXML
    private AnchorPane pane;
    private ColorPicker colorPicker;
    private JFXHamburger hamburger;
    private JFXDrawer drawer;
    @FXML
    private TableColumn Name;
    @FXML
    private TableColumn Price;
    @FXML
    private TableColumn Discount;
    @FXML
    private ImageView Cart;
    @FXML
    private TableColumn Category;
    @FXML
    private TableView table;
    @FXML
    private Button addToCartButton;
     
    private Stage warningStage;
    private Stage helpStage;
    @FXML
    private Hyperlink cartButton;
    @FXML
    private Button btnExit;
    @FXML
    private Button coupnBtn;
    @FXML
    private Hyperlink givewayhyperlink;
    @FXML
    private ImageView giveawaypic;
    @FXML
    private Hyperlink givewaytitle;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ShopDao sd = new ShopDao(); //application
        
        Name.setCellValueFactory(new PropertyValueFactory<>("PROD_Name"));
        Price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        Category.setCellValueFactory(new PropertyValueFactory<>("Category"));
        Discount.setCellValueFactory(new PropertyValueFactory<>("Discount"));
        table.setItems(sd.getProducttoShop());
    }    
private ObservableList<Product> cartItems = FXCollections.observableArrayList();
    private void changeColor(ActionEvent event) {
        
        Color selectedColor = colorPicker.getValue();
        pane.setBackground(new Background(new BackgroundFill(Paint.valueOf(selectedColor.toString()),CornerRadii.EMPTY,Insets.EMPTY)));
        
    }

    

    private void Exit(MouseEvent event) throws IOException {
        
         
    }

    
    public Product getSelectedProduct() {
        Product pr =(Product) table.getSelectionModel().getSelectedItem()  ;
        
        return pr;
    }
    
    @FXML
    private void addToCartButtonClicked(ActionEvent event)throws Exception {
        if(cartItems.contains(getSelectedProduct())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(warningStage);
            alert.getDialogPane().setContentText("Each digital purchase is automatically linked to your account. You cannot buy the same digital item twice.");
            alert.getDialogPane().setHeaderText(null);
            alert.showAndWait().filter(response -> response == ButtonType.OK);
            
        } else {
             Statement st;
            Product pr = new Product();
            
            cartItems.add(getSelectedProduct());
           cartButton.setText("Cart (" + String.valueOf(cartItems.size()) + ")");
           /*if ( cartItems.add(getSelectedProduct())){
           FXMLLoader loader = new FXMLLoader (getClass().getResource("/com/trophy/view/Cart.fxml"));
        Parent root = loader.load();
        CartController cartcontroller = loader.getController();
        cartcontroller.cartitems();}*/
           /*int q=cartItems.get(0).getQuantity()-1;
           pr.setQuantity(q);
           System.out.println(q);*/
           
               /*try{
               
              st = ConnexionSingleton.openConnection().createStatement();
              st.executeUpdate(" UPDATE `product` SET `Quantity`= "+"'"+q+"WHERE ID_PRODUCT = "+cartItems.get(0).getID_Product());
       ConnexionSingleton.closeConnection();
        }catch (SQLException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
            ConnexionSingleton.closeConnection();
        }*/
            
           
          

           
           
             
            //System.out.println("Product Added To Cart");
        }
        
    }
    
     public ObservableList<Product> getCartItems() {
        return cartItems;
    }
    
    @FXML
    public void showCart(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader (getClass().getResource("/com/trophy/view/Cart.fxml"));
        AnchorPane cart = loader.load();
        CartController cartController = loader.getController();
        cartController.updatedCartButton = cartButton;
        cartController.mainBorderPaneForCheckoutUse = pane;
        cartController.cartitems(this);
        pane.getChildren().clear();
        pane.getChildren().addAll(cart);
       


       // pane.getParent().setVisible(true);
       
                
       /* pane.getLeftAnchor(cart);
        pane.getRightAnchor(cart);
        pane.getBottomAnchor(cart);
        pane.getTopAnchor(cart);*/
       
        
        
            }
     
     
     
    
public BorderPane mainBorderPaneForCheckoutUse;
    

    @FXML
    private void ViewCart(MouseEvent event) throws IOException {
        
        
    }

    private void showCart1(ActionEvent event)  {
        try{
           Parent root = FXMLLoader.load(getClass().getResource("/com/trophy/view/Cart.fxml"));
           
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            FXMLLoader loader = new FXMLLoader (getClass().getResource("/com/trophy/view/Cart.fxml"));
             Parent roott = loader.load();
        CartController cartcontroller = loader.getController();
        /*cartcontroller.cartitems();*/
        
        } catch(IOException ex){
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE,null ,ex);
        }
        
        
        
    }

    @FXML
    private void Exit(ActionEvent event) throws IOException  {
        Parent root = FXMLLoader.load(getClass().getResource("/com/trophy/view/Home.fxml"));
       Stage window =(Stage)btnExit.getScene().getWindow();
       window.setScene(new Scene(root));
       
    }

    @FXML
    private void showcoupons(ActionEvent event) throws IOException, URISyntaxException {
        OkHttpClient client = new OkHttpClient();

       Request request = new Request.Builder()
	.url("https://gamerpower.p.rapidapi.com/api/filter?platform=epic-games-store.steam.android&type=game.loot")
	.get()
	.addHeader("x-rapidapi-host", "gamerpower.p.rapidapi.com")
	.addHeader("x-rapidapi-key", "8e98077110msh3fba1864b104aa6p1f98fajsna13a5e5ab73e")
	.build();
    Response response = client.newCall(request).execute();
     System.out.println(response.isSuccessful());
     ObjectMapper obj = new ObjectMapper();
     JsonNode jn = obj.readTree(response.body().string());
     System.out.println(jn.get(7).get("open_giveaway_url").asText());
      
     givewayhyperlink.setText(jn.get(7).get("open_giveaway_url").asText());
     givewaytitle.setText(jn.get(1).get("title").asText());
     givewayhyperlink.setOnAction(ee ->{
            try {
                Desktop.getDesktop().browse(new URI(jn.get(7).get("open_giveaway_url").asText()));
            } catch (URISyntaxException ex) {
                Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
            }
     });
     org.jsoup.Connection.Response d=Jsoup.connect(jn.get(4).get("image").asText())
             .ignoreContentType(true)
             .execute();
     
        
     giveawaypic.setImage(new Image(d.bodyStream()));
    }


    
}
