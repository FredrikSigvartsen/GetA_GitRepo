/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt.kundebehandling;

import forsikringsprogram.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 *
 * @author Jens
 */
public class TegnforsikringsLayout extends GridPane{
    
    private TextField fodselsnr, forsikringsbelop, betingelser, registreringsnr, 
            merke, modell, registreringsar, kjorelengde, prisPerKm, batRegistreringsnr, batMerke, 
            batModell, arsmodell, motorType, motorStyrke, gateAdresse, postnr, byggear, boligType,
            byggemateriale, standard, antallKVM, omrade;    
    private Label fodselsnrLabel, forsikringstypeLabel, forsikringsbelopLabel, 
            betingelserLabel, registreringsnrLabel, merkeLabel, modellLabel, registreringsarLabel, 
            kjorelengdeLabel, prisPerKmLabel, batRegistreringsnrLabel, batMerkeLabel, batModellLabel, 
            arsmodellLabel, motorTypeLabel, motorStyrkeLabel, gateAdresseLabel, postnrLabel, byggearLabel, 
            boligTypeLabel, byggematerialeLabel, standardLabel, antallKVMLabel, omradeLabel;
    private Button tegnForsikring;
    private ComboBox forsikringsType;
    private GridPane bilforsikringFelter, batforsikringFelter, boligforsikringFelter, reiseforsikringFelter;
    private Kunderegister kundeRegister;
    private String forsikringsTypeString = "";
    
    public TegnforsikringsLayout(Kunderegister register){
        tegnForsikringsSkjema();
        comboLytter();
        tegnForsikringLytter();
        this.kundeRegister = register;
    }
    
    private void tegnForsikringsSkjema(){
        bilforsikringsFelter();
        batforsikringsFelter();
        boligforsikringsFelter();
        reiseforsikringsFelter();
        
        fodselsnrLabel = new Label("Fødselsnummer:");
        fodselsnr = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        forsikringsType = new ComboBox();
        forsikringstypeLabel = new Label("Forsikrings type:");
        ObservableList<String> forsikringer = FXCollections.observableArrayList(
                                              "Bilforsikring", "Båtforsikring",
                                              "Boligforsikring", "Reiseforsikring");
        forsikringsType.setItems(forsikringer);;
        
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
        add(forsikringsbelopLabel, 1, 4);
        add(forsikringsbelop, 2, 4);
        add(betingelserLabel, 1, 5);
        add(betingelser, 2, 5);
        /*GridPane.setHalignment(registrerKunde, HPos.CENTER);
        add(registrerKunde, 1, 7, 2, 1);*/
    }
    
    private void bilforsikringsFelter(){
        bilforsikringFelter = new GridPane();
        
        tegnForsikring = new Button("Tegn bilforsikring");
        
        registreringsnrLabel = new Label("Registreringsnr:");
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
                
        registreringsarLabel = new Label("Registreringsår:");
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
        
        bilforsikringFelter.setVgap(10);
        bilforsikringFelter.setHgap(10);
        bilforsikringFelter.add(registreringsnrLabel, 0, 1);
        bilforsikringFelter.add(registreringsnr, 1, 1);
        bilforsikringFelter.add(merkeLabel, 0, 2);
        bilforsikringFelter.add(merke, 1, 2);
        bilforsikringFelter.add(modellLabel, 0, 3);
        bilforsikringFelter.add(modell, 1, 3);
        bilforsikringFelter.add(registreringsarLabel, 0, 4);
        bilforsikringFelter.add(registreringsar, 1, 4);
        bilforsikringFelter.add(kjorelengdeLabel, 0, 5);
        bilforsikringFelter.add(kjorelengde, 1, 5);
        bilforsikringFelter.add(prisPerKmLabel, 0, 6);
        bilforsikringFelter.add(prisPerKm, 1, 6);
        bilforsikringFelter.add(tegnForsikring, 0, 7, 2, 1);
    }//End of method BilforsikringFeilter
    
