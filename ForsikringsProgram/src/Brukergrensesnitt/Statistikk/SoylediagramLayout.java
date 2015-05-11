/**
 *
 * @author Elias
 */

package Brukergrensesnitt.Statistikk;

import forsikringsprogram.Forsikring;
import forsikringsprogram.Kunderegister;
import java.util.Calendar;
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
import static javafx.scene.paint.Color.DARKGRAY;

public class SoylediagramLayout extends GridPane{
    
    private Calendar calMin, calMax;
    private DatePicker datePickerFra, datePickerTil;
    private BarChart<String, Number> bc;
    private GridPane pane, pane1;
    private Label typeLabel, fraLabel, tilLabel;
    private Button oppdaterKnapp;
    private ComboBox cb;
    private Kunderegister kundeRegister;
    private ObservableList<String> forsikringer;
    
    public SoylediagramLayout(Kunderegister register) {
        kundeRegister = register;
        pane = new GridPane();
        pane.setAlignment(CENTER);
        pane1 = new GridPane();
        //pane1.setAlignment(CENTER);
        
        typeLabel = new Label("Forsikringer/Skademeldinger: ");
        fraLabel = new Label("Fra dato: ");
        tilLabel = new Label("Til dato: ");
        
        datePickerFra = new DatePicker();
        datePickerFra.setEditable(false);
        datePickerTil = new DatePicker();
        datePickerTil.setEditable(false);
        
        cb = new ComboBox();
        forsikringer = FXCollections.observableArrayList("Forsikringer", "Skademeldinger");
        cb.setItems(forsikringer);
        cb.setValue("Forsikringer");
        
        calMax = Calendar.getInstance();
        calMax.set(2020, 1, 1);
        calMax.clear(Calendar.HOUR);
        calMax.clear(Calendar.HOUR_OF_DAY);
        calMax.clear(Calendar.MINUTE);
        calMax.clear(Calendar.SECOND);
        calMax.clear(Calendar.MILLISECOND);
        
        calMin = Calendar.getInstance();
        calMin.set(2015, 1, 1);
        calMin.clear(Calendar.HOUR);
        calMin.clear(Calendar.HOUR_OF_DAY);
        calMin.clear(Calendar.MINUTE);
        calMin.clear(Calendar.SECOND);
        calMin.clear(Calendar.MILLISECOND);
        
        
        opprettSoylediagram("Forsikringer", calMin, calMax);
        opprettKnapp();
        opprettKontrollPanel();
        
        setHgap(170);
    }//end of construnctor
    
    /**
     * Oppretter soylediagram som viser antall forsikringer eller skademeldinger (gitt av type) mellom min og max datoene.
     * @param type
     * @param min
     * @param max 
     */
    private void opprettSoylediagram(String type, Calendar min, Calendar max) {
        CategoryAxis xAkse = new CategoryAxis();
        NumberAxis yAkse = new NumberAxis();
        bc = new BarChart<>(xAkse, yAkse);
        bc.setTitle("Antall " + type.toLowerCase());
        xAkse.setLabel("Type");       
        yAkse.setLabel("Antall");    
        
        bc.getData().clear();
        XYChart.Series serie1 = new XYChart.Series();
        serie1.setName(type);
        if(type.equals("Forsikringer")) {
            serie1.getData().add(new XYChart.Data(Forsikring.BAAT, 
                                                  kundeRegister.antallForsikringAvType(Forsikring.BAAT, min, max)));
            serie1.getData().add(new XYChart.Data(Forsikring.BIL, 
                                                  kundeRegister.antallForsikringAvType(Forsikring.BIL, min, max)));
            serie1.getData().add(new XYChart.Data(Forsikring.REISE, 
                                                  kundeRegister.antallForsikringAvType(Forsikring.REISE, min, max)));
            serie1.getData().add(new XYChart.Data(Forsikring.BOLIG, 
                                                  kundeRegister.antallForsikringAvType(Forsikring.BOLIG, min, max)));
        } else {
            serie1.getData().add(new XYChart.Data(Forsikring.BAAT, 
                                                  kundeRegister.antallSkademeldingAvType(Forsikring.BAAT, min, max)));
            serie1.getData().add(new XYChart.Data(Forsikring.BIL, 
                                                  kundeRegister.antallSkademeldingAvType(Forsikring.BIL, min, max)));
            serie1.getData().add(new XYChart.Data(Forsikring.REISE, 
                                                  kundeRegister.antallSkademeldingAvType(Forsikring.REISE, min, max)));
            serie1.getData().add(new XYChart.Data(Forsikring.BOLIG, 
                                                  kundeRegister.antallSkademeldingAvType(Forsikring.BOLIG, min, max)));
        }
       
        bc.getData().add(serie1);
        bc.setBorder(new Border(new BorderStroke(DARKGRAY,SOLID, new CornerRadii(5), THIN, new Insets(15))));
        bc.setCategoryGap(40);
        add(bc,1,2);
    }
    
    /**
     * Oppretter og lytter på knappen.
     */
    private void opprettKnapp() {
        oppdaterKnapp = new Button("Oppdater graf");
        
        oppdaterKnapp.setOnAction((ActionEvent e) -> {
            if(e.getSource() == oppdaterKnapp) {
                bc.getData().clear();
                getChildren().remove(bc);
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
                
                String type = cb.getValue().toString();
                opprettSoylediagram(type, min, max);
            }
        });
    }
    
    /**
     * Legger komponentene ut i "Kontrollpanelet".
     */
    private void opprettKontrollPanel() {
        pane1.add(typeLabel, 1, 1);
        pane1.add(cb, 2, 1);
        GridPane.setHalignment(pane1, HPos.LEFT);
        
        pane.add(fraLabel, 1, 1);
        pane.add(datePickerFra, 2, 1);
        GridPane.setMargin(tilLabel, new Insets(0, 0, 0, 20));
        pane.add(tilLabel, 3, 1);
        pane.add(datePickerTil, 4, 1);
        pane.add(pane1, 1, 2, 4, 1);
        GridPane.setHalignment(oppdaterKnapp, HPos.RIGHT);
        GridPane.setMargin(oppdaterKnapp, new Insets(5, 0, 0, 20));
        pane.add(oppdaterKnapp, 4, 2);
        
        GridPane.setHalignment(pane, HPos.CENTER);
        
        //pane.setHgap(5);
        pane.setVgap(5);
        pane1.setVgap(5);
        //pane1.setHgap(5);
        add(pane, 1, 1);
    }
} 
