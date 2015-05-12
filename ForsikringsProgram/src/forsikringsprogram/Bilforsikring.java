//Elias

package forsikringsprogram;

import java.io.Serializable;

/**
 * Klassen skal lagrer data som er unike for bilforsikringer.
 * Resten av konstruktør-parameterene sendes til superklassen 'Forsikring' sin konstruktør. 
 * @author Elias
 */
public class Bilforsikring extends Forsikring implements Serializable {
    
    private static final long serialVersionUID = 543L;
    private String registreringsNr;
    private String merke;
    private String modell;
    private int registreringsAar;
    private int kjoreLengdeKm;
    private int prisPrKm;
    private int bonusIprosent;
    
    /**
     * Initialiserer datafeltene.
     * @param betingelser for forsikringen.
     * @param forsikringsBelop Hvor mye bilen skal forsikres for.
     * @param registreringsNr til bilen.
     * @param merke til bilen.
     * @param modell til bilen.
     * @param registreringsAar til bilen.
     * @param kjoreLengdeKm til bilen.
     * @param prisPrKm Hvor mye det koster pr. km.
     */
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
    }//end of constructor
    
    /**
     * 
     * @return returnerer klassens datafelter som string.
     */
    @Override
    public String toString() {
        return super.toString() + 
               "\n Registrerings nr: " + registreringsNr + 
               "\n Merke: " + merke +
               "\n Modell: " + modell +
               "\n Registrerings år: " + registreringsAar +
               "\n Kjørelengde: " + kjoreLengdeKm + " km" +
               "\n Pris pr. km: " + prisPrKm + 
               "\n Bonus: " + bonusIprosent + "%";
    }//end of method toString()
}//end of class Bilforsikring
