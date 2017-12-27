/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hugosergent.app;

import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 *La classe Joueur permet de définir un joueur et son groupe de pierre
 * @author Hugo
 */
public class Joueur {

    private boolean passe;
    private Color couleur;
    ArrayList<Pierre> ListePierre = new ArrayList<>();
    private int score;

    /**
     *Constructeur de la classe
     */
    public Joueur() {
        this.passe = false;
        this.score=0;
    }

    public int getScore()
    {
        return this.score;
    }
    public void setScore(int s)
    {
        this.score = this.score + s;
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
    
    /*
    *Methode permettant de convertir les couleurs JavaFX en String
    */
    @Override
    public String toString()
    {
        String teinte = null;
        
        if(this.couleur == Color.BLACK)
        {
            teinte = "noir";
        }
        else if(this.couleur == Color.WHITE)
        {
            teinte = "blanc";
        }  
        return teinte;
    }
}