    private void batforsikringsFelter(){
        batforsikringFelter = new GridPane();
        
        tegnForsikring = new Button("Tegn båtforsikring");
        
        batRegistreringsnrLabel = new Label("Registreringsnr:");
        batRegistreringsnr = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        batMerkeLabel = new Label("Merke:");
        batMerke = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
                
        batModellLabel = new Label("Modell:");
        batModell = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
                
        arsmodellLabel = new Label("Årsmodell:");
        arsmodell = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
                
        motorTypeLabel = new Label("Motortype:");
        motorType = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
                
        motorStyrkeLabel = new Label("Motorkraft:");
        motorStyrke = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        batforsikringFelter.setVgap(10);
        bilforsikringFelter.setHgap(10);
        batforsikringFelter.add(batRegistreringsnrLabel, 0, 1);
        batforsikringFelter.add(batRegistreringsnr, 1, 1);
        batforsikringFelter.add(batMerkeLabel, 0, 2);
        batforsikringFelter.add(batMerke, 1, 2);
        batforsikringFelter.add(batModellLabel, 0, 3);
        batforsikringFelter.add(batModell, 1, 3);
        batforsikringFelter.add(arsmodellLabel, 0, 4);
        batforsikringFelter.add(arsmodell, 1, 4);
        batforsikringFelter.add(motorTypeLabel, 0, 5);
        batforsikringFelter.add(motorType, 1, 5);
        batforsikringFelter.add(motorStyrkeLabel, 0, 6);
        batforsikringFelter.add(motorStyrke, 1, 6);
        batforsikringFelter.add(tegnForsikring, 0, 7, 2, 1);
    }//End of method BatforsikringFeilter
    
    private void boligforsikringsFelter(){
        boligforsikringFelter = new GridPane();
        
        tegnForsikring = new Button("Tegn boligforsikring");
        
        gateAdresseLabel = new Label("Adresse:");
        gateAdresse = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        postnrLabel = new Label("Postnr:");
        postnr = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
                
        byggearLabel = new Label("Byggeår:");
        byggear = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
                
        boligTypeLabel = new Label("Boligtype:");
        boligType = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
                
        byggematerialeLabel = new Label("Byggemateriale:");
        byggemateriale = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
                
        standardLabel = new Label("Standard:");
        standard = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        antallKVMLabel = new Label("Antall KVM:");
        antallKVM = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        boligforsikringFelter.setVgap(10);
        boligforsikringFelter.setHgap(10);
        boligforsikringFelter.add(gateAdresseLabel, 0, 1);
        boligforsikringFelter.add(gateAdresse, 1, 1);
        boligforsikringFelter.add(postnrLabel, 0, 2);
        boligforsikringFelter.add(postnr, 1, 2);
        boligforsikringFelter.add(byggearLabel, 0, 3);
        boligforsikringFelter.add(byggear, 1, 3);
        boligforsikringFelter.add(boligTypeLabel, 0, 4);
        boligforsikringFelter.add(boligType, 1, 4);
        boligforsikringFelter.add(byggematerialeLabel, 0, 5);
        boligforsikringFelter.add(byggemateriale, 1, 5);
        boligforsikringFelter.add(standardLabel, 0, 6);
        boligforsikringFelter.add(standard, 1, 6);
        boligforsikringFelter.add(antallKVMLabel, 0, 7);
        boligforsikringFelter.add(antallKVM, 1, 7);
        boligforsikringFelter.add(tegnForsikring, 0, 8, 2, 1);
    }//End of method BoligforsikringFeilter
    
    private void reiseforsikringsFelter(){
        reiseforsikringFelter = new GridPane();
        
        tegnForsikring = new Button("Tegn reiseforsikring");
        
        omradeLabel = new Label("Område:");
        omrade = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        
        reiseforsikringFelter.setVgap(10);
        reiseforsikringFelter.setHgap(10);
        reiseforsikringFelter.add(omradeLabel, 0, 1);
        reiseforsikringFelter.add(omrade, 1, 1);
        reiseforsikringFelter.add(tegnForsikring, 0, 2, 2, 1);
    }//End of method BoligforsikringFeilter
    
