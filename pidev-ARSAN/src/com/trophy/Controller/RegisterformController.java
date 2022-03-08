/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.Controller;
import com.trophy.dao.userdao;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class RegisterformController implements Initializable {

    @FXML
    private AnchorPane fnametext;
    @FXML
    private TextField fulnametxt;
    @FXML
    private TextField idtext;
    @FXML
    private PasswordField passtext;
    @FXML
    private TextField emailtext;
  
    /**
     * Initializes the controller class.
     */
   

    @FXML
    private Button submit;
    @FXML
    private Button back;

//    private void register(ActionEvent event) {
//       
//       
//         users p = new users();
//        if(idtext.getText().equals("")&&!fulnametxt.getText().equals("") &&!passtext.getText().equals("")&&!emailtext.getText().equals("")){
//       p.setID_USER(Integer.parseInt(idtext.getText()));
//       p.setFULL_NAME(fulnametxt.getText());
//       p.setPASSWORD(passtext.getText());
//       p.setEMAIL(emailtext.getText());
//       
//        pd.insert(p);
//        
//      JOptionPane.showMessageDialog(null,"saved");
//      
//    
//       
//       
//        }
//       
//     
//    }
      

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userdao pd = new userdao();
        submit.setOnAction(event->{
             if( (fulnametxt.getText().isEmpty()|| passtext.getText().isEmpty()
                 || idtext.getText().isEmpty()|| emailtext.getText().isEmpty() ) ){
               
                  Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("User can not be created !!! ");
               alert.setContentText("Please fill in the filds !!! ");
               alert.show();
               
               
           }
              
             
             
             
             else if(pd.checkID(Integer.parseInt(idtext.getText()))){
                     int ID_USER = Integer.parseInt(idtext.getText());
                        
                Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("User can not be created !!! ");
               alert.setContentText("id already exist try agin please ");
               alert.show();
               users u = new users( ID_USER,fulnametxt.getText(),emailtext.getText(),
            passtext.getText());
           
            
        
             }else{
                 int ID_USER = Integer.parseInt(idtext.getText());
                 users u = new users( ID_USER,fulnametxt.getText(),emailtext.getText(),
            passtext.getText());
            pd.insert(u);
//               try {
//             
//            FXMLLoader fxmlLoader = new FXMLLoader();
//            Parent parent = FXMLLoader.load(getClass().getResource("/com/trophy/view/registerform.fxml"));
//            Scene scene = new Scene(parent);
//            Stage stage = new Stage();
//            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            stage.setScene(scene);
//            stage.show();
//            } catch (IOException ex) {
//                System.out.println("error in displaying the interface");
//            }  
 
            
               
               
                     
             
           
             
           
           
            
            
            fulnametxt.setText("");
            passtext.setText("");
            idtext.setText("");
            emailtext.setText("");
            
            
        
             try {
             
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent parent = FXMLLoader.load(getClass().getResource("/com/trophy/view/Login.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            } catch (IOException ex) {
                System.out.println("error in displaying the interface");
            }  
 
            
             }
            
        });
        
        
        
    
//         // Animation
//  TranslateTransition translate = new TranslateTransition();
//  translate.setNode(photoAnime);
//  translate.setDuration(Duration.millis(1000));
//  translate.setCycleCount(TranslateTransition.INDEFINITE);
//  translate.setByX(10);
//  translate.setByY(200);
//  translate.setAutoReverse(true);
//  translate.play();
//  
    
  
    }    

    @FXML
    private void back(ActionEvent event) {
        
         try {
             
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent parent = FXMLLoader.load(getClass().getResource("/com/trophy/view/Login.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            } catch (IOException ex) {
                System.out.println("error in displaying the interface");
            }  
    }
    }


    
    

