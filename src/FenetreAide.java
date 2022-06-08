import java.util.LinkedList; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class FenetreAide extends JFrame{
    
    int tailleW = Toolkit.getDefaultToolkit().getScreenSize().width;
    int tailleH = Toolkit.getDefaultToolkit().getScreenSize().height;
    
    public FenetreAide () {
        this.setTitle("Aide");
        this.setLayout(null);
        this.setSize(tailleW,tailleH);
        this.setLocation(0,0);
        this.setResizable(false); // empêche le redimensionnement de la fenêtre
        
        
        //PANNEAU ACTIONS
        JPanel panneauAideActions = new JPanel();       
        panneauAideActions.setBounds(0,resolH(10),resolW(960),this.getHeight());
        panneauAideActions.setLayout(null);
        
        //Titre Actions
        String texteTitreActions = "4 ACTIONS PAR TOUR PARMIS :";
        JTextArea titreActions = afficher(30, texteTitreActions, "titre");
        titreActions.setForeground(new Color(81,96,140));
        
        panneauAideActions.add(titreActions);
        
        
        //----Panneau déplacement----
        JPanel panneauAideDeplacement = new JPanel();       
        panneauAideDeplacement.setBounds(resolW(10),resolH(50),resolW(900),resolH(340));
        panneauAideDeplacement.setLayout(null);
        panneauAideDeplacement.setOpaque(false);
        
        panneauAideActions.add(panneauAideDeplacement);
        
        //Panneau déplacement--Titre
        String texteTitreDeplacement = "Déplacement (cliquez sur le lieu où vous souhaitez aller) :";
        JTextArea titreDeplacement = afficher(30, texteTitreDeplacement, "titre");
        
        panneauAideDeplacement.add(titreDeplacement);
        
        //Panneau déplacement--Corps
        String texteCorpsDeplacement ="Déplacement normal : \n Rejoignez votre destination par case(s) voisine(s) \n Vous utiliserez alors autant d'action que de case(s) parcourue(s) \n \n"; 
        texteCorpsDeplacement = texteCorpsDeplacement + "Déplacement vers le lieu d'une carte que vous possédez : \n Rendez-vous-y immédiatement pour une seule action \n Cette carte sera défaussée\n \n";
        texteCorpsDeplacement = texteCorpsDeplacement + "Déplacement vers n'importe quel lieu : \n Rejoignez n'importe quel lieu pour une seule action \n Cette carte sera défaussée ";
        JTextArea messageDeplacement = afficher(300, texteCorpsDeplacement, "corps");
        
        panneauAideDeplacement.add(messageDeplacement);
        
        //Images
        ImageIcon imageDeplacement1 = new ImageIcon(new ImageIcon(".//image//deplacement1.png").getImage().getScaledInstance(resolW(100), resolH(100), Image.SCALE_DEFAULT));
        JLabel deplacement1 = new JLabel(); 
        deplacement1.setIcon(imageDeplacement1);
        deplacement1.setBounds(resolW(50),resolH(40),resolW(100),resolH(100));
        
        panneauAideDeplacement.add(deplacement1);
        
        ImageIcon imageDeplacement2 = new ImageIcon(new ImageIcon(".//image//deplacement2.png").getImage().getScaledInstance(resolW(100), resolH(100), Image.SCALE_DEFAULT));
        JLabel deplacement2 = new JLabel(); 
        deplacement2.setIcon(imageDeplacement2);
        deplacement2.setBounds(resolW(50),resolH(135),resolW(100),resolH(100));
        
        panneauAideDeplacement.add(deplacement2);
        
        ImageIcon imageDeplacement3 = new ImageIcon(new ImageIcon(".//image//deplacement3.png").getImage().getScaledInstance(resolW(100), resolH(100), Image.SCALE_DEFAULT));
        JLabel deplacement3 = new JLabel(); 
        deplacement3.setIcon(imageDeplacement3);
        deplacement3.setBounds(resolW(50),resolH(235),resolW(100),resolH(100));
        
        panneauAideDeplacement.add(deplacement3);
        
        //----Panneau Partage de Connaissances----
        JPanel panneauAideCommunication = new JPanel();       
        panneauAideCommunication.setBounds(resolW(10),resolH(390),resolW(900),resolH(220));
        panneauAideCommunication.setLayout(null);
        panneauAideCommunication.setOpaque(false);
        
        panneauAideActions.add(panneauAideCommunication);
        
        //Panneau Partage de Connaissances--Titre
        String texteTitreCommunication = "Partager des connaissances :";
        JTextArea titreCommunication = afficher(30, texteTitreCommunication, "titre");
        
        panneauAideCommunication.add(titreCommunication);
        
        //Panneau Partage de Connaissances--Corps
        String texteCorpsCommunication ="Donner à un autre joueur la carte du lieu où vous vous trouvez \n         Cliquez sur la carte, se trouvant dans votre panneau de joueur \n         Indiquez le numéro de l'autre joueur dans la fenêtre qui s'ouvre  \nPrendre à un autre joueur la carte du lieu où vous vous trouvez\n         Cliquez sur la carte se trouvant dans son panneau joueur \nCondition : se trouver sur la même case que l'autre joueur"; 
        JTextArea messageCommunication = afficher(170, texteCorpsCommunication, "corps");
        
        panneauAideCommunication.add(messageCommunication);
        
        //Image
        ImageIcon imageCommunication = new ImageIcon(new ImageIcon(".//image//partage_connaissances.png").getImage().getScaledInstance(resolW(100), resolH(100), Image.SCALE_DEFAULT));
        JLabel communication = new JLabel(); 
        communication.setIcon(imageCommunication);
        communication.setBounds(resolW(40),resolH(80),resolW(100),resolH(100));
        
        panneauAideCommunication.add(communication);
        
        
        //----Panneau Traiter maladie----
        JPanel panneauAideTraiterMaladie = new JPanel();       
        panneauAideTraiterMaladie.setBounds(resolW(10),resolH(610),resolW(900),resolH(150));
        panneauAideTraiterMaladie.setLayout(null);
        panneauAideTraiterMaladie.setOpaque(false);
        
        panneauAideActions.add(panneauAideTraiterMaladie);
        
        //Panneau traiter maladie--Titre
        String texteTitreTraiterMaladie = "Traiter maladie :";
        JTextArea titreTraiterMaladie = afficher(30, texteTitreTraiterMaladie, "titre");
        
        panneauAideTraiterMaladie.add(titreTraiterMaladie);
        
        //Panneau traiter maladie--Corps
        String texteCorpsTraiterMaladie ="Faire baisser le compteur de maladie \nSi le remède n'est pas trouvé il diminuera de 1 point sinon il tombera à 0 \nCondition : se trouver sur une case où le compteur de maladie est non nul"; 
        JTextArea messageTraiterMaladie = afficher(100, texteCorpsTraiterMaladie, "corps");
        
        panneauAideTraiterMaladie.add(messageTraiterMaladie);
        
        //Image
        ImageIcon imageTraiter = new ImageIcon(new ImageIcon(".//image//Traiter_maladie.png").getImage().getScaledInstance(resolW(80), resolH(80), Image.SCALE_DEFAULT));
        JLabel traiter = new JLabel(); 
        traiter.setIcon(imageTraiter);
        traiter.setBounds(resolW(50),resolH(60),resolW(80),resolH(80));
        
        panneauAideTraiterMaladie.add(traiter);
        
        //----Panneau Placer un centre----
        JPanel panneauAidePlacerCentre = new JPanel();       
        panneauAidePlacerCentre.setBounds(resolW(10),resolH(770),resolW(890),resolH(120));
        panneauAidePlacerCentre.setLayout(null);
        panneauAidePlacerCentre.setOpaque(false);
        
        panneauAideActions.add(panneauAidePlacerCentre);
        
        //Panneau Placer un centre--Titre
        String texteTitrePlacerCentre = "Placer un centre de recherche :";
        JTextArea titrePlacerCentre = afficher(30, texteTitrePlacerCentre, "titre");
        
        panneauAidePlacerCentre.add(titrePlacerCentre);
        
        //Panneau Placer un centre--Corps
        String texteCorpsPlacerCentre ="Défausser la carte du lieu pour placer un centre de recherche \nCondition : se trouver sur un lieu dont vous possédez la carte"; 
        JTextArea messagePlacerCentre = afficher(70, texteCorpsPlacerCentre, "corps");
        
        panneauAidePlacerCentre.add(messagePlacerCentre);
        
        //Image
        ImageIcon imageCentre = new ImageIcon(new ImageIcon(".//image//centre_Recherche.png").getImage().getScaledInstance(resolW(80), resolH(60), Image.SCALE_DEFAULT));
        JLabel centre = new JLabel(); 
        centre.setIcon(imageCentre);
        centre.setBounds(resolW(50),resolH(60),resolW(80),resolH(60));
        
        panneauAidePlacerCentre.add(centre);
        

        //----Panneau Remède----
        JPanel panneauAideRemede = new JPanel();       
        panneauAideRemede.setBounds(resolW(10),resolH(890),resolW(890),resolH(120));
        panneauAideRemede.setLayout(null);
        panneauAideRemede.setOpaque(false);
        
        panneauAideActions.add(panneauAideRemede);
        
        //Panneau Remede--Titre
        String texteTitreRemede = "Créer un remède :";
        JTextArea titreRemede = afficher(30, texteTitreRemede, "titre");
        
        panneauAideRemede.add(titreRemede);
        
        //Panneau Remede--Corps
        
        String texteCorpsRemede ="Défausser 3 cartes de la zone correspondant à votre position pour découvrir le remède \nCondition : se trouver sur une case qui possède un centre de recherche"; 
        JTextArea messageRemede = afficher(70, texteCorpsRemede, "corps");
        
        panneauAideRemede.add(messageRemede);
        
        //Image
        ImageIcon imageRemede = new ImageIcon(new ImageIcon(".//image//Remede_notFound.png").getImage().getScaledInstance(resolW(100), resolH(50), Image.SCALE_DEFAULT));
        JLabel remede = new JLabel(); 
        remede.setIcon(imageRemede);
        remede.setBounds(resolW(50),resolH(60),resolW(100),resolH(50));
        
        panneauAideRemede.add(remede);
        
        
        //PANNEAU FIN DU TOUR
        JPanel panneauAideFinTour = new JPanel();       
        panneauAideFinTour.setBounds(resolW(960),resolH(10),resolW(960),resolH(600));
        panneauAideFinTour.setLayout(null);
        
        //Titre Fin du tour
        String texteTitreFinTour = "FIN DU TOUR :";
        JTextArea titreFinTour = afficher(30, texteTitreFinTour, "titre");
        titreFinTour.setForeground(new Color(81,96,140));
        
        panneauAideFinTour.add(titreFinTour);
        
        
        //----Panneau piocher des cartes----
        JPanel panneauAidePioche = new JPanel();       
        panneauAidePioche.setBounds(resolW(10),resolH(50),resolW(900),resolH(260));
        panneauAidePioche.setLayout(null);
        panneauAidePioche.setOpaque(false);
        
        panneauAideFinTour.add(panneauAidePioche);
        
        //Panneau piocher des cartes--Titre
        String texteTitrePioche = "Piocher cartes :";
        JTextArea titrePioche = afficher(30, texteTitrePioche, "titre");
        
        panneauAidePioche.add(titrePioche);
        
        //Panneau piocher des cartes--Corps
        String texteCorpsPioche ="Cette action signe la fin de votre tour \nVous piocherez alors 2 cartes \nSi vous avez plus de 5 cartes il faudra en défausser \n \n"; 
        texteCorpsPioche = texteCorpsPioche + "Il se peut que vous piochez une carte épidémie, dans ce cas-là :\nLa vitesse de propagation de la maladie augmente de 1 \nUn lieu subit une éclosion (cf. Phase propagation) \nLa défausse Propagation est remélangée et placée au-dessus de la pioche\n \n";
        JTextArea messagePioche = afficher(220, texteCorpsPioche, "corps");
        
        panneauAidePioche.add(messagePioche);
        
        //Images
        ImageIcon imagePioche1 = new ImageIcon(new ImageIcon(".//image//pioche.png").getImage().getScaledInstance(resolW(80), resolH(70), Image.SCALE_DEFAULT));
        JLabel pioche1 = new JLabel(); 
        pioche1.setIcon(imagePioche1);
        pioche1.setBounds(resolW(50),resolH(65),resolW(80),resolH(70));
        
        panneauAidePioche.add(pioche1);
        
        ImageIcon imagePioche2 = new ImageIcon(new ImageIcon(".//image//epidemie.png").getImage().getScaledInstance(resolW(70), resolH(60), Image.SCALE_DEFAULT));
        JLabel pioche2 = new JLabel(); 
        pioche2.setIcon(imagePioche2);
        pioche2.setBounds(resolW(50),resolH(175),resolW(70),resolH(60));
        
        panneauAidePioche.add(pioche2);
        
        
        //----Panneau propagation----
        JPanel panneauAidePropa = new JPanel();       
        panneauAidePropa.setBounds(resolW(10),resolH(320),resolW(900),resolH(340));
        panneauAidePropa.setLayout(null);
        panneauAidePropa.setOpaque(false);
        
        panneauAideFinTour.add(panneauAidePropa);
        
        //Panneau propagation--Titre
        String texteTitrePropa = "Phase propagation :";
        JTextArea titrePropa = afficher(30, texteTitrePropa, "titre");
        
        panneauAidePropa.add(titrePropa);
        
        //Panneau propagation--Corps
        String texteCorpsPropa ="La maladie se propage : le nombre de lieu touché est égal à la vitesse de propagation \nLes cartes lieux sont piochées puis placées dans la défausse Propagation \nLe nombre de maladie augmente de 1 dans le lieu touché  \n \n"; 
        texteCorpsPropa = texteCorpsPropa + "Si le nombre de maladie dépasse 3 dans un lieu, il y a éclosion : \nLe virus se propage sur les cases voisines : leur nombre de maladie augmente de 1 \nDes éclosions en chaîne peuvent avoir lieu \nPour chaque éclosion, le marqueur associé augmente de 1\n \n";
        JTextArea messagePropa = afficher(300, texteCorpsPropa, "corps");
        
        panneauAidePropa.add(messagePropa);
        
        //Image
        ImageIcon imagePropa = new ImageIcon(new ImageIcon(".//image//propagation.png").getImage().getScaledInstance(resolW(160), resolH(130), Image.SCALE_DEFAULT));
        JLabel propa = new JLabel(); 
        propa.setIcon(imagePropa);
        propa.setBounds(resolW(5),resolH(90),resolW(160),resolH(130));
        
        panneauAidePropa.add(propa);
        
        
        //PANNEAU FIN DE PARTIE
        JPanel panneauAideFinPartie = new JPanel();       
        panneauAideFinPartie.setBounds(resolW(960),resolH(600),resolW(960),resolH(540));
        panneauAideFinPartie.setLayout(null);
        
        //Titre Fin du tour
        String texteTitreFinPartie = "FIN DU TOUR :";
        JTextArea titreFinPartie = afficher(30, texteTitreFinPartie, "titre");
        titreFinPartie.setForeground(new Color(81,96,140));
        
        panneauAideFinPartie.add(titreFinPartie);
        
        
        //----Panneau Victoire----
        JPanel panneauAideVictoire = new JPanel();       
        panneauAideVictoire.setBounds(resolW(10),resolH(50),resolW(900),resolH(150));
        panneauAideVictoire.setLayout(null);
        panneauAideVictoire.setOpaque(false);
        
        panneauAideFinPartie.add(panneauAideVictoire);
        
        //Panneau victoire--Titre
        String texteTitreVictoire = "Victoire :";
        JTextArea titreVictoire = afficher(30, texteTitreVictoire, "titre");
        
        panneauAideVictoire.add(titreVictoire);
        
        //Panneau victoire--Corps
        String texteCorpsVictoire ="Tous les joueurs l'emportent lorsque : \n   - le remède est trouvé \n   - le nombre total de maladie sur le campus est inférieur à 5\n \n"; 
        JTextArea messageVictoire = afficher(100, texteCorpsVictoire, "corps");
        
        panneauAideVictoire.add(messageVictoire);
        
        //Image
        ImageIcon imageVictoire = new ImageIcon(new ImageIcon(".//image//victoire.png").getImage().getScaledInstance(resolW(90), resolH(80), Image.SCALE_DEFAULT));
        JLabel victoire = new JLabel(); 
        victoire.setIcon(imageVictoire);
        victoire.setBounds(resolW(50),resolH(55),resolW(80),resolH(70));
        
        panneauAideVictoire.add(victoire);
        
        //----Panneau Défaite----
        JPanel panneauAideDefaite = new JPanel();       
        panneauAideDefaite.setBounds(resolW(10),resolH(200),resolW(900),resolH(200));
        panneauAideDefaite.setLayout(null);
        panneauAideDefaite.setOpaque(false);
        
        panneauAideFinPartie.add(panneauAideDefaite);
        
        //Panneau Défaite--Titre
        String texteTitreDefaite = "Défaite :";
        JTextArea titreDefaite = afficher(30, texteTitreDefaite, "titre");
        
        panneauAideDefaite.add(titreDefaite);
        
        //Panneau Défaite--Corps
        String texteCorpsDefaite ="La partie est perdue dans les 3 cas suivants : \n   - le nombre de maladie total est supérieur à 47 : le virus est hors de contôle \n   - la pioche des joueurs est vide : les 4 chercheurs ont cédé à la panique \n   - le marqueur éclosion est de 8 : le virus s'est installé sur le campus \n \n"; 
        JTextArea messageDefaite = afficher(150, texteCorpsDefaite, "corps");
        
        panneauAideDefaite.add(messageDefaite);
        
        //Image
        ImageIcon imageDefaite = new ImageIcon(new ImageIcon(".//image//defaite.png").getImage().getScaledInstance(resolW(90), resolH(90), Image.SCALE_DEFAULT));
        JLabel defaite = new JLabel(); 
        defaite.setIcon(imageDefaite);
        defaite.setBounds(resolW(50),resolH(65),resolW(90),resolH(90));
        
        panneauAideDefaite.add(defaite);
        
        this.add(panneauAideActions);
        this.add(panneauAideFinTour);
        this.add(panneauAideFinPartie);
        this.setVisible(true);
        
    }
    
    
    
    public int resolW(int n) {
        return (int)(n*tailleW/1920);
    }
    
    public int resolH(int n) {
        return (int)(n*tailleH/1080);
    }
    
    public JTextArea afficher(int height, String texte, String type){
        
        int posW;
        int posH;
        int width;
        Color color;
        Font style; 
        
        if(type=="titre"){
            posW=resolW(10);
            posH=resolH(10);
            width=resolW(890);
            color = new Color(140,79,43);
            style = new Font("Times new Roman",Font.BOLD,resolW(25));
        } 
        
        else {
            posW=resolW(180);
            posH=resolH(60);
            width=resolW(720);
            color = Color.black;
            style = new Font("Times new Roman",Font.ITALIC,resolW(20));
        }
        
        JTextArea message = new JTextArea();
        message.setFont(style);
        message.setBounds(posW,posH,width,height);
        message.setLayout(null);
        message.setLineWrap(true);
        message.setOpaque(false);
        message.setForeground(color);
        message.setEditable(false);
        message.setText(texte);
        
        return message;
        
    }
    
}
