
import java.util.HashMap;

public class Stock {
	HashMap<String, Integer> liste = new HashMap<String, Integer>();
	
	// getters utiles
	
	int getQuantite(String produit) {
		return(this.liste.get(produit));
	}
	
	// setters
	
	void addToStock(String produit,int nbr) {
		this.liste.put(produit, nbr);
	}
	
	// gestion des stocks
	
	void diminuerStock(String produit, int nbr) {// permet de diminuer les quantites
		if(this.liste.get(produit)>=nbr) {
			this.liste.replace(produit, this.liste.get(produit)-nbr);
		}
	}
	
	void ajouterStock(String produit, int nbr) { // permet d'augmenter les quantites
		this.liste.replace(produit, this.liste.get(produit)+nbr);
	}
	
	void copyStock(Stock original) {// permet de copier la liste d'un autre stock
		this.liste=original.liste;
	}
	
	

}
