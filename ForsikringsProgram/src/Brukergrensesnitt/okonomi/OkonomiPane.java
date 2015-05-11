/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt.okonomi;

import Brukergrensesnitt.GUI;
import forsikringsprogram.ForsikringsKunde;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
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
import javafx.util.Callback;

/**
 *
 * @author Jens
 */
public class OkonomiPane extends GridPane{
    private TextField fodselsnr;
    private Label output;
    private ComboBox forsikringsType;
    private RadioButton utbetalingerArlig, utbetalingerType, utbetalingerKunde, inntektArlig, inntektType, inntektKunde;
    private ToggleGroup gruppe;
    private Button typeKnapp, kundeKnapp;
    private VBox radioKnappBox;
    private TitledPane valgTypePane, valgKundePane;
    private GridPane typeInnhold, kundeInnhold;
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
        
        utbetalingerArlig = new RadioButton("Årlig utbetaling");
        utbetalingerArlig.setFont(tekstStr);
        utbetalingerArlig.setId("utbetalingerArlig");
        utbetalingerArlig.setToggleGroup(gruppe);
        
        utbetalingerType = new RadioButton("Utbetalinger per forsikringstype");
        utbetalingerType.setFont(tekstStr);
        utbetalingerType.setId("utbetalingerType");
        utbetalingerType.setToggleGroup(gruppe);
        
        utbetalingerKunde = new RadioButton("Utbetalinger per kunde");
        utbetalingerKunde.setFont(tekstStr);
        utbetalingerKunde.setId("utbetalingerKunde");
        utbetalingerKunde.setToggleGroup(gruppe);
        
        inntektArlig = new RadioButton("Årlig inntekt");
        inntektArlig.setFont(tekstStr);
        inntektArlig.setId("inntektArlig");
        inntektArlig.setToggleGroup(gruppe);
        
        inntektType = new RadioButton("Inntekter per forsikringstype");
        inntektType.setFont(tekstStr);
        inntektType.setId("inntektType");
        inntektType.setToggleGroup(gruppe);
        
        inntektKunde = new RadioButton("Inntekter per kunde");
        inntektKunde.setFont(tekstStr);
        inntektKunde.setId("inntektKunde");
        inntektKunde.setToggleGroup(gruppe);
        
        //Legger til Radioknapper
        radioKnappBox.getChildren().addAll(visLabel, utbetalingerArlig, utbetalingerType, utbetalingerKunde, inntektArlig, inntektType, inntektKunde);
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
        typeKnapp.setFont(tekstStr);
        
