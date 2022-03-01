/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.dao;
import java.util.List;
/**
 *
 * @author lenovo
 * @param <T>
 * @param <V>
 */
public interface userinterface <T,V> {
    public void insert(T o);
    //public void acceptuser(T o,V u);//
    public void delete(T o);
    public List<T> displayAll();
    public T displayById(int id);
    public T displayacount();
    public boolean update(T os);
    
    
  }
