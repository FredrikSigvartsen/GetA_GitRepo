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
import javafx.scene.layout.GridPane;

/**
 *
 * @author Jens
 */
public class ForsikringsbehandlingLayout extends GridPane {
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
        kundeRegistrering.setBorder(GUI.KANTLINJE);
        kundeRegistrering.setHgap(20);
        kundeRegistrering.setVgap(10);
        kundeRegistrering.setMinWidth(500);
        kundeRegistrering.setMaxHeight(400);
        
        tegnForsikring = new TegnforsikringsLayout(kundeRegister);
        tegnForsikring.autosize();
        tegnForsikring.setBorder(GUI.KANTLINJE);
        tegnForsikring.setHgap(20);
        tegnForsikring.setVgap(10);
        tegnForsikring.setMinWidth(500);
        tegnForsikring.setMinHeight(300);
        tegnForsikring.setMaxHeight(400);
        
        sioppForsikring = new SioppforsikringsLayout(kundeRegister);
        sioppForsikring.autosize();
        sioppForsikring.setBorder(GUI.KANTLINJE);
        sioppForsikring.setHgap(20);
        sioppForsikring.setVgap(10);
        sioppForsikring.setMinWidth(500);
        sioppForsikring.setMaxHeight(400);     
        
        add(kundeRegistrering, 1, 1);
        add(tegnForsikring, 2, 1);
        add(sioppForsikring, 3, 1);
        
        
    }//end of method opprettLayout()

}
