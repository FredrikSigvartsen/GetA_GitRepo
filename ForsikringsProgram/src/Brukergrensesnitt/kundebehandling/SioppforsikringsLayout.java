


package Brukergrensesnitt.kundebehandling;

import Brukergrensesnitt.GUI;
import forsikringsprogram.*;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import static javafx.scene.control.Alert.AlertType.CONFIRMATION;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Jens
 */
public class SioppforsikringsLayout extends GridPane{
    
    private TextField fodselsnr;    
    private Label fodselsnrFeil;
    private Button siOppForsikring;
    private Kunderegister kundeRegister;
    
    public SioppforsikringsLayout(Kunderegister register){
        opprettSiOppForsikringsSkjema();
        siOppLytter();
        this.kundeRegister = register;
        tekstFeltLyttere();
    }//end of constructor
    
    /**
     * Oppretter skjema for oppsigelse av forsiking
     */
    public void opprettSiOppForsikringsSkjema(){
        
        siOppForsikring = new Button("Si opp forsikring");
        
        
        fodselsnr = TextFieldBuilder.create()
                   .minWidth(GUI.TEKSTFELT_BREDDE)
                   .maxWidth(GUI.TEKSTFELT_BREDDE)
                   .build();
        fodselsnrFeil = new Label("*");
        
        setVgap(10);
        setHgap(10);
        
        Label siOppForsikringLabel = new Label("Oppsigelse av forsikring");
        siOppForsikringLabel.setFont(Font.font(null, FontWeight.BOLD, 20));
        
        //legger til kolonne 1
        add(siOppForsikringLabel, 1, 1, 2, 1);
        add(new Label("Fødselsnummer for oppsigelse:"), 1, 2);
        GridPane.setHalignment(siOppForsikring, HPos.CENTER);
        add(siOppForsikring, 1, 3, 2, 1);
        
        //legger til kolonne 2
        add(fodselsnr, 2, 2);
        
        //legger til kolonne 3
        add(fodselsnrFeil, 3, 2);
    }//end of methd opprettSiOppForsikringsSkjema()
    
