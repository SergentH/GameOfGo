/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hugosergent.app;

import java.util.Comparator;

/**
 *
 * @author Hugo
 */
public class AvantageJoueur implements Comparator<Joueur> {

    @Override
    public int compare(Joueur j1, Joueur j2) {
        return j1.ListePierre.size() - j2.ListePierre.size();
    }

}
