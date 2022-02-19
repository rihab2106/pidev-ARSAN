/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.entity;

import java.time.LocalDate;

/**
 *
 * @author Syrine
 */
public class Competitions {
    private int Id_competion;
    private String game_name;
    private LocalDate dateofcomp;

    public Competitions() {
    }

    public Competitions(int Id_competion, String game_name) {
        this.Id_competion = Id_competion;
        this.game_name = game_name;
    }

    public int getId_competion() {
        return Id_competion;
    }

    public void setId_competion(int Id_competion) {
        this.Id_competion = Id_competion;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public LocalDate getDateofcomp() {
        return dateofcomp;
    }

    public void setDateofcomp(LocalDate dateofcomp) {
        this.dateofcomp = dateofcomp;
    }

    @Override
    public String toString() {
        return "Competitions{" + "Id_competion=" + Id_competion + ", game_name=" + game_name + ", dateofcomp=" + dateofcomp + '}';
    }
    
    
      
}
