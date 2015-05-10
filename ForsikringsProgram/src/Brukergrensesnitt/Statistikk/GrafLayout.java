package Brukergrensesnitt.Statistikk;

import forsikringsprogram.Forsikring;
import forsikringsprogram.Kunderegister;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import static javafx.geometry.Pos.CENTER;
import static javafx.geometry.Pos.CENTER_RIGHT;
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

/**
 *
 * @author Elias
 */
public class GrafLayout extends GridPane{
    
    private DatePicker datePickerFra, datePickerTil;
    private ComboBox cb;
    private CheckBox cb1,cb2,cb3,cb4;
    private Button knapp;
    private Label fraLabel, tilLabel, typeLabel/*, statLabel*/;
    private GridPane pane/*, pane1*/, pane2;
    private VBox vbox1;
    private LineChart<String,Number> lc;
    private Kunderegister kundeRegister;
    
    public GrafLayout(Kunderegister register) {
        kundeRegister = register;
        
        cb1 = new CheckBox("Forsikringer");
        cb2 = new CheckBox("Skademeldinger");
        cb3 = new CheckBox("Kunder");
        cb4 = new CheckBox("Utgifter");
        
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
        datePickerTil = new DatePicker();
        
        opprettGrafMedAlle();
        opprettKnapp();
        opprettComboBoxer();
        opprettKontrollPanel();
    }//end of construnctor
    
    public void opprettGrafMedAlle() {
        
        CategoryAxis xAkse = new CategoryAxis();
        NumberAxis yAkse = new NumberAxis();
        lc = new LineChart<>(xAkse,yAkse);
        xAkse.setLabel("Måned");       
        yAkse.setLabel("Antall");   
        lc.setTitle("Øking/Minking");
        
        XYChart.Series serie1 = new XYChart.Series();
        serie1.setName("Skademeldinger");
        serie1.getData().add(new XYChart.Data("Jan", 23));
        serie1.getData().add(new XYChart.Data("Feb", 14));
        serie1.getData().add(new XYChart.Data("Mar", 15));
        serie1.getData().add(new XYChart.Data("Apr", 24));
        serie1.getData().add(new XYChart.Data("Mai", 34));
        serie1.getData().add(new XYChart.Data("Jun", 36));
        serie1.getData().add(new XYChart.Data("Jul", 22));
        serie1.getData().add(new XYChart.Data("Aug", 45));
        serie1.getData().add(new XYChart.Data("Sep", 43));
        serie1.getData().add(new XYChart.Data("Okt", 17));
        serie1.getData().add(new XYChart.Data("Nov", 29));
        serie1.getData().add(new XYChart.Data("Des", 25));
        
        XYChart.Series serie2 = new XYChart.Series();
        serie2.setName("Forsikringer");
        serie2.getData().add(new XYChart.Data("Jan", 12));
        serie2.getData().add(new XYChart.Data("Feb", 3));
        serie2.getData().add(new XYChart.Data("Mar", 8));
        serie2.getData().add(new XYChart.Data("Apr", 34));
        serie2.getData().add(new XYChart.Data("Mai", 23));
        serie2.getData().add(new XYChart.Data("Jun", 50));
        serie2.getData().add(new XYChart.Data("Jul", 27));
        serie2.getData().add(new XYChart.Data("Aug", 13));
        serie2.getData().add(new XYChart.Data("Sep", 26));
        serie2.getData().add(new XYChart.Data("Okt", 19));
        serie2.getData().add(new XYChart.Data("Nov", 21));
        serie2.getData().add(new XYChart.Data("Des", 10));
        
        XYChart.Series serie3 = new XYChart.Series();
        serie3.setName("Kunder");
        serie3.getData().add(new XYChart.Data("Jan", 7));
        serie3.getData().add(new XYChart.Data("Feb", 15));
        serie3.getData().add(new XYChart.Data("Mar", 13));
        serie3.getData().add(new XYChart.Data("Apr", 21));
        serie3.getData().add(new XYChart.Data("Mai", 19));
        serie3.getData().add(new XYChart.Data("Jun", 13));
        serie3.getData().add(new XYChart.Data("Jul", 7));
        serie3.getData().add(new XYChart.Data("Aug", 15));
        serie3.getData().add(new XYChart.Data("Sep", 10));
        serie3.getData().add(new XYChart.Data("Okt", 11));
        serie3.getData().add(new XYChart.Data("Nov", 5));
        serie3.getData().add(new XYChart.Data("Des", 16));
        
        XYChart.Series serie4 = new XYChart.Series();
        serie4.setName("Erstatningskostnader");
        serie4.getData().add(new XYChart.Data("Jan", 20000));
        serie4.getData().add(new XYChart.Data("Feb", 9000));
        serie4.getData().add(new XYChart.Data("Mar", 15000));
        serie4.getData().add(new XYChart.Data("Apr", 17000));
        serie4.getData().add(new XYChart.Data("Mai", 16545));
        serie4.getData().add(new XYChart.Data("Jun", 19000));
        serie4.getData().add(new XYChart.Data("Jul", 14592));
        serie4.getData().add(new XYChart.Data("Aug", 34325));
        serie4.getData().add(new XYChart.Data("Sep", 10000));
        serie4.getData().add(new XYChart.Data("Okt", 14200));
        serie4.getData().add(new XYChart.Data("Nov", 43211));
        serie4.getData().add(new XYChart.Data("Des", 23111));
        
        lc.getData().addAll(serie1, serie2, serie3, serie4);
        lc.setBorder(new Border(new BorderStroke(DARKGRAY,SOLID, new CornerRadii(5), THIN, new Insets(15))));
        add(lc,1,2);
    }
    
