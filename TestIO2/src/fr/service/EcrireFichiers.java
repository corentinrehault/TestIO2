package fr.service;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

import fr.bean.Mot;
import fr.controleur.Controleur;


public class EcrireFichiers {

	public static void EcritureFichiers() throws IOException, FileNotFoundException {

		DataOutputStream listeComplete;
		DataOutputStream ver;
		DataOutputStream alpha;
		DataOutputStream nbreLettres;

		Iterator<Entry<String, ArrayList<Mot>>> parcoursAlpha = Controleur.listeMotParLettre.entrySet().iterator();
		Iterator<Entry<Integer, ArrayList<Mot>>> parcoursNbreLettres = Controleur.ListeNombreDeLettres.entrySet().iterator();
		Iterator<String> parcoursVer = Controleur.listeMotsVer.iterator();
		Iterator<String> parcoursComplet = Controleur.listeComplete.iterator();

		String nextLine = System.getProperty("line.separator");		
	
		ver = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("mots_en_ver.txt"))));
		while (parcoursVer.hasNext()) {
			String valeur = parcoursVer.next();
			ver.writeBytes(valeur + nextLine);
		}
		ver.close();
		
		listeComplete = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File(Controleur.titre + ".txt"))));
		while (parcoursComplet.hasNext()) {
			String valeur = parcoursComplet.next();
			listeComplete.writeBytes(valeur + nextLine);
		}
		listeComplete.close();

		alpha = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("mots_par_lettre.txt"))));
		while (parcoursAlpha.hasNext()) {
			Entry<String, ArrayList<Mot>> valeur = parcoursAlpha.next();
			alpha.writeBytes(valeur.getKey() + " : " + valeur.getValue().size() + " mots." + nextLine);
		}
		alpha.close();

		nbreLettres = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("nombre_de_lettres.txt"))));
		while (parcoursNbreLettres.hasNext()) {
			Entry<Integer, ArrayList<Mot>> valeur = parcoursNbreLettres.next();
			nbreLettres.writeBytes(valeur.getKey() + " : " + valeur.getValue().size() + " mots." + nextLine);
		}
		nbreLettres.close();

	}

}
