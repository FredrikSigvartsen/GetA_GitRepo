/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forsikringsprogram;

import javax.swing.*;

/**
 * Siden skademeldingen er spesiell for biler, er det naturlig å ha en egen klasse som arver Skademelding. 
 * Et "skjema" vil alltid være på papirform, og fylles ut av to parter under et bilkrasj. 
 * @author fredr_000
 */
public class BilSkademelding extends Skademelding {

    private ImageIcon krasjSkjema;
    
    public BilSkademelding(String skadeType, String beskrivelse, ImageIcon bilde, String vitneKontakt, int takst, int erstatningsbeløp, 
                            String inntruffetTidspunkt, ImageIcon krasjSkjema) {
        super(skadeType, beskrivelse, bilde, vitneKontakt, takst, erstatningsbeløp, inntruffetTidspunkt);
        krasjSkjema = krasjSkjema;
    }

    public ImageIcon getKrasjSkjema() {
        return krasjSkjema;
    }

    @Override
    public String toString() {
        return super.toString() + "\nSkademeldingsskjema for bilkræsj:  " + krasjSkjema;
    }
    
    
    
    
}
