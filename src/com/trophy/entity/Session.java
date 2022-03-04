/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.entity;

/**
 *
 * @author lenovo
 */
public class Session {
    
     /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ASUS
 */
 private static Session instance;
     private users SessionUser;

   
     private Session() {
     }
      private Session(users SessionUser) {
          this.SessionUser=SessionUser;
     }
     
     public static Session StartSession() {
        if(instance == null) {
            instance = new Session();
        }
        return instance;
    }
     
     public static Session StartSession(users SessionUser) {
        if(instance == null) {
            instance = new Session(SessionUser);
        }
        return instance;
    } 
     public static Session getSession() {
        return instance;
    }
      public  void clearSession() {
      SessionUser=null;

      }
      public void setSessionUser(users SessionUser){
      this.SessionUser=SessionUser;
      }
      public users getSessionUser(){
      return this.SessionUser;
      }
    
}
