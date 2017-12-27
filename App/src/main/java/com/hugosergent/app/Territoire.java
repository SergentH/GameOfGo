/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hugosergent.app;

import java.util.ArrayList;

/**
 *La classe Territoire permet de verifier les intersections qui ne sont pas des pierres pour pouvoir au final calculer le score
 * @author Hugo
 */
public class Territoire {

    public Intersection[][] ListeIntersections;

    /*
    *Constructeur de la classe
    */
    public Territoire() {
        ListeIntersections = new Intersection[19][19];
    }

    /*
    * Methode retournant une intersection à partir de positions
    */
    public Intersection getIntersection(int x, int y) {
        Intersection i = null;
        if (x >= 0 && x <= 18 && y >= 0 && y <= 18) {
            i = this.ListeIntersections[x][y];
        }
        return i;
    }

    /*
    *Methode recherchant toutes les intersections qui ne sont pas des pierres
    */
    public void rechercheIntersections(Goban g) {
        for (int x = 0; x < 19; x++) {
            for (int y = 0; y < 19; y++) {
                if (g.grille[x][y] == null) {
                    Intersection i = new Intersection(x, y);
                    //System.out.print("x:"+ i.getpositionx()+" ");
                    //System.out.print("y:"+ i.getpositiony()+" - ");
                    this.ListeIntersections[x][y] = i;
                }
            }
        }
    }

    /*
    *Methode permettant de creer un territoire a partir d'une intersection
    */
    void CreationTerritoire(Intersection i, ArrayList<Intersection> Territoire) {
        if (Territoire.indexOf(i) == -1) {
            Territoire.add(i);
        }
        Intersection iSuite;
        //intersection du haut
        if (this.getIntersection(i.getpositionx(), i.getpositiony() - 1) != null) {
            iSuite = this.getIntersection(i.getpositionx(), i.getpositiony() - 1);
            if (Territoire.indexOf(iSuite) == -1) {
                Territoire.add(iSuite);
                CreationTerritoire(iSuite, Territoire);
            }
        }
        //intersection du bas
        if (this.getIntersection(i.getpositionx(), i.getpositiony() + 1) != null) {
            iSuite = this.getIntersection(i.getpositionx(), i.getpositiony() + 1);
            if (Territoire.indexOf(iSuite) == -1) {
                Territoire.add(iSuite);
                CreationTerritoire(iSuite, Territoire);
            }
        }
        //intersection du gauche
        if (this.getIntersection(i.getpositionx() - 1, i.getpositiony()) != null) {
            iSuite = this.getIntersection(i.getpositionx() - 1, i.getpositiony());
            if (Territoire.indexOf(iSuite) == -1) {
                Territoire.add(iSuite);
                CreationTerritoire(iSuite, Territoire);
            }
        }
        //intersection du droite
        if (this.getIntersection(i.getpositionx() + 1, i.getpositiony()) != null) {
            iSuite = this.getIntersection(i.getpositionx() + 1, i.getpositiony());
            if (Territoire.indexOf(iSuite) == -1) {
                Territoire.add(iSuite);
                CreationTerritoire(iSuite, Territoire);
            }
        }
    }

    /*
    *Methode permettant de confirmer qu'un territoire appartient a un joueur et qu'il n'est pas partagé
    */
    Pierre confirmationTerritoire(ArrayList<Intersection> Territoire, Goban g) {
        Pierre pReference = null;
        Pierre p = null;

        for (Intersection i : Territoire) {
            //Posistion haut
            if (i.getpositiony() - 1 >= 0) {
                if (g.getPierre(i.getpositionx(), i.getpositiony() - 1) != null) {
                    if (pReference == null) {
                        pReference = g.getPierre(i.getpositionx(), i.getpositiony() - 1);
                    }
                    p = g.getPierre(i.getpositionx(), i.getpositiony() - 1);
                    if(pReference.getColor() != p.getColor())
                    {
                        return null;
                    }
                }
            }
            //Posistion bas
            if (i.getpositiony() + 1 <= 18) {
                if (g.getPierre(i.getpositionx(), i.getpositiony() + 1) != null) {
                    if (pReference == null) {
                        pReference = g.getPierre(i.getpositionx(), i.getpositiony() + 1);
                    }
                    p = g.getPierre(i.getpositionx(), i.getpositiony() + 1);
                    if(pReference.getColor() != p.getColor())
                    {
                        return null;
                    }
                }
            }
            //Posistion gauche
            if (i.getpositionx() - 1 >= 0) {
                if (g.getPierre(i.getpositionx() - 1, i.getpositiony()) != null) {
                    if (pReference == null) {
                        pReference = g.getPierre(i.getpositionx() - 1, i.getpositiony());
                    }
                    p = g.getPierre(i.getpositionx() - 1, i.getpositiony());
                    if(pReference.getColor() != p.getColor())
                    {
                        return null;
                    }
                    
                }
            }
            //Posistion droite
            if (i.getpositionx() + 1 <= 18) {
                if (g.getPierre(i.getpositionx() + 1, i.getpositiony()) != null) {
                    if (pReference == null) {
                        pReference = g.getPierre(i.getpositionx() + 1, i.getpositiony());
                    }
                    p = g.getPierre(i.getpositionx() + 1, i.getpositiony());
                    if(pReference.getColor() != p.getColor())
                    {
                        return null;
                    }
                }
            }
        }
        return pReference;
    }

    /*
    *Methode permettant de calculer le score des joueurs a partir des intersections a l'interieur d'un territoire
    */
    void CalculScore(Joueur jUn, Joueur jDeux, Goban g) {
        ArrayList<ArrayList<Intersection>> ListeTerritoires = new ArrayList<ArrayList<Intersection>>();

        for (int x = 0; x < 19; x++) {
            for (int y = 0; y < 19; y++) {

                Intersection i = this.getIntersection(x, y);
                if (this.getIntersection(x, y) != null) {
                    boolean estTraite = false;

                    for (ArrayList<Intersection> liste : ListeTerritoires) {
                        if (liste.indexOf(i) != -1) {
                            estTraite = true;
                            break;
                        }
                    }
                    if (estTraite == false) {
                        ArrayList<Intersection> Territoire = new ArrayList<Intersection>();
                        CreationTerritoire(i, Territoire);
                        ListeTerritoires.add(Territoire);
                    }
                }
            }
        }
        for (ArrayList<Intersection> Zone : ListeTerritoires) {
            Pierre p = confirmationTerritoire(Zone, g);   
            if (p != null) {
                if (p.getColor() == jUn.getCouleur()) {
                    jUn.setScore(Zone.size());
                } else if(p.getColor() == jDeux.getCouleur()) {
                    jDeux.setScore(Zone.size());
                }
            }

        }

    }

}
