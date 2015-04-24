/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt;

import Brukergrensesnitt.kundebehandling.RegistrerSkadeLayout;
import forsikringsprogram.*;
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
    
    //Forskjellgie typer for kundebehandling
    private RegistrerKundeLayout kundeRegistreringsPane;
    private TegnforsikringsLayout forsikringsPane;
    private SioppforsikringsLayout sioppPane;
    private KundesokLayout sokPane;
    private RegistrerSkadeLayout registrerSkademeldingLayout;
    private Kunderegister kundeRegister;
        
    public KundePane(Kunderegister register) {
        kundebehandlingsFaner();
        opprettLayout();
        this.kundeRegister = register;
    }
    public void opprettLayout(){
        kundebehandlingsMeny = kundebehandlingsFaner();
        registrerSkademeldingLayout = new RegistrerSkadeLayout(kundeRegister);
        kundeRegistreringsPane = new RegistrerKundeLayout(kundeRegister);
        forsikringsPane = new TegnforsikringsLayout(kundeRegister);
        sioppPane = new SioppforsikringsLayout(kundeRegister);
        sokPane = new KundesokLayout(kundeRegister);
        
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
    
    
    
    
    /*
    
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
