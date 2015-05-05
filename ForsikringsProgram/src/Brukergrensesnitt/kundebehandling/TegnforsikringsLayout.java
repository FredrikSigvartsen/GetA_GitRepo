/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt.kundebehandling;

import forsikringsprogram.*;
import Brukergrensesnitt.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;

/**
 *
 * @author Jens
 */
public class TegnforsikringsLayout extends GridPane{
    
    private TextField fodselsnr, forsikringsbelop, betingelser, registreringsnr, 
            merke, modell, registreringsar, kjorelengde, prisPerKm, batRegistreringsnr, batMerke, 
            batModell, arsmodell, motorStyrke, gateAdresse, postnr, byggear,
            byggemateriale, antallKVM, omrade;    
    private Label registreringsnrLabel, merkeLabel, modellLabel, registreringsarLabel, kjorelengdeLabel, 
            prisPerKmLabel, batRegistreringsnrLabel, batMerkeLabel, batModellLabel, arsmodellLabel, motorTypeLabel,
            motorStyrkeLabel, gateAdresseLabel, postnrLabel, byggearLabel, boligTypeLabel, byggematerialeLabel, boligStandardLabel,
            antallKVMLabel, omradeLabel, fodselsnrFeil, forsikringsbelopFeil, betingelserFeil, 
            registreringsnrFeil, merkeFeil, modellFeil, registreringsarFeil, kjorelengdeFeil, 
            prisPerKmFeil, batRegistreringsnrFeil, batMerkeFeil, batModellFeil, arsmodellFeil,
            motorStyrkeFeil, gateAdresseFeil, postnrFeil, byggearFeil, byggematerialeFeil,
            antallKVMFeil, omradeFeil;
    private Button tegnForsikring;
    private ComboBox forsikringsType, boligType, boligStandard, motorType;
    private GridPane boligforsikringFelter, reiseforsikringFelter;
    private StackPane bilforsikringStackPane;
    private Kunderegister kundeRegister;
    private String forsikringsTypeString;
    private String forsikringsbelopRegEx = "^[0-9]{2,7}$", registreringsNrRegEx = "^[A-Z]{2}[0-9]{5}$", 
            batRegistreringsNrRegEx = "^[A-Z]{3}[0-9]{3}$" , adresseRegEx = "^[A-ZÆØÅ][a-zA-Z æøåÆØÅ0-9\\s]*$", 
            modellRegEx = "^[A-ZÆØÅ][a-zA-Z æøåÆØÅ0-9\\s]*$", prisPerKmRegEx = "^[0-9]{1,4}$";
    
    public TegnforsikringsLayout(Kunderegister register){
        tegnForsikringsSkjema();
        tekstFeltLyttere();
        comboLytter();
        tegnForsikringLytter();
        this.kundeRegister = register;
    }//end of constructor
    
    /**
     * Oppretter de felles input feltene i skjema for tegning av forsikring
     * og kaller på metodene som oppretter de inputfeltene som er unike for 
     * hver enkelt forsikring
     */
    private void tegnForsikringsSkjema(){
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
        
        betingelser = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        betingelserFeil = new Label("*");
        
        setVgap(10);
        setHgap(10);
        
        Label tegnForsikringLabel = new Label("Tegning av forsikring:");
        tegnForsikringLabel.setFont(GUI.OVERSKRIFT);
        //legger til kolonne 1
        add(tegnForsikringLabel, 1, 1, 2, 1);
        add(new Label("Fødselsnummer:"), 1, 2);
        add(new Label("Forsikrings type:"), 1, 3);
        add(new Label("Forsikringsbeløp:"), 1, 4);
        add(new Label("Betingelser:"), 1, 5);
        setHalignment(tegnForsikring, HPos.CENTER);
        add(tegnForsikring, 1, 13, 2, 1);
        
        //legger til kolonne 2
        add(fodselsnr, 2, 2);
        add(forsikringsType, 2, 3);
        add(forsikringsbelop, 2, 4);
        add(betingelser, 2, 5);
        
        //legger til kolonne 3
        add(fodselsnrFeil, 3, 2, 3, 1);
        add(forsikringsbelopFeil, 3, 4, 4, 1);
        add(betingelserFeil, 3, 5, 4, 1);
    }//end of method tegnForsikringsSkjema()
    
