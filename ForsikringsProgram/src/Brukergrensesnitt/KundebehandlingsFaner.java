/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;

/**
 *
 * @author Jens
 */
public class KundebehandlingsFaner {
    
    private HBox kundebehandlingsMeny;
    
    private TabPane kundebehandlingsPanel;
    
    public KundebehandlingsFaner(){
        kundebehandlingsMeny = new HBox();
        kundebehandlingsPanel = new TabPane();
        kundebehandlingsPanel.setMinWidth(GUI.getSkjermBredde());
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
    
    public TabPane getFaner(){
        return kundebehandlingsPanel;
    }
    
    public HBox getMeny(){
        return kundebehandlingsMeny;
    }
}
