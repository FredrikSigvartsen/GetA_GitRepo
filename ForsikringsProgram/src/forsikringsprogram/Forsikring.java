//Elias

package forsikringsprogram;
import java.text.SimpleDateFormat;
import java.util.Calendar;

//Abstrakt superklasse til alle forsikringstypene
public abstract class Forsikring {
    
    public static final int BAAT = 1;
    public static final int REISE = 2;
    public static final int BIL = 3;
    public static final int BOLIG = 4;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
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
    }
    
    public void setForsikringsPremie(int x) {
        this.forsikringsPremie = x;////////////Må finne priser
    }
    
    public void setForsikringsBelop(int x) {
        this.forsikringsBelop = x;//////////////Må finne priser
    }
    
    //Skal kalles på når en avtale opphøres. Setter opphørsdato og gjforsikringen "inaktiv"
    public void opphorForsikring() {
        this.opphorsDato = Calendar.getInstance();
        this.aktivForsikring = false;
    }
    
    public String toString() {
        return "Forsikringsavtale nr." + avtaleNr + "\n-----------------------------------------------------------\n" +
               "Opprettelses dato: " + sdf.format(this.opprettelsesDato.getTime()) +
               "\nOpphørs dato: " + (!this.aktivForsikring ? sdf.format(this.opphorsDato.getTime())  : "Ikke opphørt") +
               "\nBetingelse: " + this.betingelser +
               "\nForsikringspremie: " + this.forsikringsPremie +
               "\nForsikringsbeløp: " + this.forsikringsBelop;
    }
}//end of class Forsikring
