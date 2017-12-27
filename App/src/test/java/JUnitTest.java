/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.hugosergent.app.Joueur;
import javafx.scene.paint.Color;
import junit.framework.*;
import org.junit.Test;


/**
 * Classe de Test JUnit du programme
 * @author Hugo
 */
public class JUnitTest extends TestCase{

    @Test
    public void testTour(){
        Joueur blanc = new Joueur();
        Joueur noir = new Joueur();
        Joueur joueur = new Joueur();
        Boolean Tour = true;
        
        if (Tour == true) {
            joueur = noir;
            assertSame("Joueur et noir sont les mêmes", joueur, noir);
        } else {
            joueur = blanc;
            assertSame("Joueur et blanc sont les mêmes", joueur, noir);
        }
    }

    @Test
    public void couleurNoir()
    {
         Joueur noir = new Joueur();
         noir.setCouleur(Color.BLACK);
         assertEquals(noir.getCouleur(),Color.BLACK);
    }
    
     @Test
    public void couleurBlanc()
    {
        Joueur blanc = new Joueur();
        blanc.setCouleur(Color.WHITE);
        assertEquals(blanc.getCouleur(),Color.WHITE);
    }
    
}
