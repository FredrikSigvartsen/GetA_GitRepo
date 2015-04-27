/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt.kundebehandling;

import Brukergrensesnitt.GUI;
import forsikringsprogram.*;
import java.util.List;
import java.util.ListIterator;
import javafx.event.*;
import javafx.scene.control.*;
import static javafx.scene.control.Alert.AlertType.INFORMATION;
import javafx.scene.layout.*;

/**
 *
 * @author Jens
 */
public class KundesokLayout extends GridPane{
    
    private Kunderegister kundeRegister;
    private TitledPane sokFodselsNrLayout, sokNavnLayout, sokForsikringstypeLayout, sokSkadeNrLayout, sokSkadetypeLayout;
    private TextArea utskriftsomraade;
    private TextField fodselsNrInput, fornavnInput, etternavnInput, skadeNrInput;
    private Button sokKnapp, sokNavnKnapp, sokForsikringstypeKnapp, sokSkadeNrKnapp, sokSkadetypeKnapp;
    private ChoiceBox forsikringstypeInput, skadetypeInput;
    
    public KundesokLayout(Kunderegister register){
        super();
        this.kundeRegister = register;
        opprettLayout();
        
    }// end of constructor
    
    private void opprettLayout(){
        
        sokFodselsNrLayout = new TitledPane( "Fødselsnummer", sokFodselsNr() );
        sokFodselsNrLayout.setExpanded(false);
        
        sokNavnLayout = new TitledPane( "Navn", sokNavn() );
        sokNavnLayout.setExpanded(false);
        
        sokForsikringstypeLayout = new TitledPane( "Forsikringstype", sokForsikringstype() );
        sokForsikringstypeLayout.setExpanded(false);
        
        sokSkadeNrLayout = new TitledPane( "Skademelding nummer", sokSkadeNr() );
        sokSkadeNrLayout.setExpanded(false);
        
        sokSkadetypeLayout = new TitledPane( "Skadetype", sokSkadetype() );
        sokSkadetypeLayout.setExpanded(false);
        
        utskriftsomraade = tekstOmraade();
        
        Label skadeLabel = new Label("Søk på skademeldinger med");
        Label kundeLabel = new Label("Søk på kunder med");
        skadeLabel.setPadding(GUI.PADDING);
        kundeLabel.setPadding(GUI.PADDING);
        
        addRow(1, kundeLabel );
        addRow(2, sokFodselsNrLayout );
        addRow(3, sokNavnLayout );
        addRow(4, sokForsikringstypeLayout );
        addRow(5, skadeLabel );
        addRow(6, sokSkadeNrLayout );
        addRow(7, sokSkadetypeLayout );
        addRow(8, utskriftsomraade);
        
        setBorder(GUI.KANTLINJE);
        setPadding(GUI.PADDING);
    } // end of method opprettLayout()
    
    /**
     * Et layout hvor bruekren kan søke opp kunder med fødselsnummer.
     * @return et GridPane layout med kundesøk. 
     */
    private GridPane sokFodselsNr(){
        GridPane fodsel = new GridPane();
        
        fodselsNrInput = new TextField();
        sokKnapp = new Button("Søk");
        sokKnapp.setOnAction((ActionEvent e) -> {
            finnKundeMedFodselsNr();
            fodselsNrInput.setText("");
        });
        
        fodsel.addRow( 1, new Label("Fødselsnummeret til kunden:"));
        fodsel.addRow( 2, fodselsNrInput);
        fodsel.addRow( 3, sokKnapp );
        
        return fodsel;
    } // end of sokFodselsNr()
    
    private GridPane sokNavn(){
        GridPane navnLayout = new GridPane();
        
        fornavnInput = new TextField();
        etternavnInput = new TextField();
        sokNavnKnapp = new Button("Søk");
        sokNavnKnapp.setOnAction((ActionEvent e) -> {
            finnKundeMedNavn();
        });
        
        navnLayout.add( new Label("Fornavn:"), 1 , 1);
        navnLayout.add( fornavnInput, 1, 2);
        navnLayout.add( new Label("Etternavn:"), 2, 1);
        navnLayout.add( etternavnInput, 2, 2);
        navnLayout.add( sokNavnKnapp, 1, 3);
        
        return navnLayout;
    } // end of sokNavn()
    
    
    
