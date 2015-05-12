/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt;

import Brukergrensesnitt.Statistikk.StatistikkPane;
import Brukergrensesnitt.kundebehandling.*;
import Brukergrensesnitt.okonomi.OkonomiPane;
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
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
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
import javafx.scene.control.TextAreaBuilder;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import static javafx.scene.layout.BorderStroke.THIN;
import static javafx.scene.layout.BorderStrokeStyle.SOLID;
import static javafx.scene.paint.Color.DARKGRAY;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Jens
 */
public class GUI extends Application{
    
    public static final int TEKSTFELT_BREDDE = 150;
    public static final String TIDSPUNKT_REGEX = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$";
    public static final String NAVN_REGEX = "^[A-ZÆØÅ][a-zA-Z æøåÆØÅ.-]*$";
    public static final String DATO_REGEX = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$";
    public static final String VALUTA_REGEX = "^\\d+(\\.\\d{1,2})?$";     
    public static final String POSTNR_REGEX = "^\\d{4}$";
    public static final String ADRESSE_REGEX = "^[A-ZÆØÅ][a-zA-Z æøåÆØÅ0-9\\s]*$";
    public static final String FORSIKRINGSBELOP_REGEX = "^[0-9]{2,7}$";
    public static final String REGISTRERINGSNR_REGEX = "^[A-Z]{2}[0-9]{5}$";
    public static final String BATREGISTRERINGSNR_REGEX = "^[A-Z]{3}[0-9]{3}$";
    public static final String MODELL_REGEX = "^[a-zA-Z æøåÆØÅ0-9\\s-]*$";
    public static final String AVTALENR_REGEX = "^[0-9]{1,4}$";
    
    private static Stage stage;
    private Scene scene;
    private static Dimension opplosning = Toolkit.getDefaultToolkit().getScreenSize();
    private HBox faneMeny;
    private TabPane fanePanel;
    private KundePane kundeLayout;
    private OkonomiPane okonomiLayout;
    private Kunderegister kundeRegister;
    private BorderPane layout;
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        lesFraFil();
        kundeLayout = new KundePane( kundeRegister);
        okonomiLayout = new OkonomiPane(kundeRegister);
        stage = primaryStage;
        stage.setTitle("Forsikringsprogram - Behandle forsikring");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../bilder/logo.png")));
        layout = new BorderPane();
        
        scene = new Scene(layout, getSkjermBredde() / 1.1, getSkjermHoyde() / 1.2);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
        stage.setOnCloseRequest((WindowEvent t) -> {
            skrivTilFil();
        } );
        opprettFaneMeny();
        layout.setCenter(kundeLayout);
        layout.setTop(faneMeny);
        
