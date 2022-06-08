// Chargement des bibliothèques Swing et AWT 

import java.util.*; 
import javax.swing.*;
import java.awt.event.*; 
import java.awt.*;

public class Jeu {
  
    //attributs
    boolean etatDuJeu;
    int nbTours;
    int vitessePropa;
    Monde plateau;
    PiochePropagation piochePropa;
    PiocheJoueur piocheJoueur;
    Joueur [] tabJoueurs;
    LinkedList<Integer> lieuPropa = new LinkedList<Integer>();//Lieux tirrés lors de la phase propogation (utilisé pour les affciher en rouge sur la carte) 


    int joueurActif;
    
    //constructeur
    public Jeu () {
                   
        etatDuJeu = true;
        nbTours = 0;
        vitessePropa=2;
       
        joueurActif=0;
        
        //création du monde
        plateau = new Monde("..//file//fichierLieu.txt");      
            
        // constituer la pioche propagation de départ
        piochePropa = new PiochePropagation(plateau.tabLieu);
        
         //premières propagations 
        initialise(piochePropa);
        
        //constituer la pioche joueur
        piocheJoueur = new PiocheJoueur(plateau.tabLieu);
        
        // préparation des joueurs
        
        tabJoueurs = new Joueur [4];
        
        for (int i=0;i<tabJoueurs.length;i++){
            tabJoueurs[i] = new Joueur(plateau.tabLieu[0]);                 
            tabJoueurs[i].ajouterCarte((CarteLieu) piocheJoueur.piocher());
            tabJoueurs[i].ajouterCarte((CarteLieu) piocheJoueur.piocher());
        }
        
        
    
    }
    
    //premières propagations
    public void initialise(PiochePropagation p){
        for(int i=0;i<3;i++){
            for(int j=0;j<2;j++){
                CarteLieu c = (CarteLieu) p.piocher();			
					plateau.tabLieu[c.numCarte].ajoutMaladie(3-i);				
            }
        }
    }
    
    //déplacement
    public boolean deplacement(int arrivee, int typeDeplacement){
        
        boolean deplacementFait = false;
        
        //par lieux voisins
        if(typeDeplacement==0){
        
            int nbActionsRestantes = plateau.deplacementPossible(tabJoueurs[joueurActif].position, plateau.tabLieu[arrivee], tabJoueurs[joueurActif].nbAction); 
            
            if(nbActionsRestantes!=-1){
                
                tabJoueurs[joueurActif].deplacement(plateau.tabLieu[arrivee], nbActionsRestantes);  

                deplacementFait = true;
            } 
        }
        
        //en utilisant la carte du lieu d'arrivée
        else if(typeDeplacement==1 && tabJoueurs[joueurActif].possedeCarte(arrivee)){
            
            int nbActionsRestantes = tabJoueurs[joueurActif].nbAction-1;
            tabJoueurs[joueurActif].deplacement(plateau.tabLieu[arrivee], nbActionsRestantes);  
            tabJoueurs[joueurActif].supprimerCarte(arrivee);                        
            
            deplacementFait = true;
            
        }
        
        //en utilisant la carte de mon lieu pour aller n'importe où
        else if(typeDeplacement==2 && tabJoueurs[joueurActif].possedeCarte(tabJoueurs[joueurActif].position.position)){
            
            int nbActionsRestantes = tabJoueurs[joueurActif].nbAction-1;
            tabJoueurs[joueurActif].deplacement(plateau.tabLieu[arrivee], nbActionsRestantes);
            tabJoueurs[joueurActif].supprimerCarte(tabJoueurs[joueurActif].position.position);
            
            
            deplacementFait = true;
            
        }
        return deplacementFait;
        
    }                     
    
    public int piocherJoueur() {
        
        lieuPropa.clear();
        
        for(int i=0; i<2; i++){
            
            //si la pioche joueur n'est pas vide, on pioche une carte
            if(!piocheJoueur.paquet.isEmpty()){
                
                Carte cartePioche = piocheJoueur.piocher();
                    
                    /*si la carte piochée est un lieu, on l'ajoute à la main du joueur, sinon une épidémie a lieu on doit alors :
                     * augmenter la vitesse de propagation de 1
                     * piocher la dernière carte de la pioche propagation et y ajouter autant de maladie nécessaire pour atteindre un nb de 3 maladies sur cette case
                     * remelanger la pioche propagation
                     * afficher la fenêtre épidémie pour mettre le joueur au courant
                    */  
                    if(cartePioche instanceof CarteLieu){
                        
                        tabJoueurs[joueurActif].ajouterCarte((CarteLieu) cartePioche);
                            
                    }else{
                        vitessePropa=vitessePropa+1;
                        CarteLieu cEpidemie = (CarteLieu) piochePropa.piocherEpidemie();
                        plateau.propagation(cEpidemie.numCarte, 3);
                        lieuPropa.add(cEpidemie.numCarte);                                
                        piochePropa.remelanger();
                        FenetreEpidemie epidemie = new FenetreEpidemie(joueurActif+1,cEpidemie.numCarte);
                    }
            }
        }
        return tabJoueurs[joueurActif].main.size();
    }
                
    //phase propagation
    public void propagation(){
        
        int nombreCarteAPiocher=vitessePropa;        
        
        // on pioche et on effectue la propagation autant de fois que la vitesse de propagation
        while(nombreCarteAPiocher!=0){
            
            CarteLieu c = (CarteLieu) piochePropa.piocher();
            plateau.propagation(c.numCarte,1);
            lieuPropa.add(c.numCarte);
            nombreCarteAPiocher=nombreCarteAPiocher-1;
            
        }
        
    }
    
    public void tourSuivant() {
    
        testFinJeu();
        
        if(etatDuJeu){
            nbTours++;
            tabJoueurs[joueurActif].nbAction=4;
            joueurActif = nbTours % 4;
        }
                
    }
    
    //tester si perdu => etatjeu false +fenetre si gagné pareil
    public void testFinJeu(){
        
        if(plateau.nbMaladieTotal>47 || piocheJoueur.paquet.isEmpty() || plateau.nbEclosion>=8 ){
            etatDuJeu=false;
            FenetreDefaite fenetrePerdu = new FenetreDefaite ();
        } else if (plateau.remedeTrouve && plateau.nbMaladieTotal<5){
            etatDuJeu=false;
            FenetreVictoire fenetreGagne = new FenetreVictoire ();
        }
        
    }
    
    public void trouverRemede(){
		if (!plateau.remedeTrouve){
			if (tabJoueurs[joueurActif].trouverRemede()){
				this.plateau.remedeTrouve =true;
			}
		}
	}
    
}
