/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt.kundebehandling;

import Brukergrensesnitt.GUI;
import forsikringsprogram.*;
import javafx.collections.*;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 *
 * @author Jens
 */
public class KundesokLayout extends GridPane{
    
    private Kunderegister kundeRegister;
    private TitledPane sokFodselsNrLayout, sokNavnLayout, sokForsikringstypeLayout, sokSkadeNrLayout, sokSkadetypeLayout;
    private TextArea utskriftsomraade;
    
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
    
    private GridPane sokFodselsNr(){
        GridPane fodsel = new GridPane();
        
        TextField fodselsNrInput = new TextField();
        Button sokKnapp = new Button("Søk");
        
        fodsel.addRow( 1, new Label("Fødselsnummeret til kunden:"));
        fodsel.addRow( 2, fodselsNrInput);
        fodsel.addRow( 3, sokKnapp );
        
        return fodsel;
    } // end of sokFodselsNr()
    
    private GridPane sokNavn(){
        GridPane navnLayout = new GridPane();
        
        TextField fornavnInput = new TextField();
        TextField etternavnInput = new TextField();
        Button sokKnapp = new Button("Søk");
        
        navnLayout.add( new Label("Fornavn:"), 1 , 1);
        navnLayout.add( fornavnInput, 1, 2);
        navnLayout.add( new Label("Etternavn:"), 2, 1);
        navnLayout.add( etternavnInput, 2, 2);
        navnLayout.add( sokKnapp, 1, 3);
        
        return navnLayout;
    } // end of sokNavn()
    
    /**
     * 
     * @return en GridPane-Layout som inneholder for riktig type informasjon og inputfelter for å søke på kunder med gitt forsikringstype. 
     */
    private GridPane sokForsikringstype(){
        GridPane forsikringLayout = new GridPane();
        
        ChoiceBox forsikringstype = new ChoiceBox();
        forsikringstype.getItems().addAll("Boligforsikring", "Båtforsikring", "Bilforsikring", "Reiseforsikring");
        Button sokKnapp = new Button("Søk");
        
        forsikringLayout.add( new Label("Type forsikring:"), 1, 1);
        forsikringLayout.add( forsikringstype, 1, 2);
        forsikringLayout.add( sokKnapp, 2, 2);
            
        return forsikringLayout;
    }// end of method sokForsikringstype() 
    
    /**
     * Inneholder et tekstfelt og en knapp. 
     * @return Et layout med søk-muligheter. 
     */
    private GridPane sokSkadeNr(){
        GridPane skadeLayout = new GridPane();
        
        TextField skadeNrInput = new TextField();
        Button sokKnapp = new Button("Søk");
        
        skadeLayout.add( new Label("Skadenummer:"), 1, 1);
        skadeLayout.add( skadeNrInput, 1, 2);
        skadeLayout.add( sokKnapp, 2, 2);
        return skadeLayout;
    } // end of method sokSkadeNr()
    
    private GridPane sokSkadetype(){
        GridPane skadetypeLayout = new GridPane();
        
        ChoiceBox skadetype = new ChoiceBox();
        skadetype.setPadding(GUI.PADDING);
        skadetype.getItems().addAll("Boligskade", "Båtskade", "Bilskade", "Reiseskade");
        Button sokKnapp = new Button("Søk");
        
        skadetypeLayout.add( new Label("Skadetype:"), 1, 1);
        skadetypeLayout.add( skadetype, 1, 2);
        skadetypeLayout.add( sokKnapp, 2, 2);
        
        return skadetypeLayout;
    }// end of method sokSkadetype()
    
    private TextArea tekstOmraade(){
        TextArea utskrift = new TextArea();
        utskrift.setPadding(GUI.PADDING);
        
        return utskrift;
    }// end of method tekstOmraade()
}// end of class SokLayout
