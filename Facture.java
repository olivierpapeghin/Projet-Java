
// Pour écrire en Sérializant dans les txt
import java.io.BufferedOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;


public class Facture {

    private int nb_clients;
    private int prix_total;
    private ArrayList<Commande> detail_commandes;

    Facture(int nb_clients, int prix_total, ArrayList<Commande> detail_commandes){
        this.nb_clients=nb_clients;
        this.prix_total=prix_total;
        this.detail_commandes=detail_commandes;
    }

    public void Ecriture(String nom_fichier){
        final String dataFile = nom_fichier;
		try (
			ObjectOutputStream out = new ObjectOutputStream(
					new BufferedOutputStream(
							new FileOutputStream(dataFile)
					)
			)
		)
		{
			out.writeObject("Date de paiement : " + LocalDate.now());
			out.writeObject("Nombre clients : " + nb_clients);
            out.writeObject("Prix total de la commande : " + prix_total);
            System.out.println("Résumé de la commande :");
            for(Commande c : detail_commandes){
                c.recap();
                HashMap<Plat,Integer> tempo = c.getListe();
                for(Plat p : tempo.keySet()){
                    out.writeObject("\nplat : " + p.getNom());
                    out.writeObject("\ntype : " + p.getType());
                    out.writeObject("\ncomposition : " + p.getComposition());
                    out.writeObject("\nprix : " + p.getPrix());
                    System.out.println("Nom du plat : " + p.getNom() + " de type : " + p.getType() + " est composé de : " + p.getComposition() + " à un prix de : " + p.getPrix());
                    
                }
                for(Integer i : tempo.values()){
                    out.writeObject("\nquantité (ordre d'affichage) : " + i);
                    System.out.println("Nombre de plats dans l'ordre : " + i);
                }
            }
			System.out.println("\nEcriture de la facture réussie.\n");
			out.close();
		}
		catch(IOException e){
			System.out.println("Problème détecté : " + e);
		}

    }

}
