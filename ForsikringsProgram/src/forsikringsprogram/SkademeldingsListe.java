/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forsikringsprogram;

import java.util.*;

/**
 *
 * @author fredr_000
 */
public class SkademeldingsListe {
    
    private List<Skademelding> skademeldingsListe;
    private ListIterator<Skademelding> iterator;
    
    public SkademeldingsListe(){
        skademeldingsListe = new ArrayList<>();
    }
    
    /* Sender med en skademelding som parameterer, og legger denne til i listen. Hvis skademeldingen blir lagt til i listen, returnerer metoden true. Hvis dette ikke går, returnerer
     metoden false. */
    public boolean registrerSkademelding(Skademelding skade){
        if(skade == null)
            return false;
        return skademeldingsListe.add(skade);
    } // end of method registrerSkademelding()
    
    /* Vi tenker oss at vi har generelle skader. Derfor sender vi med en tekst som beskriver skademeldingstypen, og sammenligner denne teksten mot alle skademeldingene i listens
       skademeldingstyper. Og vi returnerer alle skademeldinger som er av denne typen skademelding.  */
    public List<Skademelding> finnSkademeldinger(String skademeldingsType){
       List<Skademelding> liste = new ArrayList<>();
        
       iterator = skademeldingsListe.listIterator();
        while( iterator.hasNext() ){
            Skademelding gjeldeneSkademelding = iterator.next();
            if( skademeldingsType.equalsIgnoreCase( gjeldeneSkademelding.getSkadeType()) )
                liste.add (gjeldeneSkademelding);
        }// end of while
        
        return liste;
    }// end of method finnSkademeldinger(String skademeldingsType)
    
    
    /* 
       Her finner vi alle skademeldinger mellom en gitt tidsperiode. Vi sender med to Calendar objekter som parametre, og vi returnerer en liste med skademeldinger FRA OG MED
       Calender min TIL OG MED Calendar max. Metoden returnerer null hvis listen er tom, eller hvis et av objektene er null. 
    */
    public List<Skademelding> finnSkademeldinger( Calendar min, Calendar max){
        
        if( min == null || max == null)
            return null;
        
        List<Skademelding> liste = new ArrayList<>();
        iterator = skademeldingsListe.listIterator();
        
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
    
    
    
    public List<Skademelding> finnSkademeldinger( Calendar min, Calendar max, String skademeldingsType){
        List<Skademelding> liste = finnSkademeldinger(min,max);
        liste.retainAll( finnSkademeldinger(skademeldingsType));
        
        return liste;
    }// end of method finnSkademeldinger( Calendar min, Calendar max, String skademeldingsType)
    
    
    // Returnerer en utskrift av alle skademeldingene i listen. Returnerer null hvis listen er tom. 
    @Override
    public String toString(){
        
        iterator = skademeldingsListe.listIterator();
        String utskrift = "";
        while(iterator.hasNext()){
            utskrift += iterator.next().toString();
        }
        return utskrift;
    }// end of method toString()
    
    public List<Skademelding> getSkademeldingsListe() {
        return skademeldingsListe;
    }
    
}
