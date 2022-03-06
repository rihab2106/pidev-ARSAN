/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.view;

import com.trophy.Controller.ProductController;
import com.trophy.dao.ProductDao;
import com.trophy.entity.Product;
import com.trophy.utils.ConnexionSingleton;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rihab bns
 */
public class InsertController implements Initializable {

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtDiscount;
    @FXML
    private TextField txtquantity;
    @FXML
    private ComboBox ComboCategory;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnExit;
NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.FRANCE);
private Parent root ;
    /**
     * Initializes the controller class.
     */
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList <String> list = FXCollections.observableArrayList("Action-adventure Games","Survival and horror Games","Casual Games","Shooter Games","Strategy Games","Open World Games");  
    
        ComboCategory.setItems(list);
        
    }    
    ProductDao pd = new ProductDao();
int ID;
    @FXML
    private void select(ActionEvent event) {
         String s = ComboCategory.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    private void Add(ActionEvent event) throws IOException {
          Product p = new Product();
          
           if(txtName.getText().equals("")||txtPrice.getText().equals("")||txtDiscount.getText().equals("")||txtquantity.getText().equals("")|| ComboCategory.getSelectionModel().getSelectedItem()== null){
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("there is a missing field!");
                    alert.show();
                    refresh();
           }else if(!(txtName.getText().matches("[a-zA-Z]+"))  && !(txtName.getText().contains("-"))&& !(txtName.getText().contains("_"))&& !(txtName.getText().contains(" "))){
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Product name should contain only Letters , spaces ,- or _!");
                    alert.show();
                    refresh();
        
                   
       }else if (!(txtPrice.getText().toString().matches("[0-9]+"))|| !(txtquantity.getText().toString().matches("[1-9]+")) || !(txtDiscount.getText().toString().matches("[0-9]+")) ){Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    
       alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("the Price, quantity and Discount should be a digital number and quantity should at least 1");
                    alert.show();
                    refresh();} 
    else {
           
           
           
                p.setID_Product(ID);
        p.setPROD_Name(txtName.getText());
        p.setPrice(Float.parseFloat(txtPrice.getText()));
        p.setCategory(ComboCategory.getValue().toString());
        p.setDiscount(Float.parseFloat(txtDiscount.getText()));
        p.setQuantity(Integer.parseInt(txtquantity.getText()));
        
        
         pd.insert(p);
         
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Product added successfuly!");
                    alert.show();
                    refresh();
   
    System.out.println(p.toString());
    
          txtName.setText("");
          txtPrice.setText("");
          txtDiscount.setText("");
          txtquantity.setText("");
           }
        
          
       /*FXMLLoader loader = new FXMLLoader (getClass().getResource("/com/trophy/view/Product.fxml"));
        root = loader.load();
        ProductController productcontroller = loader.getController();
        productcontroller.displaypro();
            //ProductController pc = new ProductController();*/}
         
          

    @FXML
    private void Exit(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
         
             
    }

    
}