        Label forsikringsTypeLabel = new Label("Forsikringstype:");
        forsikringsTypeLabel.setFont(tekstStr);
        forsikringsType = new ComboBox();
        ObservableList<String> forsikringer = FXCollections.observableArrayList(
                                              "Bilforsikring", "Båtforsikring",
                                              "Boligforsikring", "Reiseforsikring");
        forsikringsType.setItems(forsikringer);
        forsikringsType.setCellFactory(
                new Callback<ListView<String>, ListCell<String>>() {
                    @Override
                    public ListCell<String> call(ListView<String> param) {
                        final ListCell<String> cell = new ListCell<String>() {
                            @Override
                            public void updateItem(String item,
                                    boolean empty) {
                                super.updateItem(item, empty);
                                if (item != null) {
                                    setText(item);
                                    setFont(tekstStr);
                                } else {
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }

                });
        
        typeInnhold.setHgap(10);
        typeInnhold.setVgap(10);
        
        //Legger til labeler
        typeInnhold.add(forsikringsTypeLabel, 1, 1);
    
        //Legger til tekstfelt
        typeInnhold.add(forsikringsType, 2, 1);
        typeInnhold.add(typeKnapp, 3, 1);
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
        kundeKnapp.setFont(tekstStr);
        
        Label fodselsnrLabel = new Label("Fødselsnummer:");
        fodselsnrLabel.setFont(tekstStr);
        fodselsnr = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        fodselsnr.setFont(tekstStr);
        
        kundeInnhold.setHgap(10);
        kundeInnhold.setVgap(10);
        
        //Legger til labeler
        kundeInnhold.add(fodselsnrLabel, 1, 1);
    
        //Legger til tekstfelt
        kundeInnhold.add(fodselsnr, 2, 1);
        kundeInnhold.add(kundeKnapp, 3, 1);
    }//end of method kundeInnhold()
    
    /**
     * Kolapser alle TiteldPanene
     */
    private void ingenUtvidet(){
        valgTypePane.setExpanded(false);
        valgKundePane.setExpanded(false);
    }//end of method ingenUtvidet()
    
    /**
     * Setter onMouseClicked funksjonen slik at man ikke kan trykke på TitledPanene før å utvide dem, men må bruke radioknappene
     * @param vis Den TitledPanen som er utvidet
     * @param skjul1 En av de kolapsede TitledPanene
     * @param skjul2 Den andre av de kolapsede TitledPanene
     */
    private void valgUtvidet(TitledPane vis, TitledPane skjul1){
        vis.setExpanded(true);
        vis.setOnMouseClicked((MouseEvent et) -> {
            vis.setExpanded(true);
        });
        
        skjul1.setExpanded(false);
        skjul1.setOnMouseClicked((MouseEvent et) -> {
            skjul1.setExpanded(false);
        });
    }//end of method valgUtvidet()
    
    /**
     * Oppretter skjema for visning av økonomi
     */
    public void visOkonomiSkjema(){
        typeInnhold();
        kundeInnhold();
        valgKunde();
        valgType();
        
        output = new Label();
        output.setFont(tekstStr);
        
        Label visOkonomiLabel = new Label("Økonomi");
        visOkonomiLabel.setFont(GUI.OVERSKRIFT);
        
        setHgap(10);
        setVgap(10);
        
        add(visOkonomiLabel, 1, 1);
        add(radioKnappBox, 1, 2, 1, 4);
        add(output, 2, 4);
    }//end of methd siOppForsikringsSkjema()
    
    /**
     * Sjekker input fra brukeren opp mot RegEx og gir umidelbar tilbakemelding på om inputen godkjennes eller evt hva som må endres
     * @return Returnerer true om alle feltene godkjennes av regexen
     */
    private boolean sjekkRegEx(){
        if(!(GUI.sjekkRegexFodselsNr(fodselsnr.getText()))){
            GUI.visInputFeilMelding("OBS! Fødselsnummer inneholder 11 sifre.", "For å kunne vise økonomien til en kunde må du fylle inn et eksisterende fødselsnummer med 11 sifre");
            return false;
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
        }
        
        if(valg == utbetalingerKunde || valg == inntektKunde){
            if( fodselsnr.getText().trim().isEmpty()){
                GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn fødselsnummer");
                return false;
            }
            
            if( !sjekkRegEx() ){
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
    }//end of method stTommeFelter()
    
    /**
     * Viser økonomisk informasjon av valgt type
     */
    private void visOkonomi(){
        if( !sjekkFelter() ){
            return;
        }
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy"); 
            RadioButton valg = (RadioButton) gruppe.getSelectedToggle();
            switch(valg.getId()){
                case "utbetalingerType":
                    String forsikringsType = (String) this.forsikringsType.getValue();
                    double utbetalingType = kundeRegister.utbetaltErstatningAvType(forsikringsType);
                    if(utbetalingType == 0.0){
                        output.setText("Det er ikke gjort noen utbetalinger på " + forsikringsType + ".");
                        break;
                    }
                    output.setText("Utgiftene for " + forsikringsType + " er " + utbetalingType + "kr.");
                    break;
                case "utbetalingerKunde":
                    String fodselsnr = this.fodselsnr.getText().trim();
                    ForsikringsKunde kunde = kundeRegister.finnKunde(this.fodselsnr.getText().trim());
                    double utbetalingKunde = kundeRegister.utbetalingTilKunde(fodselsnr);
                    if(utbetalingKunde == -1){
                        output.setText("Det er ikke gjort noen utbetalinger til " + kunde.getEtternavn() + ", " + kunde.getFornavn() + ".");
                        break;
                    }
                    output.setText("Utbetalingene til " + kunde.getEtternavn() + ", " + kunde.getFornavn() + " er " + utbetalingKunde + "kr.");
                    break;
                case "inntektType":
                    String forsikringsTypeInntekt = (String) this.forsikringsType.getValue();
                    double inntektType = kundeRegister.inntektFraForsikringstype(forsikringsTypeInntekt);
                    if(inntektType == 0.0){
                        output.setText("Vi har ingen inntekt fra " + forsikringsTypeInntekt + ".");
                        break;
                    }
                    output.setText("Inntektene for " + forsikringsTypeInntekt + " er " + inntektType + "kr.");
                    break;
                case "inntektKunde":
                    String fodselsnrInntekt = this.fodselsnr.getText().trim();
                    double inntektFodselsnr = kundeRegister.inntektFraKunde(fodselsnrInntekt);
                    if(inntektFodselsnr == -1){
                        output.setText("Vi har ingen inntekt fra " + fodselsnrInntekt + ".");
                        break;
                    }
                    output.setText("Inntektene fra " + fodselsnrInntekt + " er " + inntektFodselsnr + "kr.");
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
                case "utbetalingerArlig":
                    double arligUtgifter = kundeRegister.alleUtbetalteErstatninger();
                    output.setText("Den årlige utgiften er " + arligUtgifter + "kr.");
                    break;
                case "utbetalingerType":
                    ingenUtvidet();
                    valgUtvidet(valgTypePane, valgKundePane);
                    break;
                case "utbetalingerKunde":
                    ingenUtvidet();
                    valgUtvidet(valgKundePane, valgTypePane);
                    break;
                case "inntektArlig":
                    double arligInntekter = kundeRegister.aarligInntekt();
                    output.setText("Den årlige inntekten er " + arligInntekter + "kr.");
                    break;
                case "inntektType":
                    ingenUtvidet();
                    valgUtvidet(valgTypePane, valgKundePane);
                    break;
                case "inntektKunde":
                    ingenUtvidet();
                    valgUtvidet(valgKundePane, valgTypePane);
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
    }//end of method visOkonomiLytter()
}//end of class OkonomiPane
