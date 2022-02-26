/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.Controller;

import com.trophy.dao.CompetitionsDao;
import com.trophy.dao.TeamsDao;
import com.trophy.entity.Competitions;
import com.trophy.entity.Teams;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
public class TeamsController implements Initializable {

    @FXML
    private TextField inputIdTeam;
    @FXML
    private TextField inputTeamName;
    @FXML
    private Button btnAddTeam;
    @FXML
    private Button btnUpdateTeam;
    @FXML
    private Button btnDeleteTeam;
    @FXML
    private ChoiceBox inputIdCompT;
    @FXML
    private TextField inputCreatorName;
    @FXML
    private TextField SearchTeam;
    @FXML
    private TableView tableTeams;
    @FXML
    private TableColumn colIdTeam;
    @FXML
    private TableColumn<Teams,String> colIdCompTeam;
    @FXML
    private TableColumn colTeamName;
    @FXML
    private TableColumn colCreatorName;

    /**
     * Initializes the controller class.
     */
   
    
     TeamsDao td = new TeamsDao();
      @Override
    public void initialize(URL url, ResourceBundle rb) {
        
   
    /*inputIdCompT.getItems().addAll(CompetitionsDao.getInstance().getAllCompetitions()
                                    .stream().map(t -> t.getId_competion())
                                    .collect(Collectors.toList()));*/
    
        inputIdCompT.getItems().addAll(CompetitionsDao.getInstance().getAllCompetitions());
    ObservableList<Teams> listTeams =(ObservableList<Teams>)  TeamsDao.getInstance().getAllTeams();
    //ObservableList<Teams> listTeams =FXCollections.observableArrayList(  TeamsDao.getInstance().getAllTeams());
       tableTeams.setItems(td.getAllTeams());       
        colIdTeam.setCellValueFactory(new PropertyValueFactory<>("id_team"));
       colIdCompTeam.setCellValueFactory(new PropertyValueFactory<>("competitions"));
//            colIdCompTeam.setCellValueFactory(cell->{
//        return cell.getValue().getCompetitions().getGame_name();
//    });
        colTeamName.setCellValueFactory(new PropertyValueFactory<>("team_name"));
        colCreatorName.setCellValueFactory(new PropertyValueFactory<>("creator"));
        System.out.println(listTeams);
    
    
        // TODO
    }  

    @FXML
    private void Add(ActionEvent event) {
        
        Teams te = new Teams();
        if(!inputIdTeam.getText().equals("")&&!inputTeamName.getText().equals("")&&!inputCreatorName.getText().equals("")){
        te.setId_team((Integer.parseInt( inputIdTeam.getText())));
        te.setCompetitions(CompetitionsDao.getInstance().displayById(((Competitions)inputIdCompT.getValue()).getId_competion()));
        te.setTeam_name(inputTeamName.getText());
        te.setCreator(inputCreatorName.getText());
        }
        td.insert(te);
          System.out.println(te.toString());
    
          inputIdTeam.setText("");
          inputIdCompT.setValue(null);
          inputTeamName.setText("");
          inputCreatorName.setText("");
          
          tableTeams.setItems(td.getAllTeams());
        
    }

    @FXML
    private void Update(ActionEvent event) {
        
        Teams te = new Teams();
        if(!inputIdTeam.getText().equals("")&&!inputTeamName.getText().equals("")&&!inputCreatorName.getText().equals(""))
        {
        te.setId_team((Integer.parseInt( inputIdTeam.getText())));
        te.setCompetitions(CompetitionsDao.getInstance().displayById(((Competitions)inputIdCompT.getValue()).getId_competion()));
        te.setTeam_name(inputTeamName.getText());
        te.setCreator(inputCreatorName.getText());
        
        td.update(te);
           
        System.out.println(te.toString());
    
          inputIdTeam.setText("");
          inputIdCompT.setValue(null);
          inputTeamName.setText("");
          inputCreatorName.setText("");
          
          tableTeams.setItems(td.getAllTeams());
          
          System.out.println(td.getAllTeams());
        }
    }

    @FXML
    private void Delete(ActionEvent event) {
        
         Teams te = new Teams();
        if(!inputIdTeam.getText().equals("")&&!inputTeamName.getText().equals("")&&!inputCreatorName.getText().equals("")){
        te.setId_team((Integer.parseInt( inputIdTeam.getText())));
        te.setCompetitions(CompetitionsDao.getInstance().displayById(((Competitions)inputIdCompT.getValue()).getId_competion()));
        te.setTeam_name(inputTeamName.getText());
        te.setCreator(inputCreatorName.getText());
        
        td.delete(te.getId_team());
        
         inputIdTeam.setText("");
          inputIdCompT.setValue(null);
          inputTeamName.setText("");
          inputCreatorName.setText("");
          
          tableTeams.setItems(td.getAllTeams());
            }
    }
    
     @FXML
    private void clickTable(MouseEvent event) {
       Teams te =(Teams) tableTeams.getSelectionModel().getSelectedItem();
         
          inputIdTeam.setText(String.valueOf(te.getId_team()));
          inputIdCompT.setValue(te.getCompetitions());
          inputTeamName.setText(te.getTeam_name()+"");
          inputCreatorName.setText(te.getCreator());  
    }
    

    @FXML
    private void Search(ActionEvent event) {
        tableTeams.setItems(td.getSearchTeams(SearchTeam.getText()));
    }

   
    
    
    
    
    
}


