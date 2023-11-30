import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Barman extends Employe{
    
    Barman(String nom, String prenom, float salaire, ArrayList<String> edt){
        super(nom,prenom,salaire,edt);
    }

    void GestionDesCommandes(Restaurant restaurant){
        Scanner scanner = new Scanner(System.in); 
        ArrayList<Commande> liste_commandes=restaurant.getCommandes();
        System.out.println("Liste des commandes à faire :\n");
        int i=0;
        if(liste_commandes.size()==0){
            System.out.println("Il n'y a aucune commandes");
        }
        else{
            while(i<liste_commandes.size()){
            if(liste_commandes.get(i).getBoisson_prete()!=true){
                System.out.println(" la commande numéro "+i+1+" contient:\n");
                HashMap<Plat, Integer> liste_plats=liste_commandes.get(i).getListe();
                for(Map.Entry<Plat, Integer> entry : liste_plats.entrySet()) {
    			    Plat plat = entry.getKey();
    			    Integer nbr_plat = entry.getValue();
                    if(plat.getType()=="boisson" ){
                     System.out.println(nbr_plat+" "+plat.getNom());
                    }
			    }

            }
            i+=1;
        }
        System.out.println("indiquez le numéro de la commande faite\n");
        int choixCommande = scanner.nextInt();
        if(liste_commandes.get(choixCommande-1)==null){
            // gestion des problèmes
            scanner.close();
        }
        else{
            liste_commandes.get(choixCommande-1).setBoisson_prete(true);
            
        }
        scanner.close();

        }
       

    }
}
