package Brukergrensesnitt.kundebehandling;
import Brukergrensesnitt.GUI;
import forsikringsprogram.*;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.*;

/**
 * Denne klassen er et layout for si opp forsikringer. Her fyller brukeren inn fødselsnummer og dette valideres, 
 * før det vises et vindu med kundens aktive forsikringer. Så kan brukeren krysse av de forsikringene en skulle ønske å si opp
 * Siste versjon skrevet: 13/05/15 17:10
 * @author Jens Omfjord, Informasjonsteknologi, s236641
 */
public class SioppforsikringsLayout extends GridPane{
    
    private TextField fodselsnr;    
    private Label fodselsnrFeil;
    private Button siOppForsikring;
    private Kunderegister kundeRegister;
    
    /**
     * Initialiserer datafeltene. 
     * @param register Kunderegisteret forsikringene skal sies opp fra. 
     */
    public SioppforsikringsLayout(Kunderegister register){
        opprettSiOppLayout();
        siOppForsikring();
        this.kundeRegister = register;
        tekstFeltLyttere();
    }//end of constructor
    
    /**
     * Oppretter et lite layout for oppsigelse av forsikring. 
     */
    public void opprettSiOppLayout(){
        
        siOppForsikring = new Button("Si opp forsikring");
        
        fodselsnr = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        fodselsnrFeil = new Label("*");
        
        setVgap(10);
        setHgap(10);
        
        //legger til kolonne 1
        add(KundePane.overskrift("Tegn forsikring", 20), 1, 1, 2, 1);
        add(new Label("Fødselsnummer for oppsigelse:"), 1, 2);
        GridPane.setHalignment(siOppForsikring, HPos.CENTER);
        add(siOppForsikring, 1, 3, 2, 1);
        
        //legger til kolonne 2
        add(fodselsnr, 2, 2);
        
        //legger til kolonne 3
        add(fodselsnrFeil, 3, 2);
    }//end of methd opprettSiOppLayout()
    
    /**
     * Oppretter et vindu med kundens aktive forsikringer
     * @param forsikringBoks array med checkboxer som kan legges ut i vinduet
     * @param valgPane GridPane med innholdet til vinduet
     * @param fodselsnrForsikringer kundens fødselsnummer
     */
    private void visForsikringValg(CheckBox[] forsikringBoks, GridPane valgPane, String fodselsnrForsikringer){
        Alert valg = new Alert(AlertType.CONFIRMATION);
        valg.setTitle("Si opp forsikringer");
        valg.setHeaderText("");
        valg.setGraphic(valgPane);
        
        ButtonType siOppKnapp = new ButtonType("Si opp forsikringer");
        ButtonType avbrytKnapp = new ButtonType("Avbryt", ButtonData.CANCEL_CLOSE);
        
        valg.getButtonTypes().setAll(siOppKnapp, avbrytKnapp);
        
        valg.getDialogPane().setPrefWidth(550);
        valg.getDialogPane().setMinHeight(200);
        valg.setResizable(true);
        valg.initOwner(GUI.getStage());
        Optional<ButtonType> handling = valg.showAndWait();
        
        int antallAvkrysset = 0;
        int[] avtalenr = new int[forsikringBoks.length];
        if( handling.isPresent() && handling.get() == siOppKnapp){
            for(int j = 0; j < forsikringBoks.length; j++){
                if(forsikringBoks != null && forsikringBoks[j].isSelected()){
                    avtalenr[j] = Integer.parseInt(forsikringBoks[j].getId());
                    antallAvkrysset++;
                }
            }
            
            if(antallAvkrysset == 0){
                GUI.visInputFeilMelding("Feil i valg", "Velg en eller flere forsikringer som skal sies opp, eller trykk avbryt");
            }
            else{
                siOppForsikringer(avtalenr, fodselsnrForsikringer);
            }
        }
    }// end of method visForsikringValg() 
    
