/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt.kundebehandling;

import forsikringsprogram.*;
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
    private Label fodselsnrLabel, avtalenrLabel;
    private Button siOppForsikring;
    private Kunderegister kundeRegister;
    private TextArea output;
    
    public SioppforsikringsLayout(Kunderegister register, TextArea output){
        siOppForsikringsSkjema();
        siOppLytter();
        this.kundeRegister = register;
        this.output = output;
    }//end of constructor
    
    /**
     * Oppretter skjema for oppsigelse av forsiking
     */
    public void siOppForsikringsSkjema(){
        
        siOppForsikring = new Button("Si opp forsikring");
        
        fodselsnrLabel = new Label("Fødselsnummer:");
        fodselsnr = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        avtalenrLabel = new Label("Avtalenr:");
        avtalenr = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        setVgap(10);
        setHgap(10);
        add(fodselsnrLabel, 1, 1);
        add(fodselsnr, 2, 1);
        add(avtalenrLabel, 1, 2);
        add(avtalenr, 2, 2);
        GridPane.setHalignment(siOppForsikring, HPos.CENTER);
        add(siOppForsikring, 1, 3, 2, 1);
    }//end of methd siOppForsikringsSkjema()
    
    /**
     * Validerer inputfelter for å så registrere forsikrings oppsigelsen
     */
    public void siOppForsikring(){
        try{
            String fodselsnr = this.fodselsnr.getText();
            int avtalenr = Integer.parseInt(this.avtalenr.getText());
            if(fodselsnr.trim().equals("") || this.avtalenr.getText().trim().equals("")){
                Alert fyllInnAltMelding = new Alert(Alert.AlertType.WARNING);
                fyllInnAltMelding.setTitle("Feil i Inntasting");
                fyllInnAltMelding.setHeaderText("Tomme felter");
                fyllInnAltMelding.setContentText("Venligst fyll inn alle feltene");
                fyllInnAltMelding.showAndWait();
            }
            kundeRegister.siOppForsikring(fodselsnr, avtalenr);
            output.setText(kundeRegister.siOppForsikring(fodselsnr, avtalenr));
            add(output, 1, 8, 2, 1);
        }
        catch(NumberFormatException nfe){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Feil i Inntasting");
            alert.setHeaderText("Feil tallformat");
            alert.setContentText("Venligs fyll inn kun tall\n" + nfe.getMessage());
            alert.showAndWait();
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
