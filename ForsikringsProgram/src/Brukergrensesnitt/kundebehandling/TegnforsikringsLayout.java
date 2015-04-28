/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt.kundebehandling;

import forsikringsprogram.*;
import Brukergrensesnitt.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
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
    private TextArea output;
    
    public TegnforsikringsLayout(Kunderegister register, TextArea output){
        tegnForsikringsSkjema();
        comboLytter();
        tegnForsikringLytter();
        this.kundeRegister = register;
        this.output = output;
    }//end of constructor
    
    /**
     * Oppretter de felles input feltene i skjema for tegning av forsikring
     * og kaller på metodene som oppretter de inputfeltene som er unike for 
     * hver enkelt forsikring
     */
    private void tegnForsikringsSkjema(){
        bilforsikringsFelter();
        batforsikringsFelter();
        boligforsikringsFelter();
        reiseforsikringsFelter();
        
        
        tegnForsikring = new Button("Tegn forsikring");
        
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
        GridPane.setHalignment(tegnForsikring, HPos.CENTER);
        add(tegnForsikring, 1, 7, 2, 1);
    }//end of method tegnForsikringsSkjema()
    
    /**
     * Oppretter de feltene som er unike for bilforsikring
     */
    private void bilforsikringsFelter(){
        bilforsikringFelter = new GridPane();
        
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
    }//End of method bilforsikringFelter()
    
    /**
     * Oppretter de feltene som er unike for båtforsikring
     */
    private void batforsikringsFelter(){
        batforsikringFelter = new GridPane();
        
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
    }//End of method BatforsikringFelter
    
    /**
     * Oppretter de feltene som er unike for boligforsikring
     */
    private void boligforsikringsFelter(){
        boligforsikringFelter = new GridPane();
        
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
    }//End of method BoligforsikringFeilter
    
    /**
     * Oppretter de feltene som er unike for reiseforsikring
     */
    private void reiseforsikringsFelter(){
        reiseforsikringFelter = new GridPane();
        
        omradeLabel = new Label("Område:");
        omrade = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        
        reiseforsikringFelter.setVgap(10);
        reiseforsikringFelter.setHgap(10);
        reiseforsikringFelter.add(omradeLabel, 0, 1);
        reiseforsikringFelter.add(omrade, 1, 1);
    }//End of method BoligforsikringFeilter
    
    /**
     * Registrerer en bilforsikring når metoden kalles
     */
    private void registrerBilforsikring(){
        String fodselsnr = this.fodselsnr.getText().trim();
        String betingelser = this.betingelser.getText().trim();
        String registreringsnr = this.registreringsnr.getText().trim();
        String merke = this.merke.getText().trim();
        String modell = this.modell.getText().trim();
        double forsikringsbelop = Double.parseDouble(this.forsikringsbelop.getText().trim());
        int registreringsar = Integer.parseInt(this.registreringsar.getText().trim());
        int kjorelengde = Integer.parseInt(this.kjorelengde.getText().trim());
        int prisPerKm = Integer.parseInt(this.prisPerKm.getText().trim());
        Bilforsikring bilforsikring = new Bilforsikring(betingelser, forsikringsbelop,
        registreringsnr, merke, modell, registreringsar, kjorelengde, prisPerKm);
        output.setText(kundeRegister.tegnForsikring(bilforsikring, fodselsnr));
        getChildren().remove(output);
        add(output, 1, 8, 2, 1);
    }//end of method registrerBilforsikring()
    
    /**
     * Registrerer en båtforsikring når metoden kalls
     */
    private void registrerBatforsikring(){
        String fodselsnr = this.fodselsnr.getText().trim();
        String betingelser = this.betingelser.getText().trim();
        String batRegistreringsnr = this.batRegistreringsnr.getText().trim();
        String batMerke = this.batMerke.getText().trim();
        String batModell = this.batModell.getText().trim();
        String motorType = this.motorType.getText().trim();
        double forsikringsbelop = Double.parseDouble(this.forsikringsbelop.getText().trim());
        int arsmodell = Integer.parseInt(this.arsmodell.getText().trim());
        int motorStyrke = Integer.parseInt(this.motorStyrke.getText().trim());
        Baatforsikring batforsikring = new Baatforsikring(betingelser, forsikringsbelop,
                batRegistreringsnr, arsmodell, motorStyrke, batMerke, batModell, motorType);
        output.setText(kundeRegister.tegnForsikring(batforsikring, fodselsnr));
        getChildren().remove(output);
        add(output, 1, 8, 2, 1);
    }//end of method registrerBatforsikring()
    
    /**
     * Registrerer en boligforsikring når metoden kalles
     */
    private void registrerBoligforsikring(){
        String fodselsnr = this.fodselsnr.getText().trim();
        String betingelser = this.betingelser.getText().trim();
        String gateAdresse = this.gateAdresse.getText().trim();
        String boligType = this.boligType.getText().trim();
        String byggemateriale = this.byggemateriale.getText().trim();
        String standard = this.standard.getText().trim();
        String postnr = this.postnr.getText().trim();
        double forsikringsbelop = Double.parseDouble(this.forsikringsbelop.getText().trim());
        int byggear = Integer.parseInt(this.byggear.getText().trim());
        int antallKVM = Integer.parseInt(this.antallKVM.getText().trim());
        Boligforsikring boligforsikring = new Boligforsikring(betingelser, forsikringsbelop,
                gateAdresse, boligType, byggemateriale, standard, postnr, byggear, antallKVM);
        output.setText(kundeRegister.tegnForsikring(boligforsikring, fodselsnr));
        getChildren().remove(output);
        add(output, 1, 8, 2, 1);
    }//end of method registrerBoligforsikring()
    
    /**
     * Registrerer en reiseforsikring når metoden kalles
     */
    private void registrerReiseforsikring(){
        String fodselsnr = this.fodselsnr.getText().trim();
        String betingelser = this.betingelser.getText().trim();
        String omrade = this.omrade.getText().trim();
        double forsikringsbelop = Double.parseDouble(this.forsikringsbelop.getText().trim());
        Reiseforsikring reiseforsikring = new Reiseforsikring(betingelser, forsikringsbelop, omrade);
        output.setText(kundeRegister.tegnForsikring(reiseforsikring, fodselsnr));
        getChildren().remove(output);
        add(output, 1, 8, 2, 1);
    }//end of method registrerReiseforsikring
    
    
    /**
     * Sjekker om feltene i layoutet er tomme, og gir brukeren en melding om hva som må fylles inn.
     * @return Returnerer true om alle feltene er fylt inn, og false om noe mangler
     */
    private boolean sjekkFelter(){
        if( fodselsnr.getText().trim().isEmpty()){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn fødselsnummer");
            return false;
        }
        
        else if( betingelser.getText().isEmpty()){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn betingelser");
            return false;
        }
        
        else if( forsikringsbelop.getText().isEmpty()){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn forsikringsbeløp");
            return false;
        }
        
        switch (forsikringsTypeString){
            case "bilforsikring":
                if( registreringsnr.getText().trim().isEmpty()){
                    GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn registreringsnr");
                    return false;
                }
                else if( merke.getText().isEmpty()){
                    GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn merke");
                    return false;
                }
                else if( modell.getText().isEmpty()){
                    GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn modell");
                    return false;
                }
                else if( registreringsar.getText().isEmpty()){
                    GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn registreringsår");
                    return false;
                }
                else if( kjorelengde.getText().isEmpty()){
                    GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn kjørelengde");
                    return false;
                }
                else if( prisPerKm.getText().isEmpty()){
                    GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn pris per km");
                    return false;
                }
                return true;
            case "batforsikring":
                if( batRegistreringsnr.getText().trim().isEmpty()){
                    GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn registreringsnr");
                    return false;
                }
                else if( batMerke.getText().isEmpty()){
                    GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn merke");
                    return false;
                }
                else if( batModell.getText().isEmpty()){
                    GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn modell");
                    return false;
                }
                else if( arsmodell.getText().isEmpty()){
                    GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn årsmodell");
                    return false;
                }
                else if( motorType.getText().isEmpty()){
                    GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn motortype");
                    return false;
                }
                else if( motorStyrke.getText().isEmpty()){
                    GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn motorstyrke");
                    return false;
                }
                return true;
            case "boligforsikring":
                if( gateAdresse.getText().trim().isEmpty()){
                    GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn registreringsnr");
                    return false;
                }
                else if( boligType.getText().isEmpty()){
                    GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn merke");
                    return false;
                }
                else if( byggemateriale.getText().isEmpty()){
                    GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn modell");
                    return false;
                }
                else if( standard.getText().isEmpty()){
                    GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn registreringsår");
                    return false;
                }
                else if( postnr.getText().isEmpty()){
                    GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn kjørelengde");
                    return false;
                }
                else if( byggear.getText().isEmpty()){
                    GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn byggeår");
                    return false;
                }
                else if( antallKVM.getText().isEmpty()){
                    GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn antall KVM");
                    return false;
                }
                return true;
            case "reiseforsikring":
                if( omrade.getText().isEmpty()){
                    GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn område");
                    return false;
                }
                return true;
        }
        return false;
    }// end of method sjekkFelter()
    
    /**
     * Sjekker om kunden er totalkunde, og viser et meldingsvindu når kunden blir totalkunde
     */
    private void erTotalKunde(){
        ForsikringsKunde kunde = kundeRegister.finnKunde(fodselsnr.getText().trim());
        if(kunde.blirTotalKunde()){
            GUI.visInputFeilMelding("Total kunde", kunde.getEtternavn() +", " + kunde.getFornavn() + " har nå blitt total kunde!");
        }
    }//end of method erTotalKunde()
    
    /**
     * Sjekker alle innputfeltene, og registrerer en forsikring av valgt type
     */
    private void registrerForsikring(){
        if(!sjekkFelter())
                return;
        try{
            switch (forsikringsTypeString){
                case "bilforsikring":
                    registrerBilforsikring();
                    erTotalKunde();
                    break;
                case "batforsikring":
                    registrerBatforsikring();
                    erTotalKunde();
                    break;
                case "boligforsikring":
                    registrerBoligforsikring();
                    erTotalKunde();
                    break;
                case "reiseforsikring":
                    registrerReiseforsikring();
                    erTotalKunde();
                    break;
            }
        }
        catch(NumberFormatException | NullPointerException e){
            GUI.visProgramFeilMelding(e);
            return;
        }
    }//end of method registrerForsikring()
    
    /**
     * Legger til en lytter til forsikringsType comboboxen, og legger til de
     * korrekte inputfeltene for den forsikringstypen
     */
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
    }//end of method comboLytter()
    
    /**
     * Legger til en lytter på tegnForsikring knappen
     */
    private void tegnForsikringLytter(){
        tegnForsikring.setOnAction((ActionEvent event) -> {
            registrerForsikring();
        });
    }//end of method tengForsikringsLytter()
    
}//end of class TegnforsikringsLayout
