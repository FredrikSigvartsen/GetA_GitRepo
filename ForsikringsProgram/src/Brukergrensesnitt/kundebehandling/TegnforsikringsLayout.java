package Brukergrensesnitt.kundebehandling;
import forsikringsprogram.*;
import Brukergrensesnitt.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * Denne klassen er et layout for registrering av forsikringer. Her fyller brukeren inn 
 * angitte inputs og dette valideres, før det registreres som forsikringer i kunderegisteret.
 * Layout'et avhenger av hva brukeren velger i comboboxen. Velger brukeren boligforsikring i forsikringstype, kommer det inputs for registrering av boligforsikring,
 * Velger brukeren reise, kommer det inputs for registrering av reise, velger brukeren bil dukker det opp inputs for registrering av bilforsikring
 * Velger brukeren båtforsikring, dukker det opp inputs for registrering av båtforsikring. 
 * Siste versjon skrevet: 13/05/15 16:00
 * @author Jens Omfjord, Informasjonsteknologi, s236641
 */
public class TegnforsikringsLayout extends GridPane{
    
    private static final String BOLIG_BYGGMATERIELL_REGEX = "^[A-ZÆØÅ][a-zA-Z æøåÆØÅ\\.\\,\\-/]*$";
    private TextField fodselsnr, forsikringsbelop, registreringsnr, merke, 
            modell, registreringsar, kjorelengde, prisPerKm, batRegistreringsnr,
            batMerke, batModell, arsmodell, motorStyrke, gateAdresse, postnr, 
            byggear,byggemateriale, antallKVM, omrade;    
    private Label betingelserLabel, registreringsnrLabel, merkeLabel, modellLabel,
            registreringsarLabel, kjorelengdeLabel, prisPerKmLabel, 
            batRegistreringsnrLabel, batMerkeLabel, batModellLabel, arsmodellLabel,
            motorTypeLabel,motorStyrkeLabel, gateAdresseLabel, postnrLabel, 
            byggearLabel, boligTypeLabel, byggematerialeLabel, boligStandardLabel,
            antallKVMLabel, omradeLabel, fodselsnrFeil, forsikringsbelopFeil,
            registreringsnrFeil, merkeFeil, modellFeil, registreringsarFeil, 
            kjorelengdeFeil, prisPerKmFeil, batRegistreringsnrFeil, batMerkeFeil, 
            batModellFeil, arsmodellFeil, motorStyrkeFeil, gateAdresseFeil, 
            postnrFeil, byggearFeil, byggematerialeFeil, antallKVMFeil, omradeFeil;
    private Button tegnForsikring;
    private ComboBox forsikringsType, boligType, boligStandard, motorType, 
            betingelserBil, betingelserBat, betingelserBolig, betingelserReise;
    private Kunderegister kundeRegister;
    private String forsikringsTypeString;
    
    /**
     * 
     * @param register Kunderegisteret alle forsikringene skal lagres i. 
     */
    public TegnforsikringsLayout(Kunderegister register){
        opprettTegnForsikringsSkjema();
        tekstFeltLyttere();
        comboBoxLytter();
        tegnForsikringLytter();
        this.kundeRegister = register;
    }//end of constructor
    
    //Håndtering av felles felter
    
    /**
     * Oppretter de felles input feltene i skjema for tegning av forsikring
     * og kaller på metodene som oppretter de inputfeltene som er unike for 
     * hver enkelt forsikring
     */
    private void opprettTegnForsikringsSkjema(){
        fjernUnikeFelter();
        
        
        tegnForsikring = new Button("Tegn forsikring");
        
        
        fodselsnr = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        fodselsnrFeil = new Label("*");
        
        forsikringsType = new ComboBox();
        ObservableList<String> forsikringer = FXCollections.observableArrayList(
                                              "Bilforsikring", "Båtforsikring",
                                              "Boligforsikring", "Reiseforsikring");
        forsikringsType.setItems(forsikringer);
        
        forsikringsbelop = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        forsikringsbelopFeil = new Label("*");
        
        setVgap(10);
        setHgap(10);
        
        //legger til kolonne 1
        add(KundePane.overskrift("Tegn forsikring", 20), 1, 1, 2, 1);
        add(new Label("Fødselsnummer:"), 1, 2);
        add(new Label("Forsikrings type:"), 1, 3);
        add(new Label("Forsikringsbeløp:"), 1, 4);
        setHalignment(tegnForsikring, HPos.CENTER);
        add(tegnForsikring, 1, 5, 2, 1);
        
        //legger til kolonne 2
        add(fodselsnr, 2, 2);
        add(forsikringsType, 2, 3);
        add(forsikringsbelop, 2, 4);
        
        //legger til kolonne 3
        add(fodselsnrFeil, 3, 2, 3, 1);
        add(forsikringsbelopFeil, 3, 4, 4, 1);
    }//end of method opprettTegnForsikringsSkjema()
    
