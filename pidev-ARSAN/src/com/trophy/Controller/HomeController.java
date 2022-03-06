/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rihab bns
 */
public class HomeController implements Initializable {

    @FXML
    private Button lblProducts;
    @FXML
    private Button lblServices;
    @FXML
    private Button Iblusers;
    @FXML
    private Button IblTrophies;
    @FXML
    private ImageView imgshop;
    @FXML
    private ImageView btncompetition;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void openProducts(ActionEvent event) {
    }

    @FXML
    private void openServices(ActionEvent event) {
    }

    @FXML
    private void openusers(ActionEvent event) {
    }

    @FXML
    private void showshop(MouseEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("/com/trophy/view/Product.fxml"));
       Stage window =(Stage)imgshop.getScene().getWindow();
       window.setScene(new Scene(root));
    }

    @FXML
    private void showuser(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/trophy/view/useraccount.fxml"));
       Stage window =(Stage)imgshop.getScene().getWindow();
       window.setScene(new Scene(root));
    }

    @FXML
    private void showcompetition(MouseEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/com/trophy/view/Competitions.fxml"));
       Stage window =(Stage)btncompetition.getScene().getWindow();
       window.setScene(new Scene(root));
    }
    
}
