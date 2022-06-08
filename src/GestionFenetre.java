import java.util.LinkedList; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;


public class GestionFenetre extends JFrame implements ActionListener{
    
    /* couleurs cutilisées :
 (new Color(81,96,140)); // bleu fond
 (new Color(134,151,166)); // gris foncé
 (new Color(191,205,217)); // gris claire
 (new Color(191,135,86)); // marron claire
 (new Color(140,79,43)); // marron foncé
 
*/
    
    //attributs
    Jeu jeu;
    JLabel questions;
    JLabel remede;
    JLabel nbCartePiocheJoueur;
    JLabel eclosion;
    JLabel vitessePropagation;
    JLabel[] positionJoueurs;
    JLabel[] pionJoueurs;
    JPanel [] panelJoueurs;
    JButton[] pointsCarte;
    JButton BtAide;
    JButton placerCentre;
    JButton traiterMaladie;
    JButton creerRemede;
    JButton phasePioche;
    JButton muteMusic; 
    JButton[][] cartes;
    int tailleW = Toolkit.getDefaultToolkit().getScreenSize().width;
    int tailleH = Toolkit.getDefaultToolkit().getScreenSize().height;
    int[][] coordPointsCarte;
    Color pink = new Color(250,124,188);
    Color purple = new Color(189,124,254);
    Color blue = new Color(107,218,237);
    Color green = new Color(124,243,141);
    SoundEffect se = new SoundEffect();
    
    
    
