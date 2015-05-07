/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt.Statistikk;

import forsikringsprogram.Forsikring;
import forsikringsprogram.Kunderegister;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

/**
 *
 * @author Elias
 */
public class SoylediagramLayout extends GridPane{
    
    private StackedBarChart<String, Number> sbc;
    private GridPane pane;
    private Label aarLabel;
    private ComboBox cb;
    private Kunderegister kundeRegister;
    
    public SoylediagramLayout(Kunderegister register) {
        this.kundeRegister = register;
        pane = new GridPane();
        pane.setAlignment(CENTER);
        this.aarLabel = new Label("År: ");
        setHgap(120);
        opprettSoylediagram("2015");
        opprettComboBox();
    }//end of construnctor
    
    public void opprettSoylediagram(String aarstall) {
        CategoryAxis xAkse = new CategoryAxis();
        NumberAxis yAkse = new NumberAxis();
        sbc = new StackedBarChart<>(xAkse, yAkse);
        sbc.setTitle("Antall forsikringer");
        xAkse.setLabel("Forsikringstype");       
        yAkse.setLabel("Antall");    
        
        sbc.getData().clear();
        XYChart.Series serie1 = new XYChart.Series();
        serie1.setName(aarstall);
        serie1.getData().add(new XYChart.Data(Forsikring.BAAT, kundeRegister.antallForsikringAvType(Forsikring.BAAT, aarstall)));
        serie1.getData().add(new XYChart.Data(Forsikring.BIL, kundeRegister.antallForsikringAvType(Forsikring.BIL, aarstall)));
        serie1.getData().add(new XYChart.Data(Forsikring.REISE, kundeRegister.antallForsikringAvType(Forsikring.REISE, aarstall)));
        serie1.getData().add(new XYChart.Data(Forsikring.BOLIG, kundeRegister.antallForsikringAvType(Forsikring.BOLIG, aarstall)));
       
        sbc.getData().add(serie1);
        sbc.setBorder(new Border(new BorderStroke(DARKGRAY,SOLID, new CornerRadii(5), THIN, new Insets(15))));
        sbc.setCategoryGap(40);
        add(sbc,1,2);
    }
    
    public void opprettComboBox() {
        this.cb = new ComboBox();
        ObservableList<String> forsikringer = FXCollections.observableArrayList(
                                              "2015", "2016",
                                              "2017", "2018", "2019", "2020");
        this.cb.setItems(forsikringer);
        this.cb.valueProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                sbc.getData().clear();
                getChildren().remove(sbc);
                opprettSoylediagram((String) t1);
                }
            
        });
        this.cb.setValue("2015");
        
        this.pane.add(this.aarLabel,1,1);
        this.pane.add(this.cb,2,1);
        GridPane.setHalignment(this.pane, HPos.CENTER);
        add(this.pane, 1,1);
    }
}   
