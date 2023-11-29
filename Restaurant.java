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
		//initialisation des plats
		//map temporaire pour initialiser les plats
		HashMap<String, Integer> temp=new HashMap<String, Integer>(); 
		// chaque "partie" est une initialisation de plats
		temp.put("tomate", 1);
		temp.put("salade",1);
		carte.add(new Plat("salade_tomate",9,temp,"plat"));

		temp.clear();
		temp.put("salade", 1);
		carte.add(new Plat("salade",9,temp,"plat"));
		
		temp.clear();
		temp.put("oignon",3);
		carte.add(new Plat("soupe_oignon",8,temp,"plat"));

		temp.clear();
		temp.put("tomate",3);
		carte.add(new Plat("soupe_tomate",8,temp,"plat"));

		temp.clear();
		temp.put("champignon",3);
		carte.add(new Plat("soupe_champignons",8,temp,"plat"));

		temp.clear();
		temp.put("pain",1);
		temp.put("viande",1);
		temp.put("salade",1);
		temp.put("tomate",1);
		carte.add(new Plat("burger_salade_tomate",15,temp,"plat"));

		temp.clear();
		temp.put("pain",1);
		temp.put("viande",1);
		temp.put("salade",1);
		carte.add(new Plat("burger_salade",15,temp,"plat"));

		temp.clear();
		temp.put("pain",1);
		temp.put("viande",1);
		carte.add(new Plat("burger",15,temp,"plat"));

		temp.clear();
		temp.put("pate",1);
		temp.put("tomate",1);
		temp.put("fromage",1);
		carte.add(new Plat("pizza",12,temp,"plat"));

		temp.clear();
		temp.put("pate",1);
		temp.put("tomate",1);
		temp.put("fromage",1);
		temp.put("champignon",1);
		carte.add(new Plat("pizza_champignon",12,temp,"plat"));		
	
		temp.clear();
		temp.put("pate",1);
		temp.put("tomate",1);
		temp.put("fromage",1);
		temp.put("pepperoni",1);
		carte.add(new Plat("pizza_pepperoni",12,temp,"plat"));

		// setting de la carte des boissons
		
		temp.clear();
		temp.put("limonade",1);
		carte.add(new Plat("limonade",4,temp,"boisson"));

		temp.clear();
		temp.put("cidre_doux",1);
		carte.add(new Plat("cidre_doux",5,temp,"boisson"));

		temp.clear();
		temp.put("biere",1);
		carte.add(new Plat("biere",5,temp,"boisson"));

		temp.clear();
		temp.put("jus_de_fruit",1);
		carte.add(new Plat("jus_de_fruit",1,temp,"boisson"));

		temp.clear();
		carte.add(new Plat("eau",0,temp,"boisson"));

		
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

	ArrayList<Plat> getCarte(){
		return carte;
	}

	// Setters

	//Adders ( ajoute des objets au restaurant)
	void addTable(int places){
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

	void addClient(GroupeClient client){
		clients_actuels.add(client);
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
