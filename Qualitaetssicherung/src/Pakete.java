/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rathfab28
 */
public class Pakete {

    private int sollGewicht;
    private double gewicht;
    private String reissorte;

    Pakete(String reissorte, int sollGewicht, double gewicht) {
        this.sollGewicht = sollGewicht;
        this.gewicht = gewicht;
        this.reissorte = reissorte;
    }

    int getSollGewicht() {
        return sollGewicht;
    }

    String getReissorte() {
        return reissorte;
    }

    double getGewicht() {
        return gewicht;
    }
}
