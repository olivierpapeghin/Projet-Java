import java.util.HashMap;

public class Plat {
    String nom;
    int prix;
    HashMap<String, Integer> composition;

    // Constructeur

    Plat(String nom, int prix, HashMap<String, Integer> composition){
        this.nom=nom;
        this.prix=prix;
        this.composition=composition;
    }

    // Getters

    String getNom(){
        return this.nom;
    }

    int getPrix(){
        return this.prix;
    }

    HashMap<String, Integer> getComposition(){
        return composition;
    }

    // Setters

    void setNom(String nom){
        this.nom=nom;
    }

    void setPrix(int prix){
        this.prix=prix;
    }

    void setComposition(HashMap<String, Integer> composition){
        this.composition=composition;
    }
}
