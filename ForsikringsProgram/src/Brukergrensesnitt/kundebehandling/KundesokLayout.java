/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt.kundebehandling;

import Brukergrensesnitt.GUI;
import static Brukergrensesnitt.GUI.getSkjermBredde;
import static Brukergrensesnitt.GUI.getSkjermHoyde;
import forsikringsprogram.*;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import static javafx.scene.control.Alert.AlertType.INFORMATION;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import static javafx.scene.text.Font.font;

/**
 *
 * @author Jens
 */
public class KundesokLayout extends GridPane{
    
    private Kunderegister kundeRegister;
    private TitledPane sokFodselsNrLayout, sokNavnLayout, sokForsikringstypeLayout, sokSkadeNrLayout, sokSkadetypeLayout;
    private TextArea output;
    private TextField fodselsNrInput, fornavnInput, etternavnInput, skadeNrInput;
    private Button sokKnapp, sokNavnKnapp, sokForsikringstypeKnapp, sokSkadeNrKnapp, sokSkadetypeKnapp, nesteBildeKnapp, forrigeBildeKnapp;
    private ChoiceBox forsikringstypeInput, skadetypeInput;
    private GridPane sokLayout, outputLayout, bildeviserLayout;
    private ImageView gjeldendeBildeviser;
    private Skademelding gjeldendeSkademelding;
    private List<Image> skadeBilder;
    
    
    public KundesokLayout(Kunderegister register){
        super();
        this.kundeRegister = register;
        opprettLayout();
        
    }// end of constructor
    
    /**
     * Oppretter utseende på layoutet. 
     */
    private void opprettLayout(){
        sokLayout = sokLayout();
        outputLayout = outputLayout();
        bildeviserLayout = bildeviserLayout();
        bildeviserLayout.setVisible(true);
        
        addColumn(1, sokLayout);
        addColumn(2, outputLayout);
        addColumn(3, bildeviserLayout);
        
        setHgap(40);
        setVgap(20);
        setMargin(outputLayout, new Insets(40, 0, 0, 0)); 
        setPadding( new Insets(30, 20, 30, 50) ) ;
        
    } // end of method opprettLayout()
    
    /**
     * Alle søk-outputs havner i output-layoutet. 
     * @return GridPane-layout for behandling av outputs av søk-funksjonene
     */
    private GridPane outputLayout(){
        GridPane returOutput = new GridPane();
        output = output();
        
        returOutput.addRow(1, output);
        return returOutput;
    }// end of method outputLayout()
    
    private GridPane bildeviserLayout(){
        GridPane returLayout = new GridPane();
        returLayout.setVisible(false);
        gjeldendeBildeviser = imageViewer();
        HBox knappedisplay = visBildeKnappeDisplay();
        
        
        sokSkadeNrKnapp.setOnAction((ActionEvent e) -> {
            skadeBilder = behandleBilder();
        });
        
        returLayout.addRow(1, gjeldendeBildeviser);
        returLayout.addRow(2, knappedisplay);
        
        
        returLayout.setVgap(15);
        returLayout.setHgap(15);
        return returLayout;
    } // end of method bildeviserLayout()
    /**
     * 
     * @return 
     */
    private GridPane sokLayout(){
        GridPane returLayout = new GridPane();
        
        sokFodselsNrLayout = new TitledPane( "Fødselsnummer", sokFodselsNr() );
        sokFodselsNrLayout.setExpanded(false);
        sokFodselsNrLayout.setOnMouseClicked((MouseEvent t) -> {
            settUtvidet(sokFodselsNrLayout);
        });
        
        sokNavnLayout = new TitledPane( "Navn", sokNavn() );
        sokNavnLayout.setExpanded(false);
        sokNavnLayout.setOnMouseClicked((MouseEvent t) -> {
            settUtvidet(sokNavnLayout);
        });
        
        sokForsikringstypeLayout = new TitledPane( "Forsikringstype", sokForsikringstype() );
        sokForsikringstypeLayout.setExpanded(false);
        sokForsikringstypeLayout.setOnMouseClicked((MouseEvent t) -> {
            settUtvidet(sokForsikringstypeLayout);
        });
        
        sokSkadeNrLayout = new TitledPane( "Skademelding nummer", sokSkadeNr() );
        sokSkadeNrLayout.setExpanded(false);
        sokSkadeNrLayout.setOnMouseClicked((MouseEvent t) -> {
            settUtvidet(sokSkadeNrLayout);
        });
        
        sokSkadetypeLayout = new TitledPane( "Skadetype", sokSkadetype() );
        sokSkadetypeLayout.setExpanded(false);
        sokSkadetypeLayout.setOnMouseClicked((MouseEvent t) -> {
            settUtvidet(sokSkadetypeLayout);
        });
        
        
        
        Label skadeLabel = new Label("Søk på skademeldinger med");
        skadeLabel.setFont( font(22.5) );
        skadeLabel.setUnderline(true);
        Label kundeLabel = new Label("Søk på kunder med");
        kundeLabel.setFont( font(22.5) );
        kundeLabel.setUnderline(true);
        
        returLayout.addRow(1,  kundeLabel );
        returLayout.setMargin( kundeLabel, new Insets(4, 0, 10, 0));
        returLayout.addRow(2,  sokFodselsNrLayout );
        returLayout.addRow(3,  sokNavnLayout );
        returLayout.addRow(4,  sokForsikringstypeLayout );
        returLayout.addRow(5,  skadeLabel );
        returLayout.setMargin( skadeLabel, new Insets(4, 0, 10, 0));
        returLayout.addRow(6,  sokSkadeNrLayout );
        returLayout.addRow(7,  sokSkadetypeLayout );
        
        returLayout.setMinHeight(GUI.getSkjermHoyde());
        returLayout.setMaxHeight(GUI.getSkjermHoyde());
        returLayout.setPrefHeight(GUI.getSkjermHoyde());
        returLayout.setVgap(10);
        returLayout.setHgap(20);
        return returLayout;
    }// end of method returLayout()

    
    private HBox visBildeKnappeDisplay(){
        HBox returKnapper = new HBox();
        
        nesteBildeKnapp = new Button("Neste");
        nesteBildeKnapp.setOnAction((ActionEvent e) -> {
            nesteBilde();
        });
        
        forrigeBildeKnapp = new Button("Forrige");
        forrigeBildeKnapp.setOnAction((ActionEvent e) -> {
            forrigeBilde();
        });
        
        
        returKnapper.setHgrow(nesteBildeKnapp, Priority.ALWAYS);
        returKnapper.setHgrow(forrigeBildeKnapp, Priority.ALWAYS);
        nesteBildeKnapp.setMaxWidth(100);
        forrigeBildeKnapp.setMaxWidth(100);
        returKnapper.getChildren().addAll(nesteBildeKnapp, forrigeBildeKnapp);
        returKnapper.setSpacing(100);
        
        return returKnapper;
    } // end of method visBildeKnappeDisplay()
    
