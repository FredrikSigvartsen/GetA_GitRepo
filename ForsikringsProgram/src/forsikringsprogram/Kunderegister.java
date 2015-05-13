package forsikringsprogram;
import java.io.Serializable;
import java.util.*;

/**
 * Et kunderegister som består av kunder som er av objekt ForsikringsKunde. En kunde har null eller flere skademeldinger og forsikringer. I denne klassen gjør man beregninger på statistikk,
 * inntekter/utgifter og behandling av kundene med deres forsikringer og skademeldinger. 
 * Hensikten med denne klassen er å gjøre registreringer og oppsigelser av kunder, oppslag av kunder, skademeldinger og forsikringer.
 * Hensikten er også å beregne inntekter og utgifter på de forskjellige kundene
 * Siste versjon skrevet: 12/05/15 12:00
 * @author Fredrik Aleksander Sigvartsen, Dataingeniør, s236356
 * @author Elias Andreassen Thøgersen, Informasjonsteknologi, s236603
 */
public class Kunderegister implements Serializable {
    
    private static final long serialVersionUID = 123L;
    private TreeSet<ForsikringsKunde> kunderegister;
    
    /**
     * Oppretter kunderegisteret, og sorterer først på etternavn, så fornavn, og så fødselsnummer. 
     */
    public Kunderegister(){
        // Redefinerer comparatoren til å sortere på etternavn
        SerialiserbarComparator<ForsikringsKunde> comparator = new SerialiserbarComparator<ForsikringsKunde>() {

            @Override
            public int compare(ForsikringsKunde f1, ForsikringsKunde f2) {
                int x = f1.getEtternavn().compareToIgnoreCase(f2.getEtternavn());
                if(x == 0) {
                    x = f1.getFornavn().compareToIgnoreCase(f2.getFornavn());
                    if(x == 0)
                        x = f1.getFodselsNr().compareToIgnoreCase(f2.getFodselsNr());
                }
                return x;
            }// end of overriding method compare()
        };// end of inner anonymous class
        kunderegister = new TreeSet<>(comparator); // Sorterer objektene med comparatoren vi sender med. 
    }// end of constructor
    
    /**
     * Registrerer en ny kunde. 
     * @param ny ForsikringsKunde som blir lagt til i systemet. 
     * @return En boolsk verdi som indikerer om kunden ble lagt til eller ikke. Kunden blir ikke lagt til hvis det allerede finnes et slikt objekt i kunderegisteret. 
     */
    public boolean registrerKunde(ForsikringsKunde ny){
        if(ny == null)
            return false;
        return kunderegister.add(ny);
    }// end of method settInn(ForsikringsKunde)
    
    
    /**
     * Finner en kunde med fødselsnummer lik parameteren.
     * @param fodselsNr fødselsnummeret til kunden brukeren vil finne i systemet.
     * @return Kunden metoden finner. Returnerer null hvis ikke kunden finnes.
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
     * @param etternavn på kunden vi vil finnet.
     * @return Kunden som er funnet. Hvis kunden ikke finnes, returnerer metoden null.
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
    } // end of method finnKunde(String fornavn, String etternavn)
    
    /**
     * Finner alle kunder fornavn og etternavn.
     * @param fornavn På kundene vi vil finne i registeret.
     * @param etternavn På kundene vi vil finne i registeret.
     * @return En liste med ForsikringsKunde'r med angitt fornavn og etternavn. Listen kan være tom. 
     */
    public List<ForsikringsKunde> finnKunderMedNavn(String fornavn, String etternavn){
        
        List<ForsikringsKunde> kundene = finnKunderMedFornavn(fornavn);
        List<ForsikringsKunde> kunderEtternavn = finnKunderMedEtternavn(etternavn);
        kundene.retainAll(kunderEtternavn);
        
        return kundene;
    } // end of method finnKunderMedNavn(fornavn, etternavns)
    
