import java.util.ArrayList;

public class GroupeClient { //Est un groupe de clients
	
	int nb_clients; // Nombre de clients dans le groupe
	Table table; // La table assignée au groupe
	ArrayList<Commande> commandes; // Liste des commandes passées par le groupe
	
	// Constructeur
	
	GroupeClient(int nbr, Table table){
		this.nb_clients=nbr;
		this.table=table;
		commandes=new ArrayList<Commande>();
	}
	// Setters
	
	void setTable(Table nbr) {
		this.table=nbr;
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
	
	Table getTable() {
		return(this.table);
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
