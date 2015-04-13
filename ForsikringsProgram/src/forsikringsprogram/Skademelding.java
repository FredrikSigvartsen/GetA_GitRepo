package forsikringsprogram;


import java.awt.*;
import java.text.*;
import java.util.*;
import javax.swing.*;



public class Skademelding {

   private Calendar inntruffetDato;
   private String inntruffetTidspunkt;
   private String skadeType; 
   private static int nesteNr = 1;
   private int skadeNr;          
   private String beskrivelse;  // Beskrivelse av skade
   private ImageIcon bilde;         // Bilde av skade
   private String vitneKontakt; // Kontaktinformasjon om vitner
   private int takst;           // Taksteringsbeløp for skaden
   private int erstatningsbeløp;
   private SimpleDateFormat formaterDato;
   //private String skjema;   // gjelder bare for bil

    
    public Skademelding(String skadeType, String beskrivelse, ImageIcon bilde, String vitneKontakt, int takst, int erstatningsbeløp, String inntruffetTidspunkt) {
        
        this.skadeType = skadeType;
        this.beskrivelse = beskrivelse;
        this.bilde = bilde;
        this.vitneKontakt = vitneKontakt;
        this.takst = takst;
        this.erstatningsbeløp = erstatningsbeløp;
        this.inntruffetTidspunkt = inntruffetTidspunkt;
        skadeNr = nesteNr++;
        formaterDato = new SimpleDateFormat("dd/MM/yyyy");
    }
    //Utskrift av skademeldingens toString()
    public String toString(){
        String skademelding = "\n\nSkademelding\n------------------------------------------------------------\n"
                + "Skadetype  :  " + skadeType 
                + "\nSkadenummer:  " + skadeNr
                + "\nBilde av skaden:  " + bilde
                + "\nBeskrivelse av skaden:\n" + beskrivelse
                + "\nTaksteringsbeløp:  " + takst
                + "\nErstatningsbeløp:  " + erstatningsbeløp
                + "\nKontakt til vitne:  " + vitneKontakt
                + "\nDato for inntruffet skade:  " + formaterDato.format(inntruffetDato.getTime())
                + "\nAnslått tid for inntruffet skade:  " + inntruffetTidspunkt;
        
       return skademelding;
    }//end of method toString()
    
    public String skrivDatoTid(){
        String dato = "";
        
        return dato;
    }
    //Set-metoder
    public void setInntruffetDato(Calendar inntruffetDato) {
        this.inntruffetDato = inntruffetDato;
    }
    
    //Get-metoder
    public static int getNesteNr() {
        return nesteNr;
    }

    public static void setNesteNr(int nesteNr) {
        Skademelding.nesteNr = nesteNr;
    }

    public String getInntruffetTidspunkt() {
        return inntruffetTidspunkt;
    }

    public Calendar getInntruffetDato() {
        return inntruffetDato;
    }

    public String getSkadeType() {
        return skadeType;
    }

    public int getSkadeNr() {
        return skadeNr;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public ImageIcon getBilde() {
        return bilde;
    }

    public String getVitneKontakt() {
        return vitneKontakt;
    }

    public int getTakst() {
        return takst;
    }

    public int getErstatningsbeløp() {
        return erstatningsbeløp;
    }
   
   
}// end of class Skademelding
