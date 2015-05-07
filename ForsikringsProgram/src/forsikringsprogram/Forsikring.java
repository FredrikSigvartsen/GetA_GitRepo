//Elias

package forsikringsprogram;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

//Abstrakt superklasse til alle forsikringstypene
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
    
    //Konstruktør
    public Forsikring(String betingelser, double forsikringsPremie, double forsikringsBelop) {
        this.opprettelsesDato = Calendar.getInstance();
        this.betingelser = betingelser;
        this.aktivForsikring = true;
        this.avtaleNr = nesteNr++;
        this.forsikringsPremie = forsikringsPremie;
        this.forsikringsBelop = forsikringsBelop;
        sdf = new SimpleDateFormat("dd/MM/yyyy");
    }

    public int getAvtaleNr() {
        return avtaleNr;
    }
    
    public double getForsikringsPremie() {
        return this.forsikringsPremie;
    }
    
    public void setForsikringsPremie(int x) {
        this.forsikringsPremie = x;////////////Må finne priser
    }
    
    public void setForsikringsBelop(int x) {
        this.forsikringsBelop = x;//////////////Må finne priser
    }
    
    //Skal kalles på når en avtale opphøres. Setter opphørsdato og gjør forsikringen "inaktiv"
    public void opphorForsikring() {
        this.opphorsDato = Calendar.getInstance();
        this.aktivForsikring = false;
    }
    
    public Calendar getOpphorsDato() {
        return this.opphorsDato;
    }
    
    public Calendar getOpprettelsesDato() {
        return this.opprettelsesDato;
    }
    
    public boolean getAktivForsikring() {
        return this.aktivForsikring;
    }

    public static void setNesteNr(int nesteNr) {
        Forsikring.nesteNr = nesteNr;
    }

    public static int getNesteNr() {
        return nesteNr;
    }
    
    @Override
    public String toString() {
        return "\n---------------------------------------" + 
               "\n Forsikringsavtale nr." + avtaleNr + 
               "\n---------------------------------------" +
               "\n Opprettelses dato: " + sdf.format(this.opprettelsesDato.getTime()) +
               "\n Avtale opphørt: " + (!this.aktivForsikring ? sdf.format(this.opphorsDato.getTime())  : "Ikke opphørt") +
               "\n Betingelse: " + this.betingelser +
               "\n Forsikringspremie: " + this.forsikringsPremie +
               "\n Forsikringsbeløp: " + this.forsikringsBelop;
    }
}//end of class Forsikring
