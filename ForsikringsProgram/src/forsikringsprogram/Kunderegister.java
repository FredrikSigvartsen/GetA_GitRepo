/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forsikringsprogram;

import java.util.*;

/**
 *
 * @author Fredrik
 */
public class Kunderegister {
    private TreeSet<ForsikringsKunde> kunderegister;
    private Iterator<ForsikringsKunde> iterator;
    
    public Kunderegister(){
        // Redefinerer comparatoren til å sortere på etternavn
        Comparator<ForsikringsKunde> comparator = (ForsikringsKunde f1, ForsikringsKunde f2) -> f1.getEtternavn().compareToIgnoreCase(f2.getEtternavn()) ;
        // end of override method compare( ForsikringsKunde f1, ForsikringsKunde f2 )
        
        kunderegister = new TreeSet<>(comparator); // Sorterer objektene med comparatoren vi sender med. 
    }// end of oonstructor
    
    
    //Kundebehandling 
    /* Setter inn en kunde i registeret. Returnerer false hvis IKKE objektet er ForsikringsKunde, eller hvis kunden ikke blir lagt til.
       Returnerer true hvis kunden blir lagt til. 
    */
    public boolean registrerKunde(ForsikringsKunde ny){
        
        if(ny == null)
            return false;
        if(!kunderegister.add(ny))
            return false;
        return true;
    }// end of method settInn(ForsikringsKunde)
    
    // Finner en kunde med fornavn,etternavn,fødselsnummer, og returnerer denne Kunden. Returnerer null kunden ikke finnes i registeret.
    public ForsikringsKunde finnKunde(String fodselsNr){
        
        iterator = kunderegister.iterator();
        ForsikringsKunde gjeldendeKunde = null;
        while(iterator.hasNext()){
            gjeldendeKunde = iterator.next();
            if( fodselsNr.equalsIgnoreCase(gjeldendeKunde.getFodselsNr()) )
                return gjeldendeKunde;
        }// end of while
        return null;
    }// end of method finnKunde(String fornavn, String etternavn, String fodselsNr)
    
    /* Registrerer en skademelding på en kunde som har fødselsnummer lik fodselsNr. Returverdien indikerer om dette gikk eller ikke.
       Se SkademeldingsListe.registrerSkademelding  .*/
    public boolean registrerSkademelding(Skademelding skademelding, String fodselsNr){
        ForsikringsKunde kunde = finnKunde(fodselsNr);
        if(kunde == null)
            return false;
        if (!kunde.registrerSkademelding(skademelding) ) 
            return false;
        return true;
    }// end of method registrerSkademelding(Skademelding skademelding, String fodselsNr)
    
    /* Tegner/registrerer en forsikring på en kunde som har fødselsnummer lik parameteren fodselsNr. Returverdien indikerer om dette gikk eller ikke.
       Se Forsikringsliste.registrerForsikring()*/
    public boolean tegnForsikring(Forsikring ny, String fodselsNr){
        ForsikringsKunde kunde = finnKunde(fodselsNr);
        if(kunde == null)
            return false;
        if(!kunde.registrerForsikring(ny))
            return false;
        return true;        
    }// end of method tegnForsikring
    
    
    
    @Override
    public String toString(){
        if(kunderegister.isEmpty())
            return null;
        iterator = kunderegister.iterator();
        String utskrift = "";
        while(iterator.hasNext())
            utskrift += iterator.next().toString();
        return utskrift;
    }// end of method toString()
}// end of class Kundregister
