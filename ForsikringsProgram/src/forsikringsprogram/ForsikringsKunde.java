/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forsikringsprogram;
import java.io.Serializable;
import java.util.*;
import java.text.*;
/**
 *
 * @author Jens
 */

public class ForsikringsKunde implements Serializable{
    
    private static final long serialVersionUID = 321L;
    private String fornavn;
    private String etternavn;
    private String fakturaAdresse;
    private String postSted;
    private String postNr;
    
    private int forsikringsPremie;
    private boolean totalKunde;
    private int erstatninger;
    private Calendar startDato;
    private Forsikringsliste forsikringer;
    private SkademeldingsListe skademeldinger;
    private String fodselsNr;
    private boolean erForsikringsKunde;

    public ForsikringsKunde(String fornavn, String etternavn, String fakturaAdresse, String postSted, String postNr, String fodselsNr) {
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.fakturaAdresse = fakturaAdresse;
        this.postSted = postSted;
        this.postNr = postNr;
        this.startDato = Calendar.getInstance();
        this.fodselsNr = fodselsNr;
        
        erForsikringsKunde = true;
        skademeldinger = new SkademeldingsListe();
        forsikringer = new Forsikringsliste();
    }// end of constructor
    
    ///Metode jeg har brukt for å teste
    public Forsikringsliste getForsikringsliste() {
        return forsikringer;
    }
    
    public SkademeldingsListe getSkademeldingsliste() {
        return skademeldinger;
    }
    
    /* Legger til en skademelding i kundens SkademeldingsListe. Returverdien indikerer om dette gikk eller ikke. Se SkademeldingsListe.registrerSkademelding()
       Returnerer også false hvis kunden ikke har riktig type forsikring. 
    */
    public String registrerSkademelding(Skademelding ny){
        if(!forsikringer.harRiktigForsikring(ny.getSkadeType()))
            return "Kunden har ikke forsikring på " + ( ny.getSkadeType() ).toLowerCase() +"en sin, i vårt selskap";
        if(!skademeldinger.registrerSkademelding(ny))
            return "Feil i registrering av skademelding. Kontakt IT-ansvarlig.";
        return "Skademelding er nå registrert på " + etternavn + ", " + fornavn + ". Utbetaling er på vei.";
    } // end of method registrerSkademelding(Skademelding)
    
    // Legger til en forsikring i kundens Forsikringsliste. Returverdien indikerer om det gikk, eller om hva som gikk galt. Se Forsikringsliste.Forsikring()
    public String registrerForsikring(Forsikring ny){
        if(ny == null)
            return "Feil i opprettelse av forsikring. Kontakt IT-ansvarlig.";
        if(!forsikringer.registrerForsikring(ny))
            return "Feil i registrering av forsikring. Kontakt IT-ansvarlig.";
        return "Forsikring nr." + ny.getAvtaleNr() + " er nå registrert på " + etternavn + ", " + fornavn + ".";
    } // end of method registrerForsikring(Forsikring)

    //Sier opp kundens forsikring med gitt avtalenummer. Returverdi indikerer om det gikk, eller hva som gikk galt.
    public String siOppForsikring(int avtaleNr){
        Forsikring forsikringen = forsikringer.finnForsikringer(avtaleNr);
        if(forsikringen == null)
            return "Kunden har ingen forsikring med dette avtalenummeret.";
        forsikringen.opphorForsikring();
        return "\nFølgende forsikring er nå sagt opp:" + forsikringen.toString() ;
    }// end of method siOppForsikring(avtaleNr)
    
    
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
        String startDatoString = sdf.format(startDato.getTime());
        
        String utskrift = "\n\nKunde   " + fornavn + " " + etternavn + "\n--------------------------------------------------------" 
                + "\nFødselsnummer: " + fodselsNr  
                + "\nFaktura adresse: " + fakturaAdresse + ", " + postNr + " " + postSted
                + "\nStartdato: " + startDatoString 
                + "\nForsikringspremie: " + forsikringsPremie 
                + "\nTotalkunde: " + (totalKunde == true ? "Ja" : "Nei")  
                + "\nErstatninger: " + erstatninger 
                + "\nEr kunde hos oss: " + (erForsikringsKunde == true ? "Ja" : "Nei");
        return utskrift;
    } //end of method toString()
    
    public String getFornavn() {
        return fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }
    public String getAdresse(){
        return fakturaAdresse + ", " + postNr + " " + postSted;
    }

    public String getPostSted() {
        return postSted;
    }

    public String getPostNr() {
        return postNr;
    }
    
    public int getErstatninger() {
        return erstatninger;
    }

    public Calendar getStartDato() {
        return startDato;
    }

    public String getFodselsNr() {
        return fodselsNr;
    }

    public Forsikringsliste getForsikringer() {
        return forsikringer;
    }

    public SkademeldingsListe getSkademeldinger() {
        return skademeldinger;
    }
    
    public boolean getTotalKunde() {
        return totalKunde;
    }

    public void setTotalKunde(boolean totalKunde) {
        this.totalKunde = totalKunde;
    }

    public boolean getErForsikringsKunde() {
        return erForsikringsKunde;
    }

    public void setErForsikringsKunde(boolean erForsikringsKunde) {
        this.erForsikringsKunde = erForsikringsKunde;
    }
}   //End of class ForsikringsKunde
