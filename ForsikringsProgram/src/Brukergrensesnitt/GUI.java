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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Alert;
import static javafx.scene.control.Alert.AlertType.ERROR;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import static javafx.scene.layout.BorderStroke.THIN;
import static javafx.scene.layout.BorderStrokeStyle.SOLID;
import static javafx.scene.paint.Color.DARKGRAY;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Jens
 */
public class GUI extends Application{
    
    
    public static final Border KANTLINJE = new Border( new BorderStroke(DARKGRAY,SOLID, new CornerRadii(5), THIN, new Insets(15)) );
    public static final Insets PADDING = new Insets(10);
    public static final String TIMER_REGEX = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
    
    private Stage stage;
    private Scene scene;
    private static Dimension opplosning = Toolkit.getDefaultToolkit().getScreenSize();
    private HBox faneMeny;
    private TabPane fanePanel;
    private KundePane kundeLayout;
    private TegnforsikringsLayout tegnForsikringsPane;
    private Kunderegister kundeRegister;
    
    /**
     * 
     */
    public void skrivTilFil() {
        
        try(ObjectOutputStream utfil = new ObjectOutputStream(
                new FileOutputStream("liste.dta")))
        {
            utfil.writeObject(kundeRegister);
            utfil.writeInt(Forsikring.getNesteNr());
            utfil.writeInt(Skademelding.getNesteNr());
        }
        catch(NotSerializableException nse) {
            visProgramFeilMelding(nse);
        }
        catch(IOException ioe) {
            visProgramFeilMelding(ioe);
        }
    }// end of method skrivTilFil()
    
    /**
     * 
     */
    public void lesFraFil() {
        try(ObjectInputStream innfil = new ObjectInputStream(
                new FileInputStream("liste.dta")))
        {
            kundeRegister = (Kunderegister) innfil.readObject();
            Forsikring.setNesteNr(innfil.readInt());
            Skademelding.setNesteNr(innfil.readInt());
        }
        catch(ClassNotFoundException cnfe) {
            kundeRegister = new Kunderegister();
            visProgramFeilMelding(cnfe);
        }
        catch(FileNotFoundException fnfe) {
            kundeRegister = new Kunderegister();
            visProgramFeilMelding(fnfe);
        }
        catch(IOException ioe) {
            kundeRegister = new Kunderegister();
            visProgramFeilMelding(ioe);
        }
    }// end of method lesFraFil()
    
    private void visProgramFeilMelding( Exception e){
        Alert varsel = new Alert( ERROR);
        varsel.setTitle("Programfeil");
        varsel.setHeaderText("Programfeil. Kontakt IT-Ansvarlig");
        varsel.setContentText( e.getLocalizedMessage());
        
        
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String exceptionTekst = sw.toString();
        
        TextArea tekstomraade = new TextArea(exceptionTekst);
        tekstomraade.setEditable(false);
        tekstomraade.setWrapText(true);
        tekstomraade.setMaxWidth( Double.MAX_VALUE);
        tekstomraade.setMaxHeight( Double.MAX_VALUE);
        GridPane.setVgrow( tekstomraade, Priority.ALWAYS);
        GridPane.setHgrow( tekstomraade, Priority.ALWAYS);
        
        GridPane expInnhold = new GridPane();
        expInnhold.setMaxWidth( Double.MAX_VALUE);
        expInnhold.add( new Label("Programfeilen skyldtes:"), 0, 0);
        expInnhold.add( tekstomraade, 0, 0);
        
        varsel.getDialogPane().setExpandableContent(expInnhold);
        
        varsel.showAndWait();
    }// end of method visFeilMelding()
    
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
    }// end of method faneMeny()
   
    @Override
    public void start(Stage primaryStage) throws Exception{
        lesFraFil();
        System.out.println( kundeRegister.finnKunde("15129400015" ).getForsikringer().toString() );
        kundeLayout = new KundePane( kundeRegister );
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
        stage.setOnCloseRequest((WindowEvent t) -> {
            skrivTilFil();
        } );
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
    }// end of method start()
  
    public Kunderegister getKundeRegister(){
        return kundeRegister;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Application.launch(args);
    }// end of main
}// end of class GUI
