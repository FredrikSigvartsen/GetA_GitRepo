//Elias

package forsikringsprogram;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//Klassen innholder en ArrayList med forsikrings-objekter
public class Forsikringsliste {
    
    private List<Forsikring> liste = new ArrayList<>();
    private Iterator<Forsikring> iter;
    
    public Forsikringsliste(){
        liste = new ArrayList<>();
    }
    
    /*Setter inn en forsikring i listen. Returnerer false hvis man sender med et object som er null, for å unngå tomme plasser i ArrayList'en,
      eller om det ble returnert false i listen sin add-metode. Returnerer også false hvis forsikringen allerede er tegnet. Returnerer true hvis forsikringen ble lagt til i listen. 
    */
    public boolean registrerForsikring(Forsikring f) {
        if(f == null)
            return false;
        return liste.add(f);
    }// end of method settInn(forsikringer)
    
    public void opphorForsikring(Forsikring f) {
        f.opphorForsikring();
    }
    
    //For å sjekke om kunden har en type forsikring
    public boolean inneholderObjektAvType(int konstant) {
        iter = liste.listIterator();
        
        if(konstant == Forsikring.BAAT) {
            while(iter.hasNext()) 
                if(iter.next() instanceof Baatforsikring)
                    return true;
        } else if(konstant == Forsikring.REISE) {
            while(iter.hasNext())
                if(iter.next() instanceof Reiseforsikring)
                    return true;
        } else if(konstant == Forsikring.BIL) {
            while(iter.hasNext())
                if(iter.next() instanceof Bilforsikring)
                    return true;
        } else if(konstant == Forsikring.BOLIG) {
            while(iter.hasNext())
                if(iter.next() instanceof Boligforsikring)
                    return true;
        }
        return false;
    }//end of method inneholderObjektAvType(konstant)
    
    /*Finner en forsikring med avtalenummer lik det tallet man sender med metoden. Returnerer forsikringen hvis forsikringen finnes, returnerer false hvis det ikke finnes,
      eller om nummeret man sender med metoden er lavere enn de avtalenumrene som finnes. 
    */
    public Forsikring finnForsikringer(int avtaleNr){
        iter = liste.listIterator();
        Forsikring returForsikring = null;
        
        if(avtaleNr < 1)
            return null;
        
        while(iter.hasNext()){
            returForsikring = iter.next();
            if( returForsikring.getAvtaleNr() == avtaleNr)
                return returForsikring;
        }// end of while
        return returForsikring;
    }// end of method finnForsikringer(avataleNr)
    
    @Override
    public String toString() {
        iter = liste.listIterator();
        String output = "";
        
        while(iter.hasNext()) {
            output += iter.next().toString();
            if(iter.hasNext()) {
                output += "\n";
                for(int i = 0; i < 82; i++)
                    output += "-";
                output += "\n";
            }
        }//end of while
        return output;
    }// end of method toString()
}//end of class Forsikringsliste