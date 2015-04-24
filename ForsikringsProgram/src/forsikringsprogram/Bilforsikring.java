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
    public Bilforsikring(String betingelser, int forsikringsPremie, int forsikringsBelop,
                         String registreringsNr, String merke, String modell, int registreringsAar,
                         int kjoreLengdeKm, int prisPrKm) {
        super(betingelser, forsikringsPremie, forsikringsBelop);
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
               "\nRegistrerings nr: " + this.registreringsNr + 
               "\nMerke: " + this.merke +
               "\nModell: " + this.modell +
               "\nRegistrerings år: " + this.registreringsAar +
               "\nKjørelengde: " + this.kjoreLengdeKm + " km" +
               "\nPris pr. km: " + this.prisPrKm + 
               "\nBonus: " + this.bonusIprosent + "%";
    }
}//end of class Bilforsikring
