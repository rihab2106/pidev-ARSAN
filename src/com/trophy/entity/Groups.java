/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.entity;

import java.util.Objects;
import javafx.scene.control.Button;


/**
 *
 * @author lenovo
 */
public class Groups {
    private int ID_GROUP ;
    private String NAME;
    private String IMG;
    private Button button;
    

    public Groups() {
        
    }

    public Groups(int ID_GROUP, String NAME, String IMG) {
        
        this.ID_GROUP = ID_GROUP;
        this.NAME = NAME;
        this.IMG = IMG;
      //  this.button=new Button("visit");
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public Button getButton() {
        return button;
    }

    public int getID_GROUP() {
        return ID_GROUP;
    }

    /**
     *
     * @return
     */
   

    public String getNAME() {
        return NAME;
    }

    public String getIMG() {
        return IMG;
    }

    public void setID_GROUP(int ID_GROUP) {
        this.ID_GROUP = ID_GROUP;
    }

    /**
     *
     * @param NAME
     */
    
    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public void setIMG(String IMG) {
        this.IMG = IMG;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.ID_GROUP;
        hash = 83 * hash + Objects.hashCode(this.NAME);
        hash = 83 * hash + Objects.hashCode(this.IMG);
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
        final Groups other = (Groups) obj;
        if (this.ID_GROUP != other.ID_GROUP) {
            return false;
        }
        if (!Objects.equals(this.NAME, other.NAME)) {
            return false;
        }
        if (!Objects.equals(this.IMG, other.IMG)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Groups{" + "ID_GROUP=" + ID_GROUP + ", NAME=" + NAME + ", IMG=" + IMG + '}';
    }

    public String getISACTIVE() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object getInstance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    
  

   
    
    
    
}
