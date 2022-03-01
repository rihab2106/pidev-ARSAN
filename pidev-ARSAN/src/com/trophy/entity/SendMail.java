/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.entity;


import javax.mail.PasswordAuthentication;
import java.util.Properties;
import javax.mail.Session;

//import Entities.Document;
//import Services.ServicesDocument;
//import Tech.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javax.mail.Message;
import javax.mail.MessagingException;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 *
 * @author Syrine
 */
public class SendMail {

    
     public SendMail()
    {
        
    }
    private String username = "trophyhunterteamleader@gmail.com";
    private String password = "teamleadertrophy2022...";
    
    public void send(String destinataire)
    {
        Properties props = new Properties();
        //defines whether an authentication is needed for the email server
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port","587");
        Session session = Session.getInstance(props,
        new javax.mail.Authenticator() 
        {
            protected PasswordAuthentication getPasswordAuthentication() 
            {
                return new PasswordAuthentication(username, password);
            }
        });
    
        try 
        {
// Etape 2 : Création de l'objet Message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("adtrophyhun@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(destinataire));
            message.setSubject("A new team is added to a new competition");
            message.setText("There is a new team that is added to ta new competition ");
// Etape 3 : Envoyer le message
            Transport.send(message);
            System.out.println("Message sent");
        } 
        catch (MessagingException e) 
        {
            throw new RuntimeException(e);
        } 
    }
          
}
