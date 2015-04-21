/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt;
import forsikringsprogram.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Iterator;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Stage;
/**
 *
 * @author Jens
 */
public class GUI extends Application{  
    private Dimension opplosning = Toolkit.getDefaultToolkit().getScreenSize();
    private BorderPane layout;
    private VBox kundeRegistreringBox;
    private VBox skadeRegistreringBox;
    private VBox forsikringRegistreringBox;
    private VBox siOppForsikringBox;
    private VBox sokBox;
    private VBox center;
    private HBox faneMeny;
    private HBox kundebehandlingsMeny;
    private HBox outputBox;
    private TabPane fanePanel;
    private TabPane kundebehandlingsPanel;
    private ComboBox forsikringsType;
    private ComboBox forsikringsTypeSok;
    private ComboBox skadeType;
    private TextField fornavn;
    private TextField etternavn;
    private TextField adresse;
    private TextField postnr;
    private TextField poststed;
    private TextField fodselsnrSkade;
    private TextField fodselsnrKunde;
    private TextField fodselsnrForsikring;
    private TextField avtalenr;
    private TextField skadeBeskrivelse;
    private TextField sted;
    private TextField utbetalingsBelop;
    private TextField forsikringspremie;
    private TextField forsikringsbelop;
    private TextField betingelser;
    private TextField registreringsnr;
    private TextField merke;
    private TextField modell;
    private TextField registreringsar;
    private TextField kjorelengde;
    private TextField prisPerKm;
    private TextArea outputArea;
    private Kunderegister kundeRegister;
    private Bilforsikring bilForsikring;
    private Button tegnForsikring;
    private Button registrerKunde;
    private Button registrerSkade;
    
    public GUI(){
        layout = new BorderPane();
        faneMeny();
        kundeMeny();
        kundeRegistrering();
        tegnForsikring();
        siOppForsikring();
        kundeSok();
        skadeRegistrering();
        kundeRegister = new Kunderegister();
        center = new VBox();
        outputBox = new HBox();
        outputArea = TextAreaBuilder.create()
                    .prefWidth(getSkjermBredde())
                    .prefHeight(getSkjermHoyde() / 3)
                    .wrapText(true)
                    .editable(false)
                    .build();
    }
    
    private void faneMeny(){

        faneMeny = new HBox();
        fanePanel = new TabPane();
        fanePanel.setMinWidth(getSkjermBredde());
        fanePanel.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        
        Tab kundebehandlingFane = new Tab();
        kundebehandlingFane.setText("Kundebehandling");
        fanePanel.getTabs().add(kundebehandlingFane);

        Tab skademeldingFane = new Tab();
        skademeldingFane.setText("Skademelding");
        fanePanel.getTabs().add(skademeldingFane);

        Tab okonomiFane = new Tab();
        okonomiFane.setText("Økonomi");
        fanePanel.getTabs().add(okonomiFane);

        Tab statistikkFane = new Tab();
        statistikkFane.setText("Statistikk");
        fanePanel.getTabs().add(statistikkFane);
        faneMeny.getChildren().add(fanePanel);
    }
    
    private void kundeMeny(){
        kundebehandlingsMeny = new HBox();
        
        kundebehandlingsPanel = new TabPane();
        kundebehandlingsPanel.setMinWidth(getSkjermBredde());
        kundebehandlingsPanel.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        Tab registrerFane = new Tab();
        registrerFane.setText("Kunderegistrering");
        kundebehandlingsPanel.getTabs().add(registrerFane);
        
        Tab forsikringsFane = new Tab();
        forsikringsFane.setText("Tegn forsikring");
        kundebehandlingsPanel.getTabs().add(forsikringsFane);
        
        Tab sioppForsikringsFane = new Tab();
        sioppForsikringsFane.setText("Si opp forsikring");
        kundebehandlingsPanel.getTabs().add(sioppForsikringsFane);
        
        Tab sokFane = new Tab();
        sokFane.setText("Søk");
        kundebehandlingsPanel.getTabs().add(sokFane);
        
        kundebehandlingsMeny.getChildren().add(kundebehandlingsPanel);
    }
    
