/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jens
 */
import java.util.*;

public class ForsikringsKunde {
    private String fornavn;
    private String etternavn;
    private String fakturaAdresse;
    private String postSted;
    private int postNr;    
    private int forsikringsnr;
    private int forsikringsPremie;
    private boolean totalKunde;
    private SkademeldingsRegister skademelding;
    private int erstatninger;
    private Date startDato;
    private ForsikringsListe forsikringer;
    private SkadeMeldingsliste skademeldinger;
    private int fodselsNr;
    private boolean erForsikringsKunde;
    
    private ForsikringsKunde(){
        
    }
}
