import java.util.HashMap;

public class Plat {
    String nom;
    int prix;
    HashMap<String, Integer> composition;
    String type;

    // Constructeur

    Plat(String nom, int prix, HashMap<String, Integer> composition,String type){
        this.nom=nom;
        this.prix=prix;
        this.composition=composition;
        this.type=type;
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

    String getType(){
        return type;
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

    void setType(String type){
        this.type=type;
    }
}
