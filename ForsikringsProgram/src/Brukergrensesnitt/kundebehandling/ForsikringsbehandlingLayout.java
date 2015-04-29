/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt.kundebehandling;

import Brukergrensesnitt.kundebehandling.*;
import Brukergrensesnitt.GUI;
import forsikringsprogram.*;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Jens
 */
public class ForsikringsbehandlingLayout extends GridPane {
    private Kunderegister kundeRegister;
    
    public ForsikringsbehandlingLayout(Kunderegister register){
        this.kundeRegister = register;
    }
}
