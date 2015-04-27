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
    
    /**
     * Registrerer en ny kunde. 
     * @param ny ForsikringsKunde som blir lagt til i systemet. 
     * @return indikerer om kunden ble lagt til eller ikke. Kunden blir ikke lagt til hvis det allerede finnes et slikt objekt. 
     */
    public boolean registrerKunde(ForsikringsKunde ny){
        if(ny == null)
            return false;
        return kunderegister.add(ny);
    }// end of method settInn(ForsikringsKunde)
    
    
    /**
     * Finner en kunde med fødselsnummer lik parameteren.
     * @param fodselsNr fødselsnummeret til kunden brukeren vil finne i systemet.
     * @return kunden metoden finner. 
     */
    public ForsikringsKunde finnKunde(String fodselsNr){
        
        Iterator<ForsikringsKunde> iterator = kunderegister.iterator();
        while(iterator.hasNext()){
            ForsikringsKunde gjeldendeKunde = iterator.next();
            if( fodselsNr.equalsIgnoreCase(gjeldendeKunde.getFodselsNr()) )
                return gjeldendeKunde;
        }// end of while
        return null;
    }// end of method finnKunde(String fornavn, String etternavn, String fodselsNr)
    
    /**
     * Søker opp en kunde lik parametrene fornavn og etternavn. 
     * @param fornavn på kunden vi vil finne.
     * @param etternavn på kunden vi vil finnet
     * @return kunden vi har funnet. Hvis kunden ikke finnes, returnerer metoden null.
     */
    public ForsikringsKunde finnKunde(String fornavn, String etternavn){
       Iterator<ForsikringsKunde> iterator = kunderegister.iterator();
       while(iterator.hasNext()){
           ForsikringsKunde gjeldendeKunde = iterator.next();
           if( fornavn.equalsIgnoreCase( gjeldendeKunde.getFornavn()) &&
               etternavn.equalsIgnoreCase( gjeldendeKunde.getEtternavn()))
               return gjeldendeKunde;
       }// end of while
       return null;
    } // finnKunde(String fornavn, String etternavn)
    
    
    /**
     * Registrerer en skademelding på en kunde som har fødselsnummer lik den andre parameteren. 
     * @param skademelding vi vil registrere
     * @param fodselsNr på kunden vi vil registrere skademeldingen på. 
     * @return indikerer hva som gikk galt under registreringen. 
     */
    public String registrerSkademelding(Skademelding skademelding, String fodselsNr){
        ForsikringsKunde kunde = finnKunde(fodselsNr);
        if(kunde == null)
            return "Det finnes ingen kunder med dette fødselsnummeret.";
        if(skademelding == null)
            return "Skademelding ikke opprettet";
        return kunde.registrerSkademelding(skademelding);
    }// end of method registrerSkademelding(Skademelding skademelding, String fodselsNr)
    
    /** 
     * Finner en skademelding med skadenummer lik parameteren. 
     * @param skadeNr på skademeldingen brukeren til programmet vil søke opp.
     * @return skademeldingen som er funnet. 
     */
    public Skademelding finnSkademeldinger(int skadeNr){
        Iterator<ForsikringsKunde> iterator = kunderegister.iterator();
        
        while( iterator.hasNext() ){
            SkademeldingsListe gjeldendeListe = iterator.next().getSkademeldinger();
            if( gjeldendeListe != null 
               && gjeldendeListe.finnSkademeldinger(skadeNr) != null)
                return gjeldendeListe.finnSkademeldinger(skadeNr);
        }// end of while
        return null;
    }// end of method finnSkademeldinger(int skadeNr)
    
    
    /**
     * 
     * @param skadetype
     * @return 
     */
    public List<Skademelding> finnSkademeldinger( String skadetype ){
        return null;
    }// end of method finnSkademeldinger(skadetype)
    
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
    
    /**
     * 
     * @param forsikringstype
     * @return 
     */
    public List<Forsikring> finnForsikringer( String forsikringstype ){
        return null;
    }// end of method finnForsikringer( forsikringstype )
    
    /**
     * 
     * @return 
     */
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
}// end of class