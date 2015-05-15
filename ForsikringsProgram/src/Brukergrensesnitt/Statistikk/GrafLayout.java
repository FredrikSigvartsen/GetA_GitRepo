/**
 *
 * @author Elias
 */

package Brukergrensesnitt.Statistikk;

import Brukergrensesnitt.GUI;
import forsikringsprogram.Kunderegister;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import static javafx.geometry.Pos.CENTER;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import static javafx.scene.layout.BorderStroke.THIN;
import static javafx.scene.layout.BorderStrokeStyle.SOLID;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import static javafx.scene.paint.Color.DARKGRAY;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Klassen oppretter og legger en linjegraf med tilhørende kontrollpanel i et gridpane.
 * Siste versrjon skrevet: 15.05.2015 23:22
 * @author Elias Andreassen Thøgersen, Informasjonsteknologi, s236603
 */
public class GrafLayout extends GridPane{
    
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private DatePicker datePickerFra, datePickerTil;
    private ComboBox cb;
    private CheckBox cb1,cb2,cb3,cb4;
    private ObservableList<String> forsikringer;
    private Button knapp;
    private Label fraLabel, tilLabel, typeLabel, beskrivelse;
    private GridPane pane, pane1, pane2;
    private VBox vbox1;
    private LineChart<String,Number> lc;
    private Kunderegister kundeRegister;
    private Image bilde;
    private ImageView iv, iv1;
    private Tooltip tooltip;
    private Font tekstStr, tekstStr1;
    
    /**
     * Konstruktør. Setter opp gridpanet med linje-graf og kontrollpanel.
     * @param register 
     */
    public GrafLayout(Kunderegister register) {
        kundeRegister = register;
        
        cb = new ComboBox();
        forsikringer = FXCollections.observableArrayList(
                                              "Alle", "Bil", "Bolig",
                                              "Båt", "Reise");
        cb.setItems(forsikringer);
        cb.setValue("Alle");
        
        vbox1 = new VBox();
        
        pane = new GridPane();
        pane.setAlignment(CENTER);
        //pane1 = new GridPane();
        pane2 = new GridPane();
        pane1 = new GridPane();
        
        fraLabel = new Label("Fra dato: ");
        tilLabel = new Label("Til dato: ");
        typeLabel = new Label("Type: ");
        beskrivelse = new Label("Linje-graf");
        
        tekstStr = Font.font(null, FontWeight.BOLD, 20);
        tekstStr1 = Font.font(null, FontWeight.BOLD, 12);
        
        bilde = new Image(getClass().getResourceAsStream("/Bilder/info_icon.png"));
        iv = new ImageView();
        iv.setImage(bilde);
        iv.setFitWidth(28);
        iv.setFitHeight(28);
        iv1 = new ImageView();
        iv1.setImage(bilde);
        iv1.setFitWidth(60);
        iv1.setFitHeight(60);
        tooltip = new Tooltip("Linje-grafen viser øking/minking i antall registrerte forsikringer/skademeldinger/kunder " +
                              "\nog utbetalt erstatninger fra dag til dag i tidsrommet mellom de to datoene." +
                              "\nVelg datoer, velg type du vil se og oppdater deretter " +
                              "\nlinjegrafen ved å trykke på knappen.");
        tooltip.setGraphic(iv1);
        tooltip.setFont(tekstStr1);
        Tooltip.install(iv, tooltip);
        
        datePickerFra = new DatePicker();
        datePickerFra.setEditable(false);
        datePickerFra.setValue(LocalDate.of(2015, 5, 9));
        datePickerTil = new DatePicker();
        datePickerTil.setEditable(false);
        datePickerTil.setValue(LocalDate.now());
        
        opprettCheckBoxer();
        opprettGraf(opprettForsikringSerie("Alle"));
        opprettKnapp();
        opprettKontrollPanel();
    }//end of construnctor
    