    private void kundeRegistrering(){                   
        kundeRegistreringBox = VBoxBuilder.create()
                .alignment(Pos.CENTER_LEFT)
                .spacing(5.0)
                .build();
        
        Label fornavnLabel;
        Label etternavnLabel;
        Label adresseLabel;
        Label postnrLabel;
        Label poststedLabel;
        Label telefonnrLabel;
        Label fodselsnrLabel;
        
        registrerKunde = new Button("Registrer");
        
        fornavnLabel = new Label("Fornavn:");
        fornavn = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        etternavnLabel = new Label("Etternavn:");
        etternavn = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        adresseLabel = new Label("Adresse:");
        adresse = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        postnrLabel = new Label("Postnummer:");
        postnr = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        poststedLabel = new Label("Poststed:");
        poststed = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        fodselsnrLabel = new Label("Fødselsnummer:");
        fodselsnrKunde = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        kundeRegistreringBox.getChildren().addAll(fornavnLabel, fornavn, etternavnLabel,
                etternavn, adresseLabel, adresse, postnrLabel, postnr, poststedLabel, 
                poststed, fodselsnrLabel, fodselsnrKunde, registrerKunde);
    }
    
     private void tegnForsikring(){                   
        forsikringRegistreringBox = VBoxBuilder.create()
                .alignment(Pos.CENTER_LEFT)
                .spacing(5.0)
                .build();
        
        forsikringsType = new ComboBox();
          //Sjekk ut om dette skal være med
        
        //ANDRE FELTER SOM KOMMER FREM BASERT PÅ VALG I DROPDOWN!!!!
        //Bilforsikring
        
        
        Label registreringsnrLabel;
        Label merkeLabel;
        Label modellLabel;
        Label registreringsarLabel;
        Label kjorelengdeLabel;
        Label prisPerKmLabel;
        
        registreringsnrLabel = new Label("Registreringsnummer:");
        registreringsnr = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        merkeLabel = new Label("Merke:");
        merke = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
                
        modellLabel = new Label("Modell:");
        modell = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
                
        registreringsarLabel = new Label("Registrerings år:");
        registreringsar = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
                
        kjorelengdeLabel = new Label("Kjørelengde:");
        kjorelengde = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
                
        prisPerKmLabel = new Label("Pris per KM:");
        prisPerKm = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        //Slutt Bilforsikring
        
        //Båtforsikring
        //TextField registreringsnr;
        //TextField merke;
        //TextField modell;
        TextField arsmodell;
        TextField motorType;
        TextField motorStyrke;
        
        //Label registreringsnrLabel;
        //Label merkeLabel;
        //Label modellLabel;
        Label arsmodellLabel;
        Label motorTypeLabel;
        Label motorStyrkeLabel;
        //Slutt Båtforsikring
        
        //Boligforsikring
        TextField gateAdresse;
        TextField postnr;
        TextField byggear;
        TextField boligType;
        TextField byggemateriale;
        TextField standard;
        TextField antallKVM;
        
        Label gateAdresseLabel;
        Label postnrLabel;
        Label byggearLabel;
        Label boligTypeLabel;
        Label byggematerialeLabel;
        Label antallKVMLabel;
        //Slutt Boligforsikring
        
        //Reiseforsikring
        TextField omrade;
        
        Label omradeLabel;
        //Slutt Reiseforsikring
        
        Label fodselsnrLabel;
        Label forsikringstypeLabel;
        Label forsikringspremieLabel;
        Label forsikringsbelopLabel;
        Label betingelserLabel;
        
        tegnForsikring = new Button("Tegn forsikring");
        
        fodselsnrLabel = new Label("Fødselsnummer:");
        fodselsnrForsikring = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        forsikringstypeLabel = new Label("Forsikrings type:");
        ObservableList<String> forsikringer = FXCollections.observableArrayList(
                                              "Bilforsikring", "Båtforsikring",
                                              "Boligforsikring", "Reiseforsikring");
        forsikringsType.setItems(forsikringer);
        
        forsikringspremieLabel = new Label("Forsikringspremie:");
        forsikringspremie = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        forsikringsbelopLabel = new Label("Forsikringsbeløp:");
        forsikringsbelop = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        betingelserLabel = new Label("Betingelser:");
        betingelser = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        
        forsikringRegistreringBox.getChildren().addAll(fodselsnrLabel, fodselsnrForsikring, forsikringstypeLabel, forsikringsType, forsikringspremieLabel,
                forsikringspremie, forsikringsbelopLabel, forsikringsbelop, betingelserLabel, betingelser, registreringsnrLabel, 
                registreringsnr, merkeLabel, merke, modellLabel, modell, registreringsarLabel, registreringsar, kjorelengdeLabel, kjorelengde, 
                prisPerKmLabel, prisPerKm, tegnForsikring);
    }
     