    /**
     * Finner alle kunder i registeret med fornavnet som blir angitt i parameterlisten. 
     * @param fornavn Fornavnet på de kundene som det skal søkes opp.
     * @return En liste med ForsikringsKunde'r med fornavn angitt i parameterlisten.
     */
    public List<ForsikringsKunde> finnKunderMedFornavn(String fornavn){
        List<ForsikringsKunde> kundene = new ArrayList<>();
        if( kunderegister.isEmpty() )
            return kundene;
        Iterator<ForsikringsKunde> kIter = kunderegister.iterator();
        while( kIter.hasNext() ){
            ForsikringsKunde gjeldende = kIter.next();
            if( fornavn.equalsIgnoreCase( gjeldende.getFornavn() ) )
                kundene.add(gjeldende);
        }// end of while
        return kundene;
    } // end of method finnKunderMedFornavn(String fornavn)
    
    /**
     * Finner alle kunder i registeret med etternavnet som blir angitt i parameterlisten. 
     * @param etternavn Etternavnet på de kundene som det skal søkes opp.
     * @return En liste med ForsikringsKunde'r med etternavn angitt i parameterlisten.
     */
    public List<ForsikringsKunde> finnKunderMedEtternavn(String etternavn){
        List<ForsikringsKunde> kundene = new ArrayList<>();
        if( kunderegister.isEmpty() )
            return kundene;
        Iterator<ForsikringsKunde> kIter = kunderegister.iterator();
        while( kIter.hasNext() ){
            ForsikringsKunde gjeldende = kIter.next();
            if( etternavn.equalsIgnoreCase( gjeldende.getEtternavn() ) )
                kundene.add(gjeldende);
        }// end of while
        return kundene;
    } // end of method finnKunderMedFornavn(String fornavn)
    
    /**
     * Finner en kunde i registeret som har en skademelding med gitt skademeldingsnummer. 
     * @param skadeNr Nummeret på den skademeldingen som er registrert på kunden.
     * @return ForsikringsKunde med denne skademeldingen.
     */
    public ForsikringsKunde finnKunde(int skadeNr){
        if(skadeNr < 1)
            return null;
        
        Iterator<ForsikringsKunde> iterator = kunderegister.iterator();
        while(iterator.hasNext()){
            ForsikringsKunde gjeldendeKunde = iterator.next();
            SkademeldingsListe kundensSkademeldinger = gjeldendeKunde.getSkademeldinger();
            if( !( kundensSkademeldinger.erTom() ) && !( kundensSkademeldinger.finnSkademeldinger(skadeNr) == null ) )
                return gjeldendeKunde; 
        }// end of while-løkke
        return null;
    }// end of method finnKunde(skadeNr)
    
    /**
     * Finner alle kunder med en gitt forsikringstype.
     * @param forsikringstype Metoden finner alle kunder som har en forsikrng av type forsikringstype.
     * @return En liste med ForsikringsKunde'r. Listen er tom hvis det ikke finnes noen kunder med denne forsikringstypen.
     */
    public List<ForsikringsKunde> finnKunder( String forsikringstype ){
        Iterator<ForsikringsKunde> kIter = kunderegister.iterator();
        List<ForsikringsKunde> kundeListe = new ArrayList<>();
        
        while(kIter.hasNext()) {
            ForsikringsKunde kunde = kIter.next();
            if( !( kunde.getForsikringer().erTom() ) && kunde.getForsikringer().harRiktigForsikring(forsikringstype)) {
                kundeListe.add(kunde);
            }
        }// end of while
        return kundeListe;
    }// end of method finnKunder( forsikringstype )
    
    /**
     * Finner en kunde som har en forsikring med gitt avtaleNr.
     * @param avtaleNr Nummeret på forsikringen kunden vi søker etter er eier av.
     * @return Funnet ForsikringsKunde, null hvis ikke funnet.
     */
    public ForsikringsKunde finnKundeMedAvtaleNr( int avtaleNr){
        Iterator<ForsikringsKunde> kIter = kunderegister.iterator();
        
        while( kIter.hasNext() ){
            ForsikringsKunde gjeldendeKunde = kIter.next();
            if( !( gjeldendeKunde.getForsikringer().erTom() ) && 
                    gjeldendeKunde.getForsikringer().finnForsikringer(avtaleNr) != null)
                return gjeldendeKunde;
        }// end of while
        return null;
    }// end of method finnKundeMedAvtaleNr( int avtaleNr)
    
