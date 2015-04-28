/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt.kundebehandling;

import Brukergrensesnitt.*;
import forsikringsprogram.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import static javafx.scene.control.Alert.AlertType.*;
import javafx.scene.layout.*;

/**
 *
 * @author fredrik
 */
public class RegistrerSkadeLayout extends GridPane {

    private Kunderegister kundeRegister;
    private Button registrerKnapp;
    private TextArea skadeBeskrivelseInput, vitneKontaktInput, output;
    private TextField fodselsNrInput, takstInput, erstatningsOutput, tidspunktInput;
    private ChoiceBox skadetypeInput;
    private DatePicker datoInput;
//    private String fodselsNr, skadetype, takst, skadeBeskrivelse, datoIntruffet, tidspunktInntruffet, erstatningsbeløp, vitneKontakt;
    
    public RegistrerSkadeLayout(Kunderegister register, TextArea output){
        opprettRegisteringLayout();
        this.kundeRegister = register;
        this.output = output;
    }
    
    private void opprettRegisteringLayout(){
        fodselsNrInput = mellomStorInput();
        takstInput = mellomStorInput();
        erstatningsOutput = mellomStorInput();
        tidspunktInput = litenInput();
        skadeBeskrivelseInput = storInput();
        vitneKontaktInput = storInput();
        
        skadetypeInput = new ChoiceBox();
        skadetypeInput.getItems().addAll("Bolig", "Båt", "Bil", "Reise");
        
        datoInput = new DatePicker();
        
        //Knappen i layoutet med en lytter koblet til som kjører metode registrerSkademelding()
        registrerKnapp = new Button("Registrer skademelding");
        registrerKnapp.setPadding(GUI.PADDING);
        registrerKnapp.setOnAction( new EventHandler<ActionEvent>(){ 
        
            public void handle( ActionEvent e){
                registrerSkademelding();
            }
        });
        
       
        
        //Kolonne 1
        add( new Label("Kundens fødselsnummer:"), 1, 1);
        add( fodselsNrInput, 1, 2);
        add( new Label("Skadetype:"), 1, 3);
        add( skadetypeInput, 1, 4);
        add( new Label("Taksteringsbeløpet for skaden:"), 1, 5);
        add( takstInput, 1, 6);
        add( new Label("Beskrivelse av skaden:"), 1, 7);
        add( skadeBeskrivelseInput, 1, 8);
        add( registrerKnapp, 1, 9);
        
        //Kolonne 2
        add( new Label("Dato inntruffet:"), 2, 1);
        add( datoInput, 2, 2);
        add( new Label("Tidspunkt inntruffet(Timer:Minutter):"), 2, 3);
        add( tidspunktInput, 2, 4);
        add( new Label("Erstatningbeløp kunden får utbetalt:"), 2, 5);
        add( erstatningsOutput, 2, 6);
        add( new Label("Kontaktinformasjon til eventuelle vitner:"), 2, 7);
        add( vitneKontaktInput, 2, 8);
        
        setBorder(GUI.KANTLINJE);
        setHgap(20);
        setVgap(10);
        
        
    }// end of method opprettRegistreringsLayout
    
    
    /**
     * Henter tekst 
     */
    private void registrerSkademelding(){
        if( !felterErFylt() )
            return;
        if( !regexErOk())
            return;
        try{
        String fodselsNr = fodselsNrInput.getText();
        String skadetype = skadetypeInput.getValue().toString();
        double takst = Double.parseDouble(takstInput.getText());
        String skadeBeskrivelse = skadeBeskrivelseInput.getText();
        Calendar dato = new GregorianCalendar( datoInput.getValue().getYear(), datoInput.getValue().getMonthValue()-1, datoInput.getValue().getDayOfMonth() );
        String tidspunkt = tidspunktInput.getText();
        String vitneKontakt = vitneKontaktInput.getText();
        
       Skademelding skade = new Skademelding(skadetype, skadeBeskrivelse, vitneKontakt, takst, dato, tidspunkt ); 
       
       String melding = kundeRegister.registrerSkademelding(skade, fodselsNr);
        
       if( kundeRegister.finnKunde(fodselsNr) == null)
           return;
        setFelterTomme();
        }// end of try
        
        catch(NumberFormatException | NullPointerException e){
            GUI.visProgramFeilMelding(e);
            return;
        }
        
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
        input.setPadding( GUI.PADDING );
        
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
        input.setPadding( GUI.PADDING );
        
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
        innskrift.setPadding( GUI.PADDING );
        
        return innskrift;
    } // end of method storreInputOmraade()
    
}// end of class RegistrerSkadeLayout
