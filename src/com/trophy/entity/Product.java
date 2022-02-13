/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.entity;

/**
 *
 * @author rihab bns
 */

public class Product {
    private Integer ID_Product ;
    private Float Price ;
    private String PROD_Name ;
    private Integer Discount ;

    public Product() {
    }

    public Product(Integer ID_Product, Float Price, String PROD_Name, Integer Discount) {
        this.ID_Product = ID_Product;
        this.Price = Price;
        this.PROD_Name = PROD_Name;
        this.Discount = Discount;
    }

    public Integer getID_Product() {
        return ID_Product;
    }

    public Float getPrice() {
        return Price;
    }

    public String getPROD_Name() {
        return PROD_Name;
    }

    public Integer getDiscount() {
        return Discount;
    }

    public void setID_Product(Integer ID_Product) {
        this.ID_Product = ID_Product;
    }

    public void setPrice(Float Price) {
        this.Price = Price;
    }

    public void setPROD_Name(String PROD_Name) {
        this.PROD_Name = PROD_Name;
    }

    public void setDiscount(Integer Discount) {
        this.Discount = Discount;
    }

    @Override
    public String toString() {
        return "Product{" + "ID_Product=" + ID_Product + ", Price=" + Price + ", PROD_Name=" + PROD_Name + ", Discount=" + Discount + '}';
    }
   
   
    
    
}
