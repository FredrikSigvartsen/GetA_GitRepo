//Elias

package forsikringsprogram;

//Båtforsikring-klasse

import java.io.Serializable;

public class Baatforsikring extends Forsikring implements Serializable{
    
    private static final long serialVersionUID = 345L;
    private String registreringsNr;
    private int aarsmodell;
    private int motorstyrke;
    private String merke;
    private String modell;
    private String motortype;
    
    //Konstruktør
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
    }
    
    @Override
    public String toString() {
        return super.toString() + 
               "\nRegistrerings nr: " + this.registreringsNr + 
               "\nÅrsmodell: " + this.aarsmodell +
               "\nMotorstyrke: " + this.motorstyrke + " hk" +
               "\nMerke: " + this.merke + 
               "\nModell: " + this.modell +
               "\nMotortype: " + this.motortype;
    }
}//end of class Baatforsikring