    /**
     * 
     * @return en GridPane-Layout som inneholder for riktig type informasjon og inputfelter for å søke på kunder med gitt forsikringstype. 
     */
    private GridPane sokForsikringstype(){
        GridPane forsikringLayout = new GridPane();
        
        forsikringstypeInput = new ChoiceBox();
        forsikringstypeInput.getItems().addAll("Boligforsikring", "Båtforsikring", "Bilforsikring", "Reiseforsikring");
        sokForsikringstypeKnapp = new Button("Søk");
        sokForsikringstypeKnapp.setOnAction((ActionEvent e) -> {
            finnForsikringer();
        });
        
        forsikringLayout.add( new Label("Type forsikring:"), 1, 1);
        forsikringLayout.add( forsikringstypeInput, 1, 2);
        forsikringLayout.add( sokForsikringstypeKnapp, 2, 2);
            
        return forsikringLayout;
    }// end of method sokForsikringstype() 
    
    
    /**
     * Inneholder et tekstfelt og en knapp. 
     * @return Et layout med søk-muligheter. 
     */
    private GridPane sokSkadeNr(){
        GridPane skadeLayout = new GridPane();
        
        skadeNrInput = new TextField();
        sokSkadeNrKnapp = new Button("Søk");
        sokSkadeNrKnapp.setOnAction((ActionEvent e) -> {
            finnSkadeMedSkadeNr();
        });
        
        skadeLayout.add( new Label("Skadenummer:"), 1, 1);
        skadeLayout.add( skadeNrInput, 1, 2);
        skadeLayout.add( sokSkadeNrKnapp, 2, 2);
        return skadeLayout;
    } // end of method sokSkadeNr()
    
    
    /**
     * 
     * @return et liten GridPane-layout 
     */
    private GridPane sokSkadetype(){
        GridPane skadetypeLayout = new GridPane();
        
        skadetypeInput = new ChoiceBox();
        skadetypeInput.getItems().addAll("Boligskade", "Båtskade", "Bilskade", "Reiseskade");
        sokSkadetypeKnapp = new Button("Søk");
        sokSkadetypeKnapp.setOnAction((ActionEvent e) -> {
            finnSkademeldingerMedSkadeType();
        });
        
        skadetypeLayout.add( new Label("Skadetype:"), 1, 1);
        skadetypeLayout.add( skadetypeInput, 1, 2);
        skadetypeLayout.add( sokSkadetypeKnapp, 2, 2);
        
        return skadetypeLayout;
    }// end of method sokSkadetype()
    
    /**
     * Viser en melding om hvilke felter brukeren må fylle inn.
     * @param s er meldingen om hva som må fylles inn. 
     */
    private void visMelding(String overskrift, String innhold){
        Alert varsel = new Alert( INFORMATION);
        varsel.setTitle("OBS! " + overskrift);
        varsel.setHeaderText(null);
        varsel.setContentText( innhold);
        varsel.showAndWait();
    }// end of method visFyllInnMelding() med en parameter
    
    private TextArea tekstOmraade(){
        TextArea utskrift = new TextArea();
        utskrift.setPadding(GUI.PADDING);
        
        return utskrift;
    }// end of method tekstOmraade()
    
    
    //Kontroll-metodene: 
    
     private void finnKundeMedFodselsNr(){
        
        if( fodselsNrInput.getText().trim().isEmpty()){
          visMelding("Fyll inn fødselsnummer.", "Fyll inn fødselsnummeret til kunden du vil søke opp.");
          return;
        }
        
        try{
            String fodselsNr = fodselsNrInput.getText();
            ForsikringsKunde kunden = kundeRegister.finnKunde( fodselsNr );
            if(kunden == null){
               utskriftsomraade.setText("Kunden med dette fødselsnummer finnes ikke i vårt system.");
               return;
            }
            utskriftsomraade.setText("Følgende kunde funnet i systemet:" + kunden.toString());
            return;
        }
        catch(NullPointerException npe){
            GUI.visProgramFeilMelding(npe);
            return;
        }
    }// end of method finnKundeMedFodselsNr()
     
