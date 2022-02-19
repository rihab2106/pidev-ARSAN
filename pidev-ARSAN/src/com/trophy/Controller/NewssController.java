/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.trophy.Controller;

import com.trophy.dao.CommentDao;
import com.trophy.dao.NewsDao;
import com.trophy.entity.Comment;
import com.trophy.entity.News;
import com.trophy.entity.Product;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Mouelhi
 */
public class NewssController implements Initializable {

    @FXML
    private TextField txtcontent;
    @FXML
    private TextField txtheadline;
    @FXML
    private TableView commentview;
    private TableColumn CONTENTCOL;
    private TableColumn comlike;
    private TableColumn comdislike;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView tablev;
    @FXML
    private TextField txtheadline1;
    @FXML
    private TextField txtheadline11;
    @FXML
    private TextField txtheadline111;
   int idn;
   int idc;
    @FXML
    private TableColumn ID;
    @FXML
    private TableColumn Content;
    @FXML
    private TableColumn Headline;
    @FXML
    private TableColumn imgurl;
    @FXML
    private Button btnaddcom;
    @FXML
    private Button btndelcom;
    @FXML
    private Button btnupdatecom;
    @FXML
    private TextField AddComment;
    @FXML
    private TableColumn<?, ?> CONTENT;
    @FXML
    private TableColumn<?, ?> LIKES;
    @FXML
    private TableColumn<?, ?> DISLIKES;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tablev.setItems(nd.getAllNews());
        ID.setCellValueFactory(new PropertyValueFactory<>("newsid"));
        Headline.setCellValueFactory(new PropertyValueFactory<>("Headline"));
        Content.setCellValueFactory(new PropertyValueFactory<>("Content"));
        imgurl.setCellValueFactory(new PropertyValueFactory<>("imgurl"));
        System.out.println(nd.getAllNews());
        
        

    }    

    NewsDao nd=new NewsDao();
    CommentDao cd=new CommentDao();
    @FXML
    private void Add(ActionEvent event) {
        News n=new News();
       if(!txtheadline1.getText().equals("")&&!txtheadline111.getText().equals("")&&!txtheadline11.getText().equals("")){
        n.setNewsid(idn);
        n.setContent(txtheadline111.getText());
        n.setHeadline(txtheadline1.getText());
        n.setImgurl(txtheadline11.getText());
        }
      nd.insert(n); 
        System.out.println(n.toString());
   
          txtheadline11.setText("");
          txtheadline111.setText("");
          txtheadline1.setText("");
          tablev.setItems(nd.getAllNews());
          
          System.out.println(nd.getAllNews());
    }

  
    
           
    @FXML
    private void Update(ActionEvent event) {
          News n=new News();        
          if(!txtheadline1.getText().equals("")&&!txtheadline111.getText().equals("")&&!txtheadline11.getText().equals("")){
          n.setHeadline(txtheadline1.getText());
          n.setContent(txtheadline111.getText());
          n.setImgurl(txtheadline11.getText());
          n.setNewsid(idn);
          
        nd.update(n);
          
          txtheadline1.setText("");
          txtheadline111.setText("");
          txtheadline11.setText("");
            
          tablev.setItems(nd.getAllNews());
          }     
        
        
    }

    @FXML
    private void Delete(ActionEvent event) {
        News n=new News();        
          if(!txtheadline1.getText().equals("")&&!txtheadline111.getText().equals("")&&!txtheadline11.getText().equals("")){
          n.setHeadline(txtheadline1.getText());
          n.setContent(txtheadline111.getText());
          n.setImgurl(txtheadline11.getText());
          n.setNewsid(idn);
          
        nd.delete(n);
          
          txtheadline1.setText("");
          txtheadline111.setText("");
          txtheadline11.setText("");
            
          tablev.setItems(nd.getAllNews());
          }
        
    }

    @FXML
    private void ClickTable(MouseEvent event) {
        News n =(News) tablev.getSelectionModel().getSelectedItem();
           txtheadline1.setText(n.getHeadline());
           txtheadline11.setText(n.getImgurl()+"");
           txtheadline111.setText(n.getContent()+"");
           txtheadline.setText(n.getHeadline());
           txtcontent.setText(n.getContent());
           btnaddcom.setVisible(true);
           btndelcom.setVisible(true);
           btnupdatecom.setVisible(true);
           AddComment.setVisible(true);
           AddComment.setText("YOUR COMMENT");

           idn=n.getNewsid();  
           commentview.setItems(cd.getAllComments(n));
           System.out.println(cd.getAllComments(n));
        CONTENT.setCellValueFactory(new PropertyValueFactory<>("comcontent"));
        LIKES.setCellValueFactory(new PropertyValueFactory<>("likes"));
        DISLIKES.setCellValueFactory(new PropertyValueFactory<>("dislikes"));
     }

    @FXML
    private void AddCom(ActionEvent event) {
       Comment cm=new Comment();
       News n =(News) tablev.getSelectionModel().getSelectedItem();
       if(!AddComment.getText().equals("")){
        cm.setIdcomm(idc);
        cm.setComcontent(AddComment.getText());
        }
      cd.insert(cm,n); 
        AddComment.setText("");
          commentview.setItems(cd.getAllComments(n));
          
    }   
    

    @FXML
    private void DeleteCom(ActionEvent event) {
        News n =(News) tablev.getSelectionModel().getSelectedItem();
        Comment c =(Comment) commentview.getSelectionModel().getSelectedItem();

        Comment C=new Comment();        
          if(!AddComment.getText().equals("")){
          c.setComcontent(AddComment.getText());
          c.setIdcomm(c.getIdcomm());
          
        cd.delete(c,n);
          
          AddComment.setText("");
          commentview.setItems(cd.getAllComments(n));
          }
    }

    @FXML
    private void UpdateCom(ActionEvent event) {
            System.out.println("fuck off");

    }
    
    
    

    
}
