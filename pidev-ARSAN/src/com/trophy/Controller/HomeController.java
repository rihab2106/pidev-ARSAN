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
    private Button lblProducts;
    @FXML
    private Button Iblusers;
    @FXML
    private Button IblTrophies;
    @FXML
    private ImageView imgshop;
    @FXML
    private ImageView btncompetition;
    @FXML
    private ImageView btntrophy;
    @FXML
    private Button lblNews;
    @FXML
    private ImageView btnuser;
    @FXML
    private ImageView btnnews;
    @FXML
    private Button btnlogout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void openProducts(ActionEvent event) throws IOException {
        
    }


    @FXML
    private void openusers(ActionEvent event) throws IOException {
       
    }

    @FXML
    private void showshop(MouseEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("/com/trophy/view/Shop.fxml"));
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

    @FXML
    private void showgames(MouseEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/com/trophy/view/FrontGames.fxml"));
       Stage window =(Stage)btntrophy.getScene().getWindow();
       window.setScene(new Scene(root));
    }

    @FXML
    private void opennews(ActionEvent event) {
    }

    @FXML
    private void opentrophies(ActionEvent event) throws IOException {
        
    }

    @FXML
    private void showNews(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/trophy/view/UserView.fxml"));
       Stage window =(Stage)imgshop.getScene().getWindow();
       window.setScene(new Scene(root));
    }

    @FXML
    private void logoutt(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent parent = FXMLLoader.load(getClass().getResource("/com/trophy/view/Login.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            } catch (IOException ex) {
                System.out.println("error , you can Logout ");
            }
    }
    
    
}
