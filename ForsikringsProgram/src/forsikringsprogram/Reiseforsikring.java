//Elias

package forsikringsprogram;

import java.io.Serializable;

/**
 * Klassen skal lagrer data som er unike for reiseforsikringer.
 * Resten av konstruktør-parameterene sendes til superklassen Forsikring sin konstruktør.
 * @author Elias
 */
public class Reiseforsikring extends Forsikring implements Serializable {
    
    private static final long serialVersionUID = 654L;
    private String omraade;
    
    /**
     * Initialiserer datafeltene.
     * @param betingelser for forsikringen.
     * @param forsikringsBelop Hvor mye reisen skal forsikres for.
     * @param omraade omraade forsikringen gjelder.
     */
    public Reiseforsikring(String betingelser, double forsikringsBelop, String omraade) {
        super(betingelser, Forsikring.REISEPREMIE, forsikringsBelop);
        this.omraade = omraade;
    }//end of constructor
    
    /**
     * 
     * @return Omradet forsikringen gjelder for
     */
    public String getOmraade() {
        return omraade;
    }//end of method getOmraade()
    
    /**
     * 
     * @return Alle datafelter som string.
     */
    @Override
    public String toString() {
        return super.toString() + 
               "\n Område: " + omraade;
    }//end of method toString()
}//end of class Reiseforsikring