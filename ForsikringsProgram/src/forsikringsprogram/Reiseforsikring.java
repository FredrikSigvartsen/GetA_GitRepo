//Elias

package forsikringsprogram;

//Reiseforsikrings-klasse

import java.io.Serializable;

public class Reiseforsikring extends Forsikring implements Serializable {
    
    private static final long serialVersionUID = 654L;
    private String omraade;
    
    //Konstruktør
    public Reiseforsikring(String betingelser, int forsikringsPremie, int forsikringsBelop, String omraade) {
        super(betingelser, forsikringsPremie, forsikringsBelop);
        this.omraade = omraade;
    }
    
    @Override
    public String toString() {
        return super.toString() + 
               "\nOmråde: " + this.omraade;
    }
}//end of class Reiseforsikring
