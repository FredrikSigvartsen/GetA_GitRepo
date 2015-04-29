/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt.kundebehandling;

import Brukergrensesnitt.*;
import static Brukergrensesnitt.GUI.getSkjermBredde;
import static Brukergrensesnitt.GUI.getSkjermHoyde;
import forsikringsprogram.*;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.beans.value.ObservableValue;
import javafx.geometry.*;
import javafx.geometry.Insets;

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
        super();
        kundeRegister = register;
        kundebehandlingsFaner();
        opprettLayout();
        //this.kundeRegister = register;
    }//end of constructor
    
    /**
     * Oppretter og setter de ulike layoutene under Kundebehandling
     */
    public void opprettLayout(){
        kundebehandlingsMeny = kundebehandlingsFaner();
        registrerSkademeldingLayout = new RegistrerSkadeLayout(kundeRegister);
        registrerSkademeldingLayout.autosize();
//        kundeRegistreringsPane = new RegistrerKundeLayout(kundeRegister);
        kundeRegistreringsPane.autosize();
//        forsikringsPane = new TegnforsikringsLayout(kundeRegister);
        forsikringsPane.autosize();
//        sioppPane = new SioppforsikringsLayout(kundeRegister);
        sioppPane.autosize();
        sokPane = new KundesokLayout(kundeRegister);
        sokPane.autosize();
        
        
        //Setter plassering
        setTop(kundebehandlingsMeny);
        setCenter(kundeRegistreringsPane);
        setMargin(kundeRegistreringsPane, new Insets(40));
    }//end of method opprettLayout()
    
    /**
     * Oppretter fanekortene for Kundebehandlings fanen
     * @return 
     */
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
    }//end of method kundebehandlingsFaner()
    
    /**
     * Legger til en litter på fanekortene i kundebehandlingsPanelet
     */
    public void tabLytter(){
        kundebehandlingsPanel.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Tab> ov, Tab t, Tab t1) -> {
        switch (t1.getText()) {
            case "Kunderegistrering":
                setCenter(kundeRegistreringsPane);
                setMargin(kundeRegistreringsPane, new Insets(40));
                break;
            case "Tegn forsikring":
                setCenter(forsikringsPane);
                setMargin(forsikringsPane, new Insets(40));
                break;
            case "Si opp forsikring":
                setCenter(sioppPane);
                setMargin(sioppPane, new Insets(40));
                break;
            case "Søk":   
                setCenter(sokPane);
                setMargin(sokPane, new Insets(40));
                break;
            case "Registrer skademelding":
                setCenter(registrerSkademeldingLayout);
                setMargin(registrerSkademeldingLayout, new Insets(40));
                break;
            default:
                setCenter(kundeRegistreringsPane);
                setMargin(kundeRegistreringsPane, new Insets(40));
            }
        });
    }//end of method tabLytter()
        
    
}//end of class KundePane
