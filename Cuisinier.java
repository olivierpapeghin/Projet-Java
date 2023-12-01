import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Cuisinier extends Employe{
    
    Cuisinier(String nom, String prenom, float salaire, ArrayList<String> edt){
        super(nom,prenom,salaire,edt);
    }

    void GestionDesCommandes(Restaurant restaurant,Scanner scanner){
        ArrayList<Commande> liste_commandes=restaurant.getCommandes();
        System.out.println("Liste des commandes à faire :\n");
        int i=0;
        if(liste_commandes.size()==0 ){
            System.out.println("Il n'y a aucune commandes\n");
        }
        else{
            while(i<liste_commandes.size()){
            if(liste_commandes.get(i).getPlat_pret()!=true){
                System.out.println(" la commande numéro "+i+1+" \n");
            }
            i+=1;
        }
        System.out.println("indiquez le numéro de la commande a étudier\n");
        int choixCommande = scanner.nextInt();

        
        HashMap<Plat, Integer> liste_plats=liste_commandes.get(choixCommande-1).getListe();
            for(Map.Entry<Plat, Integer> entry : liste_plats.entrySet()) {
    		    Plat plat = entry.getKey();
    		    Integer nbr_plat = entry.getValue();
                if(plat.getType()=="plat" ){
                 System.out.println(nbr_plat+" "+plat.getNom()+"\n");
                }
		    }
        if(liste_commandes.get(choixCommande-1)==null){
        // gestion des problèmes
        
        }
        else{
            liste_commandes.get(choixCommande-1).setPlat_pret(true);
            
        }

        }


    }
}
