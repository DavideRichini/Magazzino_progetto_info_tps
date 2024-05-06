/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.progettomagazzino;

import exceptions.EmptyListException;
import exceptions.FileException;
import exceptions.NoSpaceLeftException;
import exceptions.PalletNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.*;

/**
 *
 * @author 39350
 */
public class ProgettoMagazzino {

    public static void main(String[] args) {
	
	int spazio=20;
	int chosenId=0;
	
	Magazzino w1=new Magazzino(spazio);
	ConsoleInput input=new ConsoleInput();
	
	int nOptions=10;
	String options[]=new String[nOptions];
	int chosen=1;
	
	options[0]="0: Esci";
	options[1]="1: Mostra tutti";
	options[2]="2: Mostra pallet con id";
	options[3]="3: Aggiungi pallet";
	options[4]="4: Rimuovi pallet per id";
	options[5]="5: Ordina per data di consegna";
	options[6]="6: Mostra numero pallet presenti";
	options[7]="7: Mostra spazio disponibile";
	options[8]="8: Salva su CSV";
	options[9]="9: Leggi da CSV";
	
	
        Menu m1=new Menu(options);
	
	while(chosen>0){
	    chosen=m1.sceltaMenu();
	    
	    switch(chosen){
		case 0:
		    break;
		case 1:
		    System.out.println(w1.toString());
		    break;
		case 2:
		    try {
			chosenId=input.readInt();
			Pallet p1= w1.getPalletById(chosenId);
			System.out.println(p1.toString());
		    } catch (IOException ex) {
			System.out.println("Impossibile leggere da tastiera");
		    } catch (NumberFormatException ex) {
			System.out.println("Formato non valido");
		    } catch (PalletNotFoundException ex) {
			System.out.println("Non esiste un pallet con id "+chosenId);
		    }
		    break;
		case 3:
		    
		try {
		    System.out.println("Inserire data consegna nel formato AAAA-MM-DD");
		    String date=input.readString();
		    LocalDate d1=LocalDate.parse(date);
		    System.out.println("Inserire contenuto pallet");
		    String content=input.readString();
		    System.out.println("Inserire quantita elementi sul pallet");
		    int quantity=input.readInt();
		    System.out.println("Inserire valore pallet");
		    int valore=input.readInt();
		    System.out.println("Inserire peso pallet");
		    int weight=input.readInt();
		    w1.setPalletToEnd(new Pallet(d1,content,quantity,valore,weight));
		    } catch (IOException ex) {
			System.out.println("Impossibile leggere da tastiera");
		    } catch (NumberFormatException ex) {
			System.out.println("Formato non valido");
		    } catch (DateTimeParseException ex){
			System.out.println("Formato data non valido");
		    } catch (NoSpaceLeftException ex) {
			System.out.println("Il magazzino e pieno");
		    }
		    break;
		
		case 4:
		    try {
			chosenId=input.readInt();
			w1.removePalletById(chosenId);
		    } catch (IOException ex) {
			System.out.println("Impossibile leggere da tastiera");
		    } catch (NumberFormatException ex) {
			System.out.println("Formato non valido");
		    } catch (EmptyListException ex) {
			System.out.println("Il magazzino e vuoto");
		    } catch (IndexOutOfBoundsException ex) {
			
		    }
		    break;
		    
		case 5:
		    w1.sortByDate();
		    System.out.println(w1.toString());
		    break;
		case 6:
		    System.out.println(w1.getNPalletPresenti());
		    break;
		case 7:
		    System.out.println(w1.getAvailableSpace());
		    break;
		case 8:
		    try {
			w1.saveToCSV("data.csv");
		    } catch (IOException ex) {
			System.out.println("impossibile accedere al file");
		    } catch (FileException ex) {
			System.out.println(ex.getCause());
		    }
		    break;
		case 9:
		    try {
			w1.readFromCSV("data.csv");
		    } catch (IOException ex) {
			System.out.println("impossibile leggere da file");
		    } catch (FileException ex) {
			System.out.println(ex.getCause());
		    }
		    break;

		default:
		    chosen=0;

	    }
	}
    }
}