    /**
     * Oppretter graf med gitt serie.
     * @param serie Første XYChart.Series som legges i grafen
     */
    private void opprettGraf(XYChart.Series serie) {
        CategoryAxis xAkse = new CategoryAxis();
        NumberAxis yAkse = new NumberAxis();
        lc = new LineChart<>(xAkse,yAkse);
        xAkse.setLabel("Måned");       
        yAkse.setLabel("Antall");   
        lc.setTitle("Øking/Minking i registrering");
        lc.getData().add(serie);
        lc.setBorder(new Border(new BorderStroke(DARKGRAY,SOLID, new CornerRadii(5), THIN, new Insets(15))));
        add(lc,1,2);
    }//end of method opprettGraf(XYChart.Series serie)
    
    /**
     * Oppretter graf med de to seriene som er gitt.
     * @param serie1 Første XYChart.Series som legges i grafen
     * @param serie2 Andre XYChart.Series som legges i grafen
     */
    private void opprettGraf(XYChart.Series serie1, XYChart.Series serie2) {
        CategoryAxis xAkse = new CategoryAxis();
        NumberAxis yAkse = new NumberAxis();
        lc = new LineChart<>(xAkse,yAkse);
        xAkse.setLabel("Måned");       
        yAkse.setLabel("Antall");   
        lc.setTitle("Øking/Minking i registrering");
        lc.getData().addAll(serie1, serie2);
        lc.setBorder(new Border(new BorderStroke(DARKGRAY,SOLID, new CornerRadii(5), THIN, new Insets(15))));
        add(lc,1,2);
    }//end of method opprettGraf(XYChart.Series serie1, XYChart.Series serie2)
    
    /**
     * Oppretter graf med de tre seriene som er gitt.
     * @param serie1 Første XYChart.Series som legges i grafen
     * @param serie2 Andre XYChart.Series som legges i grafen
     * @param serie3 Tredje XYChart.Series som legges i grafen
     */
    private void opprettGraf(XYChart.Series serie1, XYChart.Series serie2, XYChart.Series serie3) {
        CategoryAxis xAkse = new CategoryAxis();
        NumberAxis yAkse = new NumberAxis();
        lc = new LineChart<>(xAkse,yAkse);
        xAkse.setLabel("Måned");       
        yAkse.setLabel("Antall");   
        lc.setTitle("Øking/Minking i registrering");
        lc.getData().addAll(serie1, serie2, serie3);
        lc.setBorder(new Border(new BorderStroke(DARKGRAY,SOLID, new CornerRadii(5), THIN, new Insets(15))));
        add(lc,1,2);
    }//end of method opprettGraf(XYChart.Series serie1, XYChart.Series serie2, XYChart.Series serie3)
    
    /**
     * Oppretter graf med de fire seriene om er gitt.
     * @param serie1 Første XYChart.Series som legges i grafen
     * @param serie2 Andre XYChart.Series som legges i grafen
     * @param serie3 Tredje XYChart.Series som legges i grafen
     * @param serie4 Fjerde XYChart.Series som legges i grafen
     */
    private void opprettGraf(XYChart.Series serie1, XYChart.Series serie2, XYChart.Series serie3, XYChart.Series serie4) {
        CategoryAxis xAkse = new CategoryAxis();
        NumberAxis yAkse = new NumberAxis();
        lc = new LineChart<>(xAkse,yAkse);
        xAkse.setLabel("Måned");       
        yAkse.setLabel("Antall");   
        lc.setTitle("Øking/Minking i registrering");
        lc.getData().addAll(serie1, serie2, serie3, serie4);
        lc.setBorder(new Border(new BorderStroke(DARKGRAY,SOLID, new CornerRadii(5), THIN, new Insets(15))));
        add(lc,1,2);
    }//end of method opprettGraf(XYChart.Series serie1, XYChart.Series serie2, XYChart.Series serie3, XYChart.Series serie4)
    
