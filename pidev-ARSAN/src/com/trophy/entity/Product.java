/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.entity;

import java.util.Objects;

/**
 *
 * @author rihab bns
 */

public class Product {
    private Integer ID_Product ;
    private Float Price,Discount ;
    private String PROD_Name,Category ;
    private Integer Quantity ;
    
    public Product() {
    }

    public Product(Float Price, String PROD_Name, String Category, Float Discount,String Description ) {
        this.Price = Price;
        this.PROD_Name = PROD_Name;
        this.Category = Category;
        this.Discount = Discount;
        
    }
    

    public Product(Integer ID_Product, Float Price, String PROD_Name, String Category, Float Discount,Integer Quantity) {
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

    public Float getDiscount() {
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

    public void setDiscount(Float Discount) {
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

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Product other = (Product) obj;
        if (!Objects.equals(this.PROD_Name, other.PROD_Name)) {
            return false;
        }
        if (!Objects.equals(this.Category, other.Category)) {
            return false;
        }
        if (!Objects.equals(this.ID_Product, other.ID_Product)) {
            return false;
        }
        if (!Objects.equals(this.Price, other.Price)) {
            return false;
        }
        if (!Objects.equals(this.Discount, other.Discount)) {
            return false;
        }
        if (!Objects.equals(this.Quantity, other.Quantity)) {
            return false;
        }
        return true;
    }
    

    

   

    
   
    
    
}
