/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.Controller;

import com.trophy.dao.CompetitionsDao;
import com.trophy.entity.Competitions;
import com.trophy.utils.ConnexionSingleton;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
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
        @FXML
    private Button exportToExcel;
      
     
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
                //parent : élly béch yé5éou nodes lkol
                //scene: l window élly béch t7ot feha l graphic lkol 
                //stage : béch yékhou scene 
                Parent p = FXMLLoader.load(getClass().getResource("/com/trophy/view/Teams.fxml"));
                Scene scene = new Scene(p);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println("error in displaying Teams");
            }
        });
             
             
         //exportToExcel = new Button ('Export to excel'); 
//                ConnexionSingleton cs = ConnexionSingleton.getInstance();
//
//            exportToExcel.setOnAction((actionEvent -> {
//            exportToExcel.setFont(Font.font("Sansserif", 15));
//            String query = "select * from Competitions";
//            try {
//        
//            Statement pst= cs.getCnx().createStatement();
//            ResultSet rs = pst.executeQuery(query);
//            
//            //Apache POI Jar Link
//            //https://www.apache.org/dyn/closer.lua/poi/release/bin/poi-bin-5.2.0-20220106.tgz
//            
//            XSSFWorkbook wb = new XSSFWorkbook();
//            XSSFSheet sheet = wb.createSheet("List of Competitions");
//            XSSFRow header = sheet.createRow(0);
//            header.createCell(0).setCellValue("Id_competion");
//            header.createCell(1).setCellValue("game_name");
//            header.createCell(2).setCellValue("dateofcomp");
//         
//            
//            sheet.autoSizeColumn(1);
//            sheet.autoSizeColumn(2);
//            sheet.setColumnWidth(3, 256*25);//256-character width 
//            
//            sheet.setZoom(150); //scale(150%
//            
//            
//
//            
//            int index = 1; 
//            while (rs.next()) {
//                XSSFRow row = sheet.createRow(index);
//                row.createCell(0).setCellValue(rs.getString("Id_competion"));
//                row.createCell(1).setCellValue(rs.getString("game_name"));
//                row.createCell(2).setCellValue(rs.getString("dateofcomp"));
//              
//                index++;
//
//            }
//            FileOutputStream fileOut = new FileOutputStream ("ListOfCompetitions.xlsx");
//            wb.write(fileOut);
//            fileOut.close();
//            
//         
//          
//            } catch (SQLException ex) {
//                Logger.getLogger(CompetitionsController.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (IOException ex) {
//                Logger.getLogger(CompetitionsController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//              Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Information Dialog");
//            alert.setHeaderText(null);
//            alert.setContentText("Competitions details exported to excel Sheet");
//            alert.show();
//            //pst.close();
//            //rs.close();
//
//            }));    
             
             
//             inputIdComp.setTextFormatter(new TextFormatter<>(ch ->{     
//    Pattern pp=Pattern.compile("[0-9]+");
//    if (pp.matcher(ch.getControlNewText()).matches())
//        return ch;
//    return null;
//    }));
            
        
    }    
 
    
    @FXML
       private void Add(ActionEvent event) {
        //instance mél classe comp
       Competitions comp = new Competitions();
       //kén l form fér8a mana3melech insert 
        if(!inputIdComp.getText().equals("")&&!inputGameNameComp.getText().equals("")){
            //y7ot fy data mél form lel comp
            if(CompetitionsDao.getInstance().Search((Integer.parseInt( inputIdComp.getText()))))
            {
                Alert a =new Alert(Alert.AlertType.WARNING,"ID already exists",ButtonType.OK); 
           a.show();
           return; 
            }
        comp.setId_competion((Integer.parseInt( inputIdComp.getText())));
        
        comp.setGame_name(inputGameNameComp.getText());
        if(inputDateofComp.getValue().compareTo(LocalDate.now())<0)
        {
           Alert a =new Alert(Alert.AlertType.WARNING,"Date is already passed",ButtonType.OK); 
           a.show();
           return;
        }
        comp.setDateofcomp(inputDateofComp.getValue());
        
        }
        cd.insert(comp);
          System.out.println(comp.toString());
              //béch yafargh l form ba3d ma3mal insert
          inputIdComp.setText(" ");
          inputGameNameComp.setText("");
          inputDateofComp.setValue(null);
          
          tableComp.setItems(cd.getAllCompetitions());
              
    }

    
     
    @FXML
    private void Update(ActionEvent event) {
        
         Competitions comp = new Competitions();
        if(!inputIdComp.getText().equals("")&&!inputGameNameComp.getText().equals("")){
            
            if(!CompetitionsDao.getInstance().Search((Integer.parseInt(inputIdComp.getText()))))
            {
                Alert a =new Alert(Alert.AlertType.WARNING,"ID already exists",ButtonType.OK); 
           a.show();
           return; 
            }
        comp.setId_competion((Integer.parseInt( inputIdComp.getText())));
        comp.setGame_name(inputGameNameComp.getText());
        if(inputDateofComp.getValue().compareTo(LocalDate.now())<0)
        {
           Alert a =new Alert(Alert.AlertType.WARNING,"Date is already passed",ButtonType.OK); 
           a.show();
           return;
        }
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
        //béch yékhou (to retrieve) lobject mén selected row 
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
                //bech traja3lék l comp lkol m database
                .getInstance().getAllCompetitions()
                //trod list stream
                .stream()
                //t1 w t2 2 objets competition
                .sorted((t1,t2)->{
                  //comparaison entre 2 date
                return t1.getDateofcomp().compareTo(t2.getDateofcomp());
                })
                //béch yraja3 stream list 
                .collect(Collectors.toList())));
    }
    
}
    