    /**
     * Oppretter en XYChart.Series over antall registrerte forsikringer med gitt type på de forskjellige datoene.
     * @param type Spesifiserer hvilke type forsikringer som skal vises
     * @return Returnerer XYChart.Series.
     */
    private XYChart.Series opprettForsikringSerie(String type) {
        List<Date> datoListe = lagDatoListe();
        ListIterator<Date> iter = datoListe.listIterator();
        
        XYChart.Series serie = new XYChart.Series();
        serie.setName("Forsikringer");
        
        if(type.equals("Alle")) {
            while(iter.hasNext()) {
                Date dato = iter.next();
                serie.getData().add(new XYChart.Data(sdf.format(dato), kundeRegister.antallForsikringerPaaDato(dato)));
            }
        } else {
            while(iter.hasNext()) {
                Date dato = iter.next();
                serie.getData().add(new XYChart.Data(sdf.format(dato), kundeRegister.antallForsikringerPaaDatoMedType(dato, type)));
            }
        }
        
        return serie;
    }//end of opprettForsikringSerie(String type)
    
    /**
     * Oppretter en XYChart.Series over antall registrerte skademeldinger med gitt type på de forskjellige datoene.
     * @param type Spesifiserer hvilke type skademeldinger som skal vises
     * @return Returnerer XYChart.Series.
     */
    private XYChart.Series opprettSkademeldingSerie(String type) {
        List<Date> datoListe = lagDatoListe();
        ListIterator<Date> iter = datoListe.listIterator();
        
        XYChart.Series serie = new XYChart.Series();
        serie.setName("Skademeldinger");
        
        if(type.equals("Alle")) {
            while(iter.hasNext()) {
                Date dato = iter.next();
                serie.getData().add(new XYChart.Data(sdf.format(dato), kundeRegister.antallSkademeldingerPaaDato(dato)));
            }
        } else {
            while(iter.hasNext()) {
                Date dato = iter.next();
                serie.getData().add(new XYChart.Data(sdf.format(dato), kundeRegister.antallSkademeldingerPaaDatoMedType(dato, type)));
            }
        }
        
        return serie;
    }//end of method opprettSkademeldingSerie(String type)
    
    /**
     * Oppretter en XYChart.Series over antall registrerte kunder på de forskjellige datoene.
     * @return XYChart.Series.
     */
    private XYChart.Series opprettKundeSerie() {
        List<Date> datoListe = lagDatoListe();
        ListIterator<Date> iter = datoListe.listIterator();
        
        XYChart.Series serie = new XYChart.Series();
        serie.setName("Kunder");
        
        while(iter.hasNext()) {
            Date dato = iter.next();
            serie.getData().add(new XYChart.Data(sdf.format(dato), kundeRegister.antallKunderPaaDato(dato)));
        }
        
        return serie;
    }//end of method opprettKundeSerie()
    
    /**
     * Oppretter en XYChart.Series over utgifter av gitt type, på de forskjellige datoene.
     * @param type Spesifiserer hvilke type utgift som vises (bil-, båt-, bolig- eller reise-erstatning)
     * @return Reutnerer XYChart.Series.
     */
    private XYChart.Series opprettUtgifterSerie(String type) {
        List<Date> datoListe = lagDatoListe();
        ListIterator<Date> iter = datoListe.listIterator();
        
        XYChart.Series serie = new XYChart.Series();
        serie.setName("Erstatningskostnader");
        
        if(type.equals("Alle")) {
            while(iter.hasNext()) {
                Date dato = iter.next();
                serie.getData().add(new XYChart.Data(sdf.format(dato), kundeRegister.utgifterPaaDato(dato)));
            }
        } else {
            while(iter.hasNext()) {
                Date dato = iter.next();
                serie.getData().add(new XYChart.Data(sdf.format(dato), kundeRegister.utgifterPaaDatoMedType(dato, type)));
            }
        }
        
        return serie;
    }//end of method opprettUtgifterSerie(String type)
    
