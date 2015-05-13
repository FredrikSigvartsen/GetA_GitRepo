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
    
    private double aarligUtbetaling;
    private double forsikringsPremie;
    private boolean totalKunde;
    private double utbetaltErstatning;
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
        
        this.totalKunde = false;
        this.erForsikringsKunde = false;
        this.skademeldinger = new SkademeldingsListe();
        this.forsikringer = new Forsikringsliste();
    }// end of constructor
    
    //Kalles på etter at en forsikring er tegnet. Metoden "inneholderTreForskjelligeForsikringstype"
    //returnerer true hvis kunden har tre forskjellige forsikringstyper i forsikringslisten sin.
    //Vil ikke returnere true hvis kunden allerede er totalkunde.
    public boolean blirTotalKunde() {
        if(this.totalKunde)
            return false;
        if(forsikringer.innholderTreForskjelligeForsikringstyper()) {
            this.totalKunde = true;
            return true;
        }
        return false;
    }
    
    public void leggTilForsikringspremie(double forsikringspremie) {
        this.forsikringsPremie += forsikringspremie;
    }
    
    public double getAarligUtbetaling() {
        if(this.totalKunde) {
            this.aarligUtbetaling = this.forsikringsPremie - (this.forsikringsPremie * 0.10);
            return this.aarligUtbetaling;
        }
        this.aarligUtbetaling = this.forsikringsPremie;
        return this.aarligUtbetaling;
    }
    
    public String visSkademeldingsliste() {
        return skademeldinger.toString();
    }
    
    public String visForsikringsliste() {
        return forsikringer.toString();
    }
    
    /* Legger til en skademelding i kundens SkademeldingsListe. Returverdien indikerer om dette gikk eller ikke. Se SkademeldingsListe.registrerSkademelding()
       Returnerer også false hvis kunden ikke har riktig type forsikring. 
    */
    public String registrerSkademelding(Skademelding ny){
        if(!forsikringer.harRiktigForsikring(ny.getSkadeType()))
            return "Kunden har ikke forsikring på " + ( ny.getSkadeType() ).toLowerCase() +"en sin, i vårt selskap";
        if(!skademeldinger.registrerSkademelding(ny))
            return "Feil i registrering av skademelding. Kontakt IT-ansvarlig.";
        
        ny.setErstatningsbelopLikTakst();
        this.utbetaltErstatning += ny.getTakst();
        
        return "Skademelding nr. " + ny.getSkadeNr() + " er nå registrert på " + etternavn + ", " + fornavn + ". Utbetaling er på vei.";
    } // end of method registrerSkademelding(Skademelding)
    
    // Legger til en forsikring i kundens Forsikringsliste. Returverdien indikerer om det gikk, eller om hva som gikk galt. Se Forsikringsliste.Forsikring()
    public String registrerForsikring(Forsikring ny){
        if(ny == null)
            return "Feil i opprettelse av forsikring. Kontakt IT-ansvarlig.";
        if(!forsikringer.registrerForsikring(ny))
            return "Feil i registrering av forsikring. Kontakt IT-ansvarlig.";
        
        if(ny instanceof Baatforsikring) {
            leggTilForsikringspremie(Forsikring.BAATPREMIE);
        } else if(ny instanceof Bilforsikring) {
            leggTilForsikringspremie(Forsikring.BILPREMIE);
        } else if(ny instanceof Reiseforsikring) {
            leggTilForsikringspremie(Forsikring.REISEPREMIE);
        } else if(ny instanceof Boligforsikring) {
            leggTilForsikringspremie(Forsikring.BOLIGPREMIE);
        }
        setErForsikringsKunde(true);
                
        return "Forsikring nr. " + ny.getAvtaleNr() + " er nå registrert på " + etternavn + ", " + fornavn + ".";
    } // end of method registrerForsikring(Forsikring)

    //Sier opp kundens forsikring med gitt avtalenummer. Returverdi indikerer om det gikk, eller hva som gikk galt.
    public String siOppForsikring(int avtaleNr){
        Forsikring forsikringen = forsikringer.finnForsikringer(avtaleNr);
        if(forsikringen == null)
            return "Kunden har ingen forsikring med dette avtalenummeret.";
        if(forsikringen.getOpphorsDato() != null)
            return "Denne forsikringen er allerede opphørt";
        forsikringen.opphorForsikring();
        if(!forsikringer.harAktiveForsikringer()) {
            setErForsikringsKunde(false);
            return "\nFølgende forsikring er nå sagt opp:" + forsikringen.toString() + 
                   "\n\nDenne kunden har ikke lenger noen aktive forsikringer, og er ikke lenger kunde.";
        }
        return "\nFølgende forsikring er nå sagt opp:" + forsikringen.toString();
    }// end of method siOppForsikring(avtaleNr)
    
    
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
        String startDatoString = sdf.format(startDato.getTime());
        
        String utskrift = "\n\n--------------------------------------------------------"
                        + "\n Kunde: " + fornavn + " " + etternavn  
                        + "\n--------------------------------------------------------" 
                        + "\n Fødselsnummer: " + fodselsNr  
                        + "\n Faktura adresse: " + fakturaAdresse + ", " + postNr + " " + postSted
                        + "\n Startdato: " + startDatoString 
                        + "\n Forsikringspremie: " + forsikringsPremie 
                        + "\n Totalkunde: " + (totalKunde ? "Ja" : "Nei")  
                        + "\n Utbetalt erstatning: " + utbetaltErstatning 
                        + "\n Er kunde hos oss: " + (erForsikringsKunde ? "Ja" : "Nei");
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
    
    public double getUtbetalteErstatninger() {
        return this.utbetaltErstatning;
    }

    public Calendar getStartDato() {
        return startDato;
    }

    public String getFodselsNr() {
        return fodselsNr;
    }
    
    public List<Forsikring> getAktiveForsikringer() {
        return forsikringer.getAktiveForsikringer();
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

    public boolean getErForsikringsKunde() {
        return erForsikringsKunde;
    }

    public void setErForsikringsKunde(boolean erForsikringsKunde) {
        this.erForsikringsKunde = erForsikringsKunde;
    }
}//end of class ForsikringsKunde