    private void siOppForsikring(){                   
        siOppForsikringBox = VBoxBuilder.create()
                .alignment(Pos.CENTER_LEFT)
                .spacing(5.0)
                .build();
        
        TextField fodselsnr;
        TextField avtalenr;
        
        Label fodselsnrLabel;
        Label avtalenrLabel;
        
        Button siOppForsikring = new Button("Si opp forsikring");
        
        fodselsnrLabel = new Label("Fødselsnummer:");
        fodselsnr = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        avtalenrLabel = new Label("Avtalenr:");
        avtalenr = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        
        siOppForsikringBox.getChildren().addAll(fodselsnrLabel, fodselsnr, avtalenrLabel,
                avtalenr, siOppForsikring);
    }
    
     private void kundeSok(){                   
        sokBox = VBoxBuilder.create()
                .alignment(Pos.CENTER_LEFT)
                .spacing(5.0)
                .build();
        
        TextField fodsels_avtalenr;
        
        Label fodsels_avtalenrLabel;
        Label forsikringstypeSokLabel;
        
        fodsels_avtalenrLabel = new Label("Fødsels/avtalenummer:");
        fodsels_avtalenr = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        forsikringstypeSokLabel = new Label("Forsikrings type:");
        forsikringsTypeSok = new ComboBox();
        ObservableList<String> forsikringerTilSok = FXCollections.observableArrayList(
                                              "Bilforsikring", "Båtforsikring",
                                              "Boligforsikring", "Reiseforsikring");
        forsikringsTypeSok.setItems(forsikringerTilSok);
        
        
        sokBox.getChildren().addAll(fodsels_avtalenrLabel, fodsels_avtalenr, forsikringstypeSokLabel, forsikringsTypeSok);
    }
    
    private void skadeRegistrering(){                   
        skadeRegistreringBox = VBoxBuilder.create()
                .alignment(Pos.CENTER_LEFT)
                .spacing(5.0)
                .build();
        
        Label avtalenrLabel;
        Label fodselsnrLabel;
        Label skadeTypeLabel;
        Label skadeBeskrivelseLabel;
        Label datoLabel;
        Label stedLabel;
        Label utbetalingsBelopLabel;
        
        registrerSkade = new Button("Registrer og utbetal");
        
        avtalenrLabel = new Label("Avtalenummer:");
        avtalenr = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        fodselsnrLabel = new Label("Fødselsnummer:");
        fodselsnrSkade = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        skadeTypeLabel = new Label("Skadetype:");
        skadeType = new ComboBox();
        ObservableList<String> skader = FXCollections.observableArrayList(
                                              "Bilskade", "Båtskade",
                                              "Boligskade", "Reiseskade");
        skadeType.setItems(skader);
        
        skadeBeskrivelseLabel = new Label("Skadebeskrivelse:");
        skadeBeskrivelse = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        datoLabel = new Label("Dato:");
        
        stedLabel = new Label("Sted:");
        sted = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        utbetalingsBelopLabel = new Label("Utbetalingsbeløp:");
        utbetalingsBelop = TextFieldBuilder.create()
                   .minWidth(100)
                   .maxWidth(100)
                   .build();
        
        skadeRegistreringBox.getChildren().addAll(avtalenrLabel, avtalenr, fodselsnrLabel,
                fodselsnrSkade, skadeTypeLabel, skadeType, skadeBeskrivelseLabel, skadeBeskrivelse, stedLabel, sted, utbetalingsBelopLabel, 
                utbetalingsBelop, registrerSkade);
    }
    
    private double getSkjermBredde(){
        return (double)opplosning.getWidth() / 2;
    }
    
    private double getSkjermHoyde(){
        return (double)opplosning.getHeight() / 1.3;
    }
    
