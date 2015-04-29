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
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.beans.value.ObservableValue;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.RED;

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
    private TextArea output;
        
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
        output = output();
        registrerSkademeldingLayout = new RegistrerSkadeLayout(kundeRegister, output);
        kundeRegistreringsPane = new RegistrerKundeLayout(kundeRegister, output);
        forsikringsPane = new TegnforsikringsLayout(kundeRegister, output);
        sioppPane = new SioppforsikringsLayout(kundeRegister, output);
        sokPane = new KundesokLayout(kundeRegister, output);
        //kundeRegistreringsPane.setLayoutX(0.1);
        //kundeRegistreringsPane.setLayoutY(0.1);
        //kundeRegistreringsPane.add(output, 3, 1, 1, 7);
        //forsikringsPane.add(output, 3, 1, 1, 8);
        
        //registrerSkademeldingLayout.setConstraints(kundeRegistreringsPane, 2, 8, 1, 1);
        //RegistrerKundeLayout.setConstraints(kundeRegistreringsPane, 2, 8);
        //kundeRegistreringsPane.setColumnSpan(kundeRegistreringsPane, 1);
        //kundeRegistreringsPane.setMaxWidth(GUI.getSkjermBredde() / 2);
        //kundeRegistreringsPane.setMinWidth(GUI.getSkjermBredde() / 10);
        //kundeRegistreringsPane.setHalignment(kundeRegistreringsPane, HPos.LEFT);
        //forsikringsPane.setConstraints(kundeRegistreringsPane, 2, 8, 1, 1);
        //sioppPane.setConstraints(kundeRegistreringsPane, 2, 8, 1, 1);
        //sokPane.setConstraints(kundeRegistreringsPane, 2, 8, 1, 1);
        
        
        //Setter plassering
        setTop(kundebehandlingsMeny);
        //setMargin(kundeRegistreringsPane, new Insets(1, 1, 1, 1));
        //kundeRegistreringsPane.setMaxWidth(1000);
        //output.setMaxWidth(10);
        //kundeRegistreringsPane.setMaxWidth(250);
        setCenter(kundeRegistreringsPane);
        setAlignment(kundeRegistreringsPane, Pos.TOP_LEFT);
        //getLeft().maxWidth(1);
        
        //setMargin(output, new Insets(40));
        setRight(output);
        setAlignment(output, Pos.TOP_LEFT);
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
    }//end of method tabLytter()
        
    private TextArea output(){
        TextArea output = output = TextAreaBuilder.create()
                .minWidth(getSkjermBredde() / 7)
                .maxWidth(getSkjermBredde() / 7)
                .minHeight(getSkjermHoyde() / 4)
                .maxHeight(getSkjermHoyde() / 4)
                .wrapText(true)
                .editable(false)
                //.style("-fx-text-fill: red")
                //.style("-fx-background-color: red")
                .build();
        output.setBackground(new Background( new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        return output;
    }
}//end of class KundePane
