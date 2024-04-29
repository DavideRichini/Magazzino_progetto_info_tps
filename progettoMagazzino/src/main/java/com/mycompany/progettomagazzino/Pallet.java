/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progettomagazzino;

import java.time.LocalDate;

/**
 *
 * @author 39350
 */
public class Pallet {
    
    static int nextId=1;
    LocalDate delivery;
    int id;
    String content;
    int quantity;
    float value;
    float weight;

    public Pallet(LocalDate delivery, String content, int quantity, float value, float weight) {
        this.delivery = delivery;
        this.content = content;
        this.quantity = quantity;
        this.value = value;
        this.weight = weight;
        id=nextId;
        nextId++;
    }

    public LocalDate getDelivery() {
        return delivery;
    }

    public void setDelivery(LocalDate delivery) {
        this.delivery = delivery;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getValue() {
        return value;
    }

    public float getWeight() {
        return weight;
    }
    
}