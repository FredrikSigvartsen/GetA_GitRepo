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
   // private SkademeldingsRegister skademelding;
    private int erstatninger;
    private Calendar startDato;
   // private ForsikringsListe forsikringer;
   // private SkadeMeldingsliste skademeldinger;
    private String fodselsNr;
    private boolean erForsikringsKunde;

    public ForsikringsKunde(String fornavn, String etternavn, String fakturaAdresse, String postSted, int postNr, /*SkademeldingsRegister skademelding,*/ int erstatninger, /*ForsikringsListe forsikringer, SkadeMeldingsliste skademeldinger,*/ String fodselsNr) {
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.fakturaAdresse = fakturaAdresse;
        this.postSted = postSted;
        this.postNr = postNr;
        //this.skademelding = skademelding;
        this.erstatninger = erstatninger;
        this.startDato = Calendar.getInstance();
        //this.forsikringer = forsikringer;
        //this.skademeldinger = skademeldinger;
        this.fodselsNr = fodselsNr;
    }

    public String getFornavn() {
        return fornavn;
    }

    public String getEtternavn() {
        return etternavn;
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

   /* public SkademeldingsRegister getSkademelding() {
        return skademelding;
    }

    public ForsikringsListe getForsikringer() {
        return forsikringer;
    }

    public SkadeMeldingsliste getSkademeldinger() {
        return skademeldinger;
    }*/
    
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

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startDatoString = sdf.format(startDato.getTime());
        return "\nForsikringsKunde: " + "\nFornavn: " + fornavn + "\nEtternavn: " 
                + etternavn + "\nFaktura adresse: " + fakturaAdresse + "\nPoststed: " 
                + postSted + "\nPostnummer: " + postNr + "\nForsikringspremie: " + forsikringsPremie 
                + "\nTotalkunde: " + (totalKunde == true ? "Ja" : "Nei") + "\nErstatninger: " 
                + erstatninger + "\nStartdato: " + startDatoString 
                + "\nFødselsnummer: " + fodselsNr + "\nEr forsikret: " 
                + (erForsikringsKunde == true ? "Ja" : "Nei");
    } 
}   //End of class ForsikringsKunde
