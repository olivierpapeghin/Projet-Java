
public class GroupeClient { //Est un groupe de clients
	
	int nb_clients; // nombre de clients dans le groupe
	Table table; // la table assignée au groupe
	Serveur serveur; //Serveur assigné au groupe de client
	
	// constructeur
	
	GroupeClient(int nbr){
		this.nb_clients=nbr;
	}
	// setters utiles
	
	void setTable(Table nbr) {
		this.table=nbr;
	}
	
	void setServeur(Serveur serveur) {
		this.serveur=serveur;
	}
	
	// getters utiles
	
	int getClients() {
		return(this.nb_clients);
	}
	
	Table getTable() {
		return(this.table);
	}
	
	Serveur getServeur() {
		return(this.serveur);
	}

}
