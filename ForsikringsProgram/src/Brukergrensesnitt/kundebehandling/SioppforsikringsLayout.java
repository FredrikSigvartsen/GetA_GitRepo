/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt.kundebehandling;

import Brukergrensesnitt.GUI;
import forsikringsprogram.*;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 *
 * @author Jens
 */
public class SioppforsikringsLayout extends GridPane{
    
    private TextField fodselsnr, avtalenr;    
    private Label fodselsnrFeil, avtalenrFeil;
    private Button siOppForsikring;
    private Kunderegister kundeRegister;
    private String avtaleNrRegEx = "^[0-9]{1,4}$";
    
    public SioppforsikringsLayout(Kunderegister register){
        siOppForsikringsSkjema();
        siOppLytter();
        this.kundeRegister = register;
        tekstFeltLyttere();
    }//end of constructor
    
    /**
     * Oppretter skjema for oppsigelse av forsiking
     */
    public void siOppForsikringsSkjema(){
        
        siOppForsikring = new Button("Si opp forsikring");
        
        
        fodselsnr = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        fodselsnrFeil = new Label("*");
        
        avtalenr = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        avtalenrFeil = new Label("*");
        
        setVgap(10);
        setHgap(10);
        
        Label siOppForsikringLabel = new Label("Oppsigelse av forsikring:");
        siOppForsikringLabel.setFont(GUI.OVERSKRIFT);
        
        //legger til kolonne 1
        add(siOppForsikringLabel, 1, 1, 2, 1);
        add(new Label("Fødselsnummer:"), 1, 2);
        add(new Label("Avtalenr:"), 1, 3);
        GridPane.setHalignment(siOppForsikring, HPos.CENTER);
        add(siOppForsikring, 1, 4, 2, 1);
        
        //legger til kolonne 2
        add(fodselsnr, 2, 2);
        add(avtalenr, 2, 3);
        
        //legger til kolonne 3
        add(fodselsnrFeil, 3, 2);
        add(avtalenrFeil, 3, 3);
    }//end of methd siOppForsikringsSkjema()
    
    /**
     * Sjekker input fra brukeren opp mot RegEx og gir umidelbar tilbakemelding på om inputen godkjennes eller evt hva som må endres
     * @return Returnerer true om alle feltene godkjennes av regexen
     */
    private boolean tekstFeltLyttere(){
        fodselsnr.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(fodselsnrFeil, nyverdi, "Skriv inn et eksisterende fodselsnummer(11 siffer)", null);
        });
        
        avtalenr.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(avtalenrFeil, nyverdi, "Skriv inn et eksisterende avtalenummer(1-4 siffer)", avtaleNrRegEx);
        });
        return fodselsnrFeil.getText().isEmpty() && avtalenrFeil.getText().isEmpty();
    }//end of method sjekkFelterRegEx()
    
    /**
     * Sjekker alle innputfeltene, og registrerer en forsikring av valgt type
     */
    private boolean sjekkFelter(){
        if( fodselsnr.getText().trim().isEmpty()){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn fødselsnummer");
            return false;
        }
        if( avtalenr.getText().trim().isEmpty()){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn avtalenummer");
            return false;
        }
        return true;
    }//end of method sjekkFelter()
    
    /**
     * Tømmer alle tekstfelter og setter regEx labelne til *
     */
    private void setTommeFelter(){
        fodselsnr.clear();
        avtalenr.clear();
        
        avtalenrFeil.setText("*");
        fodselsnrFeil.setText("*");
    }//end of method stTommeFelter()
    
    /**
     * Validerer inputfelter for å så registrere forsikrings oppsigelsen
     */
    public void siOppForsikring(){
        try{
            String fodselsnr = this.fodselsnr.getText().trim();
            int avtalenr = Integer.parseInt(this.avtalenr.getText().trim());
            GUI.visInputFeilMelding("Forsikring sagt opp", kundeRegister.siOppForsikring(fodselsnr, avtalenr));
            setTommeFelter();
        }
        catch(NumberFormatException | NullPointerException e){
            GUI.visProgramFeilMelding(e);
            return;
        }
    }//end of class siOppForsikring()
    
    /**
     * Legger til en lytter på siOppForsikring knappen
     */
    private void siOppLytter(){
        siOppForsikring.setOnAction((ActionEvent event) -> {
            siOppForsikring();
        });
    }//end of method siOppLytter()
    
}//end of class SioppforsikringsLayout
