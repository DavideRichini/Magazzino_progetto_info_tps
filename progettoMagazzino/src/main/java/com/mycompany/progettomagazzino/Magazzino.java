/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progettomagazzino;

import java.util.ArrayList;

import exceptions.NoSpaceLeftException;
import exceptions.EmptyListException;
import exceptions.PalletNotFoundException;

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
    
    public void removePalletById(int id) throws EmptyListException, IndexOutOfBoundsException{
        if(palletList.size()==0) throw new EmptyListException();
        
        for(int i=0;i<palletList.size();++i){
	    if(palletList.get(i).getId()==id){
		palletList.remove(i);
		availableSpace++;
		return;
	    }
	}
    }
    
    public Pallet getPallet(int i) throws EmptyListException, IndexOutOfBoundsException{
	if(palletList.size()==0) throw new EmptyListException();
	
	return palletList.get(i);
    }
    
    public Pallet getPalletById(int id) throws PalletNotFoundException{
	for(int i=0;i<palletList.size();++i){
	    if(palletList.get(i).getId()==id){
		return palletList.get(i);
	    }
	}
	
	throw new PalletNotFoundException();
    }
    
    public void sortByDate(){
	palletList.sort((o1,o2)->o1.getDelivery().compareTo(o2.getDelivery()));
    }
    
    public int getNPalletPresenti(){
	return palletList.size();
    }
    
    @Override
    public String toString(){
	String s="";
	
	for(int i=0;i<palletList.size();++i){
	    s+="\n"+palletList.get(i).toString();
	}
	
	return s;
    }
}
