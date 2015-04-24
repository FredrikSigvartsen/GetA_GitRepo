/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt;

import Brukergrensesnitt.skademelding.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import static javafx.scene.layout.BorderStroke.*;
import static javafx.scene.layout.BorderStrokeStyle.SOLID;
import static javafx.scene.paint.Color.DARKGRAY;

/**
 *
 * @author Jens
 */
public class KundePane extends BorderPane{
    
    private HBox kundebehandlingsMeny;
    private TabPane kundebehandlingsPanel;
     public static final Border KANTLINJE = new Border( new BorderStroke(DARKGRAY,SOLID, new CornerRadii(5), THIN, new Insets(15)) );
     public static final Insets PADDING = new Insets(10);
    
    //Forskjellgie typer for kundebehandling
    private RegistrerKundeLayout kundeRegistreringsPane;
    private TegnforsikringsPane forsikringsPane;
    private SioppforsikringsPane sioppPane;
    private KundesokPane sokPane;
    private RegistrerSkadeLayout registrerSkademeldingLayout;
        
    public KundePane() {
        kundebehandlingsFaner();
        opprettLayout();
    }
    public void opprettLayout(){
        kundebehandlingsMeny = kundebehandlingsFaner();
        registrerSkademeldingLayout = new RegistrerSkadeLayout();
        kundeRegistreringsPane = new RegistrerKundeLayout();
        forsikringsPane = new TegnforsikringsPane();
        sioppPane = new SioppforsikringsPane();
        sokPane = new KundesokPane();
        
        //Setter plassering
        setTop(kundebehandlingsMeny);
        setCenter(kundeRegistreringsPane);
    }
    public HBox kundebehandlingsFaner(){
        HBox box = new HBox();
        kundebehandlingsPanel = new TabPane();
        kundebehandlingsPanel.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        kundebehandlingsPanel.setMinWidth(GUI.getSkjermBredde()*2);
        
        
        Tab registrerFane = new Tab("Kunderegistrering");
        kundebehandlingsPanel.getTabs().add(registrerFane);
        
        Tab forsikringsFane = new Tab("Tegn forsikring");
        kundebehandlingsPanel.getTabs().add(forsikringsFane);
        
        Tab sioppForsikringsFane = new Tab("Si opp forsikring");
        kundebehandlingsPanel.getTabs().add(sioppForsikringsFane);
        
        Tab registrerSkademeldingFane = new Tab("Registrer skademelding");
        kundebehandlingsPanel.getTabs().add(registrerSkademeldingFane);
        
        Tab sokFane = new Tab("Søk");
        kundebehandlingsPanel.getTabs().add(sokFane);
        //kundebehandlingsPanel.setRotate(90);
        
        box.getChildren().add(kundebehandlingsPanel);
        
        return box;
    }
    
    public void tabLytter(){
        kundebehandlingsPanel.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Tab> ov2, Tab t2, Tab t3) -> {
        switch (t3.getText()) {
            case "Kunderegistrering":
                setCenter(kundeRegistreringsPane);
                break;
            case "Tegn forsikring":
                setCenter(forsikringsPane);
                break;
            case "Si opp forsikring":
                setCenter(sioppPane);
                break;
            case "Søk":   
                setCenter(sokPane);
                break;
            case "Registrer skademelding":
                setCenter(registrerSkademeldingLayout);
                break;
            default:
                setCenter(kundeRegistreringsPane);
            }
        });
    }
    
    
    
    
    /*public void registrerKunde(){
        String fornavn = this.fornavn.getText();
        String etternavn = this.etternavn.getText();
        String adresse = this.adresse.getText();
        String poststed = this.poststed.getText();
        String postnr = this.postnr.getText();
        String fodselsnr = this.fodselsnr.getText();
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
                System.out.println("Fyll inn fodselsnr");
            System.out.println("Venligst fyll inn alle feltene");
        }
        else{
            kunde = new ForsikringsKunde(fornavn, etternavn, adresse, poststed, postnr, fodselsnr);
            kundeRegister.registrerKunde(kunde);
            System.out.println(kunde.toString());
            //outputArea.setText(kunde.toString());
            //outputBox.getChildren().removeAll(outputArea);
            //outputBox.getChildren().addAll(outputArea);
            //fjernTekstIFelter();
        }
    }*/
    
    /*public VBox getBox(){
        return kundeBox;
    }
    
    /*public TabPane getFaner(){
        return kundebehandlingsPanel;
    }*/
    
    /*public Button getRegistrer(){
        return registrerKunde;
    }*/
}
