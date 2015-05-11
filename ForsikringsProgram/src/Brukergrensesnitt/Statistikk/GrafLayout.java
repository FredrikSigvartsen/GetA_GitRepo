/**
 *
 * @author Elias
 */

package Brukergrensesnitt.Statistikk;

import Brukergrensesnitt.GUI;
import forsikringsprogram.Forsikring;
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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import static javafx.scene.layout.BorderStroke.THIN;
import static javafx.scene.layout.BorderStrokeStyle.SOLID;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import static javafx.scene.paint.Color.DARKGRAY;

public class GrafLayout extends GridPane{
    
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private DatePicker datePickerFra, datePickerTil;
    private ComboBox cb;
    private CheckBox cb1,cb2,cb3,cb4;
    private ObservableList<String> forsikringer;
    private Button knapp;
    private Label fraLabel, tilLabel, typeLabel/*, statLabel*/;
    private GridPane pane/*, pane1*/, pane2;
    private VBox vbox1;
    private LineChart<String,Number> lc;
    private Kunderegister kundeRegister;
    
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
        
        fraLabel = new Label("Fra dato: ");
        tilLabel = new Label("Til dato: ");
        typeLabel = new Label("Type: ");
        //statLabel = new Label("Velg statistikk du vil vise: ");
        
        datePickerFra = new DatePicker();
        datePickerFra.setEditable(false);
        datePickerFra.setValue(LocalDate.of(2015, 5, 1));
        datePickerTil = new DatePicker();
        datePickerTil.setEditable(false);
        datePickerTil.setValue(LocalDate.now());
        
        opprettCheckBoxer();
        opprettGraf(opprettForsikringSerie("Alle"));
        opprettKnapp();
        opprettKontrollPanel();
    }//end of construnctor
    
    public void opprettGraf(XYChart.Series serie) {
        CategoryAxis xAkse = new CategoryAxis();
        NumberAxis yAkse = new NumberAxis();
        lc = new LineChart<>(xAkse,yAkse);
        xAkse.setLabel("Måned");       
        yAkse.setLabel("Antall");   
        lc.setTitle("Øking/Minking");
        lc.getData().add(serie);
        lc.setBorder(new Border(new BorderStroke(DARKGRAY,SOLID, new CornerRadii(5), THIN, new Insets(15))));
        add(lc,1,2);
    }
    
    public void opprettGraf(XYChart.Series serie1, XYChart.Series serie2) {
        CategoryAxis xAkse = new CategoryAxis();
        NumberAxis yAkse = new NumberAxis();
        lc = new LineChart<>(xAkse,yAkse);
        xAkse.setLabel("Måned");       
        yAkse.setLabel("Antall");   
        lc.setTitle("Øking/Minking");
        lc.getData().addAll(serie1, serie2);
        lc.setBorder(new Border(new BorderStroke(DARKGRAY,SOLID, new CornerRadii(5), THIN, new Insets(15))));
        add(lc,1,2);
    }
    
    public void opprettGraf(XYChart.Series serie1, XYChart.Series serie2, XYChart.Series serie3) {
        CategoryAxis xAkse = new CategoryAxis();
        NumberAxis yAkse = new NumberAxis();
        lc = new LineChart<>(xAkse,yAkse);
        xAkse.setLabel("Måned");       
        yAkse.setLabel("Antall");   
        lc.setTitle("Øking/Minking");
        lc.getData().addAll(serie1, serie2, serie3);
        lc.setBorder(new Border(new BorderStroke(DARKGRAY,SOLID, new CornerRadii(5), THIN, new Insets(15))));
        add(lc,1,2);
    }
    
    public void opprettGraf(XYChart.Series serie1, XYChart.Series serie2, XYChart.Series serie3, XYChart.Series serie4) {
        CategoryAxis xAkse = new CategoryAxis();
        NumberAxis yAkse = new NumberAxis();
        lc = new LineChart<>(xAkse,yAkse);
        xAkse.setLabel("Måned");       
        yAkse.setLabel("Antall");   
        lc.setTitle("Øking/Minking");
        lc.getData().addAll(serie1, serie2, serie3, serie4);
        lc.setBorder(new Border(new BorderStroke(DARKGRAY,SOLID, new CornerRadii(5), THIN, new Insets(15))));
        add(lc,1,2);
    }
    
    public XYChart.Series opprettForsikringSerie(String type) {
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
    }
    
    public XYChart.Series opprettSkademeldingSerie(String type) {
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
    }
    
    public XYChart.Series opprettKundeSerie() {
        List<Date> datoListe = lagDatoListe();
        ListIterator<Date> iter = datoListe.listIterator();
        
        XYChart.Series serie = new XYChart.Series();
        serie.setName("Kunder");
        
        while(iter.hasNext()) {
            Date dato = iter.next();
            serie.getData().add(new XYChart.Data(sdf.format(dato), kundeRegister.antallKunderPaaDato(dato)));
        }
        
        return serie;
    }
    
    public XYChart.Series opprettUtgifterSerie(String type) {
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
    }
    
    public List<Date> lagDatoListe() {
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
    }
    
    public void opprettKnapp() {
        knapp = new Button("Oppdater graf");
        
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
            }
        });
    }   
    
    public void opprettCheckBoxer() {
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
        });
        cb4 = new CheckBox("Utgifter");
    }
    
    public void opprettKontrollPanel() {
        
        vbox1.getChildren().addAll(cb1, cb2, cb3, cb4);
        GridPane.setMargin(typeLabel, new Insets(0, 0, 0, 20));
        pane2.add(typeLabel, 1, 1);
        GridPane.setMargin(cb, new Insets(0, 0, 0, 14));
        pane2.add(cb, 2, 1);
        GridPane.setMargin(knapp, new Insets(5, 0, 0, 20));
        pane2.add(knapp, 1, 2, 2, 1);
        
        pane.add(fraLabel, 1, 1);
        pane.add(datePickerFra, 2, 1);
        GridPane.setMargin(tilLabel, new Insets(0, 0, 0, 20));
        pane.add(tilLabel, 3, 1);
        pane.add(datePickerTil, 4, 1);
        GridPane.setMargin(vbox1, new Insets(5, 0, 0, 20));
        pane.add(vbox1, 2, 2);
        
        pane.add(pane2, 3, 2,2,1);
        
        GridPane.setHalignment(pane, HPos.CENTER);
        
        pane.setVgap(5);
        pane2.setVgap(5);
        add(pane, 1, 1);
    }
}
