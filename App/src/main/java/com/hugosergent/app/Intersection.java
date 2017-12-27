/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hugosergent.app;

/**
 *Classe permettant de définir une intersection vide
 * @author Hugo
 */
public class Intersection {
    private int positionx;
    private int positiony;
    
    Intersection()
    {
    }
    
    Intersection(int x,int y)
    {
        this.positionx = x;
        this.positiony = y;
    }
    
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
}
