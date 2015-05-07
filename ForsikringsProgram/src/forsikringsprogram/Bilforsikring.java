//Elias

package forsikringsprogram;

//Reiseforsikring-klasse

import java.io.Serializable;

public class Bilforsikring extends Forsikring implements Serializable {
    
    private static final long serialVersionUID = 543L;
    private String registreringsNr;
    private String merke;
    private String modell;
    private int registreringsAar;
    private int kjoreLengdeKm;
    private int prisPrKm;
    private int bonusIprosent;
    
    //Konstruktør
    public Bilforsikring(String betingelser, double forsikringsBelop,
                         String registreringsNr, String merke, String modell, int registreringsAar,
                         int kjoreLengdeKm, int prisPrKm) {
        super(betingelser, Forsikring.BILPREMIE, forsikringsBelop);
        this.registreringsNr = registreringsNr;
        this.merke = merke;
        this.modell = modell;
        this.registreringsAar = registreringsAar;
        this.kjoreLengdeKm = kjoreLengdeKm;
        this.prisPrKm = prisPrKm;
        this.bonusIprosent = 0;
    }
    
    @Override
    public String toString() {
        return super.toString() + 
               "\n Registrerings nr: " + this.registreringsNr + 
               "\n Merke: " + this.merke +
               "\n Modell: " + this.modell +
               "\n Registrerings år: " + this.registreringsAar +
               "\n Kjørelengde: " + this.kjoreLengdeKm + " km" +
               "\n Pris pr. km: " + this.prisPrKm + 
               "\n Bonus: " + this.bonusIprosent + "%";
    }
}//end of class Bilforsikring