    /**
     * Registrerer en skademelding på en kunde som har fødselsnummer lik den andre parameteren. 
     * @param skademelding vi vil registrere.
     * @param fodselsNr på kunden vi vil registrere skademeldingen på. 
     * @return En tekststreng som indikerer hva som gikk galt/bra under registreringen. 
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
     * Metoden finner alle skademeldinger av gitt parameter.
     * @param skadetype Hvilken skadetype man vil finne i registeret. 
     * @return En liste med skademeldingen av skadetype.
     */
    public List<Skademelding> finnSkademeldinger( String skadetype ){
        Iterator<ForsikringsKunde> kIter = kunderegister.iterator();
        List<Skademelding> nyListe = new ArrayList<>();
        
        while(kIter.hasNext()) {
            SkademeldingsListe gjeldendeKundesSkademeldinger = kIter.next().getSkademeldinger();
            if(  !( gjeldendeKundesSkademeldinger.erTom() ) && ! (gjeldendeKundesSkademeldinger.listeMedSkademeldingAvType(skadetype).isEmpty()) ) {
                nyListe.addAll(gjeldendeKundesSkademeldinger.listeMedSkademeldingAvType(skadetype));
            }
        }// end of while
        return nyListe;
    }// end of method finnSkademeldinger(skadetype)
    
    /**
     * Finner alle skademeldingene innenfor det gitte tidsintervallet som blir angitt i paramaterlisten.
     * @param min Startdato for tidsintervallet det søkes i.
     * @param max Sluttdato for tidsintervallet det søkes i.
     * @return En liste med alle skademelding mellom datoene min og max.
     */
    public List<Skademelding> finnSkademeldinger( Calendar min, Calendar max){
        Iterator<ForsikringsKunde> kIter = kunderegister.iterator();
        List<Skademelding> nyListe = new ArrayList<>();
        
        while(kIter.hasNext()) {
            ForsikringsKunde kunde = kIter.next();
            if( ! ( kunde.getSkademeldinger().erTom() ) && ! (kunde.getSkademeldinger().finnSkademeldinger(min, max).isEmpty() ))
                nyListe.addAll(kunde.getSkademeldinger().finnSkademeldinger(min, max));
        }// end of while
        return nyListe;
    }// end of method finnSkademeldinger( Calendar min, Calendar max)
    
    /**
     * Finner alle skademeldingene innenfor det gitte tidsintervallet, og som er av type skademeldingsType.
     * @param min Startdato for tidsintervallet det søkes i.
     * @param max Sluttdato for tidsintervallet det søkes i.
     * @param skademeldingsType Hvilken type skademeldinger som skal søkes på.
     * @return En liste med skademeldinger. 
     */
    public List<Skademelding> finnSkademeldinger( Calendar min, Calendar max, String skademeldingsType){
        List<Skademelding> skadeliste = finnSkademeldinger( skademeldingsType );
        List<Skademelding> datoliste = finnSkademeldinger( min, max );
        if( skadeliste.retainAll(datoliste))
            return skadeliste;
        return skadeliste;
    }// end of method finnSkademeldinger( Calendar min, Calendar max, String skademeldingsType)
    
    
    /**
     * Tegner/registrerer en forsikring på en kunde som har fødselsnummer lik parameteren fodselsnummer.
       Se Forsikringsliste.registrerForsikring()
     * @param ny Forsikring som skal tegnes.
     * @param fodselsnummer Til kunden som forsikringen skal tegnes på.
     * @return En String verdi som indikerer en tekst om hva som gikk galt/greit.
     */
    public String tegnForsikring(Forsikring ny, String fodselsnummer){
        ForsikringsKunde kunde = finnKunde(fodselsnummer);
        if(kunde == null)
            return "Det finnes ingen kunder med dette fødselsnummeret.";
        return kunde.registrerForsikring(ny);        
    }// end of method tegnForsikring()
    
