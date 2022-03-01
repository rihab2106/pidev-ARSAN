/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.dao;

import com.trophy.entity.Users;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lenovo
 * @param <T>
 */
public interface groupinterface <T>{
    
    public void insert(T o);
    public void delete(T o);
    public List<T> displayAll();
    public T displayById(int id);
    
    
    public boolean update(T o);
    
}
