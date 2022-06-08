import java.util.LinkedList; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class FenetreEpidemie extends JFrame{
    
    int tailleW = Toolkit.getDefaultToolkit().getScreenSize().width;
    int tailleH = Toolkit.getDefaultToolkit().getScreenSize().height;
    SoundEffect se = new SoundEffect();
    
    public FenetreEpidemie (int numJoueur, int numCarte ) {
        
        this.setTitle("Epidémie");
        this.setSize(resolW(650),resolH(300));
        this.setLocation(resolW(1050),resolH(725));
        this.setVisible(true);
        this.setLayout(null);
        
        se.play(".//son//EpidemieSound.wav");
        
       
        
        JPanel panneau = new JPanel();       
        panneau.setBounds(0,0,resolW(650),resolH(300));
        panneau.setLayout(null);
        
        JLabel label1 = new JLabel ("Le joueur "+numJoueur+" a tiré une carte épidémie");
        label1.setFont(new java.awt.Font(Font.DIALOG,Font.BOLD,resolW(30)));
        
        ImageIcon image = new ImageIcon(new ImageIcon(".//image//BioHazard.png").getImage().getScaledInstance(resolW(100), resolH(100), Image.SCALE_DEFAULT));
        label1.setIcon(image);     
        label1.setBounds(0,0,resolW(650),resolH(100));
        
        
        JLabel label2 = new JLabel ("La vitesse de propagation va augmenter");
        label2.setFont(new Font("Times new Roman",Font.ITALIC,resolW(20)));
        label2.setBounds(resolW(100),resolH(80),resolW(650),resolH(100));
        
        JLabel label3 = new JLabel ("Et 3 maladies ont été ajouté au lieu "+numCarte);
        label3.setFont(new Font("Times new Roman",Font.ITALIC,resolW(20)));
        label3.setBounds(resolW(100),resolH(130),resolW(650),resolH(100));
        
                       
		panneau.add(label1);
        panneau.add(label2);
        panneau.add(label3);

        this.add(panneau);
        
    }
    
       
    public int resolW(int n) {
        
        return (int)(n*tailleW/2750);
        
    }
    
    public int resolH(int n) {
        
        return (int)(n*tailleH/1750);
        
    }
    
}
