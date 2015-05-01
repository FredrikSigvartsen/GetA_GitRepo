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

/**
 *
 * @author Jens
 */
public class TegnforsikringsLayout extends GridPane{
    
    private TextField fodselsnr, forsikringsbelop, betingelser, registreringsnr, 
            merke, modell, registreringsar, kjorelengde, prisPerKm, batRegistreringsnr, batMerke, 
            batModell, arsmodell, motorStyrke, gateAdresse, postnr, byggear,
            byggemateriale, antallKVM, omrade;    
    private Label fodselsnrFeil, forsikringsbelopFeil, betingelserFeil, registreringsnrFeil, merkeFeil, 
            modellFeil, registreringsarFeil, kjorelengdeFeil, prisPerKmFeil, batRegistreringsnrFeil, 
            batMerkeFeil, batModellFeil, arsmodellFeil, motorStyrkeFeil, gateAdresseFeil, postnrFeil, 
            byggearFeil,byggematerialeFeil, antallKVMFeil, omradeFeil;
    private Button tegnForsikring;
    private ComboBox forsikringsType, boligType, boligStandard, motorType;
    private GridPane bilforsikringFelter, batforsikringFelter, boligforsikringFelter, reiseforsikringFelter;
    private Kunderegister kundeRegister;
    private String forsikringsTypeString = "";
    private String forsikringsbelopRegEx = "^[0-9]{2,7}$", registreringsNrRegEx = "^[A-Z]{2}[0-9]{5}$";
    
    public TegnforsikringsLayout(Kunderegister register){
        tegnForsikringsSkjema();
        comboLytter();
        tegnForsikringLytter();
        this.kundeRegister = register;
        tekstFeltLyttere();
    }//end of constructor
    
    
    //Fjern meg!
    
    /**
     * Oppretter de felles input feltene i skjema for tegning av forsikring
     * og kaller på metodene som oppretter de inputfeltene som er unike for 
     * hver enkelt forsikring
     */
    private void tegnForsikringsSkjema(){
        bilforsikringsFelter();
        batforsikringsFelter();
        boligforsikringsFelter();
        reiseforsikringsFelter();
        
        
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
        
        //legger til kolonne 1
        add(new Label("Tegning av forsikring:"), 1, 1);
        add(new Label("Fødselsnummer:"), 1, 2);
        add(new Label("Forsikrings type:"), 1, 3);
        add(new Label("Forsikringsbeløp:"), 1, 4);
        add(new Label("Betingelser:"), 1, 5);
        
        //legger til kolonne 2
        add(fodselsnr, 2, 2);
        add(forsikringsType, 2, 3);
        add(forsikringsbelop, 2, 4);
        add(betingelser, 2, 5);
        setHalignment(tegnForsikring, HPos.CENTER);
        add(tegnForsikring, 1, 7, 2, 1);
        
        //legger til kolonne 3
        add(fodselsnrFeil, 3, 2);
        add(forsikringsbelopFeil, 3, 4);
        add(betingelserFeil, 3, 5);
    }//end of method tegnForsikringsSkjema()
    
    /**
     * Oppretter de feltene som er unike for bilforsikring
     */
    private void bilforsikringsFelter(){
        bilforsikringFelter = new GridPane();
        
        registreringsnr = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        registreringsnrFeil = new Label("*");
        
        merke = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        merkeFeil = new Label("*");
                
        modell = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        modellFeil = new Label("*");
                
        registreringsar = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        registreringsarFeil = new Label("*");
                
        kjorelengde = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        kjorelengdeFeil = new Label("*");
                
        prisPerKm = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        prisPerKmFeil = new Label("*");
        
        bilforsikringFelter.setVgap(10);
        bilforsikringFelter.setHgap(10);
        
        //legger til kolonne 0
        bilforsikringFelter.add(new Label("Registreringsnr:             "), 0, 1);//mellomrom legges til for å få riktig plassering av felten i forhold til parent GridPaneen
        bilforsikringFelter.add(new Label("Merke:"), 0, 2);
        bilforsikringFelter.add(new Label("Modell:"), 0, 3);
        bilforsikringFelter.add(new Label("Registreringsår:"), 0, 4);
        bilforsikringFelter.add(new Label("Kjørelengde:"), 0, 5);
        bilforsikringFelter.add(new Label("Pris per KM:"), 0, 6);
        
        //legger til kolonn 1
        bilforsikringFelter.add(registreringsnr, 1, 1);
        bilforsikringFelter.add(merke, 1, 2);
        bilforsikringFelter.add(modell, 1, 3);
        bilforsikringFelter.add(registreringsar, 1, 4);
        bilforsikringFelter.add(kjorelengde, 1, 5);
        bilforsikringFelter.add(prisPerKm, 1, 6);
        
        //legger til kolonne 2
        bilforsikringFelter.add(registreringsnrFeil, 2, 1);
        bilforsikringFelter.add(merkeFeil, 2, 2);
        bilforsikringFelter.add(modellFeil, 2, 3);
        bilforsikringFelter.add(registreringsarFeil, 2, 4);
        bilforsikringFelter.add(kjorelengdeFeil, 2, 5);
        bilforsikringFelter.add(prisPerKmFeil, 2, 6);
    }//End of method bilforsikringFelter()
    
