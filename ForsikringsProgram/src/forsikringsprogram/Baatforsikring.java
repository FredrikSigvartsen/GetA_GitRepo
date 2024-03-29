
package forsikringsprogram;

import java.io.Serializable;

/**
 * Klassen lagrer data som er unike for båtforsikringer.
 * Resten av konstruktør-parameterene sendes til superklassen 'Forsikring' sin konstruktør. 
 * Siste versjon skrevet: 15.05.2015 23:22
 * @author Elias Andreassen Thøgersen, Informasjonsteknologi, s236603
 */
public class Baatforsikring extends Forsikring implements Serializable{
    
    private static final long serialVersionUID = 345L;
    private String registreringsNr;
    private int aarsmodell;
    private int motorstyrke;
    private String merke;
    private String modell;
    private String motortype;
    
    /**
     * Initialiserer datafeltene.
     * @param betingelser for forsikringen.
     * @param forsikringsBelop Hvor mye båten skal forsikres for.
     * @param registreringsNr til båten.
     * @param aarsmodell til båten.
     * @param motorstyrke til båten.
     * @param merke til båten.
     * @param modell til båten.
     * @param motortype til båten.
     */
    public Baatforsikring(String betingelser, double forsikringsBelop, 
                         String registreringsNr, int aarsmodell, int motorstyrke, String merke,
                         String modell, String motortype) {
        
        super(betingelser, Forsikring.BAATPREMIE, forsikringsBelop);
        this.registreringsNr = registreringsNr;
        this.aarsmodell = aarsmodell;
        this.motorstyrke = motorstyrke;
        this.merke = merke;
        this.modell = modell;
        this.motortype = motortype;
    }//end of constructor
    
    /**
     * 
     * @return registreringsnummeret til båten
     */
    public String getRegistreringsNr() {
        return registreringsNr;
    }//end of method getRegistreringsNr()
    
    /**
     * 
     * @return merke til båten
     */
    public String getMerke() {
        return merke;
    }//end of method getMerke()
    
    /**
     * 
     * @return modellen til båten
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
               "\nÅrsmodell: " + aarsmodell +
               "\nMotorstyrke: " + motorstyrke + " HK" +
               "\nMerke: " + merke + 
               "\nModell: " + modell +
               "\nMotortype: " + motortype;
    }//end of method toString()
}//end of class Baatforsikring
