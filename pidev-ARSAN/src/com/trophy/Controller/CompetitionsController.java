/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.Controller;

import com.trophy.dao.CompetitionsDao;
import com.trophy.entity.Competitions;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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

    /**
     * Initializes the controller class.
     */
    
       CompetitionsDao cd = new CompetitionsDao();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
  
 ObservableList<Competitions> listComp =(ObservableList<Competitions>)  CompetitionsDao.getInstance().getAllCompetitions();
       tableComp.setItems(cd.getAllCompetitions());       
        colIdComp.setCellValueFactory(new PropertyValueFactory<>("ID_COMPETION"));
        colNameGameComp.setCellValueFactory(new PropertyValueFactory<>("GAME_NAME"));
        colDateofComp.setCellValueFactory(new PropertyValueFactory<>("DATEOFCOMP"));
          
        System.out.println(listComp);
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
          inputDateofComp.setValue(LocalDate.MIN);
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
          inputDateofComp.setValue(LocalDate.MIN);
          
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
         inputDateofComp.setValue(LocalDate.MIN);
      
         tableComp.setItems(cd.getAllCompetitions());
     
    }
    
}
    
    
    
        @FXML
    private void clickTable(MouseEvent event) {
     
        
    }
    
    @FXML
    private void Search(ActionEvent event) {
        
        tableComp.setItems(cd.getSearchCompetitions(SearchComp.getText()));
    }
}
    
