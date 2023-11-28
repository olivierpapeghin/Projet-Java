import java.util.HashMap;

public class Commande {

	HashMap<Plat, Integer> liste;
	
	// Constructeur
	Commande(){
		liste=new HashMap<Plat,Integer>();
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
		liste.put(plat,quantite);
	}

}
