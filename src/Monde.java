import java.util.*;
import java.awt.*;
import java.io.*;



public class Monde {	
	
	public Lieu [] tabLieu;// Tableau contenants les lieux du monde
	public int nbMaladieTotal;
	public int nbEclosion;
	public boolean remedeTrouve;
	public LinkedList<String> zoneCleared = new LinkedList<String>(); // contient les zones qui n'ont plus de maladies ( si le remède est trouvé )
	
	
	public Monde(String nomFichier) {
		
		this.tabLieu = new Lieu[1];	
		
		//Création du monde à l'aide d'un fichier texte
		this.read(nomFichier);
		this.nbEclosion=0;		
		this.remedeTrouve=false;
		
	}
	
	//Gère la propagation 
	public void propagation( int position, int nbMaladieAAjouter){
						
		ArrayList<Lieu> lieuTouche=new ArrayList<Lieu>();
		lieuTouche.add(tabLieu[position]);//lieux qui vont être touche par cette propagation	
		Lieu l;
		
		//Si la zone n'est pas "soignée" (toutes les maladies de la zone ont été enlevées et le remèdes est trouvré)
		if (!zoneCleared.contains(lieuTouche.get(0).zone)){
			
			// Ajoute les maladies 	
			lieuTouche.get(0).ajoutMaladie(nbMaladieAAjouter-1);	
			
			//parcours les lieu touchées
			for (int i=0; i<lieuTouche.size(); i++){
			
				l = lieuTouche.get(i);
				
				//Si il n'y a pas éclosions					
				if (l.nbMaladie<3){
					l.ajoutMaladie(1);
					this.nbMaladieTotal++;						
				}
				
				//Si il ya a éclosion 
				else{
					this.nbEclosion++;
					//parcours les voisins de ce lieux
					for( int j =0; j<l.voisins.length; j++ ){
						//Si le voisins n'est pas encore touché, et s'il appartient à une zone non soignée
						if( !lieuTouche.contains(tabLieu[l.voisins[j]])&& !zoneCleared.contains(tabLieu[l.voisins[j]].zone)){
						lieuTouche.add(tabLieu[l.voisins[j]]);
						}
					}
				}
			}
		}		
	}
	
	// renvoie le nombre d'action restantes aprés avoir effectuer un déplacement normal ( déplacement case par case )
	// renvoie -1 si le déplamcement n'est pas possible avec le nombre d'action donné
	public int deplacementPossible(Lieu depart, Lieu arrive, int nbActions){
		//Liste de lieux traités 
		LinkedList<Lieu> T=new LinkedList<Lieu>();
		//Liste de lieux non traité 
		LinkedList<Lieu> NT=new LinkedList<Lieu>();
		int sortie = -1;
		int currentSize;
		
		NT.add(depart);
		
		
		int i =0;
		//Tant qu'on ne dépasse pas le nombre d'actions 
		while(i<nbActions){
			currentSize = NT.size();
			
			//Parcours des Lieux non traité
			for (int x=0; x< currentSize; x++){
				
				//Parcours les voisins du lieu 				
				for( int j =0; j<NT.getFirst().voisins.length; j++ ){
					
					//Si le voisin est l'arrivé :					
					if( tabLieu[NT.getFirst().voisins[j]].equals(arrive)){
						return nbActions - (i+1);
					}
					//si le voisin n'est pas déjà traité 
					else if (!T.contains(tabLieu[NT.getFirst().voisins[j]])) {
						
						NT.add(tabLieu[NT.getFirst().voisins[j]]);
					}					
				}
				T.add(NT.getFirst());
				NT.removeFirst();
			}
			i++;
		}
		return -1;
	
	}	
	
	/**lit le fichier texte en paramètre et initialise le tableau contenant les lieux 
	 * Le fichier texte ce présente comme cela :
	 * nomLieu0
	 * 0
	 * zone
	 * num voisin0
	 * num voisin1
	 * [...]
	 * num voisinN
	 * /
	 * nomLieu1
	 * 1
	 * zone
	 *zone
	 * num voisin0
	 * num voisin1
	 * [...]
	 * /
	 ***/	
	public void read(String file){
		
		
		int taille=0;
		try{
			BufferedReader br = new BufferedReader(new FileReader(file));			
			String ligne;
			//compte le nombre de lieu
			while ((ligne = br.readLine()) != null) {
				if (ligne.equals("/")){taille++;}
			}
			
			tabLieu = new Lieu[taille];
			
			BufferedReader br2 = new BufferedReader(new FileReader(file));						
			String nom;
			int position;
			String zone;
			int [] voisins;
			LinkedList<String> listV;
			
			
			//boucle initilisant un lieu à chaque itération
			for (int i=0; i<taille;i++){
				nom = br2.readLine();
				position = Integer.parseInt(br2.readLine());
				zone = br2.readLine();
				ligne = br2.readLine();	
				
				listV = new LinkedList<String>();
				while (!ligne.equals("/")){
					listV.add(ligne);
					ligne = br2.readLine();							
				}
				
				voisins = new int[listV.size()];
				int x =0;
				for (String v: listV){
					voisins[x] = Integer.parseInt(v);
					x++;
				} 
				
				//initilisation du lieu dans le tableau de lieu
				tabLieu[i] = new Lieu(nom, position, zone, voisins);
				System.out.println(nom+" "+position+" "+zone+" "+voisins.length);
			}
		}
		catch(IOException e){}
	}
	
	// compte le nombres de maladie présente dans le monde
	public int compterMaladie(){
		int total =0;
		for ( int i =0; i< tabLieu.length;i++){
				total+=tabLieu[i].nbMaladie;
		}
		return total;
	}
	
	// ajoute à la liste zoneCleared, la zone en paramètre si cette zone ne contient pas de maladie et que le remède est trouvé. 
	public void isZoneCleared( String z){
		boolean cleared = true;
		if (remedeTrouve){
			for (int i=0; i<tabLieu.length ; i++){
				if (tabLieu[i].zone.equals(z) && tabLieu[i].nbMaladie>0){
					cleared = false;
				}
			}
			if (cleared){
				zoneCleared.add(z);
			}
		}
	}
}

