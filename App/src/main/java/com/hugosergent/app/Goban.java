/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hugosergent.app;

import java.util.ArrayList;

/**
 * La Classe goban permettant de modéliser le plateau de jeu
 *
 * @author Hugo
 */
public class Goban {

    Pierre[][] grille;
    ArrayList<Pierre> listePrisonniers;

    /*
    *Constructeur de la classe
     */
    public Goban() {
        this.grille = new Pierre[19][19];
        this.listePrisonniers = new ArrayList<>();
    }

    /*
    *Methode retournant la grille du goban 
     */
    public Pierre[][] getGoban() {
        return this.grille;
    }

    /*
    *Methode permettant d'ajouter une pierre sur le goban 
     */
    public void ajouter(Pierre newPierre) {
        grille[newPierre.getpositionx()][newPierre.getpositiony()] = newPierre;
    }

    /*
    *Methode retournant une pierre a partir d'une position
     */
    public Pierre getPierre(int x, int y) {
        Pierre p = null;
        if (x >= 0 && x <= 18 && y >= 0 && y <= 18) {
            p = this.grille[x][y];
        }
        return p;
    }

    /*
    *Methode verifiant si la position ou une pierre veut être posée est prise
     */
    public boolean isPositionPrise(Pierre pierre) {
        boolean isOk = false;

        if (this.getPierre(pierre.getpositionx(), pierre.getpositiony()) != null) {
            isOk = true;
        }
        return isOk;
    }

    /*
    *Methode comparant la couleur de deux pierres
     */
    public boolean comparaisonCouleur(Pierre p1, Pierre p2) {
        boolean result;
        if (p1.getColor() == p2.getColor()) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    /*
    *Methode amorcant la suppression des pierres ce qui permet de mettre a jour le goban
     */
    public int MiseAJourGoban(Pierre p) {
        int estPrisonnier = 0;
        //Pour choper la pierre du dessus
        if (this.getPierre(p.getpositionx(), p.getpositiony() - 1) != null) {
            if (comparaisonCouleur(p, this.getPierre(p.getpositionx(), p.getpositiony() - 1)) == false) {
                listePrisonniers.clear();
                CreerGroupePrisonnier(this.getPierre(p.getpositionx(), p.getpositiony() - 1));
                if (ControleBordures()) {
                    estPrisonnier += SuppPrisonnieres();
                }
            }
        }
        //Pour choper la pierre du dessous
        if (this.getPierre(p.getpositionx(), p.getpositiony() + 1) != null) {
            if (comparaisonCouleur(p, this.getPierre(p.getpositionx(), p.getpositiony() + 1)) == false) {
                listePrisonniers.clear();
                CreerGroupePrisonnier(this.getPierre(p.getpositionx(), p.getpositiony() + 1));
                if (ControleBordures()) {
                    estPrisonnier += SuppPrisonnieres();
                }
            }
        }
        //Pour choper la pierre de droite
        if (this.getPierre(p.getpositionx() + 1, p.getpositiony()) != null) {
            if (comparaisonCouleur(p, this.getPierre(p.getpositionx() + 1, p.getpositiony())) == false) {
                listePrisonniers.clear();
                CreerGroupePrisonnier(this.getPierre(p.getpositionx() + 1, p.getpositiony()));
                if (ControleBordures()) {
                    estPrisonnier += SuppPrisonnieres();
                }
            }
        }
        //Pour choper la pierre de gauche
        if (this.getPierre(p.getpositionx() - 1, p.getpositiony()) != null) {
            if (comparaisonCouleur(p, this.getPierre(p.getpositionx() - 1, p.getpositiony())) == false) {
                listePrisonniers.clear();
                CreerGroupePrisonnier(this.getPierre(p.getpositionx() - 1, p.getpositiony()));
                if (ControleBordures()) {
                    estPrisonnier += SuppPrisonnieres();
                }
            }
        }
        return estPrisonnier;
    }

    /*
    *Methode permettant de creer un groupe ou d'en développer un autour d'une pierre
     */
    private void CreerGroupePrisonnier(Pierre p) {
        // System.out.println("Pierre: " + p.getpositionx() + " " + p.getpositiony() + " " + p.getColor().toString());
        /*Pierre pour boucler sur la suivante*/
        Pierre pSuite;

        if (listePrisonniers.indexOf(p) == -1) {
            listePrisonniers.add(p);
        }

        //Pierre du haut
        if (this.getPierre(p.getpositionx(), p.getpositiony() - 1) != null) {
            // System.out.println("Groupe haut");
            pSuite = this.getPierre(p.getpositionx(), p.getpositiony() - 1);
            if (comparaisonCouleur(p, pSuite) == true) {
                if (listePrisonniers.indexOf(pSuite) == -1) {
                    listePrisonniers.add(pSuite);
                    CreerGroupePrisonnier(pSuite);
                }
            }
        }
        //Pierre du bas
        if (this.getPierre(p.getpositionx(), p.getpositiony() + 1) != null) {
            // System.out.println("Groupe bas");
            pSuite = this.getPierre(p.getpositionx(), p.getpositiony() + 1);
            if (comparaisonCouleur(p, pSuite) == true) {
                if (listePrisonniers.indexOf(pSuite) == -1) {
                    listePrisonniers.add(pSuite);
                    CreerGroupePrisonnier(pSuite);
                }
            }
        }
        //Pierre du droite
        if (this.getPierre(p.getpositionx() + 1, p.getpositiony()) != null) {
            // System.out.println("Groupe droite");
            pSuite = this.getPierre(p.getpositionx() + 1, p.getpositiony());
            if (comparaisonCouleur(p, pSuite) == true) {
                if (listePrisonniers.indexOf(pSuite) == -1) {
                    listePrisonniers.add(pSuite);
                    CreerGroupePrisonnier(pSuite);
                }
            }
        }
        //Pierre de gauche
        if (this.getPierre(p.getpositionx() - 1, p.getpositiony()) != null) {
            //System.out.println("Groupe gauche");
            pSuite = this.getPierre(p.getpositionx() - 1, p.getpositiony());
            if (comparaisonCouleur(p, pSuite) == true) {
                if (listePrisonniers.indexOf(pSuite) == -1) {
                    listePrisonniers.add(pSuite);
                    CreerGroupePrisonnier(pSuite);
                }
            }
        }
    }

    /*
    *Methode permettant de verifier si un groupe est prisonnier si il a des pierres en bordure du goban
     */
    private boolean ControleBordures() {
        for (Pierre p : listePrisonniers) {
            //haut
            if (p.getpositiony() - 1 >= 0) {
                if (this.getPierre(p.getpositionx(), p.getpositiony() - 1) == null) {
                    return false;
                }
            }
            //bas
            if (p.getpositiony() + 1 <= 18) {
                if (this.getPierre(p.getpositionx(), p.getpositiony() + 1) == null) {
                    return false;
                }
            }
            //droite
            if (p.getpositionx() + 1 <= 18) {
                if (this.getPierre(p.getpositionx() + 1, p.getpositiony()) == null) {
                    return false;
                }
            }
            //gauche
            if (p.getpositionx() - 1 >= 0) {
                if (this.getPierre(p.getpositionx() - 1, p.getpositiony()) == null) {
                    return false;
                }
            }
        }
        return true;
    }

    /*
    *Methode permettant de supprimer les pierres considerees comme prisonnieres
     */
    private int SuppPrisonnieres() {
        int nbSuppression = listePrisonniers.size();
        for (Pierre p : listePrisonniers) {
            this.grille[p.getpositionx()][p.getpositiony()] = null;
        }
        return nbSuppression;
    }

}
