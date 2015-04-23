/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt;

import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import forsikringsprogram.*;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author Jens
 */
public class KundePane extends BorderPane{
    private Scene kundeScene;
    private HBox kundebehandlingsMeny;
    private TabPane kundebehandlingsPanel;
    private ForsikringsKunde kunde;
    private Kunderegister kundeRegister;
    private KundebehandlingsFaner kundeFaner;
    private KunderegistreringsPane registreringsPane;
    private TegnforsikringsPane forsikringsPane;
    private SioppforsikringsPane sioppPane;
    private KundesokPane sokPane;
        
    public KundePane() {
        kundebehandlingsFaner();
        registreringsPane = new KunderegistreringsPane();
        forsikringsPane = new TegnforsikringsPane();
        sioppPane = new SioppforsikringsPane();
        sokPane = new KundesokPane();
        setTop(kundebehandlingsMeny);
        setCenter(registreringsPane);
        //setCenter(kundebehandlingsP)
    }
    
    public void kundebehandlingsFaner(){
        kundebehandlingsMeny = new HBox();
        kundebehandlingsPanel = new TabPane();
        kundebehandlingsPanel.setMinWidth(GUI.getSkjermBredde() * 2);
        kundebehandlingsPanel.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
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
    
    public void tabLytter(){
        kundebehandlingsPanel.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Tab> ov2, Tab t2, Tab t3) -> {
        switch (t3.getText()) {
            case "Kunderegistrering":
                setCenter(registreringsPane);
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
