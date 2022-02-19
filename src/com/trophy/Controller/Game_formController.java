/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.trophy.Controller;

import com.trophy.entity.Games;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author anasb
 */
public class Game_formController implements Initializable {

    @FXML
    private TextField game_in_name;
    @FXML
    private TextArea game_in_desc;
    @FXML
    private ChoiceBox<?> game_cb_cat;
    @FXML
    private TextField game_in_rate;
    @FXML
    private Button game_ok;
    @FXML
    private Button game_cancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    

    @FXML
    private void OK(ActionEvent event) {
    }

    @FXML
    private void cancel(ActionEvent event) {
    }
    
}
