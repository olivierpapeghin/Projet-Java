import java.util.Scanner;

public class FonctionsUtiles {
    	/*
	 * On va prendre la réponse de l'utilisateur jusqu'à ce qu'il donne un entier dans la bonne plage
	 */
	public int enregistreInt(int min, int max,Scanner scanner){
		int reponse=-1;
		// Tant qu'on n'est pas dans la bonne plage
		while(reponse>max || reponse<min){
			try{ // On teste si la réponse de l'utilisateur est un entier
				reponse=Integer.parseInt(scanner.next());
			}
			catch(Throwable e){ // S'il y a une erreur
				System.out.println("Vous devez rentrer un entier compris entre "+min+" et "+max+".");
				reponse=-1;
			}
			System.out.println("Vous devez rentrer un entier compris entre "+min+" et "+max+".");
		}
		return reponse;
	};
}
