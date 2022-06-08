// Chargement des bibliothèques Swing et AWT 

import java.util.*; 
import javax.swing.*;
import java.awt.event.*; 
import java.awt.*;


public class PiochePropagation extends Pioche {
   
    ArrayList<Carte> defausse;
    
    //constructeur d'une pioche
    public PiochePropagation (Lieu [] tabLieu) {
    
        super(tabLieu);
        
        defausse = new ArrayList <Carte>();
        
    }
    
    //renvoie la dernière carte du paquet (piocher normalement)
    public Carte piocher(){
        Carte c = paquet.remove(paquet.size()-1);
        defausse.add(c);
        return c;
        
    }
    
    //renvoie la première carte du paquet (évènement épidémie)
    public Carte piocherEpidemie(){
        Carte c = paquet.remove(0);
        defausse.add(c);
        return c;
        
    }
    
    //remélange la défausse et la place à la fin du paquet
    public void remelanger(){
        
        Collections.shuffle(defausse);
        paquet.addAll(defausse);
        
    }

}
        
