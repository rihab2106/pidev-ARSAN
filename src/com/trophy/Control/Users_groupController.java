/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.Control;
//import javafx.mail.Message;
import com.trophy.dao.user_groupsdao;
import com.trophy.entity.Groups;
import com.trophy.entity.users;
import com.trophy.entity.users_groups;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;


/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class Users_groupController  implements Initializable  {

    @FXML
    private TableView tabl;
    @FXML
    private TableColumn frlist;
    @FXML
    private TextField textfriend;
     @FXML
    private TableColumn name1;
    @FXML
    private Button blockbutt;
    @FXML
    private Button textdelet;
    @FXML
    private TextField textgroup;
    @FXML
    private Button buttadd;
    @FXML
    private TableColumn statusfriend1;
    @FXML
    private Button unblockbutt;
    @FXML
    private Button showbutt;
    @FXML
    private TableColumn idgr;
   
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         //refere to table 
        
       
        frlist.setCellValueFactory(new PropertyValueFactory<>("ID_USER"));
        
       
         statusfriend1.setCellValueFactory(new PropertyValueFactory<>("status"));
        
         name1.setCellValueFactory(new PropertyValueFactory<>("FULL_NAME"));
         
         idgr.setCellValueFactory(new PropertyValueFactory<>("ID_GROUP"));
         

         //tbgroup.setItems(pd.displayAll());
         
    }    
    
    
    
    
    
    
    
    
    
    
    
   
    user_groupsdao pd=new user_groupsdao();
     
    Groups g=new Groups();

   /* private void addfriend(ActionEvent event) {
        
     
        Users u=new Users();
        if(!textfriend.getText().equals("") &&!textgroup.getText().equals("")){
       
       
             u.setID_USER(Integer.parseInt(textfriend.getText()));
             g.setID_GROUP(Integer.parseInt(textgroup.getText()));
       
        }
       
    pd.insert();
   
    System.out.println(g.toString());
    
          textfriend.setText("");
          textgroup.setText("");
          
          //tabl.setItems(pd.displayAllusers(g));
        
        
    }*/

    @FXML
    private void block(ActionEvent event)  {
        
   
      
    
      
      
        users_groups os=new users_groups();
         
       if(!textfriend.getText().equals("") &&!textgroup.getText().equals("")){
          
      
            try {
                os.setID_USER(Integer.parseInt(textfriend.getText()));
                os.setID_GROUP(Integer.parseInt(textgroup.getText()));
                
                
                pd.updateuser(os);
             

                
                
            Notifications notificationBuilder=Notifications.create().title("mail sent")
                    .text("the friend was blocked").graphic(null).hideAfter(Duration.seconds(7))
                    .position(Pos.TOP_RIGHT);
                                                
                    notificationBuilder.darkStyle();
                    notificationBuilder.showConfirm();
                
                javaMail.sendMail(pd.displayemail(os));
               
            } catch (Exception ex) {
                System.out.println(ex);
            }
              
   
}
       
          textfriend.setText("");
          textgroup.setText("");
          
         tabl.setItems(pd.displayAllusers(Integer.parseInt(textgroup.getText())));
    
    }
    

    @FXML
    private void delete(ActionEvent event) {
        
        
             users_groups os=new users_groups();
            
        
          if(!textfriend.getText().equals("") &&!textgroup.getText().equals("")){
          
             os.setID_USER(Integer.parseInt(textfriend.getText()));
             os.setID_GROUP(Integer.parseInt(textgroup.getText()));
          
        pd.deleteuser(os);
            
          textfriend.setText("");
          textgroup.setText("");
          tabl.setItems(pd.displayAllusers(Integer.parseInt(textgroup.getText())));
          
          }     
  
    }
    
   /* private void clickTable1(MouseEvent event) {
             users_groups pr =(users_groups) tabl.getSelectionModel().getSelectedItem();
           textgroup.setText(pr.getID_GROUP()+"");
          
       
    
    
    
}
    */
    
    private void searchuser(ActionEvent event) {
          Groups gr=new Groups();
         //gr.setID_GROUP(Integer.parseInt(textsearch.getText()));
                   
 
          //tabl.setItems(pd.displayAllusers(textsearch.getText()));
         
        //tabl.setItems(pd.getSearchuser(textsearch.getText()));
        //tabl.setItems(pd.displayAllusers(Integer.parseInt(textsearch.getText())));
    }

    @FXML
    private void add(ActionEvent event) {
         
           users_groups os=new users_groups();
         if(!textfriend.getText().equals("") &&!textgroup.getText().equals("")){
       
        os.setID_USER(Integer.parseInt(textfriend.getText()));
             os.setID_GROUP(Integer.parseInt(textgroup.getText()));
        pd.insert(os);
          
         textfriend.setText("");
          textgroup.setText("");
        tabl.setItems(pd.displayAllusers(Integer.parseInt(textgroup.getText())));
    }
}

    @FXML
    private void unblock(ActionEvent event) {
         users_groups os=new users_groups();
         if(!textfriend.getText().equals("") &&!textgroup.getText().equals("")){
       
        os.setID_USER(Integer.parseInt(textfriend.getText()));
             os.setID_GROUP(Integer.parseInt(textgroup.getText()));
          pd.updateuser2(os);
            
               textfriend.setText("");
          textgroup.setText("");
          
           tabl.setItems(pd.displayAllusers(Integer.parseInt(textgroup.getText())));
        
    }
}

    @FXML
    private void show(ActionEvent event) {
        
     tabl.setItems(pd.displayAllusers(Integer.parseInt(textgroup.getText())));
          textgroup.setText("");
    }

    

}