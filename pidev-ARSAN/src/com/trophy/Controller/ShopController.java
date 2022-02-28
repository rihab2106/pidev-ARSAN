/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.Controller;


import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.trophy.dao.ShopDao;
import com.trophy.entity.Product;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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



/**
 * FXML Controller class
 *
 * @author rihab bns
 */
public class ShopController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
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
    private ImageView Exit;
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
    @FXML
    private void changeColor(ActionEvent event) {
        
        Color selectedColor = colorPicker.getValue();
        pane.setBackground(new Background(new BackgroundFill(Paint.valueOf(selectedColor.toString()),CornerRadii.EMPTY,Insets.EMPTY)));
        
    }

    

    @FXML
    private void Exit(MouseEvent event) {
    }

    
    public Product getSelectedProduct() {
        Product pr =(Product) table.getSelectionModel().getSelectedItem();
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
            cartItems.add(getSelectedProduct());
           cartButton.setText("Cart (" + String.valueOf(cartItems.size()) + ")");
            //System.out.println("Product Added To Cart");
        }
        
    }
    
     public ObservableList<Product> getCartItems() {
        return cartItems;
    }
    
    
     
     
     
    
public BorderPane mainBorderPaneForCheckoutUse;
    

    @FXML
    private void ViewCart(MouseEvent event) throws IOException {
        
        
    }

    @FXML
    private void showCart(ActionEvent event) {
        try{
           Parent root = FXMLLoader.load(getClass().getResource("/com/trophy/view/Cart.fxml"));
           
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            
        
        } catch(IOException ex){
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE,null ,ex);
        }
    }
    
}
