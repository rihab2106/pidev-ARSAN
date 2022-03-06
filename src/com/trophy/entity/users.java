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
public class users {

   
    private int ID_USER;
    private String FULL_NAME;
      private String EMAIL;
      private String PASSWORD;
       private int ISACTIVE;
          private int PRIVILEGE_;   

          
           public users() {
    }
          
          
          
    public users(int ID_USER, String FULL_NAME, String EMAIL, String PASSWORD) {
        this.ID_USER = ID_USER;
        this.FULL_NAME = FULL_NAME;
        this.EMAIL = EMAIL;
        this.PASSWORD = PASSWORD;
        
    }

    public int getID_USER() {
        return ID_USER;
    }

    public String getFULL_NAME() {
        return FULL_NAME;
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

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public void setISACTIVE(int ISACTIVE) {
        this.ISACTIVE = ISACTIVE;
    }

    public void setPRIVILEGE_(int intPRIVILEGE_) {
        this.PRIVILEGE_ = intPRIVILEGE_;
    }

    @Override
    public String toString() {
        return "users{" + "ID_USER=" + ID_USER + ", FULL_NAME=" + FULL_NAME + ", EMAIL=" + EMAIL + ", PASSWORD=" + PASSWORD + ", ISACTIVE=" + ISACTIVE + ", intPRIVILEGE_=" + PRIVILEGE_ + '}';
    }
          
}
