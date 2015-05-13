


package Brukergrensesnitt.okonomi;

import Brukergrensesnitt.GUI;
import forsikringsprogram.ForsikringsKunde;
import forsikringsprogram.Kunderegister;
import java.text.SimpleDateFormat;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
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
 * Denne klassen er et layout for økonomien. Her fyller brukeren inn fødselsnummer eller velger forsikringstype 
 * samt ønsket øknomisk infjormasjon, før dette skrives ut.
 * Siste versjon skrevet: 12/05/15 15.30
 * @author Jens Omfjord, Informasjonsteknologi. s236641
 */
public class OkonomiPane extends GridPane{
    private TextField fodselsnr;
    private Label output;
    private ComboBox forsikringsType;
    private RadioButton utbetalingerArlig, utbetalingerType, utbetalingerKunde, inntektArlig, inntektType, inntektKunde, totalRegnskap;
    private ToggleGroup gruppe;
    private Button typeKnapp, kundeKnapp;
    private VBox radioKnappBox;
    private TitledPane valgTypePane, valgKundePane;
    private GridPane typeInnhold, kundeInnhold;
    private Kunderegister kundeRegister;
    private Border radioKnappKant = new Border( new BorderStroke(DARKGRAY,SOLID, new CornerRadii(5), THIN, new Insets(10)) );
    private Font tekstStr = Font.font(null, 16);
    
    public OkonomiPane(Kunderegister register){
        opprettRadioKnappNavigasjon();
        opprettOkonomiSkjema();
        this.kundeRegister = register;
        radioKnappLytter();
        visOkonomiLytter();
        setAlignment( Pos.TOP_CENTER );
        setPadding( new Insets(50, 70, 0, 0) );
    }//end of constructor
    
    /**
     * Oppretter og viser radioknappene til brukeren
     */
    private void opprettRadioKnappNavigasjon(){
        radioKnappBox = new VBox();
        
        gruppe = new ToggleGroup();
        
        Label visLabel = new Label("Vis");
        visLabel.setFont(Font.font(null, FontWeight.BOLD, 20));
        
        VBox utgiftBox = new VBox();
        Label utgiftLabel = new Label("Utgifter");
        utgiftLabel.setFont(tekstStr);
        Separator utgiftSkille = new Separator(Orientation.HORIZONTAL);
        utgiftBox.getChildren().addAll(utgiftLabel, utgiftSkille);
        
        VBox inntektBox = new VBox();
        Label inntektLabel = new Label("Inntekter");
        inntektLabel.setFont(tekstStr);
        Separator inntektSkille = new Separator(Orientation.HORIZONTAL);
        inntektBox.getChildren().addAll(inntektLabel, inntektSkille);
        
        VBox totalRegnskapBox = new VBox();
        Label totalRegnskapLabel = new Label("Total");
        totalRegnskapLabel.setFont(tekstStr);
        Separator totalRegnskapSkille = new Separator(Orientation.HORIZONTAL);
        totalRegnskapBox.getChildren().addAll(totalRegnskapLabel, totalRegnskapSkille);
        
        //Oppretter utbetalingerArlig knappen
        utbetalingerArlig = new RadioButton("Årlig utbetaling");
        utbetalingerArlig.setFont(tekstStr);
        utbetalingerArlig.setId("utbetalingerArlig");
        utbetalingerArlig.setToggleGroup(gruppe);
        
        //Oppretter utbetalingerType knappen
        utbetalingerType = new RadioButton("Utbetalinger per forsikringstype");
        utbetalingerType.setFont(tekstStr);
        utbetalingerType.setId("utbetalingerType");
        utbetalingerType.setToggleGroup(gruppe);
        
        //Oppretter utbetalingerKunde knappen
        utbetalingerKunde = new RadioButton("Utbetalinger per kunde");
        utbetalingerKunde.setFont(tekstStr);
        utbetalingerKunde.setId("utbetalingerKunde");
        utbetalingerKunde.setToggleGroup(gruppe);
        
        //Oppretter inntektArlig knappen
        inntektArlig = new RadioButton("Årlig inntekt");
        inntektArlig.setFont(tekstStr);
        inntektArlig.setId("inntektArlig");
        inntektArlig.setToggleGroup(gruppe);
        
        //Oppretter inntektType knappen
        inntektType = new RadioButton("Inntekter per forsikringstype");
        inntektType.setFont(tekstStr);
        inntektType.setId("inntektType");
        inntektType.setToggleGroup(gruppe);
        
        //Oppretter inntektKunde knappen
        inntektKunde = new RadioButton("Inntekter per kunde");
        inntektKunde.setFont(tekstStr);
        inntektKunde.setId("inntektKunde");
        inntektKunde.setToggleGroup(gruppe);
        
        //Oppretter totalOkonomi knappen
        totalRegnskap = new RadioButton("Total regnskap");
        totalRegnskap.setFont(tekstStr);
        totalRegnskap.setId("totalRegnskap");
        totalRegnskap.setToggleGroup(gruppe);
        
        //Legger til Radioknapper
        radioKnappBox.getChildren().addAll(visLabel, utgiftBox, utbetalingerArlig, 
                utbetalingerType, utbetalingerKunde, inntektBox, inntektArlig, 
                inntektType, inntektKunde, totalRegnskapBox, totalRegnskap);
        radioKnappBox.setBorder(radioKnappKant);
        radioKnappBox.setPadding(new Insets(10));
        radioKnappBox.setSpacing(30);
        
    }//end of method opprettRadioKnappNavigasjon()
    