    /**
     * Sier opp en forsikring med gitt avtaleNr, på en gitt kunde med gitt fødselsnummer.
     * @param fodselsNr Fødselsnummeret til kunden forsikringen er tegnet på.
     * @param avtaleNr Avtalenummeret på forsikringen som skal sies opp.
     * @return En tekst-streng som tilsier hva som gikk bra/galt.
     */
    public String siOppForsikring(String fodselsNr, int avtaleNr){
        ForsikringsKunde kunden = finnKunde(fodselsNr);
        if(kunden == null)
            return "Det finnes ingen kunder med dette fødselsnummeret.";
        return kunden.siOppForsikring(avtaleNr);
    }// end of method siOppForsikring(fødselsnr, avtaleNr)
    
    /**
     * Finner en forsikring med gitt avtalenummer
     * @param avtaleNr Hver forsikring har et unikt avtalenummer, metoden finner en forsikring med gitt avtalenummer.
     * @return Forsikring som er funnet. 
     */
    public Forsikring finnForsikringer( int avtaleNr){
        if( avtaleNr < 1)
            return null;
        Iterator<ForsikringsKunde> kIter = kunderegister.iterator();
        
        while(kIter.hasNext()){
            Forsikringsliste kundensForsikringer = kIter.next().getForsikringer();
            Forsikring gjeldendeForsikring = kundensForsikringer.finnForsikringer(avtaleNr);
            if( !( kundensForsikringer.erTom()) && gjeldendeForsikring != null)
                return gjeldendeForsikring;
        }// end of while
        return null;
    }// end of method finnForsikringer(avtaleNr)
    
    /**
     * Finner alle forsikringer i registeret av typen som blir sendt med i parameteren.
     * @param forsikringstype Hvilken forsikringstype man vil finne forsikringer av. 
     * @return En liste med forsikringer av type gitt i parameteren. 
     */
    public List<Forsikring> finnForsikringer( String forsikringstype ){
        List<Forsikring> forsikringerAvType = new ArrayList<>();
        
        if(kunderegister.isEmpty())
            return forsikringerAvType;
        Iterator<ForsikringsKunde> kIter = kunderegister.iterator();
        while( kIter.hasNext() ){
            Forsikringsliste gjeldendeForsikringer = kIter.next().getForsikringer();
            if( !( gjeldendeForsikringer.erTom() )){
                List<Forsikring> kundensForsikringerAvType = gjeldendeForsikringer.listeMedForsikringAvType(forsikringstype);
                ListIterator<Forsikring> fIter = kundensForsikringerAvType.listIterator();
                forsikringerAvType.addAll( kundensForsikringerAvType );
            }// end of if
        }// end of while
        return forsikringerAvType;
    }// end of method finnForsikringer( skadetype )
    
    //Slutt på kundebehandlingmetoder
    
    
    
    //Inntekt metoder:
    
    
    /**
     * Finner selskapets totale utbetaling av erstatninger i løpet av et år.
     * @return Summen av selskapets totale utbetaling ila et år. 
     */
    public double alleUtbetalteErstatninger() {
        Iterator<ForsikringsKunde> iter = kunderegister.iterator();
        double sum = 0;
        while(iter.hasNext()){
            ForsikringsKunde gjeldendeKunde = iter.next();
            sum += gjeldendeKunde.getUtbetalteErstatninger();
        }// end of while
        return sum;
    }// end of method alleUtbetalteErstatninger()
    
    /**
     * Finner selskapets totale utbetaling av erstatninger for en gitt forsikringstype i løpet av et år.
     * @param forsikringstype Forsikringstypen man vil finne summen av utbetaling av erstatninger for.
     * @return Summen av ersatningene for de gitte skademeldingene av forsikringstype. 
     */
    public double utbetaltErstatningAvType(String forsikringstype) {
        Iterator<ForsikringsKunde> kIter = kunderegister.iterator();
        List<Skademelding> smListe = new ArrayList<>();
        double sum = 0;
        while(kIter.hasNext()){
            ForsikringsKunde gjeldendeKunde = kIter.next();
            if( ! (gjeldendeKunde.getSkademeldinger().erTom() ) ) {
                smListe = gjeldendeKunde.getSkademeldinger().listeMedSkademeldingAvType(forsikringstype);
                ListIterator<Skademelding> sIter = smListe.listIterator();
                while(sIter.hasNext()) {
                    sum += sIter.next().getErstatningsbelop();
                }// end of inner while
            } // end of if
        }// end of outter while
        return sum;
    }// end of method utbetaltErstatningAvType(forsikringstype)
    