    /**
     * Sjekker om feltene i layoutet er tomme, og gir brukeren en melding om hva som må fylles inn.
     * @return Returnerer true om alle feltene er fylt inn, og false om noe mangler
     */
    private boolean sjekkTommeFelter(){
        if( fodselsnr.getText().trim().isEmpty()){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn fødselsnummer");
            return false;
        }
        
        else if( forsikringsbelop.getText().isEmpty()){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn forsikringsbeløp");
            return false;
        }
        
        switch (forsikringsTypeString){
            case "bilforsikring":
                return erBilFelterTomme();
                
            case "batforsikring":
                return sjekkTommeBatFelter();
                
            case "boligforsikring":
                return sjekkTommeBoligFelter();
                
            case "reiseforsikring":
                return sjekkTommeReiseFelter();
                
        }//end of switch
        return false;
    }// end of method sjekkTommeFelter()
    
    /**
     * Sjekker input fra brukeren opp mot RegEx og gir umidelbar tilbakemelding på om inputen godkjennes eller evt hva som må endres
     * @return Returnerer true om alle feltene godkjennes av regexen
     */
    private boolean tekstFeltLyttere(){
        fodselsnr.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(fodselsnrFeil, nyverdi, "Skriv inn et eksisterende fødselsnummer (11 siffer)", null);
        });
        
        forsikringsbelop.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(forsikringsbelopFeil, nyverdi, "Skriv inn kun tall (2-7 siffer)", GUI.FORSIKRINGSBELOP_REGEX);
        });
        
        return fodselsnrFeil.getText().isEmpty() && forsikringsbelopFeil.getText().isEmpty();
    }//end of method tekstFeltLyttere()
    
    /**
     * Sjekker alle innputfeltene, og registrerer en forsikring av valgt type
     */
    private void registrerForsikring(){
        if(!sjekkTommeFelter())
                return;//end of if
        try{
            switch (forsikringsTypeString){
                case "bilforsikring":
                    registrerBilforsikring();
                    blirTotalKunde();
                    setTommeFellesFelter();
                    setTommeBilFelter();
                    break;
                case "batforsikring":
                    registrerBatforsikring();
                    blirTotalKunde();
                    setTommeFellesFelter();
                    setTommeBatFelter();
                    break;
                case "boligforsikring":
                    registrerBoligforsikring();
                    blirTotalKunde();
                    setTommeFellesFelter();
                    setTommeBoligFelter();
                    break;
                case "reiseforsikring":
                    registrerReiseforsikring();
                    blirTotalKunde();
                    setTommeFellesFelter();
                    setTommeReiseFelter();
                    break;
            }//end of switch
        }//end of try
        catch(NumberFormatException | NullPointerException e){
            GUI.visProgramFeilMelding(e);
            return;
        }//end of catch
    }//end of method registrerForsikring()
    
    /**
     * Legger til en lytter til forsikringsType comboboxen, og legger til de
     * korrekte inputfeltene for den forsikringstypen
     */
    private void comboBoxLytter(){
        forsikringsType.valueProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                switch ((String) t1) {
                    case "Bilforsikring":
                        opprettBilforsikringsFelter();
                        forsikringsTypeString = "bilforsikring";
                        break;
                    case "Båtforsikring":
                        opprettBatforsikringsFelter();
                        forsikringsTypeString = "batforsikring";
                        break;
                    case "Boligforsikring":
                        opprettBoligforsikringsFelter();
                        forsikringsTypeString = "boligforsikring";
                        break;
                    case "Reiseforsikring":
                        opprettReiseforsikringsFelter();
                        forsikringsTypeString = "reiseforsikring";
                        break;
                    default:
                        opprettBilforsikringsFelter();
                        forsikringsTypeString = "default";
                }//end of switch
            }
        });//end of inner anonymous class
    }//end of method comboLytter()
    
    /**
     * Legger til en lytter på tegnForsikring knappen
     */
    private void tegnForsikringLytter(){
        tegnForsikring.setOnAction((ActionEvent event) -> {
            registrerForsikring();
        });
    }//end of method tengForsikringsLytter()
    
    
    
    //Håndtering av bil felter
    
    /**
     * Oppretter de feltene som er unike for bilforsikring
     */
    private void opprettBilforsikringsFelter(){
        
        fjernUnikeFelter();
        
        betingelserBil = new ComboBox();
        ObservableList<String> betingelserBilListe = FXCollections.observableArrayList(
                                              "Super", "Kasko", "Delkasko", "Ansvar");
        betingelserBil.setItems(betingelserBilListe);
        betingelserLabel = new Label("Betingelser:");
        
        registreringsnr = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        registreringsnrLabel = new Label("Registreringsnr:");
        registreringsnrFeil = new Label("*");
        
        merke = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        merkeLabel = new Label("Merke:");
        merkeFeil = new Label("*");
                
        modell = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        modellLabel = new Label("Modell:");
        modellFeil = new Label("*");
                
        registreringsar = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        registreringsarLabel = new Label("Registreringsår:");
        registreringsarFeil = new Label("*");
                
        kjorelengde = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        kjorelengdeLabel = new Label("Kjørelengde:");
        kjorelengdeFeil = new Label("*");
                
        prisPerKm = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        prisPerKmLabel = new Label("Pris per KM:");
        prisPerKmFeil = new Label("*");
        
       
        //legger til kolonne 1
        add(betingelserLabel, 1, 5);
        add(registreringsnrLabel, 1, 6);
        add(merkeLabel, 1, 7);
        add(modellLabel, 1, 8);
        add(registreringsarLabel, 1, 9);
        add(kjorelengdeLabel, 1, 10);
        add(prisPerKmLabel, 1, 11);
        setRowIndex(tegnForsikring, 12);
        
        //legger til kolonn 2
        add(betingelserBil, 2, 5);
        add(registreringsnr, 2, 6);
        add(merke, 2, 7);
        add(modell, 2, 8);
        add(registreringsar, 2, 9);
        add(kjorelengde, 2, 10);
        add(prisPerKm, 2, 11);
        
        //legger til kolonne 3
        add(registreringsnrFeil, 3, 6);
        add(merkeFeil, 3, 7);
        add(modellFeil, 3, 8);
        add(registreringsarFeil, 3, 9);
        add(kjorelengdeFeil, 3, 10);
        add(prisPerKmFeil, 3, 11);
        
        bilTekstFeltLyttere();
    }//End of method opprettBilforsikringFelter()
    
    /**
     * Registrerer en bilforsikring når metoden kalles
     */
    private void registrerBilforsikring(){
        String fodselsnrInput = fodselsnr.getText().trim();
        String betingelserInput = (String) betingelserBil.getValue();
        String registreringsnrInput = registreringsnr.getText().trim();
        String merkeInput = merke.getText().trim();
        String modellInput = modell.getText().trim();
        double forsikringsbelopInput = Double.parseDouble(forsikringsbelop.getText().trim());
        int registreringsarInput = Integer.parseInt(registreringsar.getText().trim());
        int kjorelengdeInput = Integer.parseInt(kjorelengde.getText().trim());
        int prisPerKmInput = Integer.parseInt(prisPerKm.getText().trim());
        Bilforsikring bilforsikring = new Bilforsikring(betingelserInput, forsikringsbelopInput,
                                               registreringsnrInput, merkeInput, modellInput, registreringsarInput, kjorelengdeInput, prisPerKmInput);
        GUI.visInputFeilMelding("Ny forsikring registrert", kundeRegister.tegnForsikring(bilforsikring, fodselsnrInput));
    }//end of method registrerBilforsikring()
    
    /**
     * 
     * @return hvorvidt  bil tekstfeltene er tomme
     */
    private boolean erBilFelterTomme(){
        if( betingelserBil.getValue() == null){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn betingelser");
            return false;
        }

        else if( registreringsnr.getText().trim().isEmpty()){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn registreringsnr");
            return false;
        }
        else if( merke.getText().isEmpty()){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn merke");
            return false;
        }
        else if( modell.getText().isEmpty()){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn modell");
            return false;
        }
        else if( registreringsar.getText().isEmpty()){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn registreringsår");
            return false;
        }
        else if( kjorelengde.getText().isEmpty()){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn kjørelengde");
            return false;
        }
        else if( prisPerKm.getText().isEmpty()){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn pris per km");
            return false;
        }
        else if(!tekstFeltLyttere() || !bilTekstFeltLyttere()){
            GUI.visInputFeilMelding("Feil innskrivning", "Venligs fyll feltene på riktig format");
            return false;
        }
        return true;
    }//end of method erBilFelterTomme()
    
    /**
     * Sjekker bilinput fra brukeren opp mot RegEx og gir umidelbar tilbakemelding på om inputen godkjennes eller evt hva som må endres
     * @return Returnerer true om alle feltene godkjennes av regexen
     */
    private boolean bilTekstFeltLyttere(){
        registreringsnr.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(registreringsnrFeil, nyverdi, "Skriv inn et gyldig registreringsnr (AB12345)", GUI.REGISTRERINGSNR_REGEX);
        });

        merke.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(merkeFeil, nyverdi, "Skriv inn kun bokstaver, med stor forbokstav", GUI.NAVN_REGEX);
        });

        modell.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(modellFeil, nyverdi, "Skriv inn bokstaver eller tall", GUI.MODELL_REGEX);
        });

        registreringsar.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(registreringsarFeil, nyverdi, "Skriv inn et gyldig årstall med 4 tall", GUI.POSTNR_REGEX);
        });

        kjorelengde.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(kjorelengdeFeil, nyverdi, "Skriv inn kun tall", GUI.FORSIKRINGSBELOP_REGEX);
        });

        prisPerKm.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(prisPerKmFeil, nyverdi, "Skriv inn kun tall", GUI.AVTALENR_REGEX);
        });
        return registreringsnrFeil.getText().isEmpty() && merkeFeil.getText().isEmpty() && modellFeil.getText().isEmpty() && 
               registreringsarFeil.getText().isEmpty() && kjorelengdeFeil.getText().isEmpty() && prisPerKmFeil.getText().isEmpty();
    }//end of method bilTekstFeltLyttere()
    
    /**
     * Tømmer bilfeltene for tekst og setter RegEx Labelene til *
     */
    private void setTommeBilFelter(){
        betingelserBil.setValue(null);
        registreringsnr.clear();
        merke.clear();
        modell.clear();
        registreringsar.clear();
        kjorelengde.clear();
        prisPerKm.clear();
        
        registreringsnrFeil.setText("*");
        merkeFeil.setText("*");
        modellFeil.setText("*");
        registreringsarFeil.setText("*");
        kjorelengdeFeil.setText("*");
        prisPerKmFeil.setText("*");
    }//end of method setTommeBilFelter()
    
    
    
    //Håndtering av båt felter
    
    /**
     * Oppretter de feltene som er unike for båtforsikring
     */
    private void opprettBatforsikringsFelter(){
        
        fjernUnikeFelter();
        
        betingelserBat = new ComboBox();
        ObservableList<String> betingelserBatListe = FXCollections.observableArrayList(
                                              "Super", "Kasko", "Delkasko");
        betingelserBat.setItems(betingelserBatListe);
        betingelserLabel = new Label("Betingelser:");
        
        batRegistreringsnr = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        batRegistreringsnrLabel = new Label("Registreringsnr:");
        batRegistreringsnrFeil = new Label("*");
        
        batMerke = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        batMerkeLabel = new Label("Merke:");
        batMerkeFeil = new Label("*");
                
        batModell = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        batModellLabel = new Label("Modell:");
        batModellFeil = new Label("*");
                
        arsmodell = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        arsmodellLabel = new Label("Årsmodell:");
        arsmodellFeil = new Label("*");
                
        motorType = new ComboBox();
        ObservableList<String> motortyper = FXCollections.observableArrayList(
                                              "Utenbords", "Innenbords",
                                              "Seil");
        motorType.setItems(motortyper);
        motorTypeLabel = new Label("Motor type:");
                
        motorStyrke = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        motorStyrkeLabel = new Label("Motorkraft:");
        motorStyrkeFeil = new Label("*");
        
        //legger til kolonne 1
        add(betingelserLabel, 1, 5);
        add(batRegistreringsnrLabel, 1, 6);
        add(batMerkeLabel, 1, 7);
        add(batModellLabel, 1, 8);
        add(arsmodellLabel, 1, 9);
        add(motorTypeLabel, 1, 10);
        add(motorStyrkeLabel, 1, 11);
        setRowIndex(tegnForsikring, 12);
        
        //legger til kolonne 2
        add(betingelserBat, 2, 5);
        add(batRegistreringsnr, 2, 6);
        add(batMerke, 2, 7);
        add(batModell, 2, 8);
        add(arsmodell, 2, 9);
        add(motorType, 2, 10);
        add(motorStyrke, 2, 11);
        
        //legger til kolonne 3
        add(batRegistreringsnrFeil, 3, 6);
        add(batMerkeFeil, 3, 7);
        add(batModellFeil, 3, 8);
        add(arsmodellFeil, 3, 9);
        add(motorStyrkeFeil, 3, 11);
        
        batTekstFeltLyttere();
    }//End of method opprettBatforsikringFelter()
    
    /**
     * Registrerer en båtforsikring når metoden kalles
     */
    private void registrerBatforsikring(){
        String fodselsnrInput = fodselsnr.getText().trim();
        String betingelserInput = (String) betingelserBat.getValue();
        String batRegistreringsnrInput = batRegistreringsnr.getText().trim();
        String batMerkeInput = batMerke.getText().trim();
        String batModellInput = batModell.getText().trim();
        String motorTypeInput = (String) motorType.getValue();
        double forsikringsbelopInput = Double.parseDouble(forsikringsbelop.getText().trim());
        int arsmodellInput = Integer.parseInt(arsmodell.getText().trim());
        int motorStyrkeInput = Integer.parseInt(motorStyrke.getText().trim());
        Baatforsikring batforsikring = new Baatforsikring(betingelserInput, forsikringsbelopInput,
                                        batRegistreringsnrInput, arsmodellInput, motorStyrkeInput, batMerkeInput, batModellInput, motorTypeInput);
        GUI.visInputFeilMelding("Ny forsikring registrert", kundeRegister.tegnForsikring(batforsikring, fodselsnrInput));
    }//end of method registrerBatforsikring()
    
    /**
     * 
     * @return hvorvidt  båt tekstfeltene er tomme
     */
    private boolean sjekkTommeBatFelter(){
        if( betingelserBat.getValue() == null){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn betingelser");
            return false;
        }
        else if( batRegistreringsnr.getText().trim().isEmpty()){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn registreringsnr");
            return false;
        }
        else if( batMerke.getText().isEmpty()){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn merke");
            return false;
        }
        else if( batModell.getText().isEmpty()){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn modell");
            return false;
        }
        else if( arsmodell.getText().isEmpty()){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn årsmodell");
            return false;
        }
        else if( motorType.getValue() == null){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn motortype");
            return false;
        }
        else if( motorStyrke.getText().isEmpty()){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn motorstyrke");
            return false;
        }
        else if(!tekstFeltLyttere() || !batTekstFeltLyttere()){
            GUI.visInputFeilMelding("Feil innskrivning", "Venligs fyll feltene på riktig format");
            return false;
        }
        return true;
    }//end of method sjekkTommeBatFelter()
    
    /**
     * Sjekker båtinput fra brukeren opp mot RegEx og gir umidelbar tilbakemelding på om inputen godkjennes eller evt hva som må endres
     * @return Returnerer true om alle feltene godkjennes av regexen
     */
    private boolean batTekstFeltLyttere(){
        batRegistreringsnr.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(batRegistreringsnrFeil, nyverdi, "Skriv inn et gyldig registreringsnr (ABC123)", GUI.BATREGISTRERINGSNR_REGEX);
        });

        batMerke.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(batMerkeFeil, nyverdi, "Skriv inn kun bokstaver, med stor forbokstav", GUI.NAVN_REGEX);
        });

        batModell.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(batModellFeil, nyverdi, "Skriv inn kun bokstaver, med stor forbokstav", GUI.MODELL_REGEX);
        });

        arsmodell.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(arsmodellFeil, nyverdi, "Skriv inn et gyldig årstall med 4 tall", GUI.POSTNR_REGEX);
        });

        motorStyrke.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(motorStyrkeFeil, nyverdi, "Skriv inn kun tall", GUI.FORSIKRINGSBELOP_REGEX);
        });
        return batRegistreringsnrFeil.getText().isEmpty() && batMerkeFeil.getText().isEmpty() && batModellFeil.getText().isEmpty() && 
               arsmodellFeil.getText().isEmpty() && motorStyrkeFeil.getText().isEmpty();
    }//end of method batTekstFeltLyttere()
    
    /**
     * Tømmer båtfeltene for tekst og setter RegEx Labelene til *
     */
    private void setTommeBatFelter(){
        betingelserBat.setValue(null);
        batRegistreringsnr.clear();
        batMerke.clear();
        batModell.clear();
        arsmodell.clear();
        motorType.setValue(null);
        motorStyrke.clear();
        
        batRegistreringsnrFeil.setText("*");
        batMerkeFeil.setText("*");
        batModellFeil.setText("*");
        arsmodellFeil.setText("*");
        motorStyrkeFeil.setText("*");
    }//end of method setTommeBatFelter()
    
    
    
    //Håndtering av bolig felter
    
    /**
     * Oppretter de feltene som er unike for boligforsikring
     */
    private void opprettBoligforsikringsFelter(){
        
        fjernUnikeFelter();
        
        betingelserBolig = new ComboBox();
        ObservableList<String> betingelserBoligListe = FXCollections.observableArrayList(
                                              "Super", "Standard");
        betingelserBolig.setItems(betingelserBoligListe);
        betingelserLabel = new Label("Betingelser:");
        
        gateAdresse = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        gateAdresseLabel = new Label("Adresse:");
        gateAdresseFeil = new Label("*");
        
        postnr = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        postnrLabel = new Label("Postnr:");
        postnrFeil = new Label("*");
                
        byggear = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        byggearLabel = new Label("Byggeår:");
        byggearFeil = new Label("*");
                
        boligType = new ComboBox();
        ObservableList<String> boligtyper = FXCollections.observableArrayList(
                                              "Enebolig", "Tomannsbolig",
                                              "Rekkehus", "Leilighet");
        boligType.setItems(boligtyper);
        boligTypeLabel = new Label("Bolig type:");
                
        byggemateriale = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        byggematerialeLabel = new Label("Byggemateriale:");
        byggematerialeFeil = new Label("*");
                
        boligStandard = new ComboBox();
        ObservableList<String> standarder = FXCollections.observableArrayList(
                                              "Dårlig", "Middels",
                                              "God");
        boligStandard.setItems(standarder);
        boligStandardLabel = new Label("Standard:");
        
        antallKVM = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        antallKVMLabel = new Label("Antall KVM:");
        antallKVMFeil = new Label("*");
        
        //legger til kolonne 1
        add(betingelserLabel, 1, 5);
        add(gateAdresseLabel, 1, 6);
        add(postnrLabel, 1, 7);
        add(byggearLabel, 1, 8);
        add(boligTypeLabel, 1, 9);
        add(byggematerialeLabel, 1, 10);
        add(boligStandardLabel, 1, 11);
        add(antallKVMLabel, 1, 12);
        setRowIndex(tegnForsikring, 13);
        
        //legger til kolonne 2
        add(betingelserBolig, 2, 5);
        add(gateAdresse, 2, 6);
        add(postnr, 2, 7);
        add(byggear, 2, 8);
        add(boligType, 2, 9);
        add(byggemateriale, 2, 10);
        add(boligStandard, 2, 11);
        add(antallKVM, 2, 12);
        
        //legger til kolonne 3
        add(gateAdresseFeil, 3, 6);
        add(postnrFeil, 3, 7);
        add(byggearFeil, 3, 8);
        add(byggematerialeFeil, 3, 10);
        add(antallKVMFeil, 3, 12);
        
        boligTekstFeltLyttere();
    }//End of method opprettVoligforsikringFelter()
    
    /**
     * Registrerer en boligforsikring når metoden kalles
     */
    private void registrerBoligforsikring(){
        String fodselsnrInput = fodselsnr.getText().trim();
        String betingelserInput = (String) betingelserBolig.getValue();
        String gateAdresseInput = gateAdresse.getText().trim();
        String boligTypeInput = (String) boligType.getValue();
        String byggematerialeInput = byggemateriale.getText().trim();
        String standardInput = (String) boligStandard.getValue();
        String postnrInput = postnr.getText().trim();
        double forsikringsbelopInput = Double.parseDouble(forsikringsbelop.getText().trim());
        int byggearInput = Integer.parseInt(byggear.getText().trim());
        int antallKVMInput = Integer.parseInt(antallKVM.getText().trim());
        Boligforsikring boligforsikring = new Boligforsikring(betingelserInput, forsikringsbelopInput,
                gateAdresseInput, boligTypeInput, byggematerialeInput, standardInput, postnrInput, byggearInput, antallKVMInput);
        GUI.visInputFeilMelding("Ny forsikring registrert", kundeRegister.tegnForsikring(boligforsikring, fodselsnrInput));
    }//end of method registrerBoligforsikring()
    
    /**
     * 
     * @return hvorvidt  bolig tekstfeltene er tomme
     */
    private boolean sjekkTommeBoligFelter(){if( betingelserBolig.getValue() == null){
        GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn betingelser");
            return false;
        }
        else if( gateAdresse.getText().trim().isEmpty()){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn registreringsnr");
            return false;
        }
        else if( boligType.getValue() == null){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn merke");
            return false;
        }
        else if( byggemateriale.getText().isEmpty()){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn modell");
            return false;
        }
        else if( boligStandard.getValue() == null){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn registreringsår");
            return false;
        }
        else if( postnr.getText().isEmpty()){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn kjørelengde");
            return false;
        }
        else if( byggear.getText().isEmpty()){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn byggeår");
            return false;
        }
        else if( antallKVM.getText().isEmpty()){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn antall KVM");
            return false;
        }
        else if(!tekstFeltLyttere() || !boligTekstFeltLyttere()){
            GUI.visInputFeilMelding("Feil innskrivning", "Venligs fyll feltene på riktig format");
            return false;
        }
        return true;
    }//end of method sjekkTommeBoligFelter()
    
    /**
     * Sjekker boliginput fra brukeren opp mot RegEx og gir umidelbar tilbakemelding på om inputen godkjennes eller evt hva som må endres
     * @return Returnerer true om alle feltene godkjennes av regexen
     */
    private boolean boligTekstFeltLyttere(){
        gateAdresse.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(gateAdresseFeil, nyverdi, "Skriv inn kun bokstaver og tall", GUI.ADRESSE_REGEX);
        });

        byggemateriale.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(byggematerialeFeil, nyverdi, "Skriv inn kun bokstaver, med stor forbokstav", BOLIG_BYGGMATERIELL_REGEX);
        });

        postnr.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(postnrFeil, nyverdi, "Skriv inn fire tall", GUI.POSTNR_REGEX);
        });

        byggear.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(byggearFeil, nyverdi, "Skriv inn et gyldig årstall med 4 tall", GUI.POSTNR_REGEX);
        });

        antallKVM.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(antallKVMFeil, nyverdi, "Skriv inn kun tall", GUI.FORSIKRINGSBELOP_REGEX);
        });
        return gateAdresseFeil.getText().isEmpty() && byggematerialeFeil.getText().isEmpty() && postnrFeil.getText().isEmpty() && 
               byggearFeil.getText().isEmpty() && antallKVMFeil.getText().isEmpty();
    }//end of method boligTekstFeltLyttere()
    
    /**
     * Tømmer boligfeltene for tekst og setter RegEx Labelene til *
     */
    private void setTommeBoligFelter(){
        betingelserBolig.setValue(null);
        gateAdresse.clear();
        postnr.clear();
        byggear.clear();
        boligType.setValue(null);
        byggemateriale.clear();
        boligStandard.setValue(null);
        antallKVM.clear();
        
        gateAdresseFeil.setText("*");
        postnrFeil.setText("*");
        byggearFeil.setText("*");
        byggematerialeFeil.setText("*");
        antallKVMFeil.setText("*");
    }//end of method setTommeBoligFelter()
    
    
    
    //Håndtering av reise felter
    
    /**
     * Oppretter de feltene som er unike for reiseforsikring
     */
    private void opprettReiseforsikringsFelter(){
        
        fjernUnikeFelter();
        
        betingelserReise = new ComboBox();
        ObservableList<String> betingelserReiseListe = FXCollections.observableArrayList(
                                              "Helårs Super", "Helårs");
        betingelserReise.setItems(betingelserReiseListe);
        betingelserLabel = new Label("Betingelser:");
        
        omrade = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        omradeLabel = new Label("Område:");
        omradeFeil = new Label("*");
        
        //legger til kolonne 1
        add(betingelserLabel, 1, 5);
        add(omradeLabel, 1, 6);
        setRowIndex(tegnForsikring, 7);
        
        //legger til kolonne 2
        add(betingelserReise, 2, 5);
        add(omrade, 2, 6);
        
        //legger til kolonne 3
        add(omradeFeil, 3, 6);
        
        reiseTekstFeltLyttere();
    }//End of method opprettReiseforsikringFeilter
    
    /**
     * Registrerer en reiseforsikring når metoden kalles
     */
    private void registrerReiseforsikring(){
        String fodselsnrInput = fodselsnr.getText().trim();
        String betingelserInput = (String) betingelserReise.getValue();
        String omradeInput = omrade.getText().trim();
        double forsikringsbelopInput = Double.parseDouble(forsikringsbelop.getText().trim());
        Reiseforsikring reiseforsikring = new Reiseforsikring(betingelserInput, forsikringsbelopInput, omradeInput);
        GUI.visInputFeilMelding("Ny forsikring registrert", kundeRegister.tegnForsikring(reiseforsikring, fodselsnrInput));
    }//end of method registrerReiseforsikring
    
    /**
     * Sjekker resieinput fra brukeren opp mot RegEx og gir umidelbar tilbakemelding på om inputen godkjennes eller evt hva som må endres
     * @return Returnerer true om alle feltene godkjennes av regexen
     */
    private boolean reiseTekstFeltLyttere(){
        omrade.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(omradeFeil, nyverdi, "Skriv inn kun bokstaver, med stor forbokstav", GUI.NAVN_REGEX);
        });
        return omradeFeil.getText().isEmpty();
    }//end of method reiseTekstFeltLyttere()
    
    /**
     * Tømmer reisefeltene for tekst og setter RegEx Labelene til *
     */
    private void setTommeReiseFelter(){
        betingelserReise.setValue(null);
        omrade.clear();
        
        omradeFeil.setText("*");
    }//end of method setTommeReiseFelter()
    
    /**
     * 
     * @return hvorvidt reise tekstfeltene er tomme
     */
    private boolean sjekkTommeReiseFelter(){
        if( betingelserReise.getValue() == null){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn betingelser");
            return false;
        }
        else if( omrade.getText().isEmpty()){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn område");
            return false;
        }
        else if(!tekstFeltLyttere() || !reiseTekstFeltLyttere()){
            GUI.visInputFeilMelding("Feil innskrivning", "Venligs fyll feltene på riktig format");
            return false;
        }
        return true;
    }//end of method sjekkTommeReiselFelter()
    
    /**
     * Tømmer de gjennomgående feltene for tekst og setter RegEx Labelene til *
     */
    private void setTommeFellesFelter(){
        fodselsnr.clear();
        forsikringsbelop.clear();
        
        fodselsnrFeil.setText("*");
        forsikringsbelopFeil.setText("*");
    }//end of method setTommeFellesFelter()
    
    /**
     * Sjekker om kunden er totalkunde, og viser et meldingsvindu når kunden blir totalkunde
     */
    private void blirTotalKunde(){
        ForsikringsKunde kunde = kundeRegister.finnKunde(fodselsnr.getText().trim());
        if(kunde == null)
            return;
        if(kunde.blirTotalKunde()){
            GUI.visInputFeilMelding("Total kunde", kunde.getEtternavn() +", " + kunde.getFornavn() + " har nå blitt total kunde!");
        }
    }//end of method blirTotalKunde()
    
    /**
     * fjerner alle labeler og tekstfelt for de felter som er unike for de forskjeldinge forsikringstypene
     */
    private void fjernUnikeFelter(){
        getChildren().removeAll(betingelserLabel, registreringsnrLabel, merkeLabel,
            modellLabel, registreringsarLabel, kjorelengdeLabel,prisPerKmLabel, 
            batRegistreringsnrLabel, batMerkeLabel, batModellLabel, arsmodellLabel,
            motorTypeLabel, motorStyrkeLabel, gateAdresseLabel, postnrLabel, 
            byggearLabel, boligTypeLabel, byggematerialeLabel, boligStandardLabel, 
            antallKVMLabel, omradeLabel, registreringsnr, merke, modell,
            registreringsar, kjorelengde, prisPerKm, batRegistreringsnr, batMerke, 
            batModell, arsmodell, motorStyrke, gateAdresse, postnr, byggear,
            byggemateriale, antallKVM, omrade, registreringsnrFeil, merkeFeil, 
            modellFeil, registreringsarFeil, kjorelengdeFeil, prisPerKmFeil, 
            batRegistreringsnrFeil, batMerkeFeil, batModellFeil, arsmodellFeil,
            motorStyrkeFeil, gateAdresseFeil, postnrFeil, byggearFeil, 
            byggematerialeFeil, antallKVMFeil, omradeFeil, motorType, boligType,
            boligStandard, betingelserBil, betingelserBat, betingelserBolig, 
            betingelserReise);
    }//end of method fjernUnikeFelter()
    
}//end of class TegnforsikringsLayout
