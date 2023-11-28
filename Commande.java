
import java.util.HashMap;

public class Commande {
	HashMap<String, Integer> liste = new HashMap<String, Integer>();
	
	// getters utiles
	
	int getQuantite(String produit) {
		return(this.liste.get(produit));
	}
	
	// setters
	
	void addToCommande(String produit,int nbr) {
		this.liste.put(produit, nbr);
	}
	
	// gestion des stocks
	
	void ajouterCommande(String produit, int nbr) {
		this.liste.replace(produit, this.liste.get(produit)+nbr);
	}
	
	

}
