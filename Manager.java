import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Manager extends Employe{
    
    Manager(String nom, String prenom, float salaire, ArrayList<String> edt){
        super(nom,prenom,salaire,edt);
    }

    // permet d'imprimer la liste de course et de remplir les stocks
    void GestdionDesStocks(Restaurant restaurant,Scanner scanner){ 
        System.out.println("---- Liste de Course \n\n");
        Stock stock=restaurant.getStock();
        int total=0;
        for(Map.Entry<String, Integer> entry : stock.getListe().entrySet()) {
    		        String ingredient = entry.getKey();
    		        Integer nbr = entry.getValue();
                    if((stock.getListe_max().get(ingredient)-nbr)!=0){
                         System.out.println(" - "+(stock.getListe_max().get(ingredient)-nbr)
                         +" : "+ingredient+"\n");
                         total+=(stock.getListe_max().get(ingredient)-nbr);
                    }
	    }
         System.out.println("\nNombre total d'ingredients a acheter : "+total +"\n");
         System.out.println("\n Les courses ont elles été faites ?");
         String rep = scanner.next();
         if(rep.equals("oui")){
            restaurant.getStock().fillStock();
         }

    }

    void ajouterSupprimerEmployes(Restaurant restaurant,Scanner scanner){
        System.out.println("Voulez-vous supprimer où ajouter des employés\n");
        System.out.println("1 - ajouter // 2- Supprimer\n");
        int rep = scanner.nextInt();
        if(rep==2){
            ArrayList<Employe> liste=restaurant.getEmploye();
            for(Employe entry : liste) {
                System.out.println("Prenom :"+entry.getPrenom()+"Nom : "+entry.getNom()+ "Role : "+entry.getClass()+
                "Salaire : "+entry.getSalaire()+"\n");
            }
            System.out.println("\n indiquez le nom de l'employé a enlever : ");
            String nom = scanner.next();
            int i=0;
            for(Employe entry2 : liste) {
                if(nom.equals(entry2.getNom())){
                    restaurant.getEmploye().remove(i);
                }
                i+=1;
            }

        }
        else{
            System.out.println("\nNom de l'employés : ");
            String nom = scanner.next();
            System.out.println("\nPrenom de l'employés : ");
            String Prenom= scanner.next();
            System.out.println("\nSalaire de l'employés : ");
            float salaire= scanner.nextFloat();
            System.out.println("\nRole de l'employés : \n");
            String role= scanner.next(); 
            ArrayList<String> edt=new ArrayList<String>();
            if(role.equals("barman")){ // pas de switch case pour utiliser equals
                restaurant.addEmploye(new Barman(nom, Prenom, salaire, edt));
            }
            else if(role.equals("cuisinier")){
                restaurant.addEmploye(new Cuisinier(nom, Prenom, salaire, edt));
            }
            else if(role.equals("serveur")){
                restaurant.addEmploye(new Serveur(nom, Prenom, salaire, edt));
            }
            else if(role.equals("manager")){
                restaurant.addEmploye(new Serveur(nom, Prenom, salaire, edt));
            }
            else{
                System.out.println("Ce que vous avez entré n'existe pas\n");
            }
        }
    }

    
}






//REFLEXION CYCLES JOURS/HEURES
/*
heures ouverture fermeture : A DEFINIR
jours d'ouverture : 6/7 du lundi au samedi

idée : chaque groupe de client a une durée de consommation (fixe ou random ?)
       à chaque fois que le serveur finit la transaction des clients, l'heure de la journée augmente
       si l'heure est supérieur à H-1, on n'accepte plus personne
ex : un groupe de 2-4 prendra 1h à 2h, un groupe de 6-8 2h à 3h




 */