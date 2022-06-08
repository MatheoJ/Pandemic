import java.awt.*;

public class Lieu implements Comparable<Lieu> {
	
	public String nom;
	public int position;
	public String zone;
	public boolean centreRecherche;
	public int nbMaladie;
	public int [] voisins;
	
	public Lieu(String nom, int pos, String zone, int[] v )  {
		this.nom = nom;
		this.position = pos;
		this.zone = zone; 
		this.centreRecherche = false;
		this.nbMaladie= 0;
		
		voisins =  new int [v.length];
		for (int i=0; i<voisins.length;i++){
				voisins[i]=v[i];
		}
	}
	
	public void ajoutMaladie(int nbMaladie){
		this.nbMaladie += nbMaladie;
		if (this.nbMaladie>3){this.nbMaladie=3;}
	}
	public void setMaladie(int valueMaladie){
		this.nbMaladie = valueMaladie;
	}
	public void addCentreRecherche(){
		this.centreRecherche = true;
	}
	
	public int compareTo( Lieu autreLieu){
		return (autreLieu.nom.compareTo(nom));
	}
	public String toString(){
		String description = position+" ("+nom+")";
		return description;
	}
}

