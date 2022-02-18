/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.entity;

import java.util.Objects;

/**
 *
 * @author lenovo
 */
public class users_groups {
    
    private int ID_USERS_GPS;
    private int ID_USER;
    private int ID_GROUP ;
    private String JOINING_DATE;

    public users_groups() {
    }

    public users_groups(int ID_USERS_GPS, int ID_USER, int ID_GROUP, String JOINING_DATE) {
        this.ID_USERS_GPS = ID_USERS_GPS;
        this.ID_USER = ID_USER;
        this.ID_GROUP = ID_GROUP;
        this.JOINING_DATE = JOINING_DATE;
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

    public String getJOINING_DATE() {
        return JOINING_DATE;
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

    public void setJOINING_DATE(String JOINING_DATE) {
        this.JOINING_DATE = JOINING_DATE;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.ID_USERS_GPS;
        hash = 71 * hash + this.ID_USER;
        hash = 71 * hash + this.ID_GROUP;
        hash = 71 * hash + Objects.hashCode(this.JOINING_DATE);
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
        if (this.ID_GROUP != other.ID_GROUP) {
            return false;
        }
        if (!Objects.equals(this.JOINING_DATE, other.JOINING_DATE)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "users_groups{" + "ID_USERS_GPS=" + ID_USERS_GPS + ", ID_USER=" + ID_USER + ", ID_GROUP=" + ID_GROUP + ", JOINING_DATE=" + JOINING_DATE + '}';
    }
    
    
    
    
    
    
}
