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
    private Border kunderegistreringskant = new Border( new BorderStroke(DARKGRAY,SOLID, new CornerRadii(5), THIN, new Insets(15)) );
    private Border tegnforsikringskant = new Border( new BorderStroke(DARKGRAY,SOLID, new CornerRadii(5), THIN, new Insets(15)) );
    private Border sioppforsikringskant = new Border( new BorderStroke(DARKGRAY,SOLID, new CornerRadii(5), THIN, new Insets(15)) );
    private RegistrerKundeLayout kundeRegistrering;
    private TegnforsikringsLayout tegnForsikring;
    private SioppforsikringsLayout sioppForsikring;
    private Kunderegister kundeRegister;
    
    public ForsikringsbehandlingLayout(Kunderegister register){
        this.kundeRegister = register;
        opprettLayout();
    }
    
    private void opprettLayout(){
        kundeRegistrering = new RegistrerKundeLayout(kundeRegister);
        kundeRegistrering.autosize();
        kundeRegistrering.setBorder(kunderegistreringskant);
        kundeRegistrering.setHgap(20);
        kundeRegistrering.setVgap(10);
        kundeRegistrering.setMinWidth(560);
        kundeRegistrering.setMinHeight(350);
        kundeRegistrering.setMaxHeight(350);
        
        tegnForsikring = new TegnforsikringsLayout(kundeRegister);
        tegnForsikring.autosize();
        tegnForsikring.setBorder(tegnforsikringskant);
        tegnForsikring.setHgap(20);
        tegnForsikring.setVgap(10);
        tegnForsikring.setMinWidth(500);
        tegnForsikring.setMinHeight(550);
        
        sioppForsikring = new SioppforsikringsLayout(kundeRegister);
        sioppForsikring.autosize();
        sioppForsikring.setBorder(sioppforsikringskant);
        sioppForsikring.setHgap(20);
        sioppForsikring.setVgap(10);
        sioppForsikring.setMinWidth(560);
        sioppForsikring.setMinHeight(200);     
        
        add(kundeRegistrering, 1, 1, 1, 3);
        add(tegnForsikring, 2, 3, 1, 3);
        add(sioppForsikring, 1, 5, 1, 3);
        
        
    }//end of method opprettLayout()

}
