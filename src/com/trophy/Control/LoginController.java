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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class LoginController implements Initializable {

    @FXML
    private Button signup;
    @FXML
    private TextField tfLogEmail;
    @FXML
    private  PasswordField tfLogPwd;
    @FXML
    private Button signin;
   
    @FXML
    private TextField tfname;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      }   
    
    
    

    
   

    @FXML
    private void signin(ActionEvent event) {
        userdao us = new userdao();
        users user=new users();
           if( (!tfname.getText().isEmpty()&& !tfLogEmail.getText().isEmpty()&& !tfLogPwd.getText().isEmpty()) ){
               if(us.checklogin(tfname.getText(),tfLogEmail.getText(), tfLogPwd.getText())==true){
                   user.setFULL_NAME(tfname.getText());
                   user.setEMAIL(tfLogEmail.getText());
                   user.setPASSWORD(tfLogPwd.getText());
                   if((("admin1@esprit.tn".equals(tfLogEmail.getText()))&&("admin1".equals(tfLogPwd.getText())))
                           ||(("admin2@esprit.tn".equals(tfLogEmail.getText()))&&("admin2".equals(tfLogPwd.getText())))){
             try {
             Session.StartSession().setSessionUser(user);
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent parent = FXMLLoader.load(getClass().getResource("/com/trophy/view/adminaccount.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            } catch (IOException ex) {
                System.out.println("error in displaying User Interface");
            }
                   }
                   else{
                       try {
             Session.StartSession().setSessionUser(user);
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent parent = FXMLLoader.load(getClass().getResource("/com/trophy/view/useraccount.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            } catch (IOException ex) {
                System.out.println("error in displaying User Interface");
            }
                   }
          } 
               else{
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("User not found !!!  ");
               alert.setContentText("User not found !!! ");
               alert.show();
           }
               
           }
           else{
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("User not found !!! ");
               alert.setContentText("Please fill in the filds !!! ");
               alert.show();
 
           }
              

    }

    @FXML
    private void signup(ActionEvent event) {
        
                try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent parent = FXMLLoader.load(getClass().getResource("/com/trophy/view/registerform.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            } catch (IOException ex) {
                System.out.println("error in displaying Article");
            }
    }
  
     
    
}
