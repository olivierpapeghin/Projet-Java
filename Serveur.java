import java.util.ArrayList;
import java.util.Scanner;

public class Serveur extends Employe{
    
    Serveur(String nom, String prenom, float salaire, ArrayList<String> edt){
        super(nom,prenom,salaire,edt);
    }

    public void accueilClient(Restaurant restaurant, Scanner scanner){
        System.out.println("Combien de clients y a-t-il ?");
        int nb_clients=scanner.nextInt();
        // On regarde s'il y a assez de tables pour accueillir les clients
        ArrayList<Table> tables_client = restaurant.checkPlaces(nb_clients);
        if (tables_client.size()==0){
           // S'il n'y a pas de table disponible
           System.out.println("Il n'y a pas assez de places pour accueillir ces clients");
        }
        else{
        // S'il y a assez de place alors on peut indiquer aux clients où se placer et attendre qu'ils commandent
        System.out.print("Ils seront à/aux table(s) "+tables_client.get(0).getNumero());
        for (int i=1;i<tables_client.size();i++){
           System.out.print(" et "+tables_client.get(i).getNumero());
        }
        System.out.println();
        restaurant.addClient(new GroupeClient(nb_clients, tables_client)); // On ajoute les clients
        }
    }
}
