/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt.kundebehandling;

import Brukergrensesnitt.GUI;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import forsikringsprogram.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.text.*;

/**
 *
 * @author Jens
 */
public class RegistrerKundeLayout extends GridPane{
    
    private TextField fornavn, etternavn, adresse, postnr, poststed, fodselsnr;    
    private Label fornavnFeil, etternavnFeil, adresseFeil, postnrFeil, poststedFeil, fodselsnrFeil;
    private Button registrerKunde;
    private Kunderegister kundeRegister;
    private String adresseRegEx = "^[A-ZÆØÅ][a-zA-Z æøåÆØÅ0-9\\s]*$";
    
    public RegistrerKundeLayout(Kunderegister register){
        kundeRegistreringSkjema();
        registrerLytter();
        this.kundeRegister = register;
        tekstFeltLyttere();
    }//end of construnctor
    
    /**
     * Oppretter skjema for registrering av kunde
     */
    public void kundeRegistreringSkjema(){
        
        registrerKunde = new Button("Registrer");
        
        
        fornavn = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        fornavnFeil = new Label("*");
        
        etternavn = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        etternavnFeil = new Label("*");
        
        adresse = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        adresseFeil = new Label("*");
        
        postnr = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        postnrFeil = new Label("*");
        
        
        poststed = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        poststedFeil = new Label("*");
        
        fodselsnr = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        fodselsnrFeil = new Label("*");
        
        setVgap(10);
        setHgap(10);
        
        Label registrerKundeLabel = new Label("Registrering av kunde");
        registrerKundeLabel.setFont(GUI.OVERSKRIFT);
        
        //legger til kolonne 1
        add(registrerKundeLabel, 1, 1, 2, 1);
        add(new Label("Fornavn:"), 1, 2);
        add(new Label("Etternavn:"), 1, 3);
        add(new Label("Adresse:"), 1, 4);
        add(new Label("Postnummer:"), 1, 5);
        add(new Label("Poststed:"), 1, 6);
        add(new Label("Fødselsnummer:"), 1, 7);
        GridPane.setHalignment(registrerKunde, HPos.CENTER);
        add(registrerKunde, 1, 8, 2, 1);
        
        //legger til kolonne 2
        add(fornavn, 2, 2);
        add(etternavn, 2, 3);
        add(adresse, 2, 4);
        add(postnr, 2, 5);
        add(poststed, 2, 6);
        add(fodselsnr, 2, 7);
        
        //legger til kolonne 3
        add(fornavnFeil, 3, 2);
        add(etternavnFeil, 3, 3);
        add(adresseFeil, 3, 4);
        add(postnrFeil, 3, 5);
        add(poststedFeil, 3, 6);
        add(fodselsnrFeil, 3, 7);
        
    }//end of method kundeRegistreringsSkjema()
    
    /**
     * Sjekker input fra brukeren opp mot RegEx og gir umidelbar tilbakemelding på om inputen godkjennes eller evt hva som må endres
     * @return Returnerer true om alle feltene godkjennes av regexen
     */
    private boolean tekstFeltLyttere(){
        fornavn.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(fornavnFeil, nyverdi, "Skriv inn kun bokstaver, med stor forbokstav", GUI.NAVN_REGEX);
        });
        
        etternavn.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(etternavnFeil, nyverdi, "Skriv inn kun bokstaver, med stor forbokstav", GUI.NAVN_REGEX);
        });
        
        adresse.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(adresseFeil, nyverdi, "Skriv inn kun bokstaver eller tall", adresseRegEx);
        });
        
        postnr.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(postnrFeil, nyverdi, "Skriv inn kun fire tall", GUI.POSTNR_REGEX);
        });
        
        poststed.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(poststedFeil, nyverdi, "Skriv inn kun bokstaver, med stor forbokstav", GUI.NAVN_REGEX);
        });
        
        fodselsnr.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(fodselsnrFeil, nyverdi, "Skriv inn et gyldig fødselsnummer (11 siffer)", null);
        });
        return fornavnFeil.getText().isEmpty() && etternavnFeil.getText().isEmpty() && adresseFeil.getText().isEmpty() && postnrFeil.getText().isEmpty() && poststedFeil.getText().isEmpty() && fodselsnrFeil.getText().isEmpty();
    }//end of method tekstFeltLyttere()
        
    /**
     * Sjekker alle innputfeltene, og registrerer en forsikring av valgt type
     */
    private boolean sjekkFelter(){
        if( fornavn.getText().trim().isEmpty()){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn fornavn");
            return false;
        }
        
        if( etternavn.getText().trim().isEmpty()){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn etternavn");
            return false;
        }
        
        if( adresse.getText().trim().isEmpty()){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn adresse");
            return false;
        }
        
        if( postnr.getText().trim().isEmpty()){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn postnummer");
            return false;
        }
        
        if( poststed.getText().trim().isEmpty()){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn poststed");
            return false;
        }
        
        if( fodselsnr.getText().trim().isEmpty()){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn fødselsnummer");
            return false;
        }
        if(!tekstFeltLyttere()){
            GUI.visInputFeilMelding("Feil innskrivning", "Venligs fyll feltene på riktig format");
            return false;
        }
        return true;
    }//end of method sjekkFelter()
    
    /**
     * Tømmer alle tekstfelter og setter regEx labelne til *
     */
    private void setTommeFelter(){
        fornavn.clear();
        etternavn.clear();
        adresse.clear();
        postnr.clear();
        poststed.clear();
        fodselsnr.clear();
        
        fornavnFeil.setText("*");
        etternavnFeil.setText("*");
        adresseFeil.setText("*");
        postnrFeil.setText("*");
        poststedFeil.setText("*");
        fodselsnrFeil.setText("*");
    }//end of method stTommeFelter()
    
    /**
     * leser inn og kontrolerer inputene fra bruker og registrerer kunden
     */
    public void registrerKunde(){
        if(!sjekkFelter()){
            return;
        }
        try{
            String fornavn = this.fornavn.getText().trim();
            String etternavn = this.etternavn.getText().trim();
            String adresse = this.adresse.getText().trim();
            String poststed = this.poststed.getText().trim();
            String postnr = this.postnr.getText().trim();
            String fodselsnr = this.fodselsnr.getText().trim();
            ForsikringsKunde kunde = new ForsikringsKunde(fornavn, etternavn, adresse, poststed, postnr, fodselsnr);
            
            if(kundeRegister.finnKunde(fodselsnr) != null){
                GUI.visInputFeilMelding("Feil ved registrering av kunde","Kunde med fødselsnr: " + kunde.getFodselsNr() + ", er allerede registrert");
                return;
            }
            if( ! (kundeRegister.registrerKunde(kunde) ) ){
                GUI.visInputFeilMelding("OBS! Feil i registrering.", "Kunden ble ikke registrert grunnet feil i registrering.\nKontakt IT-ansvarlig.");
                return;
            }
            GUI.visInputFeilMelding("Kunde registrert", kunde.getEtternavn() + ", " + kunde.getFornavn() + " ble registrert som kunde");
            setTommeFelter();
            
        }
        catch(NumberFormatException | NullPointerException e){
            GUI.visProgramFeilMelding(e);
            return;
        }
    }//end of method registrerKunde()
    
    /**
     * lytteren til registrerKunde knappen
     */
    private void registrerLytter(){
        registrerKunde.setOnAction((ActionEvent event) -> {
            registrerKunde();
        });
    }//end of method registrerLytter()
    
}//end of class RegistrerKundeLayout
