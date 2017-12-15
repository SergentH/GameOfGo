/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hugosergent.app;

import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 *
 * @author Hugo
 */
public class Joueur {

    private boolean passe;
    private Color couleur;
    ArrayList<Pierre> ListePierre = new ArrayList<>();

    Joueur() {
        this.passe = false;
    }

    public boolean getPasse() {
        return this.passe;
    }

    public void setPasse(boolean p) {
        this.passe = p;
    }

    public Joueur getJoueur(Joueur joueur) {
        return joueur;
    }

    public void setCouleur(Color c) {
        this.couleur = c;
    }

    public Color getCouleur() {
        return this.couleur;
    }

    public String toString() {
        return this.couleur.toString();
    }
}
