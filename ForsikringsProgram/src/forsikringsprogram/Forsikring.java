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
    private SimpleDateFormat sdf;
    private Calendar opprettelsesDato;
    private Calendar opphorsDato;
    private int forsikringsPremie;
    private int forsikringsBelop;
    private String betingelser;
    private boolean aktivForsikring;
    private static int nesteNr = 1;
    private int avtaleNr;
    
    //Konstruktør
    public Forsikring(String betingelser, int forsikringsPremie, int forsikringsBelop) {
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

    public static void setNesteNr(int nesteNr) {
        Forsikring.nesteNr = nesteNr;
    }

    public static int getNesteNr() {
        return nesteNr;
    }
    
    public String toString() {
        return "\nForsikringsavtale nr." + avtaleNr + "\n-------------------------------------------\n" +
               "Opprettelses dato: " + sdf.format(this.opprettelsesDato.getTime()) +
               "\nAvtale opphørt: " + (!this.aktivForsikring ? sdf.format(this.opphorsDato.getTime())  : "Ikke opphørt") +
               "\nBetingelse: " + this.betingelser +
               "\nForsikringspremie: " + this.forsikringsPremie +
               "\nForsikringsbeløp: " + this.forsikringsBelop;
    }
}//end of class Forsikring
