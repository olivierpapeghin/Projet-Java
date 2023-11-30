import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Barman extends Employe{
    
    Barman(String nom, String prenom, float salaire, ArrayList<String> edt){
        super(nom,prenom,salaire,edt);
    }

    int GestionDesCommandes(Restaurant restaurant){
        Scanner scanner = new Scanner(System.in); 
        ArrayList<Commande> liste_commandes=restaurant.getCommandes();
        System.out.println("Liste des commandes à faire :\n");
        int i=0;
        while(i<liste_commandes.size()){
            System.out.println(" la commande numéro "+i+1+" contient:\n");
            HashMap<Plat, Integer> liste_plats=liste_commandes.get(i).getListe();
            for(Map.Entry<Plat, Integer> entry : liste_plats.entrySet()) {
    			Plat plat = entry.getKey();
    			Integer nbr_plat = entry.getValue();
                if(plat.getType()=="boisson"){
                    System.out.println(nbr_plat+" "+plat.getNom());
                }
			}
        }
        System.out.println("indiquez le numéro de la commande faite, 0 pour sortir du menu\n");
        int choixCommande = scanner.nextInt();
        if(choixCommande==0){
            scanner.close();
            return(0);
        }
        else{
            liste_commandes.get(choixCommande-1).setBoisson_prete(true);
            GestionDesCommandes(restaurant);
        }
        scanner.close();
        return(0);
    }
}
