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

public class StatistikkPane extends GridPane {
    
    private GrafLayout graf;
    private SoylediagramLayout soylediagram;
    private Kunderegister kundeRegister;
        
    public StatistikkPane(Kunderegister register) {
        super();
        kundeRegister = register;
        soylediagram = new SoylediagramLayout(kundeRegister);
        graf = new GrafLayout(kundeRegister);
        opprettLayout();
    }//end of constructor
    
    /**
     * Oppretter og setter de ulike layoutene under Kundebehandling
     */
    public void opprettLayout(){
        add(soylediagram,1,1);
        add(graf,2,1);
        setVgap(70);
    }//end of method opprettLayout()   
}//end of class KundePane
