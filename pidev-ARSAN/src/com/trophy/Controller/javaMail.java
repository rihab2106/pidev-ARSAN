/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.Controller;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author lenovo
 */
public class javaMail {
    
    public static void sendMail(String recepient) throws Exception{
        System.out.println("preparing to sent email");
         Properties props;
        props = new Properties();
        
props.put("mail.smtp.host","smtp.gmail.com");

props.put("mail.smtp.starttls.enable","true");
  //props.put("mail.smtp.socketFactory.port","465") ;     
        
    //props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");   
        
      props.put("mail.smtp.auth","true");
      props.put("mail.smtp.port","587");
      String myAccountEmail="benamaranermine99@gmail.com";
      String password="211JFT1347";
      Session session;
        session = Session.getInstance(props,new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){ 
                return new PasswordAuthentication( myAccountEmail,password);}
             
        });
      Message message=prepareMessage(session,myAccountEmail,recepient);
   Transport.send(message);
        System.out.println("message sent successfully");
    
 }
    private static Message prepareMessage(Session session, String myAccountEmail,String recepient) {
       
        try {
            Message message =new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("TROPHY HUNTER");
            message.setText(" Sorry you are blocked.");
            return message;
        } catch (MessagingException ex) {
            System.out.println(ex);
        }
       return null;
    }
    
}
