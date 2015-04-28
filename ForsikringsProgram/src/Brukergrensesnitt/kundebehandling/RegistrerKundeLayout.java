/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt.kundebehandling;

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
    private Label fornavnLabel, etternavnLabel, adresseLabel, postnrLabel, poststedLabel, fodselsnrLabel, registrertLabel;
    private Button registrerKunde;
    private Kunderegister kundeRegister;
    
    public RegistrerKundeLayout(Kunderegister register){
        kundeRegistreringSkjema();
        registrerLytter();
        this.kundeRegister = register;
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
    }
    
    public void registrerKunde(){
        String fornavn = this.fornavn.getText();
        String etternavn = this.etternavn.getText();
        String adresse = this.adresse.getText();
        String poststed = this.poststed.getText();
        String postnr = this.postnr.getText();
        String fodselsnr = this.fodselsnr.getText();
        if(fornavn.trim().equals("") || etternavn.trim().equals("") || adresse.trim().equals("") || poststed.trim().equals("") || postnr.trim().equals("") || fodselsnr.trim().equals("")){
            Alert fyllInnAltMelding = new Alert(Alert.AlertType.WARNING);
            fyllInnAltMelding.setTitle("Feil i Inntasting");
            fyllInnAltMelding.setHeaderText("Tomme felter");
            fyllInnAltMelding.setContentText("Venligst fyll inn alle feltene");
            fyllInnAltMelding.showAndWait();
        }
        else{
            ForsikringsKunde kunde = new ForsikringsKunde(fornavn, etternavn, adresse, poststed, postnr, fodselsnr);
            if(kundeRegister.finnKunde(fodselsnr) != null){
                getChildren().remove(registrertLabel);
                registrertLabel = new Label("Kunde med fødselsnr: " + kunde.getFodselsNr() + ", er allerede registrert");
                add(registrertLabel, 1, 8, 2, 1);
            }
            else{
                getChildren().remove(registrertLabel);
                kundeRegister.registrerKunde(kunde);
                registrertLabel = new Label(kunde.getEtternavn() + ", " + kunde.getFornavn() + " ble registrert som kunde");
                add(registrertLabel, 1, 8, 3, 1);
            }
            System.out.println( kundeRegister.toString() );
        }
    }
    
    private void registrerLytter(){
        registrerKunde.setOnAction((ActionEvent event) -> {
            registrerKunde();
        });
    }
}
