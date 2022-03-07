/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.entity;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
/**
 *
 * @author rihab bns
 */
public class Notificationtwi {
    public static final String ACCOUNT_SID ="AC97b9b1afb3b68605a0dbec9ce9567174";
    public static final String AUTH_TOKEN ="bedb866cd96c88f020e87879b89a749a";
    
    public static void notif (String s){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+21628564711"),
                "MGe9c9b8c623ffc333c419a522028b894f",
                "A product  " +s+" exprises , Please check The shop and remove it ")
        .create();
        System.out.println(message.getSid());
    }
    
    
}
