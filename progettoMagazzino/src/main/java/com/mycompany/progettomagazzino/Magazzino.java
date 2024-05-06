/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progettomagazzino;

import java.util.ArrayList;
import java.io.Serializable;
import util.TextFile;

import exceptions.NoSpaceLeftException;
import exceptions.EmptyListException;
import exceptions.PalletNotFoundException;
import java.io.IOException;
import exceptions.FileException;
import java.io.FileNotFoundException;

import java.time.LocalDate;

/**
 *
 * @author 39350
 */
public class Magazzino implements Serializable{
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
    
    public void saveToCSV(String filename) throws FileNotFoundException, IOException, FileException{
	TextFile file=new TextFile(filename, 'W');
	
	for(int i=0;i<palletList.size();++i){
	    Pallet p1=palletList.get(i);
	    file.toFile(p1.getId()+";"+p1.getDelivery().toString()+";"+p1.getContent()+";"+p1.getQuantity()+";"+p1.getValue()+";"+p1.getWeight());
	}
	
	file.close();
	
    }
    
    public void readFromCSV(String filename) throws IOException, FileException{
	TextFile file=new TextFile(filename, 'R');
	
	while(true){
	    String temp=file.fromFile();
	    String data[]=temp.split(";");
	    palletList.add(new Pallet(Integer.parseInt(data[0]),LocalDate.parse(data[1]),data[2],Integer.parseInt(data[3]),Float.parseFloat(data[4]),Float.parseFloat(data[5])));
	}
    }
}
