/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt.kundebehandling;

import Brukergrensesnitt.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 *
 * @author fredr_000
 */
public class RegistrerSkadeLayout extends GridPane {
    
    private VBox skadeInfoBox;
    private VBox ekstraInfoBox;
    private Border kantlinje;
    
    public RegistrerSkadeLayout(){
        opprettRegisteringLayout();
    }
    
    private void opprettRegisteringLayout(){
        skadeInfoBox =  skadeInfoBox();
        ekstraInfoBox = ekstraInfoBox();
        
        addColumn( 1, skadeInfoBox);
        addColumn( 2, ekstraInfoBox);
        setBorder(GUI.KANTLINJE);
        
    }// end of method opprettRegistreringsLayout
    
    //Returnerer en VBox(VertikalBoks) hvor brukeren skal skrive inn fødselsnr, velge skadetype, og beskrive skaden. 
    private VBox skadeInfoBox(){
        VBox box = new VBox(8);
        box.setPadding(GUI.PADDING);
//        box.setBorder(KundePane.KANTLINJE);
        
        //Labels
        Label fodselsNrLabel = new Label("Kundens fødselsnummer:");
        Label skadeTypeLabel = new Label("Skadetype:");
        Label skadeBeskrivelseLabel = new Label("Beskrivelse av skaden:");
        Label taksteringsLabel = new Label("Taksteringsbeløpet for skaden:");
        
        //TextInputs
        TextField fodselsNr = new TextField();
        fodselsNr.setMinWidth(50);
        fodselsNr.setPrefWidth(50);
        fodselsNr.setMaxWidth(400);
        
        ChoiceBox skadeType = new ChoiceBox();
        skadeType.getItems().addAll("Bolig", "Båt", "Bil", "Reise");
        
        TextArea skadeBeskrivelse = new TextArea();
        skadeBeskrivelse.setMinWidth(400);
        skadeBeskrivelse.setPrefWidth(400);
        skadeBeskrivelse.setMaxWidth(800);
        skadeBeskrivelse.setMinHeight(200);
        skadeBeskrivelse.setPrefHeight(200);
        skadeBeskrivelse.setMaxHeight(800);
        
        TextField takst = new TextField();
        takst.setMinWidth(100);
        takst.setPrefWidth(100);
        takst.setMaxWidth(400);
        
        
        box.getChildren().addAll(fodselsNrLabel, fodselsNr, 
                                 skadeTypeLabel, skadeType, 
                                 taksteringsLabel, takst,
                                 skadeBeskrivelseLabel,  skadeBeskrivelse
                                 );
        return box;
    }// end of method genereringAvUtbetlingBox()
    
    
    // En vertikal boks med info om dato og tidspunkt for inntruffet skade, og input for kontaktinformasjon
    private VBox ekstraInfoBox(){
        VBox box = new VBox(8);
        box.setPadding(GUI.PADDING);
//        box.setBorder(KundePane.KANTLINJE);
        
        //TextInputs
        DatePicker datoVelger = new DatePicker();
        TextField tidspunktInput = new TextField();
        tidspunktInput.setMinWidth(40);
        tidspunktInput.setPrefWidth(80);
        tidspunktInput.setMaxWidth(80);
        
        TextField erstatningInput = new TextField();
        erstatningInput.setEditable(false);
        
        TextArea vitne = new TextArea();
        vitne.setMinWidth(400);
        vitne.setPrefWidth(400);
        vitne.setMaxWidth(800);
        vitne.setMinHeight(200);
        vitne.setPrefHeight(200);
        vitne.setMaxHeight(800);
        
        //Legger til dataInputs og beskrivende labels.
        box.getChildren().addAll( new Label("Dato inntruffet:"), datoVelger,
                                  new Label("Tidspunkt inntruffet:"), tidspunktInput,
                                  new Label("Erstatningbeløp kunden får utbetalt:"), erstatningInput,
                                  new Label("Kontaktinformasjon til eventuelle vitner:"), vitne );
        return box;
    }// end of method ekstraInfoBox()
    
}// end of class RegistrerSkadeLayout
