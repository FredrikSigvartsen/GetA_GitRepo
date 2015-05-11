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
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
    private TitledPane sokFodselsNrLayout, sokNavnLayout, sokForsikringstypeLayout, sokSkadeNrLayout, sokSkadetypeLayout
            , sokForsikringLayout, sokForsikringerLayout;
    private TextArea output;
    private TextField fodselsNrInput, fornavnInput, etternavnInput, skadeNrInput, avtaleNrInput;
    private Button sokKnapp, sokNavnKnapp, sokForsikringstypeKnapp, sokSkadeNrKnapp, sokSkadetypeKnapp, nesteBildeKnapp, forrigeBildeKnapp
            , visForsikringerKnapp, visSkademeldingerKnapp, sokForsikringerKnapp, sokForsikringKnapp;
    private ChoiceBox forsikringstypeInput, skadetypeInput, forsikringertypeInput, velgKundeBox;
    private GridPane sokLayout, outputLayout, bildeviserLayout;
    private ImageView imageviewer;
    private Skademelding gjeldendeSkademelding;
    private List<Image> skadeBilder;
    private HBox knappedisplay, sokFodselsNrKnappeDisplay, sokNavnKnappeDisplay ;
    private Label ingenbilder, antallBilder;
    private int indeks;
    private ForsikringsKunde kunden;
    
    
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
        imageviewer = imageViewer();
        knappedisplay = visBildeKnappeDisplay();
        
        ingenbilder = new Label("Ingen bilder lagt til i denne skademeldingen.") ;
        ingenbilder.setFont( font(22));
        bildeviserLayout.setMargin( ingenbilder, new Insets(40, 0 , 0, 0)  );
        
        returLayout.addRow( 1, ingenbilder  );
        returLayout.addRow(2, imageviewer);
        returLayout.addRow(3, knappedisplay);
        returLayout.setVgap(15);
        returLayout.setHgap(15);
        return returLayout;
    } // end of method bildeviserLayout()
    /**
     * 
     * @return Et Layout for diverse søk-metoder. 
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
            settSynligVisKnapper(false);
        });
        
        sokSkadeNrLayout = new TitledPane( "Skademelding nummer", sokSkadeNr() );
        sokSkadeNrLayout.setExpanded(false);
        sokSkadeNrLayout.setOnMouseClicked((MouseEvent t) -> {
            settUtvidet(sokSkadeNrLayout);
            settSynligVisKnapper(false);
        });
        
        sokSkadetypeLayout = new TitledPane( "Skadetype", sokSkadetype() );
        sokSkadetypeLayout.setExpanded(false);
        sokSkadetypeLayout.setOnMouseClicked((MouseEvent t) -> {
            settUtvidet(sokSkadetypeLayout);
            settSynligVisKnapper(false);
        });
        
        sokForsikringLayout = new TitledPane("Avtalenummer", sokForsikringAvtaleNr() );
        sokForsikringLayout.setExpanded(false);
        sokForsikringLayout.setOnMouseClicked((MouseEvent t) -> {
            settUtvidet(sokForsikringLayout);
            settSynligVisKnapper(false);
        });
        
        sokForsikringerLayout = new TitledPane("Forsikringstype", sokForsikringerForsikringstype() );
        sokForsikringerLayout.setExpanded(false);
        sokForsikringerLayout.setOnMouseClicked((MouseEvent t) -> {
            settUtvidet(sokForsikringerLayout);
            settSynligVisKnapper(false);
        });
        
        Label skadeLabel = new Label("Søk på skademeldinger med");
        skadeLabel.setFont( font(22.5) );
        skadeLabel.setUnderline(true);
        Label kundeLabel = new Label("Søk på kunder med");
        kundeLabel.setFont( font(22.5) );
        kundeLabel.setUnderline(true);
        Label forsikringsLabel = new Label("Søk på forsikringer med");
        forsikringsLabel.setFont( font(22.5) );
        forsikringsLabel.setUnderline(true);
        
        returLayout.addRow(1,  kundeLabel );
        returLayout.setMargin( kundeLabel, new Insets(4, 0, 10, 0));
        returLayout.addRow(2,  sokFodselsNrLayout );
        returLayout.addRow(3,  sokNavnLayout );
        returLayout.addRow(4,  sokForsikringstypeLayout );
        returLayout.addRow(5,  skadeLabel );
        returLayout.setMargin( skadeLabel, new Insets(4, 0, 10, 0));
        returLayout.addRow(6,  sokSkadeNrLayout );
        returLayout.addRow(7,  sokSkadetypeLayout );
        returLayout.addRow(8,  forsikringsLabel );
        returLayout.setMargin( forsikringsLabel, new Insets(4, 0, 10, 0));
        returLayout.addRow( 9, sokForsikringLayout);
        returLayout.addRow( 10, sokForsikringerLayout);
        
        returLayout.setMinHeight(GUI.getSkjermHoyde());
        returLayout.setMaxHeight(GUI.getSkjermHoyde());
        returLayout.setPrefHeight(GUI.getSkjermHoyde());
        returLayout.setVgap(10);
        returLayout.setHgap(20);
        return returLayout;
    }// end of method returLayout()
    
    /**
     * Setter innhold og størrelse på layout for søk på forsikring
     * @return Et GridPane-layout med inputs og knapp for søking etter forsikringer av gitt type. 
     */
    private GridPane sokForsikringerForsikringstype(){
        GridPane returLayout = new GridPane();
        
        forsikringertypeInput = new ChoiceBox();
        forsikringertypeInput.getItems().addAll("Bolig", "Båt", "Bil", "Reise");
        sokForsikringerKnapp = new Button("Søk");
        sokForsikringerKnapp.setOnAction((ActionEvent e) -> {
            finnForsikringerMedForsikringstype();
            bildeviserLayout.setVisible(false);
        });
        
        returLayout.addColumn( 1, new Label("Velg forsikringstype: "));
        returLayout.addColumn( 2, forsikringertypeInput);
        returLayout.addColumn( 3, sokForsikringerKnapp);
        returLayout.setHgap(15);
        return returLayout;
    }// end of method

    /**
     * Kontrollerer og validerer brukerens input, og søker deretter i kunderegisteret etter hvilke typer forsikringer som ble valgt.
     */
    private void finnForsikringerMedForsikringstype(){
        output.setText("");
        if(forsikringertypeInput.getValue() == null){
            GUI.visInputFeilMelding("OBS!","Velg hvilken type forsikringer du vil søke på");
            return;
        }
        try{
            String forsikringstype = forsikringertypeInput.getValue().toString();
        
            List<Forsikring> forsikringerAvType = kundeRegister.finnForsikringer(forsikringstype);
        
            if(forsikringerAvType.isEmpty()){
               output.setText("\n Finnes ingen " + forsikringstype.toLowerCase() + "forsikringer i vårt system." );
               return;
            }
            ListIterator<Forsikring> iter = forsikringerAvType.listIterator();
            output.setText("\n Alle " + forsikringstype.toLowerCase() + "forsikringer:");
            while(iter.hasNext()){
                Forsikring gjeldendeForsikring = iter.next();
                output.appendText( gjeldendeForsikring.toString() );
                
                ForsikringsKunde forsikringsEier = kundeRegister.finnKundeMedAvtaleNr( gjeldendeForsikring.getAvtaleNr());
                if(forsikringsEier != null)
                    output.appendText( "\n Registrert på: " + forsikringsEier.getFornavn() + " " + forsikringsEier.getEtternavn()
                                      +"\n Fødselsnummeret til kunden: " + forsikringsEier.getFodselsNr());
            }// end of while
        }// end of try// end of try
        catch(NullPointerException npe){
            GUI.visProgramFeilMelding(npe);
        }
    }// end of method finnForsikringerMedForsikringstype()
    
    /**
     * Setter innhold og størrelse på layout for søk på forsikring
     * @return Et GridPane-layout med inputs og knapp for søking etter forsikring. 
     */
    private GridPane sokForsikringAvtaleNr(){
        GridPane returLayout = new GridPane();
        avtaleNrInput = new TextField();
        avtaleNrInput.setMaxWidth(100);
        
        sokForsikringKnapp = new Button("Søk");
        sokForsikringKnapp.setOnAction( (ActionEvent e) -> {
            finnForsikring();
            bildeviserLayout.setVisible(false);
        });
        returLayout.addColumn( 1, new Label("Avtalenummeret til forsikringen du vil søke på: "));
        returLayout.addColumn( 2, avtaleNrInput);
        returLayout.addColumn( 3, sokForsikringKnapp);
        returLayout.setVgap(10);
        returLayout.setHgap(10);
        return returLayout;
    }// end of method sokForsikringerAvtaleNr()
    
    /**
     * Et layout hvor brukeren kan søke opp kunder med fødselsnummer.
     * @return Et GridPane layout med kundesøk. 
     */
    private GridPane sokFodselsNr(){
        GridPane returSokFodselsNrLayout = new GridPane();
        fodselsNrInput = new TextField();
        fodselsNrInput.setMaxWidth(150);
        sokKnapp = new Button("Søk");
        sokKnapp.setOnAction((ActionEvent e) -> {
            if( finnKundeMedFodselsNr() ){
                bildeviserLayout.setVisible(false);
                settSynligVisKnapper(true);
            }
        });
        //Knappe displayet for visning av forsikringer og skademeldinger til kunden
        sokFodselsNrKnappeDisplay = sokKundeKnapper();
        
        returSokFodselsNrLayout.add( new Label("Fødselsnummeret til kunden:"), 1 , 1);
        returSokFodselsNrLayout.add( fodselsNrInput, 1 , 2);
        returSokFodselsNrLayout.add( sokKnapp, 1 , 3);
        returSokFodselsNrLayout.add( sokFodselsNrKnappeDisplay, 2 , 3 );
        
        returSokFodselsNrLayout.setHgap(10);
        returSokFodselsNrLayout.setVgap(10);
        return returSokFodselsNrLayout;
    } // end of sokFodselsNr()
    
    /**
     * Et layout hvor brukeren kan søke opp kunder med navn. 
     * @return Et GridPane layout med kundesøk med navn. 
     */
    private GridPane sokNavn(){
        GridPane navnLayout = new GridPane();
        
        fornavnInput = new TextField();
        etternavnInput = new TextField();
        etternavnInput.setMaxWidth(250);
        sokNavnKnapp = new Button("Søk");
        sokNavnKnapp.setOnAction((ActionEvent e) -> {
            if( finnKundeMedNavn() ){
                bildeviserLayout.setVisible(false);
                settSynligVisKnapper(true);
            }
            else
                settSynligVisKnapper(false);
        });
        //Knappe displayet for visning av forsikringer og skademeldinger til kunden
        sokNavnKnappeDisplay = sokKundeKnapper();
        navnLayout.add( new Label("Fornavn:"), 1 , 1);
        navnLayout.add( fornavnInput, 1, 2);
        navnLayout.add( new Label("Etternavn:"), 2, 1);
        navnLayout.add( etternavnInput, 2, 2);
        navnLayout.add( sokNavnKnapp, 1, 3);
        navnLayout.add( sokNavnKnappeDisplay, 2, 3);
        
        navnLayout.setHgap(10);
        navnLayout.setVgap(10);
        return navnLayout;
    } // end of sokNavn()
    
    /**
     * Fikser størrelse, innhold i et GridPane-layout for søk på kunder med en gitt forsikringstype
     * @return en GridPane-Layout som inneholder for riktig type informasjon og inputfelter for å søke på kunder med gitt forsikringstype. 
     */
    private GridPane sokForsikringstype(){
        GridPane forsikringLayout = new GridPane();
        
        forsikringstypeInput = new ChoiceBox();
        forsikringstypeInput.getItems().addAll("Bolig", "Båt", "Bil", "Reise");
        sokForsikringstypeKnapp = new Button("Søk");
        sokForsikringstypeKnapp.setOnAction((ActionEvent e) -> {
            finnKunderMedForsikringstype();
            bildeviserLayout.setVisible(false);
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
     * Returnerer et lite GridPane-layout med input og knaper for søk på skadetype
     * @return et liten GridPane-layout 
     */
    private GridPane sokSkadetype(){
        GridPane skadetypeLayout = new GridPane();
        
        skadetypeInput = new ChoiceBox();
        skadetypeInput.getItems().addAll("Bolig", "Båt", "Bil", "Reise");
        sokSkadetypeKnapp = new Button("Søk");
        sokSkadetypeKnapp.setOnAction((ActionEvent e) -> {
            finnSkademeldingerMedSkadeType();
            bildeviserLayout.setVisible(false);
            
        });
        
        skadetypeLayout.add( new Label("Velg skadetype:"), 1, 1);
        skadetypeLayout.add( skadetypeInput, 1, 2);
        skadetypeLayout.add( sokSkadetypeKnapp, 2, 2);
        skadetypeLayout.setHgap(10);
        skadetypeLayout.setVgap(10);
        return skadetypeLayout;
    }// end of method sokSkadetype()
    
    /**
     * Setter innhold og størrelse på et lite knappedisplay for vising av en valgt kundes skademeldinger og forsikringer
     * @return En horisontal boks med to knapper "vis forsikringer" og "vis skademeldinger" og metoder knyttet til disse.
     */
    private HBox sokKundeKnapper(){
        HBox returKnapper = new HBox(20);
        returKnapper.setVisible(false);
        visForsikringerKnapp = new Button("Vis forsikringer");
        visForsikringerKnapp.setOnAction((ActionEvent e) -> {
            visKundensForsikringer();
        });
        visSkademeldingerKnapp = new Button("Vis skademeldinger");
        visSkademeldingerKnapp.setOnAction((ActionEvent e) -> {
            visKundensSkademeldinger();
        });
        
        returKnapper.getChildren().addAll( visForsikringerKnapp, visSkademeldingerKnapp);
        return returKnapper;
    }// end of method sokKundeKnapper()
    
    /**
     * Bildedisplay for navigering mellom bilder
     * @return Et horisontalt display med knapper for navigering mellom bilder, og en label som viser hvor man er i bildelista.
     */
    private HBox visBildeKnappeDisplay(){
        HBox returKnapper = new HBox();
        antallBilder = new Label();
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
        returKnapper.getChildren().addAll(forrigeBildeKnapp, antallBilder, nesteBildeKnapp);
        returKnapper.setSpacing(100);
        returKnapper.setAlignment(Pos.CENTER);
        
        return returKnapper;
    } // end of method visBildeKnappeDisplay()
    
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
    
    /**
     * Legger til kunden som er funnet, sine skademeldinger i output-vinduet
     */
    private void visKundensSkademeldinger(){
        if( kunden == null)
            return;
        output.setText("");
        SkademeldingsListe kundensSkademeldinger = kunden.getSkademeldinger();
        output.setText(kunden.toString());
        if( kundensSkademeldinger.erTom())
            output.appendText("\n\n " +  kunden.getFornavn() + " " + kunden.getEtternavn() + " har ingen registrerte skademeldinger.");
        else
            output.appendText("\n\n " + kunden.getFornavn() + " " + kunden.getEtternavn() + " har følgende skademeldinger:\n" + kundensSkademeldinger.toString() );
    }// end of method visKundensSkademeldinger()
    
    /**
     * Legger til kundens forsikringer i output-vinduet
     */
    private void visKundensForsikringer(){
        if( kunden == null)
            return;
        output.setText("");
        Forsikringsliste kundensForsikringer = kunden.getForsikringer();
        output.setText(kunden.toString());
        if( kundensForsikringer.erTom())
            output.appendText("\n\n " + kunden.getFornavn() + " " + kunden.getEtternavn() + " har ingen forsikringer.");
        else
            output.appendText("\n\n " + kunden.getFornavn() + " " + kunden.getEtternavn() + " har følgende forsikringer:\n" + kundensForsikringer.toString());
    }// end of method visKundensForsikringer()
    
    /**
      * Finner en kunde med fødselsnummer. Skriver ut tilbakemelding i outputvinduet avhengig av om kunden finnes.
      */
     private boolean finnKundeMedFodselsNr(){
        output.setText("");
        if( fodselsNrInput.getText().trim().isEmpty()){
          GUI.visInputFeilMelding(" OBS!", "Du må fylle inn fødselsnummeret til kunden du vil søke opp.");
          return false;
        }//end of if
        
        String fodselsNr = fodselsNrInput.getText().trim();
        if( !( GUI.sjekkRegexFodselsNr(fodselsNr) ) ){
            GUI.visInputFeilMelding(" OBS!", " For å finne en kunde basert på fødselsnummer, må du fylle inn et gyldig fødselsnummer."
                                                             + " Et gyldig fødselsnummer består av 11 sifre. Hvorav de 6 første er fødselsdato.");
            return false;
        }// end of if
        
        try{
            kunden = kundeRegister.finnKunde( fodselsNr );
            if(kunden == null){
               output.setText("\n Kunden med dette fødselsnummer finnes ikke i vårt system.");
               return false;
            }
            output.setText("\n " + kunden.getFornavn() + " " + kunden.getEtternavn() + " funnet i systemet:" + kunden.toString());
            return true;
        }
        catch(NullPointerException npe){
            GUI.visProgramFeilMelding(npe);
            return false;
        }// end of try-catch
    }// end of method finnKundeMedFodselsNr()
     
     /**
      * Finner en kunde med fornavn og etternavn. Skriver ut tilbakemelding i outputvinduet avhengig av om kunden finnes.
      */
    private boolean finnKundeMedNavn(){
        output.setText("");
        if( fornavnInput.getText().trim().isEmpty() && etternavnInput.getText().trim().isEmpty()){
            GUI.visInputFeilMelding("OBS!", "Du må fylle inn fornavnet eller etternavn på kunden du vil søke på.");
            return false;
        }// end of if
        
        String fornavn = fornavnInput.getText().trim();
        String etternavn = etternavnInput.getText().trim();
        
        if( !( GUI.sjekkRegex(GUI.NAVN_REGEX, fornavn) || !( GUI.sjekkRegex(GUI.NAVN_REGEX, etternavn)) ) ){
            GUI.visInputFeilMelding("OBS!", "For å kunne søke på en kunde med fornavn og etternavn, må du fylle inn et gyldig navn."
                                          + " Et gyldig navn består av bokstaver.");
            return false;
        }// end of if
        
        try{
            //Hvis fornavn-felt er tomt skal man søke på etternavn
            if( fornavn.isEmpty() )
                output.setText( visValgAvKunder("Etternavn") );
            
            //Hvis etternavn-felt er tomt skal man søke på fornavn
            else if( etternavn.isEmpty() )
                output.setText( visValgAvKunder("Fornavn") );
            
            //Hvis begge er fylt, så skal man søke på både fornavn og etternavn
            else if( !( fornavn.isEmpty() ) && !( etternavn.isEmpty() ) )
                output.setText( visValgAvKunder("FornavnEtternavn") );
            //Hvis det ikke finnes noen kunde, er kunden null. 
            if( kunden == null )
                return false;
            return true;
        }// end of try
        catch(NullPointerException npe){
            GUI.visProgramFeilMelding(npe);
            return false;
        }// end of try-catch
    }// end of method finnKundeMedNavn()
    
    /**
     * Hvis det finnes flere brukere med samme fornavn/etternavn blir
     * @param fornavnANDORetternavn 
     */
    private String visValgAvKunder(String fornavnANDORetternavn){
        
        List<ForsikringsKunde> kundene;
        GridPane velgKundeLayout = null;
        
        //Hvis brukeren bare har skrevet inn fornavn
        if( fornavnANDORetternavn.equalsIgnoreCase("Fornavn")){
            
            String fornavn = fornavnInput.getText().trim();
            kundene = kundeRegister.finnKunderMedFornavn( fornavn );
            
            if( kundene.isEmpty() ){
                kunden = null;
                return "\n Det finnes ingen kunder i vårt system som heter " + fornavn + " til  fornavn";
            }
            
            if(kundene.size() == 1){
                kunden = kundene.get(0);
                fornavnInput.setText( kunden.getFornavn());
                etternavnInput.setText( kunden.getEtternavn() );
                return "\n " + kunden.getFornavn() + " " + kunden.getEtternavn() + " funnet i systemet:" + kunden.toString();
            }// end of inner if
            velgKundeLayout = kundeValgLayout( "Det finnes flere som heter " + fornavn + " til fornavn" , kundene);
        }// end of outter if
        
        //Hvis brukeren bare har skrevet inn etternavn
        else if( fornavnANDORetternavn.equalsIgnoreCase("Etternavn")){
            String etternavn = etternavnInput.getText().trim();
            kundene = kundeRegister.finnKunderMedEtternavn( etternavn );
            
            if( kundene.isEmpty() ){
                kunden = null;
                return "\n Det finnes ingen kunder i vårt system som heter " + etternavn + " til  etternavn";
            }
            
            if(kundene.size() == 1){
                kunden = kundene.get(0);
                fornavnInput.setText( kunden.getFornavn());
                etternavnInput.setText( kunden.getEtternavn() );
                return "\n " + kunden.getFornavn() + " " + kunden.getEtternavn() + " funnet i systemet:" + kunden.toString();
            }// end of inner if
            velgKundeLayout =  kundeValgLayout( "Det finnes flere som heter " + etternavn + " til etternavn", kundene );
        }// end of outter if
        
        // Hvis brukeren har skrevet inn både fornavn og etternavn
        else if( fornavnANDORetternavn.equalsIgnoreCase("FornavnEtternavn")){
            String fornavn = fornavnInput.getText().trim();
            String etternavn = etternavnInput.getText().trim();
            kundene = kundeRegister.finnKunderMedNavn(fornavn, etternavn);
            
            if(kundene.isEmpty()){
                kunden = null;
                return "\n Det finnes ingen kunder i vårt system som heter " + fornavn + " " + etternavn;
            }
            if(kundene.size() == 1){
                kunden = kundene.get(0);
                fornavnInput.setText( kunden.getFornavn());
                etternavnInput.setText( kunden.getEtternavn() );
                return "\n " + kunden.getFornavn() + " " + kunden.getEtternavn() + " funnet i systemet:" + kunden.toString();
            }// end of inner if
            velgKundeLayout = kundeValgLayout( "Det finnes flere som heter  " + fornavn + " " + etternavn, kundene);
        }// end of outter if
        
        Alert melding = new Alert(Alert.AlertType.CONFIRMATION);
        melding.setTitle("OBS!");
        melding.setHeaderText(null);
        melding.setResizable(true);
        melding.setWidth( 400 );
        
        GridPane.setVgrow(velgKundeLayout, Priority.ALWAYS);
        melding.getDialogPane().setContent( velgKundeLayout );
        melding.getDialogPane().setPrefSize(500, 600);
        Optional<ButtonType> handling = melding.showAndWait();
        
        if( handling.isPresent() && handling.get() == ButtonType.OK ){
            kunden = kundeRegister.finnKunde( velgKundeBox.getValue().toString() );
            if( kunden != null){
            fornavnInput.setText( kunden.getFornavn());
            etternavnInput.setText( kunden.getEtternavn() );
            return "\n " + kunden.getFornavn() + " " + kunden.getEtternavn() + " funnet i systemet:" + kunden.toString();
            }
        }// end of outter if
        else{
            return "\n Du avbrøt handlingen. Søk gjerne på nytt for å finne  kunden du leter etter.";
        }// end of else
        return " Feil i søking etter kunde. Kontakt IT-ansvarlig";
    }// end of method visValgAvKunder()
    
    /**
     * 
     * @return Et GridPane-layout for utvalg av en spesiell kunde, blant mange. 
     */
    private GridPane kundeValgLayout(String fornavnANDORetternavn, List<ForsikringsKunde> kunder){
        GridPane returLayout = new GridPane();
        if(kunder.isEmpty())
            return returLayout;
        
        Label beskrivelse = new Label( fornavnANDORetternavn + ".\nVelg ut fødselsnummeret til kunden du vil søke på:");
        Label kundeInfo = new Label();
        
        velgKundeBox = new ChoiceBox();
        Iterator<ForsikringsKunde> kIter = kunder.listIterator();
        while( kIter.hasNext() ){
            ForsikringsKunde gjeldende = kIter.next();
            velgKundeBox.getItems().add( gjeldende.getFodselsNr());
        }
        velgKundeBox.getSelectionModel().selectedItemProperty().addListener( new ChangeListener<String>(){
            
            public void changed( ObservableValue<? extends String> source, String oldValue, String newValue){
                kundeInfo.setText( "Kunde valgt:" + kundeRegister.finnKunde(newValue).toString() );
            }
        });
        returLayout.addRow(1, beskrivelse);
        returLayout.addRow(2, velgKundeBox);
        returLayout.addRow(3, kundeInfo);
        returLayout.setVgap(15);
        returLayout.setMaxWidth( Double.MAX_VALUE );
        returLayout.setMaxHeight( Double.MAX_VALUE );
        return returLayout;
    }// end of method kundeValgLayout() 
    
    /**
     * Finner alle kunder med forsikring av typen brukeren velger. 
     */
    private void finnKunderMedForsikringstype(){
        output.setText("");
        if(forsikringstypeInput.getValue() == null){
            visMelding("Velg forsikringstype.","Velg hvilken type forsikringer du vil søke på");
            return;
        }
        try{
            String forsikringstype = forsikringstypeInput.getValue().toString();
        
            List<ForsikringsKunde> kunderMedGittForsikringstype = kundeRegister.finnKunder(forsikringstype);
        
            if(kunderMedGittForsikringstype.isEmpty()){
               output.setText("\n Finnes ingen forsikringer av typen " + forsikringstype.toLowerCase() );
               return;
            }
            ListIterator<ForsikringsKunde> iter = kunderMedGittForsikringstype.listIterator();
            output.setText("\n Følgende kunder har " + forsikringstype.toLowerCase() + "forsikringer:");
            while(iter.hasNext()){
                ForsikringsKunde gjeldendeKunde = iter.next();
                output.appendText( gjeldendeKunde.toString() + "\n Har forsikring av type: " + forsikringstype.toLowerCase() );
            }// end of while
        }// end of try// end of try
        catch(NullPointerException npe){
            GUI.visProgramFeilMelding(npe);
        }
    }// end of method finnKunderMedForsikringstype()
    
    /**
     * Kontrollerer inputs, og finner forsikringen basert på hvilket avtalenummer som er tastet inn
     * @return En boolsk verdi som tilsier om dette gikk eller ikke. 
     */
    private void finnForsikring(){
        if( avtaleNrInput.getText().trim().isEmpty()){
            GUI.visInputFeilMelding("Fyll inn avtalenummer", " For å kunne søke på en forsikring må du fylle inn avtalenummeret"
                                                              + " på forsikringen du vil søke opp.");
            return;
        }//end of if
        if( ! ( GUI.sjekkRegex("^\\d+$", avtaleNrInput.getText().trim()) ) ){
            GUI.visInputFeilMelding("OBS! Tast inn gyldig avtalenummer", " For å kunne søke opp en forsikring må du fylle inn et gyldig avtalenummer"
                                                                       + ".\n Et avtalenummer består av bare tall");
            return;
        }// end of if
        
        try{
            int avtaleNr = Integer.parseInt( avtaleNrInput.getText().trim() );
            Forsikring forsikringen = kundeRegister.finnForsikringer(avtaleNr);
            
            if(forsikringen == null){
                output.setText( "\n Forsikring nr." + avtaleNr + " finnes ikke i vårt system.");
                return;
            }
            output.setText("\n Forsikring nr." + avtaleNr + " funnet:" + forsikringen.toString());
            ForsikringsKunde forsikringsEier = kundeRegister.finnKundeMedAvtaleNr(avtaleNr);
            if( forsikringsEier != null){
                output.appendText( "\n Er registrert på: " + forsikringsEier.getFornavn() + " " + forsikringsEier.getEtternavn() +
                                   "\n Kundens fødselsnummer: " + forsikringsEier.getFodselsNr());
            }// end of if
        }// end of try
        catch( NumberFormatException | NullPointerException e){
            GUI.visProgramFeilMelding(e);
        }// end of try-catch
    }// end of method finnForsikringer()
    
    /**
     * Finner skademelding med riktig type skadenummer, og skriver ut informasjon om denne i output-vinduet. Hvis skademeldingen har bilder, blir dette vist. 
     */
    private void finnSkadeMedSkadeNr(){
        output.setText("");
        if( skadeNrInput.getText().trim().isEmpty()){
            visMelding("Fyll inn skadenummer", "Skriv inn skadenummeret på den skaden du vil søke opp");
            return;
        }
        if( !( GUI.sjekkRegex("^\\d+$", skadeNrInput.getText().trim()) ) ){
            GUI.visInputFeilMelding(" OBS!", "For å kunne søke opp en skademelding, må du fylle inn et gyldig skademeldingsnummer på skaden du vil finne. "
                                            + "Et gyldig nummer består av bare tall.");
            return;
        }
        try{
            int skadeNr = Integer.parseInt(skadeNrInput.getText().trim());
            
            gjeldendeSkademelding = kundeRegister.finnSkademeldinger(skadeNr);
            
            //Hvis skademeldingen ikke finnes. 
            if( gjeldendeSkademelding == null){
                bildeviserLayout.setVisible(false);
                output.setText("\n Skademelding nr." + skadeNr + " finnes ikke i vårt system.");
                return;
            }
            bildeviserLayout.setVisible(true);
            
            //Hvis skademeldingen ikke har noen bilder. 
            if( gjeldendeSkademelding.getBilder().isEmpty()){
                ingenbilder.setVisible(true);
                knappedisplay.setVisible(false);
                imageviewer.setVisible(false);
            
            }else{
                skadeBilder = behandleBilder();
                ingenbilder.setVisible(false);
                knappedisplay.setVisible(true);
                imageviewer.setVisible(true);
            }
            ForsikringsKunde skademeldingEier =  kundeRegister.finnKunde(skadeNr);
            output.setText("\n Følgende skademelding funnet:" + gjeldendeSkademelding.toString() + "\n Skademeldingen er registrert på: " + 
                    skademeldingEier.getFornavn() + " " + skademeldingEier.getEtternavn() + "\n Fødselsnummer til kunden: " + skademeldingEier.getFodselsNr());
        }// end of try
        catch( NumberFormatException | NullPointerException nfe){
            GUI.visProgramFeilMelding(nfe);
            return;
        }// end of try-catch
    }// end of method finnSkadeMedSkadeNr()
    
    /**
     * Finner alle skademeldinger av type brukeren velger. 
     */
    private void finnSkademeldingerMedSkadeType(){
        output.setText("");
        if(skadetypeInput.getValue() == null){
            visMelding("Velg skadetype.", "Velg hvilken type skademeldinger du vil søke opp");
            return;
        }
        try{
            String skadetype = skadetypeInput.getValue().toString();
            
            List<Skademelding> skademeldinger = kundeRegister.finnSkademeldinger(skadetype);
            if(skademeldinger.isEmpty()){
                output.setText("\n Det finnes ingen skademeldinger av typen " + skadetype.toLowerCase() );
                return;
            }
            ListIterator<Skademelding> iter = skademeldinger.listIterator();
            output.setText("\n Følgende skademeldinger er av type " + skadetype.toLowerCase() + " :");
            while(iter.hasNext()){
                Skademelding iterSkademelding = iter.next();
                output.appendText( iterSkademelding.toString());
                //Legger til tekst om hvem skademeldingen er registrert på
                ForsikringsKunde skademeldingEier = kundeRegister.finnKunde( iterSkademelding.getSkadeNr());
                if(skademeldingEier != null){
                    output.appendText("\n Skademeldingen er registrert på: " + 
                    skademeldingEier.getFornavn() + " " + skademeldingEier.getEtternavn() + "\n Fødselsnummer til kunden: " + skademeldingEier.getFodselsNr());
                }
            }// end of while
        }// end of try// end of try
        catch(NullPointerException npe){
            GUI.visProgramFeilMelding(npe);
            return;
        }
    }// end of method finnSkademeldingerMedSkadeType()
    
    /**
     * Lager en iterator som starter der det nåværende bilde er i listen, og hvis det er flere bilder, setter vi neste bildet i imageviewer.
     */
    private void nesteBilde(){
        ListIterator<Image> iter = skadeBilder.listIterator(skadeBilder.indexOf(imageviewer.getImage() ) + 1 );
        if(iter.hasNext()){
            Image nesteBilde = iter.next();
            imageviewer.setImage( nesteBilde );
            antallBilder.setText( ++indeks +"/" + skadeBilder.size() );
        }
    }// end of method nesteBilde()
    
    /**
     * Lager en iterator som starter der det nåværende bilde er i listen, og hvis lista ikke starter på første plass i lista, setter vi forrige bildet i imageviewer.
     */
    private void forrigeBilde(){
        ListIterator<Image> iter = skadeBilder.listIterator(  skadeBilder.indexOf(imageviewer.getImage() )  );
        if(iter.hasPrevious()){
            Image forrigeBilde = iter.previous();
            imageviewer.setImage( forrigeBilde );
            antallBilder.setText( --indeks +"/" + skadeBilder.size() );
        }
    }// end of method forrigeBilde()
    
    /**
     * Behandler bilder fra en liste av filer til en liste av bilder. Returnerer Denne listen.
     * @return En liste av bilder lagt til i skademeldingen.
     */
    private List<Image> behandleBilder(){
        List<File> filBilder = gjeldendeSkademelding.getBilder();
        List<Image> returBilder = new ArrayList<>();
        indeks = 1;
        
        
        ListIterator<File> iter = filBilder.listIterator();
        while(iter.hasNext()){
            try {
                Image gjeldendeBilde = new Image (iter.next().toURI().toURL().toString() );
                returBilder.add(gjeldendeBilde);
            } catch (MalformedURLException ex) {
                GUI.visProgramFeilMelding(ex);
            }// end of try-catch
        }// end of while
        imageviewer.setImage( returBilder.get(0) );
        antallBilder.setText(indeks +"/" + returBilder.size() );
        return returBilder;
    }// end of method behandleBilder()
        
    /**
     * 
     * @return Et imageview med passende størrelse. 
     */
    private ImageView imageViewer(){
        ImageView returViser = new ImageView();
        
        returViser.setSmooth(true);
        returViser.setFitWidth(GUI.getSkjermBredde() / 3.5);
        returViser.setFitHeight(GUI.getSkjermHoyde() / 3);
        
        return returViser;
    }// end of method imageViewer()
    
    /**
     * Returnerer et fikset output-vindu
     * @return Et tekstområde med fikset størrelse
     */
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
                returOutput.setFont( font(20));
                
        return returOutput;
    }// end of method output()
    
    /**
     * Behandler oppførselen på TitledPane-layoutene med søkfunksjoner.
     * @param layout Den layouten man vil sette utvidet, de andre lukkes. 
     */
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
        else if( ! ( layout.equals(sokForsikringLayout) ) && sokForsikringLayout.isExpanded() ){
            sokForsikringLayout.setExpanded(false);
        }
        else if( ! ( layout.equals(sokForsikringerLayout) ) && sokForsikringerLayout.isExpanded() ){
            sokForsikringerLayout.setExpanded(false);
        }
        layout.setExpanded(layout.isExpanded());
    }// end of method settUtvidet()
    
    /**
     * Metoden har til hensikt å skjule knappene "Vis forsikringer" og "Vis skademeldingerer"
     * @param skjulte En parameter som tilsier om knappene skal være skjult eller ikke.
     */
    private void settSynligVisKnapper(boolean skjulte){
        sokNavnKnappeDisplay.setVisible(skjulte);
        sokFodselsNrKnappeDisplay.setVisible(skjulte);
    }// end of method skjulKnapper
}// end of class SokLayout
