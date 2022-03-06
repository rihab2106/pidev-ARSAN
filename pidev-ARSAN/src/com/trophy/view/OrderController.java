/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.view;

import com.trophy.dao.OrderDao;
import com.trophy.entity.Order;
import java.net.URL;
import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
int Month,Year,ID;
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

    @FXML
    private void Order(ActionEvent event) {
        if(TXTCARD.getText().equals("")||TXTPASS.getText().equals("")||TXTNAME.getText().equals("")){
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("there is a missing field!");
                    alert.show();
                    refresh();
           } else if ( !(TXTCARD.getText().length()==16) || !(TXTCARD.getText().toString().matches("[0-9]+"))) {
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("NUMBER ON THe CARD is Composed of 16 number!");
                    alert.show();
                    refresh();
           }else if (!(TXTPASS.getText().length()==3) ||!(TXTPASS.getText().toString().matches("[0-9]+"))){
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("NUMBER ON THe Password is Composed of 4 number!");
                    alert.show();
                    refresh();
           
           }  else if(!(TXTNAME.getText().matches("[a-zA-Z]+"))&& !(TXTNAME.getText().contains(" "))) {
               
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("your name should contain only letters and a space!");
                    alert.show();
                    refresh();
                   }else{
        OrderDao od = new OrderDao();
        Order o = new Order();
        o.setOrderID(ID);
        o.setCardNumber(TXTCARD.getText());
        o.setMonth(Month);
        o.setYear(Year);
        o.setCardPassword(TXTPASS.getText());
        o.setName(TXTNAME.getText());
        od.insert(o);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
    
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("your data is  added successfuly!");
                    alert.show();
                    refresh();
        }
        
    }

    @FXML
    private void Exit(ActionEvent event) {
    }
    
}