        fanePanel.setTabMinWidth((sceneBredde() / 3) - 20);
        kundeLayout.getKundebehandlingsPanel().setTabMinWidth((sceneBredde() / 3) - 20);
        final InvalidationListener resizeLytter = (Observable observable) -> {
            fanePanel.setTabMinWidth((sceneBredde() / 3) - 20);
            kundeLayout.getKundebehandlingsPanel().setTabMinWidth((sceneBredde() / 3) - 20);
        };
        scene.widthProperty().addListener(resizeLytter);
    }// end of method start()
  
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
     * Oppretter navigasjonsmenyen på toppen av vinduet, og kobler opp lyttere til de forskjellige 'tab'sene. 
     */
    public void opprettFaneMeny(){
        faneMeny = new HBox();
        fanePanel = new TabPane();
        fanePanel.setTabMinHeight(30);
        fanePanel.setTabMaxHeight(30);
        fanePanel.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        
        Tab kundebehandlingFane = new Tab();
        Label kundebehandlingLabel = new Label("Kundebehandling");
        kundebehandlingLabel.setFont(Font.font(null, 18));
        kundebehandlingFane.setGraphic(kundebehandlingLabel);
        kundebehandlingFane.setId("kundebehandling");
        fanePanel.getTabs().add(kundebehandlingFane);

        Tab okonomiFane = new Tab();
        Label okonomiLabel = new Label("Økonomi");
        okonomiLabel.setFont(Font.font(null, 18));
        okonomiFane.setGraphic(okonomiLabel);
        okonomiFane.setId("okonomi");
        fanePanel.getTabs().add(okonomiFane);

        Tab statistikkFane = new Tab();
        Label statistikkLabel = new Label("Statistikk");
        statistikkLabel.setFont(Font.font(null, 18));
        statistikkFane.setGraphic(statistikkLabel);
        statistikkFane.setId("statistikk");
        fanePanel.getTabs().add(statistikkFane);
        
        faneMeny.getChildren().add(fanePanel);
        faneMeny.setHgrow(fanePanel, Priority.ALWAYS);
        
        fanePanel.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Tab> ov, Tab t, Tab t1) -> {
            switch (t1.getId()) {
                case "kundebehandling":
                    layout.setCenter(kundeLayout);
                    setTittel("Forsikringsprogram - Kundebehandling - " + kundeLayout.getFaneString());
                    break;
                case "okonomi":
                    layout.setCenter(okonomiLayout);
                    setTittel("Forsikringsprogram - Økonomi");
                    break;
                case "statistikk":
                    layout.setCenter(new StatistikkPane(kundeRegister));
                    setTittel("Forsikringsprogram - Statistikk");
                    break;
            }
        });
        kundeLayout.tabLytter();
    }// end of method opprettFaneMeny()
   
    /**
     * Sjekker teksten skrevet inn i nyverdi mot regex
     * @param feilLabel-Labelen med * som skal endres på
     * @param nyverdi Den nye String verdien fra lytteren som kaller på metoden
     * @param melding
     * @param regex
     * @return 
     */
    public static boolean sjekkRegEx(Label feilLabel, String nyverdi, String melding, String regex){
        if(regex == null){
            if(!(nyverdi.trim().isEmpty()) && sjekkRegexFodselsNr(nyverdi)){
                feilLabel.setText("");
                return true;
            }
            feilLabel.setText(melding);
        }
        else{
            if(!(nyverdi.trim().isEmpty()) && sjekkRegex(regex, nyverdi)){
                feilLabel.setText("");
                return true;
            }
            feilLabel.setText(melding);
        }
        return false;
    }//end of method sjekkRegEx()
    
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
    
    /**
     * 
     * @param fodselNr Sender med det fødselsnummeret som skal valideres opp imot gyldige fødselsnumre.  
     * @return En boolsk verdi som tilsier om fødselsnummeret er validert eller ikke. 
     */
    public static boolean sjekkRegexFodselsNr(String fodselNr) {

        if(fodselNr == null || fodselNr.trim().equals("")) 
          return false;
        if( fodselNr.length() != 11)
          return false;
        return sjekkFodselsNr(fodselNr);
    }// end of method sjekkRegexFodselsNr(String fodselsDato)
    
    /**
     * Formelen for sjekking av gyldig fødselsnummer er basert på Wikipedia-artikkelen: http://no.wikipedia.org/wiki/F%C3%B8dselsnummer
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

    /**
     * Viser en varsel-melding som brukes til å fortelle brukeren at det er gjort noe feil, og brukeren må trykke OK for å fortsette. 
     * @param tittel Tittel på varsel-vinduet
     * @param innhold Innholdet i varsel-vinduet
     */
    public static void visInputFeilMelding(String tittel, String innhold){
        Alert melding = new Alert(Alert.AlertType.INFORMATION);
        melding.setTitle(tittel);
        melding.setHeaderText(null);
        melding.setContentText(innhold);
        melding.showAndWait();
    }// end of method visInputFeilMelding(String tittel, String innhold)
    
    
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
    
    /**
     * 
     * @return Kunderegisteret brukt i GUI-klassen. 
     */
    public Kunderegister getKundeRegister(){
        return kundeRegister;
    }// end of method getKundeRegister()
    
    /**
     * 
     * @return Returnerer scenen sin bredde
     */
    public double sceneBredde(){
        return scene.getWidth();
    }
    
    /**
     * 
     * @return skjermbredde/2
     */
    public static double getSkjermBredde(){
        return (double)opplosning.getWidth();
    } // end of method getSkjermBredde()

    /**
     * 
     * @return Stage'n som blir brukt. 
     */
    public static Stage getStage() {
        return stage;
    }// end of method getStage()
    
    /**
     * 
     * @return skjermhøyde / 1,3
     */
    public static double getSkjermHoyde(){
        return (double)opplosning.getHeight();
    }// end of method getSkjermHoyde()
    
    public static void setTittel(String tittel){
        stage.setTitle(tittel);
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Application.launch(args);
    }// end of main
}// end of class GUI
