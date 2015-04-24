/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt;

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
    private ForsikringsKunde kunde;
    private Kunderegister kundeRegister;
    
    public RegistrerKundeLayout(Kunderegister register){
        kundeRegistreringSkjema();
        registrerLytter();
        this.kundeRegister = register;
    }
    
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
            /*if(fornavn.trim().equals(""))
                System.out.println("Fyll inn fornavn");
            else if(etternavn.trim().equals(""))
                System.out.println("Fyll inn etternavn");
            else if(adresse.trim().equals(""))
                System.out.println("Fyll inn adresse");
            else if(postnr.trim().equals(""))
                System.out.println("Fyll inn postnr");
            else if(poststed.trim().equals(""))
                System.out.println("Fyll inn poststed");
            else if(fodselsnr.trim().equals(""))
                System.out.println("Fyll inn fodselsnr");*/
            System.out.println("Venligst fyll inn alle feltene");
        }
        else{
            kunde = new ForsikringsKunde(fornavn, etternavn, adresse, poststed, postnr, fodselsnr);
            if(kundeRegister.registrerKunde(kunde) )
                System.out.println(  kundeRegister.registrerKunde(kunde) );
            //outputArea.setText(kunde.toString());
            //outputBox.getChildren().removeAll(outputArea);
            //outputBox.getChildren().addAll(outputArea);
            //fjernTekstIFelter();
        }
    }
    
    private void registrerLytter(){
        registrerKunde.setOnAction((ActionEvent event) -> {
            registrerKunde();
        });
    }
}
