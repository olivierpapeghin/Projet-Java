
public class Table {
	int numero;
	int nb_places;
	boolean occupe;
	
	Table(int nbr,int nbr_places){
		this.numero=nbr; // numéro de la table
		this.nb_places=nbr_places; // nombre de places
		this.occupe=false; // si des clients sont à la table
	}
	
	// Setters

	void setNumero(int num){
		numero=num;
	}

	void setNb_places(int nb){
		nb_places=nb;
	}

	void setOccupe(boolean occup){
		occupe=occup;
	}

	// Getters

	int getNumero(){
		return this.numero;
	}

	int getNb_places(){
		return this.nb_places;
	}

	boolean getOccupe(){
		return this.occupe;
	}

}