    /**
     * Oppretter TiteldPanen for de valgene med forsikringstype som søkekriterie
     */
    private void valgType(){
        valgTypePane = new TitledPane();
        valgTypePane.setExpanded(false);
        valgTypePane.setContent(typeInnhold);
        valgTypePane.setMinWidth(700);
        valgTypePane.setOnMouseClicked((MouseEvent t) -> { //Setter en lytter på Titeld panen slik at den ikke utvider seg før man trykker på den riktige radioknappen
            valgTypePane.setExpanded(false);
        });
    }//end of method valgType()
    
    /**
     * Oppretter innholdet til TiteldPanen valgTypePane
     */
    private void opprettTypeSkjema(){
        typeInnhold = new GridPane();
        
        typeKnapp = new Button("Vis økonomi");
        typeKnapp.setFont(tekstStr);
        
        Label forsikringsTypeLabel = new Label("Forsikringstype:");
        forsikringsTypeLabel.setFont(tekstStr);
        forsikringsType = new ComboBox();
        ObservableList<String> forsikringer = FXCollections.observableArrayList(
                                              "Bil", "Båt", "Bolig", "Reise");
        forsikringsType.setItems(forsikringer);
        forsikringsType.setCellFactory(                                         //Setter Font typen til innholdet i comboboxen
                new Callback<ListView<String>, ListCell<String>>() {
                    @Override
                    public ListCell<String> call(ListView<String> param) {
                        final ListCell<String> linje = new ListCell<String>() {
                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (item != null) {
                                    setText(item);
                                    setFont(tekstStr);
                                }//end of if
                                else 
                                    setText(null);
                                //end of else
                            }
                        };//end of linje
                        return linje;
                    }
                });//end of inner anonymous class
        
        typeInnhold.setHgap(10);
        typeInnhold.setVgap(10);
        
        //Legger til labeler
        typeInnhold.add(forsikringsTypeLabel, 1, 1);
    
