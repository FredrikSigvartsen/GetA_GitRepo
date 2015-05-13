
package forsikringsprogram;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Abstrakt superklasse til alle forsikringstypene.
 * @author Elias
 */
public abstract class Forsikring implements Serializable{
    
    private static final long serialVersionUID = 432L;
    public static final String BAAT = "Båt";
    public static final String REISE = "Reise";
    public static final String BIL = "Bil";
    public static final String BOLIG = "Bolig";
    public static final double BAATPREMIE = 10000;
    public static final double REISEPREMIE = 4000;
    public static final double BILPREMIE = 10000;
    public static final double BOLIGPREMIE = 20000;
    private SimpleDateFormat sdf;
    private Calendar opprettelsesDato;
    private Calendar opphorsDato;
    private double forsikringsPremie;
    private double forsikringsBelop;
    private String betingelser;
    private boolean aktivForsikring;
    private static int nesteNr = 1;
    private int avtaleNr;
    
    /**
     * Initialiserer datafeltene.
     * @param betingelser til forsikringen.
     * @param forsikringsPremie Årlig beløp som må betales for denne forsikringen.
     * @param forsikringsBelop Hvor mye 'gjenstanden' er forsikret for.
     */
    public Forsikring(String betingelser, double forsikringsPremie, double forsikringsBelop) {
        this.opprettelsesDato = Calendar.getInstance();
        this.betingelser = betingelser;
        this.aktivForsikring = true;
        this.avtaleNr = nesteNr++;
        this.forsikringsPremie = forsikringsPremie;
        this.forsikringsBelop = forsikringsBelop;
        sdf = new SimpleDateFormat("dd/MM/yyyy");
    }//end of constructor
    
    /** 
     * @return returnerer avtalenummeret til forsikringen. 
     */
    public int getAvtaleNr() {
        return avtaleNr;
    }
    
    /**
     * 
     * @return betingelsene for forsikringen
     */
    public String getBetingelser() {
        return betingelser;
    }
    
    /**
     * @return returnerer forsikringspremien. 
     */
    public double getForsikringsPremie() {
        return this.forsikringsPremie;
    }
    
    /**
     * Skal kalles på når en avtale opphøres. Setter opphørsdato og gjør forsikringen "inaktiv"
     */
    public void opphorForsikring() {
        this.opphorsDato = Calendar.getInstance();
        this.aktivForsikring = false;
    }
    
    /**
     * @return returnerer datoen når forsikringen ble sagt opp. 
     */
    public Calendar getOpphorsDato() {
        return this.opphorsDato;
    }
    
    /**
     * @return returnerer datoen når forsikringen ble opprettet.
     */
    public Calendar getOpprettelsesDato() {
        return this.opprettelsesDato;
    }
    
    /**
     * 
     * @return returnerer true eller false alt ettersom forsikringen er aktiv eller ikke. 
     */
    public boolean isActive() {
        return this.aktivForsikring;
    }
    
    /**
     * Brukes i forbindelse med å lese fra fil,
     * ettersom static datafelter ikke blir skrevet til fil med ObjectOutputStream.
     * @param nesteNr 
     */
    public static void setNesteNr(int nesteNr) {
        Forsikring.nesteNr = nesteNr;
    }
    
    /**
     * Brukes i forbindelse med å skrive nesteNr til fil,
     * ettersom static datafelter ikke blir skrevet til fil med ObjectOutputStream.
     * @return returnerer nesteNr.
     */
    public static int getNesteNr() {
        return nesteNr;
    }
    
    /**
     * 
     * @return returnerer klassens datafelter som string. 
     */
    @Override
    public String toString() {
        return "\n--------------------------------------------" + 
               "\nForsikringsavtale nr." + avtaleNr + (!( this.aktivForsikring ) ? " - Avtale opphørt" : "") +
               "\n--------------------------------------------" +
               "\nOpprettelses dato: " + sdf.format(this.opprettelsesDato.getTime()) +
               "\nAvtale opphørt: " + (!this.aktivForsikring ? sdf.format(this.opphorsDato.getTime())  : "Ikke opphørt") +
               "\nBetingelse: " + this.betingelser +
               "\nForsikringspremie: " + this.forsikringsPremie +
               "\nForsikringsbeløp: " + this.forsikringsBelop;
    }//end of method toString()
}//end of class Forsikring
