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
 * 
 * classe per rappresentare un magazzino composto da un arrayList di pallet
 * 
 */
public class Magazzino implements Serializable{
    ArrayList<Pallet> palletList=new ArrayList<Pallet>();
    private int availableSpace;

    
    /**
     * 
     * @param availableSpace il numero di pallet contenibili nel magazzino
     */
    public Magazzino(int availableSpace) {
        this.availableSpace = availableSpace;
    }

    /**
     * 
     * @return il numero di pallet che si possono aggiungere
     */
    public int getAvailableSpace() {
        return availableSpace;
    }

    /**
     * cambia il numero massimo di pallet contenibili nel magazzino
     * @param availableSpace nuovo numero di pallet
     */
    public void setAvailableSpace(int availableSpace) {
        this.availableSpace = availableSpace;
    }
    
    /**
     * aggiunge un pallet alla fine dell'array
     * @param p pallet da aggiungere
     * @throws NoSpaceLeftException viene lanciata se non vi è più spazio nel magazzino
     */
    public void setPalletToEnd(Pallet p) throws NoSpaceLeftException{
        if(availableSpace<=0) throw new NoSpaceLeftException();
            
        palletList.add(p);
        availableSpace--;
    }
    
    /**
     * rimuove un pallet con un determinato id
     * @param id id del pallet da rimuovere
     * @throws EmptyListException la lista è vuota
     * @throws IndexOutOfBoundsException non viene mai lanciata
     */
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
    
    /**
     * rimuove il pallet presente ad un determinato indice
     * 
     * @param i indice del pallet nell'array
     * @return il pallet all'indice i
     * @throws EmptyListException il magazzino è vuoto
     * @throws IndexOutOfBoundsException l'indice non è valido
     */
    public Pallet getPallet(int i) throws EmptyListException, IndexOutOfBoundsException{
	if(palletList.size()==0) throw new EmptyListException();
	
	return palletList.get(i);
    }
    
    /**
     * ritorna il pallet con un determinato id
     * @param id id del pallet da trovare
     * @return il pallet trovato
     * @throws PalletNotFoundException il pallet cercato non esiste
     */
    public Pallet getPalletById(int id) throws PalletNotFoundException{
	for(int i=0;i<palletList.size();++i){
	    if(palletList.get(i).getId()==id){
		return palletList.get(i);
	    }
	}
	
	throw new PalletNotFoundException();
    }
    
    /**
     * ordina l'array per data di consegna dei pallet
     */
    public void sortByDate(){
	palletList.sort((o1,o2)->o1.getDelivery().compareTo(o2.getDelivery()));
    }
    
    /**
     * 
     * @return il numero di pallet presenti
     */
    public int getNPalletPresenti(){
	return palletList.size();
    }
    
    /**
     * 
     * @return stringa concatenando i toString dei pallet
     */
    @Override
    public String toString(){
	String s="";
	
	for(int i=0;i<palletList.size();++i){
	    s+="\n"+palletList.get(i).toString();
	}
	
	return s;
    }
    
    /**
     * salva l'array su file csv
     * @param filename il filepath su cui scrivere
     * @throws FileNotFoundException il file non esiste
     * @throws IOException impossibile scrivere sul file
     * @throws FileException il file è aperto in lettura
     */
    public void saveToCSV(String filename) throws FileNotFoundException, IOException, FileException{
	TextFile file=new TextFile(filename, 'W');
	
	for(int i=0;i<palletList.size();++i){
	    Pallet p1=palletList.get(i);
	    file.toFile(p1.getId()+";"+p1.getDelivery().toString()+";"+p1.getContent()+";"+p1.getQuantity()+";"+p1.getValue()+";"+p1.getWeight());
	}
	
	file.close();
	
    }
    
    /**
     * importa i dati da file CSV
     * @param filename il filepath da cui leggere
     * @throws IOException impossibile leggere da file
     * @throws FileException il file è aperto in scrittura
     */
    public void readFromCSV(String filename) throws IOException, FileException{
	TextFile file=new TextFile(filename, 'R');
	
	while(true){
	    String temp=file.fromFile();
	    String data[]=temp.split(";");
	    palletList.add(new Pallet(Integer.parseInt(data[0]),LocalDate.parse(data[1]),data[2],Integer.parseInt(data[3]),Float.parseFloat(data[4]),Float.parseFloat(data[5])));
	}
    }
}
