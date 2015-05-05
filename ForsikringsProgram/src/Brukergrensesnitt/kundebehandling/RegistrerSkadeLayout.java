/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt.kundebehandling;

import Brukergrensesnitt.*;
import forsikringsprogram.*;
import java.io.File;
import java.net.MalformedURLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import static javafx.scene.control.Alert.AlertType.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import static javafx.scene.text.Font.font;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 *
 * @author fredrik
 */
public class RegistrerSkadeLayout extends GridPane {

    private Kunderegister kundeRegister;
    private Button registrerKnapp, lastOppFilKnapp;
    private TextArea skadeBeskrivelseInput, vitneKontaktInput;
    private TextField fodselsNrInput, takstInput, erstatningsOutput, tidspunktInput;
    private GridPane bildeLayout, registreringsLayout;
    private ChoiceBox skadetypeInput;
    private DatePicker datoInput;
    private Label output;
    private List<File> bilder;
    
    public RegistrerSkadeLayout(Kunderegister register){
        opprettRegisteringLayout();
        this.kundeRegister = register;
    }
    
    private void opprettRegisteringLayout(){
        bildeLayout = bildeOpplastning();
        registreringsLayout = registreringLayout();
        
        addColumn( 1, registreringsLayout);
        addColumn( 2, bildeLayout);
        setPadding(new Insets(30, 20, 30, 50));
        setVgap(10);
        setHgap(20);
        
    }// end of method opprettRegistreringsLayout
    
    /**
     * Laster opp bilder, og lagrer dem som filer i en ArrayList av filer. 
     * @return Et layout med opplastning av bilder. 
     */
    private GridPane bildeOpplastning(){
        GridPane returLayout = new GridPane();
        Label lastOppOverskrift = new Label("Last opp bilder av skaden her");
        lastOppOverskrift.setFont( font(22.5));
        Label lastOppSubskrift = new Label("  - har du ingen bilder, hopp over dette.");
        lastOppSubskrift.setFont( font(14));
        Label filLastetOpp = new Label();
        bilder = new ArrayList<>();
        
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
                        if(bilder.add(valgtFil))
                            filLastetOpp.setText(  valgtFil.getName() + " er lagt til, og vil bli registrert på denne skademeldingen.");
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
        return returLayout;
    }// end of method bildeOpplastning()
    
    /**
     * Her foregår registreringen av en skademelding
     * @return Layout for registrering av skademelding. 
     */
    private GridPane registreringLayout(){
        GridPane returLayout = new GridPane();
        
        fodselsNrInput = mellomStorInput();
        takstInput = mellomStorInput();
        erstatningsOutput = mellomStorInput();
        erstatningsOutput.setEditable(false);
        
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
            registrerSkademelding();
        });
        
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
        returLayout.add( new Label("Erstatningbeløp kunden får utbetalt:"), 2, 5);
        returLayout.add( erstatningsOutput, 2, 6);
        returLayout.add( new Label("Kontaktinformasjon til eventuelle vitner:"), 2, 7);
        returLayout.add( vitneKontaktInput, 2, 8);
        returLayout.add( output, 2, 9);
        
        returLayout.setMargin(output, new Insets(10));
        returLayout.setHgap(40);
        returLayout.setVgap(10);
        
       
        
        return returLayout;
    }// end of method registreringsLayout()
    
    /**
     * Henter tekst 
     */
    private void registrerSkademelding(){
        if( !felterErFylt() )
            return;
        if( !regexErOk())
            return;
        try{
            
            String fodselsNr = fodselsNrInput.getText().trim();
            if( kundeRegister.finnKunde(fodselsNr) == null){
                 output.setText("Det finnes ingen kunder med fødselsnummer: " + fodselsNr);
                return;
            }
        
            String skadetype = skadetypeInput.getValue().toString();
            double takst = Double.parseDouble(takstInput.getText().trim());
            String skadeBeskrivelse = skadeBeskrivelseInput.getText().trim();
            Calendar dato = new GregorianCalendar( datoInput.getValue().getYear(), datoInput.getValue().getMonthValue()-1, datoInput.getValue().getDayOfMonth() );
            String tidspunkt = tidspunktInput.getText().trim();
            String vitneKontakt = vitneKontaktInput.getText().trim();
        
            Skademelding skade = new Skademelding(skadetype, skadeBeskrivelse, vitneKontakt, takst, dato, tidspunkt, bilder ); 
            output.setText(  kundeRegister.registrerSkademelding(skade, fodselsNr) );
            setFelterTomme();
        }// end of try
        catch(NumberFormatException | NullPointerException e){
            GUI.visProgramFeilMelding(e);
            return;
        }//end of try-catch
    }// end of method knappeLytter()
    
    /**
     * Sjekker om feltene i layoutet er tomme, og gir brukeren en melding om hva som må fylles inn. 
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
        
        if( ! (GUI.sjekkRegex( "^\\d{11}$", fodselsNrInput.getText() ) ) ){
            GUI.visInputFeilMelding("OBS! Fødselsnummer inneholder 11 sifre.", "For å kunne registrere en skademelding må du fylle inn et eksisterende fødselsnummer"
                                    + " med 11 sifre");
            return false;
        }
        else if( !( GUI.sjekkRegex( GUI.VALUTA_REGEX, takstInput.getText() ) ) ){
            GUI.visInputFeilMelding("OBS! Taksten er i feil format", "For å kunne registrere en skademelding, må du fylle inn riktig takst for skaden. Angis i antall kroner.");
            return false;
        }
        else if( ! (GUI.sjekkRegex( GUI.DATO_REGEX, datoInput.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) ) ) ){
            System.out.println( datoInput.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) );
            GUI.visInputFeilMelding("OBS! Dato er i feil format", "For å kunne registrere en skademelding, må du fylle inn dato for skaden. Angis f.eks i dd/mm/åååå");
            return false; 
        }
        else if( ! (GUI.sjekkRegex( GUI.TIDSPUNKT_REGEX, tidspunktInput.getText() ) ) ){
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
        erstatningsOutput.setText("");
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
    
    private Label outputLabel(){
        Label nyoutput = new Label();
        nyoutput.setMaxWidth( GUI.getSkjermBredde()/4);
        nyoutput.setPrefWidth( GUI.getSkjermBredde()/4);
        nyoutput.setWrapText(true);
        nyoutput.setFont( font(20.00) );
        return nyoutput;
    } // end of method setOutputLabel()
    
    
}// end of class RegistrerSkadeLayout
