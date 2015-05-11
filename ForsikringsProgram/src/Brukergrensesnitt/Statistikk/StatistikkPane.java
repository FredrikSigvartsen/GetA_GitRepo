/**
 *
 * @author Elias
 */
package Brukergrensesnitt.Statistikk;
import Brukergrensesnitt.*;
import forsikringsprogram.*;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

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
        //setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
    }//end of constructor
    
    /**
     * Oppretter og setter de ulike layoutene under Kundebehandling
     */
    public void opprettLayout(){
        GridPane.setMargin(soylediagram, new Insets(0,20,0,0));
        add(soylediagram,1,1);
        add(new Separator(Orientation.VERTICAL), 2, 1);
        GridPane.setMargin(graf, new Insets(0,0,0,20));
        add(graf,3,1);
        setVgap(70);
    }//end of method opprettLayout()   
}//end of class KundePane
