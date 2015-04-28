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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    
    public static final String TIDSPUNKT_REGEX = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$";
    public static final String NAVN_REGEX = "^[A-ZÆØÅ][a-zA-Z æøåÆØÅ]*$";
    public static final String DATO_REGEX = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$";
    
    private Stage stage;
    private Scene scene;
    private static Dimension opplosning = Toolkit.getDefaultToolkit().getScreenSize();
    private HBox faneMeny;
    private TabPane fanePanel;
    private KundePane kundeLayout;
    private Kunderegister kundeRegister;
    
    /**
     * Skriver til fil.
     */
    public void skrivTilFil() {
        
        try(ObjectOutputStream utfil = new ObjectOutputStream(
                new FileOutputStream("liste.dta"))){
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
     * Leser fra fil
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
    
    /**
     * Viser en feilmelding forårsaket av en exception.
     * @param e er det unntaktet som har oppstått.
     */
    public static void visProgramFeilMelding( Exception e){
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
  
    /**
     * En metode som sjekker om teksten stemmer overens med regexen man sender med. 
     * @param regex Sender med et regulært uttrykk 
     * @param sjekk Teksten som skal valideres for det regulære uttrykket.
     * @return En boolsk verdi som tilsier om teksten stemmer overens med regex'en. 
     */
    public static boolean sjekkRegex(String regex, String sjekk){
        Pattern regExPattern = Pattern.compile(regex);
        Matcher regMatch = regExPattern.matcher(sjekk);
        
        return regMatch.matches();
    }// end of method sjekkRegex
    
    public Kunderegister getKundeRegister(){
        return kundeRegister;
    }
    
    /**
     * 
     * @param fodselNr Sender med det fødselsnummeret programmereren vil validere. 
     * @return En boolsk verdi som tilsier om fødselsnummeret er validert eller ikke. 
     */
    public static boolean sjekkRegexFodselsNr(String fodselNr) {

        if(fodselNr == null || fodselNr.trim().equals("")) 
          return false;
        return sjekkFodselsNr(fodselNr);
    }// end of method sjekkRegexFodselsNr(String fodselsDato)
    
    /**
     * 
     * @param fodselsNr Sjekker om fødselsnummer stemmer overens med norske fødselsnummer. 
     * @return En boolsk verdi som tilsier om fødselsnummeret kan være et norsk fødselsnummer. 
     */
    private static boolean sjekkFodselsNr(String fodselsNr) {
      try {
         // dag
        int dag1 = Integer.parseInt(new String("" + fodselsNr.charAt(0)));
        int dag2 = Integer.parseInt(new String("" + fodselsNr.charAt(1)));
        // Måned
        int m1 = Integer.parseInt(new String("" + fodselsNr.charAt(2)));
        int m2 = Integer.parseInt(new String("" + fodselsNr.charAt(3)));
        // År
        int y1 = Integer.parseInt(new String("" + fodselsNr.charAt(4)));
        int y2 = Integer.parseInt(("" + fodselsNr.charAt(5)));
        // Individ sifre
        int i1 = Integer.parseInt(new String("" + fodselsNr.charAt(6)));
        int i2 = Integer.parseInt(new String("" + fodselsNr.charAt(7)));
        int i3 = Integer.parseInt(new String("" + fodselsNr.charAt(8)));
        int bk1 = Integer.parseInt(new String("" + fodselsNr.charAt(9)));
        int bk2 = Integer.parseInt(new String("" + fodselsNr.charAt(10)));
        int rest1, k1, rest2, k2;
 
        rest1 = ((3 * dag1 + 7 * dag2 + 6 * m1 + 1 * m2 + 8 * y1 + 9 * y2 + 4
                 * i1 + 5 * i2 + 2 * i3) % 11);
        if (rest1 == 0) {
            k1 = 0;
        } else {
            k1 = 11 - rest1;
        }
        rest2 = ((5 * dag1 + 4 * dag2 + 3 * m1 + 2 * m2 + 7 * y1 + 6 * y2 + 5
             * i1 + 4 * i2 + 3 * i3 + 2 * k1) % 11);
  
        if (rest2 == 0) {
           k2 = 0;
        } else {
           k2 = 11 - rest2;
        }
        if (k1 == bk1 && k2 == bk2) {
            return true;
        } else {
            return false;
        }
      }// end of try// end of try
      catch (Exception nfe) {
          visProgramFeilMelding(nfe);
          return false;
      }// end of catch
    }// end of method sjekkFodselsNr(String birthNumber)

    public static void main(String[] args) {
        // TODO code application logic here
        Application.launch(args);
    }// end of main
}// end of class GUI