    /**
     * fjerner alle labeler og tekstfelt for de felter som er unike for de forskjeldinge forsikringstypene
     */
    private void fjernUnikeFelter(){
        getChildren().removeAll(registreringsnrLabel, merkeLabel, modellLabel, 
            registreringsarLabel, kjorelengdeLabel,prisPerKmLabel, batRegistreringsnrLabel,
            batMerkeLabel, batModellLabel, arsmodellLabel, motorTypeLabel, motorStyrkeLabel,
            gateAdresseLabel, postnrLabel, byggearLabel, boligTypeLabel, byggematerialeLabel, boligStandardLabel, 
            antallKVMLabel, omradeLabel, registreringsnr, merke, modell,
            registreringsar, kjorelengde, prisPerKm, batRegistreringsnr, batMerke, 
            batModell, arsmodell, motorStyrke, gateAdresse, postnr, byggear,
            byggemateriale, antallKVM, omrade, registreringsnrFeil, merkeFeil, modellFeil, 
            registreringsarFeil, kjorelengdeFeil, prisPerKmFeil, batRegistreringsnrFeil,
            batMerkeFeil, batModellFeil, arsmodellFeil, motorStyrkeFeil, gateAdresseFeil,
            postnrFeil, byggearFeil,byggematerialeFeil, antallKVMFeil, omradeFeil, 
            motorType, boligType, boligStandard);
    }//end of method fjernUnikeFelter()
    
    /**
     * Oppretter de feltene som er unike for bilforsikring
     */
    private void bilforsikringsFelter(){
        
        fjernUnikeFelter();
        
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
        add(registreringsnrLabel, 1, 6);
        add(merkeLabel, 1, 7);
        add(modellLabel, 1, 8);
        add(registreringsarLabel, 1, 9);
        add(kjorelengdeLabel, 1, 10);
        add(prisPerKmLabel, 1, 11);
        
        //legger til kolonn 2
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
    }//End of method bilforsikringFelter()
    
    /**
     * Oppretter de feltene som er unike for båtforsikring
     */
    private void batforsikringsFelter(){
        
        fjernUnikeFelter();
        
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
        add(batRegistreringsnrLabel, 1, 6);
        add(batMerkeLabel, 1, 7);
        add(batModellLabel, 1, 8);
        add(arsmodellLabel, 1, 9);
        add(motorTypeLabel, 1, 10);
        add(motorStyrkeLabel, 1, 11);
        
        //legger til kolonne 2
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
    }//End of method BatforsikringFelter
    
    /**
     * Oppretter de feltene som er unike for boligforsikring
     */
    private void boligforsikringsFelter(){
        
        fjernUnikeFelter();
        
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
        add(gateAdresseLabel, 1, 6);
        add(postnrLabel, 1, 7);
        add(byggearLabel, 1, 8);
        add(boligTypeLabel, 1, 9);
        add(byggematerialeLabel, 1, 10);
        add(boligStandardLabel, 1, 11);
        add(antallKVMLabel, 1, 12);
        
        //legger til kolonne 2
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
    }//End of method BoligforsikringFeilter
    
    /**
     * Oppretter de feltene som er unike for reiseforsikring
     */
    private void reiseforsikringsFelter(){
        
        fjernUnikeFelter();
        
        omrade = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        omradeLabel = new Label("Område:");
        omradeFeil = new Label("*");
        
        //legger til kolonne 1
        add(omradeLabel, 1, 6);
        
        //legger til kolonne 2
        add(omrade, 2, 6);
        
        //legger til kolonne 3
        add(omradeFeil, 3, 6);
        
        reiseTekstFeltLyttere();
    }//End of method BoligforsikringFeilter
    
    /**
     * Registrerer en bilforsikring når metoden kalles
     */
    private void registrerBilforsikring(){
        String fodselsnr = this.fodselsnr.getText().trim();
        String betingelser = this.betingelser.getText().trim();
        String registreringsnr = this.registreringsnr.getText().trim();
        String merke = this.merke.getText().trim();
        String modell = this.modell.getText().trim();
        double forsikringsbelop = Double.parseDouble(this.forsikringsbelop.getText().trim());
        int registreringsar = Integer.parseInt(this.registreringsar.getText().trim());
        int kjorelengde = Integer.parseInt(this.kjorelengde.getText().trim());
        int prisPerKm = Integer.parseInt(this.prisPerKm.getText().trim());
        Bilforsikring bilforsikring = new Bilforsikring(betingelser, forsikringsbelop,
                                               registreringsnr, merke, modell, registreringsar, kjorelengde, prisPerKm);
        GUI.visInputFeilMelding("Ny forsikring registrert", kundeRegister.tegnForsikring(bilforsikring, fodselsnr));
    }//end of method registrerBilforsikring()
    
