/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.dao;

import java.util.List;

/**
 *
 * @author rihab bns
 */
public interface ProductInterface<T> {
    public void insert(T p);
    public void delete(int id);
    public List<T> displayAll();
    public T displayById(int ID_Product);
    
    public void update(T p);
}