        //Legger til tekstfelt
        typeInnhold.add(forsikringsType, 2, 1);
        typeInnhold.add(typeKnapp, 3, 1);
    }//end of method opprettTypeSkjema()
    
    /**
     * Oppretter TiteldPanen for de valgene med fødselsnummer som søkekriterie
     */
    private void valgKunde(){
        valgKundePane = new TitledPane();
        valgKundePane.setExpanded(false);
        valgKundePane.setContent(kundeInnhold);
        valgKundePane.setMinWidth(700);
        valgKundePane.setOnMouseClicked((MouseEvent t) -> { //Setter en lytter på Titeld panen slik at den ikke utvider seg før man trykker på den riktige radioknappen
            valgKundePane.setExpanded(false);
        });
    }//end of method valgKunde()
    
    /**
     * Oppretter innholdet til TiteldPanen valgKundePane
     */
    private void opprettKundeSkjema(){
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
    }//end of method opprettKundeSkjema()
    
    /**
     * Oppretter layouten for visning av økonomi
     */
    public void opprettOkonomiSkjema(){
        opprettTypeSkjema();
        opprettKundeSkjema();
        valgKunde();
        valgType();
        
        output = new Label();
        output.setFont(Font.font(null, 24));
        
        Label visOkonomiLabel = new Label("Økonomi");
        visOkonomiLabel.setFont(Font.font(null, FontWeight.BOLD, 20));
        
        setHgap(10);
        setVgap(10);
        add(valgTypePane, 2, 6);
        add(valgKundePane, 2, 7);
        add(visOkonomiLabel, 1, 5);
        setMargin(visOkonomiLabel, new Insets(0,0,0,10));
        add(radioKnappBox, 1, 6, 1, 4);
        add(output, 2, 8);
    }//end of methd siOppForsikringsSkjema()
    
    /**
     * Kolapser begge TiteldPanene
     */
    private void setIngenUtvidet(){
        valgTypePane.setExpanded(false);
        valgKundePane.setExpanded(false);
    }//end of method setIngenUtvidet()
    
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
        }//end of if
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy"); 
            RadioButton valg = (RadioButton) gruppe.getSelectedToggle();
            switch(valg.getId()){
                case "utbetalingerType":
                    String forsikringsTypeInput = (String) this.forsikringsType.getValue();
                    setTommeFelter();
                    double utbetalingType = kundeRegister.utbetaltErstatningAvType(forsikringsTypeInput);
                    if(utbetalingType == 0.0){
                        output.setText("Det er ikke gjort noen utbetalinger på " + forsikringsTypeInput + ".");
                        break;
                    }
                    output.setText("Selskapets totale utbetaling for " + forsikringsTypeInput + " er i år " + utbetalingType + "kr.");
                    break;
                case "utbetalingerKunde":
                    String fodselsnrInput = this.fodselsnr.getText().trim();
                    setTommeFelter();
                    ForsikringsKunde kundeUtbetaling = kundeRegister.finnKunde(fodselsnrInput);
                    if(kundeUtbetaling == null){
                        GUI.visInputFeilMelding("Feil inntasting", "Det finnes ingen kunde i registeret med fødselsnummeret " + fodselsnrInput);
                        break;
                    }
                    double utbetalingKunde = kundeRegister.utbetalingTilKunde(fodselsnrInput);
                    if(utbetalingKunde == -1){
                        output.setText("Det er ikke gjort noen utbetalinger til " + kundeUtbetaling.getEtternavn() + ", " + kundeUtbetaling.getFornavn() + ".");
                        break;
                    }
                    output.setText("Selskapets totale utbetaling til " + kundeUtbetaling.getEtternavn() + ", " + kundeUtbetaling.getFornavn() + " er i år " + utbetalingKunde + "kr.");
                    break;
                case "inntektType":
                    String forsikringsTypeInntektInput = (String) this.forsikringsType.getValue();
                    setTommeFelter();
                    double inntektType = kundeRegister.inntektFraForsikringstype(forsikringsTypeInntektInput);
                    if(inntektType == 0.0){
                        output.setText("Vi har ingen inntekt fra " + forsikringsTypeInntektInput + ".");
                        break;
                    }
                    output.setText("Selskapets totale inntekt for " + forsikringsTypeInntektInput + " er i år " + inntektType + "kr.");
                    break;
                case "inntektKunde":
                    String fodselsnrInntektInput = this.fodselsnr.getText().trim();
                    setTommeFelter();
                    ForsikringsKunde kundeInntekt = kundeRegister.finnKunde(fodselsnrInntektInput);
                    if(kundeInntekt == null){
                        GUI.visInputFeilMelding("Feil inntasting", "Det finnes ingen kunde i registeret med fødselsnummeret " + fodselsnrInntektInput);
                        break;
                    }
                    double inntektFodselsnr = kundeRegister.inntektFraKunde(fodselsnrInntektInput);
                    if(inntektFodselsnr == -1){
                        output.setText("Vi har ingen inntekt fra " + kundeInntekt.getEtternavn() + ", " + kundeInntekt.getFornavn() + ".");
                        break;
                    }
                    output.setText("Selskapets totale inntekt fra " + kundeInntekt.getEtternavn() + ", " + kundeInntekt.getFornavn() + " er i år " + inntektFodselsnr + "kr.");
                    break;
            }// end of switch
        }// end of try
        catch( NumberFormatException | NullPointerException e){
            GUI.visProgramFeilMelding(e);
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
                    setIngenUtvidet();
                    double arligUtgifter = kundeRegister.alleUtbetalteErstatninger();
                    output.setText("Selskapets totale utgift for i år er " + arligUtgifter + "kr.");
                    break;
                case "utbetalingerType":
                    setIngenUtvidet();
                    output.setText("");
                    valgUtvidet(valgTypePane, valgKundePane);
                    break;
                case "utbetalingerKunde":
                    setIngenUtvidet();
                    output.setText("");
                    valgUtvidet(valgKundePane, valgTypePane);
                    break;
                case "inntektArlig":
                    setIngenUtvidet();
                    double arligInntekter = kundeRegister.aarligInntekt();
                    output.setText("Selskapets totale inntekt for i år er " + arligInntekter + "kr.");
                    break;
                case "inntektType":
                    setIngenUtvidet();
                    output.setText("");
                    valgUtvidet(valgTypePane, valgKundePane);
                    break;
                case "inntektKunde":
                    setIngenUtvidet();
                    output.setText("");
                    valgUtvidet(valgKundePane, valgTypePane);
                    break;
                case "totalRegnskap":
                    setIngenUtvidet();
                    double regnskap = kundeRegister.aarligInntekt() - kundeRegister.alleUtbetalteErstatninger();
                    if(regnskap < 0)
                        output.setText("Selskapet går " + regnskap + "kr i minus hittil i år");
                    else if(regnskap == 0)
                        output.setText("Selskapet går i null hittil i år");
                    else if(regnskap > 0)
                        output.setText("Selskapet går " + regnskap + "kr i pluss hittil i år");
                    break;
            }// end of switch-case
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
