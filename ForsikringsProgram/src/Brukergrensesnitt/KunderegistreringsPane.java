/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 *
 * @author Jens
 */
public class KunderegistreringsPane {
    
    private TextField fornavn, etternavn, adresse, postnr, poststed, fodselsnr;    
    private Label fornavnLabel, etternavnLabel, adresseLabel, postnrLabel, poststedLabel, fodselsnrLabel;
    private Button registrerKunde;
    
    public KunderegistreringsPane(){
        kundeRegistreringSkjema();
    }
    
    public void kundeRegistreringSkjema(){
        //kundebehandlingsFaner();
        kundeRegistreringsBox = VBoxBuilder.create()
                .alignment(Pos.CENTER_LEFT)
                .spacing(5.0)
                .build();
        
        registrerKunde = new Button("Registrer");
        /*registrer.setOnAction((ActionEvent event) -> {
           registrerKunde();
        });*/
        
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
        
        /*kundeRegistreringsBox.getChildren().addAll(fornavnLabel, fornavn, etternavnLabel,
                etternavn, adresseLabel, adresse, postnrLabel, postnr, poststedLabel, 
                poststed, fodselsnrLabel, fodselsnr, registrerKunde);*/
        add(fornavnLabel, 1, 1);
        add
        kundeBox = new VBox();
        kundeFaner = new KundebehandlingsFaner();
        kundeBox.getChildren().addAll(kundeFaner.getMeny(), kundeRegistreringsBox);
        /*layout = new BorderPane();
        layout.setCenter(kundeBox);
        layout.setTop(faneMeny());
        kundeScene1 = new Scene(layout, StartGUI.getSkjermBredde(), StartGUI.getSkjermHoyde());*/
    }
}