    private void nesteBilde(){
        ListIterator<Image> iter = skadeBilder.listIterator(skadeBilder.indexOf( gjeldendeBildeviser.getImage() ));
        if(iter.hasNext())
            gjeldendeBildeviser.setImage( iter.next());
    }
    private void forrigeBilde(){
        
    }
    private List<Image> behandleBilder(){
        List<File> filBilder = gjeldendeSkademelding.getBilder();
        skadeBilder = new ArrayList<>();
        
        ListIterator<File> iter = filBilder.listIterator();
        while(iter.hasNext()){
            try {
                Image gjeldendeBilde = new Image (iter.next().toURI().toURL().toString() );
                skadeBilder.add(gjeldendeBilde);
            } catch (MalformedURLException ex) {
                GUI.visProgramFeilMelding(ex);
            }// end of try-catch
        }// end of while
        return skadeBilder;
    }// end of method behandleBilder()
        
    /**
     * 
     * @return 
     */
    private ImageView imageViewer(){
        ImageView returViser = new ImageView();
        
        returViser.setSmooth(true);
        returViser.setFitWidth(GUI.getSkjermBredde() / 2.5);
        returViser.setFitHeight(GUI.getSkjermHoyde() / 2);
        
        return returViser;
    }// end of method imageViewer()
    
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
        fodsel.setHgap(10);
        fodsel.setVgap(10);
        
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
        navnLayout.setHgap(10);
        navnLayout.setVgap(10);
        return navnLayout;
    } // end of sokNavn()
    
    /**
     * 
     * @return en GridPane-Layout som inneholder for riktig type informasjon og inputfelter for å søke på kunder med gitt forsikringstype. 
     */
    private GridPane sokForsikringstype(){
        GridPane forsikringLayout = new GridPane();
        
        forsikringstypeInput = new ChoiceBox();
        forsikringstypeInput.getItems().addAll("Bolig", "Båt", "Bil", "Reise");
        sokForsikringstypeKnapp = new Button("Søk");
        sokForsikringstypeKnapp.setOnAction((ActionEvent e) -> {
            finnForsikringer();
        });
        
        forsikringLayout.add( new Label("Type forsikring:"), 1, 1);
        forsikringLayout.add( forsikringstypeInput, 1, 2);
        forsikringLayout.add( sokForsikringstypeKnapp, 2, 2);
        forsikringLayout.setHgap(10);
        forsikringLayout.setVgap(10);
            
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
        skadeLayout.setHgap(10);
        skadeLayout.setVgap(10);
        return skadeLayout;
    } // end of method sokSkadeNr()
    
    
    /**
     * 
     * @return et liten GridPane-layout 
     */
    private GridPane sokSkadetype(){
        GridPane skadetypeLayout = new GridPane();
        
        skadetypeInput = new ChoiceBox();
        skadetypeInput.getItems().addAll("Bolig", "Båt", "Bil", "Reise");
        sokSkadetypeKnapp = new Button("Søk");
        sokSkadetypeKnapp.setOnAction((ActionEvent e) -> {
            finnSkademeldingerMedSkadeType();
        });
        
        skadetypeLayout.add( new Label("Velg skadetype:"), 1, 1);
        skadetypeLayout.add( skadetypeInput, 1, 2);
        skadetypeLayout.add( sokSkadetypeKnapp, 2, 2);
        skadetypeLayout.setHgap(10);
        skadetypeLayout.setVgap(10);
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
               output.setText("Kunden med dette fødselsnummer finnes ikke i vårt system.");
               return;
            }
            output.setText("Følgende kunde funnet i systemet:" + kunden.toString());
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
                output.setText( fornavn + " " + etternavn + " finnes ikke i systemet vårt." ) ;
                return;
            }
            output.setText( "Følgende kunde funnet i systemet:" + kunden.toString() ) ;
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
        
            List<ForsikringsKunde> forsikringer = kundeRegister.finnForsikringer(forsikringstype);
        
            if(forsikringer.isEmpty()){
               output.setText("Finnes ingen forsikringer av typen " + forsikringstype);
               return;
            }
            ListIterator<ForsikringsKunde> iter = forsikringer.listIterator();
            while(iter.hasNext()){
                output.appendText( iter.next().toString());
            }
        }// end of try// end of try
        catch(NullPointerException npe){
            GUI.visProgramFeilMelding(npe);
        }
    }// end of method finnForsikringer()
    
    /**
     * Finner gjeldendeSkademelding med riktig type skadenummer. 
     */
    private void finnSkadeMedSkadeNr(){
        if( skadeNrInput.getText().trim().isEmpty()){
            visMelding("Fyll inn skadenummer", "Skriv inn skadenummeret på den skaden du vil søke opp");
            return;
        }
        
        try{
            int skadeNr = Integer.parseInt(skadeNrInput.getText());
            
            gjeldendeSkademelding = kundeRegister.finnSkademeldinger(skadeNr);
            if( gjeldendeSkademelding == null){
                output.setText("Skademelding nr." + skadeNr + " finnes ikke i vårt system.");
                return;
            }
            output.setText("Følgende skademelding funnet:" + gjeldendeSkademelding.toString());
            bildeviserLayout.setVisible(true);
            skadeBilder = behandleBilder();
        if( gjeldendeSkademelding.getBilder() == null){
            bildeviserLayout.addRow(1, new Label("Ingen bilder lagt til i denne skademeldingen."));
        }
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
                output.setText("Det finnes ingen skademeldinger av typen " + skadetype);
                return;
            }
            ListIterator<Skademelding> iter = skademeldinger.listIterator();
            while(iter.hasNext()){
                output.appendText( iter.next().toString());
            }
            
        }// end of try// end of try
        catch(NullPointerException npe){
            GUI.visProgramFeilMelding(npe);
            return;
        }
    }// end of method finnSkademeldingerMedSkadeType()
    
    private TextArea output(){
        TextArea returOutput = new TextArea();
                returOutput.setMinWidth(getSkjermBredde() / 3.5);
                returOutput.setMaxWidth(getSkjermBredde() / 3.5);
                returOutput.setPrefWidth(getSkjermBredde() / 3.5);
                returOutput.setMinHeight(getSkjermHoyde() / 2);
                returOutput.setPrefHeight(getSkjermHoyde() / 2);
                returOutput.setMaxHeight(getSkjermHoyde() / 2);
                returOutput.setWrapText(true);
                returOutput.setEditable(false);
                returOutput.setBackground(Background.EMPTY) ;
                returOutput.selectRange(20, 20);
        return returOutput;
    }// end of method output()
    
    private void settUtvidet(TitledPane layout){
        // sokFodselsNrLayout, sokNavnLayout, sokForsikringstypeLayout, sokSkadeNrLayout, sokSkadetypeLayout
        if( ! ( layout.equals(sokFodselsNrLayout) ) && sokFodselsNrLayout.isExpanded() ){
            sokFodselsNrLayout.setExpanded(false);
        }
        else if( ! ( layout.equals(sokNavnLayout) ) && sokNavnLayout.isExpanded() ){
            sokNavnLayout.setExpanded(false);
        }
        else if( ! ( layout.equals(sokForsikringstypeLayout) ) && sokForsikringstypeLayout.isExpanded() ){
            sokForsikringstypeLayout.setExpanded(false);
        }
        else if( ! ( layout.equals(sokSkadeNrLayout) ) && sokSkadeNrLayout.isExpanded() ){
            sokSkadeNrLayout.setExpanded(false);
        }
        else if( ! ( layout.equals(sokSkadetypeLayout) ) && sokSkadetypeLayout.isExpanded() ){
            sokSkadetypeLayout.setExpanded(false);
        }
        layout.setExpanded(layout.isExpanded());
    }
}// end of class SokLayout