    /**
     * Oppretter de feltene som er unike for båtforsikring
     */
    private void batforsikringsFelter(){
        batforsikringFelter = new GridPane();
        
        batRegistreringsnr = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        batRegistreringsnrFeil = new Label("*");
        
        batMerke = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        batMerkeFeil = new Label("*");
                
        batModell = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        batModellFeil = new Label("*");
                
        arsmodell = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        arsmodellFeil = new Label("*");
                
        motorType = new ComboBox();
        ObservableList<String> motortyper = FXCollections.observableArrayList(
                                              "Utenbords", "Innenbords",
                                              "Seil");
        motorType.setItems(motortyper);
                
        motorStyrke = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        motorStyrkeFeil = new Label("*");
        
        //legger til kolonne 0
        batforsikringFelter.setVgap(10);
        batforsikringFelter.setHgap(10);
        batforsikringFelter.add(new Label("Registreringsnr:             "), 0, 1);//mellomrom legges til for å få riktig plassering av felten i forhold til parent GridPaneen
        batforsikringFelter.add(new Label("Merke:"), 0, 2);
        batforsikringFelter.add(new Label("Modell:"), 0, 3);
        batforsikringFelter.add(new Label("Årsmodell:"), 0, 4);
        batforsikringFelter.add(new Label("Motor type:"), 0, 5);
        batforsikringFelter.add(new Label("Motorkraft:"), 0, 6);
        
        //legger til kolonne 1
        batforsikringFelter.add(batRegistreringsnr, 1, 1);
        batforsikringFelter.add(batMerke, 1, 2);
        batforsikringFelter.add(batModell, 1, 3);
        batforsikringFelter.add(arsmodell, 1, 4);
        batforsikringFelter.add(motorType, 1, 5);
        batforsikringFelter.add(motorStyrke, 1, 6);
        
        //legger til kolonne 2
        batforsikringFelter.add(batRegistreringsnrFeil, 2, 1);
        batforsikringFelter.add(batMerkeFeil, 2, 2);
        batforsikringFelter.add(batModellFeil, 2, 3);
        batforsikringFelter.add(arsmodellFeil, 2, 4);
        batforsikringFelter.add(motorStyrkeFeil, 2, 6);
    }//End of method BatforsikringFelter
    
    /**
     * Oppretter de feltene som er unike for boligforsikring
     */
    private void boligforsikringsFelter(){
        boligforsikringFelter = new GridPane();
        
        gateAdresse = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        gateAdresseFeil = new Label("*");
        
        postnr = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        postnrFeil = new Label("*");
                
        byggear = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        byggearFeil = new Label("*");
                
        boligType = new ComboBox();
        ObservableList<String> boligtyper = FXCollections.observableArrayList(
                                              "Enebolig", "Tomannsbolig",
                                              "Rekkehus", "Leilighet");
        boligType.setItems(boligtyper);
                
        byggemateriale = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        byggematerialeFeil = new Label("*");
                
        boligStandard = new ComboBox();
        ObservableList<String> standarder = FXCollections.observableArrayList(
                                              "Dårlig", "Middels",
                                              "God");
        boligStandard.setItems(standarder);
        
        antallKVM = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        antallKVMFeil = new Label("*");
        
        boligforsikringFelter.setVgap(10);
        boligforsikringFelter.setHgap(10);
        
        //legger til kolonne 0
        boligforsikringFelter.add(new Label("Adresse:                        "), 0, 1);//mellomrom legges til for å få riktig plassering av felten i forhold til parent GridPaneen
        boligforsikringFelter.add(new Label("Postnr:"), 0, 2);
        boligforsikringFelter.add(new Label("Byggeår:"), 0, 3);
        boligforsikringFelter.add(new Label("Bolig type:"), 0, 4);
        boligforsikringFelter.add(new Label("Byggemateriale:"), 0, 5);
        boligforsikringFelter.add(new Label("Standard:"), 0, 6);
        boligforsikringFelter.add(new Label("Antall KVM:"), 0, 7);
        
        //legger til kolonne 1
        boligforsikringFelter.add(gateAdresse, 1, 1);
        boligforsikringFelter.add(postnr, 1, 2);
        boligforsikringFelter.add(byggear, 1, 3);
        boligforsikringFelter.add(boligType, 1, 4);
        boligforsikringFelter.add(byggemateriale, 1, 5);
        boligforsikringFelter.add(boligStandard, 1, 6);
        boligforsikringFelter.add(antallKVM, 1, 7);
        
        //legger til kolonne 2
        boligforsikringFelter.add(gateAdresseFeil, 2, 1);
        boligforsikringFelter.add(postnrFeil, 2, 2);
        boligforsikringFelter.add(byggearFeil, 2, 3);
        boligforsikringFelter.add(byggematerialeFeil, 2, 5);
        boligforsikringFelter.add(antallKVMFeil, 2, 7);
        
    }//End of method BoligforsikringFeilter
    
