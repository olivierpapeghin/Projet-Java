import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Barman extends Employe{

    FonctionsUtiles utiles = new FonctionsUtiles();
    
    Barman(String nom, String prenom, float salaire, ArrayList<String> edt){
        super(nom,prenom,salaire,edt);
    }

    void GestionDesCommandes(Restaurant restaurant,Scanner scanner){
        ArrayList<Commande> liste_commandes=restaurant.getCommandes();
        System.out.println("Liste des commandes à faire :\n");
        int i=0;
        if(liste_commandes.size()==0 || checkBoisson(liste_commandes)==false){
            System.out.println("Il n'y a aucune commandes\n");
        }
        else{
            while(i<liste_commandes.size()){
                if(checkBoisson(liste_commandes.get(i))){
                    System.out.println("["+(i+1)+"] - Pour la table "+liste_commandes.get(i).getTable()+" \n");
                }
            i+=1;
            }
            System.out.println("Indiquez le numéro de la commande a étudier\n");
            int choixCommande = utiles.enregistreInt(1,liste_commandes.size(),scanner);
            if(liste_commandes.get(choixCommande-1)==null){
                System.out.println("Cette commande n'existe pas \n");
            }
            else{

                HashMap<Plat, Integer> liste_plats=liste_commandes.get(choixCommande-1).getListe();
                for(Map.Entry<Plat, Integer> entry : liste_plats.entrySet()) {
    		        Plat plat = entry.getKey();
    		        Integer nbr_plat = entry.getValue();
                    if(plat.getType()== "boisson"){
                        System.out.println(nbr_plat+" "+plat.getNom()+"\n");
                    }
		        }
            System.out.println("Valider la commande ?(oui/non)\n");
            String rep = scanner.next();
            if(rep.equals("oui")){
            
                liste_commandes.get(choixCommande-1).setBoisson_prete(true);
            }
        }
    }

        
        


    }

    boolean checkBoisson(ArrayList<Commande> liste_commandes){
        for(Commande entry : liste_commandes) {
    		   for(Map.Entry<Plat, Integer> entry2 : entry.getListe().entrySet()) {
    		    Plat plat = entry2.getKey();
                if(plat.getType()== "boisson" && entry.getBoisson_prete()==false){
                    return(true);
                }
		    }
		}
        return(false);
    }

    boolean checkBoisson(Commande commande){
        for(Map.Entry<Plat, Integer> entry : commande.getListe().entrySet()) {
    		    Plat plat = entry.getKey();
                if(plat.getType()== "boisson" && commande.getBoisson_prete()==false){
                    return(true);
                }
        }
        return(false);
    }
}
