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
    }

    public String getGateAdresse() {
        return gateAdresse;
    }

    public String getPostNr() {
        return postNr;
    }

    public int getAntallKvm() {
        return antallKvm;
    }
    
    @Override
    public String toString() {
        return super.toString() + 
               "\n Gate adresse: " + this.gateAdresse +
               "\n Post nr: " + this.postNr + 
               "\n Bolig type: " + this.boligType +
               "\n Byggemateriale: " + this.byggeMateriale +
               "\n Standard: " + this.standard +
               "\n Bygg år: " + this.byggAar +
               "\n Kvadratmeter: " + this.antallKvm;
    }
}//end of class Boligforsikring
