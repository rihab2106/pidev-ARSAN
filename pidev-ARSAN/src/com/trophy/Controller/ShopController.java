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
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ShopDao sd = new ShopDao();
        
        Name.setCellValueFactory(new PropertyValueFactory<>("PROD_Name"));
        Price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        Category.setCellValueFactory(new PropertyValueFactory<>("Category"));
        Discount.setCellValueFactory(new PropertyValueFactory<>("Discount"));
        table.setItems(sd.getProducttoShop());
    }    

    @FXML
    private void changeColor(ActionEvent event) {
        
        Color selectedColor = colorPicker.getValue();
        pane.setBackground(new Background(new BackgroundFill(Paint.valueOf(selectedColor.toString()),CornerRadii.EMPTY,Insets.EMPTY)));
        
    }

    @FXML
    private void ViewCart(MouseEvent event) {
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

    @FXML
    private void Exit(MouseEvent event) {
    }
    
}