    /**
     * Finner selskapets utbetaling til en gitt forsikringskunde i løpet av kundeforholdet.
     * @param fodselsNr Fødselsnummeret til kunden man vil finne utbetalingene for.
     * @return Summen av utbetalingene. Returnerer -1 hvis kunden ikke er funnet. 
     */
    public double utbetalingTilKunde(String fodselsNr) {
        ForsikringsKunde kunde = finnKunde(fodselsNr);
        if(kunde == null)
            return -1; //Fant ikke kunden
        return kunde.getUtbetalteErstatninger();
    }// end of method utbetalingTilKunde()
    
    /**
     * Finner selskapets totale forsikringspremieinntekter i løpet av et år.
     * @return Den totale årlige inntekten.
     */
    public double aarligInntekt() {
        Iterator<ForsikringsKunde> iter = kunderegister.iterator();
        double sum = 0;
        while(iter.hasNext()){
            ForsikringsKunde gjeldendeKunde = iter.next();
            sum += gjeldendeKunde.getAarligUtbetaling();
        }
        return sum;
    }// end of method aarligInntekt()
    
    /**
     * Finner selskapets totale forsikringspremieinntekter for en gitt forsikringstype i løpet av et år.
     * @param forsikringstype Metoden finner inntekter for angitt forsikringstype.
     * @return Inntekten fra gitt forsikringstype.
     */
    public double inntektFraForsikringstype(String forsikringstype) {
        Iterator<ForsikringsKunde> kIter = kunderegister.iterator();
        List<Forsikring> fListe = new ArrayList<>();
        double sum = 0;
        while(kIter.hasNext()){
            ForsikringsKunde gjeldendeKunde = kIter.next();
            if( ! (gjeldendeKunde.getForsikringer().erTom() ) ) {
                fListe = gjeldendeKunde.getForsikringer().listeMedForsikringAvType(forsikringstype);
                ListIterator<Forsikring> fIter = fListe.listIterator();
                while(fIter.hasNext()) {
                    sum += fIter.next().getForsikringsPremie();
                }
            }// end of outter if
        }// end of while
        return sum;
    }// end of method inntektFraForsikringstype(String forsikringstype)
    
    /**
     * Returner all inntekt registrert på en kunde.
     * @param fodselsNr Fødselsnummeret på kunden man vil finne inntekten til.
     * @return Inntekten til en kunde. Returnerer -1 hvis kunden ikke er funnet.
     */
    public double inntektFraKunde(String fodselsNr) {
        ForsikringsKunde kunde = finnKunde(fodselsNr);
        if(kunde == null)
            return -1; //Fant ikke kunden
        return kunde.getAarligUtbetaling();
    }// end of method inntektFraKunde()
    
    // Slutt på inntekt/utgift - metoder
    
    
    //Statistikk-metoder: 
    /**
     * Teller opp antall aktive forsikringer av gitt type, i tidsrommet mellom min og max.
     * @param forsikringstype Hvilken forsikringstype man vil finne antall av. 
     * @param min Startdato for tidsintervallet det søkes i.
     * @param max Sluttdato for tidsintervallet det søkes i.
     * @return Returnerer (int)antall forsikringer av gitt type.
     */
    public int antallForsikringAvType(String forsikringstype, Calendar min, Calendar max) {
        Iterator<ForsikringsKunde> kIter = kunderegister.iterator();
        int sum = 0;
        
        while(kIter.hasNext()) {
            ForsikringsKunde kunde = kIter.next();
            if(kunde.getForsikringer() != null && kunde.getForsikringer().listeMedForsikringAvType(forsikringstype) != null) {
                List<Forsikring> liste = kunde.getForsikringer().listeMedForsikringAvType(forsikringstype);
                Iterator<Forsikring> fIter = liste.iterator();
                while(fIter.hasNext()) {
                    Forsikring forsikring = fIter.next();
                    if(forsikring.isActive()) {
                        Calendar dato = forsikring.getOpprettelsesDato();
                        dato.clear(Calendar.HOUR);
                        dato.clear(Calendar.HOUR_OF_DAY);
                        dato.clear(Calendar.MINUTE);
                        dato.clear(Calendar.SECOND);
                        dato.clear(Calendar.MILLISECOND);
                        if(!dato.before(min) && !dato.after(max)) {
                                sum++;
                        }//end of second inner if
                    }//end of first inner if
                }//end of inner while
            }//end of outer if
        }//end of outer while
        return sum;
    }//end of method antallForsikringAvType(String forsikringstype, Calendar min, Calendar max)
    
