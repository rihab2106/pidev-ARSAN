/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.Controller;

import com.trophy.dao.ProductDao;
import com.trophy.entity.Product;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

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
        
        table.setItems(pd.getAllProduct());
        
                }    
  ProductDao pd = new ProductDao();
    @FXML
    private void Add(ActionEvent event) {
        
        Product p = new Product();
        if(!txtName.getText().equals("")&&!txtPrice.getText().equals("")){
        p.setID_Product(ID);
        p.setPROD_Name(txtName.getText());
        p.setPrice(Float.parseFloat(txtPrice.getText()));
        p.setCategory(ComboCategory.getValue().toString());
        }
        if(!txtDiscount.getText().equals(""))
          p.setDiscount(Integer.parseInt(txtDiscount.getText()));
         
    pd.insert(p);
   
    System.out.println(p.toString());
    
          txtName.setText("");
          txtPrice.setText("");
          txtDiscount.setText("");
          table.setItems(pd.getAllProduct());
    }

    @FXML
    private void Update(ActionEvent event) {
        
        Product pr = new Product();
        
          if(!txtName.getText().equals("")&&!txtPrice.getText().equals("")){
          pr.setPROD_Name(txtName.getText());
          pr.setPrice(Float.parseFloat(txtPrice.getText()));
          pr.setDiscount(Integer.parseInt(txtDiscount.getText()));
          pr.setCategory(ComboCategory.getValue().toString());
          pr.setID_Product(ID);
          
        pd.update(pr);
          
          txtName.setText("");
          txtPrice.setText("");
          txtDiscount.setText("");
          
          
          
          table.setItems(pd.getAllProduct());
          }     
        
    }

    @FXML
    private void Delete(ActionEvent event) {
        /*pd.delete(ID);
        
          txtName.setText("");
          
          txtPrice.setText("");
          txtDiscount.setText("");
          
          table.setItems(pd.getAllProduct());*/
        
        
    }


    

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
           ID=pr.getID_Product();
           ComboCategory.setValue(pr.getCategory());
    }

    @FXML
    private void Search(ActionEvent event) {
        table.setItems(pd.getSearchProduct(txtSearch.getText()));
    }
    
    
    
    
    
    
    
}
