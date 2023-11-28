import java.util.ArrayList;
import java.util.HashMap;

public class Restaurant {
	
	ArrayList<Table> tables = new ArrayList<Table>();
	ArrayList<Employe> employes = new ArrayList<Employe>();
	boolean ouvert;
	Stock stock = new Stock();
	ArrayList<Facture> listeFacture = new ArrayList<Facture>();
	HashMap<String, Integer> carte_plats = new HashMap<String, Integer>();
	HashMap<String, Integer> carte_boissons = new HashMap<String, Integer>();
	
	Restaurant(){
		//commence ouvert
		ouvert=true;
		// setting de la carte des plats
		carte_plats.put("salade_tomate", 9);
		carte_plats.put("salade",9);
		carte_plats.put("soupe_oignon",8);
		carte_plats.put("soupe_tomate",8);
		carte_plats.put("soupe_champignons",8);
		carte_plats.put("burger_salade_tomate",15);
		carte_plats.put("burger_salade",15);
		carte_plats.put("burger",15);
		carte_plats.put("pizza",12);
		carte_plats.put("pizza_champignon",12);
		carte_plats.put("pizza_pepperoni",12);
		
		// setting de la carte des boissons
		
		carte_boissons.put("limonade",4);
		cartes_boissons.put("cidre_doux",5);
		cartes_boissons.put("biere",5);
		cartes_boissons.put("jus_de_fruit",1);
		cartes_boissons.put("eau",0);
		
		//Creation des employés de base
		
		
	}

}
