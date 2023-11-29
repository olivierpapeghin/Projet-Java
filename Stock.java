
import java.util.HashMap;

public class Stock {
	HashMap<String, Integer> liste = new HashMap<String, Integer>();
	HashMap<String,Integer> liste_max=new HashMap<String, Integer>();
	
	// Getters
	
	int getQuantite(String produit) {
		return(this.liste.get(produit));
	}

	HashMap<String,Integer> getListe(){
		return(this.liste);
	}

	HashMap<String,Integer> getListe_max(){
		return(this.liste_max);
	}

	int getQuantiteMax(String produit) {
		return(this.liste_max.get(produit));
	}
	
	// Setters
	
	void addToStock(String produit,int nbr) {
		this.liste.put(produit, nbr);
	}

	//Contructeur
	void SetStockMax(int nbr_salade,int nbr_tomate,int nbr_oignons,int nbr_champis,int nbr_viande,int nbr_pain,
	int nbr_pate,int nbr_fromage,int nbr_limonade,int nbr_jus, int nbr_biere,int nbr_cidre){
		this.liste_max.put("salade", nbr_salade);
		this.liste_max.put("tomate", nbr_tomate);
		this.liste_max.put("oignons", nbr_oignons);
		this.liste_max.put("champignons", nbr_champis);
		this.liste_max.put("viande", nbr_viande);
		this.liste_max.put("pain", nbr_pain);
		this.liste_max.put("pate", nbr_pate);
		this.liste_max.put("fromage", nbr_fromage);
		this.liste_max.put("limonade", nbr_limonade);
		this.liste_max.put("jus", nbr_jus);
		this.liste_max.put("biere", nbr_biere);
		this.liste_max.put("cidre", nbr_cidre);
	}
	
	// Autres
	
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
