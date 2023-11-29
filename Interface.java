import java.util.HashMap;
import java.util.Scanner;

public class Interface {
	static Scanner scanner = new Scanner(System.in);

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
				+ "4- Donner l'addition\n");
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
		}
	}

	// Ecran d'accueil d'un nouveau groupe de clients
	public static void accueilClient(){
		// On commence par vérifier s'il y a assez de tables libres
		 System.out.println("Combien de clients y a-t-il ?");
		 int nb_clients=scanner.nextInt();
	}

	// Ecran prise de commande
	public static void priseCommande() {
		HashMap<String,Integer> commande = new HashMap<String,Integer>();
		// On prend le numéro de table pour plus tard pouvoir retrouver les clients
		System.out.println("---- Vous êtes dans le menu de prise de commande ----\n"
				+"Quel est le numéro de la table qui commande ?");
		int numtable=scanner.nextInt();
		// Maintenant on prend la commande
		System.out.println("Veuillez entrer la commande : (Produit puis quantité)\n");
		String reponse = "oui";
		// On dit que tant que le serveur ne décide pas que c'est terminé on continue à ajouter
		while(reponse.equals("oui")) {
			System.out.println("Nom Quantité : ");
			String nomProduit = scanner.next();
			int quantiteProduit = scanner.nextInt();
			commande.put(nomProduit, quantiteProduit);
			
			System.out.println("Continuer ? (oui/non");
			reponse=scanner.next();
		}
		// On fait un récap de la commande
		System.out.println("Récapitulatif :\n"
				+ "Pour la table "+numtable);
		for (String i : commande.keySet()) {
			  System.out.println("•"+i+" x"+commande.get(i));
		}
		
		// Création de la commande ici
		//
		//
		//
	}

	// Ecran de récupération d'un commande (pour la livrer aux clients correspondants)
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
		restaurant.stock.SetStockMax(30,60, 20, 40, 30, 35,35, 30, 30, 30, 30, 30);
		ecranGeneral();
	}

}
