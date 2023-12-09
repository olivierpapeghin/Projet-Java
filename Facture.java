// Pour écrire en String avec précision dans les txt
// import java.util.ArrayList;
// import java.io.PrintWriter;
// import java.io.FileNotFoundException;

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
    //private int numero_table;
    private int prix_total;
    private ArrayList<Commande> detail_commandes;

    Facture(int nb_clients, /*int numero_table,*/ int prix_total, ArrayList<Commande> detail_commandes){
        this.nb_clients=nb_clients;
        //this.numero_table=numero_table;
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
			out.writeObject(LocalDate.now());
			out.writeInt(nb_clients);
			//out.writeInt(numero_table);
            out.writeInt(prix_total);
            System.out.println("Résumé de la commande :\n");
            for(Commande c : detail_commandes){
                c.recap();
                HashMap<Plat,Integer> tempo = c.getListe();
                for(Plat p : tempo.keySet()){
                    out.writeObject("nom du plat : " + p.getNom());
                    out.writeObject("type de plat : " + p.getType());
                    out.writeObject("composition du plat : " + p.getComposition());
                    out.writeObject("prix du plat : " + p.getPrix());
                }
                for(Integer i : tempo.values()){
                    out.writeObject("nombre de plats dans l'ordre : " + i);
                }
			    //out.writeObject(tempo);  //erreur de serialization de Plat on sait pas pourquoi
            }
			System.out.println("\nEcriture de la facture réussie.\n");
			out.close();
		}
		catch(IOException e){
			System.out.println("Problème détecté : " + e);
		}
    }

}
