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

	void ajoutPlat(Plat plat,int quantite){
		liste.put(plat,quantite); // On ajoute le plat dans la quantité désirée
	}

	int prixTotal(){
		int prixtot=0;
		for (Plat plat : liste.keySet()){ // On parcours les clés du HashMap
			prixtot+=plat.getPrix()*liste.get(plat);
		}		// On fait prix*quantité pour chaque plat de la commande
		return prixtot;
	}

	boolean est_prete(){
		if (boisson_prete && plat_pret){
			return true;
		}
		else{
			return false;
		}
	}

	void recap(){
		System.out.println("Récapitulatif pour la table "+table+" :");
		for (Plat plat : liste.keySet()) {
			  System.out.println("-"+plat.getNom()+" x"+liste.get(plat));
		}
		System.out.println("\n");
	}

}
