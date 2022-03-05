/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.Control;

import com.trophy.dao.userdao;
import com.trophy.entity.Session;
import com.trophy.entity.users;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
    private void visitgroup(ActionEvent event) {
    }

    @FXML
    private void visitusergroup(ActionEvent event) {
    }

    @FXML
    private void logoutbutt(ActionEvent event) {
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
            
                 
               
        
        }
    }

    @FXML
    private void delaccount(ActionEvent event) {
        
          
          
        
       
        
         
    }
    
}
