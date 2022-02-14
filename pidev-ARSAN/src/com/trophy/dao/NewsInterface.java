/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.trophy.dao;

import java.util.List;

/**
 *
 * @author Mouelhi
 */
public interface NewsInterface<T> {
    public void insert(T p);
    public void delete(T p);
    public List<T> displayAll();
    public T displayById(int ID_Product);
    public boolean update(T p);
    
}
