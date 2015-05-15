package Brukergrensesnitt.kundebehandling;
import Brukergrensesnitt.*;
import forsikringsprogram.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Dette Layout'et er et BorderPane-layout. 
 * Hensikten med denne klassen er å fikse navigeringen mellom layout'ene "Behandle forsikringer", "Registrer Skademelding" og "Søk". 
 * Navigasjonsbaren er fast hvis brukeren er i GUI sin fane "Kundebehandling"
 * Siste versjon skrevet: 13/05/15 12:00
 * @author Jens Omfjord, Informasjonsteknologi. s236641
 */
public class KundePane extends BorderPane{
    
    private HBox kundebehandlingsMeny;
    private TabPane kundebehandlingsPanel;
    private KundesokLayout sokPane;
    private RegistrerSkadeLayout registrerSkademeldingLayout;
    private ForsikringsbehandlingLayout forsikringsBehandlingLayout;
    private Kunderegister kundeRegister;
    private String faneString = "Behandle forsikring";
    private Font faneFont = Font.font(null, FontWeight.BOLD, 18);
        
    /**
     * Oppretter layoutet som skal ligge i dette BorderPane-layout'et
     * @param register Kunderegisteret som all data skal lagres i. 
     */
    public KundePane(Kunderegister register) {
        super();
        kundeRegister = register;
        kundebehandlingsFaner();
        opprettLayout();
        //this.kundeRegister = register;
    }//end of constructor
    
    /**
     * Oppretter og setter de ulike layoutene under Kundebehandling
     */
    private void opprettLayout(){
        kundebehandlingsMeny = kundebehandlingsFaner();
        
        forsikringsBehandlingLayout = new ForsikringsbehandlingLayout(kundeRegister);
        registrerSkademeldingLayout = new RegistrerSkadeLayout(kundeRegister);
        registrerSkademeldingLayout.autosize();
        sokPane = new KundesokLayout(kundeRegister);
        sokPane.autosize();
        
        //Setter plassering
        setTop(kundebehandlingsMeny);
        setCenter(forsikringsBehandlingLayout);
        setMargin(forsikringsBehandlingLayout, new Insets(40));
    }//end of method opprettLayout()
    
    /**
     * Oppretter fanene for Kundebehandlings fanen
     * @return En HBox med kundebehandlingsfaner. 
     */
    private HBox kundebehandlingsFaner(){
        HBox box = new HBox();
        kundebehandlingsPanel = new TabPane();
        kundebehandlingsPanel.setTabMinHeight(30);
        kundebehandlingsPanel.setTabMaxHeight(30);
        kundebehandlingsPanel.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        
        //Legger inn bilde og fikser plassering for "Behandle forsikringer"-fane
        Tab forsikringsBehandlingFane = new Tab();
        GridPane forsikringsBehandlingPane = new GridPane();
        Label forsikringsBehandlingLabel = new Label("Behandle forsikringer");
        forsikringsBehandlingLabel.setFont(faneFont);
        Image forsikringsBehandlingBilde = new Image(getClass().getResourceAsStream("/Bilder/forsikringsBehandling_icon.png"));
        ImageView iv = new ImageView(forsikringsBehandlingBilde);
        iv.setFitWidth(28);
        iv.setFitHeight(28);
        forsikringsBehandlingPane.add(forsikringsBehandlingLabel,1,1);
        GridPane.setMargin(iv, new Insets(0,0,0,15));
        forsikringsBehandlingPane.add(iv,2,1);
        forsikringsBehandlingFane.setGraphic(forsikringsBehandlingPane);
        forsikringsBehandlingFane.setId("forsikringsbehandling");
        kundebehandlingsPanel.getTabs().add(forsikringsBehandlingFane);
        
        //Legger inn bilde og fikser plassering for "Registrer skademelding"-fane
        Tab registrerSkademeldingFane = new Tab();
        GridPane registrerSkademeldingPane = new GridPane();
        Label registrerSkademeldingLabel = new Label("Registrer skademelding");
        registrerSkademeldingLabel.setFont(faneFont);
        Image registrerSkademeldingBilde = new Image(getClass().getResourceAsStream("/Bilder/registrerSkademelding_icon.png"));
        ImageView iv1 = new ImageView(registrerSkademeldingBilde);
        iv1.setFitWidth(28);
        iv1.setFitHeight(28);
        registrerSkademeldingPane.add(registrerSkademeldingLabel,1,1);
        GridPane.setMargin(iv1, new Insets(0,0,0,15));
        registrerSkademeldingPane.add(iv1,2,1);
        registrerSkademeldingFane.setGraphic(registrerSkademeldingPane);
        registrerSkademeldingFane.setId("skaderegistrering");
        kundebehandlingsPanel.getTabs().add(registrerSkademeldingFane);
        
        //Legger inn bilde og fikser plassering for søk-fane
        Tab sokFane = new Tab();
        GridPane sokePane = new GridPane();
        Label sokLabel = new Label("Søk");
        sokLabel.setFont(faneFont);
        Image sokBilde = new Image(getClass().getResourceAsStream("/Bilder/sok_icon.png"));
        ImageView iv2 = new ImageView(sokBilde);
        iv2.setFitWidth(28);
        iv2.setFitHeight(28);
        sokePane.add(sokLabel,1,1);
        GridPane.setMargin(iv2, new Insets(0,0,0,15));
        sokePane.add(iv2,2,1);
        sokFane.setGraphic(sokePane);
        sokFane.setId("sok");
        kundebehandlingsPanel.getTabs().add(sokFane);
        //kundebehandlingsPanel.setRotate(90);
        
        box.getChildren().add(kundebehandlingsPanel);
        
        return box;
    }//end of method kundebehandlingsFaner()
    
