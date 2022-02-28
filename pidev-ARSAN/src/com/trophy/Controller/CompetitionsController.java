/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.Controller;

import com.trophy.dao.CompetitionsDao;
import com.trophy.entity.Competitions;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Syrine
 */
public class CompetitionsController implements Initializable {

    @FXML
    private TextField inputIdComp;
    @FXML
    private TextField inputGameNameComp;
    @FXML
    private Button btnAddComp;
    @FXML
    private Button btnUpdateComp;
    @FXML
    private Button btnDeleteComp;
    @FXML
    private DatePicker inputDateofComp;
    @FXML
    private TextField SearchComp;
    @FXML
    private TableView tableComp;
    @FXML
    private TableColumn colIdComp;
    @FXML
    private TableColumn colNameGameComp;
    @FXML
    private TableColumn colDateofComp;
     @FXML
    private Button sortComp;
      @FXML
    private Button gototeams;
     
    /**
     * Initializes the controller class.
     */
    
       CompetitionsDao cd = new CompetitionsDao();
   
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
  
 ObservableList<Competitions> listComp =(ObservableList<Competitions>)  CompetitionsDao.getInstance().getAllCompetitions();
       tableComp.setItems(cd.getAllCompetitions());       
        colIdComp.setCellValueFactory(new PropertyValueFactory<>("Id_competion"));
        colNameGameComp.setCellValueFactory(new PropertyValueFactory<>("game_name"));
        colDateofComp.setCellValueFactory(new PropertyValueFactory<>("dateofcomp"));
          
        System.out.println(listComp);
        
            SearchComp.textProperty().addListener((obj,old,ne)->{
            tableComp.setItems(cd.getSearchCompetitions(ne));});
            
            
             gototeams.setOnAction((event) -> {

            try {
                Parent p = FXMLLoader.load(getClass().getResource("/com/trophy/view/Teams.fxml"));
                Scene scene = new Scene(p);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println("error in displaying Teams");
            }
        });
            
        
    }    
 
    
    @FXML
       private void Add(ActionEvent event) {
        
       Competitions comp = new Competitions();
        if(!inputIdComp.getText().equals("")&&!inputGameNameComp.getText().equals("")){
        comp.setId_competion((Integer.parseInt( inputIdComp.getText())));
        comp.setGame_name(inputGameNameComp.getText());
        comp.setDateofcomp(inputDateofComp.getValue());
        }
        cd.insert(comp);
          System.out.println(comp.toString());
    
          inputIdComp.setText("");
          inputGameNameComp.setText("");
          inputDateofComp.setValue(null);
          tableComp.setItems(cd.getAllCompetitions());
              
    }

    
     
    @FXML
    private void Update(ActionEvent event) {
        
         Competitions comp = new Competitions();
        if(!inputIdComp.getText().equals("")&&!inputGameNameComp.getText().equals("")){
        comp.setId_competion((Integer.parseInt( inputIdComp.getText())));
        comp.setGame_name(inputGameNameComp.getText());
        comp.setDateofcomp(inputDateofComp.getValue());
        }        
        cd.update(comp);
        
        
        System.out.println(comp.toString());
    
          inputIdComp.setText("");
          inputGameNameComp.setText("");
          inputDateofComp.setValue(null);
          
          tableComp.setItems(cd.getAllCompetitions());
          
          System.out.println(cd.getAllCompetitions());
            }
    

    @FXML
    private void Delete(ActionEvent event) {
        
        Competitions comp = new Competitions();


          if(!inputIdComp.getText().equals("")&&!inputGameNameComp.getText().equals("")){
          comp.setId_competion(Integer.parseInt(inputIdComp.getText()));
          comp. setGame_name(inputGameNameComp.getText());
          comp.setDateofcomp(inputDateofComp.getValue());
         
           cd.delete(comp.getId_competion());
        
         inputIdComp.setText("");  
         inputGameNameComp.setText("");
         inputDateofComp.setValue(null);
      
         tableComp.setItems(cd.getAllCompetitions());
     
    }
    
}
    
    
    
        @FXML
    private void clickTable(MouseEvent event) {
     Competitions comp =(Competitions) tableComp.getSelectionModel().getSelectedItem();
                
          inputIdComp.setText(String.valueOf(comp.getId_competion()));
          inputGameNameComp.setText(comp.getGame_name()+"");
          inputDateofComp.setValue(comp.getDateofcomp());
          
           
    }
    
//@FXML
//    private void Search(ActionEvent event) {
//       
//       tableComp.setItems(cd.getSearchCompetitions(SearchComp.getText()));
//    }

    @FXML
    private void sortComp(ActionEvent event) {
        
        tableComp.setItems(FXCollections.observableArrayList(CompetitionsDao
                .getInstance().getAllCompetitions()
                .stream()
                .sorted((t1,t2)->{
                  
                return t1.getDateofcomp().compareTo(t2.getDateofcomp());
                })
                .collect(Collectors.toList())));
    }
    
           
    
   
}
    

