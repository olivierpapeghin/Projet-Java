import java.util.HashMap;

public class Commande {

	HashMap<Plat, Integer> liste;
	boolean prete;
	
	// Constructeur
	Commande(){
		// De base la liste des plats est vide 
		liste=new HashMap<Plat,Integer>();
		prete=false;
	}

	// Getters
	
	HashMap<Plat, Integer> getListe() {
		return this.liste;
	}
	
	// Setters
	
	void setListe(HashMap<Plat, Integer> liste){
		this.liste=liste;
	}

	// Autre

	void ajoutPlat(Plat plat,int quantite){
		liste.put(plat,quantite); // On ajoute le plat dans la quantité désirée
	}

	int prixTotal(){
		int prixtot=0;
		for (Plat plat : liste.keySet()){ // On parcours les clés du HashMap
			prixtot+=plat.getPrix()*liste.get(plat);
		}		// On fait prix*quantité pour chaque plat de la commande
		return prixtot;
	}

}