    /**
     * Oppretter et vindu med kundens aktive forsikringer og sier opp de forsikringene kunden velger
     * @param fodselsnrForsikringer Fødselsnummeret til kunden som det skal sies opp forsikringer på
     * @param kundensForsikringer En liste med forsikringene til den valgte kunden
     */
    private void opprettForsikringsValgLayout(String fodselsnrForsikringer, List<Forsikring> kundensForsikringer){
        GridPane forsikringsValg = new GridPane();
        if(kundensForsikringer.isEmpty()){
            GUI.visInputFeilMelding("Ingen forsikringer", "Kunden har ingen forsikringer");
            return;
        }
        
        ForsikringsKunde kunde = kundeRegister.finnKunde(fodselsnrForsikringer);
        Label beskrivelse = new Label( kunde.getEtternavn() + ", " + kunde.getFornavn() + " har følgende forsikringer.\nVelg de forsikringene du vil si opp:");
        
        forsikringsValg.add(beskrivelse, 1, 1, 2, 2);
        forsikringsValg.setVgap(15);
        
        int i = 0;
        
        CheckBox[] forsikringValgBoks = new CheckBox[kundensForsikringer.size()];
        
        Iterator<Forsikring> fIter = kundensForsikringer.listIterator();
        while(fIter.hasNext()){
            Forsikring gjeldende = fIter.next();
            if(gjeldende.getAktivForsikring()){
                if(gjeldende instanceof Bilforsikring){
                    Bilforsikring bil = (Bilforsikring) gjeldende;
                    //forsikringValgBoks[i] = new CheckBox("Forsikringsnummer " + String.valueOf(gjeldende.getAvtaleNr()) + ": Bilforsikring, " +  bil.getMerke() + " " + bil.getModell() + ", " + bil.getRegistreringsNr() + ", " + gjeldende.getBetingelser() + ".");
                    forsikringValgBoks[i].setId(String.valueOf(gjeldende.getAvtaleNr()));
                    forsikringsValg.add(forsikringValgBoks[i], 1, (i + 3));
                }
                else if(gjeldende instanceof Baatforsikring){
                    Baatforsikring bat = (Baatforsikring) gjeldende;
                    //forsikringValgBoks[i] = new CheckBox("Forsikringsnummer " + String.valueOf(gjeldende.getAvtaleNr()) + ": Båtforsikring, " +  bat.getMerke() + " " + bat.getModell() + ", " + bat.getRegistreringsNr() + ", " + gjeldende.getBetingelser() + ".");
                    forsikringValgBoks[i].setId(String.valueOf(gjeldende.getAvtaleNr()));
                    forsikringsValg.add(forsikringValgBoks[i], 1, (i + 3));
                }
                else if(gjeldende instanceof Boligforsikring){
                    Boligforsikring bolig = (Boligforsikring) gjeldende;
                    //forsikringValgBoks[i] = new CheckBox("Forsikringsnummer " + String.valueOf(gjeldende.getAvtaleNr()) + ": Boligforsikring, " + bolig.getGateAdresse() + ", " + bolig.getPostNr() + ", " + bolig.getAntallKvm() + "KVM, "  + gjeldende.getBetingelser() + ".");
                    forsikringValgBoks[i].setId(String.valueOf(gjeldende.getAvtaleNr()));
                    forsikringsValg.add(forsikringValgBoks[i], 1, (i + 3));
                }
                else if(gjeldende instanceof Reiseforsikring){
                    Reiseforsikring reise = (Reiseforsikring) gjeldende;
                    //forsikringValgBoks[i] = new CheckBox("Forsikringsnummer " + String.valueOf(gjeldende.getAvtaleNr()) + ": Reiseforsikring, " + reise.getOmraade() + ", " + gjeldende.getBetingelser() + ".");
                    forsikringValgBoks[i].setId(String.valueOf(gjeldende.getAvtaleNr()));
                    forsikringsValg.add(forsikringValgBoks[i], 1, (i + 3));
                }
            }//end of outter if
                i++;
        }//end of while
        
        if(i == 0){
            return;
        }
        
        Alert valg = new Alert(CONFIRMATION);
        valg.setTitle("Si opp forsikringer");
        valg.setHeaderText("");
        valg.setGraphic(forsikringsValg);
        Optional<ButtonType> handling = valg.showAndWait();
        
        int antallAvkrysset = 0;
        StringBuilder output = new StringBuilder();
        int[] avtalenr = new int[forsikringValgBoks.length];
        if( handling.isPresent() && handling.get() == ButtonType.OK ){
            for(int j = 0; j < forsikringValgBoks.length; j++){
                if(forsikringValgBoks != null && forsikringValgBoks[j].isSelected()){
                    avtalenr[j] = Integer.parseInt(forsikringValgBoks[j].getId());
                    antallAvkrysset++;
                }
            }
            
            if(antallAvkrysset == 0){
                GUI.visInputFeilMelding("Feil i valg", "Velg en eller flere forsikringer som skal sies opp, eller trykk avbryt");
                return;
            }
            else{
                for(int k = 0; k < avtalenr.length; k++){
                    if(avtalenr[k] != 0){
                        kundeRegister.siOppForsikring(fodselsnrForsikringer, avtalenr[k]);
                        output.append("Forsikringsnummer " + avtalenr[k] + " er nå sagt opp\n\n");
                    }
                }
                GUI.visInputFeilMelding("Forsikringer sagt opp", output.toString());
                setTommeFelter();
            }
        }
    }// end of method opprettForsikringsValgLayout() 
    
    /**
     * Sjekker input fra brukeren opp mot RegEx og gir umidelbar tilbakemelding på om inputen godkjennes eller evt hva som må endres
     * @return Returnerer true om alle feltene godkjennes av regexen
     */
    private boolean tekstFeltLyttere(){
        fodselsnr.textProperty().addListener((ObservableValue<? extends String> observable, String gammelverdi, String nyverdi) -> {
            GUI.sjekkRegEx(fodselsnrFeil, nyverdi, "Skriv inn et eksisterende fodselsnummer(11 siffer)", null);
        });
        return fodselsnrFeil.getText().isEmpty();
    }//end of method sjekkFelterRegEx()
    
    /**
     * Sjekker alle innputfeltene, og registrerer en forsikring av valgt type
     */
    private boolean sjekkFelter(){
        if( fodselsnr.getText().trim().isEmpty()){
            GUI.visInputFeilMelding("Feil inntasting", "Venligst fyll inn fødselsnummer");
            return false;
        }
        return true;
    }//end of method sjekkFelter()
    
    /**
     * Tømmer alle tekstfelter og setter regEx labelne til *
     */
    private void setTommeFelter(){
        fodselsnr.clear();
        
        fodselsnrFeil.setText("*");
    }//end of method stTommeFelter()
    
    /**
     * Legger til en lytter på siOppForsikring knappen
     */
    private void siOppLytter(){
        siOppForsikring.setOnAction((ActionEvent event) -> {
            if( !sjekkFelter() ){
                return;
            }
            if( !tekstFeltLyttere() ){
                return;
            }
            try{
                ForsikringsKunde forsikringsKunde = kundeRegister.finnKunde(fodselsnr.getText().trim());
                opprettForsikringsValgLayout(fodselsnr.getText().trim(), forsikringsKunde.getForsikringer().getListe());
            }//end of try
            catch(NumberFormatException | NullPointerException e){
                GUI.visProgramFeilMelding(e);
                return;
            }//end of catch
        });
    }//end of method siOppLytter()
    
}//end of class SioppforsikringsLayout
