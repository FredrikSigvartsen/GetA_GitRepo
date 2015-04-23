/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt;

import javafx.collections.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 *
 * @author Jens
 */
public class SkadeScene {
    
    private VBox skadeBox;
    private TextField fodselsnr, avtalenr, skadeBeskrivelse, sted, utbetalingsBelop;
    private Label avtalenrLabel, fodselsnrLabel, skadeTypeLabel, skadeBeskrivelseLabel, datoLabel, stedLabel, utbetalingsBelopLabel;
    private Button registrerSkade;
    private ComboBox skadeType;
    private DatePicker dato;
    
    public SkadeScene(){                 
        skadeBox = VBoxBuilder.create()
                .alignment(Pos.CENTER_LEFT)
                .spacing(5.0)
                .build();
        
        registrerSkade = new Button("Registrer og utbetal");
        
        avtalenrLabel = new Label("Avtalenummer:");
        avtalenr = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        fodselsnrLabel = new Label("Fødselsnummer:");
        fodselsnr = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        skadeTypeLabel = new Label("Skadetype:");
        skadeType = new ComboBox();
        ObservableList<String> skader = FXCollections.observableArrayList(
                                              "Bilskade", "Båtskade",
                                              "Boligskade", "Reiseskade");
        skadeType.setItems(skader);
        
        skadeBeskrivelseLabel = new Label("Skadebeskrivelse:");
        skadeBeskrivelse = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        datoLabel = new Label("Dato:");
        dato = new DatePicker();
        
        stedLabel = new Label("Sted:");
        sted = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        utbetalingsBelopLabel = new Label("Utbetalingsbeløp:");
        utbetalingsBelop = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        skadeBox.getChildren().addAll(avtalenrLabel, avtalenr, fodselsnrLabel,
                fodselsnr, skadeTypeLabel, skadeType, skadeBeskrivelseLabel, skadeBeskrivelse, datoLabel, dato, stedLabel, sted, utbetalingsBelopLabel, 
                utbetalingsBelop, registrerSkade);
    
    }
    
    public VBox getBox(){
        return skadeBox;
    }
}
