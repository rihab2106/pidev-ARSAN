/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.Controller;

import com.trophy.dao.ProductDao;
import com.trophy.dao.ShopDao;
import com.trophy.entity.Product;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author rihab bns
 */
public class CartController implements Initializable {

    @FXML
     TableView<Product> cartTable;
    @FXML
    private TableColumn<Product, Product> coverCol;
    @FXML
     TableColumn<Product, String> titleCol;
    @FXML
     TableColumn<Product, Float> priceCol;
    @FXML
    private TableColumn<Product, Product> removeCol;
    @FXML
     Label subtotalLabel;
public Hyperlink updatedCartButton;
 
private ShopController shopController;
public AnchorPane mainBorderPaneForCheckoutUse;
NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.FRANCE);
private Stage stage;
private Parent root;
private Scene scene;
 float total = 0; 
    @FXML
    private Button btnshop;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cartTable.setPlaceholder(new Label("Your shopping cart is empty..."));
        
         
         
         
         
         
         
    
    
    }    

    public void cartitems(ShopController shopController){
        this.shopController = shopController;
        cartTable.setItems(shopController.getCartItems());
        ProductDao pd = new ProductDao();
       
    for (Product product : cartTable.getItems()){
            
         total = total+ (product.getPrice()- (product.getPrice()*(product.getDiscount()/100)));
         product.setQuantity((product.getQuantity())-1);
         pd.update(product);
         String currencyPrice = currencyFormatter.format(total);
          subtotalLabel.setText(currencyPrice); 
                    
         System.out.println(total);}
        titleCol.setCellValueFactory(new PropertyValueFactory<>("PROD_Name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));
     priceCol.setCellFactory(col -> new TableCell<Product, Float>(){
            @Override
            public void updateItem(Float Price, boolean empty) {
                
                super.updateItem(Price, empty);
                if(empty) {
                    setGraphic(null);
                } else {
                    //Product product = new Product();
                    String currencyPrice = currencyFormatter.format(Price);
                    Label priceLabel = new Label(currencyPrice);
                    setGraphic(priceLabel); 
                    
                    
                }
                
            }
            
        });
     removeCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        removeCol.setCellFactory(param -> new TableCell<Product,Product>(){
            private final Hyperlink removeFromCart = new Hyperlink("");
            @Override
            protected void updateItem(Product product, boolean empty) {
                super.updateItem(product, empty);
                if(product == null) {
                    setGraphic(null);
                    return;
                }
                HBox removeHBox = new HBox();
                TextField quantity = new TextField("1");
                quantity.setPrefWidth(35.0);
                quantity.setAlignment(Pos.CENTER_RIGHT);
                quantity.setEditable(false);
                quantity.setStyle("-fx-background-radius: 0;");
                removeHBox.setAlignment(Pos.CENTER);
                removeHBox.getChildren().addAll(quantity, removeFromCart);
                setGraphic(removeHBox);
                setGraphic(removeHBox);
                Image deleteIcon = new Image(getClass().getResourceAsStream("/com/trophy/Css/ic_delete_black_18dp_1x.png"));
                removeFromCart.setGraphic(new ImageView(deleteIcon));
                removeFromCart.setStyle("-fx-text-fill: black;");
                removeFromCart.setOnAction(e -> {
                    getTableView().getItems().remove(product);
                    total = total - (product.getPrice()- (product.getPrice()*(product.getDiscount()/100)));
                    String currencyPrice = currencyFormatter.format(total);
                    subtotalLabel.setText(currencyPrice);
                    updatedCartButton.setText("Cart (" + String.valueOf(shopController.getCartItems().size()) + ")");
                });
            }
        });     
       
    }
     
    
    
    
    @FXML
    private void continueShopping(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/trophy/view/Shop.fxml"));
       Stage window =(Stage)btnshop.getScene().getWindow();
       window.setScene(new Scene(root));
       
    }

    @FXML
    private void proceedToCheckout(ActionEvent event) throws IOException {
        
        root = FXMLLoader.load(getClass().getResource("/com/trophy/view/Order.fxml"));
       Stage window =(Stage)btnshop.getScene().getWindow();
       window.setScene(new Scene(root));
    }

    @FXML
    private void item(ContextMenuEvent event) {
    }

    

    
    
   
}
