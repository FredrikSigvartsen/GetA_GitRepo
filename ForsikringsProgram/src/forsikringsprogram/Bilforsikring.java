
package forsikringsprogram;

import java.io.Serializable;

/**
 * Klassen lagrer data som er unike for bilforsikringer.
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
     * @return registreringsnummeret til bilen
     */
    public String getRegistreringsNr() {
        return registreringsNr;
    }//end of method getRegistreringsNr()
    
    /**
     * 
     * @return merke til bilen
     */
    public String getMerke() {
        return merke;
    }//end of method getMerke()
    
    /**
     * 
     * @return modellen til bilen
     */
    public String getModell() {
        return modell;
    }//end of method getModell()
    
    /**
     * 
     * @return returnerer klassens datafelter som string.
     */
    @Override
    public String toString() {
        return super.toString() + 
               "\nRegistrerings nr: " + registreringsNr + 
               "\nMerke: " + merke +
               "\nModell: " + modell +
               "\nRegistrerings år: " + registreringsAar +
               "\nKjørelengde: " + kjoreLengdeKm + " km" +
               "\nPris pr. km: " + prisPrKm + 
               "\nBonus: " + bonusIprosent + "%";
    }//end of method toString()
}//end of class Bilforsikring