    /**
     * Teller opp antall skademeldinger av gitt type, i tidsrommet mellom min og max.
     * @param skadeType Hvilken skadetype man vil finne antall av.
     * @param min Startdato for tidsintervallet det søkes i.
     * @param max Sluttdato for tidsintervallet det søkes i.
     * @return Returnerer antall skademeldinger av gitt type.
     */
    public int antallSkademeldingAvType(String skadeType, Calendar min, Calendar max) {
        Iterator<ForsikringsKunde> kIter = kunderegister.iterator();
        int sum = 0;
        
        while(kIter.hasNext()) {
            ForsikringsKunde kunde = kIter.next();
            if(kunde.getSkademeldinger() != null && kunde.getSkademeldinger().listeMedSkademeldingAvType(skadeType) != null) {
                List<Skademelding> liste = kunde.getSkademeldinger().listeMedSkademeldingAvType(skadeType);
                Iterator<Skademelding> sIter = liste.iterator();
                while(sIter.hasNext()) {
                    Skademelding skademelding = sIter.next();
                    Calendar dato = skademelding.getOpprettelsesDato();
                    dato.clear(Calendar.HOUR);
                    dato.clear(Calendar.HOUR_OF_DAY);
                    dato.clear(Calendar.MINUTE);
                    dato.clear(Calendar.SECOND);
                    dato.clear(Calendar.MILLISECOND);
                    if(!dato.before(min) && !dato.after(max)) {
                            sum++;
                    }//end of inner if
                }//end of inner while
            }//end of outer if
        }//end of outer while
        return sum;
    }//end of method antallSkademeldingAvType(String skadeType, Calendar min, Calendar max)
    
    /**
     * Teller opp antall forsikringer på gitt dato.
     * @param dato Hvilken dato man vil finne antall registrerte forsikringer.
     * @return Returnerer antallet.
     */
    public int antallForsikringerPaaDato(Date dato) {
        Calendar calendarDato = Calendar.getInstance();
        calendarDato.setTime(dato);
        
        Iterator<ForsikringsKunde> kIter = kunderegister.iterator();
        int sum = 0;
        
        while(kIter.hasNext()) {
            ForsikringsKunde kunde = kIter.next();
            if(kunde.getForsikringer() != null) {
                List<Forsikring> liste = kunde.getForsikringer().getListe();
                Iterator<Forsikring> fIter = liste.iterator();
                while(fIter.hasNext()) {
                    Forsikring forsikring = fIter.next();
                    Calendar fDato = forsikring.getOpprettelsesDato();
                    if(calendarDato.get(Calendar.YEAR) == fDato.get(Calendar.YEAR)
                       && calendarDato.get(Calendar.DAY_OF_YEAR) == fDato.get(Calendar.DAY_OF_YEAR)) {
                            sum++;
                    }//end of inner if
                }//end of inner while
            }//end of outer if
        }//end of outer while
        return sum;
    }//end of method antallForsikringerPaaDato(Date dato)
    
