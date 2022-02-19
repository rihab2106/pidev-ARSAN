/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.dao;

import java.util.List;

/**
 *
 * @author Syrine
 */
public interface CompetitionsInterface <C> {
    public void insert(C c);
    public void delete(int id);
    public List<C> displayAll();
    public C displayById(int ID_COMPETION);
    
    public void update(C c);
}

