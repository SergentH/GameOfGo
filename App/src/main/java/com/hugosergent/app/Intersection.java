/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hugosergent.app;

/**
 *
 * @author Hugo
 */
public class Intersection {
    private int positionx;
    private int positiony;
    boolean checked;
    
    Intersection()
    {
        checked = false;
    }
    
    Intersection(int x,int y)
    {
        this.positionx = x;
        this.positiony = y;
        checked = false;
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
