
import java.util.HashMap;

public class Stock {
	HashMap<String, Integer> liste = new HashMap<String, Integer>();
	
	// getters utiles
	
	int getQuantite(String produit) {
		return(this.liste.get(produit));
	}
	
	// gestion des stocks
	
	void diminuerStock(String produit, int nbr) {
		if(this.liste.get(produit)>=nbr) {
			this.liste.replace(produit, this.liste.get(produit)-nbr);
		}
	}
	
	void ajouterStock(String produit, int nbr) {
		this.liste.replace(produit, this.liste.get(produit)+nbr);
	}

}
