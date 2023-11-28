import java.util.HashMap;

public class Plat {
    String nom;
    HashMap<String, Integer> composition;

    // Constructeur

    Plat(String nom, HashMap<String, Integer> composition){
        this.nom=nom;
        this.composition=composition;
    }

    // Getters

    String getNom(){
        return this.nom;
    }

    HashMap<String, Integer> getComposition(){
        return composition;
    }

    // Setters

    void setNom(String nom){
        this.nom=nom;
    }

    void setComposition(HashMap<String, Integer> composition){
        this.composition=composition;
    }
}
