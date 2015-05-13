package Brukergrensesnitt.kundebehandling;

import Brukergrensesnitt.*;
import forsikringsprogram.*;
import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import static javafx.scene.control.Alert.AlertType.*;
import javafx.scene.layout.*;
import static javafx.scene.layout.BorderStroke.THIN;
import static javafx.scene.layout.BorderStrokeStyle.SOLID;
import static javafx.scene.paint.Color.DARKGRAY;
import static javafx.scene.text.Font.font;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Denne klassen er et layout for registrering av skademeldinger. Her fyller brukeren inn angitte inputs og dette valideres, før det registreres i kunderegisteret.
 * Brukeren kan laste opp evt. bilder av skaden, men er ikke nødt. 
 * Siste versjon skrevet: 12/05/15 10:00
 * @author Fredrik Aleksander Sigvartsen, Dataingeniør. s236356
 */
public class RegistrerSkadeLayout extends GridPane {

    private Kunderegister kundeRegister;
    private Button registrerKnapp, lastOppFilKnapp;
    private TextArea skadeBeskrivelseInput, vitneKontaktInput;
    private TextField fodselsNrInput, takstInput, tidspunktInput;
    private GridPane lastOppBildeLayout, registrerSkademeldingLayout, registrerLayout;
    private ChoiceBox skadetypeInput;
    private DatePicker datoInput;
    private Label output, filLastetOpp;
    private List<File> bildefiler;
    private int antallBilder;
    private String tilbakemelding;
    
    /**
     * Oppretter hele layoutet.
     * @param register Kunderegisteret skademeldingen skal registreres i. 
     */
    public RegistrerSkadeLayout(Kunderegister register){
        opprettLayout();
        this.kundeRegister = register;
    }// end of constructor
    
    /**
     * Oppretter layoutet
     */
    private void opprettLayout(){
        lastOppBildeLayout = bildeOpplastning();
        registrerSkademeldingLayout = fyllInnMedOverskriftLayout();
        bildefiler = new ArrayList<>();
        
        add( registrerSkademeldingLayout, 1, 1);
        add( lastOppBildeLayout, 2, 1);
        setAlignment(Pos.TOP_CENTER);
        setPadding( new Insets(50, 0, 0, 0));
    }// end of method opprettLayout()
    
    /**
     * Oppretter layouet for registrering av skademelding. 
     * @return Et GridPane layout med oversikt og et layout som blir opprettet i fyllInnLayout()
     */
    private GridPane fyllInnMedOverskriftLayout(){
        GridPane registreringsLayout = new GridPane();
        VBox fyllInnOverskrift = KundePane.overskrift("Fyll inn informasjon om skaden", 24);
        
        registreringsLayout.add( fyllInnOverskrift, 1, 1);
        registreringsLayout.add( fyllInnLayout(), 1, 2);
        registreringsLayout.setVgap(15);
        registreringsLayout.setBorder( new Border( new BorderStroke(DARKGRAY,SOLID, new CornerRadii(5), THIN, new Insets(0)) ));
        registreringsLayout.setPadding( new Insets( 5, 20, 20, 20) );
        return registreringsLayout;
    }// end of method fyllInnMedOverskriftLayout
    