    /**
     * Registrerer en båtforsikring når metoden kalles
     */
    private void registrerBatforsikring(){
        String fodselsnr = this.fodselsnr.getText().trim();
        String betingelser = this.betingelser.getText().trim();
        String batRegistreringsnr = this.batRegistreringsnr.getText().trim();
        String batMerke = this.batMerke.getText().trim();
        String batModell = this.batModell.getText().trim();
        String motorType = (String) this.motorType.getValue();
        double forsikringsbelop = Double.parseDouble(this.forsikringsbelop.getText().trim());
        int arsmodell = Integer.parseInt(this.arsmodell.getText().trim());
        int motorStyrke = Integer.parseInt(this.motorStyrke.getText().trim());
        Baatforsikring batforsikring = new Baatforsikring(betingelser, forsikringsbelop,
                                        batRegistreringsnr, arsmodell, motorStyrke, batMerke, batModell, motorType);
        GUI.visInputFeilMelding("Ny forsikring registrert", kundeRegister.tegnForsikring(batforsikring, fodselsnr));
    }//end of method registrerBatforsikring()
    
    /**
     * Registrerer en boligforsikring når metoden kalles
     */
    private void registrerBoligforsikring(){
        String fodselsnr = this.fodselsnr.getText().trim();
        String betingelser = this.betingelser.getText().trim();
        String gateAdresse = this.gateAdresse.getText().trim();
        String boligType = (String) this.boligType.getValue();
        String byggemateriale = this.byggemateriale.getText().trim();
        String standard = (String) this.boligStandard.getValue();
        String postnr = this.postnr.getText().trim();
        double forsikringsbelop = Double.parseDouble(this.forsikringsbelop.getText().trim());
        int byggear = Integer.parseInt(this.byggear.getText().trim());
        int antallKVM = Integer.parseInt(this.antallKVM.getText().trim());
        Boligforsikring boligforsikring = new Boligforsikring(betingelser, forsikringsbelop,
                gateAdresse, boligType, byggemateriale, standard, postnr, byggear, antallKVM);
        GUI.visInputFeilMelding("Ny forsikring registrert", kundeRegister.tegnForsikring(boligforsikring, fodselsnr));
    }//end of method registrerBoligforsikring()
    
    /**
     * Registrerer en reiseforsikring når metoden kalles
     */
    private void registrerReiseforsikring(){
        String fodselsnr = this.fodselsnr.getText().trim();
        String betingelser = this.betingelser.getText().trim();
        String omrade = this.omrade.getText().trim();
        double forsikringsbelop = Double.parseDouble(this.forsikringsbelop.getText().trim());
        Reiseforsikring reiseforsikring = new Reiseforsikring(betingelser, forsikringsbelop, omrade);
        GUI.visInputFeilMelding("Ny forsikring registrert", kundeRegister.tegnForsikring(reiseforsikring, fodselsnr));
    }//end of method registrerReiseforsikring
    
