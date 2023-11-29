import java.util.ArrayList;
import java.util.HashMap;

public class Restaurant {
	
	private ArrayList<Table> tables = new ArrayList<Table>();
	private ArrayList<Employe> employes = new ArrayList<Employe>();
	private ArrayList<GroupeClient> clients_actuels = new ArrayList<GroupeClient>();
	private int jour;
	private Stock stock = new Stock();
	private ArrayList<Facture> listeFacture = new ArrayList<Facture>();
	private static ArrayList<Plat> carte = new ArrayList<Plat>();
	
	Restaurant(){
		HashMap<String, Integer> temp=new HashMap<String, Integer>();
		carte.add(new Plat("salade_tomate",9,))
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
		carte_boissons.put("cidre_doux",5);
		carte_boissons.put("biere",5);
		carte_boissons.put("jus_de_fruit",1);
		carte_boissons.put("eau",0);
		
		//Creation des employés de base
	}

	// Getter

	ArrayList<Table> getTables(){
		return tables;
	}

	ArrayList<Employe> getEmploye(){
		return employes;
	}

	Stock getStock(){
		return stock;
	}

	ArrayList<Facture> getFactures(){
		return listeFacture;
	}

	int getJour(){
		return jour;
	}

	ArrayList<GroupeClient> getClientsActuels(){
		return this.clients_actuels;
	}

	// Setters

	//Adders ( ajoute des objets au restaurant)
	void AddTable(int places){
		int x=this.tables.get(this.tables.size()).getNumero();
		tables.add(new Table(x+1, places));
	}

	void switchJours(){
		if(this.jour==7){
			this.jour=0;
		}
		else{
			this.jour=jour+1;
		}
	}

	//Autres

	ArrayList<Table> checkPlaces(int nbr){
		int i =0;
		ArrayList<Table> tablesLibres= new ArrayList<Table>();
		int nbr_libre=0;
		while(i<tables.size()){
			if(this.tables.get(i).getOccupe()==false){
				tablesLibres.add(this.tables.get(i));
				nbr_libre=nbr_libre+this.tables.get(i).getNb_places();
				this.tables.get(i).setOccupe(true);
				if(nbr_libre>=nbr){
					return tablesLibres;
				}
			}
			i+=1;
		}
		return tablesLibres;

	}

}
