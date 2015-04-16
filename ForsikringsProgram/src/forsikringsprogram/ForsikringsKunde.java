/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forsikringsprogram;
import java.util.*;
import java.text.*;
/**
 *
 * @author Jens
 */

public class ForsikringsKunde {
    private String fornavn;
    private String etternavn;
    private String fakturaAdresse;
    private String postSted;
    private int postNr;
    
    private int forsikringsPremie;
    private boolean totalKunde;
    private int erstatninger;
    private Calendar startDato;
    private Forsikringsliste forsikringer;
    private SkademeldingsListe skademeldinger;
    private String fodselsNr;
    private boolean erForsikringsKunde;

    public ForsikringsKunde(String fornavn, String etternavn, String fakturaAdresse, String postSted, int postNr, String fodselsNr) {
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.fakturaAdresse = fakturaAdresse;
        this.postSted = postSted;
        this.postNr = postNr;
        this.erstatninger = erstatninger;
        this.startDato = Calendar.getInstance();
        this.fodselsNr = fodselsNr;
    }// end of constructor

    

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
        String startDatoString = sdf.format(startDato.getTime());
        
        String utskrift = "\n\nKunde   " + fornavn + " " + etternavn + "\n--------------------------------------------------------\n" 
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

    public int getPostNr() {
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
