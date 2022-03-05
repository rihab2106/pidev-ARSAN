/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.Controller;

import com.trophy.dao.CompetitionsDao;
import com.trophy.dao.TeamsDao;
import com.trophy.entity.Competitions;
import com.trophy.entity.SendMail;
import com.trophy.entity.Teams;
import com.trophy.utils.ConnexionSingleton;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
//    @FXML
//    private TextField inputCreatorName;
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
//    @FXML
//    private TableColumn colCreatorName;
     @FXML
    private Button gotocomp;
      @FXML
    private Button exportToExcel;

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
    
       tableTeams.setItems(td.getAllTeams());       
        colIdTeam.setCellValueFactory(new PropertyValueFactory<>("id_team"));
       colIdCompTeam.setCellValueFactory(new PropertyValueFactory<>("competitions"));
        colTeamName.setCellValueFactory(new PropertyValueFactory<>("team_name"));
        //colCreatorName.setCellValueFactory(new PropertyValueFactory<>("creator"));
        System.out.println(listTeams);
    
        SearchTeam.textProperty().addListener((obj,old,ne)->{
            tableTeams.setItems(td. getSearchTeams(ne));});
        
        
        
        
        gotocomp.setOnAction((event) -> {

            try {
                Parent p = FXMLLoader.load(getClass().getResource("/com/trophy/view/Competitions.fxml"));
                Scene scene = new Scene(p);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println("error in displaying Teams");
            }
        });
    
         //exportToExcel = new Button ('Export to excel'); 
               ConnexionSingleton cs = ConnexionSingleton.getInstance();
//
            exportToExcel.setOnAction((actionEvent -> {
            exportToExcel.setFont(Font.font("Sansserif", 15));
            String query = "select * from Teams";
           try {
        
            Statement pst= cs.getCnx().createStatement();
            ResultSet rs = pst.executeQuery(query);
          
//            //Apache POI Jar Link
//            //https://www.apache.org/dyn/closer.lua/poi/release/bin/poi-bin-5.2.0-20220106.tgz
//            
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("List of Teams");
            XSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("id_team");
            header.createCell(1).setCellValue("Competition Name");
            header.createCell(2).setCellValue("team_name");
            //header.createCell(3).setCellValue("creator");
         
//            
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.setColumnWidth(3, 256*25);//256-character width 
//            
            sheet.setZoom(150); //scale(150%)
//            



               int index = 1; 
               while (rs.next()) {
               XSSFRow row = sheet.createRow(index);
               row.createCell(0).setCellValue(rs.getString("ID_TEAM"));
               row.createCell(1).setCellValue(CompetitionsDao.getInstance().displayById(rs.getInt("ID_COMPETION")).toString());
               row.createCell(2).setCellValue(rs.getString("TEAM_NAME"));
               //row.createCell(3).setCellValue(rs.getString("CREATOR"));
             
                index++;

            }
            FileOutputStream fileOut = new FileOutputStream ("ListOfTeams.xlsx");
            wb.write(fileOut);
            fileOut.close();
//            
//         
         
            } catch (SQLException ex) {
              Logger.getLogger(CompetitionsController.class.getName()).log(Level.SEVERE, null, ex);
          } catch (IOException ex) {
               Logger.getLogger(CompetitionsController.class.getName()).log(Level.SEVERE, null, ex);
            }
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Information Dialog");
           alert.setHeaderText(null);
           alert.setContentText("Teams details exported to excel Sheet");
           alert.show();
            //pst.close();
            //rs.close();

            }));    
    }  

    @FXML
    private void Add(ActionEvent event) {
        
        Teams te = new Teams();
        if(!inputIdTeam.getText().equals("")&&!inputTeamName.getText().equals("")//&&!inputCreatorName.getText().equals("")
                ){
             if(TeamsDao.getInstance().SearchT((Integer.parseInt(inputIdTeam.getText()))))
            {
                Alert a =new Alert(Alert.AlertType.WARNING,"ID already exists",ButtonType.OK); 
           a.show();
           return; 
            }
        te.setId_team((Integer.parseInt( inputIdTeam.getText())));
        te.setCompetitions(CompetitionsDao.getInstance().displayById(((Competitions)inputIdCompT.getValue()).getId_competion()));
        te.setTeam_name(inputTeamName.getText());
       // te.setCreator(inputCreatorName.getText());
        }
        td.insert(te);
          System.out.println(te.toString());
    
          inputIdTeam.setText("");
          inputIdCompT.setValue(null);
          inputTeamName.setText("");
         // inputCreatorName.setText("");
          
          tableTeams.setItems(td.getAllTeams());
          
          SendMail mail= new SendMail();
          mail.send("adtrophyhun@gmail.com");
          
        
    }

    @FXML
    private void Update(ActionEvent event) {
        
        Teams te = new Teams();
        if(!inputIdTeam.getText().equals("")&&!inputTeamName.getText().equals("")//&&!inputCreatorName.getText().equals("")
                )
        {
            
            if(!TeamsDao.getInstance().SearchT((Integer.parseInt(inputIdTeam.getText()))))
            {
                Alert a =new Alert(Alert.AlertType.WARNING,"you can't change ID",ButtonType.OK); 
           a.show();
           return; 
            }
        te.setId_team((Integer.parseInt( inputIdTeam.getText())));
        te.setCompetitions(CompetitionsDao.getInstance().displayById(((Competitions)inputIdCompT.getValue()).getId_competion()));
        te.setTeam_name(inputTeamName.getText());
      //  te.setCreator(inputCreatorName.getText());
        
        td.update(te);
           
        System.out.println(te.toString());
    
          inputIdTeam.setText("");
          inputIdCompT.setValue(null);
          inputTeamName.setText("");
          //inputCreatorName.setText("");
          
          tableTeams.setItems(td.getAllTeams());
          
          System.out.println(td.getAllTeams());
        }
    }

    @FXML
    private void Delete(ActionEvent event) {
        
         Teams te = new Teams();
        if(!inputIdTeam.getText().equals("")&&!inputTeamName.getText().equals("")//&&!inputCreatorName.getText().equals("")
                ){
        te.setId_team((Integer.parseInt( inputIdTeam.getText())));
        te.setCompetitions(CompetitionsDao.getInstance().displayById(((Competitions)inputIdCompT.getValue()).getId_competion()));
        te.setTeam_name(inputTeamName.getText());
        //te.setCreator(inputCreatorName.getText());
        
        td.delete(te.getId_team());
        
         inputIdTeam.setText("");
          inputIdCompT.setValue(null);
          inputTeamName.setText("");
         // inputCreatorName.setText("");
          
          tableTeams.setItems(td.getAllTeams());
            }
    }
    
     @FXML
    private void clickTable(MouseEvent event) {
       Teams te =(Teams) tableTeams.getSelectionModel().getSelectedItem();
         
          inputIdTeam.setText(String.valueOf(te.getId_team()));
          inputIdCompT.setValue(te.getCompetitions());
          inputTeamName.setText(te.getTeam_name()+"");
         // inputCreatorName.setText(te.getCreator());  
    }
    
// @FXML
//    private void Search(ActionEvent event) {
//        tableTeams.setItems(td.getSearchTeams(SearchTeam.getText()));
//   }

   
   
}


