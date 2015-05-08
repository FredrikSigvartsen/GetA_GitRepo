/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt.okonomi;

import Brukergrensesnitt.GUI;
import forsikringsprogram.Kunderegister;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextAreaBuilder;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFieldBuilder;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
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
    private TextArea output;
    private DatePicker datoStartType, datoSluttType, datoStartKunde, datoSluttKunde, datoStartAlle, datoSluttAlle;
    private ComboBox forsikringsType, visningsType;
    private RadioButton utbetalingerAlle, utbetalingerType, utbetalingerKunde, inntektAlle, inntektType, inntektKunde;
    private ToggleGroup gruppe;
    private Button alleKnapp, typeKnapp, kundeKnapp;
    private VBox radioKnappBox;
    private TitledPane valgTypePane, valgKundePane, valgAllePane;
    private GridPane typeInnhold, kundeInnhold, alleInnhold;
    private Kunderegister kundeRegister;
    private Border radioKnappKant = new Border( new BorderStroke(DARKGRAY,SOLID, new CornerRadii(5), THIN, new Insets(10)) );
    private Font tekstStr = Font.font(null, 16);
    
    public OkonomiPane(Kunderegister register){
        radioKnapper();
        visOkonomiSkjema();
        this.kundeRegister = register;
        //tekstFeltLyttere();
        radioKnappLytter();
        visOkonomiLytter();
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
     * Oppretter TiteldPanen for de valgene med forsikringstype som søkekriterie
     */
    private void valgType(){
        valgTypePane = new TitledPane();
        valgTypePane.setExpanded(false);
        valgTypePane.setContent(typeInnhold);
        add(valgTypePane, 2, 2);
        valgTypePane.setOnMouseClicked((MouseEvent t) -> {
            valgTypePane.setExpanded(false);
        });
    }//end of method valgType()
    
    /**
     * Oppretter innholdet til TiteldPanen valgTypePane
     */
    private void typeInnhold(){
        typeInnhold = new GridPane();
        
        typeKnapp = new Button("Vis økonomi");
        
        forsikringsType = new ComboBox();
        ObservableList<String> forsikringer = FXCollections.observableArrayList(
                                              "Bilforsikring", "Båtforsikring",
                                              "Boligforsikring", "Reiseforsikring");
        forsikringsType.setItems(forsikringer);
        
        datoStartType = new DatePicker();
        
        datoSluttType = new DatePicker();
        
        typeInnhold.setHgap(10);
        typeInnhold.setVgap(10);
        
        //Legger til labeler
        typeInnhold.add(new Label("Forsikringstype:"), 1, 1);
        typeInnhold.add(new Label("Start dato:"), 3, 1);
        typeInnhold.add(new Label("Slutt dato:"), 5, 1);
    
        //Legger til tekstfelt
        typeInnhold.add(forsikringsType, 2, 1);
        typeInnhold.add(datoStartType, 4, 1);
        typeInnhold.add(datoSluttType, 6, 1);
        typeInnhold.add(typeKnapp, 1, 2, 2, 1);
    }//end of method typeInnhold()
    
    /**
     * Oppretter TiteldPanen for de valgene med fødselsnummer som søkekriterie
     */
    private void valgKunde(){
        valgKundePane = new TitledPane();
        valgKundePane.setExpanded(false);
        valgKundePane.setContent(kundeInnhold);
        add(valgKundePane, 2, 3);
        valgKundePane.setOnMouseClicked((MouseEvent t) -> {
            valgKundePane.setExpanded(false);
        });
    }//end of method valgKunde()
    
    /**
     * Oppretter innholdet til TiteldPanen valgKundePane
     */
    private void kundeInnhold(){
        kundeInnhold = new GridPane();
        
        kundeKnapp = new Button("Vis Økonomi");
        
        fodselsnr = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        
        datoStartKunde = new DatePicker();
        
        datoSluttKunde = new DatePicker(); //bruk isAfter() for å sammelikne datoer
        
        kundeInnhold.setHgap(10);
        kundeInnhold.setVgap(10);
        
        //Legger til labeler
        kundeInnhold.add(new Label("Fødselsnummer:"), 1, 1);
        kundeInnhold.add(new Label("Start dato:"), 3, 1);
        kundeInnhold.add(new Label("Slutt dato:"), 5, 1);
    
        //Legger til tekstfelt
        kundeInnhold.add(fodselsnr, 2, 1);
        kundeInnhold.add(datoStartKunde, 4, 1);
        kundeInnhold.add(datoSluttKunde, 6, 1);
        kundeInnhold.add(kundeKnapp, 1, 2, 2, 1);
    }//end of method kundeInnhold()
    
    /**
     * Oppretter TiteldPanen for de valgene med kun dato som søkekriterie
     */
    private void valgAlle(){
        valgAllePane = new TitledPane();
        valgAllePane.setExpanded(false);
        valgAllePane.setContent(alleInnhold);
        add(valgAllePane, 2, 4);
        valgAllePane.setOnMouseClicked((MouseEvent t) -> {
            valgAllePane.setExpanded(false);
        });
    }//end of method valgAlle()
    
    /**
     * Oppretter innholdet til TiteldPanen valgAllePane
     */
    private void alleInnhold(){
        alleInnhold = new GridPane();
        
        alleKnapp = new Button("Vis økonomi");
        
        datoStartAlle = new DatePicker();
        
        datoSluttAlle = new DatePicker();
        
        alleInnhold.setHgap(10);
        alleInnhold.setVgap(10);
        
        //Legger til labeler
        alleInnhold.add(new Label("Start dato:"), 1, 1);
        alleInnhold.add(new Label("Slutt dato:"), 3, 1);
    
        //Legger til tekstfelt
        alleInnhold.add(datoStartAlle, 2, 1);
        alleInnhold.add(datoSluttAlle, 4, 1);
        alleInnhold.add(alleKnapp, 1, 2, 2, 1);
    }//end of method alleInnhold()
    
    /**
     * Kolapser alle TiteldPanene
     */
    private void ingenUtvidet(){
        valgTypePane.setExpanded(false);
        valgKundePane.setExpanded(false);
        valgAllePane.setExpanded(false);
    }//end of method ingenUtvidet()
    
    /**
     * Setter onMouseClicked funksjonen slik at man ikke kan trykke på TitledPanene før å utvide dem, men må bruke radioknappene
     * @param vis Den TitledPanen som er utvidet
     * @param skjul1 En av de kolapsede TitledPanene
     * @param skjul2 Den andre av de kolapsede TitledPanene
     */
    private void valgUtvidet(TitledPane vis, TitledPane skjul1, TitledPane skjul2){
        vis.setExpanded(true);
        vis.setOnMouseClicked((MouseEvent et) -> {
            vis.setExpanded(true);
        });
        
        skjul1.setExpanded(false);
        skjul1.setOnMouseClicked((MouseEvent et) -> {
            skjul1.setExpanded(false);
        });
        
        skjul2.setExpanded(false);
        skjul2.setOnMouseClicked((MouseEvent et) -> {
            skjul2.setExpanded(false);
        });
    }//end of method valgUtvidet()
    
    /**
     * Oppretter skjema for visning av økonomi
     */
    public void visOkonomiSkjema(){
        typeInnhold();
        kundeInnhold();
        alleInnhold();
        valgKunde();
        valgType();
        valgAlle();
        
        output = TextAreaBuilder.create()
                .editable(false)
                .wrapText(true)
                .build();
        output.setFont(tekstStr);
        
        Label visOkonomiLabel = new Label("Økonomi");
        visOkonomiLabel.setFont(GUI.OVERSKRIFT);
        
        setHgap(10);
        setVgap(10);
               
        add(visOkonomiLabel, 1, 1);
        add(radioKnappBox, 1, 2, 1, 4);
        add(output, 2, 5);
    }//end of methd siOppForsikringsSkjema()
    
    /**
     * Sjekker om startdatoen er før slutt datoen
     */
    /*private void erStartDatoForSluttDato(DatePicker dato1, DatePicker dato2){
        return dato1.getValue().isAfter(dato2.getValue());
    }*/
    
    /**
     * Sjekker input fra brukeren opp mot RegEx og gir umidelbar tilbakemelding på om inputen godkjennes eller evt hva som må endres
     * @return Returnerer true om alle feltene godkjennes av regexen
     */
    private boolean sjekkRegEx(){
        RadioButton valg = (RadioButton) gruppe.getSelectedToggle();
        if(valg == utbetalingerType || valg == inntektType){
            if( ! (GUI.sjekkRegex( GUI.DATO_REGEX, datoStartType.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) ) ) ){
                GUI.visInputFeilMelding("OBS! Dato er i feil format", "For å kunne vise økonomi et et gitt tidsrom må du oppgi en start dato(dd.mm.åååå)");
                return false;
            }

            if( ! (GUI.sjekkRegex( GUI.DATO_REGEX, datoSluttType.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) ) ) ){
                GUI.visInputFeilMelding("OBS! Dato er i feil format", "For å kunne vise økonomi et et gitt tidsrom må du oppgi en slutt dato(dd.mm.åååå)");
                return false;
            }
        }
        
        if(valg == utbetalingerKunde || valg == inntektKunde){
            if(!(GUI.sjekkRegexFodselsNr(fodselsnr.getText()))){
                GUI.visInputFeilMelding("OBS! Fødselsnummer inneholder 11 sifre.", "For å kunne vise økonomien til en kunde må du fylle inn et eksisterende fødselsnummer med 11 sifre");
                return false;
            }

            if( ! (GUI.sjekkRegex( GUI.DATO_REGEX, datoStartKunde.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) ) ) ){
                GUI.visInputFeilMelding("OBS! Dato er i feil format", "For å kunne vise økonomi et et gitt tidsrom må du oppgi en start dato(dd.mm.åååå)");
                return false;
            }

            if( ! (GUI.sjekkRegex( GUI.DATO_REGEX, datoSluttKunde.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) ) ) ){
                GUI.visInputFeilMelding("OBS! Dato er i feil format", "For å kunne vise økonomi et et gitt tidsrom må du oppgi en slutt dato(dd.mm.åååå)");
                return false;
            }
        }
        
        if(valg == utbetalingerAlle || valg == inntektAlle){
            if( ! (GUI.sjekkRegex( GUI.DATO_REGEX, datoStartAlle.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) ) ) ){
                GUI.visInputFeilMelding("OBS! Dato er i feil format", "For å kunne vise økonomi et et gitt tidsrom må du oppgi en start dato(dd.mm.åååå)");
                return false;
            }

            if( ! (GUI.sjekkRegex( GUI.DATO_REGEX, datoSluttAlle.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) ) ) ){
                GUI.visInputFeilMelding("OBS! Dato er i feil format", "For å kunne vise økonomi et et gitt tidsrom må du oppgi en slutt dato(dd.mm.åååå)");
                return false;
            }
        }
        return true;
    }//end of method sjekkFelterRegEx()
    
    /**
     * Sjekker om det er skrvet inn noe i alle innputfeltene
     */
    private boolean sjekkFelter(){
        RadioButton valg = (RadioButton) gruppe.getSelectedToggle();
        if(valg == utbetalingerType || valg == inntektType){
            if( forsikringsType.getValue() == null){
                GUI.visInputFeilMelding("Feil inntasting", "Venligst velg en forsikrings type");
                return false;
            }
            if( datoStartType.getValue() == null){
                GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn start dato");
                return false;
            }

            if( datoSluttType.getValue() == null){
                GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn slutt dato");
                return false;
            }
        }
        
        if(valg == utbetalingerKunde || valg == inntektKunde){
            if( fodselsnr.getText().trim().isEmpty()){
                GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn fødselsnummer");
                return false;
            }
            if( datoStartKunde.getValue() == null){
                GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn start dato");
                return false;
            }

            if( datoSluttKunde.getValue() == null){
                GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn slutt dato");
                return false;
            }
        }
        if(valg == utbetalingerAlle || valg == inntektAlle){
            if( datoStartAlle.getValue() == null){
                GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn start dato");
                return false;
            }

            if( datoSluttAlle.getValue() == null){
                GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn slutt dato");
                return false;
            }
        }
        return true;
    }//end of method sjekkFelter()
    
    /**
     * Tømmer alle tekstfelter
     */
    private void setTommeFelter(){
        fodselsnr.clear();
        forsikringsType.setValue("");
        datoStartType.setValue(null);
        datoSluttType.setValue(null);
        datoStartKunde.setValue(null);
        datoSluttKunde.setValue(null);
        datoStartAlle.setValue(null);
        datoSluttAlle.setValue(null);
    }//end of method stTommeFelter()
    
    /**
     * Viser økonomisk informasjon av valgt type
     */
    private void visOkonomi(){
        if( !sjekkFelter() ){
            return;
        }
        if( !sjekkRegEx() ){
            return;
        }
        try{
            RadioButton valg = (RadioButton) gruppe.getSelectedToggle();
            switch(valg.getId()){
                case "utbetalingerAlle":
                    double utbetalingAlle = kundeRegister.alleUtbetalteErstatninger();
                    //Calendar forsteDato = new GregorianCalendar( datoStart.getValue().getYear(), datoStart.getValue().getMonthValue()-1, datoStart.getValue().getDayOfMonth() );
                    //Calendar andreDato = new GregorianCalendar( datoSlutt.getValue().getYear(), datoSlutt.getValue().getMonthValue()-1, datoSlutt.getValue().getDayOfMonth() );
                    //SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy"); 
                    //String startDatoString = sdf.format(forsteDato);
                    //String sluttDatoString = sdf.format(andreDato);
                    
                    output.setText("Alle utbetalingene utgjør " + utbetalingAlle + "kr.");
                    break;
                case "utbetalingerType":
                    String forsikringsType = (String) this.forsikringsType.getValue();
                    double utbetalingType = kundeRegister.utbetaltErstatningAvType(forsikringsType);
                    output.setText("Utgiftene for " + forsikringsType + " er " + utbetalingType + "kr.");
                    break;
                case "utbetalingerKunde":
                    String fodselsnr = this.fodselsnr.getText().trim();
                    double utbetalingKunde = kundeRegister.utbetalingTilKunde(fodselsnr);
                    output.setText("Utbetalingene til " + fodselsnr + " er " + utbetalingKunde + "kr.");
                    break;
                case "inntektAlle":
                    break;
                case "inntektType":
                    break;
                case "inntektKunde":
                    break;
            }
        }
        catch( NumberFormatException | NullPointerException e){
            GUI.visProgramFeilMelding(e);
            return;
        }// end of try-catch
    }//end of method visOkonomi
    
    /**
     * Legger til en lytter på radioknappene
     */
    private void radioKnappLytter(){
        gruppe.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) -> {
            RadioButton valg = (RadioButton) t1.getToggleGroup().getSelectedToggle();
            switch(valg.getId()){
                case "utbetalingerAlle":
                    ingenUtvidet();
                    valgUtvidet(valgAllePane, valgTypePane, valgKundePane);
                    break;
                case "utbetalingerType":
                    ingenUtvidet();
                    valgUtvidet(valgTypePane, valgKundePane, valgAllePane);
                    break;
                case "utbetalingerKunde":
                    ingenUtvidet();
                    valgUtvidet(valgKundePane, valgAllePane, valgTypePane);
                    break;
                case "inntektAlle":
                    ingenUtvidet();
                    valgUtvidet(valgAllePane, valgTypePane, valgKundePane);
                    break;
                case "inntektType":
                    ingenUtvidet();
                    valgUtvidet(valgTypePane, valgKundePane, valgAllePane);
                    break;
                case "inntektKunde":
                    ingenUtvidet();
                    valgUtvidet(valgKundePane, valgAllePane, valgTypePane);
                    break;
            }
        });
    }//end of method radioKnappLytter()
    
    /**
     * Legger til en lytter på typeKnapp, kundeKnapp og alleKnapp, knappene
     */
    private void visOkonomiLytter(){
        typeKnapp.setOnAction((ActionEvent event) -> {
            visOkonomi();
        });
        
        kundeKnapp.setOnAction((ActionEvent event) -> {
            visOkonomi();
        });
        
        alleKnapp.setOnAction((ActionEvent event) -> {
            visOkonomi();
        });
    }//end of method visOkonomiLytter()
}//end of class OkonomiPane
