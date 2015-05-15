/**
 *
 * @author Elias
 */
package Brukergrensesnitt.Statistikk;
import forsikringsprogram.*;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * Klassen er et gridpane som inneholder to diagrammer med hvert sitt kontrollpanel. Disse brukes til å vise statistikk.
 * Siste versjon skrevet: 15.05.2015 23:17
 * @author Elias Andreassen Thøgersen, Informasjonsteknologi, s236603
 */
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
        setAlignment(Pos.CENTER);
    }//end of constructor
    
    /**
     * Oppretter og setter de ulike layoutene under Kundebehandling
     */
    public void opprettLayout(){
        GridPane.setMargin(soylediagram, new Insets(0,34,0,0));
        add(soylediagram,1,1);
        add(new Separator(Orientation.VERTICAL), 2, 1);
        GridPane.setMargin(graf, new Insets(0,0,0,30));
        add(graf,3,1);
    }//end of method opprettLayout()   
}//end of class KundePane
