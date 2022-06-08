import java.util.LinkedList; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class FenetreDefaite extends JFrame{
    
    int tailleW = Toolkit.getDefaultToolkit().getScreenSize().width;
    int tailleH = Toolkit.getDefaultToolkit().getScreenSize().height;
    SoundEffect se = new SoundEffect();
    
    public FenetreDefaite () {
        this.setTitle("Défaite");
        this.setLayout(null);
        this.setSize(resolW(1200),resolH(1200));
        this.setLocation(resolW(800),resolH(200));
        this.setResizable(false); // empêche le redimensionnement de la fenêtre
        
        se.play(".//son//LoseSound.wav");
        
        JPanel PanneauDefaite = new JPanel();       
        PanneauDefaite.setBounds(0,0,resolW(1200),resolH(1200));
        PanneauDefaite.setLayout(null);
        PanneauDefaite.setBackground(new Color(191,205,217)); // bleu fond
        
        JLabel messageDefaite = new JLabel("... Défaite ...",JLabel.CENTER);
        messageDefaite.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,resolW(100)));
        messageDefaite.setForeground(Color.red);
        messageDefaite.setBounds(resolW(0),resolH(0),resolW(1200),resolH(600));
        messageDefaite.setLayout(null);
        PanneauDefaite.add(messageDefaite);
        
        JTextArea messageDefaite2 = new JTextArea("L'INSA est maintenant un énorme cluster ! \nLes cours en présentiel ne sont pas près de reprendre :(");
        messageDefaite2.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,resolW(50)));
        messageDefaite2.setBounds(resolW(0),resolH(800),resolW(1200),resolH(400));
        messageDefaite2.setLayout(null);
        messageDefaite2.setLineWrap(true);
        messageDefaite2.setOpaque(false);
        messageDefaite2.setEditable(false);
        PanneauDefaite.add(messageDefaite2);
        
        this.add(PanneauDefaite);
        this.setVisible(true);
    }
    
    
    
    public int resolW(int n) {
        return (int)(n*tailleW/2750);
    }
    
    public int resolH(int n) {
        return (int)(n*tailleH/1750);
    }
}
