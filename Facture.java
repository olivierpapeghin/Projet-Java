import java.util.ArrayList;

public class Facture {
    int nb_clients;
    int numero_table;
    int prix_total;
    ArrayList<Commande> detail_commandes;

    Facture(int nb_clients, int numero_table, int prix_total, ArrayList<Commande> detail_commandes){
        this.nb_clients=nb_clients;
        this.numero_table=numero_table;
        this.prix_total=prix_total;
        this.detail_commandes=detail_commandes;
    }
}
