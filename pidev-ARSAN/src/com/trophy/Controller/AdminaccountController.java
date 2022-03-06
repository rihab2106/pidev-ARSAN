/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.Controller;

import com.trophy.dao.groupsdao;
import com.trophy.dao.userdao;
import com.trophy.entity.Groups;
import com.trophy.entity.users;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class AdminaccountController implements Initializable {

    @FXML
    private Button bttndelete;
    @FXML
    private TableView tbadmin;
    @FXML
    private TableColumn idfield;
    @FXML
    private TableColumn flfield;
    @FXML
    private TableColumn  emailfld;
    @FXML
    private Button buttsearch;
    @FXML
    private TextField txtsearch;
    @FXML
    private TextField iduser;
    @FXML
    private Button buttblock;
    @FXML
    private TableColumn isact;
    @FXML
    private Button ub;
    @FXML
    private Button bts;
    @FXML
    private Button reftext;
    @FXML
    private Button logouttxt;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          idfield.setCellValueFactory(new PropertyValueFactory<>("ID_USER"));
        
       
         flfield.setCellValueFactory(new PropertyValueFactory<>("FULL_NAME"));
         emailfld.setCellValueFactory(new PropertyValueFactory<>("EMAIL"));
        
                isact.setCellValueFactory(new PropertyValueFactory<>("ISACTIVE"));
       tbadmin.setItems(pd.displayAll());
        
        
    }  
    
     userdao pd=new userdao();

    @FXML
    private void deleteuser(ActionEvent event) {
        
         users p = new users();
        
          if(!iduser.getText().equals("") ){
          
       
        p.setID_USER(Integer.parseInt(iduser.getText()));
          
        pd.delete(p);
          
           iduser.setText("");
          tbadmin.setItems(pd.displayAll());
         
        
    }
    }

    @FXML
    private void searchuser(ActionEvent event) {
        
        tbadmin.setItems(pd.getSearchuser(txtsearch.getText()));
        
        
    }

    @FXML
    private void block(ActionEvent event) {
           users p = new users();
        
          if(!iduser.getText().equals("") ){
          
       
        p.setID_USER(Integer.parseInt(iduser.getText()));
          
        pd.block(p);
          
           iduser.setText("");
          tbadmin.setItems(pd.displayAll());
         
        
    }
        
    }

    @FXML
    private void unblock(ActionEvent event) {
        
         users p = new users();
        
          if(!iduser.getText().equals("") ){
          
       
        p.setID_USER(Integer.parseInt(iduser.getText()));
          
        pd.unblock(p);
          
           iduser.setText("");
          tbadmin.setItems(pd.displayAll());
         
        
        
    }
    
}

    @FXML
    private void sortusers(ActionEvent event) {
        //béch ta3mel sort 
        //
        tbadmin.setItems(FXCollections.observableArrayList(userdao
                
                .getInstance().displayAll()
           
                .stream()
        
                .sorted((t1,t2)->{
               
                return t1.getFULL_NAME().compareTo(t2.getFULL_NAME());
               // return t2.getID_USER().compareTo(t1.getID_USER());
                })
           
                .collect(Collectors.toList())));
        
        
        
    }

    @FXML
    private void refresh(ActionEvent event) {
         tbadmin.setItems(pd.displayAll());
    }

    @FXML
    private void logout(ActionEvent event) {
        
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
}