    /**
     * Legger til en litter på fanekortene i kundebehandlingsPanelet
     */
    public void tabLytter(){
        kundebehandlingsPanel.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Tab> ov, Tab t, Tab t1) -> {
        switch (t1.getId()) {
            case "forsikringsbehandling":
                setCenter(forsikringsBehandlingLayout);
                setMargin(forsikringsBehandlingLayout, new Insets(40));
                GUI.setTittel("Forsikringsprogram - Kundebehandling - Behandle forsikring");
                faneString = "Behandle forsikring";
                break;
            case "sok":   
                setCenter(sokPane);
                setMargin(sokPane, new Insets(40));
                GUI.setTittel("Forsikringsprogram - Kundebehandling - Søk");
                faneString = "Søk";
                break;
            case "skaderegistrering":
                setCenter(registrerSkademeldingLayout);
                setMargin(registrerSkademeldingLayout, new Insets(40));
                GUI.setTittel("Forsikringsprogram - Kundebehandling - Registrer skademelding");
                faneString = "Registrer skademelding";
                break;
            default:
                setCenter(forsikringsBehandlingLayout);
                setMargin(forsikringsBehandlingLayout, new Insets(40));
                GUI.setTittel("Forsikringsprogram - Kundebehandling - Behandle forsikring");
                faneString = "Behandle forsikring";
            }// end of switch case
        });
    }//end of method tabLytter()
    
    /**
     * Oppretter en VBox som er en overskrift med innhold fra parameterlisten. 
     * @param overskrift Teksten som overskriften skal inneholde 
     * @param strl Størrelse på teksten som overskriften skal inneholde
     * @return En VBox med overskrift, og en separator, Altså en "stylet" overskrift. 
     */
    public static VBox overskrift(String overskrift, int strl){
        VBox returOverskrift = new VBox();
        Label overskriftsLabel = new Label(overskrift);
        overskriftsLabel.setFont(Font.font(null, FontWeight.BOLD, strl));
        Separator skille = new Separator( Orientation.HORIZONTAL );
        returOverskrift.getChildren().addAll( overskriftsLabel, skille);
        return returOverskrift;
    }// end of method overskrift
    
    /**
     * 
     * @return returnerer kundebehandlingsPanelet
     */
    public TabPane getKundebehandlingsPanel(){
        return kundebehandlingsPanel;
    }//end of method getKundebehandlingsPanel()
    
    /**
     * 
     * @return returnerer en String som inneholder navnet til den valgte kundebehandlingsfanen
     */
    public String getFaneString(){
        return faneString;
    }//end of method getFaneString
        
    
}//end of class KundePane
