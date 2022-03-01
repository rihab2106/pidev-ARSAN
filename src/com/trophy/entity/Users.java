/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.entity;
import java.util.Objects;


/**
 *
 * @author wlh nermine wlh
 */
public class Users {
private int ID_USER ;

private String FULL_NAME;
private String IMG;
private String EMAIL;
private String PASSWORD;
private int ISACTIVE;
private int PRIVILEGE_;

    public Users() {
       
    }

    public Users(int ID_USER,  String FULL_NAME, String IMG, String EMAIL, String PASSWORD) {
     
        this.ID_USER = ID_USER;
        this.FULL_NAME = FULL_NAME;
        this.IMG = IMG;
        this.EMAIL = EMAIL;
        this.PASSWORD = PASSWORD;
      
        
    }

    

    

    public int getID_USER() {
        return ID_USER;
    }

    

    public String getFULL_NAME() {
        return FULL_NAME;
    }

    public String getIMG() {
        return IMG;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public int getISACTIVE() {
        return ISACTIVE;
    }

    public int getPRIVILEGE_() {
        return PRIVILEGE_;
    }

    public void setID_USER(int ID_USER) {
        this.ID_USER = ID_USER;
    }

    
    public void setFULL_NAME(String FULL_NAME) {
        this.FULL_NAME = FULL_NAME;
    }

    public void setIMG(String IMG) {
        this.IMG = IMG;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public void setISACTIVE(int ISACTIVE) {
        this.ISACTIVE = ISACTIVE;
    }

    public void setPRIVILEGE_(int PRIVILEGE_) {
        this.PRIVILEGE_ = PRIVILEGE_;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.ID_USER);
        hash = 19 * hash + Objects.hashCode(this.FULL_NAME);
        hash = 19 * hash + Objects.hashCode(this.IMG);
        hash = 19 * hash + Objects.hashCode(this.EMAIL);
        hash = 19 * hash + Objects.hashCode(this.PASSWORD);
        hash = 19 * hash + Objects.hashCode(this.ISACTIVE);
        hash = 19 * hash + Objects.hashCode(this.PRIVILEGE_);
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
        final Users other = (Users) obj;
        if (!Objects.equals(this.ID_USER, other.ID_USER)) {
            return false;
        }
        
        if (!Objects.equals(this.FULL_NAME, other.FULL_NAME)) {
            return false;
        }
        if (!Objects.equals(this.IMG, other.IMG)) {
            return false;
        }
        if (!Objects.equals(this.EMAIL, other.EMAIL)) {
            return false;
        }
        if (!Objects.equals(this.PASSWORD, other.PASSWORD)) {
            return false;
        }
        if (!Objects.equals(this.ISACTIVE, other.ISACTIVE)) {
            return false;
        }
        if (!Objects.equals(this.PRIVILEGE_, other.PRIVILEGE_)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Users{" + "ID_USER=" + ID_USER +  ", FULL_NAME=" + FULL_NAME + ", IMG=" + IMG + ", EMAIL=" + EMAIL + ", PASSWORD=" + PASSWORD + ", ISACTIVE=" + ISACTIVE + ", PRIVILEGE_=" + PRIVILEGE_ + "\n"+'}';
    }

   
    
}