    /**
     * Oppretter de feltene som er unike for reiseforsikring
     */
    private void reiseforsikringsFelter(){
        reiseforsikringFelter = new GridPane();
        
        omrade = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        omradeFeil = new Label("*");
        
        reiseforsikringFelter.setVgap(10);
        reiseforsikringFelter.setHgap(10);
        
        //legger til kolonne 0
        reiseforsikringFelter.add(new Label("Område:                        "), 0, 1);//mellomrom legges til for å få riktig plassering av felten i forhold til parent GridPaneen
        
        //legger til kolonne 1
        reiseforsikringFelter.add(omrade, 1, 1);
        
        //legger til kolonne 2
        reiseforsikringFelter.add(omradeFeil, 2, 1);
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
        kundeRegister.tegnForsikring(batforsikring, fodselsnr);
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
        kundeRegister.tegnForsikring(boligforsikring, fodselsnr);
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
        kundeRegister.tegnForsikring(reiseforsikring, fodselsnr);
        GUI.visInputFeilMelding("Ny forsikring registrert", kundeRegister.tegnForsikring(reiseforsikring, fodselsnr));
    }//end of method registrerReiseforsikring
    
    private boolean tekstFeltLyttere(){
        fodselsnr.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(fodselsnrFeil, nyverdi, "Skriv inn etGyldig fødselsnummer", null);
        });
        
        betingelser.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(betingelserFeil, nyverdi, "Skriv inn kun bokstaver,\n med stor forbokstav", GUI.NAVN_REGEX);
        });
        
        forsikringsbelop.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(forsikringsbelopFeil, nyverdi, "Skriv inn kun tall", forsikringsbelopRegEx);
        });
        
        /*switch (forsikringsTypeString){
            case "bilforsikring":
                registreringsnr.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
                    GUI.sjekkRegEx(registreringsnrFeil, nyverdi, "Skriv inn et Gyldig registreringsnr", registreringsNrRegEx);
                });
                /*else if( merke.getText().isEmpty()){
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
                return true;
            case "reiseforsikring":
                if( omrade.getText().isEmpty()){
                    GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn område");
                    return false;
                }
                return true;
        }*/
        //return fornavnFeil.getText().equals("") && etternavnFeil.getText().equals("") && adresseFeil.getText().equals("") && postnrFeil.getText().equals("") && poststedFeil.getText().equals("") && fodselsnrFeil.getText().equals("");
        return false;
    }//end of method tekstFeltLyttere()*/
    
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
                if(!tekstFeltLyttere()){
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
                if(!tekstFeltLyttere()){
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
                if(!tekstFeltLyttere()){
                    GUI.visInputFeilMelding("Feil innskrivning", "Venligs fyll feltene på riktig format");
                    return false;
                }
                return true;
            case "reiseforsikring":
                if( omrade.getText().isEmpty()){
                    GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn område");
                    return false;
                }
                if(!tekstFeltLyttere()){
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
                        getChildren().removeAll(batforsikringFelter, bilforsikringFelter, boligforsikringFelter, reiseforsikringFelter);
                        add(bilforsikringFelter, 1, 6, 2, 1);
                        forsikringsTypeString = "bilforsikring";
                        break;
                    case "Båtforsikring":
                        getChildren().removeAll(bilforsikringFelter, batforsikringFelter, boligforsikringFelter, reiseforsikringFelter);
                        add(batforsikringFelter, 1, 6, 2, 1);
                        forsikringsTypeString = "batforsikring";
                        break;
                    case "Boligforsikring":
                        getChildren().removeAll(bilforsikringFelter, batforsikringFelter, boligforsikringFelter, reiseforsikringFelter);
                        add(boligforsikringFelter, 1, 6, 2, 1);
                        forsikringsTypeString = "boligforsikring";
                        break;
                    case "Reiseforsikring":
                        getChildren().removeAll(bilforsikringFelter, batforsikringFelter, boligforsikringFelter, reiseforsikringFelter);
                        add(reiseforsikringFelter, 1, 6, 2, 1);
                        forsikringsTypeString = "reiseforsikring";
                        break;
                    default:
                        getChildren().removeAll(bilforsikringFelter, batforsikringFelter, boligforsikringFelter, reiseforsikringFelter);
                        add(bilforsikringFelter, 1, 6, 2, 1);
                        forsikringsTypeString = "default";
                }
            }
        });
    }//end of method comboLytter()
    
    /**
     * Legger til en lytter på tegnForsikring knappen
     */
    private void tegnForsikringLytter(){
        tegnForsikring.setOnAction((ActionEvent event) -> {
            registrerForsikring();
        });
    }//end of method tengForsikringsLytter()
    
}//end of class TegnforsikringsLayout
