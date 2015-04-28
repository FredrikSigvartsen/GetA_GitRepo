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

/**
 *
 * @author Jens
 */
public class RegistrerKundeLayout extends GridPane{
    
    private TextField fornavn, etternavn, adresse, postnr, poststed, fodselsnr;    
    private Label fornavnLabel, etternavnLabel, adresseLabel, postnrLabel, poststedLabel, fodselsnrLabel;
    private Button registrerKunde;
    private Kunderegister kundeRegister;
    private TextArea output;
    
    public RegistrerKundeLayout(Kunderegister register, TextArea output){
        kundeRegistreringSkjema();
        registrerLytter();
        this.kundeRegister = register;
        this.output = output;
    }//end of construnctor
    
    /**
     * Oppretter skjema for registrering av kunde
     */
    public void kundeRegistreringSkjema(){
        
        registrerKunde = new Button("Registrer");
        
        fornavnLabel = new Label("Fornavn:");
        fornavn = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        etternavnLabel = new Label("Etternavn:");
        etternavn = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        adresseLabel = new Label("Adresse:");
        adresse = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        postnrLabel = new Label("Postnummer:");
        postnr = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        poststedLabel = new Label("Poststed:");
        poststed = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        fodselsnrLabel = new Label("Fødselsnummer:");
        fodselsnr = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        setVgap(10);
        setHgap(10);
        add(fornavnLabel, 1, 1);
        add(fornavn, 2, 1);
        add(etternavnLabel, 1, 2);
        add(etternavn, 2, 2);
        add(adresseLabel, 1, 3);
        add(adresse, 2, 3);
        add(postnrLabel, 1, 4);
        add(postnr, 2, 4);
        add(poststedLabel, 1, 5);
        add(poststed, 2, 5);
        add(fodselsnrLabel, 1, 6);
        add(fodselsnr, 2, 6);
        GridPane.setHalignment(registrerKunde, HPos.CENTER);
        add(registrerKunde, 1, 7, 2, 1);
    }//end of method kundeRegistreringsSkjema()
    
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
