/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

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
    private Button lblEmployees;
    @FXML
    private Button lblStatistics;

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
    private void openEmployee(ActionEvent event) {
    }
    
}
