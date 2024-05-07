/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.progettomagazzino;

import exceptions.EmptyListException;
import exceptions.FileException;
import exceptions.NoSpaceLeftException;
import exceptions.PalletNotFoundException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.format.DateTimeParseException;

import java.time.LocalDate;
import util.*;

/**
 *
 * @author 39350
 */
public class ProgettoMagazzino {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
	
	int spazio=20;
	int chosenId=0;
        String options[];
	int chosen=1;
        String attore="";
	String opzioniPallet[]=new String[5];
	
	opzioniPallet[0]="0: data consegna";
	opzioniPallet[1]="1: contenuto";
	opzioniPallet[2]="2: quantità";
	opzioniPallet[3]="3: valore";
	opzioniPallet[4]="4: peso";
        
	Magazzino w1=new Magazzino(spazio);
	ConsoleInput input=new ConsoleInput();
	
	
        try {
            System.out.println("Inserire tipo di utente (admin o user)");
            attore=input.readString();
        } catch (IOException ex) {
            
        } catch (NumberFormatException ex) {
            
        }
	
        if(attore.equals("admin")){
            int nOptions=13;
            options=new String[nOptions];
            options[0]="0: Esci";
            options[1]="1: Mostra tutti";
            options[2]="2: Mostra pallet con id";
            options[3]="3: Ordina per data di consegna";
            options[4]="4: Mostra numero pallet presenti";
            options[5]="5: Mostra spazio disponibile";
            options[6]="6: Salva su CSV";
            options[7]="7: Leggi da CSV";
            options[8]="8: Leggi da file binario";
            options[9]="9: Salva su file binario";
	    options[10]="10: modifica pallet per id";
            options[11]="11: Rimuovi pallet per id";
            options[12]="12: Aggiungi pallet";
        } else{
            int nOptions=10;
            options=new String[nOptions];
            options[0]="0: Esci";
            options[1]="1: Mostra tutti";
            options[2]="2: Mostra pallet con id";
            options[3]="3: Ordina per data di consegna";
            options[4]="4: Mostra numero pallet presenti";
            options[5]="5: Mostra spazio disponibile";
            options[6]="6: Salva su CSV";
            options[7]="7: Leggi da CSV";
            options[8]="8: Leggi da file binario";
            options[9]="9: Salva file binario";
        }
        
        
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
		case 3:
		    w1.sortByDate();
		    System.out.println(w1.toString());
		    break;
		
		case 4:
		    System.out.println(w1.getNPalletPresenti());
		    break;
		    
		case 5:
		    System.out.println(w1.getAvailableSpace());
		    break;
		case 6:
                    try {
			w1.saveToCSV("data.csv");
		    } catch (IOException ex) {
			System.out.println("impossibile accedere al file");
		    } catch (FileException ex) {
			System.out.println(ex.getCause());
		    }
		    break;
		case 7:
                    try {
			w1.readFromCSV("data.csv");
		    } catch (IOException ex) {
			System.out.println("impossibile leggere da file");
		    } catch (FileException ex) {
			System.out.println(ex.getCause());
		    }
		    break;
		case 8:
                    try {
			FileInputStream file=new FileInputStream("magazzino.data");
			ObjectInputStream reader=new ObjectInputStream(file);
			w1=(Magazzino)reader.readObject();
		    } catch (FileNotFoundException ex) {
			System.out.println("il file non esiste");
		    } catch (IOException ex) {
			System.out.println("impossibile leggere dal file");
		    } catch (ClassNotFoundException ex) {
			
		    }catch(ClassCastException ex){
			System.out.println("File non valido");
		    }
		    break;
		case 9:
                    try {
			FileOutputStream file=new FileOutputStream("magazzino.data");
			ObjectOutputStream writer=new ObjectOutputStream(file);
			writer.writeObject(w1);
		    } catch (FileNotFoundException ex) {
			System.out.println("il file non esiste");
		    } catch (IOException ex) {
			System.out.println("impossibile scrivere sul file");
		
		    }
		    break;
		case 10:
		{
		    try {
			chosenId=input.readInt();
			Pallet tmp=w1.getPalletById(chosenId);
			Menu m2=new Menu(opzioniPallet);
			int scelta=m2.sceltaMenu();
			switch(scelta){
			    case 0:
				System.out.println("Inserire data consegna nel formato AAAA-MM-DD");
				String date=input.readString();
				LocalDate d1=LocalDate.parse(date);
				tmp.setDelivery(d1);
				break;
			    case 1:
				System.out.println("Inserire contenuto");
				String contenuto=input.readString();
				tmp.setContent(contenuto);
				break;
			    case 2:
				System.out.println("Inserire quantità");
				int quantita=input.readInt();
				tmp.setQuantity(quantita);
				break;
			    case 3:
				System.out.println("Inserire valore");
				float valore=input.readFloat();
				tmp.setValue(valore);
				break;
			    case 4:
				System.out.println("Inserire peso");
				float peso=input.readFloat();
				tmp.setWeight(peso);
				break;
				
			}
		    } catch (IOException ex) {
			System.out.println("IMpossibile leggere da tastiera");
		    } catch (NumberFormatException ex) {
			System.out.println("Non è stato inserito un numero");
		    } catch (PalletNotFoundException ex) {
			System.out.println("Non esiste un pallet con l'id specificato");
		    }catch (DateTimeParseException ex){
			System.out.println("Formato data non valido");
		    }
		}

		    
		case 11:
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
		case 12:
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
		    
		default:
		    chosen=0;

	    }
	}
    }
}
