/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt;

import Brukergrensesnitt.kundebehandling.KundePane;
import Brukergrensesnitt.kundebehandling.TegnforsikringsLayout;
import forsikringsprogram.*;
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
    private TegnforsikringsLayout tegnForsikringsPane;
    private Kunderegister kundeRegister;
    
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
   
    public void start(Stage primaryStage) throws Exception{
        kundeRegister = new Kunderegister();
        kundeLayout = new KundePane( kundeRegister );
        //ForsikringsKunde kunde = new ForsikringsKunde("Fredrik", "Sigvartsen", "Tullin", "ff","ff","ff");
        //if(kundeRegister.registrerKunde( kunde) )
        //    System.out.println(kunde);
        faneMeny();
        kundeLayout.tabLytter();  // Ikke denne heller vel? JO!
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
        
        
        /*registrerSkade.setOnAction((ActionEvent event) -> {
            registrerSkadeMelding();
        });
        tegnForsikring.setOnAction((ActionEvent event) -> {
            registrerForsikring();
        });*/
    }
  
    public Kunderegister getKundeRegister(){
        return kundeRegister;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Application.launch(args);
    }
}
