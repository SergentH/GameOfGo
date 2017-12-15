/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hugosergent.app;

import javafx.scene.paint.Color;

/**
 *
 * @author Hugo
 */
public class Pierre {

    private int positionx;
    private int positiony;
    private Color couleur;

    public void setpositionx(int x) {
        this.positionx = x;
    }

    public void setpositiony(int y) {
        this.positiony = y;
    }

    public int getpositionx() {
        return this.positionx;
    }

    public int getpositiony() {
        return this.positiony;
    }
    
    public void setColor(Color c)
    {
        this.couleur = c;
    }
    
    public Color getColor()
    {
        return this.couleur;
    }

}
