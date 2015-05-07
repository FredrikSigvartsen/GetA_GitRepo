/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt.kundebehandling;

import Brukergrensesnitt.GUI;
import forsikringsprogram.Kunderegister;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFieldBuilder;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import static javafx.scene.layout.BorderStroke.THIN;
import static javafx.scene.layout.BorderStrokeStyle.SOLID;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import static javafx.scene.paint.Color.DARKGRAY;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Jens
 */
public class OkonomiPane extends GridPane{
    private TextField fodselsnr;
    private DatePicker datoStart, datoSlutt;
    private Label fodselsnrFeil;
    private ComboBox forsikringsType, visningsType;
    private RadioButton utbetalingerAlle, utbetalingerType, utbetalingerKunde, inntektAlle, inntektType, inntektKunde;
    private ToggleGroup gruppe;
    private Button visOkonomi;
    private VBox radioKnappBox;
    private TitledPane valgTypePane, valgKundePane, valgAllePane;
    private Kunderegister kundeRegister;
    private Border radioKnappKant = new Border( new BorderStroke(DARKGRAY,SOLID, new CornerRadii(5), THIN, new Insets(10)) );
    private Font tekstStr = Font.font(null, 16);
    
    public OkonomiPane(Kunderegister register){
        radioKnapper();
        visOkonomiSkjema();
        //visOkonomiLytter();
        this.kundeRegister = register;
        //tekstFeltLyttere();
        radioKnappLytter();
    }//end of constructor
    
    /**
     * Oppretter og viser radioknappene til brukeren
     */
    private void radioKnapper(){
        radioKnappBox = new VBox();
        
        gruppe = new ToggleGroup();
        
        Label visLabel = new Label("Vis");
        visLabel.setFont(GUI.OVERSKRIFT);
        
        utbetalingerAlle = new RadioButton("Alle utbetalinger");
        utbetalingerAlle.setFont(tekstStr);
        utbetalingerAlle.setId("utbetalingerAlle");
        utbetalingerAlle.setToggleGroup(gruppe);
        
        utbetalingerType = new RadioButton("Utbetalinger per forsikringstype");
        utbetalingerType.setFont(tekstStr);
        utbetalingerType.setId("utbetalingerType");
        utbetalingerType.setToggleGroup(gruppe);
        
        utbetalingerKunde = new RadioButton("Utbetalinger per kunde");
        utbetalingerKunde.setFont(tekstStr);
        utbetalingerKunde.setId("utbetalingerKunde");
        utbetalingerKunde.setToggleGroup(gruppe);
        
        inntektAlle = new RadioButton("Alle inntekter");
        inntektAlle.setFont(tekstStr);
        inntektAlle.setId("inntektAlle");
        inntektAlle.setToggleGroup(gruppe);
        
        inntektType = new RadioButton("Inntekter per forsikringstype");
        inntektType.setFont(tekstStr);
        inntektType.setId("inntektType");
        inntektType.setToggleGroup(gruppe);
        
        inntektKunde = new RadioButton("Inntekter per kunde");
        inntektKunde.setFont(tekstStr);
        inntektKunde.setId("inntektKunde");
        inntektKunde.setToggleGroup(gruppe);
        
        //Legger til Radioknapper
        radioKnappBox.getChildren().addAll(visLabel, utbetalingerAlle, utbetalingerType, utbetalingerKunde, inntektAlle, inntektType, inntektKunde);
        radioKnappBox.setBorder(radioKnappKant);
        radioKnappBox.setPadding(GUI.PADDING);
        radioKnappBox.setSpacing(30);
        
    }//end of method radioKnapper()
    
    /**
     * 
     */
    private void valgType(){
        valgTypePane = new TitledPane();
        valgTypePane.setExpanded(false);
        
    }
    
    private void valgKunde(){
        
    }
    
    private void valgAlle(){
        
    }
    
