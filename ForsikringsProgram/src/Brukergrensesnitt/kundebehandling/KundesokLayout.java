/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt.kundebehandling;

import forsikringsprogram.*;
import javafx.collections.*;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 *
 * @author Jens
 */
public class KundesokLayout extends GridPane{
    
    private TextField fodsels_avtalenr;    
    private Label fodsels_avtalenrLabel, forsikringstypeLabel;
    private Button sok;
    private ComboBox forsikringsType;
    private Kunderegister kundeRegister;
    
    public KundesokLayout(Kunderegister register){
        kundeSokSkjema();
        this.kundeRegister = register;
    }
    
    public void kundeSokSkjema(){
        
        sok = new Button("Søk");
        
        fodsels_avtalenrLabel = new Label("Fødselsnummer:");
        fodsels_avtalenr = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        forsikringstypeLabel = new Label("Forsikrings type:");
        forsikringsType = new ComboBox();
        ObservableList<String> forsikringerTilSok = FXCollections.observableArrayList(
                                              "Bilforsikring", "Båtforsikring",
                                              "Boligforsikring", "Reiseforsikring");
        forsikringsType.setItems(forsikringerTilSok);
        
        setVgap(10);
        setHgap(10);
        add(fodsels_avtalenrLabel, 1, 1);
        add(fodsels_avtalenr, 2, 1);
        add(forsikringstypeLabel, 1, 2);
        add(forsikringsType, 2, 2);
        GridPane.setHalignment(sok, HPos.CENTER);
        add(sok, 1, 3, 2, 1);
    }
}
