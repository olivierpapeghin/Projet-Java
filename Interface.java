import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

public class Interface {
	// L'outil qui va permettre à l'utilisateur de communiquer des informations
	static Scanner scanner = new Scanner(System.in); 
	static Restaurant restaurant; // Le restaurant dans lequel on travaille 

//-------------- Menu général -----------------------------------------------------------
	public static void ecranGeneral(){
		System.out.println("Quel écran souhaitez vous afficher ?\n"
				+ "1- Ecran Serveur\n"
				+ "2- Ecran cuisine\n"
				+ "3- Ecran bar\n"
				+ "4- Ecran Monitoring\n");
		int choixEcran = scanner.nextInt();
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
		int choixEcran = scanner.nextInt();
		afficheEcranServeur(choixEcran);
	}

	// Redirection dans les sous menus du menu serveur
	public static void afficheEcranServeur(int choix){
		Serveur serveur = new Serveur(null, null, 0, null);
		switch(choix) {
			case 1:
				serveur.accueilClient(restaurant, scanner);
				serveur();
				break;
			case 2:
				serveur.priseCommande(restaurant, scanner);
				serveur();
				break;
			case 3:
				recupCommande();
				serveur();
				break;
			case 4:
				addition();
				serveur();
				break;
			case 5:
				ecranGeneral();
				break;
		}
	}

	//Ecran d'accueil d'un nouveau groupe de clients
	public static void accueilClient(){
		
	}

	// Ecran de récupération d'un commande (pour la livrer aux clients correspondants)
	/*
	 * On va transferer une des commandes en cours prête pour aller la livrer aux clients 
	 * donc on va ajouter cette commande dans le groupe client
	 */
	public static void recupCommande(){}

	// Ecran pour régler les commandes d'un groupe de clients
	public static void addition(){}

//-------------- Menu du cuisinier -----------------------------------------------------------
	public static void cuisine() {
		System.out.println("Quelle action souhaitez vous faire ?\n"
				+ "1- Gestion des commandes\n"
				+ "2- Retour");
		int choixEcran = scanner.nextInt();
		
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
				ecranGeneral();
				break;
		}
	}
		
	

//-------------- Menu du barman -----------------------------------------------------------
	public static void bar() {
		System.out.println("Quelle action souhaitez vous faire ?\n"
				+ "1- Gestion des commandes\n"
				+ "2- Retour");
		int choixEcran = scanner.nextInt();
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
				ecranGeneral();
				break;
		}
	}


//-------------- Menu du manager -----------------------------------------------------------
	public static void monitoring() {
		
	}
	

	public static void main(String[] args) {
		Interface affichage = new Interface();
		// Initialisation du restaurant ici
		Restaurant restaurant=new Restaurant();
		// On set le stock
		restaurant.getStock().SetStockMax(30,60, 20, 40, 30, 35,35, 30,20, 30, 30, 30, 30);
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
		affichage.restaurant=restaurant;
		
		ecranGeneral();
	}

}
