/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rihab bns
 */
public class HomeAdminController implements Initializable {

    @FXML
    private ImageView btnshop;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void showshop(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/trophy/view/Product.fxml"));
       Stage window =(Stage)btnshop.getScene().getWindow();
       window.setScene(new Scene(root));
    }
    
}
