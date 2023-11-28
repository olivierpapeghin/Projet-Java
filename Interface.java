import java.util.HashMap;
import java.util.Scanner;

public class Interface {
	static Scanner scanner = new Scanner(System.in);
	
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
	
	public static void cuisine() {
		
	}
	
	public static void bar() {
		
	}
	
	public static void monitoring() {
		
	}
	
	public static void afficheEcran(int choix) {
		// Initialisation du restaurant ici
		//
		//
		//
		
		switch(choix) {
			case 1:
				priseCommande();
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

	public static void main(String[] args) {
		System.out.println("Quel écran souhaitez vous afficher ?\n"
				+ "1- Ecran prise de commande\n"
				+ "2- Ecran cuisine\n"
				+ "3- Ecran bar\n"
				+ "4- Ecran Monitoring\n");
		int choixEcran = scanner.nextInt();
		afficheEcran(choixEcran);
	}

}
