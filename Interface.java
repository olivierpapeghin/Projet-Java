import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Interface {
	// L'outil qui va permettre à l'utilisateur de communiquer des informations
	static Scanner scanner = new Scanner(System.in); 
	static Restaurant restaurant; // Le restaurant dans lequel on travaille
	static FonctionsUtiles utiles = new FonctionsUtiles();

//-------------- Menu général -----------------------------------------------------------
	public static void ecranGeneral(){
		if(restaurant.getOuvert()){
			System.out.println("\nQuel écran souhaitez vous afficher ?\n"
					+ "1- Ecran Serveur\n"
					+ "2- Ecran cuisine\n"
					+ "3- Ecran bar\n"
					+ "4- Ecran Monitoring\n");
			int choixEcran = utiles.enregistreInt(1,5,scanner);
			afficheEcranGeneral(choixEcran);
		}
		else{
			System.out.println("\nLe restaurant est fermé aujourd'hui ("+restaurant.getJour()+")\n"
					+"1- Fin de journée");
			utiles.enregistreInt(1,1,scanner);
			Manager manager = new Manager(null, null, 0, null);
			manager.finDeJournee(restaurant, scanner);
		}
	}

	// Redirection depuis le menu général
	public static void afficheEcranGeneral(int choix) {
		switch(choix) {
			case 1:
				serveur();
				break;
			case 2:
				cuisine();
				break;
			case 3:
				bar();
				break;
			case 4:
				monitoring();
				break;
		}
	}

//-------------- Menu du serveur -----------------------------------------------------------
/* Un serveur peut faire 4 choses :
- Accueillir un nouveau groupe de clients s'il y a des tables disponnibles
- Prendre une commande pour un groupe de client
- Récupérer une commande
- Donner l'addition à un groupe client
*/
	public static void serveur(){
		System.out.println("Quelle action souhaitez vous faire ?\n"
				+ "1- Accueillir des clients\n"
				+ "2- Prendre une commande\n"
				+ "3- Recupérer une commande\n"
				+ "4- Donner l'addition\n"
				+ "5- Retour");
		int choixEcran = utiles.enregistreInt(1, 5,scanner);
		afficheEcranServeur(choixEcran);
	}

	// Redirection dans les sous menus du menu serveur
	public static void afficheEcranServeur(int choix){
		Serveur serveur = new Serveur(null, null, 0, null);
		switch(choix) {
			case 1:
				restaurant=serveur.accueilClient(restaurant, scanner);
				serveur();
				break;
			case 2:
				restaurant=serveur.priseCommande(restaurant, scanner);
				serveur();
				break;
			case 3:
				restaurant=serveur.recupCommande(restaurant,scanner);
				serveur();
				break;
			case 4:
				//addition();
				serveur();
				break;
			case 5:
				break;
		}
	}

//-------------- Menu du cuisinier -----------------------------------------------------------
	public static void cuisine() {
		System.out.println("Quelle action souhaitez vous faire ?\n"
				+ "1- Gestion des commandes\n"
				+ "2- Retour");
		int choixEcran = utiles.enregistreInt(1, 2,scanner);
		afficheEcranCuisinier(choixEcran);
	}

	public static void afficheEcranCuisinier(int choix){
		switch(choix) {
			
			case 1:
				Cuisinier cuisinier = new Cuisinier(null, null, 0, null);
				cuisinier.GestionDesCommandes(restaurant,scanner);
				cuisine();
				break;
			case 2:
				break;
		}
	}
		

//-------------- Menu du barman -----------------------------------------------------------
	public static void bar() {
		System.out.println("Quelle action souhaitez vous faire ?\n"
				+ "1- Gestion des commandes\n"
				+ "2- Retour");
		int choixEcran = utiles.enregistreInt(1, 2,scanner);
		afficheEcranBarman(choixEcran);
	}

	public static void afficheEcranBarman(int choix){
		switch(choix) {
			case 1:
				Barman barman = new Barman(null, null, 0, null);
				barman.GestionDesCommandes(restaurant,scanner);
				bar();
				break;
			case 2:
				break;
		}
	}


//-------------- Menu du manager -----------------------------------------------------------
	public static void monitoring() {
		System.out.println("Quelle action souhaitez vous faire ?\n"
				+ "1- Performances\n"
				+ "2- Ajouter/Supprimer des employés\n"
				+ "3- Gérer les emplois du temps de la semaine\n"
				+ "4- Gestion des stocks\n"
				+ "5- Fin de la journée\n"
				+ "6- Retour");
		int choix = utiles.enregistreInt(1, 6,scanner);

		Manager manager = new Manager(null, null, 0, null);
		switch(choix) {
			case 1:
				manager.performances(restaurant, scanner);
				monitoring();
				break;
			case 2:
				manager.ajouterSupprimerEmployes(restaurant, scanner);
				monitoring();
				break;
			case 3:
				manager.GestionEDT(restaurant, scanner);
				break;
			case 4:
				manager.GestionDesStocks(restaurant, scanner);
				monitoring();
				break;
			case 5:
				manager.finDeJournee(restaurant, scanner);
				break;
			case 6:
				break;
		}
	}
		
	public static void main(String[] args) {
		// Initialisation du restaurant ici
		Restaurant restaurant=new Restaurant();
		// On set le stock
		restaurant.getStock().SetStockMax(30,60, 20, 40, 30, 35,35, 30,20, 30, 30, 30, 30);
		restaurant.getStock().fillStock(); // On rempli le stock actuel en fonction du stock max
		// Puis les tables
		restaurant.addTable(1,4);
		restaurant.addTable(2,4);
		restaurant.addTable(3,4);

		// Puis les employés
		// Le manager
		ArrayList<String> edt = new ArrayList<String>(Arrays.asList("Lundi","Mardi","Mercredi","Jeudi",
									"Vendredi","Samedi"));
		restaurant.addEmploye(new Manager("Papeghin","Olivier",4500,edt));

		// Les barmans
		ArrayList<String> edt1 = new ArrayList<String>(Arrays.asList("Lundi","Mardi","Mercredi"));
		restaurant.addEmploye(new Barman("Lacroix","Matthieu",2000,edt1));
		ArrayList<String> edt2 = new ArrayList<String>(Arrays.asList("Jeudi","Vendredi","Samedi"));
		restaurant.addEmploye(new Barman("Delerue","Paul",2000,edt2));

		// Les cuisiniers
		ArrayList<String> edt3 = new ArrayList<String>(Arrays.asList("Lundi","Mardi","Jeudi","Vendredi"));
		restaurant.addEmploye(new Cuisinier("Etchebest","Philippe",1300,edt3));
		ArrayList<String> edt4 = new ArrayList<String>(Arrays.asList("Mardi","Mercredi","Vendredi","Samedi"));
		restaurant.addEmploye(new Cuisinier("Cocca","Florian",1300,edt4));
		restaurant.addEmploye(new Cuisinier("Sokolowski","Raphaël",1500,edt4));
		restaurant.addEmploye(new Cuisinier("Rosier","Enzo",300,edt4));

		// Les serveurs
		ArrayList<String> edt5 = new ArrayList<String>(Arrays.asList("Lundi","Mardi","Mercredi","Vendredi","Samedi"));
		restaurant.addEmploye(new Serveur("Sergiani","Enzo",300,edt5));
		ArrayList<String> edt6 = new ArrayList<String>(Arrays.asList("Lundi","Mardi","Jeudi","Vendredi","Samedi"));
		restaurant.addEmploye(new Serveur("Yataghene","Lydia",13000,edt6));
		Interface.restaurant=restaurant;
		
		while (true) {
			ecranGeneral();
		}
	}

}