    public GestionFenetre (){     // constructeur de la fenêtre principale
        
                
        jeu = new Jeu();
        
        this.setTitle("COV'INSA");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(tailleW,tailleH);
        this.setLocation(0,0);
        
        se.backGroundMusic("..//son//Renaud - Corona_song_(Officiel).wav");
        
        JPanel conteneurPrincipale = new JPanel();       // panneau Principale
        conteneurPrincipale.setBounds(0,0,tailleW,tailleH);
        conteneurPrincipale.setLayout(null);
        
        
        JPanel conteneurJoueurs = new JPanel();       // panneau des joueurs
        conteneurJoueurs.setBounds(0,0,resolW(600),resolH(1500));
        conteneurJoueurs.setLayout(null);
        conteneurJoueurs.setBackground(new Color(81,96,140)); // bleu fond
        
        panelJoueurs = new JPanel[4];      // tableau des panneaux de joueurs
        positionJoueurs = new JLabel[4];// tableau des titres des panneaux de joueurs
        cartes = new JButton[4][7];
        
        
            for (int i=0 ; i<4 ; i++){                      // on parcoure les joueurs pour remplir leurs panneaux respectifs
                panelJoueurs[i] = new JPanel();                                              // panneau du joueur [i]
                panelJoueurs[i].setBounds(resolW(20),resolH(20+(375)*i),resolW(560),resolH(335));
                panelJoueurs[i].setLayout(null);
                panelJoueurs[i].setBackground(new Color(191,205,217)); // gris claire
                
                positionJoueurs[i] = new JLabel ("Joueur "+(i+1)+" se trouve en : "+jeu.tabJoueurs[i].position.toString());   //titres des panneaux de joueur [i]
                positionJoueurs[i].setFont(new java.awt.Font(Font.SERIF,Font.BOLD,resolW(20)));
                positionJoueurs[i].setBounds(resolW(10),resolH(10),resolW(560),resolH(40));
                positionJoueurs[i].setLayout(null);
                positionJoueurs[i].setBackground(Color.red);
                panelJoueurs[i].add(positionJoueurs[i]);
                
                
                for (int j=0 ; j<4 ; j++){                                               // On parcours les cartes de chaque joueurs en deux temps (car deux lignes de cartes)
                    // carte 1 du joueur N
                    cartes[i][j] = new JButton();
                    cartes[i][j].setBounds(resolW(8+(j)*138),resolH(60),resolW(130),resolH(120));
                    cartes[i][j].setLayout(null);
                    cartes[i][j].addActionListener(this);
                    cartes[i][j].setFont(new java.awt.Font(Font.SERIF,Font.BOLD,resolW(40)));
                    panelJoueurs[i].add(cartes[i][j]);
                    
                                    
                }
                for (int j=4 ; j<7 ; j++){
                                           
                    // carte  du joueur N
                    cartes[i][j] = new JButton();
                    cartes[i][j].setBounds(resolW(8+(j-4)*138),resolH(200),resolW(130),resolH(120));
                    cartes[i][j].setLayout(null);
                    cartes[i][j].addActionListener(this);
                    cartes[i][j].setFont(new java.awt.Font(Font.SERIF,Font.BOLD,resolW(40)));
                    panelJoueurs[i].add(cartes[i][j]);
                                            
                }
                
                
                conteneurJoueurs.add(panelJoueurs[i]);
            }
        
        panelJoueurs[jeu.joueurActif].setBorder(BorderFactory.createMatteBorder(resolW(5), resolW(5), resolH(5), resolH(5), Color.RED));
        
        
        
        for(int i=0; i<jeu.tabJoueurs.length;i++){
            actualiserMain(i);
        }
        

        
            // panneau de la carte
        JPanel conteneurPhoto = new JPanel();         
        conteneurPhoto.setBounds(resolW(600),0,resolW(2100),resolH(1500));
        conteneurPhoto.setLayout(null);
        conteneurPhoto.setBackground(Color.red);
        
            
            
            
            // Points de la carte
           int[][] coordPoints = { 
                                                    {resolW(1935) , resolH(633)},  // point 0
                                                    {resolW(1716) , resolH(520)},  // point 1
                                                    {resolW(1860) , resolH(293)},  // point 2
                                                    {resolW(1447) , resolH(251)},  // point 3
                                                    {resolW(1458) , resolH(493)},  // point 4
                                                    {resolW(1659) , resolH(746)},  // point 5
                                                    {resolW(1361) , resolH(789)},  // point 6
                                                    {resolW(1170) , resolH(701)},  // point 7
                                                    {resolW(1099) , resolH(522)},  // point 8
                                                    {resolW(1232) , resolH(316)},  // point 9
                                                    {resolW(839) , resolH(253)},  // point 10
                                                    {resolW(941) , resolH(426)},  // point 11
                                                    {resolW(915) , resolH(736)},  // point 12
                                                    {resolW(736) , resolH(620)},  // point 13
                                                    {resolW(736) , resolH(918)},  // point 14
                                                    {resolW(592) , resolH(1039)},  // point 15
                                                    {resolW(321) , resolH(1187)},  // point 16
                                                    {resolW(398) , resolH(930)},  // point 17
                                                    {resolW(207) , resolH(925)},  // point 18
                                                    {resolW(106) , resolH(676)},  // point 19
                                                    {resolW(319) , resolH(609)},  // point 20
                                                    {resolW(532) , resolH(696)},  // point 21
                                                    {resolW(455) , resolH(459)},  // point 22
                                                    {resolW(666) , resolH(410)},  // point 23
                                                    {resolW(547) , resolH(164)},  // point 24
                                                    {resolW(122) , resolH(151)},  // point 25
                                                    {resolW(125) , resolH(451)},  // point 26                                                     
                                                    
                                                };
           coordPointsCarte= new int[27][2];
           for (int i=0; i<coordPointsCarte.length;i++){
				for (int y=0; y<coordPointsCarte[0].length;y++){
					coordPointsCarte[i][y] = coordPoints[i][y];
				}
			}
                                                
            
            
            pointsCarte = new JButton[27];  // tableau des JLabel pour chacun des points de la carte
            
            for (int k =0 ; k<27 ; k++){
                pointsCarte[k] = new JButton();
                pointsCarte[k].setBounds(coordPointsCarte[k][0]-resolW(15),coordPointsCarte[k][1],resolW(110),resolH(90));
                pointsCarte[k].setFont(new java.awt.Font(Font.SERIF,Font.BOLD,resolW(55)));
                pointsCarte[k].setForeground(Color.black);
                
                pointsCarte[k].setOpaque(false);
			    pointsCarte[k].setContentAreaFilled(false);
			    pointsCarte[k].setBorderPainted(false);
			    pointsCarte[k].addActionListener(this);
			    pointsCarte[k].setLayout(null);
                
                if(jeu.plateau.tabLieu[k].centreRecherche == true) {
                    pointsCarte[k].setText("C.R.");
                }else{
                    pointsCarte[k].setText(""+jeu.plateau.tabLieu[k].nbMaladie );
                }
                
                
                conteneurPhoto.add(pointsCarte[k]);
                
            }
            
            pionJoueurs = new JLabel[4];
            int i =0;            
            int t =30;
            
            ImageIcon imageJ1 = new ImageIcon(new ImageIcon("..//image//rouge.png").getImage().getScaledInstance(resolW(t), resolH(t), Image.SCALE_DEFAULT));
            pionJoueurs[0] = new JLabel();
            pionJoueurs[0].setIcon(imageJ1);     
            pionJoueurs[0].setBounds(coordPointsCarte[i][0]+resolW(5),coordPointsCarte[i][1]+resolW(45),resolW(t),resolH(t));
            conteneurPhoto.add(pionJoueurs[0]);
            
             ImageIcon imageJ2 = new ImageIcon(new ImageIcon("..//image//bleu.png").getImage().getScaledInstance(resolW(t), resolH(t), Image.SCALE_DEFAULT));
            pionJoueurs[1] = new JLabel();
            pionJoueurs[1].setIcon(imageJ2);     
            pionJoueurs[1].setBounds(coordPointsCarte[i][0]+resolW(5),coordPointsCarte[i][1]+resolW(15),resolW(t),resolH(t));
            conteneurPhoto.add(pionJoueurs[1]);
            
            ImageIcon imageJ3 = new ImageIcon(new ImageIcon("..//image//vert.png").getImage().getScaledInstance(resolW(t), resolH(t), Image.SCALE_DEFAULT));
            pionJoueurs[2] = new JLabel();
            pionJoueurs[2].setIcon(imageJ3);     
            pionJoueurs[2].setBounds(coordPointsCarte[i][0]+resolW(50),coordPointsCarte[i][1]+resolW(15),resolW(t),resolH(t));
            conteneurPhoto.add(pionJoueurs[2]);
            
            
            ImageIcon imageJ4 = new ImageIcon(new ImageIcon("...//image//violet.png").getImage().getScaledInstance(resolW(t), resolH(t), Image.SCALE_DEFAULT));
            pionJoueurs[3] = new JLabel();
            pionJoueurs[3].setIcon(imageJ4);     
            pionJoueurs[3].setBounds(coordPointsCarte[i][0]+resolW(50),coordPointsCarte[i][1]+resolW(45),resolW(t),resolH(t));
            conteneurPhoto.add(pionJoueurs[3]);
            
            positionJoueurs[0].setIcon(imageJ1);
            positionJoueurs[1].setIcon(imageJ2);
            positionJoueurs[2].setIcon(imageJ3);
            positionJoueurs[3].setIcon(imageJ4);
           
            
            
            
            //ajout photo de la carte 
            ImageIcon image = new ImageIcon(new ImageIcon("..//image//pandemic.jpg").getImage().getScaledInstance(resolW(2100), resolH(1500), Image.SCALE_DEFAULT));
            JLabel photo = new JLabel();
            photo.setIcon(image);     
            photo.setBounds(0,0,resolW(2100),resolH(1500));
            conteneurPhoto.add(photo);
            
            
            
           

            
        
        
        
    // Panneau d'interactions :__________________________________________________________________________________
        JPanel conteneurInteraction = new JPanel();   // panneau des questions/Réponses
        conteneurInteraction.setBounds(0,resolH(1500),resolW(2700),resolH(200));
        conteneurInteraction.setLayout(null);
        conteneurInteraction.setBackground(new Color(81,96,140)); // bleu fond
        
            questions = new JLabel("A toi de jouer joueur 1 !");
            questions.setBounds(resolW(20),resolH(20),resolW(2600),resolH(160));
            questions.setLayout(null);
            questions.setBackground(new Color(191,205,217)); // gris claire
            questions.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,resolW(25)));
            questions.setOpaque(true);
            questions.setForeground(Color.black);
            conteneurInteraction.add(questions);

            
        
        
    // Panneau d'actions :__________________________________________________________________________________
        JPanel conteneurActions = new JPanel();        // panneau des Actions/avancement jeux
        conteneurActions.setBounds(resolW(1600),resolH(1000),resolW(1100),resolH(500));
        conteneurActions.setLayout(null);
        conteneurActions.setBackground(new Color(81,96,140)); // bleu fond
        
        // Informations avancement jeux :____________________________________________________________________________
        
            eclosion = new JLabel("Avancement de l'Eclosion : 0/8");       // Eclosion  (!!!!!!!!!!!!!variable d'eclosion à ajouter)
            eclosion.setBounds(resolW(100),resolH(20),resolW(400),resolH(50));
            eclosion.setLayout(null);
            eclosion.setForeground(Color.white);
            eclosion.setFont(new Font("Arial", Font.BOLD, resolW(25)));
            conteneurActions.add(eclosion);
        
            vitessePropagation = new JLabel("Vitesse de propagation : 2");       // Vitesse propagation  (!!!!!!!!!!!!!variable de Vitesse de propagation à ajouter)
            vitessePropagation.setBounds(resolW(100),resolH(90),resolW(400),resolH(50));
            vitessePropagation.setLayout(null);
            vitessePropagation.setForeground(Color.white);
            vitessePropagation.setFont(new Font("Arial", Font.BOLD, resolW(25)));
            conteneurActions.add(vitessePropagation);
			
            nbCartePiocheJoueur = new JLabel("Pioche joueur cartes restantes : "+this.jeu.piocheJoueur.paquet.size());       // Nombre de cartes restantes dans la pioche joueur
            nbCartePiocheJoueur.setBounds(resolW(100),resolH(160),resolW(405),resolH(50));
            nbCartePiocheJoueur.setLayout(null);
            nbCartePiocheJoueur.setForeground(Color.white);
            nbCartePiocheJoueur.setFont(new Font("Arial", Font.BOLD, resolW(25)));
            conteneurActions.add(nbCartePiocheJoueur);
            
			ImageIcon imageRemede = new ImageIcon(new ImageIcon("..//image//Remede_notFound.png").getImage().getScaledInstance(resolW(250), resolH(150), Image.SCALE_DEFAULT));
            remede = new JLabel(""); 
            remede.setIcon(imageRemede);      // Remede : changer le background et le text dedans si on gagne
            remede.setBounds(resolW(100),resolH(200),resolW(350),resolH(200));
            conteneurActions.add(remede);
            
            ImageIcon imageMusique = new ImageIcon(new ImageIcon("..//image//musicOn.png").getImage().getScaledInstance(resolW(100), resolH(100), Image.SCALE_DEFAULT));
            muteMusic = new JButton("");
            muteMusic.setIcon(imageMusique);
            muteMusic.setBounds(resolW(350),resolH(250),resolW(100),resolH(100));
            muteMusic.setLayout(null);
            muteMusic.setBackground(new Color(134,151,166)); // gris foncé
            muteMusic.addActionListener(this); 
			conteneurActions.add(muteMusic);
        
        
        // Boutons d'actions :____________________________________________________________________________
            
            phasePioche = new JButton("Piocher carte");       // bouton piocher carte
            phasePioche.setBounds(resolW(600),resolH(20),resolW(400),resolH(100));
            phasePioche.setLayout(null);
            phasePioche.setBackground(new Color(134,151,166)); // gris foncé
            phasePioche.setFont(new Font("Arial", Font.BOLD, resolW(25)));
            phasePioche.setBorder(new RoundBtn(15)); 
            phasePioche.addActionListener(this); 
            conteneurActions.add(phasePioche);
            
            placerCentre = new JButton("Placer un centre");       // bouton placer centre
            placerCentre.setBounds(resolW(600),resolH(140),resolW(400),resolH(100));
            placerCentre.setLayout(null);
            placerCentre.setBackground(new Color(134,151,166)); // gris foncé
            placerCentre.setFont(new Font("Arial", Font.BOLD, resolW(25)));
            placerCentre.setBorder(new RoundBtn(15)); 
            placerCentre.addActionListener(this); 
            conteneurActions.add(placerCentre);
            
            traiterMaladie = new JButton("Traiter maladie");       // bouton taiter maladie
            traiterMaladie.setBounds(resolW(600),resolH(260),resolW(400),resolH(100));
            traiterMaladie.setLayout(null);
            traiterMaladie.setBackground(new Color(134,151,166)); // gris foncé
            traiterMaladie.setFont(new Font("Arial", Font.BOLD, resolW(25)));
            traiterMaladie.setBorder(new RoundBtn(15));
            traiterMaladie.addActionListener(this);  
            conteneurActions.add(traiterMaladie);
            
            creerRemede = new JButton("Creer remede");       // bouton Créer remede
            creerRemede.setBounds(resolW(600),resolH(380),resolW(400),resolH(100));
            creerRemede.setLayout(null);
            creerRemede.setBackground(new Color(134,151,166)); // gris foncé
            creerRemede.setFont(new Font("Arial", Font.BOLD, resolW(25)));
            creerRemede.setBorder(new RoundBtn(15));
            creerRemede.addActionListener(this);  
            conteneurActions.add(creerRemede);
            
            BtAide = new JButton("Aide");       // bouton aide qui ouvre une fenêtre d'aide
            BtAide.setBounds(resolW(100),resolH(404),resolW(400),resolH(76));
            BtAide.setLayout(null);
            BtAide.setBackground(new Color(134,151,166)); // gris foncé
            BtAide.setFont(new Font("Arial", Font.BOLD, resolW(25)));
            BtAide.setBorder(new RoundBtn(15)); 
            BtAide.addActionListener(this);    
            conteneurActions.add(BtAide);
            
          
            
        
        
             
        
        conteneurPrincipale.add(conteneurInteraction);
        conteneurPrincipale.add(conteneurActions);
        conteneurPrincipale.add(conteneurJoueurs);
        conteneurPrincipale.add(conteneurPhoto);
        
        this.add(conteneurPrincipale);
        this.setVisible(true);
        
       
    
    }
    
    public void actionPerformed(ActionEvent e){
       
       //Aide
       if (e.getSource() == BtAide && jeu.etatDuJeu) {
          FenetreAide fenetreAide = new FenetreAide();
        
       }
       if (e.getSource() == muteMusic && jeu.etatDuJeu) {
         se.stop();
         actuliserMuteMusic();
       }
       
       //Deplacement
       
		se.play("..//son//button.wav");

		for(int i=0; i<pointsCarte.length; i++){
			
			if (e.getSource() == pointsCarte[i] && jeu.tabJoueurs[jeu.joueurActif].nbAction!=0 && jeu.etatDuJeu) {
                System.out.println(i);
				boolean deplacementFait;
            
				//fenetre de dialogue qui demande le type de déplacement
				String [] choix = {"déplacement normal","utiliser la carte du lieu d'arrivée","utiliser la carte de mon lieu pour aller n'importe où"};
            
				int typeDeplacement = JOptionPane.showOptionDialog(null, "Quel type de déplacement voulez-vous effectuer ?", "Type de déplacement", 
                                                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, choix, choix[0]);
            
				//si le joueur n'a pas appuyer sur la croix de la fenetre précédente
				if(typeDeplacement!= -1){                          
                    
						deplacementFait = jeu.deplacement(i, typeDeplacement);
                    
						if(deplacementFait){
							actualiserPosition(jeu.joueurActif);
							actualiserMain(jeu.joueurActif);
							questions.setText("Il vous reste "+jeu.tabJoueurs[jeu.joueurActif].nbAction+" action(s)");
						}else{
							questions.setText("Avec le mode de deplacement choisi, vous ne pouvez pas aller sur le lieu numéro "+i);
						}
                    
                
                
				}
			}
		}
        
        
        //Partager des connaissances
        
        for(int i=0; i<cartes.length; i++){
                    
            for(int j=0; j<cartes[i].length;j++){
                
                //Donner la carte du lieu où le joueur actif se trouve
                if(e.getSource() == cartes[jeu.joueurActif][j] && jeu.tabJoueurs[jeu.joueurActif].nbAction!=0 && jeu.etatDuJeu){
                    
                    int numeroCarte=Integer.parseInt(cartes[i][j].getText());
                    
                    //demander à quel joueur il faut donner la carte
                    
                    if(jeu.tabJoueurs[jeu.joueurActif].position.position==numeroCarte) {
                        String joueurReceveur = JOptionPane.showInputDialog(
                                    null, "Avec quel joueur voulez-vous donner la carte (entrer son numéro)","Choix du joueur",
                                        JOptionPane.PLAIN_MESSAGE);
                                             
                        int numeroJoueur = stringToInt(joueurReceveur);
                            
                        if(numeroJoueur==-1 || numeroJoueur>4 || numeroJoueur<1 || (numeroJoueur-1)==jeu.joueurActif || jeu.tabJoueurs[numeroJoueur-1].position.position!=numeroCarte){
                            questions.setText("Il faut rentrer le numéro d'un autre joueur se trouvant sur votre case");
                        }
                        
                        else {
                        
                            jeu.tabJoueurs[jeu.joueurActif].echanger(jeu.tabJoueurs[numeroJoueur-1], numeroCarte);
                            jeu.tabJoueurs[jeu.joueurActif].nbAction--;
                            actualiserMain(jeu.joueurActif);
                            actualiserMain(numeroJoueur-1);
                            
                            //Defausser si plus de 5 cartes
                            defausser(numeroJoueur-1);
                            
                            //Affichage du nombre d'actions restantes
                            questions.setText("Il vous reste "+jeu.tabJoueurs[jeu.joueurActif].nbAction+" action(s)");
                        }
                        
                    } else {
                        questions.setText("Vous devez donner la carte du lieu où vous vous trouvez");
                    }
                    
                }
                
                //Recevoir la carte d'un autre joueur se trouvant sur la même case. Cette carte doit être celle du lieu où ils se trouvent         
                if(e.getSource() == cartes[i][j] && i!=jeu.joueurActif && jeu.tabJoueurs[jeu.joueurActif].nbAction!=0 && jeu.etatDuJeu){
                    
                    int numeroCarte = Integer.parseInt(cartes[i][j].getText());
                    
                    if(jeu.tabJoueurs[jeu.joueurActif].position.position==numeroCarte && jeu.tabJoueurs[i].position.position==numeroCarte){
                        
                        jeu.tabJoueurs[i].echanger(jeu.tabJoueurs[jeu.joueurActif], numeroCarte);
                        jeu.tabJoueurs[jeu.joueurActif].nbAction--;
                        actualiserMain(jeu.joueurActif);
                        actualiserMain(i);
                        
                        //Defausser si plus de 5 cartes
                        defausser(jeu.joueurActif);
                        
                        //Affichage du nombre d'actions restantes
                        questions.setText("Il vous reste "+jeu.tabJoueurs[jeu.joueurActif].nbAction+" action(s)");
                        
                    } else {
                        questions.setText("Vous ne pouvez recevoir que la carte d'un autre joueur se trouvant sur la même case. Cette carte doit être celle du lieu où vous vous trouvez ");
                    }
                }
                        
                    
            }
        }
        
        //Traitement            
        if (e.getSource() == traiterMaladie && jeu.tabJoueurs[jeu.joueurActif].nbAction!=0 && jeu.etatDuJeu) {
            
            jeu.tabJoueurs[jeu.joueurActif].traitement(jeu.plateau.remedeTrouve); 
            jeu.plateau.isZoneCleared(jeu.tabJoueurs[jeu.joueurActif].position.zone);      
            actualiserPlateau();
            
            questions.setText("Il vous reste "+jeu.tabJoueurs[jeu.joueurActif].nbAction+" action(s)");
            
        }
        
        //Placement d'un centre            
        if (e.getSource() == placerCentre && jeu.tabJoueurs[jeu.joueurActif].nbAction!=0 && jeu.etatDuJeu) {
            
            
            jeu.tabJoueurs[jeu.joueurActif].placerCentre();     
            actualiserPlateau();
            actualiserMain(jeu.joueurActif);
            
            questions.setText("Il vous reste "+jeu.tabJoueurs[jeu.joueurActif].nbAction+" action(s)");
                       
        }
        
        //Découverte remède
        if (e.getSource() == creerRemede && jeu.tabJoueurs[jeu.joueurActif].nbAction!=0 && jeu.etatDuJeu) {
            
            jeu.trouverRemede(); 
            actualiserRemede();
            actualiserMain(jeu.joueurActif);
            
            questions.setText("Il vous reste "+jeu.tabJoueurs[jeu.joueurActif].nbAction+" action(s)");
        
        }
        
        //PhasePiocheJoueur
        if (e.getSource() == phasePioche && jeu.etatDuJeu) {
            
            jeu.piocherJoueur();
            actualiserMain(jeu.joueurActif);
            actualiserNbCartePiocheJoueur();
            
            //Defausser si plus de 5 cartes
            defausser(jeu.joueurActif);
            
            //Propagation du virus
            jeu.propagation();
            actualiserPlateau();
            actualiserEclosion();
            actualiserVitessePropa();
            
            panelJoueurs[jeu.joueurActif].setBorder(BorderFactory.createMatteBorder(resolW(1), resolW(1), resolH(1), resolH(1), Color.BLACK));
            jeu.tourSuivant();
            questions.setText("A toi de jouer joueur "+(jeu.joueurActif+1)+" !");
            panelJoueurs[jeu.joueurActif].setBorder(BorderFactory.createMatteBorder(resolW(5), resolW(5), resolH(5), resolH(5), Color.RED));
        } 
       
	}
    
    public static int stringToInt(String valeur) {
            
        try {
            return Integer.parseInt(valeur);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    public void defausser(int numeroJoueur) {
        
        int nbCarteMainJoueur = jeu.tabJoueurs[numeroJoueur].main.size();
        int numeroCarteADefausser;
                            
        while(nbCarteMainJoueur>5){
                                
        // Lui demander d'indiquer les cartes à enlever
            String carteADefausser = JOptionPane.showInputDialog( null, "Quelle carte voulez-vous défausser joueur "+(numeroJoueur+1)+"? (entrer le numéro du lieu)","Choix de la carte à défausser", JOptionPane.PLAIN_MESSAGE);
                                
            numeroCarteADefausser = stringToInt(carteADefausser);
                                
            if(numeroCarteADefausser==-1 || !jeu.tabJoueurs[numeroJoueur].possedeCarte(numeroCarteADefausser)){
                
                JOptionPane.showMessageDialog(this, "Vous devez rentrer le numéro d'une carte que vous possédez pour la défausser");
                
            } else {
                
                jeu.tabJoueurs[numeroJoueur].supprimerCarte(numeroCarteADefausser);                    
                nbCarteMainJoueur=jeu.tabJoueurs[numeroJoueur].main.size();
                actualiserMain(numeroJoueur);
                
            }
                                 
        }
        
        
    }
    
   public void actualiserMain(int numJoueur){
        
        cartes[numJoueur][0].setText(" ");
        
        for(int i=0; i<jeu.tabJoueurs[numJoueur].main.size();i++){
            
            cartes[numJoueur][i].setText(""+jeu.tabJoueurs[numJoueur].main.get(i).numCarte);
                
            if(jeu.tabJoueurs[numJoueur].main.get(i).zoneCarte.equals("rose")){
                cartes[numJoueur][i].setBackground(pink);
            }else if(jeu.tabJoueurs[numJoueur].main.get(i).zoneCarte.equals("bleu")){
                cartes[numJoueur][i].setBackground(blue);
            }else if(jeu.tabJoueurs[numJoueur].main.get(i).zoneCarte.equals("vert")){
                cartes[numJoueur][i].setBackground(green);
            }else if(jeu.tabJoueurs[numJoueur].main.get(i).zoneCarte.equals("violet")){
                cartes[numJoueur][i].setBackground(purple);
            }
            cartes[numJoueur][i].setBorderPainted(true);
            cartes[numJoueur][i].addActionListener(this);
            
        }
        
        //retirer les cartes en trop
        for(int i=jeu.tabJoueurs[numJoueur].main.size(); i<cartes[numJoueur].length;i++){
            cartes[numJoueur][i].setText(" ");
            cartes[numJoueur][i].setBackground(null);
            cartes[numJoueur][i].setBorderPainted(false);
            cartes[numJoueur][i].removeActionListener(this);
        }
        
  }   
    
	public void actuliserMuteMusic(){
		
		ImageIcon imageMusique; 
		if (se.clipMusique.isRunning()){
			imageMusique = new ImageIcon(new ImageIcon("..//image//musicOn.png").getImage().getScaledInstance(resolW(100), resolH(100), Image.SCALE_DEFAULT));
		}
		else{
			imageMusique = new ImageIcon(new ImageIcon("..//image//musicOff.png").getImage().getScaledInstance(resolW(100), resolH(100), Image.SCALE_DEFAULT));
		}
		muteMusic.setIcon(imageMusique);
	}
    
    public void actualiserPlateau(){
        
        for (int k =0 ; k<27 ; k++){ 
            
            if(jeu.plateau.tabLieu[k].centreRecherche == true) {
				ImageIcon imageCentre = new ImageIcon(new ImageIcon("..//image//centre_Recherche.png").getImage().getScaledInstance(resolW(60), resolH(60), Image.SCALE_DEFAULT));
                pointsCarte[k].setText("");
                pointsCarte[k].setIcon(imageCentre);            }
            else{
				pointsCarte[k].setText(""+jeu.plateau.tabLieu[k].nbMaladie );
			}
			
            if(jeu.plateau.zoneCleared.contains(jeu.plateau.tabLieu[k].zone)){
				pointsCarte[k].setForeground(Color.green);
				pointsCarte[k].setFont(new java.awt.Font(Font.DIALOG,Font.BOLD,resolW(65)));
			}
            else if(jeu.lieuPropa.contains(k)){
				pointsCarte[k].setForeground(Color.red);
				pointsCarte[k].setFont(new java.awt.Font(Font.DIALOG,Font.BOLD,resolW(65)));
			}
            
            else{                
                pointsCarte[k].setForeground(Color.black);
				pointsCarte[k].setFont(new java.awt.Font(Font.SERIF,Font.BOLD,resolW(55)));
            }

        }
        
    }
    
    public void actualiserRemede(){
        
        if(jeu.plateau.remedeTrouve) {
            ImageIcon imageRemede = new ImageIcon(new ImageIcon("..//image//Remede_found.png").getImage().getScaledInstance(resolW(250), resolH(150), Image.SCALE_DEFAULT));
            remede.setIcon(imageRemede); 
        }
        
    }
    
    public void actualiserPosition(int numJoueur){
        
        positionJoueurs[numJoueur].setText("Joueur "+(numJoueur+1)+" se trouve en : "+jeu.tabJoueurs[numJoueur].position.toString());
        
        int posJ =jeu.tabJoueurs[numJoueur].position.position;
        
        if (numJoueur ==0){
			pionJoueurs[0].setLocation(coordPointsCarte[posJ][0]+resolW(5),coordPointsCarte[posJ][1]+resolW(45));
		}
		if (numJoueur ==1){
			pionJoueurs[1].setLocation(coordPointsCarte[posJ][0]+resolW(5),coordPointsCarte[posJ][1]+resolW(15));
		}
		if (numJoueur ==2){
			pionJoueurs[2].setLocation(coordPointsCarte[posJ][0]+resolW(50),coordPointsCarte[posJ][1]+resolW(15));
		}
		if (numJoueur ==3){
			pionJoueurs[3].setLocation(coordPointsCarte[posJ][0]+resolW(50),coordPointsCarte[posJ][1]+resolW(45));
		}
        
    }
    
    public void actualiserNbCartePiocheJoueur(){
        nbCartePiocheJoueur.setText("Pioche joueur cartes restantes : "+this.jeu.piocheJoueur.paquet.size());
    }
    
    public void actualiserEclosion(){       
        eclosion.setText("Avancement de l'Eclosion : "+this.jeu.plateau.nbEclosion+"/8");
	}
	
    public void actualiserVitessePropa(){       
        vitessePropagation.setText("Vitesse de propagation : "+this.jeu.vitessePropa);;
	}
	
    public int resolW(int n) {
        
        return (int)(n*tailleW/2750);
        
    }
    
    public int resolH(int n) {
        
        return (int)(n*tailleH/1750);
        
    }
    
    class RoundBtn implements Border{
        private int r;
        RoundBtn(int r) {
            this.r = r;
        }
        public Insets getBorderInsets(Component c) {
            return new Insets(this.r+1, this.r+1, this.r+2, this.r);
        }
        public boolean isBorderOpaque() {
            return true;
        }
        public void paintBorder(Component c, Graphics g, int x, int y, 
        int width, int height) {
            g.drawRoundRect(x, y, width-1, height-1, r, r);
        }
    }
}
    
    
