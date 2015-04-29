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

/**
 *
 * @author Jens
 */
public class RegistrerKundeLayout extends GridPane{
    
    private TextField fornavn, etternavn, adresse, postnr, poststed, fodselsnr;    
    private Label fornavnLabel, etternavnLabel, adresseLabel, postnrLabel, poststedLabel, fodselsnrLabel,
            fornavnFeil, etternavnFeil, adresseFeil, postnrFeil, poststedFeil, fodselsnrFeil;
    private Button registrerKunde;
    private Kunderegister kundeRegister;
    private TextArea output;
    private String adresseRegEx = "^[A-ZÆØÅ][a-zA-Z æøåÆØÅ0-9\\s]*$";
    
    public RegistrerKundeLayout(Kunderegister register, TextArea output){
        this.output = output;
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
        
        fornavnLabel = new Label("Fornavn:");
        fornavn = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        fornavnFeil = new Label("*");
        
        etternavnLabel = new Label("Etternavn:");
        etternavn = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        etternavnFeil = new Label("*");
        
        adresseLabel = new Label("Adresse:");
        adresse = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        adresseFeil = new Label("*");
        
        postnrLabel = new Label("Postnummer:");
        postnr = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        postnrFeil = new Label("*");
        
        
        poststedLabel = new Label("Poststed:");
        poststed = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        poststedFeil = new Label("*");
        
        fodselsnrLabel = new Label("Fødselsnummer:");
        fodselsnr = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        fodselsnrFeil = new Label("*");
        
        setVgap(10);
        setHgap(10);
        //legger til kolonne 1
        add(fornavnLabel, 1, 1);
        add(etternavnLabel, 1, 2);
        add(adresseLabel, 1, 3);
        add(postnrLabel, 1, 4);
        add(poststedLabel, 1, 5);
        add(fodselsnrLabel, 1, 6);
        GridPane.setHalignment(registrerKunde, HPos.CENTER);
        add(registrerKunde, 1, 7, 2, 1);
        
        //legger til kolonne 2
        add(fornavn, 2, 1);
        add(etternavn, 2, 2);
        add(adresse, 2, 3);
        add(postnr, 2, 4);
        add(poststed, 2, 5);
        add(fodselsnr, 2, 6);
        
        //legger til kolonne 3
        add(fornavnFeil, 3, 1);
        add(etternavnFeil, 3, 2);
        add(adresseFeil, 3, 3);
        add(postnrFeil, 3, 4);
        add(poststedFeil, 3, 5);
        add(fodselsnrFeil, 3, 6);
        
    }//end of method kundeRegistreringsSkjema()
    
    /**
     * Sjekker input fra brukeren opp mot RegEx og gir umidelbar tilbakemelding på om inputen godkjennes eller evt hva som må endres
     * @return Returnerer true om alle feltene godkjennes av regexen
     */
    private boolean tekstFeltLyttere(){
        fornavn.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(fornavnFeil, nyverdi, "Skriv inn kun bokstaver,\n med stor forbokstav", GUI.NAVN_REGEX);
        });
        
        etternavn.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(etternavnFeil, nyverdi, "Skriv inn kun bokstaver,\n med stor forbokstav", GUI.NAVN_REGEX);
        });
        
        adresse.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(adresseFeil, nyverdi, "Skriv inn kun bokstaver eller tall", adresseRegEx);
        });
        
        postnr.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(postnrFeil, nyverdi, "Skriv inn kun fire tall", GUI.POSTNR_REGEX);
        });
        
        poststed.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(poststedFeil, nyverdi, "Skriv inn kun bokstaver,\n med stor forbokstav", GUI.NAVN_REGEX);
        });
        
        fodselsnr.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(fodselsnrFeil, nyverdi, "Skriv inn et gyldig fødselsnr", null);
        });
        return fornavnFeil.getText().equals("") && etternavnFeil.getText().equals("") && adresseFeil.getText().equals("") && postnrFeil.getText().equals("") && poststedFeil.getText().equals("") && fodselsnrFeil.getText().equals("");
    }//end of method sjekkFelterRegEx()
        
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
                getChildren().remove(output);
                output.setText("Kunde med fødselsnr: " + kunde.getFodselsNr() + ", er allerede registrert");
                add(output, 1, 8, 2, 1);
            }
            else{
                getChildren().remove(output);
                kundeRegister.registrerKunde(kunde);
                output.setText(kunde.getEtternavn() + ", " + kunde.getFornavn() + " ble registrert som kunde");
                add(output, 1, 8, 3, 1);
            }
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
