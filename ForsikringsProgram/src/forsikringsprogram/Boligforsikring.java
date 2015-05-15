
package forsikringsprogram;

import java.io.Serializable;

/**
 * Klassen lagrer data som er unike for boligforsikringer.
 * Resten av konstruktør-parameterene sendes til superklassen 'Forsikring' sin konstruktør. 
 * Siste versjon skrevet: 15.05.2015 23:22
 * @author Elias Andreassen Thøgersen, Informasjonsteknologi, s236603
 */
public class Boligforsikring extends Forsikring implements Serializable {
    
    private static final long serialVersionUID = 456L;
    private String gateAdresse;
    private String boligType;
    private String byggeMateriale;
    private String standard;
    private String postNr;
    private int byggAar;
    private int antallKvm;
    
    /**
     * Initialiserer datafeltene.
     * @param betingelser for forsikringen.
     * @param forsikringsBelop Hvor mye boligen skal forsikres for.
     * @param gateAdresse Adressen til boligen.
     * @param boligType Hva slags type bolig det er snakk om.
     * @param byggeMateriale Hvilket byggemateriale boligen består av.
     * @param standard Boligens standar. (høy, middels, lav)
     * @param postNr Boligens postnr.
     * @param byggAar Boligens byggår.
     * @param antallKvm Hvor stor boligen er i kvadratmeter.
     */
    public Boligforsikring(String betingelser, double forsikringsBelop,
                           String gateAdresse, String boligType, String byggeMateriale, 
                           String standard, String postNr, int byggAar, int antallKvm) {
        super(betingelser, Forsikring.BOLIGPREMIE, forsikringsBelop);
        this.gateAdresse = gateAdresse;
        this.boligType = boligType;
        this.byggeMateriale = byggeMateriale;
        this.standard = standard;
        this.postNr = postNr;
        this.byggAar = byggAar;
        this.antallKvm = antallKvm;
    }//end of constructor
    
    /**
     * 
     * @return gate adressen til forsikringen
     */
    public String getGateAdresse() {
        return gateAdresse;
    }//end of method getGateAdresse()
    
    /**
     * 
     * @return postnummeret til forsikringen
     */
    public String getPostNr() {
        return postNr;
    }//end of method getPostNr()
    
    /**
     * 
     * @return antall KVM for forsikringen
     */
    public int getAntallKvm() {
        return antallKvm;
    }//end of method getAntallKvm()
    
    /**
     * 
     * @return returnerer klassens datafelter som string.
     */
    @Override
    public String toString() {
        return super.toString() + 
               "\nGate adresse: " + gateAdresse +
               "\nPost nr: " + postNr + 
               "\nBolig type: " + boligType +
               "\nByggemateriale: " + byggeMateriale +
               "\nStandard: " + standard +
               "\nBygg år: " + byggAar +
               "\nKvadratmeter: " + antallKvm;
    }//end of method toString();
}//end of class Boligforsikring
