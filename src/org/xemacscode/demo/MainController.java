/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xemacscode.demo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;

/**
 * FXML Controller class
 *
 * @author rihab bns
 */
public class MainController implements Initializable {

    @FXML
    private TextField txtUserName;
    @FXML
    private Label btnForgot;
    @FXML
    private Button btnFB;
    @FXML
    private Label lblError;
    @FXML
    private Button signin;
    @FXML
    private Button btnsignup;
    @FXML
    private PasswordField password;
    @FXML
    private Label lblServer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void isSign(DragEvent event) {
    }

    @FXML
    private void isSign(ActionEvent event) {
    }
    
}
