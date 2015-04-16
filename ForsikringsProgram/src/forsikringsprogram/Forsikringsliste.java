//Elias

package forsikringsprogram;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//Klassen innholder en ArrayList med forsikrings-objekter
public class Forsikringsliste {
    
    private List<Forsikring> liste = new ArrayList<>();
    private Iterator<Forsikring> iter;
    
    public void settInn(Forsikring f) {
        liste.add(f);
    }
    
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
    }
    
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
        }
        return output;
    }
}//end of class Forsikringsliste
