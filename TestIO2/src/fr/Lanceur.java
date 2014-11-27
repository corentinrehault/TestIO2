package fr;
import fr.controleur.Controleur;
import fr.service.EcrireFichiers;

public class Lanceur {
	public static void main(String[] args) throws Exception {
		
		Controleur controleur = new Controleur();
		
		controleur.readFile();
		
		EcrireFichiers.EcritureFichiers();
		
	}
	
}