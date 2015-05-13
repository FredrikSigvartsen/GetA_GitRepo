//Elias

package forsikringsprogram;

//Reiseforsikrings-klasse

import java.io.Serializable;

public class Reiseforsikring extends Forsikring implements Serializable {
    
    private static final long serialVersionUID = 654L;
    private String omraade;
    
    //Konstruktør
    public Reiseforsikring(String betingelser, double forsikringsBelop, String omraade) {
        super(betingelser, Forsikring.REISEPREMIE, forsikringsBelop);
        this.omraade = omraade;
    }

    public String getOmraade() {
        return omraade;
    }
    
    @Override
    public String toString() {
        return super.toString() + 
               "\n Område: " + this.omraade;
    }
}//end of class Reiseforsikring