    /**
     * Sjekker om feltene i layoutet er tomme, og gir brukeren en melding om hva som må fylles inn.
     * @return Returnerer true om alle feltene er fylt inn, og false om noe mangler
     */
    private boolean sjekkFelter(){
        if( fodselsnr.getText().trim().isEmpty()){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn fødselsnummer");
            return false;
        }
        
        else if( betingelser.getText().isEmpty()){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn betingelser");
            return false;
        }
        
        else if( forsikringsbelop.getText().isEmpty()){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn forsikringsbeløp");
            return false;
        }
        
        switch (forsikringsTypeString){
            case "bilforsikring":
                if( registreringsnr.getText().trim().isEmpty()){
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
            case "batforsikring":
                if( batRegistreringsnr.getText().trim().isEmpty()){
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
            case "boligforsikring":
                if( gateAdresse.getText().trim().isEmpty()){
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
            case "reiseforsikring":
                if( omrade.getText().isEmpty()){
                    GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn område");
                    return false;
                }
                else if(!tekstFeltLyttere() || !reiseTekstFeltLyttere()){
                    GUI.visInputFeilMelding("Feil innskrivning", "Venligs fyll feltene på riktig format");
                    return false;
                }
                return true;
        }
        return false;
    }// end of method sjekkFelter()
    
    /**
     * Sjekker om kunden er totalkunde, og viser et meldingsvindu når kunden blir totalkunde
     */
    private void erTotalKunde(){
        ForsikringsKunde kunde = kundeRegister.finnKunde(fodselsnr.getText().trim());
        if(kunde.blirTotalKunde()){
            GUI.visInputFeilMelding("Total kunde", kunde.getEtternavn() +", " + kunde.getFornavn() + " har nå blitt total kunde!");
        }
    }//end of method erTotalKunde()
    
    /**
     * Sjekker alle innputfeltene, og registrerer en forsikring av valgt type
     */
    private void registrerForsikring(){
        if(!sjekkFelter())
                return;
        try{
            switch (forsikringsTypeString){
                case "bilforsikring":
                    registrerBilforsikring();
                    erTotalKunde();
                    break;
                case "batforsikring":
                    registrerBatforsikring();
                    erTotalKunde();
                    break;
                case "boligforsikring":
                    registrerBoligforsikring();
                    erTotalKunde();
                    break;
                case "reiseforsikring":
                    registrerReiseforsikring();
                    erTotalKunde();
                    break;
            }
        }
        catch(NumberFormatException | NullPointerException e){
            GUI.visProgramFeilMelding(e);
            return;
        }
    }//end of method registrerForsikring()
    
    /**
     * Legger til en lytter til forsikringsType comboboxen, og legger til de
     * korrekte inputfeltene for den forsikringstypen
     */
    private void comboLytter(){
        forsikringsType.valueProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                switch ((String) t1) {
                    case "Bilforsikring":
                        bilforsikringsFelter();
                        forsikringsTypeString = "bilforsikring";
                        break;
                    case "Båtforsikring":
                        batforsikringsFelter();
                        forsikringsTypeString = "batforsikring";
                        break;
                    case "Boligforsikring":
                        boligforsikringsFelter();
                        forsikringsTypeString = "boligforsikring";
                        break;
                    case "Reiseforsikring":
                        reiseforsikringsFelter();
                        forsikringsTypeString = "reiseforsikring";
                        break;
                    default:
                        bilforsikringsFelter();
                        forsikringsTypeString = "default";
                }
            }
        });
    }//end of method comboLytter()
    