    /**
     * Teller opp antall forsikringer på gitt dato, med gitt type.
     * @param dato Hvilken dato man vil finne antall registrerte forsikringer.
     * @param forsikringstype Hvilken type forsikring man vil ha antall av.
     * @return Returnerer antallet. 
     */
    public int antallForsikringerPaaDatoMedType(Date dato, String forsikringstype) {
        Calendar calendarDato = Calendar.getInstance();
        calendarDato.setTime(dato);
        
        Iterator<ForsikringsKunde> kIter = kunderegister.iterator();
        int sum = 0;
        
        while(kIter.hasNext()) {
            ForsikringsKunde kunde = kIter.next();
            if( !( kunde.getForsikringer().erTom() ) && ! ( kunde.getForsikringer().listeMedForsikringAvType(forsikringstype).isEmpty() ) ) {
                List<Forsikring> liste = kunde.getForsikringer().listeMedForsikringAvType(forsikringstype);
                Iterator<Forsikring> fIter = liste.iterator();
                while(fIter.hasNext()) {
                    Forsikring forsikring = fIter.next();
                    Calendar fDato = forsikring.getOpprettelsesDato();
                    if(calendarDato.get(Calendar.YEAR) == fDato.get(Calendar.YEAR)
                       && calendarDato.get(Calendar.DAY_OF_YEAR) == fDato.get(Calendar.DAY_OF_YEAR)) {
                            sum++;
                    }//end of inner if  
                }//end of inner while
            }//end of outer if
        }//end of outer while
        return sum;
    }//end of method antallForsikringerPaaDatoMedType(Date dato, String forsikringstype)
    
    /**
     * Teller opp antall skademeldinger på gitt dato.
     * @param dato Hvilken dato man vil finne antall registrerte skademeldinger.
     * @return Returnerer antallet.
     */
    public int antallSkademeldingerPaaDato(Date dato) {
        Calendar calendarDato = Calendar.getInstance();
        calendarDato.setTime(dato);
        
        Iterator<ForsikringsKunde> kIter = kunderegister.iterator();
        int sum = 0;
        
        while(kIter.hasNext()) {
            ForsikringsKunde kunde = kIter.next();
            if(kunde.getForsikringer() != null) {
                List<Skademelding> liste = kunde.getSkademeldinger().getListe();
                Iterator<Skademelding> sIter = liste.iterator();
                while(sIter.hasNext()) {
                    Skademelding skademelding = sIter.next();
                    Calendar fDato = skademelding.getOpprettelsesDato();
                    if(calendarDato.get(Calendar.YEAR) == fDato.get(Calendar.YEAR)
                       && calendarDato.get(Calendar.DAY_OF_YEAR) == fDato.get(Calendar.DAY_OF_YEAR)) {
                            sum++;
                    }//end of inner if  
                }//end of inner while
            }//end of outer if
        }//end of outer while
        return sum;
    }//end of method antallSkademeldingerPaaDato(Date dato)
    
    /**
     * Teller opp antall skademeldinger på gitt dato, med gitt type.
     * @param dato Hvilken dato man vil finne antall registrerte skademeldinger.
     * @param skadeType Hvilken type skade man vil finne antallet av.
     * @return Returnerer antallet.
     */
    public int antallSkademeldingerPaaDatoMedType(Date dato, String skadeType) {
        Calendar calendarDato = Calendar.getInstance();
        calendarDato.setTime(dato);
        
        Iterator<ForsikringsKunde> kIter = kunderegister.iterator();
        int sum = 0;
        
        while(kIter.hasNext()) {
            ForsikringsKunde kunde = kIter.next();
            if(kunde.getSkademeldinger() != null && kunde.getSkademeldinger().listeMedSkademeldingAvType(skadeType) != null) {
                List<Skademelding> liste = kunde.getSkademeldinger().listeMedSkademeldingAvType(skadeType);
                Iterator<Skademelding> sIter = liste.iterator();
                while(sIter.hasNext()) {
                    Skademelding skademelding = sIter.next();
                    Calendar fDato = skademelding.getOpprettelsesDato();
                    if(calendarDato.get(Calendar.YEAR) == fDato.get(Calendar.YEAR)
                       && calendarDato.get(Calendar.DAY_OF_YEAR) == fDato.get(Calendar.DAY_OF_YEAR)) {
                            sum++;
                    }//end of inner if
                }//end of inner while
            }//end of outer if
        }//end og outer while
        return sum;
    }//end of method antallSkademeldingerPaaDatoMedType(Date dato, String skadeType)
    
