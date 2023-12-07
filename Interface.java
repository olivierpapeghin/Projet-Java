import java.util.ArrayList;
import java.util.Scanner;

public class Interface {
	// L'outil qui va permettre à l'utilisateur de communiquer des informations
	static Scanner scanner = new Scanner(System.in); 
	static Restaurant restaurant; // Le restaurant dans lequel on travaille
	static FonctionsUtiles utiles = new FonctionsUtiles();

//-------------- Menu général -----------------------------------------------------------
	public static void ecranGeneral(){
		System.out.println("\nQuel écran souhaitez vous afficher ?\n"
				+ "1- Ecran Serveur\n"
				+ "2- Ecran cuisine\n"
				+ "3- Ecran bar\n"
				+ "4- Ecran Monitoring\n");
		int choixEcran = utiles.enregistreInt(1,5,scanner);
		afficheEcranGeneral(choixEcran);
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
				// Gestion edt
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
		ArrayList<String> edt = new ArrayList<String>();
		edt.add("Lundi"); edt.add("Mardi"); edt.add("Mercredi"); edt.add("Jeudi");
		edt.add("Vendredi"); edt.add("Samedi");
		restaurant.addEmploye(new Manager("Papeghin","Olivier",4500,edt));

		// Les barmans
		edt.clear(); edt.add("Lundi"); edt.add("Mardi"); edt.add("Mercredi");
		restaurant.addEmploye(new Barman("Lacroix","Matthieu",2000,edt));
		edt.clear(); edt.add("Jeudi"); edt.add("Vendredi"); edt.add("Samedi");
		restaurant.addEmploye(new Barman("Delerue","Paul",2000,edt));

		// Les cuisiniers
		edt.clear(); edt.add("Lundi"); edt.add("Mardi"); edt.add("Jeudi"); edt.add("Vendredi");
		restaurant.addEmploye(new Cuisinier("Etchebest","Philippe",1300,edt));
		edt.clear(); edt.add("Lundi"); edt.add("Mardi"); edt.add("Mercedi"); edt.add("Vendredi"); edt.add("Samedi");
		restaurant.addEmploye(new Cuisinier("Bocuse","Paul",1300,edt));
		restaurant.addEmploye(new Cuisinier("Bigard","Jean-marie",1500,edt));
		restaurant.addEmploye(new Cuisinier("Rosier","Enzo",300,edt));

		// Les serveurs
		edt.clear(); edt.add("Lundi"); edt.add("Mardi"); edt.add("Mercredi"); edt.add("Vendredi"); edt.add("Samedi");
		restaurant.addEmploye(new Serveur("Sergiani","Enzo",300,edt));
		edt.clear(); edt.add("Lundi"); edt.add("Mardi"); edt.add("Jeudi"); edt.add("Vendredi"); edt.add("Samedi");
		restaurant.addEmploye(new Serveur("Yataghene","Lydia",1300,edt));
		Interface.restaurant=restaurant;
		
		while (true) {
			ecranGeneral();
		}
	}

}