    /**
     * Sjekker input fra brukeren opp mot RegEx og gir umidelbar tilbakemelding på om inputen godkjennes eller evt hva som må endres
     * @return Returnerer true om alle feltene godkjennes av regexen
     */
    private boolean tekstFeltLyttere(){
        fodselsnr.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(fodselsnrFeil, nyverdi, "Skriv inn et gyldig fødselsnummer (11 siffer)", null);
        });
        
        betingelser.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(betingelserFeil, nyverdi, "Skriv inn kun bokstaver, med stor forbokstav", GUI.NAVN_REGEX);
        });
        
        forsikringsbelop.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(forsikringsbelopFeil, nyverdi, "Skriv inn kun tall", forsikringsbelopRegEx);
        });
        
        return fodselsnrFeil.getText().isEmpty() && betingelserFeil.getText().isEmpty() && forsikringsbelopFeil.getText().isEmpty();
    }//end of method tekstFeltLyttere()
    
    
    /**
     * Sjekker bilinput fra brukeren opp mot RegEx og gir umidelbar tilbakemelding på om inputen godkjennes eller evt hva som må endres
     * @return Returnerer true om alle feltene godkjennes av regexen
     */
    private boolean bilTekstFeltLyttere(){
        registreringsnr.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(registreringsnrFeil, nyverdi, "Skriv inn et gyldig registreringsnr (AB12345)", registreringsNrRegEx);
        });

        merke.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(merkeFeil, nyverdi, "Skriv inn kun bokstaver, med stor forbokstav", GUI.NAVN_REGEX);
        });

        modell.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(modellFeil, nyverdi, "Skriv inn kun bokstaver, med stor forbokstav", modellRegEx);
        });

        registreringsar.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(registreringsarFeil, nyverdi, "Skriv inn et gyldig årstall med 4 tall", GUI.POSTNR_REGEX);
        });

        kjorelengde.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(kjorelengdeFeil, nyverdi, "Skriv inn kun tall", forsikringsbelopRegEx);
        });

        prisPerKm.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(prisPerKmFeil, nyverdi, "Skriv inn kun tall", prisPerKmRegEx);
        });
        return registreringsnrFeil.getText().isEmpty() && merkeFeil.getText().isEmpty() && modellFeil.getText().isEmpty() && 
               registreringsarFeil.getText().isEmpty() && kjorelengdeFeil.getText().isEmpty() && prisPerKmFeil.getText().isEmpty();
    }
    
    /**
     * Sjekker båtinput fra brukeren opp mot RegEx og gir umidelbar tilbakemelding på om inputen godkjennes eller evt hva som må endres
     * @return Returnerer true om alle feltene godkjennes av regexen
     */
    private boolean batTekstFeltLyttere(){
        batRegistreringsnr.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(batRegistreringsnrFeil, nyverdi, "Skriv inn et gyldig registreringsnr (ABC123)", batRegistreringsNrRegEx);
        });

        batMerke.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(batMerkeFeil, nyverdi, "Skriv inn kun bokstaver, med stor forbokstav", GUI.NAVN_REGEX);
        });

        batModell.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(batModellFeil, nyverdi, "Skriv inn kun bokstaver, med stor forbokstav", modellRegEx);
        });

        arsmodell.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(arsmodellFeil, nyverdi, "Skriv inn et gyldig årstall med 4 tall", GUI.POSTNR_REGEX);
        });

        motorStyrke.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(motorStyrkeFeil, nyverdi, "Skriv inn kun tall", forsikringsbelopRegEx);
        });
        return batRegistreringsnrFeil.getText().isEmpty() && batMerkeFeil.getText().isEmpty() && batModellFeil.getText().isEmpty() && 
               arsmodellFeil.getText().isEmpty() && motorStyrkeFeil.getText().isEmpty();
    }
    
    /**
     * Sjekker boliginput fra brukeren opp mot RegEx og gir umidelbar tilbakemelding på om inputen godkjennes eller evt hva som må endres
     * @return Returnerer true om alle feltene godkjennes av regexen
     */
    private boolean boligTekstFeltLyttere(){
        gateAdresse.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(gateAdresseFeil, nyverdi, "Skriv inn kun bokstaver og tall", adresseRegEx);
        });

        byggemateriale.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(byggematerialeFeil, nyverdi, "Skriv inn kun bokstaver, med stor forbokstav", GUI.NAVN_REGEX);
        });

        postnr.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(postnrFeil, nyverdi, "Skriv inn fire tall", GUI.POSTNR_REGEX);
        });

        byggear.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(byggearFeil, nyverdi, "Skriv inn et gyldig årstall med 4 tall", GUI.POSTNR_REGEX);
        });

        antallKVM.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(antallKVMFeil, nyverdi, "Skriv inn kun tall", forsikringsbelopRegEx);
        });
        return gateAdresseFeil.getText().isEmpty() && byggematerialeFeil.getText().isEmpty() && postnrFeil.getText().isEmpty() && 
               byggearFeil.getText().isEmpty() && antallKVMFeil.getText().isEmpty();
    }
    
    /**
     * Sjekker resieinput fra brukeren opp mot RegEx og gir umidelbar tilbakemelding på om inputen godkjennes eller evt hva som må endres
     * @return Returnerer true om alle feltene godkjennes av regexen
     */
    private boolean reiseTekstFeltLyttere(){
        omrade.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(omradeFeil, nyverdi, "Skriv inn kun bokstaver, med stor forbokstav", GUI.NAVN_REGEX);
        });
        return omradeFeil.getText().isEmpty();
    }
    
    /**
     * Legger til en lytter på tegnForsikring knappen
     */
    private void tegnForsikringLytter(){
        tegnForsikring.setOnAction((ActionEvent event) -> {
            registrerForsikring();
        });
    }//end of method tengForsikringsLytter()
    
}//end of class TegnforsikringsLayout
