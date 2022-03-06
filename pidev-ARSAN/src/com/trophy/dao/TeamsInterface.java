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
public interface TeamsInterface <T> {
    public void insert(T t);
    public void delete(int id);
    public List<T> displayAll();
    public T displayById(int ID_TEAM );
    public void update(T t);
}
