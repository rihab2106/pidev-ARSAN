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
    private String PROD_Name,Category ;
    private Integer Discount,Quantity ;
    
    public Product() {
    }

    public Product(Float Price, String PROD_Name, String Category, Integer Discount) {
        this.Price = Price;
        this.PROD_Name = PROD_Name;
        this.Category = Category;
        this.Discount = Discount;
    }
    

    public Product(Integer ID_Product, Float Price, String PROD_Name, String Category, Integer Discount,Integer Quantity) {
        this.ID_Product = ID_Product;
        this.Price = Price;
        this.PROD_Name = PROD_Name;
        this.Category = Category;
        this.Discount = Discount;
        this.Quantity=Quantity;
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

    public String getCategory() {
        return Category;
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

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public void setDiscount(Integer Discount) {
        this.Discount = Discount;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer Quantity) {
        this.Quantity = Quantity;
    }
    

    @Override
    public String toString() {
        return "Product{" + "ID_Product=" + ID_Product + ", Price=" + Price + ", PROD_Name=" + PROD_Name + ", Category=" + Category + ", Discount=" + Discount + ", Quantity=" + Quantity + '}';
    }

   

    
   
    
    
}
