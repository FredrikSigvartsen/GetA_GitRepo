/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 *
 * @author Jens
 */
public class TegnforsikringsPane extends GridPane{
    
    private TextField fodselsnr, forsikringspremie, forsikringsbelop, betingelser, registreringsnr, merke, modell, registreringsar, kjorelengde, prisPerKm;    
    private Label fodselsnrLabel, forsikringstypeLabel, forsikringspremieLabel, forsikringsbelopLabel, betingelserLabel, registreringsnrLabel, merkeLabel, modellLabel, registreringsarLabel, kjorelengdeLabel, prisPerKmLabel;
    private Button tegnForsikring;
    private ComboBox forsikringsType;
    
    public TegnforsikringsPane(){
        tegnForsikringsSkjema();
    }
    
    public void tegnForsikringsSkjema(){
        forsikringsType = new ComboBox();
        
        tegnForsikring = new Button("Tegn forsikring");
        
        registreringsnrLabel = new Label("Registreringsnummer:");
        registreringsnr = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        merkeLabel = new Label("Merke:");
        merke = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
                
        modellLabel = new Label("Modell:");
        modell = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
                
        registreringsarLabel = new Label("Registrerings år:");
        registreringsar = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
                
        kjorelengdeLabel = new Label("Kjørelengde:");
        kjorelengde = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
                
        prisPerKmLabel = new Label("Pris per KM:");
        prisPerKm = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        //Slutt Bilforsikring
        /*
        //Båtforsikring
        //TextField registreringsnr;
        //TextField merke;
        //TextField modell;
        TextField arsmodell;
        TextField motorType;
        TextField motorStyrke;
        
        //Label registreringsnrLabel;
        //Label merkeLabel;
        //Label modellLabel;
        Label arsmodellLabel;
        Label motorTypeLabel;
        Label motorStyrkeLabel;
        //Slutt Båtforsikring
        
        //Boligforsikring
        TextField gateAdresse;
        TextField postnr;
        TextField byggear;
        TextField boligType;
        TextField byggemateriale;
        TextField standard;
        TextField antallKVM;
        
        Label gateAdresseLabel;
        Label postnrLabel;
        Label byggearLabel;
        Label boligTypeLabel;
        Label byggematerialeLabel;
        Label antallKVMLabel;
        //Slutt Boligforsikring
        
        //Reiseforsikring
        TextField omrade;
        
        Label omradeLabel;
        //Slutt Reiseforsikring
        
        Label fodselsnrLabel;
        Label forsikringstypeLabel;
        Label forsikringspremieLabel;
        Label forsikringsbelopLabel;
        Label betingelserLabel;*/
        
        tegnForsikring = new Button("Tegn forsikring");
        
        fodselsnrLabel = new Label("Fødselsnummer:");
        fodselsnr = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        forsikringstypeLabel = new Label("Forsikrings type:");
        ObservableList<String> forsikringer = FXCollections.observableArrayList(
                                              "Bilforsikring", "Båtforsikring",
                                              "Boligforsikring", "Reiseforsikring");
        forsikringsType.setItems(forsikringer);
        
        forsikringspremieLabel = new Label("Forsikringspremie:");
        forsikringspremie = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        forsikringsbelopLabel = new Label("Forsikringsbeløp:");
        forsikringsbelop = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        betingelserLabel = new Label("Betingelser:");
        betingelser = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        setVgap(10);
        setHgap(10);
        add(fodselsnrLabel, 1, 1);
        add(fodselsnr, 2, 1);
        add(forsikringstypeLabel, 1, 2);
        add(forsikringsType, 2, 2);
        add(forsikringspremieLabel, 1, 3);
        add(forsikringspremie, 2, 3);
        add(forsikringsbelopLabel, 1, 4);
        add(forsikringsbelop, 2, 4);
        add(betingelserLabel, 1, 5);
        add(betingelser, 2, 5);
        /*GridPane.setHalignment(registrerKunde, HPos.CENTER);
        add(registrerKunde, 1, 7, 2, 1);*/
    }
    
    public void comboLytter(){
        forsikringsType.valueProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                String valg = (String) t1;
                if(valg.equals("Bilforsikring")){
                    System.out.println("(Y)");
                }
            }
        });
    }
}
