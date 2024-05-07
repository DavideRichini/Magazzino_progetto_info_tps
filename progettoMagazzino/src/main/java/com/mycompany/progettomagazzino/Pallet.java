/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progettomagazzino;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author 39350
 */
public class Pallet implements Serializable {
    
    static private int nextId=1;
    private LocalDate delivery;
    private int id;
    private String content;
    private int quantity;
    private float value;
    private float weight;

    /**
     * 
     * @param delivery data consegna
     * @param content contenuto pallet
     * @param quantity quantità elementi sul pallet
     * @param value valore del contenuto in euro
     * @param weight peso del pallet in chili
     */
    public Pallet(LocalDate delivery, String content, int quantity, float value, float weight) {
        this.delivery = delivery;
        this.content = content;
        this.quantity = quantity;
        this.value = value;
        this.weight = weight;
        id=nextId;
        nextId++;
    }
    
    /**
     * 
     * @param delivery data consegna
     * @param content contenuto pallet
     * @param quantity quantità elementi sul pallet
     * @param value valore del contenuto in euro
     * @param weight peso del pallet in chili
     * @param id id da assegnare al pallet
     */
    public Pallet(int id,LocalDate delivery, String content, int quantity, float value, float weight) {
        this.delivery = delivery;
        this.content = content;
        this.quantity = quantity;
        this.value = value;
        this.weight = weight;
        this.id=id;
        if(nextId<=id) nextId=id+1;
    }

    /**
     * 
     * @return data condegna in LocalDate
     */
    public LocalDate getDelivery() {
        return delivery;
    }

    /**
     * 
     * @param delivery data condegna in formato ISO
     */
    public void setDelivery(LocalDate delivery) {
        this.delivery = delivery;
    }

    /**
     * 
     * @param content contenuto da assegnare
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 
     * @param quantity quantità da assegnare, se negativa diventa 0
     */
    public void setQuantity(int quantity) {
        if(quantity<0) quantity=0;
        this.quantity = quantity;
    }

    /**
     * 
     * @param value valore da asegnare, se negativo diventa 0
     */
    public void setValue(float value) {
        if(value<0) value=0;
        this.value = value;
    }

    /**
     * 
     * @param weight peso
     */
    public void setWeight(float weight) {
        if(weight<0) weight=0;
        this.weight = weight;
    }

    /**
     * 
     * @return id del pallet
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @return contenuto del pallet come stringa
     */
    public String getContent() {
        return content;
    }

    /**
     * 
     * @return quantita elementi sul pallet
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * 
     * @return valore pallet
     */
    public float getValue() {
        return value;
    }

    /**
     * 
     * @return peso pallet
     */
    public float getWeight() {
        return weight;
    }
    
    /**
     * 
     * @return stringa contenente dati pallet
     */
    @Override
    public String toString(){
	String s="";
	
	s="id: "+id+"\ndata consegna: "+delivery.toString()+"\ncontenuto: "+content;
	s+="\nquantita: "+quantity+"\nvalore: "+value+"€\npeso: "+weight;
	
	return s;
    }
    
    public boolean equals(Object obj){
        Pallet p1=(Pallet) obj;
        
        return p1.getContent().equals(this.getContent())&&p1.getDelivery().equals(this.getDelivery())&&p1.getId()==id&&p1.getQuantity()==quantity&&p1.getValue()==value&&p1.getWeight()==weight;
    }
    
}
