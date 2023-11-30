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
		switch(choix) {
			case 1:
				accueilClient();
				break;
			case 2:
				priseCommande();
				break;
			case 3:
				recupCommande();
				break;
			case 4:
				addition();
				break;
			case 5:
				ecranGeneral();
		}
	}

	//Ecran d'accueil d'un nouveau groupe de clients
	/*On prend le nombre de clients, puis on regarde s'il y a assez de place pour eux
	 * si oui on leur assigne des tables (qui deviennent donc occupée)
	 * sinon on ne peut pas les accueillir
	 */
	public static void accueilClient(){
		 System.out.println("Combien de clients y a-t-il ?");
		 int nb_clients=scanner.nextInt();
		 // On regarde s'il y a assez de tables pour accueillir les clients
		 ArrayList<Table> tables_client = restaurant.checkPlaces(nb_clients);
		 if (tables_client.size()==0){
			// S'il n'y a pas de table disponible
			System.out.println("Il n'y a pas assez de places pour accueillir ces clients");
			serveur(); // On retourne au menu des serveurs
		 }
		// S'il y a assez de place alors on peut indiquer aux clients où se placer et attendre qu'ils commandent
		System.out.print("Ils seront à/aux table(s)"+tables_client.get(0));
		for (int i=1;i<tables_client.size();i++){
			System.out.print(" et "+tables_client.get(i));
		}
		restaurant.addClient(new GroupeClient(nb_clients, tables_client)); // On ajoute les clients
		serveur(); // Retour à l'écran serveur
	}

	// Ecran prise de commande
	/*
	 * Lors de la prise de commande le serveur sélectionne les plats et leur quantité parmi les plats de la 
	 * carte, une fois terminé un récap s'affiche pour qu'il confirme.
	 * Une fois confirmée la commande est envoyée dans les commandes en cours qui seront effectuées par les
	 * cuisiniers et barmans
	 */
	public static void priseCommande() {
		Commande commande = new Commande();
		// On prend le numéro de table pour pouvoir retrouver les clients
		System.out.println("---- Vous êtes dans le menu de prise de commande ----\n"
				+"Quel est le numéro de la table qui commande ?");
		int numtable=scanner.nextInt();
		// Maintenant on prend la commande
		System.out.println("Veuillez entrer la commande : (Produit puis quantité)\n");
		String reponse = "oui";
		// On dit que tant que le serveur ne décide pas que c'est terminé on continue à ajouter des plats
		while(reponse.equals("oui")) {
			// On affiche la carte pour que le serveur sélectionne le bon plat
			for (int i=0;i<restaurant.getCarte().size();i++){
				System.out.println("["+i+"] "+restaurant.getCarte().get(i).getNom()+" : "+
				restaurant.getCarte().get(i).getPrix());
				// On doit checker si le plat est faisable avec les stocks actuels
				if(restaurant.getStock().checkDisponibilite(restaurant.getCarte().get(i))){
					// Si on peut faire le plat
					System.out.print(" (Disponible)");
				}
				else{
					System.out.print("Indisponible");
				}
			}
			System.out.print("Plat n° ");
			int numPlat = scanner.nextInt(); // On prend le numéro du plat dans la liste
			System.out.print(" x ");
			int quantitePlat = scanner.nextInt(); // Puis la quantité
			commande.ajoutPlat(restaurant.getCarte().get(numPlat), quantitePlat); // On ajoute à la commande

			System.out.println("Continuer ? (oui/non)");
			reponse=scanner.next();
		}
		// On fait un récap de la commande
		System.out.println("Pour la table "+numtable);
		commande.recap();
		restaurant.addCommande(commande); // On envoie la commande aux cuisiniers/barmans
		restaurant.getStock().consommationStock(commande);
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
		
	}

//-------------- Menu du barman -----------------------------------------------------------
	public static void bar() {
		
	}

//-------------- Menu du manager -----------------------------------------------------------
	public static void monitoring() {
		
	}
	

	public static void main(String[] args) {
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

		
		ecranGeneral();
	}

}
