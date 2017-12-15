package com.hugosergent.app;

import java.net.URL;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class FXMLController implements Initializable {
    
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private GridPane grille;
    @FXML
    private Label PointsNoir;
    @FXML
    private Label PointsBlanc;
    @FXML
    private Label LabelFin;
    @FXML
    private Button BoutonPasser;
    @FXML
    private Button BoutonSauvegarder;
    @FXML
    private Button BoutonReMain;


    /*Log4j*/
    static Logger logger = Logger.getLogger(FXMLController.class);


    /*Variable pour changer le tour des joueurs*/
    Boolean Tour = true;
    /*Variable globale pour qu'un joueur joue*/
    Joueur joueur;
    /*Couleur d'un joueur*/
    Color couleur;

    /*Definiton du goban*/
    Goban goban = new Goban();
    /*Pour savoir si une case est prise*/
    Boolean positionPrise;
    /*Pour que le joueur rende la main au lieu de devoir obligatoirement finir la partie si le premier passe*/
    Boolean precedantPasse = false;
    /*Partie finie*/
    Boolean partieFinie = false;

    /*CREATION DES JOUEURS*/
    Joueur blanc = new Joueur();
    Joueur noir = new Joueur();

    /*FIN CREATION DES JOUEURS*/
 /*
    * Fonction permettant a un joueur de passer son tour
     */
    @FXML
    public void BPasserPressed() {
        /*faire un boolean pour empecher le retour du clic*/
        System.out.println("Joueur Passe\n");
        
        joueur.setPasse(true);

        /*Changement de joueur*/
        Tour = !Tour;
        if (Tour == true) {
            joueur = noir;
        } else {
            joueur = blanc;
        }
        /*Definition de la nouvelle couleur*/
        couleur = joueur.getCouleur();
        
        BoutonReMain.setVisible(true);
        precedantPasse = true;

        /*si les deux joueurs passent c'est la fin de la partie*/
        if (noir.getPasse() == true && blanc.getPasse() == true) {
            LabelFin.visibleProperty().setValue(true);
            partieFinie = true;
            BoutonReMain.setVisible(false);
            BoutonPasser.setVisible(false);
            BoutonSauvegarder.setVisible(false);
        }
    }

    /*
    * Fonction permettant de sauvegarder
     */
    @FXML
    public void BSVGPressed() {
        
    }

    /*
    * Fonction permettant de redonner la main
     */
    @FXML
    public void ReMainPressed() {

        /*Changement de joueur*/
        Tour = !Tour;
        
        if (Tour == true) {
            joueur = noir;
        } else {
            joueur = blanc;
        }
        /*Definition de la nouvelle couleur*/
        couleur = joueur.getCouleur();
        precedantPasse = false;
        BoutonReMain.setVisible(false);
    }

    /*
    * Fonction permettant de poser des pierres
     */
    @FXML
    public void MousePressed(MouseEvent mouseEvent) {
        if (partieFinie == false) {
            if (precedantPasse == false) {
                
                
                /*Creation graphique d'une pierre*/
                Circle circle = new Circle(mouseEvent.getX(), mouseEvent.getY(), 15, couleur);
                /*Création d'une pierre*/
                Pierre p = new Pierre();
                p.setpositionx((int) (circle.getCenterX()) / 42);
                p.setpositiony((int) (circle.getCenterY()) / 42);
                
                positionPrise = goban.isPositionPrise(p);

                /*Verifier que le joueur peut poser la pierre a cette place*/
                if (positionPrise == false) {
                    grille.add(circle, (int) (circle.getCenterX()) / 42, (int) (circle.getCenterY()) / 42, 1, 1);
                    GridPane.setHalignment(circle, HPos.CENTER); // To align horizontally in the cell
                    GridPane.setValignment(circle, VPos.CENTER); // To align vertically in the cell
                    goban.ajouter(p);
                    joueur.ListePierre.add(p);
                    
                    
                    System.out.println("joueur: " + joueur.toString() + ", nombre de pierres: " + joueur.ListePierre.size());
                    System.out.println("Ajut de la pierre; " + p.getpositionx()+ " "+ p.getpositiony());
                    

                    /*Changement de joueur*/
                    Tour = !Tour;
                    
                    joueur.setPasse(false);
                    
                    if (Tour == true) {
                        joueur = noir;
                    } else {
                        joueur = blanc;
                    }
                    /*Definition de la nouvelle couleur*/
                    couleur = joueur.getCouleur();
                }
                /*Fin positionPrise*/
            }
        }
    }

    /*
    * Fonction d'initialisation du controlleur
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        logger.info("La partie commence");

        /*Attribution des couleurs*/
        blanc.setCouleur(Color.WHITE);
        noir.setCouleur(Color.BLACK);
        /*fin attribution des couleurs*/

 /*Lancement de la partie*/
        System.out.println("CA COMMENCE\n");
        joueur = noir;
        couleur = joueur.getCouleur();
        
    }
}
