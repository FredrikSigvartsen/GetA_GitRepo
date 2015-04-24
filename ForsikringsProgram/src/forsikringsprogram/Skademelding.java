package forsikringsprogram;


import java.awt.*;
import java.io.Serializable;
import java.text.*;
import java.util.*;
import javax.swing.*;

/*
 En skademelding er i "forsikringssystemet" viktig, da det for forsikringsselskapet er viktig å kunne generere utbetalinger på bakgrunn av informasjonen om kunden
 og skademeldingen. Dette for å avlaste selskapet med unødige behandlinger. Det er en saksbehandler som behandler skademeldingene, og avgjører om informasjonen fra kunden
 er tilstrekkelig for å kunne registrere dette som en skademelding. 
 Vi har get-metoder for de fleste variablene, da det er interessant å kunne hente informasjon om en skademelding senere i programmet. 
*/

public class Skademelding implements Serializable {
    
    private static final long serialVersionUID = 765L;
    private Calendar inntruffetDato;
    private String inntruffetTidspunkt;
    private String skadeType; 
    private static int nesteNr = 1;
    private int skadeNr;          
    private String beskrivelse;  // Beskrivelse av skade
    private String vitneKontakt; // Kontaktinformasjon om vitner
    private double takst;           // Taksteringsbeløp for skaden
    private double erstatningsbelop;
    private SimpleDateFormat formaterDato;

    
    public Skademelding(String skadeType, String beskrivelse, String vitneKontakt, double takst, Calendar inntruffetDato, String inntruffetTidspunkt) {
        
        this.skadeType = skadeType;
        this.beskrivelse = beskrivelse;
        this.vitneKontakt = vitneKontakt;
        this.takst = takst;
        this.inntruffetTidspunkt = inntruffetTidspunkt;
        this.inntruffetDato = inntruffetDato;
        skadeNr = nesteNr++;
        formaterDato = new SimpleDateFormat("dd/MM/yyyy");
    }
    
    
    // Redefinerer toString() for å få en bedre utskriftsform av objekter av type Skademelding. 
   @Override
    public String toString(){
        String skademelding = "\n\n\nSkademelding nr." + skadeNr + "\n------------------------------------------------------------\n"
                + "Skadetype  :  " + skadeType 
                + "\nBeskrivelse av skaden:\n" + beskrivelse
                + "\nTaksteringsbeløp:  " + takst
                + "\nErstatningsbeløp:  " + erstatningsbelop
                + "\nKontakt til vitne:  " + vitneKontakt
                + "\nDato for inntruffet skade:  " + formaterDato.format(inntruffetDato.getTime())
                + "\nAnslått tid for inntruffet skade:  " + inntruffetTidspunkt;
        
       return skademelding;
    }//end of method toString()
    
//    
//    //Set-metoder
//    public void setInntruffetDato(Calendar inntruffetDato) {
//        this.inntruffetDato = inntruffetDato;
//    }
//    
    
    public void setErstatningsbelop(double takst) {
        this.erstatningsbelop = takst;
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

    public String getVitneKontakt() {
        return vitneKontakt;
    }

    public double getTakst() {
        return takst;
    }

    public double getErstatningsbeløp() {
        return erstatningsbelop;
    }
   
   
}// end of class Skademelding
