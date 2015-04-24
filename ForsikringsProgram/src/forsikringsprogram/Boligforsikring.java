//Elias

package forsikringsprogram;

//Boligforsikrings-klasse

import java.io.Serializable;

public class Boligforsikring extends Forsikring implements Serializable {
    
    private static final long serialVersionUID = 456L;
    private String gateAdresse;
    private String boligType;
    private String byggeMateriale;
    private String standard;
    private String postNr;
    private int byggAar;
    private int antallKvm;
    
    //Konstruktør
    public Boligforsikring(String betingelser, int forsikringsPremie, int forsikringsBelop,
                           String gateAdresse, String boligType, String byggeMateriale, 
                           String standard, String postNr, int byggAar, int antallKvm) {
        super(betingelser, forsikringsPremie, forsikringsBelop);
        this.gateAdresse = gateAdresse;
        this.boligType = boligType;
        this.byggeMateriale = byggeMateriale;
        this.standard = standard;
        this.postNr = postNr;
        this.byggAar = byggAar;
        this.antallKvm = antallKvm;
    }
    
    @Override
    public String toString() {
        return super.toString() + 
               "\nGate adresse: " + this.gateAdresse +
               "\nPost nr: " + this.postNr + 
               "\nBolig type: " + this.boligType +
               "\nByggemateriale: " + this.byggeMateriale +
               "\nStandard: " + this.standard +
               "\nBygg år: " + this.byggAar +
               "\nKvadratmeter: " + this.antallKvm;
    }
}//end of class Boligforsikring
