/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.progettomagazzino;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author 39350
 */
public class PalletTest {
    
    private Pallet pallet;
    
    public PalletTest() {
        
    }
    
    @BeforeEach
    public void setUp(){
        pallet=new Pallet(1,null, null, 0, 0, 0);
    }

    /**
     * Test of getDelivery method, of class Pallet.
     */
    @org.junit.jupiter.api.Test
    public void testGetSetDelivery() {
        LocalDate date=LocalDate.parse("2024-06-12");
        pallet.setDelivery(date);
        assertEquals(date, pallet.getDelivery());
    }

    /**
     * Test of setContent method, of class Pallet.
     */
    @org.junit.jupiter.api.Test
    public void testSetGetContent() {
        String data="ferro";
        pallet.setContent(data);
        assertEquals(data, pallet.getContent());
    }

    /**
     * Test of setQuantity method, of class Pallet.
     */
    @org.junit.jupiter.api.Test
    public void testSetGetQuantityPositiva() {
        int data=10;
        pallet.setQuantity(data);
        assertEquals(data, pallet.getQuantity());
    }

    @org.junit.jupiter.api.Test
    public void testSetGetQuantityNegativa(){
        int data=-10;
        pallet.setQuantity(data);
        assertEquals(0, pallet.getQuantity());
    }
    
    @org.junit.jupiter.api.Test
    public void testSetGetQuantityZero(){
        int data=0;
        pallet.setQuantity(data);
        assertEquals(data, pallet.getQuantity());
    }
    
    
    /**
     * Test of setValue method, of class Pallet.
     */
    @org.junit.jupiter.api.Test
    public void testSetGetValuePositivo() {
        float data=50;
        pallet.setValue(data);
        assertEquals(data, pallet.getValue());
    }
    
    @org.junit.jupiter.api.Test
    public void testSetGetValueZero() {
        float data=0;
        pallet.setValue(data);
        assertEquals(data, pallet.getValue());
    }
    
    @org.junit.jupiter.api.Test
    public void testSetGetValueNegativo() {
        float data=-10;
        pallet.setValue(data);
        assertEquals(0, pallet.getValue());
    }

    /**
     * Test of setWeight method, of class Pallet.
     */
    @org.junit.jupiter.api.Test
    public void testSetWeightPositivo() {
        float data=50;
        pallet.setWeight(data);
        assertEquals(data, pallet.getWeight());
    }
    
    @org.junit.jupiter.api.Test
    public void testSetWeightZero() {
        float data=0;
        pallet.setWeight(data);
        assertEquals(data, pallet.getWeight());
    }
    
    @org.junit.jupiter.api.Test
    public void testSetWeightNegativo() {
        float data=-50;
        pallet.setWeight(data);
        assertEquals(0, pallet.getWeight());
    }

    /**
     * Test of getId method, of class Pallet.
     */
    @org.junit.jupiter.api.Test
    public void testGetId() {
        assertEquals(1, pallet.getId());
    }
    /**
     * Test of toString method, of class Pallet.
     */
    @org.junit.jupiter.api.Test
    public void testToString() {
        LocalDate date=LocalDate.parse("2024-01-01");
        String aspettato="id: 1\ndata consegna: 2024-01-01\ncontenuto: ferro\nquantita: 10\nvalore: 50.0€\npeso: 100.0";
        pallet=new Pallet(1,date,"ferro",10,50,100);
        assertEquals(aspettato, pallet.toString());
    }
    
}
