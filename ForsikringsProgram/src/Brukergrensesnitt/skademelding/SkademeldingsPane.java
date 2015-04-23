/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt.skademelding;


import javafx.geometry.Insets;
import javafx.scene.layout.*;

/**
 *
 * @author fredr_000
 */
public class SkademeldingsPane extends BorderPane {
    
    RegistrerSkadeLayout registrerLayout;
    public SkademeldingsPane(){
        registrerLayout = new RegistrerSkadeLayout();
        
        setCenter(registrerLayout);
        
    }
}
