
import java.util.*;
import java.awt.*;


public class Joueur {
	
	public Lieu position;
	public LinkedList<CarteLieu> main;
	public int nbAction;
	
	
	public Joueur ( Lieu posInitial) {
		
		this.position = posInitial;
		main=new LinkedList<CarteLieu>(); //main du joueur contenant ses cartes
        nbAction = 4;
        
	}
	
	// permet donner la carte de numéro nbC avec le joueur J2
	public void echanger( Joueur j2, int nbC){
		CarteLieu carte = this.main.getFirst();
		
		for(CarteLieu c: this.main){
			if(c.numCarte==nbC){
				carte=c;
			}				
		}
		j2.ajouterCarte(carte);
						
		this.main.remove(carte);
		
	}
	
	public void deplacement(Lieu arrivee, int nbAction){		
		this.position= arrivee;
		this.nbAction=nbAction;		
	} 
	
	public void ajouterCarte ( CarteLieu c){
		this.main.add(c);
	}
	
	//Supprime la carte de la main du joueur, correspondant au numéro du lieu 
	public void supprimerCarte ( int numCarte){
		for(CarteLieu c: this.main){
			if(c.numCarte==numCarte){
                this.main.remove(c);
                break;           
			}			
		}
	}	
	
	public void editNbAction(int nb){
		this.nbAction = nb ; 
	}

	/** Renvoie true si le joueur peux trouver un remède, c'est à dire:
	 * Si il possède trois cartes de la même couleur 
	 * Si il se trouve sur une centre de recherche **/
	 
	public boolean trouverRemede(){
		
		Collections.sort(this.main);
		boolean remedeFaisable=false;
		int nbSameZone=1;//compte le nombre de carte avec la même couleur 
		int firstPosition=0; //position de la première carte qui sera supprimer pour creer le remède
		int nbCarteNecessaire = 3; // nb de carte neccessaire pour creer un remède
		
		if (position.centreRecherche){
			
			for(int i=0; i<this.main.size()-1; i++){
				
				if(this.main.get(i).zoneCarte.equals(this.main.get(i+1).zoneCarte)){
					nbSameZone ++;
				}
				else { 
					nbSameZone =1;
				}
				
				if(nbSameZone >= (nbCarteNecessaire)){			
					remedeFaisable = true;
					firstPosition = i-(nbCarteNecessaire-2);
					nbAction--;
				}
			}
			
			//Si le remède est faisable supprimer les trois cartes 
			if ( remedeFaisable){
				for(int i =0; i<nbCarteNecessaire;i++){
					this.main.remove(firstPosition);
				}
			}			
		} 
		return remedeFaisable;
	}
	
	public boolean possedeCarte(int numC){
		for(CarteLieu c: this.main){
			if(c.numCarte==numC){
				return true;
			}				
		}	
		return false;	
	}
	
	/** Traite les maladie de la case ou se trouve le joueur
	 * Enlève une maladie si le remede n'est pas trouvé
	 * Enlève toutes les maladie si le remede est trouvé **/ 
	public void traitement(boolean remedeTrouve){
			if (remedeTrouve){
				position.setMaladie(0);
				this.nbAction --;
			}
			else{
				if (position.nbMaladie!=0){
					position.ajoutMaladie(-1);
					this.nbAction--;
				}
			}
	}
	
	//Si le joueur possède la carte du lieu où il se trouve : creer un centre sur ce lieu
	public void placerCentre(){
        if (this.position.centreRecherche == false && possedeCarte(position.position)) {
			position.addCentreRecherche();
			this.nbAction --; 
            supprimerCarte(position.position);
        }
	}
	
}

