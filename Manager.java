import java.util.ArrayList;

public class Manager extends Employe{
    
    Manager(String nom, String prenom, float salaire, ArrayList<String> edt){
        super(nom,prenom,salaire,edt);
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