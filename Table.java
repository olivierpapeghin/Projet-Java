
public class Table {
	int numero;
	int nb_places;
	boolean occupe;
	
	Table(int nbr,int nbr_places){
		this.numero=nbr; // numéro de la table
		this.nb_places=nbr_places; // nombre de places
		this.occupe=false; // si des clients sont à la table
	}
	
	void switchOccupation() { // indique si des clients se mettent à table où s'en vont
		if(this.occupe==true) {
			this.occupe=false;
		}
		else {
			this.occupe=true;
		}
	}

}
