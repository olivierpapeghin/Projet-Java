import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Serveur extends Employe{

    Serveur(String nom, String prenom, int salaire, ArrayList<String> edt){
        super(nom,prenom,salaire,edt);
    }


    /*
	 * On prend le nombre de clients, puis on regarde s'il y a assez de place pour eux
	 * si oui on leur assigne des tables (qui deviennent donc occupée)
	 * sinon on ne peut pas les accueillir
	 */
    public void accueilClient(Restaurant restaurant, Scanner scanner){
        System.out.println("\nCombien de clients y a-t-il ?");
        int nb_clients=utiles.enregistreInt(1, 20, scanner);

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
    }


    /*
	 * Lors de la prise de commande le serveur sélectionne les plats et leur quantité parmi les plats de la 
	 * carte, une fois terminé un récap s'affiche pour qu'il confirme.
	 * Une fois confirmée la commande est envoyée dans les commandes en cours qui seront effectuées par les
	 * cuisiniers et barmans
	 */
    public void priseCommande(Restaurant restaurant, Scanner scanner){
        Commande commande = new Commande();

		// On prend le numéro de table pour pouvoir retrouver les clients

		// On doit commencer par déterminer quelles tables sont occupées et peuvent donc commander
		// On sait que les tables sont données dans l'ordre de la liste, on doit donc trouver la première table
		// libre pour savoir où s'arrêter 
		int max=0;
		int indice_table=0;
		while(max==0 && indice_table!=restaurant.getTables().size()){

			if(restaurant.getTables().get(indice_table).occupe==false){ // Si on rencontre une table libre
				max=indice_table; // On peut déduire que la liste des tables occupées s'arrête à cet indice
			}
			indice_table++; // On passe à la table suivante
		}
		// Si on n'a toujours pas de max (toutes les tables sont occupées)
		if(max==restaurant.getTables().size()){
			max=restaurant.getTables().size();
		}

		if(restaurant.getClientsActuels().size()!=0){ // S'il y a des clients à servir
			System.out.println("\nQuel est le numéro de la table qui commande ?");
			int numtable=utiles.enregistreInt(1, max, scanner);
			
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
				int numPlat = utiles.enregistreInt(0, 15, scanner); // On prend le numéro du plat dans la liste
				System.out.print("	x ");
				int quantitePlat = utiles.enregistreInt(1, 100, scanner); // Puis la quantité
				commande.ajoutPlat(restaurant.getCarte().get(numPlat), quantitePlat); // On ajoute à la commande

				System.out.println("Continuer ? (oui/non)");
				reponse=scanner.next();
			}
			// On fait un récap de la commande
			commande.recap();
			commande.autoAnalyse(); // On prépare déjà la commande pour les cuisiniers/barmans
			restaurant.addCommande(commande); // On envoie la commande aux cuisiniers/barmans
			restaurant.getStock().consommationStock(commande);
		}
		else{
			System.out.println("Il n'y a pas de clients.");
		}
    }


	/*
	 * On va regarder parmi les commandes lesquelles sont prêtes à être livrées
	 * Le serveur va en choisir une, ce faisant on va la retirer des commandes actuelles du restaurant
	 * pour l'ajouter dans les commandes du groupe client associé
	 */
	public void recupCommande(Restaurant restaurant,Scanner scanner){
		// On récupère les commandes prêtes
		ArrayList<Commande> commande_prete = new ArrayList<Commande>();
		for(Commande commande : restaurant.getCommandes()){
			if(commande.est_prete()){
				commande_prete.add(commande);
			}
		}
		// S'il existe des commandes prêtes
		if(commande_prete.size()!=0){
			// On affiche les commandes prêtes au serveur
			for(int i=0;i<commande_prete.size();i++){
				System.out.print("["+i+"] ");
				commande_prete.get(i).recap();
			}

			// On attend la réponse du serveur
			int reponse = utiles.enregistreInt(0,commande_prete.size()-1,scanner);
			Commande commande_choisie = commande_prete.get(reponse);

			// Maintenant on va retrouver la commande dans celles en cours dans le restaurant pour l'enlever
			for (int i=0; i<restaurant.getCommandes().size();i++){
				if(restaurant.getCommandes().get(i)==commande_choisie){
					restaurant.getCommandes().remove(i);
				}
			}

			// Et ensuite la mettre dans le groupe client de la table correspondante à la commande
			for (int ind_client=0; ind_client<restaurant.getClientsActuels().size();ind_client++){
				for (int ind_table=0;ind_table<restaurant.getClientsActuels().get(ind_client).getTable().size();ind_table++){
					if (commande_choisie.getTable()==restaurant.getClientsActuels().get(ind_client).getTable().get(ind_table).getNumero()){
						restaurant.getClientsActuels().get(ind_client).nouvelleCommande(commande_choisie);
					}
				}
			}
			System.out.println("Commande livrée");
			// Enfin pour les performances on ajoute 1 aux commandes finies
			restaurant.addCommandeFinie();
		}
		else{
			System.out.println("Il n'y a aucune commande prête en ce moment");
		}
	}


	/*
	 * 
	 */
	public void addition(Restaurant restaurant,Scanner scanner){
		int max=0;
		int indice_table=0;
		while(max==0 || indice_table!=restaurant.getTables().size()){
			if(restaurant.getTables().get(indice_table).occupe==false){ // Si on rencontre une table libre
				max=indice_table; // On peut déduire que la liste des tables occupées s'arrête à cet indice
			}
			indice_table++; // On passe à la table suivante
		}

		//s'il y a des clients
		if(restaurant.getClientsActuels().size()!=0){
			String reponse = "oui";
			boolean rien_en_cours = true;
			int numtable = 0;

			ArrayList<Commande> comEnCours = restaurant.getCommandes();

			//si tous les clients (et donc toutes les tables) ont été servis
			while(reponse.equals("oui")){
				System.out.println("\nQuel est le numéro de la table qui demande l'addition ?");
				numtable = utiles.enregistreInt(1, max, scanner);
				for(Commande comm : comEnCours){
					if(comm.getTable() == numtable){
						rien_en_cours = false;
					}
				}
				System.out.println("\nY a-t-il d'autres tables pour ce groupe de clients (et qu'elles ont été servies) ? (oui/non)");
				reponse = scanner.next();
			}

			
			if(rien_en_cours == true){
				//le fichier est créé une fois son nom rentré
				System.out.println("\nQuel sera le nom du fichier ?");
				String nom_fichier = scanner.next();
				File fichier = new File(nom_fichier);
				try{
					boolean fichier_cree = fichier.createNewFile();
					if(fichier_cree){
						System.out.println("Fichier créé avec succès");
					}
					else{
						System.out.println("Le fichier existe déjà");
					}
				}
				catch (IOException e){
					System.err.println("Erreur lors de la création du fichier : " + e.getMessage());
				}

				//je crée une liste groupeclient pour le foreach
				ArrayList<GroupeClient> clients = new ArrayList<GroupeClient>();
				clients = restaurant.getClientsActuels();
				//je crée une liste table pour le foreach
				ArrayList<Table> tables = new ArrayList<Table>();
				boolean fact_ecrite = false;
				int prix_tot = 0;
				Facture facture;

				for(GroupeClient c : clients){
					tables = c.getTable(); //je récupère la table du groupeclient
					for(Table t : tables){
						if(t.getNumero() == numtable && fact_ecrite == false){ //je vérifie si c'est bien la table que je recherche
							for(Commande com : c.getCommandes()){
								prix_tot += com.prixTotal();
							}
							facture = new Facture(c.getClients(), prix_tot, c.getCommandes());
							facture.Ecriture(nom_fichier);
							restaurant.addFacture(facture);
							fact_ecrite = true;
						}
					}
				}
			}
			else{
				System.out.println("Certains clients de ce groupe n'ont pas encore reçu leur commande.");
			}

			//RETIRER LES CLIENTS
			// On va chercher le groupe client à retirer à partir du numéro de table donné plus haut
			for (int id_client=0; id_client<restaurant.getClientsActuels().size();id_client++){

				for(Table table : restaurant.getClientsActuels().get(id_client).getTable()){

					if(table.getNumero()==numtable){ // On a trouvé le groupe client en question
						restaurant.getClientsActuels().remove(id_client);
						break; // On sort de la boucle pour éviter les problèmes
					}
				}
			}
			
		}
		else{
			System.out.println("Il n'y a pas de clients.");
		}

	}
}
