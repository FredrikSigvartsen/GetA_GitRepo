/**
 *
 * @author Elias
 */

package Brukergrensesnitt.Statistikk;

import forsikringsprogram.Forsikring;
import forsikringsprogram.Kunderegister;
import java.time.LocalDate;
import java.util.Calendar;
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
import static javafx.scene.paint.Color.DARKGRAY;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Oppretter søylediagram med kontrollpanel.
 * @author Elias
 */
public class SoylediagramLayout extends GridPane{
    
    private Calendar calMin, calMax;
    private DatePicker datePickerFra, datePickerTil;
    private BarChart<String, Number> bc;
    private GridPane pane, pane1, pane2;
    private Label typeLabel, fraLabel, tilLabel, beskrivelse;
    private Button oppdaterKnapp;
    private ComboBox cb;
    private Kunderegister kundeRegister;
    private ObservableList<String> forsikringer;
    private Tooltip tooltip;
    private Image bilde;
    private ImageView iv, iv1;
    private Font tekstStr, tekstStr1;
    
    /**
     * Konstruktør. Setter opp gridpanet med søylediagram og kontrollpanel.
     * @param register 
     */
    public SoylediagramLayout(Kunderegister register) {
        kundeRegister = register;
        pane = new GridPane();
        pane.setAlignment(CENTER);
        pane1 = new GridPane();
        pane2 = new GridPane();
        
        typeLabel = new Label("Forsikringer/Skademeldinger: ");
        fraLabel = new Label("Fra dato: ");
        tilLabel = new Label("Til dato: ");
        beskrivelse = new Label("Søylediagram");
        
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
        tooltip = new Tooltip("Søylediagrammet viser antall aktive forsikringer/skademeldinger i" +
                              "\ntidsrommet mellom de to datoene. Velg datoer, velg type du vil se, oppdater deretter " +
                              "\ndiagrammet ved å trykke på knappen.");
        tooltip.setGraphic(iv1);
        tooltip.setFont(tekstStr1);
        Tooltip.install(iv, tooltip);
        
        datePickerFra = new DatePicker();
        datePickerFra.setEditable(false);
        datePickerFra.setValue(LocalDate.of(2015, 5, 9));
        datePickerTil = new DatePicker();
        datePickerTil.setEditable(false);
        datePickerTil.setValue(LocalDate.now());
        
        cb = new ComboBox();
        forsikringer = FXCollections.observableArrayList("Forsikringer", "Skademeldinger");
        cb.setItems(forsikringer);
        cb.setValue("Forsikringer");
        
        calMax = Calendar.getInstance();
        calMax.set(datePickerTil.getValue().getYear(), 
                   datePickerTil.getValue().getMonthValue() - 1, 
                   datePickerTil.getValue().getDayOfMonth());
        calMax.clear(Calendar.HOUR);
        calMax.clear(Calendar.HOUR_OF_DAY);
        calMax.clear(Calendar.MINUTE);
        calMax.clear(Calendar.SECOND);
        calMax.clear(Calendar.MILLISECOND);
        
        calMin = Calendar.getInstance();
        calMin.set(datePickerFra.getValue().getYear(), 
                   datePickerFra.getValue().getMonthValue() - 1, 
                   datePickerFra.getValue().getDayOfMonth());
        calMin.clear(Calendar.HOUR);
        calMin.clear(Calendar.HOUR_OF_DAY);
        calMin.clear(Calendar.MINUTE);
        calMin.clear(Calendar.SECOND);
        calMin.clear(Calendar.MILLISECOND);
        
        
        opprettSoylediagram("Forsikringer", calMin, calMax);
        opprettKnapp();
        opprettKontrollPanel();
        
        //setHgap(145);
    }//end of construnctor
    
    /**
     * Oppretter soylediagram som viser antall aktive forsikringer eller skademeldinger (gitt av type) mellom min og max datoene.
     * @param type Spesifiserer om søylediagrammet skal vise forsikringer elelr skademeldinger
     * @param min Startdato for tidsintervallet det søkes i
     * @param max Ssluttdato for tidsintervallet det søkes i
     */
    private void opprettSoylediagram(String type, Calendar min, Calendar max) {
        CategoryAxis xAkse = new CategoryAxis();
        NumberAxis yAkse = new NumberAxis();
        bc = new BarChart<>(xAkse, yAkse);
        bc.setTitle("Antall aktive " + type.toLowerCase());
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
        GridPane.setMargin(bc, new Insets(25,0,0,0));
        add(bc,1,2);
    }//end of method opprettSoylediagram(String type, Calendar min, Calendar max)
    
    /**
     * Oppretter og lytter på knappen.
     */
    private void opprettKnapp() {
        oppdaterKnapp = new Button("Oppdater søylediagram");
        
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
            }//end of if
        });//end of setOnAction
    }//end of method opprettKnapp()
    
    /**
     * Legger komponentene ut i "Kontrollpanelet".
     */
    private void opprettKontrollPanel() {
        pane1.add(typeLabel, 1, 1);
        pane1.add(cb, 2, 1);
        GridPane.setHalignment(pane1, HPos.LEFT);
        
        beskrivelse.setFont(tekstStr);
        pane2.add(beskrivelse, 1, 1);
        GridPane.setMargin(iv, new Insets(0, 0, 0, 15));
        pane2.add(iv, 2, 1);
        
        pane.add(pane2, 1, 1, 4, 1);
        pane.add(fraLabel, 1, 2);
        pane.add(datePickerFra, 2, 2);
        GridPane.setMargin(tilLabel, new Insets(0, 0, 0, 20));
        pane.add(tilLabel, 3, 2);
        pane.add(datePickerTil, 4, 2);
        GridPane.setMargin(pane1, new Insets(18,0,0,0));
        pane.add(pane1, 1, 3, 4, 1);
        GridPane.setHalignment(oppdaterKnapp, HPos.RIGHT);
        GridPane.setMargin(oppdaterKnapp, new Insets(21, 0, 0, 20));
        pane.add(oppdaterKnapp, 4, 3);
        
        GridPane.setHalignment(pane, HPos.CENTER);
        
        pane.setVgap(5);
        pane1.setVgap(5);
        add(pane, 1, 1);
    }//end of method opprettKotrollPanel()
}//end of class SoylediagramLayout(kundeRegister) 
