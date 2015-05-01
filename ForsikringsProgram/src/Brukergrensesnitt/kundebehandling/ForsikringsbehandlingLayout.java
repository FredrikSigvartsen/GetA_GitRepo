/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt.kundebehandling;

import Brukergrensesnitt.kundebehandling.*;
import Brukergrensesnitt.GUI;
import forsikringsprogram.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import static javafx.scene.layout.BorderStroke.THIN;
import static javafx.scene.layout.BorderStrokeStyle.SOLID;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import static javafx.scene.paint.Color.DARKGRAY;

/**
 *
 * @author Jens
 */
public class ForsikringsbehandlingLayout extends GridPane {
    private Border kunderegistreringskant = new Border( new BorderStroke(DARKGRAY,SOLID, new CornerRadii(5), THIN, new Insets(0)) );
    private Border tegnforsikringskant = new Border( new BorderStroke(DARKGRAY,SOLID, new CornerRadii(5), THIN, new Insets(0)) );
    private Border sioppforsikringskant = new Border( new BorderStroke(DARKGRAY,SOLID, new CornerRadii(5), THIN, new Insets(0)) );
    private RegistrerKundeLayout kundeRegistrering;
    private TegnforsikringsLayout tegnForsikring;
    private SioppforsikringsLayout sioppForsikring;
    private Kunderegister kundeRegister;
    
    public ForsikringsbehandlingLayout(Kunderegister register){
        this.kundeRegister = register;
        opprettLayout();
    }
    
    private void opprettLayout(){
        
        //Registrer kunde - layout
        kundeRegistrering = new RegistrerKundeLayout(kundeRegister);
        kundeRegistrering.setBorder(kunderegistreringskant);
        kundeRegistrering.setHgap(20);
        kundeRegistrering.setVgap(10);
        
        kundeRegistrering.setMinWidth( GUI.getSkjermBredde()/ 3 );
        kundeRegistrering.setMaxWidth( GUI.getSkjermBredde()/ 3 );
        kundeRegistrering.setPrefWidth( GUI.getSkjermBredde()/ 3 );
        
        kundeRegistrering.setMinHeight( GUI.getSkjermHoyde()/ 2.2 );
        kundeRegistrering.setPrefHeight( GUI.getSkjermHoyde()/ 2.2 );
        kundeRegistrering.setMaxHeight( GUI.getSkjermHoyde()/ 2.2 );
        
        
        //Tegn forsikring - layout
        tegnForsikring = new TegnforsikringsLayout(kundeRegister);
        tegnForsikring.setBorder(tegnforsikringskant);
        tegnForsikring.setHgap(20);
        tegnForsikring.setVgap(10);
        
        tegnForsikring.setMinWidth( GUI.getSkjermBredde()/ 2.7);
        tegnForsikring.setMaxWidth( GUI.getSkjermBredde()/ 2.7);
        tegnForsikring.setPrefWidth( GUI.getSkjermBredde()/ 2.7);
        
        tegnForsikring.setMinHeight( GUI.getSkjermHoyde()/ 1.3);
        tegnForsikring.setMaxHeight( GUI.getSkjermHoyde()/ 1.3);
        tegnForsikring.setPrefHeight( GUI.getSkjermHoyde()/ 1.3);
        
        
        //Si opp forsikring - layout 
        sioppForsikring = new SioppforsikringsLayout(kundeRegister);
        sioppForsikring.setBorder(sioppforsikringskant);
        sioppForsikring.setHgap(20);
        sioppForsikring.setVgap(10);
        sioppForsikring.setMinWidth( GUI.getSkjermBredde()/ 3);
        sioppForsikring.setMaxWidth( GUI.getSkjermBredde()/ 3);
        sioppForsikring.setPrefWidth( GUI.getSkjermBredde()/ 3);
        
        sioppForsikring.setMinHeight( GUI.getSkjermHoyde()/ 4);     
        
        add(kundeRegistrering, 1, 1, 1, 3);
        add(sioppForsikring, 1, 5, 1, 3);
        add(tegnForsikring, 2, 3, 1, 3);
        
        setVgap( GUI.getSkjermHoyde()/ 70);
        setHgap( GUI.getSkjermBredde()/ 50);
//        setMargin()
        
    }//end of method opprettLayout()

}
