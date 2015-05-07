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

/**
 *
 * @author Elias
 */
public class GrafLayout extends GridPane{
    
    private ComboBox cb;
    private Button knapp;
    private GridPane pane;
    private LineChart<String,Number> lc;
    private Kunderegister kundeRegister;
    
    public GrafLayout(Kunderegister register) {
        this.kundeRegister = register;
        
        pane = new GridPane();
        pane.setAlignment(CENTER);
        opprettGrafMedAlle();
        opprettKnapp();
        opprettComboBox();
    }//end of construnctor
    
    public void opprettGrafMedAlle() {
        
        CategoryAxis xAkse = new CategoryAxis();
        NumberAxis yAkse = new NumberAxis();
        this.lc = new LineChart<>(xAkse,yAkse);
        xAkse.setLabel("Måned");       
        yAkse.setLabel("Antall");   
        this.lc.setTitle("Øking/Minking");
        
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
        
        this.lc.getData().addAll(serie1, serie2, serie3);
        this.lc.setBorder(new Border(new BorderStroke(DARKGRAY,SOLID, new CornerRadii(5), THIN, new Insets(15))));
        add(lc,1,2);
    }
    
    public void opprettForsikringsGraf() {
        CategoryAxis xAkse = new CategoryAxis();
        NumberAxis yAkse = new NumberAxis();
        this.lc = new LineChart<>(xAkse,yAkse);
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
        
        this.lc.getData().add(serie2);
        this.lc.setBorder(new Border(new BorderStroke(DARKGRAY,SOLID, new CornerRadii(5), THIN, new Insets(15))));
        add(this.lc,1,2);
    }
    
    public void opprettSkademeldingGraf() {
        CategoryAxis xAkse = new CategoryAxis();
        NumberAxis yAkse = new NumberAxis();
        this.lc = new LineChart<>(xAkse,yAkse);
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
        
        this.lc.getData().add(serie1);
        this.lc.setBorder(new Border(new BorderStroke(DARKGRAY,SOLID, new CornerRadii(5), THIN, new Insets(15))));
        add(this.lc,1,2);
    }
    
    public void opprettKundeGraf() {
        CategoryAxis xAkse = new CategoryAxis();
        NumberAxis yAkse = new NumberAxis();
        this.lc = new LineChart<>(xAkse,yAkse);
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
        
        this.lc.getData().add(serie3);
        this.lc.setBorder(new Border(new BorderStroke(DARKGRAY,SOLID, new CornerRadii(5), THIN, new Insets(15))));
        add(this.lc,1,2);
    }
    
    public void opprettKnapp() {
        this.knapp = new Button("Vis en og en");
        
        this.knapp.setOnAction((ActionEvent e) -> {
            if(this.cb.isDisable()) {
                if(this.cb.getValue().equals("Kunder")) {
                    getChildren().remove(lc);
                    opprettKundeGraf();
                } else if(this.cb.getValue().equals("Forsikringer")) {
                    getChildren().remove(lc);
                    opprettForsikringsGraf();
                } else if(this.cb.getValue().equals("Skademeldinger")) {
                    getChildren().remove(lc);
                    opprettSkademeldingGraf();
                }
                    
                this.cb.setDisable(false);
                this.knapp.setText("Vis alle");
            }
            else {
                getChildren().remove(lc);
                opprettGrafMedAlle();
                this.cb.setDisable(true);
                this.knapp.setText("Vis en og en");
            }
        });
        
        this.pane.add(this.knapp,2,1);
    }
    
    public void opprettComboBox() {
        this.cb = new ComboBox();
        ObservableList<String> forsikringer = FXCollections.observableArrayList(
                                              "Forsikringer", "Skademeldinger",
                                              "Kunder");
        this.cb.setItems(forsikringer);
        this.cb.valueProperty().addListener(new ChangeListener<String>(){
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
            
        });
        this.cb.setValue("Forsikringer");
        this.cb.setDisable(true);
        
        this.pane.add(this.cb,1,1);
        GridPane.setHalignment(this.pane, HPos.CENTER);
        add(this.pane, 1,1);
    }
}   
