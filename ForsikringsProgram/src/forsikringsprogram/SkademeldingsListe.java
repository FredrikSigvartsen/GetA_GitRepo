package forsikringsprogram;

import java.io.Serializable;
import java.util.*;

/**
 * En skademeldingsliste inneholder null eller flere skademeldinger. I denne klassen behandler man søk og registrering av og på skademeldinger. 
 * Hensikten med denne klassen er at hver forsikringskunde har en egen liste med skademeldinger. Så her gjør vi søk på kundens skademeldinger og
 * registrering av kundens skademeldinger i kundens skademeldingsliste. 
 * Siste versjon skrevet: 07/05/15 12:00
 * @author Fredrik Aleksander Sigvartsen, Dataingeniør, s236356
 * @author Elias Andreassen Thøgersen, Informasjonsteknologi, s236603
 */
public class SkademeldingsListe implements Serializable {
    
    private static final long serialVersionUID = 567L;
    private List<Skademelding> skademeldingsListe;
    
    /**
     * Oppretter en skademeldingsliste, av type ArrayList. 
     */
    public SkademeldingsListe(){
        skademeldingsListe = new ArrayList<>();
    }// end of constructor
    
    /**
     * Finner alle skademeldinger som er av typen gitt i parameterlisten.
     * @param skadetype Skadetypen på skademeldingene vi vil finne
     * @return En liste med skademeldingene av typen angitt i parameterlisten.
     */
    public List<Skademelding> listeMedSkademeldingAvType(String skadetype) {
        ListIterator<Skademelding> iter = skademeldingsListe.listIterator();
        List<Skademelding> ny = new ArrayList<>();
        
        switch (skadetype) {
            case "Båt":
                while(iter.hasNext()) {
                    Skademelding skademelding = iter.next();
                    if(skademelding.getSkadeType().equals(skadetype)) {
                        ny.add(skademelding);
                    }
                }// end of while      
                break;
            case "Reise":
                while(iter.hasNext()) {
                    Skademelding skademelding = iter.next();
                    if(skademelding.getSkadeType().equals(skadetype)) {
                        ny.add(skademelding);
                    }
                }// end of while      
                break;
            case "Bolig":
                while(iter.hasNext()) {
                    Skademelding skademelding = iter.next();
                    if(skademelding.getSkadeType().equals(skadetype)) {
                        ny.add(skademelding);
                    }
                }// end of while      
                break;
            case "Bil":
                while(iter.hasNext()) {
                    Skademelding skademelding = iter.next();
                    if(skademelding.getSkadeType().equals(skadetype)) {
                        ny.add(skademelding);
                    }
                }// end of while   
                break;
        }// end of switch-case
        return ny;
    }// end of method listeMedSkademeldingAvType(String skadetype)
    
    /**
     * Sender med en skademelding som parameterer, og legger denne til i listen.
     * @param skade Skademeldinger som skal registreres i listen.
     * @return En boolsk verdi som tilsier om dette gikk, eller ikke.
     */
    public boolean registrerSkademelding(Skademelding skade){
        if(skade == null)
            return false;
        return skademeldingsListe.add(skade);
    } // end of method registrerSkademelding()
    
    /**
     * Sjekker om SkademeldingsListe til kunden er tom.
     * @return En boolsk verdi som tilsier om listen SkademeldingsListe'n er tom. 
     */
    public boolean erTom(){
        return skademeldingsListe.isEmpty();
    }//end of method erTom()
    
    /** 
     * Finner en skademelding med skadenummer lik parameteren. 
     * @param skadeNr på skademeldingen brukeren til programmet vil søke opp.
     * @return skademeldingen som er funnet. 
     */
    public Skademelding finnSkademeldinger(int skadeNr){
        ListIterator<Skademelding> iterator = skademeldingsListe.listIterator();
        
        while(iterator.hasNext()){
            Skademelding gjeldendeSkademelding = iterator.next();
            if( skadeNr == gjeldendeSkademelding.getSkadeNr())
                return gjeldendeSkademelding;
        }
        return null;
    }// end of method finnSkademeldinger(int skadeNr)
    
    /**
     * Metoden finner alle skademeldinger av gitt parameter
     * @param skademeldingsType Hvilken skadetype man vil finne i listen av skademeldinger.. 
     * @return En liste med skademeldingen av skadetype
     */
    public List<Skademelding> finnSkademeldinger(String skademeldingsType){
       List<Skademelding> liste = new ArrayList<>();
        
       ListIterator<Skademelding> iterator = skademeldingsListe.listIterator();
        while( iterator.hasNext() ){
            Skademelding gjeldeneSkademelding = iterator.next();
            if( skademeldingsType.equalsIgnoreCase( gjeldeneSkademelding.getSkadeType()) )
                liste.add (gjeldeneSkademelding);
        }// end of while
        return liste;
    }// end of method finnSkademeldinger(String skademeldingsType)
    
    /**
     * Finner alle skademeldingene innenfor det gitte tidsintervallet som blir angitt i paramaterlisten.
     * @param min Startdato for tidsintervallet det søkes i
     * @param max Sluttdato for tidsintervallet det søkes i
     * @return En liste med alle skademelding mellom datoene min og max.
     */
    public List<Skademelding> finnSkademeldinger( Calendar min, Calendar max){
        
        if( min == null || max == null)
            return null;
        
        List<Skademelding> liste = new ArrayList<>();
        ListIterator<Skademelding> iterator = skademeldingsListe.listIterator();
        
        while(iterator.hasNext()){
            Skademelding gjeldendeSkademelding = iterator.next();
            Calendar skademeldingDato = gjeldendeSkademelding.getInntruffetDato();
            
            if( skademeldingDato.after(min)  &&  skademeldingDato.before(max))
                liste.add(gjeldendeSkademelding);
            else if( skademeldingDato.equals(max) || skademeldingDato.equals(min))
                liste.add(gjeldendeSkademelding);
        }// end of while
        
        return liste;
    }// end of method finnSkademeldinger( Calendar min, Calendar max)
    
    /**
     * Finner alle skademeldingene innenfor det gitte tidsintervallet, og som er av type skademeldingsType.
     * @param min Startdato for tidsintervallet det søkes i
     * @param max Sluttdato for tidsintervallet det søkes i
     * @param skademeldingsType Hvilken type skademeldinger som skal søkes på
     * @return En liste med skademeldinger. 
     */
    public List<Skademelding> finnSkademeldinger( Calendar min, Calendar max, String skademeldingsType){
        List<Skademelding> liste = finnSkademeldinger(min,max);
        if(liste != null) {
            if(finnSkademeldinger(skademeldingsType) != null) {
                liste.retainAll( finnSkademeldinger(skademeldingsType));
                return liste;
            } // end of inner if
        }// end of outter if
        return null;
    }// end of method finnSkademeldinger( Calendar min, Calendar max, String skademeldingsType)
    
    /**
     * @return returnerer skademeldingslisten.
     */
    public List<Skademelding> getListe() {
        return this.skademeldingsListe;
    }
    
    /**
     * 
     * @return Alle skademldingene i listen.  
     */
    @Override
    public String toString(){
        
        ListIterator<Skademelding> iterator = skademeldingsListe.listIterator();
        String utskrift = "";
        while(iterator.hasNext()){
            utskrift += iterator.next().toString();
        }
        return utskrift;
    }// end of method toString()
    
    /**
     * 
     * @return En liste med alle skademeldingene. 
     */
    public List<Skademelding> getSkademeldingsListe() {
        return skademeldingsListe;
    } // end of method getSkademeldingsListe() 
}// end of class SkademeldingsListe
