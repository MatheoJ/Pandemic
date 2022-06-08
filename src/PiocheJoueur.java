// Chargement des bibliothèques Swing et AWT 

import java.util.*; 
import javax.swing.*;
import java.awt.event.*; 


public class PiocheJoueur extends Pioche {
    
    //constructeur d'une pioche
    public PiocheJoueur (Lieu [] tabLieu) {
    
        super(tabLieu);
        
        //diviser la pioche de 27 cartes en 3 parties (9 cartes, 10 cartes, 8 cartes)
        //puis on ajoute une carte épidémie dans les 2 premiers tas
        //puis on mélange ce tas
        
        CarteEpidemie epidemie = new CarteEpidemie();
        
        List<Carte> tas1 = paquet.subList(0, 8);
        tas1.add(epidemie);
        Collections.shuffle(tas1);
        
        List<Carte> tas2 = paquet.subList(9, 18);
        tas2.add(epidemie);
        Collections.shuffle(tas2);
        
    }
    
    //renvoie la dernière carte du paquet
    public Carte piocher(){
        return paquet.remove(paquet.size()-1);
    } 
    
}
