package Brukergrensesnitt.kundebehandling;
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
 * Hensikten med denne klassen er å samle layoutene siOppforsikringsLayout, RegistrerKundeLayout og TegnforsikringsLayout i et eget layout,
 * og fikse kantlinjer rundt disse. 
 * Siste versjon skrevet: 7/05/15 12:00
 * @author Jens Omfjord, Informasjonsteknologi, s236641
 */

public class ForsikringsbehandlingLayout extends GridPane {
    
    private RegistrerKundeLayout kundeRegistrering;
    private TegnforsikringsLayout tegnForsikring;
    private SioppforsikringsLayout sioppForsikring;
    private Kunderegister kundeRegister;
    
    /**
     * 
     * @param register Kunderegisteret forsikringene og kundene skal registreres i, og forsikringene skal sies opp fra. 
     */
    public ForsikringsbehandlingLayout(Kunderegister register){
        this.kundeRegister = register;
        opprettLayout();
        setAlignment( Pos.TOP_CENTER );
    }//end of constructor
    
    /**
     * Oppretter Forsikringsbehandlig Layouten ved å opprette objekter av RegistrerKundeLayout, TegnforsikringsLayout, SioppforsikringsLayout klassene
     */
    private void opprettLayout(){
        
        //Registrer kunde - layout
        kundeRegistrering = new RegistrerKundeLayout(kundeRegister);
        kundeRegistrering.setBorder(new Border( new BorderStroke(DARKGRAY,SOLID, new CornerRadii(5), THIN, new Insets(15)) ));
        
        kundeRegistrering.setMinWidth( GUI.getSkjermBredde() / 3);
        kundeRegistrering.setMaxWidth( GUI.getSkjermBredde() / 3);
        kundeRegistrering.setPrefWidth( GUI.getSkjermBredde() / 3);
        
        kundeRegistrering.setMinHeight( GUI.getSkjermHoyde() / 2.5);
        kundeRegistrering.setPrefHeight( GUI.getSkjermHoyde() / 2.5);
        kundeRegistrering.setMaxHeight( GUI.getSkjermHoyde() / 2.5);
        
        
        //Tegn forsikring - layout
        tegnForsikring = new TegnforsikringsLayout(kundeRegister);
        tegnForsikring.setBorder(new Border( new BorderStroke(DARKGRAY,SOLID, new CornerRadii(5), THIN, new Insets(15)) ));
        
        tegnForsikring.setMinWidth( GUI.getSkjermBredde() / 3);
        tegnForsikring.setMaxWidth( GUI.getSkjermBredde() / 3);
        tegnForsikring.setPrefWidth( GUI.getSkjermBredde() / 3);
        
        tegnForsikring.setMinHeight( GUI.getSkjermHoyde() / 1.70);
        tegnForsikring.setMaxHeight( GUI.getSkjermHoyde() / 1.70);
        tegnForsikring.setPrefHeight( GUI.getSkjermHoyde() / 1.70);
        
        
        //Si opp forsikring - layout 
        sioppForsikring = new SioppforsikringsLayout(kundeRegister);
        sioppForsikring.setBorder(new Border( new BorderStroke(DARKGRAY,SOLID, new CornerRadii(5), THIN, new Insets(15)) ));
        
        sioppForsikring.setMinWidth( GUI.getSkjermBredde() / 3);
        sioppForsikring.setMaxWidth( GUI.getSkjermBredde() / 3);
        sioppForsikring.setPrefWidth( GUI.getSkjermBredde() / 3);
        
        sioppForsikring.setMinHeight( GUI.getSkjermHoyde() / 5.25);
        sioppForsikring.setMaxHeight( GUI.getSkjermHoyde() / 5.25);  
        sioppForsikring.setPrefHeight( GUI.getSkjermHoyde() / 5.25);       
        
        add(kundeRegistrering, 1, 1, 1, 3);
        add(sioppForsikring, 1, 5, 1, 3);
        add(tegnForsikring, 2, 3, 1, 3);
        setPadding( new Insets( 30, 0 , 0, 0 ));
    }//end of method opprettLayout()
}//end of class
