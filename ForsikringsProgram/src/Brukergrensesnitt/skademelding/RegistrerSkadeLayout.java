/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt.skademelding;

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
        
    }// end of method opprettRegistreringsLayout
    
    //Returnerer en VBox(VertikalBoks) hvor brukeren skal skrive inn fødselsnr, velge skadetype, og beskrive skaden. 
    private VBox skadeInfoBox(){
        VBox box = new VBox(8);
        box.setBorder(SkademeldingsPane.KANTLINJE);
        
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
        skadeBeskrivelse.setMinWidth(50);
        skadeBeskrivelse.setPrefWidth(50);
        skadeBeskrivelse.setMaxWidth(400);
        
        TextField takst = new TextField();
        takst.setMinWidth(50);
        takst.setPrefWidth(50);
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
        box.setBorder(SkademeldingsPane.KANTLINJE);
        //Labels
        Label datoLabel = new Label("Dato inntruffet:");
        Label tidspunktLabel = new Label("Tidspunkt inntruffet:");
        Label vitneLabel = new Label("Kontaktinformasjon til eventuelle vitner:");
        
        //TextInputs
        DatePicker dato = new DatePicker();
        TextField tidspunkt = new TextField();
        tidspunkt.setMinWidth(40);
        tidspunkt.setPrefWidth(80);
        tidspunkt.setMaxWidth(80);
        
        
        TextArea vitne = new TextArea();
        
        //Legger til dataInputs og beskrivende labels.
        box.getChildren().addAll( datoLabel, dato,
                                  tidspunktLabel, tidspunkt,
                                  vitneLabel, vitne );
        return box;
    }// end of method ekstraInfoBox()
    
}// end of class RegistrerSkadeLayout
