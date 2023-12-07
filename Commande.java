import java.util.HashMap;

public class Commande {

	private HashMap<Plat, Integer> liste;
	private int table; // Le numéro de la table associée à la commande 
					   //(si le groupe client est sur plusieurs tables on ne prend que la première)
	private boolean boisson_prete;
	private boolean plat_pret;
	
	// Constructeur
	Commande(){
		// De base la liste des plats est vide et la commande n'est pas prête
		liste=new HashMap<Plat,Integer>();
		boisson_prete=false;
		plat_pret=false;
	}

	// Getters
	
	HashMap<Plat, Integer> getListe() {
		return this.liste;
	}

	boolean getBoisson_prete(){
		return boisson_prete;
	}

	boolean getPlat_pret(){
		return plat_pret;
	}

	int getTable(){
		return table;
	}
	
	// Setters
	
	void setListe(HashMap<Plat, Integer> liste){
		this.liste=liste;
	}

	void setBoisson_prete(boolean pret){
		boisson_prete=pret;
	}

	void setPlat_pret(boolean pret){
		plat_pret=pret;
	}

	void setTable(int numTable){
		table=numTable;
	}

	// Autre

	/*
	 * On ajoute un plat à la liste déjà existente des plats de la commande
	 */
	void ajoutPlat(Plat plat,int quantite){
		liste.put(plat,quantite); // On ajoute le plat dans la quantité désirée
	}

	/*
	 * On renvoie le prix total
	 */
	int prixTotal(){
		int prixtot=0;
		for (Plat plat : liste.keySet()){ // On parcours les clés du HashMap
			prixtot+=plat.getPrix()*liste.get(plat);
		}		// On fait prix*quantité pour chaque plat de la commande
		return prixtot;
	}

	/*
	 * On regarde si les boissons et les plats sont prêts si oui on renvoie true sinon false
	 */
	boolean est_prete(){
		if (boisson_prete && plat_pret){
			return true;
		}
		else{
			return false;
		}
	}

	/*
	 * On affiche le contenu total de la commande
	 */
	void recap(){
		System.out.println("Pour la table "+table+" :");
		for (Plat plat : liste.keySet()) {
			  System.out.println("-"+plat.getNom()+" x"+liste.get(plat));
		}
		System.out.println("\n");
	}
	
	/*
	 * On affiche le contenu des plats de la commande
	 */
	void recapPlat(){
		System.out.println("Pour la table "+table+" :");
		for (Plat plat : liste.keySet()) {
			if(plat.getType()=="plat"){
			  System.out.println("-"+plat.getNom()+" x"+liste.get(plat));
			}
		}
		System.out.println("\n");
	}

	/*
	 * On affiche le contenu des boissons de la commande
	 */
	void recapBoisson(){
		System.out.println("Pour la table "+table+" :");
		for (Plat plat : liste.keySet()) {
			if(plat.getType()=="boisson"){
			  System.out.println("-"+plat.getNom()+" x"+liste.get(plat));
			}
		}
		System.out.println("\n");
	}

	/*
	 * On regarde le contenu de la commande, s'il n'y a pas de boisson ou de plat alors on met
	 * plat_pret ou boisson_prete à true, on va lancer l'autoAnalyse au moment d'envoyer la commande
	 * en préparation pour éviter que les cuisiniers/barmans aient à valider des commandes qui ne 
	 * les concernent pas 
	 */
	void autoAnalyse(){
		plat_pret = true;
		boisson_prete = true;
		for(Plat plat : liste.keySet()){
			if (plat.getType() == "plat"){ // SI c'est un plat
				plat_pret=false;
			}
			else{ // Sinon c'est forcement une boisson
				boisson_prete=false;
			}
		}
	}

}