    private void registrerKunde(){
        String fornavn = this.fornavn.getText();
        String etternavn = this.etternavn.getText();
        String adresse = this.adresse.getText();
        String poststed = this.poststed.getText();
        String postnr = this.postnr.getText();
        String fodselsnr = this.fodselsnrKunde.getText();
        if(fornavn.trim().equals("") || etternavn.trim().equals("") || adresse.trim().equals("") || poststed.trim().equals("") || postnr.trim().equals("") || fodselsnr.trim().equals("")){
            /*if(fornavn.trim().equals(""))
                System.out.println("Fyll inn fornavn");
            else if(etternavn.trim().equals(""))
                System.out.println("Fyll inn etternavn");
            else if(adresse.trim().equals(""))
                System.out.println("Fyll inn adresse");
            else if(postnr.trim().equals(""))
                System.out.println("Fyll inn postnr");
            else if(poststed.trim().equals(""))
                System.out.println("Fyll inn poststed");
            else if(fodselsnr.trim().equals(""))
                System.out.println("Fyll inn fodselsnr");*/
            System.out.println("Venligst fyll inn alle feltene");
        }
        else{
            ForsikringsKunde kunde = new ForsikringsKunde(fornavn, etternavn, adresse, poststed, postnr, fodselsnr);
            kundeRegister.registrerKunde(kunde);
            //System.out.println(kunde.toString());
            outputArea.setText(kunde.toString());
            outputBox.getChildren().removeAll(outputArea);
            outputBox.getChildren().addAll(outputArea);
            //fjernTekstIFelter();
        }
    }
    
    private void registrerForsikring(){
        String fodselsnr = this.fodselsnrForsikring.getText();
        String forsikringsType = (String) this.forsikringsType.getValue();
        int forsikringspremie = Integer.parseInt(this.forsikringspremie.getText());
        int forsikringsbelop = Integer.parseInt(this.forsikringsbelop.getText());
        String betingelser = this.betingelser.getText();
        String registreringsnr = this.registreringsnr.getText();
        String merke = this.merke.getText();
        String modell = this.modell.getText();
        int registreringsar = Integer.parseInt(this.registreringsar.getText());
        int kjorelengde = Integer.parseInt(this.kjorelengde.getText());
        int prisPerKm = Integer.parseInt(this.prisPerKm.getText());
        
        if(fodselsnr.trim().equals("") || forsikringsType.trim().equals("") || forsikringspremie == 0 
                || forsikringsbelop == 0 || betingelser.trim().equals("") || registreringsnr.trim().equals("")
                || merke.trim().equals("") || modell.trim().equals("") || registreringsar == 0
                || kjorelengde == 0 || prisPerKm == 0){
            System.out.println("Venligst fyll inn allefeltene");
        }
        else{
            bilForsikring = new Bilforsikring(betingelser, forsikringspremie, forsikringsbelop,
                         registreringsnr, merke, modell, registreringsar, kjorelengde, prisPerKm);
            kundeRegister.tegnForsikring(bilForsikring, fodselsnr);
            outputArea.setText(bilForsikring.toString());
            outputBox.getChildren().removeAll(outputArea);
            outputBox.getChildren().addAll(outputArea);
        }
    }
    
    private void registrerSkadeMelding(){
        String avtalenr = this.avtalenr.getText();
        String fodselsnr = this.fodselsnrSkade.getText();
        //skadestype
        String skadeBeskrivelse = this.skadeBeskrivelse.getText();
        //dato
        String sted = this.sted.getText();
        int utbetalingsBelop = Integer.parseInt(this.utbetalingsBelop.getText());
        if(avtalenr.trim().equals("") || fodselsnr.trim().equals("") || skadeBeskrivelse.trim().equals("") || sted.trim().equals("") || utbetalingsBelop == 0){
            System.out.println("Venligst fyll inn alle feltene");
        }
        else{
            Skademelding skade = new Skademelding(null, skadeBeskrivelse, null, null, 0, utbetalingsBelop, null, null );
            System.out.println(skade.toString());
            /*outputArea = TextAreaBuilder.create()
                    .prefWidth(getSkjermBredde())
                    .prefHeight(getSkjermHoyde() / 3)
                    .wrapText(true)
                    .editable(false)
                    .build();*/
            outputArea.setText(skade.toString());
            outputBox.getChildren().removeAll(outputArea);
            outputBox.getChildren().addAll(outputArea);
        }
    }
    
    /*private void fjernTekstIFelter(){
        layout.getChildren().stream().filter((node) -> (node instanceof TextField)).forEach((node) -> {
            ((TextField)node).setText("");
        });
    }*/
    //Override av Application sin start metode slik at vi kan vise de ønskede komponentene på skjermen
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Forsikringsprogram");
                
        
        
