/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.trophy.Controller;

import com.trophy.dao.NewsDao;
import com.trophy.entity.News;
import com.trophy.entity.Product;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Mouelhi
 */
public class NewssController implements Initializable {

    @FXML
    private TextField txtcontent;
    @FXML
    private TextField txtheadline;
    @FXML
    private TableView commentview;
    @FXML
    private TableColumn COMCOL;
    @FXML
    private TableColumn CONTENTCOL;
    @FXML
    private TableColumn comlike;
    @FXML
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
    @FXML
    private TableColumn ID;
    @FXML
    private TableColumn Content;
    @FXML
    private TableColumn Headline;
    @FXML
    private TableColumn imgurl;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tablev.setItems(nd.getAllNews());
        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        Headline.setCellValueFactory(new PropertyValueFactory<>("Content"));
        Content.setCellValueFactory(new PropertyValueFactory<>("Headline"));
        imgurl.setCellValueFactory(new PropertyValueFactory<>("imgurl"));
        System.out.println(nd.getAllNews());

    }    

    NewsDao nd=new NewsDao();
    @FXML
    private void Add(ActionEvent event) {
        News n=new News();
       if(!txtheadline1.getText().equals("")&&!txtheadline111.getText().equals("")&&!txtheadline11.getText().equals("")){
        n.setNewsid(idn);
        n.setContent(txtheadline111.getText());
        n.setHeadline(txtheadline1.getText());
        n.setImgurl(txtheadline11.getText());
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
        
        
    }

    @FXML
    private void Delete(ActionEvent event) {
    }

    @FXML
    private void ClickTable(MouseEvent event) {
        News n =(News) tablev.getSelectionModel().getSelectedItem();
           txtheadline1.setText(n.getHeadline());
           txtheadline11.setText(n.getImgurl()+"");
           txtheadline111.setText(n.getContent()+"");
           idn=n.getNewsid();
    }

    
}