    /**
     * Oppretter knappen og lytter på den.
     */
    private void opprettKnapp() {
        knapp = new Button("Oppdater linje-graf");
        
        knapp.setOnAction((ActionEvent e) -> {
            if(e.getSource() == knapp) {
                lc.getData().clear();
                getChildren().remove(lc);
                String type = cb.getValue().toString();
                if(cb1.isSelected() && !cb2.isSelected() && !cb3.isSelected() && !cb4.isSelected()) {
                    opprettGraf(opprettForsikringSerie(type));
                } else if(!cb1.isSelected() && cb2.isSelected() && !cb3.isSelected() && !cb4.isSelected()) {
                    opprettGraf(opprettSkademeldingSerie(type));
                } else if(!cb1.isSelected() && !cb2.isSelected() && cb3.isSelected() && !cb4.isSelected()) {
                    opprettGraf(opprettKundeSerie());
                } else if(!cb1.isSelected() && !cb2.isSelected() && !cb3.isSelected() && cb4.isSelected()) {
                    opprettGraf(opprettUtgifterSerie(type));
                } else if(cb1.isSelected() && cb2.isSelected() && !cb3.isSelected() && !cb4.isSelected()) {
                    opprettGraf(opprettForsikringSerie(type), 
                                opprettSkademeldingSerie(type));
                } else if(cb1.isSelected() && !cb2.isSelected() && cb3.isSelected() && !cb4.isSelected()) {
                    opprettGraf(opprettForsikringSerie(type),
                                opprettKundeSerie());
                } else if(cb1.isSelected() && !cb2.isSelected() && !cb3.isSelected() && cb4.isSelected()) {
                    opprettGraf(opprettForsikringSerie(type),
                                opprettUtgifterSerie(type));
                } else if(!cb1.isSelected() && cb2.isSelected() && cb3.isSelected() && !cb4.isSelected()) {
                    opprettGraf(opprettSkademeldingSerie(type),
                                opprettKundeSerie());
                } else if(!cb1.isSelected() && cb2.isSelected() && !cb3.isSelected() && cb4.isSelected()) {
                    opprettGraf(opprettSkademeldingSerie(type),
                                opprettUtgifterSerie(type));
                } else if(!cb1.isSelected() && !cb2.isSelected() && cb3.isSelected() && cb4.isSelected()) {
                    opprettGraf(opprettKundeSerie(),
                                opprettUtgifterSerie(type));
                } else if(cb1.isSelected() && cb2.isSelected() && cb3.isSelected() && !cb4.isSelected()) {
                    opprettGraf(opprettForsikringSerie(type),
                                opprettSkademeldingSerie(type),
                                opprettKundeSerie());
                } else if(cb1.isSelected() && !cb2.isSelected() && cb3.isSelected() && cb4.isSelected()) {
                    opprettGraf(opprettForsikringSerie(type),
                                opprettKundeSerie(),
                                opprettUtgifterSerie(type));
                } else if(cb1.isSelected() && cb2.isSelected() && !cb3.isSelected() && cb4.isSelected()) {
                    opprettGraf(opprettForsikringSerie(type),
                                opprettSkademeldingSerie(type),
                                opprettUtgifterSerie(type));
                } else if(!cb1.isSelected() && cb2.isSelected() && cb3.isSelected() && cb4.isSelected()) {
                    opprettGraf(opprettSkademeldingSerie(type),
                                opprettKundeSerie(),
                                opprettUtgifterSerie(type));
                } else if(cb1.isSelected() && cb2.isSelected() && cb3.isSelected() && cb4.isSelected()) {
                    opprettGraf(opprettForsikringSerie(type),
                                opprettSkademeldingSerie(type),
                                opprettKundeSerie(),
                                opprettUtgifterSerie(type));
                } else if(!cb1.isSelected() && !cb2.isSelected() && !cb3.isSelected() && !cb4.isSelected()) {
                    GUI.visInputFeilMelding("Feil", "Du må huke av minst en av boksene!");
                }
            }//end of if
        });//end of setOnAction
    }//end of method opprettKnapp()   
    
    
    /**
     * Oppretter CheckBoxer og lytter på dem.
     */
    private void opprettCheckBoxer() {
        cb1 = new CheckBox("Forsikringer");
        cb1.setSelected(true);
        cb2 = new CheckBox("Skademeldinger");
        cb3 = new CheckBox("Kunder");
        cb3.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue) {
                    cb.setValue("Alle");
                    cb.setDisable(true);
                } else {
                    cb.setDisable(false);
                }
            }
        });//end of addListener
        cb4 = new CheckBox("Utgifter");
    }//end of opprettCheckBoxer()
    
    /**
     * Legger alle komponentene inn i "Kontrollpanelet".
     */
    private void opprettKontrollPanel() {
        
        vbox1.getChildren().addAll(cb1, cb2, cb3, cb4);
        GridPane.setMargin(typeLabel, new Insets(0, 0, 0, 20));
        pane2.add(typeLabel, 1, 1);
        GridPane.setMargin(cb, new Insets(0, 0, 0, 14));
        pane2.add(cb, 2, 1);
        GridPane.setMargin(knapp, new Insets(5, 0, 0, 20));
        pane2.add(knapp, 1, 2, 2, 1);
        
        pane1.add(beskrivelse, 1, 1);
        GridPane.setMargin(iv, new Insets(0, 0, 0, 15));
        pane1.add(iv, 2, 1);
        
        beskrivelse.setFont(tekstStr);
        pane.add(pane1, 1, 1, 4, 1);
        pane.add(fraLabel, 1, 2);
        pane.add(datePickerFra, 2, 2);
        GridPane.setMargin(tilLabel, new Insets(0, 0, 0, 20));
        pane.add(tilLabel, 3, 2);
        pane.add(datePickerTil, 4, 2);
        GridPane.setMargin(vbox1, new Insets(5, 0, 0, 20));
        pane.add(vbox1, 2, 3);
        
        pane.add(pane2, 3, 3,2,1);
        
        GridPane.setHalignment(pane, HPos.CENTER);
        
        pane.setVgap(5);
        pane2.setVgap(5);
        add(pane, 1, 1);
    }//end of method opprettKontrollPanel()
    
    /**
     * Lager en liste med Date-objekter utifra Fra-dato og Til-dato satt av bruker.
     * @return Returnerer en List<Date> med alle datoer fra og med "Fra dato"-DatePickern, til og med "Til dato"-DatePickern. 
     */
    private List<Date> lagDatoListe() {
        List<Date> datoListe = new ArrayList<>();
        
        Calendar min = Calendar.getInstance();
        min.set(datePickerFra.getValue().getYear(), 
                datePickerFra.getValue().getMonthValue() - 1, 
                datePickerFra.getValue().getDayOfMonth()); 
        min.clear(Calendar.HOUR);
        min.clear(Calendar.HOUR_OF_DAY);
        min.clear(Calendar.MINUTE);
        min.clear(Calendar.SECOND);
        min.clear(Calendar.MILLISECOND);
        Calendar max = Calendar.getInstance(); 
        max.set(datePickerTil.getValue().getYear(),
                datePickerTil.getValue().getMonthValue() - 1, 
                datePickerTil.getValue().getDayOfMonth());
        max.clear(Calendar.HOUR);
        max.clear(Calendar.HOUR_OF_DAY);
        max.clear(Calendar.MINUTE);
        max.clear(Calendar.SECOND);
        max.clear(Calendar.MILLISECOND);
        
        while(!min.after(max)) {
            datoListe.add(min.getTime());
            min.add(Calendar.DATE, 1);
        }
        return datoListe;
    }//end of method lagDatoListe()
}//end of class GrafLayout
