
package forsikringsprogram;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author Fredrik
 */
public class Kunderegister implements Serializable {
    
    private static final long serialVersionUID = 123L;
    private TreeSet<ForsikringsKunde> kunderegister;
    
    public Kunderegister(){
        // Redefinerer comparatoren til å sortere på etternavn
        SerialiserbarComparator<ForsikringsKunde> comparator = new SerialiserbarComparator<ForsikringsKunde>() {

            @Override
            public int compare(ForsikringsKunde f1, ForsikringsKunde f2) {
                return f1.getEtternavn().compareToIgnoreCase(f2.getEtternavn());
            }
        };
        
        kunderegister = new TreeSet<>(comparator); // Sorterer objektene med comparatoren vi sender med. 
    }// end of oonstructor
    
    
    //Kundebehandling 
    /* Setter inn en kunde i registeret. Returnerer false hvis IKKE objektet er ForsikringsKunde, eller hvis kunden ikke blir lagt til.
       Returnerer true hvis kunden blir lagt til. 
    */
    public boolean registrerKunde(ForsikringsKunde ny){
        if(ny == null)
            return false;
        return kunderegister.add(ny);
    }// end of method settInn(ForsikringsKunde)
    
    // Finner en kunde med fornavn,etternavn,fødselsnummer, og returnerer denne Kunden. Returnerer null kunden ikke finnes i registeret.
    public ForsikringsKunde finnKunde(String fodselsNr){
        
        Iterator<ForsikringsKunde> iterator = kunderegister.iterator();
        while(iterator.hasNext()){
            ForsikringsKunde gjeldendeKunde = iterator.next();
            if( fodselsNr.equalsIgnoreCase(gjeldendeKunde.getFodselsNr()) )
                return gjeldendeKunde;
        }// end of while
        return null;
    }// end of method finnKunde(String fornavn, String etternavn, String fodselsNr)
    
    /* Registrerer en skademelding på en kunde som har fødselsnummer lik fodselsNr. Returverdien indikerer om dette gikk eller ikke.
       Se SkademeldingsListe.registrerSkademelding  .*/
    public String registrerSkademelding(Skademelding skademelding, String fodselsNr){
        ForsikringsKunde kunde = finnKunde(fodselsNr);
        if(kunde == null)
            return "Det finnes ingen kunder med dette fødselsnummeret.";
        if(skademelding == null)
            return "Skademelding ikke opprettet";
        return kunde.registrerSkademelding(skademelding);
    }// end of method registrerSkademelding(Skademelding skademelding, String fodselsNr)
    
    /* Tegner/registrerer en forsikring på en kunde som har fødselsnummer lik parameteren fodselsNr. Returverdien indikerer om dette gikk eller ikke.
       Se Forsikringsliste.registrerForsikring()*/
    public String tegnForsikring(Forsikring ny, String fodselsNr){
        ForsikringsKunde kunde = finnKunde(fodselsNr);
        if(kunde == null)
            return "Det finnes ingen kunder med dette fødselsnummeret.";
        return kunde.registrerForsikring(ny);        
    }// end of method tegnForsikring
    
    //Sier opp en forsikring med gitt avtaleNr, på en gitt kunde med gitt fødselsnummer. Returverdien indikerer om hva som gikk galt, eller om alt gikk bra. 
    // Se ForsikringsKunde.siOppForsikring
    public String siOppForsikring(String fdnr, int avtaleNr){
        ForsikringsKunde kunden = finnKunde(fdnr);
        if(kunden == null)
            return "Det finnes ingen kunder med dette fødselsnummeret.";
        return kunden.siOppForsikring(avtaleNr);
    }// end of method siOppForsikring(fødselsnr, avtaleNr)
    
    @Override
    public String toString(){
        if(kunderegister.isEmpty())
            return null;
        Iterator<ForsikringsKunde> iterator = kunderegister.iterator();
        String utskrift = "";
        while(iterator.hasNext())
            utskrift += iterator.next().toString();
        return utskrift;
    }// end of method toString()
}// end of class Kundregister