     /**
      * 
      */
    private void finnKundeMedNavn(){
        if( fornavnInput.getText().trim().isEmpty()){
            visMelding("Fyll inn fornavn.", "Skriv inn fornavnet på kunden du vil søke på.");
            return;
        }
        else if( etternavnInput.getText().trim().isEmpty()){
            visMelding("Fyll inn etternavn", "Skriv inn etternavnet på kunden du vil søke på");
            return;
        }
        
        try{
            String fornavn = fornavnInput.getText();
            String etternavn = etternavnInput.getText();
            ForsikringsKunde kunden = kundeRegister.finnKunde(fornavn, etternavn);
            if( kunden == null){
                utskriftsomraade.setText( fornavn + " " + etternavn + " finnes ikke i systemet vårt." ) ;
                return;
            }
            utskriftsomraade.setText( "Følgende kunde funnet i systemet:" + kunden.toString() ) ;
            return;
        }
        catch(NullPointerException npe){
            GUI.visProgramFeilMelding(npe);
            return;
        }
    }// end of method finnKundeMedNavn()
    
    /**
     * Finner alle forsikringer av typen brukeren velger. 
     */
    private void finnForsikringer(){
        if(forsikringstypeInput.getValue() == null){
            visMelding("Velg forsikringstype.","Velg hvilken type forsikringer du vil søke på");
            return;
        }
        try{
            String forsikringstype = forsikringstypeInput.getValue().toString();
        
            List<Forsikring> forsikringer = kundeRegister.finnForsikringer(forsikringstype);
        
            if(forsikringer.isEmpty()){
               utskriftsomraade.setText("Finnes ingen forsikringer av typen " + forsikringstype);
               return;
            }
            ListIterator<Forsikring> iter = forsikringer.listIterator();
            while(iter.hasNext()){
                utskriftsomraade.appendText( iter.next().toString());
            }
        }// end of try
        catch(NullPointerException npe){
            GUI.visProgramFeilMelding(npe);
            return;
        }
    }// end of method finnForsikringer()
    
    /**
     * Finner skademeldingen med riktig type skadenummer. 
     */
    private void finnSkadeMedSkadeNr(){
        if( skadeNrInput.getText().trim().isEmpty()){
            visMelding("Fyll inn skadenummer", "Skriv inn skadenummeret på den skaden du vil søke opp");
            return;
        }
        
        try{
            int skadeNr = Integer.parseInt(skadeNrInput.getText());
            
            Skademelding skademeldingen = kundeRegister.finnSkademeldinger(skadeNr);
            if( skademeldingen == null){
                utskriftsomraade.setText("Skademelding nr." + skadeNr + " finnes ikke i vårt system.");
                return;
            }
            utskriftsomraade.setText("Følgende skademelding funnet:" + skademeldingen.toString());
        }
        catch( NumberFormatException | NullPointerException nfe){
            GUI.visProgramFeilMelding(nfe);
            return;
        }
    }// end of method finnSkadeMedSkadeNr()
    
    /**
     * Finner alle skademeldinger av type brukeren velger. 
     */
    private void finnSkademeldingerMedSkadeType(){
        if(skadetypeInput.getValue() == null){
            visMelding("Velg skadetype.", "Velg hvilken type skademeldinger du vil søke opp");
            return;
        }
        try{
            String skadetype = skadetypeInput.getValue().toString();
            
            List<Skademelding> skademeldinger = kundeRegister.finnSkademeldinger(skadetype);
            if(skademeldinger.isEmpty()){
                utskriftsomraade.setText("Det finnes ingen skademeldinger av typen " + skadetype);
                return;
            }
            
        }// end of try
        catch(NullPointerException npe){
            GUI.visProgramFeilMelding(npe);
            return;
        }
    }// end of method finnSkademeldingerMedSkadeType()
}// end of class SokLayout
