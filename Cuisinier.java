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
        if(liste_commandes.size()==0 || checkPlat(liste_commandes)==false){
            System.out.println("Il n'y a aucune commande\n");
        }
        else{
            while(i<liste_commandes.size()){                
                if(checkPlat(liste_commandes.get(i))){
                    System.out.println(" - Commande numéro "+(i+1)+" \n");
                }
            i+=1;
            }
            System.out.println("Indiquez le numéro de la commande a étudier\n");
            int choixCommande = scanner.nextInt();
            if(liste_commandes.get(choixCommande-1)==null){
                System.out.println("Cette commande n'existe pas \n");
            }
            else{
                HashMap<Plat, Integer> liste_plats=liste_commandes.get(choixCommande-1).getListe();
                for(Map.Entry<Plat, Integer> entry : liste_plats.entrySet()) {
    		        Plat plat = entry.getKey();
    		        Integer nbr_plat = entry.getValue();
                    if(plat.getType()== "plat"){
                        System.out.println(nbr_plat+" "+plat.getNom()+"\n");
                    }
		    }
            System.out.println("La commande est elle validée ?(oui/non)\n");
            String rep = scanner.next();
            if(rep=="non"){
            
                GestionDesCommandes( restaurant, scanner);
            }
            else{
                liste_commandes.get(choixCommande-1).setPlat_pret(true);
            }
        }
    }

        

    }

    boolean checkPlat(ArrayList<Commande> liste_commandes){
        for(Commande entry : liste_commandes) {
    		   for(Map.Entry<Plat, Integer> entry2 : entry.getListe().entrySet()) {
    		    Plat plat = entry2.getKey();
                
                if(plat.getType()== "plat" && entry.getPlat_pret()==false){
                    
                    return(true);
                    
                }
		    }
		}
        return(false);
    }

    boolean checkPlat(Commande commande){
        for(Map.Entry<Plat, Integer> entry : commande.getListe().entrySet()) {
    		    Plat plat = entry.getKey();
                if(plat.getType()== "plat" && commande.getPlat_pret()==false){
                    return(true);
                }
        }
        return(false);
    }
}
