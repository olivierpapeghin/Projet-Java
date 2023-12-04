import java.util.ArrayList;
import java.util.Scanner;

public class Serveur extends Employe{
    
    Serveur(String nom, String prenom, float salaire, ArrayList<String> edt){
        super(nom,prenom,salaire,edt);
    }


    /*
	 * On prend le nombre de clients, puis on regarde s'il y a assez de place pour eux
	 * si oui on leur assigne des tables (qui deviennent donc occupée)
	 * sinon on ne peut pas les accueillir
	 */
    public Restaurant accueilClient(Restaurant restaurant, Scanner scanner){
        System.out.println("\nCombien de clients y a-t-il ?");
        int nb_clients=scanner.nextInt();
        // On regarde s'il y a assez de tables pour accueillir les clients
        ArrayList<Table> tables_client = restaurant.checkPlaces(nb_clients);
        if (tables_client.size()==0){
           // S'il n'y a pas de table disponible
           System.out.println("\nIl n'y a pas assez de places pour accueillir ces clients\n");
        }
        else{
            // S'il y a assez de place alors on peut indiquer aux clients où se placer et attendre qu'ils commandent
            System.out.print("\nIls seront à/aux table(s) "+tables_client.get(0).getNumero());
            for (int i=1;i<tables_client.size();i++){
                System.out.print(" et "+tables_client.get(i).getNumero());
            }
            System.out.println("\n");
            restaurant.addClient(new GroupeClient(nb_clients, tables_client)); // On ajoute les clients
        }
		return restaurant;
    }


    /*
	 * Lors de la prise de commande le serveur sélectionne les plats et leur quantité parmi les plats de la 
	 * carte, une fois terminé un récap s'affiche pour qu'il confirme.
	 * Une fois confirmée la commande est envoyée dans les commandes en cours qui seront effectuées par les
	 * cuisiniers et barmans
	 */
    public Restaurant priseCommande(Restaurant restaurant, Scanner scanner){
        Commande commande = new Commande();
		// On prend le numéro de table pour pouvoir retrouver les clients
		System.out.println("\nQuel est le numéro de la table qui commande ?");
		int numtable=scanner.nextInt();
		commande.setTable(numtable);
		// Maintenant on prend la commande
		System.out.println("\nVeuillez entrer la commande : (Produit puis quantité)\n");
		String reponse = "oui";
		// On dit que tant que le serveur ne décide pas que c'est terminé on continue à ajouter des plats
		while(reponse.equals("oui")) {
			// On affiche la carte pour que le serveur sélectionne le bon plat
			for (int i=0;i<restaurant.getCarte().size();i++){
				System.out.print("["+i+"] "+restaurant.getCarte().get(i).getNom()+" : "+
				restaurant.getCarte().get(i).getPrix()+"$");
				// On doit checker si le plat est faisable avec les stocks actuels
				if(restaurant.getStock().checkDisponibilite(restaurant.getCarte().get(i))){
					// Si on peut faire le plat
					System.out.println(" (Disponible)");
				}
				else{
					System.out.println("Indisponible");
				}
			}
			System.out.print("Plat n° ");
			int numPlat = scanner.nextInt(); // On prend le numéro du plat dans la liste
			System.out.print("	x ");
			int quantitePlat = scanner.nextInt(); // Puis la quantité
			commande.ajoutPlat(restaurant.getCarte().get(numPlat), quantitePlat); // On ajoute à la commande

			System.out.println("Continuer ? (oui/non)");
			reponse=scanner.next();
		}
		// On fait un récap de la commande
		commande.recap();
		restaurant.addCommande(commande); // On envoie la commande aux cuisiniers/barmans
		restaurant.getStock().consommationStock(commande);
		return restaurant;
    }

	/*
	 * On va regarder parmi les commandes lesquelles sont prêtes à être livrées
	 * Le serveur va en choisir une, ce faisant on va la retirer des commandes actuelles du restaurant
	 * pour l'ajouter dans les commandes du groupe client associé
	 */
	public Restaurant recupCommande(Restaurant restaurant,Scanner scanner){
		// On récupère les commandes prêtes
		ArrayList<Commande> commande_prete= new ArrayList<Commande>();
		for(Commande commande : restaurant.getCommandes()){
			if(commande.est_prete()){
				commande_prete.add(commande);
			}
		}
		// S'il existe des commandes prêtes
		if(commande_prete.size()==0){

		// On affiche les commandes prêtes au serveur
		for(int i=0;i<commande_prete.size();i++){
			System.out.println("["+i+"] Pour la table "+commande_prete.get(i).getTable());
		}

		// On attend la réponse du serveur
		int reponse = scanner.nextInt();
		Commande commande_choisie = commande_prete.get(reponse);

		// Maintenant on va retrouver la commande dans celles en cours dans le restaurant pour l'enlever
		for (int i=0; i<restaurant.getCommandes().size();i++){
			if(restaurant.getCommandes().get(i)==commande_choisie){
				restaurant.getCommandes().remove(i);
			}
		}

		// Et ensuite la mettre dans le groupe client à la table correspondante à la commande
		for (int ind_client=0; ind_client<restaurant.getClientsActuels().size();ind_client++){
			for (int ind_table=0;ind_table<restaurant.getClientsActuels().get(ind_client).getTable().size();ind_table++){
				if (commande_choisie.getTable()==restaurant.getClientsActuels().get(ind_client).getTable().get(ind_table).getNumero()){
					restaurant.getClientsActuels().get(ind_client).nouvelleCommande(commande_choisie);
				}
			}
		}
		}
		else{
			System.out.println("Il n'y a aucune commande prête en ce moment");
		}
		return restaurant;
	}
}
