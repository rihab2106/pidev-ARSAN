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
public class Order {
    private Integer OrderID;
    private String CardNumber,CardPassword,Name;

    public Order() {
    }

    public Order(Integer OrderID, String CardNumber, String CardPassword, String Name) {
        this.OrderID = OrderID;
        this.CardNumber = CardNumber;
        this.CardPassword = CardPassword;
        this.Name = Name;
    }

    public Integer getOrderID() {
        return OrderID;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public String getCardPassword() {
        return CardPassword;
    }

    public String getName() {
        return Name;
    }

    public void setOrderID(Integer OrderID) {
        this.OrderID = OrderID;
    }

    public void setCardNumber(String CardNumber) {
        this.CardNumber = CardNumber;
    }

    public void setCardPassword(String CardPassword) {
        this.CardPassword = CardPassword;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    @Override
    public String toString() {
        return "Order{" + "OrderID=" + OrderID + ", CardNumber=" + CardNumber + ", CardPassword=" + CardPassword + ", Name=" + Name + '}';
    }
    
    
    
    
    
}