    /**
     * Laster opp bilder, og lagrer dem som filer i en ArrayList av filer. 
     * @return Et layout for opplastning av bilder. 
     */
    private GridPane bildeOpplastning(){
        GridPane returLayout = new GridPane();
        Label lastOppSubskrift = new Label("  - har du ingen bilder, hopp over dette.");
        lastOppSubskrift.setFont( font(14));
        filLastetOpp = new Label();
        VBox lastOppOverskrift = KundePane.overskrift("Last opp bilde av skaden", 24);
        
        //Knappen i layoutet som laster opp en fil. 
        lastOppFilKnapp = new Button("Last opp");
        lastOppFilKnapp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                    FileChooser filvelger = new FileChooser();
                    filvelger.getExtensionFilters().add(
                            new ExtensionFilter("Bilder", "*.png", "*.jpg", "*.gif") );
                    File valgtFil = filvelger.showOpenDialog(GUI.getStage());
                    
                    if( valgtFil == null){
                        filLastetOpp.setText( "Bilde ikke lastet opp.");
                    }//end of if
                    else{
                        if(bildefiler.add(valgtFil))
                            filLastetOpp.setText(  valgtFil.getName() + " er lagt til\nBildet vil bli registrert på denne skademeldingen."
                                                                      + "\nDu har nå lagt til " + ++antallBilder + " bilde(r)");
                        else
                            filLastetOpp.setText( "Feil i opplasting.\nBilde vil ikke bli registrert med denne skademeldingen."
                                    + " prøv på nytt");
                    }// end of else
            } // end of overriding method handle()
        }); // end of inner anonymous class
        
        
        //Kolonne 1
        returLayout.add( lastOppOverskrift, 1, 1);
        returLayout.add( lastOppSubskrift , 1, 2);
        returLayout.add( lastOppFilKnapp, 1, 3);
        returLayout.add( filLastetOpp, 1, 4);
        returLayout.setVgap(20);
        returLayout.setHgap(20);
        returLayout.setBorder( new Border( new BorderStroke(DARKGRAY,SOLID, new CornerRadii(5), THIN, new Insets(0)) ));
        returLayout.setPadding( new Insets( 5, 50, 20, 20) );
        lastOppFilKnapp.setAlignment(Pos.CENTER_RIGHT);
        return returLayout;
    }// end of method bildeOpplastning()
    
    /**
     * Et layout for registrering av skademeldinger. Fikser plassering, størrelse, og lytter på knappene. 
     * @return Layout for registrering av skademelding. 
     */
    private GridPane fyllInnLayout(){
        GridPane returLayout = new GridPane();
        
        fodselsNrInput = mellomStorInput();
        takstInput = mellomStorInput();
        
        tidspunktInput = litenInput();
        skadeBeskrivelseInput = storInput();
        vitneKontaktInput = storInput();
        
        
        skadetypeInput = new ChoiceBox();
        skadetypeInput.getItems().addAll("Bolig", "Båt", "Bil", "Reise");
        
        datoInput = new DatePicker();
        output = outputLabel();
        
        //Knappen i layoutet med en lytter koblet til som kjører metode registrerSkademelding()
        registrerKnapp = new Button("Registrer skademelding");
        registrerKnapp.setOnAction((ActionEvent e) -> {
            if(registrerSkademelding()){
               bildefiler = new ArrayList<>();
               tilbakemelding += "\nErstatning utbetalt: kr. " + takstInput.getText();        
               setFelterTomme();
               filLastetOpp.setText( "" );
            }// end of if
            output.setText(tilbakemelding);
        });// end of lambda expression
        
        //Kolonne 1
        returLayout.add( new Label("Kundens fødselsnummer:"), 1, 1);
        returLayout.add( fodselsNrInput, 1, 2);
        returLayout.add( new Label("Skadetype:"), 1, 3);
        returLayout.add( skadetypeInput, 1, 4);
        returLayout.add( new Label("Taksteringsbeløpet for skaden:"), 1, 5);
        returLayout.add( takstInput, 1, 6);
        returLayout.add( new Label("Beskrivelse av skaden:"), 1, 7);
        returLayout.add( skadeBeskrivelseInput, 1, 8);
        returLayout.add( registrerKnapp, 1, 9);
        
        //Kolonne 2
        returLayout.add( new Label("Dato inntruffet:"), 2, 1);
        returLayout.add( datoInput, 2, 2);
        returLayout.add( new Label("Tidspunkt inntruffet(Timer:Minutter):"), 2, 3);
        returLayout.add( tidspunktInput, 2, 4);
        returLayout.add( new Label("Kontaktinformasjon til eventuelle vitner:"), 2, 7);
        returLayout.add( vitneKontaktInput, 2, 8);
        returLayout.add( output, 2, 9);
        
        returLayout.setMargin(output, new Insets(10));
        returLayout.setHgap(40);
        returLayout.setVgap(10);
        
        return returLayout;
    }// end of method registrerSkademeldingLayout()
    
    /**
     * Henter tekst og validerer. Hvis alt valideres, registreres skademeldingen i kunderegisteret, og brukeren får melding avhengig av hva som er feil/galt 
     * @return En boolsk verdi som tilsier om skademeldingen ble registrert. Returnerer false hvis feltene ikke er fylt inn av brukeren, og hvis ikke feltene ble validert. 
     */
    private boolean registrerSkademelding(){
        if( !felterErFylt() )
            return false;
        if( !regexErOk())
            return false;
        try{
            
            String fodselsNr = fodselsNrInput.getText().trim();
            if( kundeRegister.finnKunde(fodselsNr) == null){
                 GUI.visInputFeilMelding("OBS!","Det finnes ingen kunder med fødselsnummer: " + fodselsNr);
                return false;
            }
        
            String skadetype = skadetypeInput.getValue().toString();
            double takst = Double.parseDouble(takstInput.getText().trim());
            String skadeBeskrivelse = skadeBeskrivelseInput.getText();
            Calendar dato = new GregorianCalendar( datoInput.getValue().getYear(), datoInput.getValue().getMonthValue()-1, datoInput.getValue().getDayOfMonth() );
            String tidspunkt = tidspunktInput.getText().trim();
            String vitneKontakt = vitneKontaktInput.getText();
            tilbakemelding = "";
            
            Skademelding skade = new Skademelding(skadetype, skadeBeskrivelse, vitneKontakt, takst, dato, tidspunkt, bildefiler );
            tilbakemelding = kundeRegister.registrerSkademelding(skade, fodselsNr);
            ForsikringsKunde skademeldingEier = kundeRegister.finnKunde( skade.getSkadeNr() );
            if( skademeldingEier == null)
                return false;
            if( skademeldingEier.getForsikringer().erTom() )
                return false;
            if( ! ( skademeldingEier.getForsikringer().harRiktigForsikring( skade.getSkadeType()) ) )
                return false;
            
        }// end of try
        catch(NumberFormatException | NullPointerException e){
            GUI.visProgramFeilMelding(e);
            return false;
        }//end of try-catch
        return true;
    }// end of method knappeLytter()
    
    /**
     * Sjekker om feltene i layoutet er tomme, og gir brukeren en melding om hva som må fylles inn. 
     * @return En boolsk verdi som tilsier om feltene er fylt, eller ikke.
     */
    private boolean felterErFylt(){
        if( fodselsNrInput.getText().trim().isEmpty()){
            visFyllInnMelding("fødselsnummer");
            return false;
        }
        
        else if( skadetypeInput.getValue() == null  ){
            visFyllInnMelding("skadetype");
            return false;
        }
        
        else if( takstInput.getText().trim().isEmpty()){
            visFyllInnMelding( "takst");
            return false;
        }
        
        else if( skadeBeskrivelseInput.getText().trim().isEmpty()){
            visFyllInnMelding("beskrivelse av skaden");
            return false;
        }
        
        else if( datoInput.getValue() == null ){
            visFyllInnMelding( "dato for skaden");
            return false;
        }
        
        else if( tidspunktInput.getText().trim().isEmpty()){
            visFyllInnMelding( "tidspunkt for skaden");
            return false;
        }
        
        else if( vitneKontaktInput.getText().trim().isEmpty()){
            visFyllInnMelding( "kontaktinfo til eventuelle vitner", "Finnes det ingen vitner, skriv dette.");
            return false;
        }
        return true;
    }// end of method felterErFylt()
    
    /**
     * Sjekker alle input-feltene for riktig format. Viser brukeren en melding om hva som er fylt inn feil. 
     * @return False hvis formatet er feil, og true hvis alt er OK. 
     */
    private boolean regexErOk(){
        
        if( ! (GUI.sjekkRegexFodselsNr( fodselsNrInput.getText().trim() ) ) ){
            GUI.visInputFeilMelding("OBS! Fødselsnummer inneholder 11 sifre.", "For å kunne registrere en skademelding må du fylle inn et gyldig fødselsnummer"
                                    + " med 11 sifre");
            return false;
        }
        else if( !( GUI.sjekkRegex( GUI.VALUTA_REGEX, takstInput.getText().trim() ) ) ){
            GUI.visInputFeilMelding("OBS! Taksten er i feil format", "For å kunne registrere en skademelding, må du fylle inn riktig takst for skaden. Angis i antall kroner.");
            return false;
        }
        else if( ! (GUI.sjekkRegex( GUI.DATO_REGEX, datoInput.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) ) ) ){
            System.out.println( datoInput.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) );
            GUI.visInputFeilMelding("OBS! Dato er i feil format", "For å kunne registrere en skademelding, må du fylle inn dato for skaden. Angis f.eks i dd/mm/åååå");
            return false; 
        }
        else if( ! (GUI.sjekkRegex( GUI.TIDSPUNKT_REGEX, tidspunktInput.getText().trim() ) ) ){
            GUI.visInputFeilMelding("OBS! Tidspunkt er i feil format", "For å kunne registrere en skademelding, må du fylle inn riktig tidspunkt for skaden. Angis i timer:minutter");
            return false;
        }
        return true;
    }// end of method regexErOk()
    
    /**
     * Tømmer alle feltene i registreringen av skademelding
     */
    private void setFelterTomme(){
        fodselsNrInput.setText("");
        skadetypeInput.setValue(null);
        takstInput.setText("");
        skadeBeskrivelseInput.setText("");
        datoInput.setValue(null);
        tidspunktInput.setText("");
        vitneKontaktInput.setText("");
    }// end of method setFelterTomme()
    
    /**
     * Viser en melding om hva brukeren må fylle inn.
     * @param s er meldingen om hva som må fylles inn. 
     */
    private void visFyllInnMelding(String s){
        Alert varsel = new Alert( INFORMATION);
        varsel.setTitle("OBS! Fyll inn " + s);
        varsel.setHeaderText(null);
        varsel.setContentText( "Du må fylle inn " + s + " for å kunne registrere en skademelding.");
        varsel.showAndWait();
    }// end of method visFyllInnMelding() med en parameter
    
    /**
     * Viser en melding om hva brukeren må fylle inn.
     * @param s er meldingen om hva som må fylles inn. 
     * @param s2 er en tekst som kan legges til for tilleggsinformasjon til brukeren. 
     */
    private void visFyllInnMelding(String s, String s2){
        Alert varsel = new Alert( INFORMATION);
        varsel.setTitle("OBS! Fyll inn " + s);
        varsel.setHeaderText(null);
        varsel.setContentText( "Du må fylle inn " + s + " for å kunne registrere en skademelding.\n" + s2);
        varsel.showAndWait();
    }// end of method visFyllInnMelding() med to parametre
    
    /**
     * 
     * @return et lite tekstfelt for input. 
     */
    private TextField litenInput(){
        TextField input = new TextField();
        
        input.setMinWidth(40);
        input.setPrefWidth(80);
        input.setMaxWidth(80);
        
        return input;
    }// end of method litenInput()
    
    /**
     * 
     * @return et mellomstort tekstfelt for input.
     */
    private TextField mellomStorInput(){
        TextField input = new TextField();
        
        input.setMinWidth(100);
        input.setPrefWidth(100);
        input.setMaxWidth(400);
        
        return input;
    }// end of method mellomStorInput()
    
    /**
     * 
     * @return et stort tekstfelt for input.
     */
    private TextArea storInput(){
        TextArea innskrift = new TextArea();
        innskrift.setMinWidth(400);
        innskrift.setPrefWidth(400);
        innskrift.setMaxWidth(800);
        innskrift.setMinHeight(200);
        innskrift.setPrefHeight(200);
        innskrift.setMaxHeight(800);
        
        return innskrift;
    } // end of method storreInputOmraade()
    
    /**
     * Setter størrelse og skrift. 
     * @return En label for outputs. 
     */
    private Label outputLabel(){
        Label nyoutput = new Label();
        nyoutput.setMaxWidth( GUI.getSkjermBredde()/4);
        nyoutput.setPrefWidth( GUI.getSkjermBredde()/4);
        nyoutput.setWrapText(true);
        nyoutput.setFont( font(20.00) );
        return nyoutput;
    } // end of method setOutputLabel()
}// end of class RegistrerSkadeLayout
