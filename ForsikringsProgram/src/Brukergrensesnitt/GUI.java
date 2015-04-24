/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt;

import Brukergrensesnitt.skademelding.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.*;
import static javafx.scene.layout.BorderStroke.THIN;
import static javafx.scene.layout.BorderStrokeStyle.SOLID;
import static javafx.scene.paint.Color.DARKGRAY;
import javafx.stage.Stage;

/**
 *
 * @author Jens
 */
public class GUI extends Application{
    
    
    public static final Border KANTLINJE = new Border( new BorderStroke(DARKGRAY,SOLID, new CornerRadii(5), THIN, new Insets(15)) );
    public static final Insets PADDING = new Insets(10);
    
    private Stage stage;
    private Scene scene;
    private static Dimension opplosning = Toolkit.getDefaultToolkit().getScreenSize();
    private HBox faneMeny;
    private TabPane fanePanel;
    private KundePane kundeLayout;
    private KundebehandlingsFaner faner;
    private TegnforsikringsPane tegnForsikringsPane;
    
    
    public static double getSkjermBredde(){
        return (double)opplosning.getWidth() / 2;
    }
    
    public static double getSkjermHoyde(){
        return (double)opplosning.getHeight() / 1.3;
    }
    
    public void faneMeny(){
        faneMeny = new HBox();
        fanePanel = new TabPane();
        fanePanel.setMinWidth(GUI.getSkjermBredde() * 2);
        fanePanel.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        
        Tab kundebehandlingFane = new Tab();
        kundebehandlingFane.setText("Kundebehandling");
        fanePanel.getTabs().add(kundebehandlingFane);

        Tab okonomiFane = new Tab();
        okonomiFane.setText("Økonomi");
        fanePanel.getTabs().add(okonomiFane);

        Tab statistikkFane = new Tab();
        statistikkFane.setText("Statistikk");
        fanePanel.getTabs().add(statistikkFane);
        faneMeny.getChildren().add(fanePanel);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        kundeLayout = new KundePane();
        tegnForsikringsPane = new TegnforsikringsPane(); // Denne skal vel ikke være her Jens? 
        faneMeny();
        kundeLayout.tabLytter();
        tegnForsikringsPane.comboLytter();  // Ikke denne heller vel?
        //TabPane kundeFaner = kundeLayout.kundebehandlingsFaner();
        stage = primaryStage;
        stage.setTitle("Forsikringsprogram");
        BorderPane layout = new BorderPane();
        layout.setTop(faneMeny);
        layout.setCenter(kundeLayout);
        
        scene = new Scene(layout, getSkjermBredde(), getSkjermHoyde());
        
        stage.setScene(scene);
        stage.show();
        
        fanePanel.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Tab> ov, Tab t, Tab t1) -> {
            switch (t1.getText()) {
                case "Kundebehandling":
                    layout.setCenter(kundeLayout);
                    break;
                case "Økonomi":
                    break;
                case "Statistikk":
                    break;
            }
        });
        
        /*kundeLayout.kundebehandlingsFaner().getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Tab> ov2, Tab t2, Tab t3) -> {
            switch (t3.getText()) {
                case "Kunderegistrering":
                    System.out.println("Bytte til Kunderegistrering");
                    break;
                case "Tegn forsikring":
                    System.out.println("Bytte til Tegn forsikring");
                    break;
                case "Si opp forsikring":
                    System.out.println("Bytte til Si opp forsikring");
                    break;
                case "Søk":   
                    System.out.println("Bytte til Søk");
                    break;
                }
            });
        /*
        kundeSceneObjekt.getRegistrer().setOnAction((ActionEvent event) -> {
            kundeSceneObjekt.registrerKunde();
        });
        /*registrerSkade.setOnAction((ActionEvent event) -> {
            registrerSkadeMelding();
        });
        tegnForsikring.setOnAction((ActionEvent event) -> {
            registrerForsikring();
        });*/
    }
  
    
    public static void main(String[] args) {
        // TODO code application logic here
        Application.launch(args);
    }
}
