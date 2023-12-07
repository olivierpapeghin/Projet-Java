import java.util.ArrayList;

public class Employe {
    // Les informations du contrat de travail
    private String nom;
    private String prenom;
    private int salaire;
    private ArrayList<String> edt;

    FonctionsUtiles utiles = new FonctionsUtiles(); // Pour pouvoir utiliser les fonctions utiles
    
    Employe(String nom, String prenom, int salaire, ArrayList<String> edt){
        this.nom=nom;
        this.prenom=prenom;
        this.salaire=salaire;
        this.edt=edt;
    }

    // Getters 

    String getNom(){
        return this.nom;
    }

    String getPrenom(){
        return this.prenom;
    }

    int getSalaire(){
        return this.salaire;
    }

    ArrayList<String> getEdt(){
        return this.edt;
    }

    // Setters

    void setNom(String nom){
        this.nom=nom;
    }

    void setPrenom(String prenom){
        this.prenom=prenom;
    }

    void setSalaire(int salaire){
        this.salaire=salaire;
    }

    void setEdt(ArrayList<String> edt){
        this.edt=edt;
    }

    // Autre

    void info(){
        // On affiche les infos de manière concise
        System.out.println("Nom : "+nom+"\nPrenom : "+prenom
                    +"\nSalaire : "+salaire+"$\nEmploi du temps de la semaine :");
        for(int i=0;i<edt.size();i++){
            System.out.print(" "+edt.get(i));
        }
        System.out.println("\n");
    }
}