    public void tegnForsikring(){
        String fodselsnr = this.fodselsnr.getText();
        double forsikringsbelop = Double.parseDouble(this.forsikringsbelop.getText());
        String betingelser = this.betingelser.getText();
        if(fodselsnr.trim().equals("") || forsikringsbelop == 0 || betingelser.trim().equals("")){
            System.out.println("Venligst lyll inn alle feltene");
        }
        else{
            switch (forsikringsTypeString){
                case "bilforsikring":
                    String registreringsnr = this.registreringsnr.getText();
                    String merke = this.merke.getText();
                    String modell = this.modell.getText();
                    int registreringsar = Integer.parseInt(this.registreringsar.getText());
                    int kjorelengde = Integer.parseInt(this.kjorelengde.getText());
                    int prisPerKm = Integer.parseInt(this.prisPerKm.getText());
                    if(registreringsnr.trim().equals("") || merke.trim().equals("") || modell.trim().equals("") 
                            || registreringsar == 0 || kjorelengde == 0 || prisPerKm == 0)
                        System.out.println("Venligst fyll inn alle feltene");
                    else{
                        Bilforsikring bilforsikring = new Bilforsikring(betingelser, forsikringsbelop,
                                registreringsnr, merke, modell, registreringsar, kjorelengde, prisPerKm);
                        System.out.println(kundeRegister.tegnForsikring(bilforsikring, fodselsnr));
                        ForsikringsKunde kunde = kundeRegister.finnKunde(fodselsnr);
                        System.out.println( kunde.visForsikringsliste() );
                    }
                    break;
                case "batforsikring":
                    String batRegistreringsnr = this.batRegistreringsnr.getText();
                    String batMerke = this.batMerke.getText();
                    String batModell = this.batModell.getText();
                    String motorType = this.motorType.getText();
                    int arsmodell = Integer.parseInt(this.arsmodell.getText());
                    int motorStyrke = Integer.parseInt(this.motorStyrke.getText());
                    if(batRegistreringsnr.trim().equals("") || batMerke.trim().equals("") || batModell.trim().equals("") 
                            || motorType.trim().equals("") || arsmodell == 0 || motorStyrke == 0 )
                        System.out.println("Venligst fyll inn alle feltene");
                    else{
                        Baatforsikring batforsikring = new Baatforsikring(betingelser, forsikringsbelop,
                                batRegistreringsnr, arsmodell, motorStyrke, batMerke, batModell, motorType);
                        kundeRegister.tegnForsikring(batforsikring, fodselsnr);
                    }
                    break;
                case "boligforsikring":
                    String gateAdresse = this.gateAdresse.getText();
                    String boligType = this.boligType.getText();
                    String byggemateriale = this.byggemateriale.getText();
                    String standard = this.standard.getText();
                    String postnr = this.postnr.getText();
                    int byggear = Integer.parseInt(this.byggear.getText());
                    int antallKVM = Integer.parseInt(this.antallKVM.getText());
                    if(gateAdresse.trim().equals("") || boligType.trim().equals("") || byggemateriale.trim().equals("") 
                            || standard.trim().equals("") || postnr.trim().equals("") || byggear == 0 || antallKVM == 0 )
                        System.out.println("Venligst fyll inn alle feltene");
                    else{
                        Boligforsikring boligforsikring = new Boligforsikring(betingelser, forsikringsbelop,
                                gateAdresse, boligType, byggemateriale, standard, postnr, byggear, antallKVM);
                        kundeRegister.tegnForsikring(boligforsikring, fodselsnr);
                    }
                    break;
                case "reiseforsikring":
                    String omrade = this.omrade.getText();
                    if(omrade.trim().equals(""))
                        System.out.println("Venligst fyll inn alle feltene");
                    else{
                        Reiseforsikring reiseforsikring = new Reiseforsikring(betingelser, forsikringsbelop, omrade);
                        kundeRegister.tegnForsikring(reiseforsikring, fodselsnr);
                    }
                    break;
            }
            
        }
    }
    
    private void comboLytter(){
        forsikringsType.valueProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                switch ((String) t1) {
                    case "Bilforsikring":
                        getChildren().removeAll(batforsikringFelter, bilforsikringFelter, boligforsikringFelter, reiseforsikringFelter);
                        add(bilforsikringFelter, 1, 6, 2, 1);
                        forsikringsTypeString = "bilforsikring";
                        break;
                    case "Båtforsikring":
                        getChildren().removeAll(bilforsikringFelter, batforsikringFelter, boligforsikringFelter, reiseforsikringFelter);
                        add(batforsikringFelter, 1, 6, 2, 1);
                        forsikringsTypeString = "batforsikring";
                        break;
                    case "Boligforsikring":
                        getChildren().removeAll(bilforsikringFelter, batforsikringFelter, boligforsikringFelter, reiseforsikringFelter);
                        add(boligforsikringFelter, 1, 6, 2, 1);
                        forsikringsTypeString = "boligforsikring";
                        break;
                    case "Reiseforsikring":
                        getChildren().removeAll(bilforsikringFelter, batforsikringFelter, boligforsikringFelter, reiseforsikringFelter);
                        add(reiseforsikringFelter, 1, 6, 2, 1);
                        forsikringsTypeString = "reiseforsikring";
                        break;
                    default:
                        getChildren().removeAll(bilforsikringFelter, batforsikringFelter, boligforsikringFelter, reiseforsikringFelter);
                        add(bilforsikringFelter, 1, 6, 2, 1);
                        forsikringsTypeString = "default";
                }
            }
        });
    }
    
    private void tegnForsikringLytter(){
        tegnForsikring.setOnAction((ActionEvent event) -> {
            tegnForsikring();
            System.out.println("Tegner forsikring");
        });
    }
}
