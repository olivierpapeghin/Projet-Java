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
    private int numero_table;
    private int prix_total;
    private ArrayList<Commande> detail_commandes;

    Facture(int nb_clients, int numero_table, int prix_total, ArrayList<Commande> detail_commandes){
        this.nb_clients=nb_clients;
        this.numero_table=numero_table;
        this.prix_total=prix_total;
        this.detail_commandes=detail_commandes;
    }

    public void Ecriture(){
        /*PrintWriter fichier = null;
		try {
			fichier = new PrintWriter("facture.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        try {
            fichier.println("========== FACTURE =========="); //ajout option date/heure/numéro de facture ?
            fichier.println("Nombre de clients : "+nb_clients);
            fichier.println("Prix total : "+prix_total);
            fichier.println("Detail de la commande : \n");
            for(int indice_com = 0; indice_com < detail_commandes.size(); indice_com++){
                for(int indice_list = 0; indice_list < detail_commandes.size(); indice_list++){
                    fichier.println(detail_commandes.get(indice_com).getListe().get(indice_list));
                }
            }
            System.out.println("Ecriture réussie !");
        }
        catch(Exception x) {
            System.err.format("Exception : %s%n", x);
            x.getStackTrace();
        }*/

        final String dataFile = "facture1.txt";
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
			out.writeInt(numero_table);
            out.writeInt(prix_total);
            System.out.println("Résumé de la commande :\n");
            for(Commande c : detail_commandes){
                c.recap();
                HashMap<Plat,Integer> tempo = c.getListe();
			    out.writeObject(tempo);
            }
			System.out.println("\nEcriture de la facture réussit.\n");
			out.close();
		}
		catch(IOException e){
			System.out.println("Problème détecté : " + e);
		}
    }
}
