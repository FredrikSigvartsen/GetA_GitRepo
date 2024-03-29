package forsikringsprogram;


import java.io.File;
import java.io.Serializable;
import java.text.*;
import java.util.*;
import java.util.List;
/**
 * En skademelding er i "forsikringssystemet" viktig, da det for forsikringsselskapet er viktig å kunne generere utbetalinger på bakgrunn av informasjonen om kunden
 * og skademeldingen. Dette for å avlaste selskapet med unødige behandlinger. Det er en saksbehandler som behandler skademeldingene, og avgjører om informasjonen fra kunden
 * er tilstrekkelig for å kunne registrere dette som en skademelding. 
 * Vi har get-metoder for de fleste variablene, da det er interessant å kunne hente informasjon om en skademelding senere i programmet.
 * Hensikten med denne klassen å ha alle skademeldinger som et eget objekt, slik at det blir lettere å lagre informasjon om disse skademeldingene. 
 * Siste versjon skrevet: 05/05/15 12:00
 * @author Fredrik Aleksander Sigvartsen, Dataingeniør, s236356
 */
public class Skademelding implements Serializable {
    
    private static final long serialVersionUID = 765L;
    private Calendar inntruffetDato, opprettelsesDato;
    private String inntruffetTidspunkt;
    private String skadeType; 
    private static int nesteNr = 1;
    private int skadeNr;          
    private String beskrivelse;  // Beskrivelse av skade
    private String vitneKontakt; // Kontaktinformasjon om vitner
    private double takst;           // Taksteringsbeløp for skaden
    private double erstatningsbelop;
    private List<File> bilder;
    private SimpleDateFormat formaterDato;

    /**
     * Setter Skadenummeret til skaden til neste nummer.
     * @param skadeType for skaden
     * @param beskrivelse av skaden
     * @param vitneKontakt til skaden
     * @param takst for skaden
     * @param inntruffetDato til skaden
     * @param inntruffetTidspunkt til skaden
     * @param bilder liste av filer, for lagring av flere bilder av skaden.
     */
    public Skademelding(String skadeType, String beskrivelse, String vitneKontakt, double takst, 
                        Calendar inntruffetDato, String inntruffetTidspunkt, List<File> bilder) {
        this.opprettelsesDato = Calendar.getInstance();
        this.skadeType = skadeType;
        this.beskrivelse = beskrivelse;
        this.vitneKontakt = vitneKontakt;
        this.takst = takst;
        this.inntruffetTidspunkt = inntruffetTidspunkt;
        this.inntruffetDato = inntruffetDato;
        this.bilder = bilder;
        skadeNr = nesteNr++;
        this.formaterDato = new SimpleDateFormat("dd/MM/yyyy");
    }// end of constructor
    
    /**
     * Setter ersatningsbeløpet lik taksten. 
     */
    public Calendar getOpprettelsesDato() {
        return this.opprettelsesDato;
    }//end of method getOpprettelsesDato()
    
    /**
     * Setter erstatningsbeløpet lik taksten.
     */
    public void setErstatningsbelopLikTakst() {
        this.erstatningsbelop = this.takst;
    }// end of method getBilder()
    
    /**
     * 
     * @return nesteNr, altså løpenummeret til skaden. For skriving til/lesing fra fil. 
     */
    public static int getNesteNr() {
        return nesteNr;
    }// end of method getNesteNr()

    /**
     * 
     * @param nesteNr Setter nestenummeret(løpenummeret) - for lagring og skriving til fil
     */
    public static void setNesteNr(int nesteNr) {
        Skademelding.nesteNr = nesteNr;
    }// end of method setNesteNr(int nesteNr)
    
    /**
     * 
     * @return Tidspunktet når skaden oppstod. 
     */
    public String getInntruffetTidspunkt() {
        return inntruffetTidspunkt;
    }// end of method getInntruffetTidspunkt()

    /**
     * 
     * @return Dato for når skaden oppstod. 
     */
    public Calendar getInntruffetDato() {
        return inntruffetDato;
    }// end of method getInntruffetDato()

    /**
     * 
     * @return Hva slags type skade det er.
     */
    public String getSkadeType() {
        return skadeType;
    }// end of method getSkadeType()

    /**
     * 
     * @return Det unike skadenummeret til skaden
     */
    public int getSkadeNr() {
        return skadeNr;
    }// end of method getSkadeNr()

    /**
     * 
     * @return Beskrivelse av skaden 
     */
    public String getBeskrivelse() {
        return beskrivelse;
    }// end of method getBeskrivelse()

    /**
     * 
     * @return Kontaktinformasjon om vitner til skaden.  
     */
    public String getVitneKontakt() {
        return vitneKontakt;
    }// end of method getVitneKontakt()

    /**
     * 
     * @return Taksten til skaden 
     */
    public double getTakst() {
        return this.takst;
    }// end of method getTakst()

    /**
     * 
     * @return Erstatningsbeløpet til skaden.
     */
    public double getErstatningsbelop() {
        return erstatningsbelop;
    }// end of getErstatningsbelop()
    
    /**
     * @return returnerer listen med bilder.
     */
    public List<File> getBilder() {
        return bilder;
    } // end of method getBilder()
    
    /**
     * 
     * @return Alle opplysninger om skaden. Foruten bilder.
     */
   @Override
    public String toString(){
        String skademelding = 
                "\n\n---------------------------------------------------"
                + "\nSkademelding nr." + skadeNr  
                + "\n---------------------------------------------------"
                + "\nSkadetype  :  " + skadeType 
                + "\nBeskrivelse av skaden:\n " + beskrivelse
                + "\nTaksteringsbeløp:  " + takst
                + "\nErstatningsbeløp:  " + erstatningsbelop
                + "\nKontakt til vitne:  " + vitneKontakt
                + "\nDato for inntruffet skade:  " + formaterDato.format(inntruffetDato.getTime())
                + "\nAnslått tid for inntruffet skade:  " + inntruffetTidspunkt;
        
       return skademelding;
    }//end of method toString()
}// end of class Skademelding
