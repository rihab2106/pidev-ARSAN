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
public class users_groups {
    
    private int ID_USERS_GPS;
    private int ID_USER;
    private int ID_GROUP ;
    private String status;
    private String FULL_NAME;
    private String mail;
     

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setFULL_NAME(String FULL_NAME) {
        this.FULL_NAME = FULL_NAME;
    }

    public String getFULL_NAME() {
        return FULL_NAME;
    }

    public users_groups() {
    }

    public users_groups( int ID_USER, int ID_GROUP) {
       
        this.ID_USER = ID_USER;
        this.ID_GROUP = ID_GROUP;
        this.status="friend";    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    

   

   

    public int getID_USERS_GPS() {
        return ID_USERS_GPS;
    }

    public int getID_USER() {
        return ID_USER;
    }

    public int getID_GROUP() {
        return ID_GROUP;
    }

   
    public void setID_USERS_GPS(int ID_USERS_GPS) {
        this.ID_USERS_GPS = ID_USERS_GPS;
    }

    public void setID_USER(int ID_USER) {
        this.ID_USER = ID_USER;
    }

    public void setID_GROUP(int ID_GROUP) {
        this.ID_GROUP = ID_GROUP;
    }

   

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.ID_USERS_GPS;
        hash = 71 * hash + this.ID_USER;
        hash = 71 * hash + this.ID_GROUP;
      
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final users_groups other = (users_groups) obj;
        if (this.ID_USERS_GPS != other.ID_USERS_GPS) {
            return false;
        }
        if (this.ID_USER != other.ID_USER) {
            return false;
        }
        return this.ID_GROUP == other.ID_GROUP;
    }

    @Override
    public String toString() {
        return "users_groups{" + "ID_USERS_GPS=" + ID_USERS_GPS + ", ID_USER=" + ID_USER + ", ID_GROUP=" + ID_GROUP + ", status=" + status + '}';
    }

   
   
    }
    
    
    
    
    
    

