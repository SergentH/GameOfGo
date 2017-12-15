/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hugosergent.app;

import java.util.ArrayList;

/**
 *
 * @author Hugo
 */
public class Goban {

    Pierre grille[][] = new Pierre[19][19];
    ArrayList<Pierre> listePrisonniers = new ArrayList<>();

    public void ajouter(Pierre newPierre) {
        grille[newPierre.getpositionx()][newPierre.getpositiony()] = newPierre;
    }

    public void supprimer(Pierre pierre) {
        grille[pierre.getpositionx()][pierre.getpositiony()] = null;
    }

    public Pierre getPierre(int x, int y) {
        Pierre p = this.grille[x][y];
        return p;
    }

    public boolean isPositionPrise(Pierre pierre) {
        boolean isOk = false;

        if (this.grille[pierre.getpositionx()][pierre.getpositiony()] != null) {
            isOk = true;
        }
        return isOk;
    }

    public int MiseAJourGoban(Pierre p) {
        int estPrisonnier = 0;

        //Pour choper la pierre du dessus
        if (this.grille[p.getpositionx()][p.getpositiony() - 1] != null && this.grille[p.getpositionx()][p.getpositiony() - 1].getColor() != p.getColor()) {
            listePrisonniers.clear();
            CreerGroupePrisonnier(this.getPierre(p.getpositionx(), p.getpositiony() - 1));

            //effacer avec fonction estPrisonnier        
        }
        //Pour choper la pierre du dessous
        if (this.grille[p.getpositionx()][p.getpositiony() + 1] != null && this.grille[p.getpositionx()][p.getpositiony() + 1].getColor() != p.getColor()) {
            listePrisonniers.clear();
            CreerGroupePrisonnier(this.getPierre(p.getpositionx(), p.getpositiony() + 1));

            //effacer avec fonction estPrisonnier        
        }
        //Pour choper la pierre de droite
        if (this.grille[p.getpositionx() + 1][p.getpositiony()] != null && this.grille[p.getpositionx() + 1][p.getpositiony()].getColor() != p.getColor()) {
            listePrisonniers.clear();
            CreerGroupePrisonnier(this.getPierre(p.getpositionx() + 1, p.getpositiony()));

            //effacer avec fonction estPrisonnier        
        }
        //Pour choper la pierre de gauche
        if (this.grille[p.getpositionx() - 1][p.getpositiony()] != null && this.grille[p.getpositionx() - 1][p.getpositiony()].getColor() != p.getColor()) {
            listePrisonniers.clear();
            CreerGroupePrisonnier(this.getPierre(p.getpositionx() - 1, p.getpositiony()));

            //effacer avec fonction estPrisonnier        
        }

        return estPrisonnier;
    }

    private void CreerGroupePrisonnier(Pierre p) {

        if(listePrisonniers.indexOf(p)==-1)
        {
            listePrisonniers.add(p);
        }
        //suite
    }

}
