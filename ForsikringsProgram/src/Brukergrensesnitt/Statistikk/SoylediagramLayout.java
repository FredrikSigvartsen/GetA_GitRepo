package Brukergrensesnitt.Statistikk;

import forsikringsprogram.Forsikring;
import forsikringsprogram.Kunderegister;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

/**
 *
 * @author Elias
 */
public class SoylediagramLayout extends GridPane{
    
    private DatePicker datePickerFra, datePickerTil;
    private StackedBarChart<String, Number> sbc;
    private GridPane pane, pane1;
    private Label typeLabel, fraLabel, tilLabel;
    private Button oppdaterKnapp;
    private ComboBox cb;
    private Kunderegister kundeRegister;
    
    public SoylediagramLayout(Kunderegister register) {
        kundeRegister = register;
        pane = new GridPane();
        pane.setAlignment(CENTER);
        pane1 = new GridPane();
        //pane1.setAlignment(CENTER);
        
        oppdaterKnapp = new Button("Oppdater graf");
        
        typeLabel = new Label("Forsikringer/Skademeldinger: ");
        fraLabel = new Label("Fra dato: ");
        tilLabel = new Label("Til dato: ");
        
        datePickerFra = new DatePicker();
        datePickerTil = new DatePicker();
        
        setHgap(120);
        opprettSoylediagram("Forsikringer");
        opprettComboBox();
        opprettKontrollPanel();
    }//end of construnctor
    
    public void opprettSoylediagram(String beskrivelse) {
        CategoryAxis xAkse = new CategoryAxis();
        NumberAxis yAkse = new NumberAxis();
        sbc = new StackedBarChart<>(xAkse, yAkse);
        sbc.setTitle("Antall " + beskrivelse.toLowerCase());
        xAkse.setLabel("Type");       
        yAkse.setLabel("Antall");    
        
        sbc.getData().clear();
        XYChart.Series serie1 = new XYChart.Series();
        serie1.setName(beskrivelse);
        serie1.getData().add(new XYChart.Data(Forsikring.BAAT, 5));
        serie1.getData().add(new XYChart.Data(Forsikring.BIL, 10));
        serie1.getData().add(new XYChart.Data(Forsikring.REISE, 7));
        serie1.getData().add(new XYChart.Data(Forsikring.BOLIG, 9));
       
        sbc.getData().add(serie1);
        sbc.setBorder(new Border(new BorderStroke(DARKGRAY,SOLID, new CornerRadii(5), THIN, new Insets(15))));
        sbc.setCategoryGap(40);
        add(sbc,1,2);
    }
    
    public void opprettComboBox() {
        cb = new ComboBox();
        ObservableList<String> forsikringer = FXCollections.observableArrayList("Forsikringer", "Skademeldinger");
        cb.setItems(forsikringer);
        cb.valueProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                sbc.getData().clear();
                getChildren().remove(sbc);
                opprettSoylediagram((String) t1);
                }
            
        });
        cb.setValue("Forsikringer");
    }
    
    public void opprettKontrollPanel() {
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
