/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.Control;

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
    private Button lblServices;
    @FXML
    private Button lblProducts;
    @FXML
    private Button Iblusers;
    @FXML
    private Button IblTrophies;
    @FXML
    private ImageView imgshop;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void openServices(ActionEvent event) {
    }

    @FXML
    private void openProducts(ActionEvent event) {
    }

    @FXML
    private void openusers(ActionEvent event) {
    }

    @FXML
    private void showshop(MouseEvent event) {
    }

    @FXML
    private void OpenNews(MouseEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("/com/trophy/view/Newss.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
            
    }
    
}