    /**
     * Teller opp antall kunder registrert på gitt dato.
     * @param dato Hvilken dato man vil finne antall registrerte kunder.
     * @return Returnerer antallet.
     */
    public int antallKunderPaaDato(Date dato) {
        Calendar calendarDato = Calendar.getInstance();
        calendarDato.setTime(dato);
        
        Iterator<ForsikringsKunde> kIter = kunderegister.iterator();
        int sum = 0;
        
        while(kIter.hasNext()) {
            ForsikringsKunde kunde = kIter.next();
            Calendar fDato = kunde.getStartDato();
            if(calendarDato.get(Calendar.YEAR) == fDato.get(Calendar.YEAR)
               && calendarDato.get(Calendar.DAY_OF_YEAR) == fDato.get(Calendar.DAY_OF_YEAR)) {
                    sum++;
            }//end of if
        }//end of while
        return sum;
    }//end of method antallKunderPaaDato(Date dato)
    
    /**
     * Regner sammen utgifter på gitt dato.
     * @param dato Hvilken dato man vil finne utgifter.
     * @return Returnerer summen.
     */
    public int utgifterPaaDato(Date dato) {
        Calendar calendarDato = Calendar.getInstance();
        calendarDato.setTime(dato);
        
        Iterator<ForsikringsKunde> kIter = kunderegister.iterator();
        int sum = 0;
        
        while(kIter.hasNext()) {
            ForsikringsKunde kunde = kIter.next();
            if(kunde.getSkademeldinger() != null) {
                List<Skademelding> liste = kunde.getSkademeldinger().getListe();
                Iterator<Skademelding> sIter = liste.iterator();
                while(sIter.hasNext()) {
                    Skademelding skademelding = sIter.next();
                    Calendar fDato = skademelding.getOpprettelsesDato();
                    if(calendarDato.get(Calendar.YEAR) == fDato.get(Calendar.YEAR)
                       && calendarDato.get(Calendar.DAY_OF_YEAR) == fDato.get(Calendar.DAY_OF_YEAR)) {
                            sum += (int) skademelding.getErstatningsbelop();
                    }//end of inner if
                }//end of inner while
            }//end of outer if
        }//end of outer while
        return sum;
    }//end of method utgifterPaaDato(Date dato)
    
    /**
     * Regner ut utgiftene for en gitt skadetype på en gitt dato.
     * @param dato Hvilken dato man vil finne utgifter.
     * @param skadeType Spesifiserer hvilken type skade man vil rege sammen utgifter av.
     * @return Returnerer summen.
     */
    public int utgifterPaaDatoMedType(Date dato, String skadeType) {
        Calendar calendarDato = Calendar.getInstance();
        calendarDato.setTime(dato);
        Iterator<ForsikringsKunde> kIter = kunderegister.iterator();
        int sum = 0;
        
        while(kIter.hasNext()) {
            ForsikringsKunde kunde = kIter.next();
            if(kunde.getSkademeldinger() != null && kunde.getSkademeldinger().listeMedSkademeldingAvType(skadeType) != null) {
                List<Skademelding> liste = kunde.getSkademeldinger().listeMedSkademeldingAvType(skadeType);
                Iterator<Skademelding> sIter = liste.iterator();
                while(sIter.hasNext()) {
                    Skademelding skademelding = sIter.next();
                    Calendar fDato = skademelding.getOpprettelsesDato();
                    if(calendarDato.get(Calendar.YEAR) == fDato.get(Calendar.YEAR)
                       && calendarDato.get(Calendar.DAY_OF_YEAR) == fDato.get(Calendar.DAY_OF_YEAR)) {
                            sum += (int) skademelding.getErstatningsbelop();
                    }//end of inner if  
                }//end of inner while
            }//end of outer if  
        }//end of outer while
        return sum;
    }//end of method utgifterPaaDatoMedType(Date dato, String skadeType)
    
    /**
     * 
     * @return En tekststreng for alle kundene i hele registeret. Altså en samling av alle kundenes opplysninger. 
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
