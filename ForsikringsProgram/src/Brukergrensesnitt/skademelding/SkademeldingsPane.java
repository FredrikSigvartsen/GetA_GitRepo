/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt.skademelding;


import Brukergrensesnitt.GUI;
import javafx.beans.value.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import static javafx.scene.layout.BorderStrokeStyle.SOLID;
import static javafx.scene.paint.Color.DARKGRAY;

/**
 *
 * @author fredr_000
 */
public class SkademeldingsPane extends BorderPane {
    
    public static final Border KANTLINJE = new Border( new BorderStroke(DARKGRAY,SOLID, new CornerRadii(5), new BorderWidths(2), new Insets(15)) );
    private RegistrerSkadeLayout registrerLayout;
    private HBox skademeldingNav;
   
    
    public SkademeldingsPane(){
        registrerLayout = new RegistrerSkadeLayout();
        skademeldingNav = kundebehandlingsFaner();
        setTop(skademeldingNav); //Navigasjonsbar
        setCenter(registrerLayout); // Registrering av kunde-layout
        
    }// end of constructor
    
        public HBox kundebehandlingsFaner(){
        HBox skademeldingMeny = new HBox();
        TabPane skademeldingPanel = new TabPane();
        skademeldingPanel.setMinWidth(GUI.getSkjermBredde() * 2);
        skademeldingPanel.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        
        
        Tab registrerFane = new Tab();
        registrerFane.setText("Registrering av skademelding");
        skademeldingPanel.getTabs().add(registrerFane);
        
        Tab forsikringsFane = new Tab();
        forsikringsFane.setText("Søk på skademelding");
        skademeldingPanel.getTabs().add(forsikringsFane);
        
        skademeldingMeny.getChildren().add(skademeldingPanel);
        
        return skademeldingMeny;
    }// end of method kundebehandlingsFaner()
        
//        public void tabLytter(){
//        kundebehandlingsPanel.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Tab> ov2, Tab t2, Tab t3) -> {
//        switch (t3.getText()) {
//            case "Kunderegistrering":
//                setCenter(registreringsPane);
//                break;
//            case "Tegn forsikring":
//                setCenter(forsikringsPane);
//                break;
//            case "Si opp forsikring":
//                setCenter(sioppPane);
//                break;
//            case "Søk":   
//                setCenter(sokPane);
//                break;
//            }
//        });
//    }// end of method tabLytter()


}// end of class SkademeldingsPane
