/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author rihab bns
 */
public class OrderController implements Initializable {

    @FXML
    private TextField TXTCARD;
    @FXML
    private TextField TXTPASS;
    @FXML
    private TextField TXTNAME;
    @FXML
    private Spinner<Integer> month;
    @FXML
    private Spinner<Integer> year;
    @FXML
    private Button btndone;
    @FXML
    private Button btnexit;
int Month,Year;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SpinnerValueFactory<Integer> valueFactorymonth = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,12);
        SpinnerValueFactory<Integer> valueFactoryyear = new SpinnerValueFactory.IntegerSpinnerValueFactory(2022,2100);
        valueFactorymonth .setValue(1);
        month.setValueFactory(valueFactorymonth);
        Month =month.getValue();
        valueFactoryyear.setValue(2022);
        year.setValueFactory(valueFactoryyear);
        Year =year.getValue();
    }    
    
}
