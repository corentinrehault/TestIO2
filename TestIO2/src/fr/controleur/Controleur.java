package fr.controleur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import fr.bean.Mot;
import fr.service.Converter;


/**
 * @author Altenide-mov
 * Se charge des traitements et des appels aux services.
 */
public class Controleur {

	// map globale contient en clé les lettres et en value la liste des mots commençant par cette lettre.
	public static Map<String, ArrayList<Mot>> listeMotParLettre = new HashMap<String, ArrayList<Mot>>();
	// map contenant en K un entier et en V la liste des mots.
	public static Map<Integer, ArrayList<Mot>> ListeNombreDeLettres = new HashMap<Integer, ArrayList<Mot>>();
	// arraylist Ver
	public static List<String> listeMotsVer = new ArrayList<String>();
	// arraylist Complète
	public static List<String> listeComplete = new ArrayList<String>();
	

	// la ligne etudiée
	String line;
	
	// on stocke l'unicode de la première lettre

	static String lettreTemoinUnicode;
	static String lettreTemoinUtf8;
	int nbreDeLettres;
	public static String titre = new String();


	public void readFile() throws MalformedURLException, IOException, InterruptedException {

		// on lit le fichier externe
		BufferedReader r = new BufferedReader(new InputStreamReader(new URL("http://www.altenide.com/lmg.html").openStream(), "ISO-8859-1"));


		// on déclare un id pour nos mots ( à toutes fin utiles... )
		int idMot = 0;
		



		// on parcours lebufferReader line par ligne et on traite seulement si la ligne ne contient pas de caractère "<,>"
		long first = System.currentTimeMillis();
		while ((line = r.readLine()) != null) {
			
			if (line.contains("<title>")) {
				titre = line.toString();
				titre = titre.replace("<title>", "");
				titre = titre.replace("</title>", "");
				titre = titre.replace(" ", "_");
			}

			if (!line.contains("<")) {

				// on récupère l'unicode de la lettre courante du mot récupéré
				String lettreCouranteUnicode = String.format("\\u%04x", (int)line.charAt(0));
				String lettreCouranteUtf8 = Converter.convertUnicodeToString(lettreCouranteUnicode);



				// cas particulier de la première lettre
				if (idMot == 0) {

					// on définit la lettre témoin
					lettreTemoinUnicode = lettreCouranteUnicode;
					lettreTemoinUtf8 = lettreCouranteUtf8;
					// on créé une clé de map avec une liste vide
					listeMotParLettre.put(lettreCouranteUtf8, new ArrayList<Mot>());

				}

				// on incrémente l'id
				++idMot;

				// On détecte si la première lettre du mot est différente de la première lettre du mot témoin.
				// si la listeDesLettres ne contient pas cette lettre on l'ajoute.


				if (!lettreTemoinUnicode.equals(lettreCouranteUnicode) && !listeMotParLettre.containsKey(lettreCouranteUtf8)) {

					// on créé également une ligne avec une liste vide dans la hasmap.
					listeMotParLettre.put(lettreCouranteUtf8, new ArrayList<Mot>());

				}	
				
				
				
				
				Mot mot =  new Mot();
				mot.setFirstLetter(line.charAt(0));
				mot.setId(idMot);
				mot.setSize(line.length());
				mot.setTerme(line);
				mot.setEndByver(line.endsWith("ver"));
				
				
				if (!ListeNombreDeLettres.containsKey(mot.getSize())) {
					ListeNombreDeLettres.put(mot.getSize(), new ArrayList<Mot>());
				}
				
				if (mot.isEndByver()) {
					listeMotsVer.add(mot.getTerme());
				}

				// on ajoute la lettre courante sur la bonne clé de la map

				listeMotParLettre.get(lettreCouranteUtf8).add(mot);
				ListeNombreDeLettres.get(mot.getSize()).add(mot);
				
				//On ajoute le mot à la liste complète
				
				listeComplete.add(mot.getTerme());

				// on réaffecte la lettre témoin

				lettreTemoinUnicode = lettreCouranteUnicode;

			}






		}

		long last = System.currentTimeMillis();
		Long time = last - first ;
		System.err.println("Time : " + time);

		System.out.println("Taille de l'alphabet : " + listeMotParLettre.size());
		System.out.println(ListeNombreDeLettres.size());

		int totalsize = 0;

		Iterator<Map.Entry<String,ArrayList<Mot>>> iterator = listeMotParLettre.entrySet().iterator();
		
		while (iterator.hasNext()) {
			
			Map.Entry<String,ArrayList<Mot>> entry = (Map.Entry<String,ArrayList<Mot>>) iterator.next();
			String key = (String) entry.getKey();
			ArrayList<Mot> value = (ArrayList<Mot>) entry.getValue();

			//System.out.println(key + " = " + value);

			totalsize = totalsize  + value.size();
		}

		System.out.println("totalsize : " + totalsize + " contre : " + idMot);

	}

	// fin de traitement bouclage de l'alphabet !



	//on parcours les clés de la map

	//		while (listeMotParLettre.keySet() != null) {
	//			
	//			System.out.println("keyset is : " + listeMotParLettre.keySet());
	//	
	//		}


	// résultats :




}
