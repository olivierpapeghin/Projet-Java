import java.util.ArrayList;
import java.util.Scanner;

public class Serveur extends Employe{
    
    Serveur(String nom, String prenom, float salaire, ArrayList<String> edt){
        super(nom,prenom,salaire,edt);
    }


    /*On prend le nombre de clients, puis on regarde s'il y a assez de place pour eux
	 * si oui on leur assigne des tables (qui deviennent donc occupée)
	 * sinon on ne peut pas les accueillir
	 */
    public void accueilClient(Restaurant restaurant, Scanner scanner){
        System.out.println("Combien de clients y a-t-il ?");
        int nb_clients=scanner.nextInt();
        // On regarde s'il y a assez de tables pour accueillir les clients
        ArrayList<Table> tables_client = restaurant.checkPlaces(nb_clients);
        if (tables_client.size()==0){
           // S'il n'y a pas de table disponible
           System.out.println("Il n'y a pas assez de places pour accueillir ces clients\n");
        }
        else{
            // S'il y a assez de place alors on peut indiquer aux clients où se placer et attendre qu'ils commandent
            System.out.print("Ils seront à/aux table(s) "+tables_client.get(0).getNumero());
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
		System.out.println("Quel est le numéro de la table qui commande ?");
		int numtable=scanner.nextInt();
		// Maintenant on prend la commande
		System.out.println("Veuillez entrer la commande : (Produit puis quantité)\n");
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
		System.out.println("Pour la table "+numtable);
		commande.recap();
		restaurant.addCommande(commande); // On envoie la commande aux cuisiniers/barmans
		restaurant.getStock().consommationStock(commande);
    }
}