        //ForsikringsKunde kunde = new ForsikringsKunde("Jens", "Omfjord", "Pilestredet Park 21", "Oslo", "1336", "27129346367");
        
        
        /*HBox outputBox = new HBox();
        TextArea output;
        output = TextAreaBuilder.create()
                .prefWidth(getSkjermBredde())
                .prefHeight(getSkjermHoyde() / 3)
                .wrapText(true)
                .editable(false)
                .build();
        //output.setText(kunde.toString());
        outputBox.getChildren().addAll(output);*/
        
        
        center.getChildren().addAll(kundebehandlingsMeny, kundeRegistreringBox);
        
       
        
        layout.setTop(faneMeny);
        layout.setCenter(center);
        layout.setBottom(outputBox);
        
        
        Scene scene = new Scene(layout, getSkjermBredde(), getSkjermHoyde());      
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //kaller metodene som skal i center
        
        
        fanePanel.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Tab> ov, Tab t, Tab t1) -> {
            switch (t1.getText()) {
                case "Kundebehandling":
                    System.out.println("Bytte til Kundebehandling");
                    center.getChildren().removeAll(kundebehandlingsMeny, kundeRegistreringBox, forsikringRegistreringBox, siOppForsikringBox, sokBox, skadeRegistreringBox);
                    center.getChildren().addAll(kundebehandlingsMeny, kundeRegistreringBox);
                    break;
                case "Skademelding":
                    System.out.println("Bytte til Skademelsing");
                    center.getChildren().removeAll(kundebehandlingsMeny, kundeRegistreringBox, forsikringRegistreringBox, siOppForsikringBox, sokBox, skadeRegistreringBox);
                    center.getChildren().addAll(skadeRegistreringBox);
                    break;
                case "Økonomi":
                    System.out.println("Bytte til Økonomi");
                    center.getChildren().removeAll(kundebehandlingsMeny, kundeRegistreringBox, forsikringRegistreringBox, siOppForsikringBox, sokBox, skadeRegistreringBox);
                    break;
                case "Statistikk":
                    System.out.println("Bytte til Statistikk");
                    center.getChildren().removeAll(kundebehandlingsMeny, kundeRegistreringBox, forsikringRegistreringBox, siOppForsikringBox, sokBox, skadeRegistreringBox);
                    break;
            }
        });
        kundebehandlingsPanel.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Tab> ov, Tab t, Tab t1) -> {
            switch (t1.getText()) {
                case "Kunderegistrering":
                    System.out.println("Bytte til Kunderegistrering");
                    center.getChildren().removeAll(kundebehandlingsMeny, kundeRegistreringBox, forsikringRegistreringBox, siOppForsikringBox, sokBox);
                    center.getChildren().addAll(kundebehandlingsMeny, kundeRegistreringBox);
                    break;
                case "Tegn forsikring":
                    System.out.println("Bytte til Tegn forsikring");
                    center.getChildren().removeAll(kundebehandlingsMeny, kundeRegistreringBox, forsikringRegistreringBox, siOppForsikringBox, sokBox);
                    center.getChildren().addAll(kundebehandlingsMeny, forsikringRegistreringBox);
                    break;
                case "Si opp forsikring":
                    System.out.println("Bytte til Si opp forsikring");
                    center.getChildren().removeAll(kundebehandlingsMeny, kundeRegistreringBox, forsikringRegistreringBox, siOppForsikringBox, sokBox);
                    center.getChildren().addAll(kundebehandlingsMeny, siOppForsikringBox);
                    break;
                case "Søk":
                    System.out.println("Bytte til Søk");    
                    center.getChildren().removeAll(kundebehandlingsMeny, kundeRegistreringBox, forsikringRegistreringBox, siOppForsikringBox, sokBox);
                    center.getChildren().addAll(kundebehandlingsMeny, sokBox);
                    break;
            }
        });
        registrerKunde.setOnAction((ActionEvent event) -> {
            registrerKunde();
        });
        registrerSkade.setOnAction((ActionEvent event) -> {
            registrerSkadeMelding();
        });
        tegnForsikring.setOnAction((ActionEvent event) -> {
            registrerForsikring();
        });
        /*forsikringsType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ComboBox>() {
            @Override
            public void changed(ObservableValue<? extends ComboBox> ov, ComboBox t, ComboBox t1) {
                System.out.println("Woop!");
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });*/
    }
  
    
    public static void main(String[] args) {
        // TODO code application logic here
        Application.launch(args);
    }
} //end of class GUI