    public void opprettForsikringsGraf() {
        CategoryAxis xAkse = new CategoryAxis();
        NumberAxis yAkse = new NumberAxis();
        lc = new LineChart<>(xAkse,yAkse);
        xAkse.setLabel("Måned");       
        yAkse.setLabel("Antall");   
        lc.setTitle("Øking/Minking");
        
        XYChart.Series serie2 = new XYChart.Series();
        serie2.setName("Forsikringer");
        serie2.getData().add(new XYChart.Data("Jan", 12));
        serie2.getData().add(new XYChart.Data("Feb", 3));
        serie2.getData().add(new XYChart.Data("Mar", 8));
        serie2.getData().add(new XYChart.Data("Apr", 34));
        serie2.getData().add(new XYChart.Data("Mai", 23));
        serie2.getData().add(new XYChart.Data("Jun", 50));
        serie2.getData().add(new XYChart.Data("Jul", 27));
        serie2.getData().add(new XYChart.Data("Aug", 13));
        serie2.getData().add(new XYChart.Data("Sep", 26));
        serie2.getData().add(new XYChart.Data("Okt", 19));
        serie2.getData().add(new XYChart.Data("Nov", 21));
        serie2.getData().add(new XYChart.Data("Des", 10));
        
        lc.getData().add(serie2);
        lc.setBorder(new Border(new BorderStroke(DARKGRAY,SOLID, new CornerRadii(5), THIN, new Insets(15))));
        add(lc,1,2);
    }
    
    public void opprettSkademeldingGraf() {
        CategoryAxis xAkse = new CategoryAxis();
        NumberAxis yAkse = new NumberAxis();
        lc = new LineChart<>(xAkse,yAkse);
        xAkse.setLabel("Måned");       
        yAkse.setLabel("Antall");   
        lc.setTitle("Øking/Minking");
        
        XYChart.Series serie1 = new XYChart.Series();
        serie1.setName("Skademeldinger");
        serie1.getData().add(new XYChart.Data("Jan", 23));
        serie1.getData().add(new XYChart.Data("Feb", 14));
        serie1.getData().add(new XYChart.Data("Mar", 15));
        serie1.getData().add(new XYChart.Data("Apr", 24));
        serie1.getData().add(new XYChart.Data("Mai", 34));
        serie1.getData().add(new XYChart.Data("Jun", 36));
        serie1.getData().add(new XYChart.Data("Jul", 22));
        serie1.getData().add(new XYChart.Data("Aug", 45));
        serie1.getData().add(new XYChart.Data("Sep", 43));
        serie1.getData().add(new XYChart.Data("Okt", 17));
        serie1.getData().add(new XYChart.Data("Nov", 29));
        serie1.getData().add(new XYChart.Data("Des", 25));
        
        lc.getData().add(serie1);
        lc.setBorder(new Border(new BorderStroke(DARKGRAY,SOLID, new CornerRadii(5), THIN, new Insets(15))));
        add(lc,1,2);
    }
    
