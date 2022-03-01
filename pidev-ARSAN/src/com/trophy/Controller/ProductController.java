/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.Controller;

import com.trophy.dao.ProductDao;
import com.trophy.entity.Product;
import com.trophy.view.InsertController;

import java.io.IOException;
import java.net.URL;

import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.demo.Main;

/**
 * FXML Controller class
 *
 * @author rihab bns
 */
public class ProductController implements Initializable {

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPrice;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField txtDiscount;
    @FXML
    private TextField txtSearch;
    @FXML
    private TableView table;
    @FXML
    private TableColumn id;
    @FXML
    private TableColumn name;
    @FXML
    private TableColumn price;
    @FXML
    private TableColumn discount;
    @FXML
    private ComboBox ComboCategory;
    @FXML
    private TableColumn Category;
   
    int ID;
    @FXML
    private Button btnASC;
    @FXML
    private Button btnDESC;
    @FXML
    private ImageView DESCBut;
    @FXML
    private Button btnDelete1;
    @FXML
    private TextField txtquantity;
    @FXML
    private TableColumn Quantity;
    @FXML
    private Button btnRefrech;
    @FXML
    private Button btnshop;
    
    private Stage stage;
    private Parent root;
    private Scene scene;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ObservableList <String> list = FXCollections.observableArrayList("Action-adventure Games","Survival and horror Games","Casual Games","Shooter Games","Strategy Games","Open World Games");  
    
        ComboCategory.setItems(list);
        
        //refere to table 
        id.setCellValueFactory(new PropertyValueFactory<>("ID_Product"));
        name.setCellValueFactory(new PropertyValueFactory<>("PROD_Name"));
        price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        Category.setCellValueFactory(new PropertyValueFactory<>("Category"));
        discount.setCellValueFactory(new PropertyValueFactory<>("Discount"));
        Quantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        
        
        
        table.setItems(pd.getAllProduct());
        
                }    
  ProductDao pd = new ProductDao();
    @FXML
    private void Add(ActionEvent event) {
        
        Product p = new Product();
        if(!txtName.getText().equals("")&&!txtPrice.getText().equals("")&&!txtDiscount.getText().equals("")){
        p.setID_Product(ID);
        p.setPROD_Name(txtName.getText());
        p.setPrice(Float.parseFloat(txtPrice.getText()));
        p.setCategory(ComboCategory.getValue().toString());
        p.setDiscount(Float.parseFloat(txtDiscount.getText()));
        p.setQuantity(Integer.parseInt(txtquantity.getText()));
        }
        
         
         
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
          table.setItems(pd.getAllProduct());
    }

    @FXML
    private void Update(ActionEvent event) {
        
        Product pr = new Product();
        
          if(Float.parseFloat(txtPrice.getText())>0&&Integer.parseInt(txtquantity.getText())>0&&Float.parseFloat(txtDiscount.getText())>0){
          pr.setPROD_Name(txtName.getText());
          pr.setPrice(Float.parseFloat(txtPrice.getText()));
          pr.setDiscount(Float.parseFloat(txtDiscount.getText()));
          pr.setCategory(ComboCategory.getValue().toString());
          pr.setQuantity(Integer.parseInt(txtquantity.getText()));
          pr.setID_Product(ID);
          
          
        pd.update(pr);
         /* Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Product updated successfuly!");
                    alert.show();*/
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success!");
                        alert.setHeaderText("Successfully Updated Product Information!");
                        alert.setContentText(null);
                        alert.showAndWait();
                        
                        
                    refresh();
          txtName.setText("");
          txtPrice.setText("");
          txtDiscount.setText("");
          txtquantity.setText("");
         
          table.setItems(pd.getAllProduct());
          }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error!");
                    alert.setHeaderText("Discount ,Price and quantity  value can not be lower than 0 !");
                    alert.setContentText(null);
                    alert.showAndWait();
          }  
        
    }

    @FXML
    private void Delete(ActionEvent event) {
        Product pr = new Product();
        Product pt =(Product) table.getSelectionModel().getSelectedItem();
          
        
        if (pt != null){
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Causion!");
            alert.setHeaderText("Are you sure you want to delete Part with ID : " + pt.getID_Product()+ "?");
            alert.setContentText(null);
            Optional<ButtonType> result = alert.showAndWait();
            
            if (result.get() == ButtonType.OK) {
                if(!txtName.getText().equals("")&&!txtPrice.getText().equals("")){
          pr.setPROD_Name(txtName.getText());
          pr.setPrice(Float.parseFloat(txtPrice.getText()));
          pr.setDiscount(Float.parseFloat(txtDiscount.getText()));
          pr.setCategory(ComboCategory.getValue().toString());
          pr.setQuantity(Integer.parseInt(txtquantity.getText()));
          pr.setID_Product(ID);
          System.out.println(pr.getID_Product());
          pd.delete(ID);
         
         txtName.setText("");
          txtPrice.setText("");
          txtDiscount.setText("");
          txtquantity.setText("");
          
          table.setItems(pd.getAllProduct());
                
            }
        }}else {
            // when no part is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error!");
            alert.setHeaderText("Please, Select at-least one part to perform delete operation!");
            alert.setContentText(null);

            alert.showAndWait();
        
        
        
        /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Product Deleted successfuly!");
                    alert.show();
                    refresh();*/
          
        
        
    }}


    

    @FXML
    private void select(ActionEvent event) {
        String s = ComboCategory.getSelectionModel().getSelectedItem().toString();
    }


    @FXML
    private void clickTable(MouseEvent event) {
           Product pr =(Product) table.getSelectionModel().getSelectedItem();
           txtName.setText(pr.getPROD_Name());
           txtPrice.setText(pr.getPrice()+"");
           txtDiscount.setText(pr.getDiscount()+"");
           txtquantity.setText(pr.getQuantity()+"");
           ID=pr.getID_Product();
           ComboCategory.setValue(pr.getCategory());
    }

    @FXML
    private void Search(ActionEvent event) {
        table.setItems(pd.getSearchProduct(txtSearch.getText()));
    }

    @FXML
    private void ASC(ActionEvent event) {
        table.setItems(pd.Sort_ASC());
       
    }

    

    @FXML
    private void DESC(ActionEvent event) {
        table.setItems(pd.Sort_DESC());
    }

    @FXML
    private void DESC(MouseEvent event) {
    }

    @FXML
    private void Refrech(ActionEvent event) {
        
            
       table.getItems().forEach(( t )->{
           Product e=(Product)t;
       if (e.getQuantity()<1){
        Image img = new Image("/com/trophy/Css/icons8-alert-64.png");
        Notifications notificationBuilder = Notifications.create()
                .title("Warning").text("the product "+e.getPROD_Name()+" will expire soon ")
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_LEFT)
                .onAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
               System.out.println("Clicked on Notifications");
            }
                });
        notificationBuilder.darkStyle();
        notificationBuilder.show();
       }
           
       });
       //System.out.println(p);
       
        
        
        
        
    }

    @FXML
    public void switchshop(ActionEvent event) throws Exception  {
       root = FXMLLoader.load(getClass().getResource("/com/trophy/view/Shop.fxml"));
       Stage window =(Stage)btnshop.getScene().getWindow();
       window.setScene(new Scene(root));
    }

    
        
        
    }

    

    

    
    
    
    
    
    
    
    

