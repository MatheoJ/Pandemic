import java.util.LinkedList; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class FenetreVictoire extends JFrame{
    
    int tailleW = Toolkit.getDefaultToolkit().getScreenSize().width;
    int tailleH = Toolkit.getDefaultToolkit().getScreenSize().height;
    SoundEffect se = new SoundEffect();
    
    public FenetreVictoire () {
        this.setTitle("Victoire");
        this.setLayout(null);
        this.setSize(resolW(1200),resolH(1200));
        this.setLocation(resolW(800),resolH(200));
        this.setResizable(false); // empêche le redimensionnement de la fenêtre
        
        se.play(".//son//WinSound.wav");
        
        JPanel PanneauVictoire = new JPanel();       
        PanneauVictoire.setBounds(0,0,resolW(1200),resolH(1200));
        PanneauVictoire.setLayout(null);
        PanneauVictoire.setBackground(new Color(191,205,217)); // bleu fond
        
        JLabel messageVictoire = new JLabel("! VICTOIRE !",JLabel.CENTER);
        messageVictoire.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,resolW(100)));
        messageVictoire.setForeground(Color.green);
        messageVictoire.setBounds(resolW(0),resolH(0),resolW(1200),resolH(600));
        messageVictoire.setLayout(null);
        PanneauVictoire.add(messageVictoire);
        
        JTextArea messageVictoire2 = new JTextArea("Vous avez éliminé le covid de l'INSA! \n Grâce à vous, \n les soirées reprendrons !");
        messageVictoire2.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,resolW(50)));
        messageVictoire2.setBounds(resolW(0),resolH(800),resolW(1200),resolH(400));
        messageVictoire2.setLayout(null);
        messageVictoire2.setLineWrap(true);
        messageVictoire2.setOpaque(false);
        messageVictoire2.setEditable(false);
        PanneauVictoire.add(messageVictoire2);
        
        this.add(PanneauVictoire);
        this.setVisible(true);
    }
    
    
    
    public int resolW(int n) {
        return (int)(n*tailleW/2750);
    }
    
    public int resolH(int n) {
        return (int)(n*tailleH/1750);
    }
}
