/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.Control;

import com.trophy.dao.groupsdao;
import com.trophy.dao.user_groupsdao;
import com.trophy.entity.Groups;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class GroupController implements Initializable {

    
    @FXML
    private Button  visitbutt;
    @FXML
    private Button createbutton;
    @FXML
    private Button updatebutton;
    @FXML
    private Button deletebutton;
    @FXML
    private Button searchbutton;
    @FXML
    private TextField searchtext;
    @FXML
    private TableView tbgroup;
    @FXML
    private TableColumn colgroup;
    @FXML
    private TableColumn colname;
    @FXML
    private TableColumn colnbutt;
    @FXML
    private TextField textgname;
    @FXML
    private TextField textidgroup;
    private TextField textimage;
    @FXML
    private Button bback;

     @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         //refere to table 
        
       
        colgroup.setCellValueFactory(new PropertyValueFactory<>("ID_GROUP"));
        
       
         colname.setCellValueFactory(new PropertyValueFactory<>("NAME"));
        
       
         tbgroup.setItems(pd.displayAll());
         
    }    
    
     

     groupsdao pd=new groupsdao();
    @FXML
    private void create(ActionEvent event) {
        
          Groups p = new Groups();
        if(!textgname.getText().equals("") &&!textidgroup.getText().equals("")/*&&!textimage.getText().equals("")*/){
       
        p.setNAME(textgname.getText());
        p.setID_GROUP(Integer.parseInt(textidgroup.getText()));
        pd.insert(p);
       
        }
       
    
   
    System.out.println(p.toString());
    
          textgname.setText("");
          textidgroup.setText("");
          textimage.setText("");
          tbgroup.setItems(pd.displayAll());
        
        
        
    }

    @FXML
    private void update(ActionEvent event) {
        
            Groups p = new Groups();
        
          if(!textgname.getText().equals("") &&!textidgroup.getText().equals("")&&!textimage.getText().equals("")){
          
        p.setNAME(textgname.getText());
        p.setID_GROUP(Integer.parseInt(textidgroup.getText()));
      
          
        pd.update(p);
          
           textgname.setText("");
          textidgroup.setText("");
          textimage.setText("");
          tbgroup.setItems(pd.displayAll());
        
          
          
          }     
        
        
        
        
    }

    @FXML
    private void delete(ActionEvent event) {
        
        
            Groups p = new Groups();
        
          if(!textgname.getText().equals("") ||!textidgroup.getText().equals("")){
          
        p.setNAME(textgname.getText());
        p.setID_GROUP(Integer.parseInt(textidgroup.getText()));
          
        pd.delete(p);
          
           textgname.setText("");
          textidgroup.setText("");
          textimage.setText("");
          tbgroup.setItems(pd.displayAll());
        
       
          }     
          
    }
    private void clickTable2(MouseEvent event) {
           Groups G =(Groups) tbgroup.getSelectionModel().getSelectedItem();
           textgname.setText(G.getNAME());
           textidgroup.setText(G.getID_GROUP()+"");
           //textimage.setText(G.getIMG());
       
           
    }

     @FXML
    private void searchgroup(ActionEvent event) {
        tbgroup.setItems(pd.getSearchGroup(searchtext.getText()));
    }
    
    
    
    

   /*@FXML
    private void visitfriends(ActionEvent event) {
        tbgroup.setItems(pd.getSearchGroup(searchtext.getText()));
    }*/

    @FXML 
    private void visit(ActionEvent event) throws IOException {
  

        
        
              
            Parent root = FXMLLoader.load(getClass().getResource("/com/trophy/view/users_group.fxml"));
       
      
            Stage  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene  scene = new Scene(root);              
            stage.setScene(scene);
            stage.show();
  
    }

    @FXML
    private void backtoaccount(ActionEvent event) throws IOException {
            
            Parent root = FXMLLoader.load(getClass().getResource("/com/trophy/view/useraccount.fxml"));
       
      
            Stage  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene  scene = new Scene(root);              
            stage.setScene(scene);
            stage.show();
        
    }
   
    
}