    /**
     * Oppretter skjema for oppsigelse av forsiking
     */
    public void visOkonomiSkjema(){
        Label visOkonomiLabel = new Label("Økonomi");
        visOkonomiLabel.setFont(GUI.OVERSKRIFT);
        
        add(visOkonomiLabel, 1, 1);
        add(radioKnappBox, 1, 2);
        /*
        visOkonomi = new Button("Vis Økonomi");
        
        fodselsnr = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        fodselsnrFeil = new Label("*");
        
        forsikringsType = new ComboBox();
        ObservableList<String> forsikringer = FXCollections.observableArrayList(
                                              "Bilforsikring", "Båtforsikring",
                                              "Boligforsikring", "Reiseforsikring");
        forsikringsType.setItems(forsikringer);
        
        datoStart = new DatePicker();
        
        datoSlutt = new DatePicker();
        
        visningsType = new ComboBox();
        ObservableList<String> visninger = FXCollections.observableArrayList(
                                              "Alle utbetalinger", "Utbeltalinger pr forsikringstype",
                                              "Boligforsikring", "Reiseforsikring");
        visningsType.setItems(visninger);
        
        
        setVgap(10);
        setHgap(10);
        
        Label visOkonomiLabel = new Label("Økonomi");
        visOkonomiLabel.setFont(GUI.OVERSKRIFT);
        
        //legger til kolonne 1
        add(visOkonomiLabel, 1, 1, 2, 1);
        add(new Label("Fødselsnummer:"), 1, 2);
        add(new Label("Forsikringstype:"), 3, 2);
        add(new Label("Start dato:"), 5, 2);
        add(new Label("Slutt dato:"), 7, 2);
        add(new Label("Visningsinfo:"), 9, 2);
        GridPane.setHalignment(visOkonomi, HPos.CENTER);
        add(visOkonomi, 11, 2, 2, 1);
        
        //legger til kolonne 2
        add(fodselsnr, 2, 2);
        add(forsikringsType, 4, 2);
        add(datoStart, 6, 2);
        add(datoSlutt, 8, 2);
        add(visningsType, 10, 2);
        */
        //legger til kolonne 3
        //add(fodselsnrFeil, 3, 2);
    }//end of methd siOppForsikringsSkjema()
    
    /**
     * Sjekker input fra brukeren opp mot RegEx og gir umidelbar tilbakemelding på om inputen godkjennes eller evt hva som må endres
     * @return Returnerer true om alle feltene godkjennes av regexen
     */
    private boolean tekstFeltLyttere(){
        fodselsnr.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(fodselsnrFeil, nyverdi, "Skriv inn et eksisterende fodselsnummer(11 siffer)", null);
        });
        
        return fodselsnrFeil.getText().isEmpty();
    }//end of method sjekkFelterRegEx()
    
    /**
     * Sjekker alle innputfeltene, og registrerer en forsikring av valgt type
     */
    private boolean sjekkFelter(){
        if( fodselsnr.getText().trim().isEmpty()){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn fødselsnummer");
            return false;
        }
        return true;
    }//end of method sjekkFelter()
    
    /**
     * Tømmer alle tekstfelter og setter regEx labelne til *
     */
    private void setTommeFelter(){
        fodselsnr.clear();
        forsikringsType.setValue("");
        datoStart.setValue(null);
        datoSlutt.setValue(null);
        
        fodselsnrFeil.setText("*");
    }//end of method stTommeFelter()
    
    /**
     * Validerer inputfelter for å så registrere forsikrings oppsigelsen
     */
    public void visOkonomi(){
        try{
            String fodselsnr = this.fodselsnr.getText().trim();
            setTommeFelter();
        }
        catch(NumberFormatException | NullPointerException e){
            GUI.visProgramFeilMelding(e);
            return;
        }
    }//end of class siOppForsikring()
    
    /**
     * Legger til en lytter på radioknappene
     */
    private void radioKnappLytter(){
        gruppe.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) -> {
            RadioButton valg = (RadioButton) t1.getToggleGroup().getSelectedToggle();
            switch(valg.getId()){
                case "utbetalingerAlle":
                    break;
                case "utbetalingerType":
                    break;
                case "utbetalingerKunde":
                    break;
                case "inntektAlle":
                    break;
                case "inntektType":
                    break;
                case "inntektKunde":
                    break;
            }
        });
    }//end of method siOppLytter()
    
    /**
     * Legger til en lytter på visOkonpmi knappen
     */
    private void visOkonomiLytter(){
        visOkonomi.setOnAction((ActionEvent event) -> {
            visOkonomi();
        });
    }//end of method siOppLytter()
}
