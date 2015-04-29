/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt.kundebehandling;

import Brukergrensesnitt.GUI;
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
    private Label fodselsnrLabel, avtalenrLabel, siOppLabel;
    private Button siOppForsikring;
    private Kunderegister kundeRegister;
    
    public SioppforsikringsLayout(Kunderegister register){
        siOppForsikringsSkjema();
        siOppLytter();
        this.kundeRegister = register;
    }//end of constructor
    
    /**
     * Oppretter skjema for oppsigelse av forsiking
     */
    public void siOppForsikringsSkjema(){
        
        siOppForsikring = new Button("Si opp forsikring");
        
        fodselsnrLabel = new Label("Fødselsnummer:");
        fodselsnr = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        
        avtalenrLabel = new Label("Avtalenr:");
        avtalenr = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
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
     * Validerer inputfelter for å så registrere forsikrings oppsigelsen
     */
    public void siOppForsikring(){
        try{
            String fodselsnr = this.fodselsnr.getText().trim();
            int avtalenr = Integer.parseInt(this.avtalenr.getText().trim());
            siOppLabel = new Label("");
            siOppLabel.setText(kundeRegister.siOppForsikring(fodselsnr, avtalenr));
            add(siOppLabel, 1, 4, 1, 2);
            //output.setText(kundeRegister.siOppForsikring(fodselsnr, avtalenr));
            //add(output, 1, 8, 2, 1);
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
