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
         if(rep=="oui"){
            restaurant.getStock().fillStock();
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