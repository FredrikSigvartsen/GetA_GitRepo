/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Brukergrensesnitt.Statistikk;

/**
 *
 * @author Elias
 */
import Brukergrensesnitt.*;
import forsikringsprogram.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class StatistikkPane extends BorderPane {
    
    private SoylediagramLayout soylediagram;
    private Kunderegister kundeRegister;
        
    public StatistikkPane(Kunderegister register) {
        super();
        kundeRegister = register;
        soylediagram = new SoylediagramLayout(kundeRegister);
        opprettLayout();
        //this.kundeRegister = register;
    }//end of constructor
    
    /**
     * Oppretter og setter de ulike layoutene under Kundebehandling
     */
    public void opprettLayout(){
        setCenter(soylediagram);
    }//end of method opprettLayout()   
}//end of class KundePane
