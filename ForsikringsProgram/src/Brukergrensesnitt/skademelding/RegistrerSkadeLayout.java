/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt.skademelding;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 *
 * @author fredr_000
 */
public class RegistrerSkadeLayout extends GridPane {
    
    private VBox skadeInfoBox;
    private VBox ekstraInfoBox;
    
    public RegistrerSkadeLayout(){
        opprettRegisteringLayout();
        
    }
    
    private void opprettRegisteringLayout(){
        skadeInfoBox =  skadeInfoBox();
        ekstraInfoBox = ekstraInfoBox();
        
        addColumn( 1, skadeInfoBox);
        addColumn( 2, ekstraInfoBox);
        
    }// end of method opprettRegistreringsLayout
    
    //Returnerer en VBox(VertikalBoks) hvor brukeren skal skrive inn fødselsnr, velge skadetype, og beskrive skaden. 
    private VBox skadeInfoBox(){
        VBox box = new VBox(8);
        box.setPadding(new Insets(15));
        
        //Labels
        Label fodselsNrLabel = new Label("Kundens fødselsnummer:");
        Label skadeTypeLabel = new Label("Skadetype:");
        Label skadeBeskrivelseLabel = new Label("Beskrivelse av skaden:");
        Label taksteringsLabel = new Label("Taksteringsbeløpet for skaden:");
        
        //TextInputs
        TextField fodselsNr = new TextField();
        ChoiceBox skadeType = new ChoiceBox();
        skadeType.getItems().addAll("Bolig", "Båt", "Bil", "Reise");
        TextArea skadeBeskrivelse = new TextArea();
        TextField takst = new TextField();
        
        
        box.getChildren().addAll(fodselsNrLabel, fodselsNr, 
                                 skadeTypeLabel, skadeType, 
                                 skadeBeskrivelseLabel,  skadeBeskrivelse,
                                 taksteringsLabel, takst);
        return box;
    }// end of method genereringAvUtbetlingBox()
    
    
    // En vertikal boks med info om dato og tidspunkt for inntruffet skade, og input for kontaktinformasjon
    private VBox ekstraInfoBox(){
        VBox box = new VBox(8);
        box.setPadding(new Insets(15));
        //Labels
        Label datoLabel = new Label("Dato inntruffet:");
        Label tidspunktLabel = new Label("Tidspunkt inntruffet:");
        Label vitneLabel = new Label("Kontaktinformasjon til eventuelle vitner:");
        
        //TextInputs
        DatePicker dato = new DatePicker();
        TextField tidspunkt = new TextField();
        TextArea vitne = new TextArea();
        
        //Legger til dataInputs og beskrivende labels.
        box.getChildren().addAll( datoLabel, dato,
                                  tidspunktLabel, tidspunkt,
                                  vitneLabel, vitne );
        return box;
    }// end of method ekstraInfoBox()
    
}// end of class RegistrerSkadeLayout
