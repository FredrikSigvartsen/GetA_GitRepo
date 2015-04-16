//Elias

package forsikringsprogram;

//Reiseforsikrings-klasse
public class Reiseforsikring extends Forsikring {
    
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
