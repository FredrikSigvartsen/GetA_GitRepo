//Elias

package forsikringsprogram;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Klassen lagrer forsikrings-objekter i en ArrayList.
 * @author Elias
 */
public class Forsikringsliste implements Serializable {
    
    private static final long serialVersionUID = 234L;
    private List<Forsikring> liste;
    
    /**
     * Initialiserer listen.
     */
    public Forsikringsliste(){
        liste = new ArrayList<>();
    }
    
    /**
     * Sjekker om listen med forsikringer har en eller flere forsikringer.
     * @return En boolsk verdi som tilsier om listen er tom eller ikke.
     */
    public boolean erTom(){
        return liste.isEmpty();
    }//end of method erTom()
    
    /*Setter inn en forsikring i listen. Returnerer false hvis man sender med et object som er null, for å unngå tomme plasser i ArrayList'en,
      eller om det ble returnert false i listen sin add-metode. Returnerer også false hvis forsikringen allerede er tegnet. Returnerer true hvis forsikringen ble lagt til i listen. 
    */
    public boolean registrerForsikring(Forsikring f) {
        if(f == null)
            return false;
        return liste.add(f);
    }// end of method settInn(forsikringer)
    
    /**
     * Metoden tar imot en forsikring og opphører den.
     * @param f forsikringen som skal opphøres.
     */
    public void opphorForsikring(Forsikring f) {
        f.opphorForsikring();
    }
    
    /**
     * Sjekker om listen inneholder en aktiv-forsikring.
     * @return returnerer true hvis listen inneholder en aktiv forsikring, ellers false.
     */
    public boolean harAktiveForsikringer() {
        ListIterator<Forsikring> iter = liste.listIterator();
        boolean ok = false;
        
        while(iter.hasNext()) {
            if(iter.next().getAktivForsikring())
                ok = true;
        }
        return ok;
    }//end of method harAktiveForsikringer()
    
    /**
     * Finner alle forsikringer i registeret av typen som blir sendt med i parameteren.
     * @param konstant Hvilken forsikringstype man vil finne forsikringer av. 
     * @return En liste med forsikringer av type gitt av parameteren. 
     */
    public List<Forsikring> listeMedForsikringAvType(String konstant) {
        ListIterator<Forsikring> iter = liste.listIterator();
        List<Forsikring> ny = new ArrayList<>();
        
        switch (konstant) {
            case Forsikring.BAAT:
                while(iter.hasNext()) {
                    Forsikring forsikring = iter.next();
                    if(forsikring instanceof Baatforsikring) {
                        ny.add(forsikring);
                    }
                }//end of while    
                break;
            case Forsikring.REISE:
                while(iter.hasNext()) {
                    Forsikring forsikring = iter.next();
                    if(forsikring instanceof Reiseforsikring) {
                        ny.add(forsikring);
                    }
                }//end of while    
                break;
            case Forsikring.BOLIG:
                while(iter.hasNext()) {
                    Forsikring forsikring = iter.next();
                    if(forsikring instanceof Boligforsikring) {
                        ny.add(forsikring);
                    }
                }//end of while    
                break;
            case Forsikring.BIL:
                while(iter.hasNext()) {
                    Forsikring forsikring = iter.next();
                    if(forsikring instanceof Bilforsikring) {
                        ny.add(forsikring);
                    }
                }//end of while   
                break;
        }//end of switch-case
        return ny;
    }//end of listeMedForsikringAvType(String konstant)
    
    /**
     * For å sjekke om kunden har en type forsikring ( I forhold til erstatnings-utbetaling ).
     * @param konstant Typen forsikring det skal sjekke på.
     * @return returnerer true hvis listen inneholder riktig forsikring, ellers false.
     */
    public boolean harRiktigForsikring(String konstant) {
        ListIterator<Forsikring> iter = liste.listIterator();
        
        switch (konstant) {
            case Forsikring.BAAT:
                while(iter.hasNext())
                    if(iter.next() instanceof Baatforsikring)
                        return true;
                break;
            case Forsikring.REISE:
                while(iter.hasNext())
                    if(iter.next() instanceof Reiseforsikring)
                        return true;
                break;
            case Forsikring.BIL:
                while(iter.hasNext())
                    if(iter.next() instanceof Bilforsikring)
                        return true;
                break;
            case Forsikring.BOLIG:
                while(iter.hasNext())
                    if(iter.next() instanceof Boligforsikring)
                        return true;
                break;
        }// end of switch-case
        return false;
    }//end of method inneholderObjektAvType(String konstant)
    
    /**
     * Brukes for å sjekke om en kunde kvalifiserer til å bli totalkunde.
     * @return returnerer true hvis listen inneholder objekter av tre forskjellige forsikringer, ellers false.
     */
    public boolean innholderTreForskjelligeForsikringstyper() {
        ListIterator<Forsikring> iter = liste.listIterator();
        int antBaat = 0;
        int antReise = 0;
        int antBil = 0;
        int antBolig = 0;
        
        while(iter.hasNext()) {
            Forsikring f = iter.next();
            if(f instanceof Baatforsikring && antBaat == 0)
                antBaat++;
            if(f instanceof Reiseforsikring && antReise == 0)
                antReise++;
            if(f instanceof Bilforsikring && antBil == 0)
                antBil++;
            if(f instanceof Boligforsikring && antBolig == 0)
                antBolig++;
        }//end of while
        return (antBaat + antReise + antBil + antBolig) >= 3;       
    }//end of innholderTreForskjelligeForsikringstyper()
    
    /**
     * Finner en forsikring med gitt avtalenummer
     * @param avtaleNr Hver forsikring har et unikt avtalenummer, metoden finner en forsikring med gitt avtalenummer.
     * @return Forsikring som er funnet. 
     */
    public Forsikring finnForsikringer(int avtaleNr){
        ListIterator<Forsikring> iter = liste.listIterator();
        
        if(avtaleNr < 1)
            return null;
        while(iter.hasNext()){
            Forsikring returForsikring = iter.next();
            if( returForsikring.getAvtaleNr() == avtaleNr)
                return returForsikring;
        }// end of while
        return null;
    }// end of method finnForsikringer(avataleNr)
    
    /**
     * @return returnerer listen med forsikringer. 
     */
    public List<Forsikring> getListe() {
        return this.liste;
    }
    
    public List<Forsikring> getAktiveForsikringer() {
        ListIterator<Forsikring> iter = liste.listIterator();
        List<Forsikring> ny = new ArrayList<>();
        while(iter.hasNext()) {
            Forsikring løper = iter.next();
            if(løper.getAktivForsikring()) {
                ny.add(løper);
            }
        }
        return ny;
    }
    
    /**
     * @return returnerer alle forsikringene i listen sin toString(). 
     */
    @Override
    public String toString() {
        ListIterator<Forsikring> iter = liste.listIterator();
        String output = "";
        
        while(iter.hasNext()) {
            output += iter.next().toString();
            if(iter.hasNext()) 
                output += "\n";
        }//end of while
        return output;
    }// end of method toString()
}//end of class Forsikringsliste
