import java.util.ArrayList;

public class GroupeClient { //Est un groupe de clients
	
	private int nb_clients; // Nombre de clients dans le groupe
	private ArrayList<Table> tables; // La table assignée au groupe
	private ArrayList<Commande> commandes; // Liste des commandes passées par le groupe
	
	// Constructeur
	
	GroupeClient(int nbr, ArrayList<Table> table){
		this.nb_clients=nbr;
		this.tables=table;
		commandes=new ArrayList<Commande>();
	}
	// Setters
	
	void setTable(ArrayList<Table> tables) {
		this.tables=tables;
	}

	void setNb_clients(int nbr){
		this.nb_clients=nbr;
	}

	void setCommandes(ArrayList<Commande> commandes){
		this.commandes=commandes;
	}
	
	
	// Getters
	
	int getClients() {
		return(this.nb_clients);
	}
	
	ArrayList<Table> getTable() {
		return(this.tables);
	}

	ArrayList<Commande> getCommandes(){
		return(this.commandes);
	}

	// Autre

	void nouvelleCommande(Commande commande){
		// On ajoute une nouvelle commande à la liste
		commandes.add(commande);
	}
}
