package forsikringsprogram;
import java.io.Serializable;
import java.util.*;
import java.text.*;

/**
 * En kunde i forsikringsselskapet har 0 eller flere forsikringer og skademeldinger. Derfor har en ForsikringsKunde en Forsikringsliste og SkademeldingsListe.
 * Hensikten med denne klassen er å registrere skademeldinger, registrere og si opp forsikringer og beregning av totalt utgift/inntekt på en kunde. 
 * Siste versjon skrevet: 01/05/15 12:00
 * @author Fredrik Aleksander Sigvartsen, Dataingeniør, s236356
 * @author Elias Andreassen Thøgersen, Informasjonsteknologi, s236603
 * @author Jens Omfjord, Informasjonsteknologi, s236641
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

    /**
     * 
     * @param fornavn Fornavnet til kunden som det skal opprettes
     * @param etternavn Etternavnet til kunden som skal opprettes
     * @param fakturaAdresse Gateadresse/Fakturaadresse på kunden som skal opprettes
     * @param postSted Poststed til bostedet hvor kunden bor
     * @param postNr Postnummeret til bostedet hvor kunden bor
     * @param fodselsNr Fødselsnummeret til kunden som skal opprettes
     */
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
    
    /**
     * Registrerer en skademelding på kunden, altså legger til skademeldingen i kundens skademeldingsliste. 
     * @param ny Den nye skademeldingen som skal registreres. 
     * @return En String-verdi som tilsier om hva som gikk galt eller bra i registreringen av skademeldingen. 
     */
    public String registrerSkademelding(Skademelding ny){
        if(!forsikringer.harRiktigForsikring(ny.getSkadeType()))
            return "Kunden har ikke forsikring på " + ( ny.getSkadeType() ).toLowerCase() +"en sin, i vårt selskap";
        if(!skademeldinger.registrerSkademelding(ny))
            return "Feil i registrering av skademelding. Kontakt IT-ansvarlig.";
        
        ny.setErstatningsbelopLikTakst();
        this.utbetaltErstatning += ny.getTakst();
        
        return "Skademelding nr. " + ny.getSkadeNr() + " er nå registrert på " + etternavn + ", " + fornavn + ". Utbetaling er på vei.";
    } // end of method registrerSkademelding()
    
    /**
     * Legger til en forsikring i kundens Forsikringsliste, altså registrerer en forsikring på kunden. 
     * @param ny Den nye forsikringen som skal registreres/tegnes på kunden. 
     * @return Returverdien indikerer om det gikk, eller om hva som gikk galt. Se Forsikringsliste.Forsikring()
     */
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

    // 
    /**
     * Sier opp kundens forsikring med gitt avtalenummer.
     * @param avtaleNr
     * @return Returverdi inneholder en tekststreng som indikerer om det gikk, eller hva som gikk galt.
     */
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
    
    /**
     * Kalles på etter at en forsikring er tegnet. Metoden "inneholderTreForskjelligeForsikringstype"
     * @return True hvis kunden har forsikring av riktig type, OG IKKE er totalkunde fra før. False hvis kunnden er totalkunde og om kunden ikke har rett forsikring
     */
    public boolean blirTotalKunde() {
        if(this.totalKunde)
            return false;
        if(forsikringer.innholderTreForskjelligeForsikringstyper()) {
            this.totalKunde = true;
            return true;
        }
        return false;
    }// end of method blirTotalKunde()
    
    /**
     * Legger til den årlige forsikringspremien på kunden. 
     * @param forsikringspremie Det beløpet som skal registreres som kundens årlige forsikringspremie. 
     */
    public void leggTilForsikringspremie(double forsikringspremie) {
        this.forsikringsPremie += forsikringspremie;
    }// end of method leggTilForsikringspremie()
    
    /**
     * 
     * @return Kundens årlige betaling til selskapet, med hensyn på om kunden er totalkunde.  
     */
    public double getAarligUtbetaling() {
        if(this.totalKunde) {
            this.aarligUtbetaling = this.forsikringsPremie - (this.forsikringsPremie * 0.10);
            return this.aarligUtbetaling;
        }// end of if
        this.aarligUtbetaling = this.forsikringsPremie;
        return this.aarligUtbetaling;
    }// end of getAarligUtbetaling()
    
    /**
     * 
     * @return En tekst med info om alle kundens skademeldinger. 
     */
    public String visSkademeldingsliste() {
        return skademeldinger.toString();
    }// end of method visSkademeldingsliste()
    
    /**
     * 
     * @return En tekst med info om alle kundens forsikringer.  
     */
    public String visForsikringsliste() {
        return forsikringer.toString();
    }// end of method visForsikringsliste()
    
    
    /**
     * 
     * @return Fornavnet til kunden.
     */
    public String getFornavn() {
        return fornavn;
    }// end of method getFornavn()

    /**
     * 
     * @return Etternavnet til kunden 
     */
    public String getEtternavn() {
        return etternavn;
    }// end of method getEtternavn()
    
    /**
     * 
     * @return En tekststreng med gateadresse, postnummer, og poststed, hvor kunden bor. 
     */
    public String getAdresse(){
        return fakturaAdresse + ", " + postNr + " " + postSted;
    }// end of method getAdresse()

    /**
     * 
     * @return Poststedet hvor kunden bor.
     */
    public String getPostSted() {
        return postSted;
    }// end of method getPostSted()

    /**
     * 
     * @return Postnummeret hvor kunden bor 
     */
    public String getPostNr() {
        return postNr;
    }// end of method getPostNr()
    
    /**
     * 
     * @return Alle kundens utbetalte erstatninger
     */
    public double getUtbetalteErstatninger() {
        return this.utbetaltErstatning;
    }// end of method getUtbetalteErstatninger()

    /**
     * 
     * @return Calendar-objekt som inneholder datoen for når kunden ble opprettet.  
     */
    public Calendar getStartDato() {
        return startDato;
    }// end of method getStartDato()

    /**
     * 
     * @return Fødselsnummeret til kunden
     */
    public String getFodselsNr() {
        return fodselsNr;
    }// end of method getFodselsNr()
    
    /**
     * 
     * @return En liste med alle kundens aktive forsikringer.  
     */
    public List<Forsikring> getAktiveForsikringer() {
        return forsikringer.getAktiveForsikringer();
    }// end of method getAktiveForsikringer()
    
    /**
     * 
     * @return Forsikringsliste-objekt som inneholder kundens forsikringer, hvis kunden har noen.  
     */
    public Forsikringsliste getForsikringer() {
        return forsikringer;
    }// end of method getForsikringer()

    /**
     * 
     * @return SkademeldingsListe-objekt som inneholder kundens skademeldinger, hvis kunden har noen.  
     */
    public SkademeldingsListe getSkademeldinger() {
        return skademeldinger;
    }// end of method getSkademeldinger()
    
    /**
     * 
     * @return En boolsk verdi som tilsier om kunden er totalkunde eller ikke. 
     */
    public boolean erTotalKunde() {
        return totalKunde;
    }// end of method erTotalKunde()

    /**
     * 
     * @return En boolsk verdi som tilsier om personen har et eksisterende kunde-forhold eller ikke. 
     */
    public boolean erForsikringskunde() {
        return erForsikringsKunde;
    }// end of method erForsikringskunde()

    /**
     * 
     * @param erForsikringsKunde  Setter kunden sitt eksisterende kundeforhold til den dene verdien. 
     */
    public void setErForsikringsKunde(boolean erForsikringsKunde) {
        this.erForsikringsKunde = erForsikringsKunde;
    }// end of method setErForsikringsKunde()
    
    /**
     * 
     * @return En tekststreng med informasjon om kunden. 
     */
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
        String startDatoString = sdf.format(startDato.getTime());
        
        String utskrift = "\n\n--------------------------------------------------------"
                        + "\nKunde: " + fornavn + " " + etternavn  
                        + "\n--------------------------------------------------------" 
                        + "\nFødselsnummer: " + fodselsNr  
                        + "\nFaktura adresse: " + fakturaAdresse + ", " + postNr + " " + postSted
                        + "\nStartdato: " + startDatoString 
                        + "\nForsikringspremie: " + forsikringsPremie 
                        + "\nTotalkunde: " + (totalKunde ? "Ja" : "Nei")  
                        + "\nUtbetalt erstatning: " + utbetaltErstatning 
                        + "\nEr kunde hos oss: " + (erForsikringsKunde ? "Ja" : "Nei");
        return utskrift;
    } //end of method toString()
}//end of class ForsikringsKunde
