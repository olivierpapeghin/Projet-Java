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
	private ArrayList<Commande> commandeEnCours = new ArrayList<Commande>();
	
	Restaurant(){
		
		initalise_carte(); // fonction qui permet d'eviter d'avoir unr grosse quantitée de code dans le
		//constructeur

		// initialisation des variables de base
		jour=1;

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

	ArrayList<Commande> getCommandes(){
		return this.commandeEnCours;
	}

	//Adders ( ajoute des objets au restaurant)
	void addTable(int num, int places){
		tables.add(new Table(num, places));
	}

	void addCommande(Commande commande){
		this.commandeEnCours.add(commande);
	}

	void addClient(GroupeClient client){
		clients_actuels.add(client);
	}

	void addEmploye(Employe employe){
		this.employes.add(employe);
	}

	//Autres

	void switchJours(){
		if(this.jour==7){
			this.jour=1;
		}
		else{
			this.jour=jour+1;
		}
	}


	// verifie si l'on peut placer les clients, et si oui, donne les tables qui seront  associées
	//aux clients
	ArrayList<Table> checkPlaces(int nbr){
		int i =0;
		ArrayList<Table> tablesLibres= new ArrayList<Table>();
		int nbr_libre=0;
		while(i<tables.size()){
			if(this.tables.get(i).getOccupe()==false){
				tablesLibres.add(this.tables.get(i));
				nbr_libre=nbr_libre+this.tables.get(i).getNb_places();
				
				if(nbr_libre>=nbr){
					for(Table entry : tablesLibres){
						entry.setOccupe(true);
					}
					return tablesLibres;
				}
			}
			i+=1;
		}
		if(nbr_libre<nbr){
			tablesLibres.clear();
		}
		
		return tablesLibres;

	}

	// fonction qui intialise la carte

	void initalise_carte(){

		//initialisation des plats
		//map temporaire pour initialiser les plats
		HashMap<String, Integer> temp=new HashMap<String, Integer>(); 
		// chaque "partie" est une initialisation de plats
		temp.put("tomate", 1);
		temp.put("salade",1);
		carte.add(new Plat("Salade de tomate",9,temp,"plat"));

		temp.clear();
		temp.put("salade", 1);
		carte.add(new Plat("Salade",9,temp,"plat"));
		
		temp.clear();
		temp.put("oignon",3);
		carte.add(new Plat("Soupe à l'oignon",8,temp,"plat"));

		temp.clear();
		temp.put("tomate",3);
		carte.add(new Plat("Soupe de tomate",8,temp,"plat"));

		temp.clear();
		temp.put("champignon",3);
		carte.add(new Plat("Soupe de champignons",8,temp,"plat"));

		temp.clear();
		temp.put("pain",1);
		temp.put("viande",1);
		temp.put("salade",1);
		temp.put("tomate",1);
		carte.add(new Plat("Burger salade et tomate",15,temp,"plat"));

		temp.clear();
		temp.put("pain",1);
		temp.put("viande",1);
		temp.put("salade",1);
		carte.add(new Plat("Burger salade",15,temp,"plat"));

		temp.clear();
		temp.put("pain",1);
		temp.put("viande",1);
		carte.add(new Plat("Burger",15,temp,"plat"));

		temp.clear();
		temp.put("pate",1);
		temp.put("tomate",1);
		temp.put("fromage",1);
		carte.add(new Plat("Pizza",12,temp,"plat"));

		temp.clear();
		temp.put("pate",1);
		temp.put("tomate",1);
		temp.put("fromage",1);
		temp.put("champignon",1);
		carte.add(new Plat("Pizza aux champignons",12,temp,"plat"));		
	
		temp.clear();
		temp.put("pate",1);
		temp.put("tomate",1);
		temp.put("fromage",1);
		temp.put("pepperoni",1);
		carte.add(new Plat("Pizza aux pepperonis",12,temp,"plat"));

		// setting de la carte des boissons
		
		temp.clear();
		temp.put("limonade",1);
		carte.add(new Plat("Limonade",4,temp,"boisson"));

		temp.clear();
		temp.put("cidre_doux",1);
		carte.add(new Plat("Cidre doux",5,temp,"boisson"));

		temp.clear();
		temp.put("biere",1);
		carte.add(new Plat("Bière",5,temp,"boisson"));

		temp.clear();
		temp.put("jus_de_fruit",1);
		carte.add(new Plat("Jus de fruits",1,temp,"boisson"));

		temp.clear();
		carte.add(new Plat("Verre d'eau",0,temp,"boisson"));
		
	}
	

}