    public void opprettKundeGraf() {
        CategoryAxis xAkse = new CategoryAxis();
        NumberAxis yAkse = new NumberAxis();
        lc = new LineChart<>(xAkse,yAkse);
        xAkse.setLabel("Måned");       
        yAkse.setLabel("Antall");   
        lc.setTitle("Øking/Minking");
        
        XYChart.Series serie3 = new XYChart.Series();
        serie3.setName("Kunder");
        serie3.getData().add(new XYChart.Data("Jan", 7));
        serie3.getData().add(new XYChart.Data("Feb", 15));
        serie3.getData().add(new XYChart.Data("Mar", 13));
        serie3.getData().add(new XYChart.Data("Apr", 21));
        serie3.getData().add(new XYChart.Data("Mai", 19));
        serie3.getData().add(new XYChart.Data("Jun", 13));
        serie3.getData().add(new XYChart.Data("Jul", 7));
        serie3.getData().add(new XYChart.Data("Aug", 15));
        serie3.getData().add(new XYChart.Data("Sep", 10));
        serie3.getData().add(new XYChart.Data("Okt", 11));
        serie3.getData().add(new XYChart.Data("Nov", 5));
        serie3.getData().add(new XYChart.Data("Des", 16));
        
        lc.getData().add(serie3);
        lc.setBorder(new Border(new BorderStroke(DARKGRAY,SOLID, new CornerRadii(5), THIN, new Insets(15))));
        add(lc,1,2);
    }
    
    public void opprettKnapp() {
        knapp = new Button("Oppdater graf");
        
        knapp.setOnAction((ActionEvent e) -> {
            if(cb.isDisable()) {
                if(cb.getValue() == null)
                    cb.setValue("Forsikringer");
                if(cb.getValue().equals("Kunder")) {
                    getChildren().remove(lc);
                    opprettKundeGraf();
                } else if(cb.getValue().equals("Forsikringer")) {
                    getChildren().remove(lc);
                    opprettForsikringsGraf();
                } else if(cb.getValue().equals("Skademeldinger")) {
                    getChildren().remove(lc);
                    opprettSkademeldingGraf();
                }
                    
                cb.setDisable(false);
                knapp.setText("Vis alle");
            }
            else {
                getChildren().remove(lc);
                opprettGrafMedAlle();
                cb.setDisable(true);
                knapp.setText("Vis en og en");
            }
        });
    }
    
    public void opprettComboBoxer() {
        cb = new ComboBox();
        ObservableList<String> forsikringer = FXCollections.observableArrayList(
                                              "Alle", "Bil", "Bolig",
                                              "Båt", "Reise");
        cb.setItems(forsikringer);
        cb.setValue("Alle");
        /*cb.valueProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                lc.getData().clear();
                getChildren().remove(lc);
                switch ((String) t1) {
                    case "Forsikringer":
                        opprettForsikringsGraf();
                        break;
                    case "Skademeldinger":
                        opprettSkademeldingGraf();
                        break;
                    case "Kunder":
                        opprettKundeGraf();
                        break;
                }
            }
            
        });*/
        //cb.setDisable(true);
    }
    
    public void opprettKontrollPanel() {
        //pane1.add(cb1, 1, 1);
        //pane1.add(cb2, 2, 1);
        //pane1.add(cb3, 3, 1);
        //pane1.add(cb4, 4, 1);
        
        vbox1.getChildren().addAll(cb1, cb2, cb3, cb4);
        GridPane.setMargin(typeLabel, new Insets(0, 0, 0, 20));
        pane2.add(typeLabel, 1, 1);
        GridPane.setMargin(cb, new Insets(0, 0, 0, 14));
        pane2.add(cb, 2, 1);
        GridPane.setMargin(knapp, new Insets(5, 0, 0, 20));
        pane2.add(knapp, 1, 2, 2, 1);
        //pane2.setAlignment(CENTER);
        
        pane.add(fraLabel, 1, 1);
        pane.add(datePickerFra, 2, 1);
        GridPane.setMargin(tilLabel, new Insets(0, 0, 0, 20));
        pane.add(tilLabel, 3, 1);
        pane.add(datePickerTil, 4, 1);
        GridPane.setMargin(vbox1, new Insets(5, 0, 0, 20));
        pane.add(vbox1, 2, 2);
        
        pane.add(pane2, 3, 2,2,1);
   
       
        
        //pane.add(cb,1,2);
        //pane.add(knapp,2,2);
        //pane.add(pane1, 1, 2, 4, 1);
        //GridPane.setHalignment(oppdaterKnapp, HPos.RIGHT);
        //GridPane.setMargin(oppdaterKnapp, new Insets(5, 0, 0, 20));
        //pane.add(oppdaterKnapp, 4, 2);
        
        GridPane.setHalignment(pane, HPos.CENTER);
        
        //pane.setHgap(5);
        pane.setVgap(5);
        //pane1.setVgap(5);
        pane2.setVgap(5);
        //pane1.setHgap(5);
        add(pane, 1, 1);
    }
}