    /**
     * Oppretter et GridPane-layout med en beskrivende tekst, og en dynamisk checkbox som avhenger av hva slags
     * liste som blir sendt med i parameterlisten. 
     * @param fodselsnrForsikringer Fødselsnummeret til kunden som det skal sies opp forsikringer på
     * @param kundensForsikringer En liste med forsikringene til den valgte kunden som skal vises i checkboxen
     */
    private void opprettForsikringValgLayout(String fodselsnrForsikringer, List<Forsikring> kundensForsikringer){
        GridPane forsikringsValg = new GridPane();
        if(kundensForsikringer.isEmpty()){
            GUI.visInputFeilMelding("Ingen forsikringer", "Kunden har ingen aktive forsikringer");
            setTommeFelter();
            return;
        }// end of if
        
        ForsikringsKunde kunde = kundeRegister.finnKunde(fodselsnrForsikringer);
        Label beskrivelse = new Label( kunde.getEtternavn() + ", " + kunde.getFornavn() + " har følgende forsikringer.\nKryss av de forsikringene du vil si opp:");
        
        forsikringsValg.add(beskrivelse, 1, 1, 2, 2);
        forsikringsValg.setVgap(15);
        
        int i = 0;
        
        CheckBox[] forsikringValgBoks = new CheckBox[kundensForsikringer.size()];
        
        Iterator<Forsikring> fIter = kundensForsikringer.listIterator();
        /**
         * Oppretter checkboxer for alle de aktive forsikringene til kunden
         */
        while(fIter.hasNext()){
            Forsikring gjeldende = fIter.next();
            if(gjeldende instanceof Bilforsikring){
                Bilforsikring bil = (Bilforsikring) gjeldende;
                forsikringValgBoks[i] = new CheckBox("Avtalenummer " + String.valueOf(gjeldende.getAvtaleNr()) + ": Bilforsikring, " +  bil.getMerke() + 
                        " " + bil.getModell() + ", " + bil.getRegistreringsNr() + ", " + gjeldende.getBetingelser() + ".");
                forsikringValgBoks[i].setId(String.valueOf(gjeldende.getAvtaleNr()));
                forsikringsValg.add(forsikringValgBoks[i], 1, (i + 3));
            }
            else if(gjeldende instanceof Baatforsikring){
                Baatforsikring bat = (Baatforsikring) gjeldende;
                forsikringValgBoks[i] = new CheckBox("Avtalenummer " + String.valueOf(gjeldende.getAvtaleNr()) + ": Båtforsikring, " +  bat.getMerke() + 
                        " " + bat.getModell() + ", " + bat.getRegistreringsNr() + ", " + gjeldende.getBetingelser() + ".");
                forsikringValgBoks[i].setId(String.valueOf(gjeldende.getAvtaleNr()));
                forsikringsValg.add(forsikringValgBoks[i], 1, (i + 3));
            }
            else if(gjeldende instanceof Boligforsikring){
                Boligforsikring bolig = (Boligforsikring) gjeldende;
                forsikringValgBoks[i] = new CheckBox("Avtalenummer " + String.valueOf(gjeldende.getAvtaleNr()) + ": Boligforsikring, " + bolig.getGateAdresse() + 
                        ", " + bolig.getPostNr() + ", " + bolig.getAntallKvm() + "KVM, "  + gjeldende.getBetingelser() + ".");
                forsikringValgBoks[i].setId(String.valueOf(gjeldende.getAvtaleNr()));
                forsikringsValg.add(forsikringValgBoks[i], 1, (i + 3));
            }
            else if(gjeldende instanceof Reiseforsikring){
                Reiseforsikring reise = (Reiseforsikring) gjeldende;
                forsikringValgBoks[i] = new CheckBox("Avtalenummer " + String.valueOf(gjeldende.getAvtaleNr()) + ": Reiseforsikring, " + reise.getOmraade() + 
                        ", " + gjeldende.getBetingelser() + ".");
                forsikringValgBoks[i].setId(String.valueOf(gjeldende.getAvtaleNr()));
                forsikringsValg.add(forsikringValgBoks[i], 1, (i + 3));
            }
            i++;
        }//end of while
        
        visForsikringValg(forsikringValgBoks, forsikringsValg, fodselsnrForsikringer);
        
    }//end of method opprettForsikringValgLayout()
    
    /**
     * Sier opp alle forsikringer brukeren har valgt i dialog-vinduet.
     * @param avtalenr En array av typen integer som inneholder avtalenummere som skal sies opp
     * @param fodselsnrOppsigelse fødselsnummeret til forsikringskunden som forsikringene sies op fra
     */
    private void siOppForsikringer(int[] avtalenr, String fodselsnrOppsigelse){
        StringBuilder output = new StringBuilder();
        for(int k = 0; k < avtalenr.length; k++){
            if(avtalenr[k] > 0){
                kundeRegister.siOppForsikring(fodselsnrOppsigelse, avtalenr[k]);
                output.append("Forsikringsnummer ").append(avtalenr[k]).append(" er nå sagt opp\n\n");
            }
        }
        GUI.visInputFeilMelding("Forsikringer sagt opp", output.toString());
        setTommeFelter();
    }// end of method siOppForsikringer()
    
    /**
     * Sjekker input fra brukeren opp mot RegEx og gir umidelbar tilbakemelding på om inputen godkjennes eller evt hva som må endres
     * @return Returnerer true om alle feltene godkjennes av regexen
     */
    private boolean tekstFeltLyttere(){
        fodselsnr.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(fodselsnrFeil, nyverdi, "Skriv inn et eksisterende fodselsnummer(11 siffer)", null);
        });
        return fodselsnrFeil.getText().isEmpty();
    }//end of method tekstFeltLytter()
    
    /**
     * Tømmer alle tekstfelter og setter regEx labelne til *
     */
    private void setTommeFelter(){
        fodselsnr.clear();
        
        fodselsnrFeil.setText("*");
    }//end of method stTommeFelter()
    
    /**
     * Sjekker om fødselsnummerinputen er tomt
     * @return En boolsk verdi som tilsier om fødselsnummer-inputfeltet er tomt 
     */
    private boolean erFelterTomme(){
        if( fodselsnr.getText().trim().isEmpty()){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn fødselsnummer");
            return false;
        }
        return true;
    }//end of method erFelterTomme()
    
    /**
     * Legger til en lytter på siOppForsikring knappen
     */
    private void siOppForsikring(){
        siOppForsikring.setOnAction((ActionEvent event) -> {
            if( !erFelterTomme() ){
                return;
            }
            if( !tekstFeltLyttere() ){
                return;
            }
            try{
                ForsikringsKunde forsikringsKunde = kundeRegister.finnKunde(fodselsnr.getText().trim());
                if(forsikringsKunde != null)
                    opprettForsikringValgLayout(fodselsnr.getText().trim(), forsikringsKunde.getAktiveForsikringer());
                else
                    GUI.visInputFeilMelding("Finner ikke kunde", "Kunden er ikke registrert i systemet");
            }//end of try//end of try//end of try//end of try
            catch(NumberFormatException | NullPointerException e){
                GUI.visProgramFeilMelding(e);
                return;
            }//end of catch
        });
    }//end of method siOppForsikring()
    
}//end of class SioppforsikringsLayout
