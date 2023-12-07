import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class Manager extends Employe{
    
    Manager(String nom, String prenom, int salaire, ArrayList<String> edt){
        super(nom,prenom,salaire,edt);
    }

    // permet d'imprimer la liste de course et de remplir les stocks
    void GestionDesStocks(Restaurant restaurant,Scanner scanner){ 
        System.out.println("\n\n---- Liste de Course ---- \n");
        Stock stock=restaurant.getStock();
        int total=0;

        for(Map.Entry<String, Integer> entry : stock.getListe().entrySet()) {
    		        String ingredient = entry.getKey();
    		        Integer nbr = entry.getValue();

                    if((stock.getListe_max().get(ingredient)-nbr)!=0){
                         System.out.println(" - "+ingredient
                         +" x "+(stock.getListe_max().get(ingredient)-nbr));
                         total+=(stock.getListe_max().get(ingredient)-nbr);
                    }
	    }
         System.out.println("\nNombre total d'ingrédients à acheter : "+total +"\n");
         System.out.println("\nLes courses ont elles été faites ?");
         String rep = scanner.next();
         if(rep.equals("oui")){
            restaurant.getStock().fillStock();
         }

    }

    void ajouterSupprimerEmployes(Restaurant restaurant,Scanner scanner){
        System.out.println("Voulez-vous supprimer où ajouter des employés\n");
        System.out.println("[1] - Ajouter \n[2]- Supprimer\n");
        int rep = utiles.enregistreInt(1, 2, scanner); 
        if(rep==2){
            ArrayList<Employe> liste=restaurant.getEmploye();

            for(int id_employe=0;id_employe<liste.size();id_employe++) {
                System.out.print("["+id_employe+"] - ");
                liste.get(id_employe).info();
            }
            System.out.println("\nNuméro de l'employé a enlever : ");
            int id_target = utiles.enregistreInt(0, liste.size(), scanner);
            liste.remove(id_target);
            System.out.println("Ce mécréant ne sera plus une nuisance\n");
        }
        else if(rep==1){ // ajout d'un employé
            // on recupere les infos de l'employé
            System.out.println("\nNom de l'employé : ");
            String nom = scanner.next();
            System.out.println("\nPrenom de l'employé : ");
            String Prenom= scanner.next();
            System.out.println("\nSalaire de l'employé : ");
            int salaire= utiles.enregistreInt(0, 100000, scanner);
            System.out.println("\nRole de l'employé :");
            String role= scanner.next(); 
            ArrayList<String> edt=new ArrayList<String>();
            if(role.equals("Barman")){ // pas de switch case pour utiliser equals
                restaurant.addEmploye(new Barman(nom, Prenom, salaire, edt));
            }
            else if(role.equals("Cuisinier")){
                restaurant.addEmploye(new Cuisinier(nom, Prenom, salaire, edt));
            }
            else if(role.equals("Serveur")){
                restaurant.addEmploye(new Serveur(nom, Prenom, salaire, edt));
            }
            else if(role.equals("Manager")){
                restaurant.addEmploye(new Serveur(nom, Prenom, salaire, edt));
            }
            else{ // securité
                System.out.println("Ce que vous avez entré n'existe pas\n");
            }
        }
        else{// securité
            System.out.println("Ce que vous avez entré n'existe pas\n");
        }
    }

    /*
     * On va terminer la journée et passer à la suivante
     */
    void finDeJournee(Restaurant restaurant, Scanner scanner){
        // On commence par vérifier qu'il ne reste plus de clients à servir
        if(restaurant.getClientsActuels().size()==0){
            restaurant.switchJours(); // On change le jour du restaurant
            // On va compter le nombre de chaque employé qui va pouvoir travailler pour la nouvelle journée
            int nb_serveur=0;
            int nb_cuisinier=0;
            int nb_barman=0;
            int nb_manager=0;
            for(int ind_employe=0;ind_employe<restaurant.getEmploye().size();ind_employe++){
                // Si l'employé travaille ce jour
                if(restaurant.getEmploye().get(ind_employe).getEdt().contains(restaurant.getJour())){
                    if(restaurant.getEmploye().get(ind_employe).getClass()==Serveur.class){
                        nb_serveur++;
                    }
                    else if (restaurant.getEmploye().get(ind_employe).getClass()==Cuisinier.class){
                        nb_cuisinier++;
                    }
                    else if (restaurant.getEmploye().get(ind_employe).getClass()==Barman.class){
                        nb_barman++;
                    }
                    else{
                        nb_manager++;
                    }
                }
            }

            // Maintenant on va regarder si on a assez de chaque employé pour ouvrir
            if(nb_serveur>=2 && nb_cuisinier>=4 && nb_barman>=1 && nb_manager>=1){
                restaurant.ouverture(true);
            }
            else{
                restaurant.ouverture(false);
            }
        }
    }

    void GestionEDT(Restaurant restaurant,Scanner scanner){
        ArrayList<Employe> liste=restaurant.getEmploye();
        for(int id_employe=0;id_employe<liste.size();id_employe++) {
            System.out.print("["+id_employe+"] - ");
            liste.get(id_employe).info();
        }
        System.out.println("\nNuméro de l'employé à modifier : ");
        int id_target = utiles.enregistreInt(0, liste.size(), scanner);
        int jours=0; // variable qui suit les jours de travail d'affilés
        ArrayList<String> semaine=new ArrayList<String>(Arrays.asList("Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi"));
        ArrayList<String> edt =new ArrayList<String>();

        for (int id_jour=0;id_jour<semaine.size();id_jour++){
            if(jours<2){
                System.out.println("\nFaire travailler le "+semaine.get(id_jour)+" ? (oui/non)");
                String rep = scanner.next();

                if(rep.equals("oui")){
                    if(liste.get(id_target).getClass()!=Manager.class && edt.size()==0){
                        jours++;
                    }
                    else if(liste.get(id_target).getClass()!=Manager.class && edt.get(edt.size()-1)==semaine.get(id_jour-1)){
                        // Si l'employé n'est pas un manager alors on doit tenir compte des jours d'affilés
                        jours++;
                    }
                    edt.add(semaine.get(id_jour));
                }
            }
            else{
                System.out.println("\nTravail impossible ce "+semaine.get(id_jour));
                jours=1; // On reset le compteur de jour d'affilés
            }
        }
        restaurant.getEmploye().get(id_target).setEdt(edt);
    }

    

    /*
     * On va afficher au manager les performances du restaurant aujourd'hui
     */
    void performances(Restaurant restaurant, Scanner scanner){
        System.out.println("Performances du jour ("+restaurant.getJour()+") :\n\n"+
        "- Nombre de groupes de clients actuel : "+restaurant.getClientsActuels().size()+
        "\n- Nombre de commandes en cours : "+restaurant.getCommandes().size()+
        "\n- Nombre de commandes terminées : "+restaurant.getNb_commandes_finies()+"\n");
    }
    
}