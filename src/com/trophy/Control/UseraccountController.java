/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.Control;

import com.trophy.dao.userdao;
import com.trophy.entity.Session;
import com.trophy.entity.users;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class UseraccountController implements Initializable {

    @FXML
    private Button buttvisitgrp;
    @FXML
    private Button busergroup;
    @FXML
    private Button buttlogout;
    @FXML
    private Button buttupd;
    @FXML
    private Button buttnddelet;

    /**
     * Initializes the controller class.
     */
       userdao pd=new userdao();
    @FXML
    private TextField mailtext;
    @FXML
    private TextField usrname;
    @FXML
    private TextField passtext;
    @FXML
    private TextField iduse;
    
    
      users p=Session.StartSession().getSessionUser();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
 
           
             usrname.setText(p.getFULL_NAME());
            
             mailtext.setText( p.getEMAIL());
             //passtext.setText(p.getID_USER(pd.displayacount(p)));

    }    
  
    @FXML
    private void visitgroup(ActionEvent event) throws IOException {
           
            Parent root = FXMLLoader.load(getClass().getResource("/com/trophy/view/group.fxml"));
       
      
            Stage  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene  scene = new Scene(root);              
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void visitusergroup(ActionEvent event) throws IOException {
           
            Parent root = FXMLLoader.load(getClass().getResource("/com/trophy/view/users_group.fxml"));
       
      
            Stage  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene  scene = new Scene(root);              
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void logoutbutt(ActionEvent event) {
        
        
                try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent parent = FXMLLoader.load(getClass().getResource("/com/trophy/view/Login.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            } catch (IOException ex) {
                System.out.println("error in displaying Article");
            }
    }

    @FXML
    private void upaccount(ActionEvent event) {
        
      
   //users p=new users();
        if(!usrname.getText().equals("") &&! mailtext.getText().equals("")&&!passtext.getText().equals("")&&!iduse.getText().equals("") ){
               p.setID_USER(Integer.parseInt(iduse.getText()));
               p.setFULL_NAME(usrname.getText());
               p.setEMAIL(mailtext.getText());
               p.setPASSWORD(passtext.getText());
               pd.updateuser(p);
               usrname.setText("");
               mailtext.setText("");
               passtext.setText("");
               
               usrname.setText(p.getFULL_NAME());
            
               mailtext.setText( p.getEMAIL());
                 
               
        
        }
    }

    @FXML
    private void delaccount(ActionEvent event) {
        
          if(!iduse.getText().equals("")){
              p.setID_USER(Integer.parseInt(iduse.getText()));
              pd.block(p);
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
              alert.setTitle(" Account is deleted ");
              alert.setContentText("Your account is deleted you must log out ");
              alert.show();
              iduse.setText("");
              usrname.setText("");
              mailtext.setText("");
              passtext.setText("");
          
          }
          
        
       
        
         
    }
    
}
