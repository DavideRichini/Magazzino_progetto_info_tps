/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import java.io.IOException;
import exceptions.FileException;

/**
 *
 * @author Studente
 * rapprensenta un file .txt.
 * permette di scrivere e leggere dal file di testo.
 */
public class TextFile 
{
    private char mode;
    private BufferedReader reader;
    private BufferedWriter writer;
    
    /**
     * 
     * @param fileName pathname del file da aprire
     * @param mode modalita di apertura del file
     * può esser W (scrittura) o R (lettura)
     * in caso di altri valori il file viene aperto in lettura
     * @throws FileNotFoundException sollevata quando il file non viene trovato
     * @throws IOException sollevata quando è impossibile accedere al file
     */
    
    public TextFile(String fileName, char mode) throws FileNotFoundException, IOException
    {
        this.mode='R';
        if(mode=='W' || mode=='w')
            this.mode='W';
        
        if(this.mode=='R')
        {
            reader=new BufferedReader(new FileReader(fileName));
        }   
        
        else
        {
            writer=new BufferedWriter(new FileWriter(fileName));
        }
    
    }
    
    /**
     * 
     * @param fileName
     * @param mode
     * @param append
     * @throws FileNotFoundException
     * @throws IOException 
     */
    
    public TextFile(String fileName, char mode, boolean append) throws FileNotFoundException, IOException
    {
        
        this.mode='R';
        if(mode=='W' || mode=='w')
            this.mode='W';
        
        if(this.mode=='R')
        {
            reader=new BufferedReader(new FileReader(fileName));
        }
            
        
        else
        {
            writer=new BufferedWriter(new FileWriter(fileName, append));
        }
    
    }
    
    /**
     * Scrive una stringa su un file aperto in scrittura
     * @param line string da scrivere
     * @throws FileException
     * @throws IOException sollevata quando è impossibile accedere al file
     */
    
    public void toFile(String line) throws FileException, IOException
    {
        if (mode=='R')
        {
            throw new FileException("il file è aperto in lettura!!!");
        }
        else
            writer.write(line);
            writer.newLine();
    }
    
    /**
     * Legge una stringa da un file aperto in lettura
     * @return la stringa letta
     * @throws FileException
     * @throws IOException sollevata quando è impossibile accedere al file
     */
    
    public String fromFile() throws FileException, IOException
    {
        String line;
        if (mode=='W')
        {
            throw new FileException("il file è aperto in scrittura");
        }
        else
            line=reader.readLine();
        
        if(line==null)
            throw new FileException("fine del file");
        return line;
    }
    
    /**
     * chiude il file aperto
     * @throws IOException sollevata quando è impossibile accedere al file
     */
    
    public void close() throws IOException
    {
        if(mode=='R')
        {
            reader.close();
        }
        else
            writer.close();
    }
    
    
    
    
}
