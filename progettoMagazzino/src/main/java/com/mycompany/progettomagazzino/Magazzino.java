/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progettomagazzino;

import java.util.ArrayList;

import exceptions.NoSpaceLeftException;
import exceptions.EmptyListException;

/**
 *
 * @author 39350
 */
public class Magazzino {
    ArrayList<Pallet> palletList=new ArrayList<Pallet>();
    private int availableSpace;

    public Magazzino(int availableSpace) {
        this.availableSpace = availableSpace;
    }

    public int getAvailableSpace() {
        return availableSpace;
    }

    public void setAvailableSpace(int availableSpace) {
        this.availableSpace = availableSpace;
    }
    
    public void setPalletToEnd(Pallet p) throws NoSpaceLeftException{
        if(availableSpace<=0) throw new NoSpaceLeftException();
            
        palletList.add(p);
        availableSpace--;
    }
    
    public void removePalletAtIndex(int i) throws EmptyListException, IndexOutOfBoundsException{
        if(palletList.size()==0) throw new EmptyListException();
        
        palletList.remove(i);
    }
    
}
