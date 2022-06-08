import java.util.*; 
import javax.swing.*;
import java.awt.event.*; 
import java.awt.*;

public class CarteLieu implements Carte, Comparable<CarteLieu> {
    
    int numCarte;
    String zoneCarte;
    
    public CarteLieu (int n, String c){
        numCarte=n;
        zoneCarte = c;
    }
    
    public int compareTo(CarteLieu cL) {
        return (this.numCarte - cL.numCarte);
    }
        
}
