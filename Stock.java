
import java.util.HashMap;
import java.util.Map;

public class Stock {
	private HashMap<String, Integer> liste = new HashMap<String, Integer>();
	private HashMap<String,Integer> liste_max=new HashMap<String, Integer>();
	
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
	int nbr_pate,int nbr_fromage, int nbr_pepperoni,int nbr_limonade,int nbr_jus, int nbr_biere,int nbr_cidre){
		this.liste_max.put("salade", nbr_salade);
		this.liste_max.put("tomate", nbr_tomate);
		this.liste_max.put("oignon", nbr_oignons);
		this.liste_max.put("champignon", nbr_champis);
		this.liste_max.put("viande", nbr_viande);
		this.liste_max.put("pain", nbr_pain);
		this.liste_max.put("pate", nbr_pate);
		this.liste_max.put("fromage", nbr_fromage);
		this.liste_max.put("pepperoni", nbr_pepperoni);
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
	
	void fillStock() {// permet de remplir le stock
		// On ne peut pas mettre un simple = a cause des problèmes de mutabilités
		liste.clear();
		for(String ingredient : liste_max.keySet()){
			liste.put(ingredient,liste_max.get(ingredient));
		}
	}

	// Permet de checker si un plat est faisable avec les ingredients disponibles
	boolean checkDisponibilite(Plat plat){
		HashMap<String, Integer> compo = new HashMap<String, Integer>();
		for(Map.Entry<String, Integer> entry : compo.entrySet()) {
    		String key = entry.getKey();
    		Integer value = entry.getValue();
			if(liste.get(key)<value){
				return(false);
			}
		}
		return(true);
	}

	// Diminue le stock une fois le plat commandé
	void consommationStock(Commande commande){
		HashMap<Plat, Integer> liste = commande.getListe();

		for(Map.Entry<Plat, Integer> entry : liste.entrySet()) {
    		Plat plats = entry.getKey();
    		Integer nbr_plat = entry.getValue();
			HashMap<String, Integer> compo = plats.getComposition();

			for(Map.Entry<String, Integer> entry2 : compo.entrySet()) {
    			String ingredient = entry2.getKey();
    			Integer nbr_ingredient = entry.getValue();
				this.liste.replace(ingredient,this.liste.get(ingredient)-(nbr_ingredient*nbr_plat));
			}
		}
	}


}
