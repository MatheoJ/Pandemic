// Chargement des bibliothèques Swing et AWT 

import java.util.*; 
import javax.swing.*;
import java.awt.event.*; 
import java.awt.*;

public abstract class Pioche {
    
    // attributs 
    ArrayList<Carte> paquet;
    
    
    //constructeur d'une pioche
    public Pioche (Lieu [] tabLieu) { 
        
        paquet = new ArrayList<Carte>();
        
        for(int i=0;i<tabLieu.length;i++){
            CarteLieu c = new CarteLieu ( tabLieu[i].position, tabLieu[i].zone); 
            paquet.add(c);
        }
        
        Collections.shuffle(paquet);
       
    }
    
    //méthode abstraite
    public abstract Carte piocher();
    
    
}    
