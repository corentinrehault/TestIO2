package fr.controleur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import fr.bean.Mot;
import fr.service.Converter;


/**
 * @author Altenide-mov
 * Se charge des traitements et des appels aux services.
 */
public class Controleur {

	// map globale contient en clï¿½ les lettres et en value la liste des mots commenï¿½ant par cette lettre.
	static Map<String, ArrayList<Mot>> listeMotParLettre = new HashMap<String, ArrayList<Mot>>();

	// la ligne etudiï¿½e
	String line;
	
	// on stocke l'unicode de la premiere lettre

	static String lettreTemoinUnicode;
	static String lettreTemoinUtf8;


	public void readFile() throws MalformedURLException, IOException, InterruptedException {

		// on lit le fichier externe
		BufferedReader r = new BufferedReader(new InputStreamReader(new URL("http://www.altenide.com/lmg.html").openStream()));


		// on dï¿½clare un id pour nos mots ( ï¿½ toutes fin utiles... )
		int idMot = 0;
		



		// on parcours lebufferReader line par ligne et on traite seulement si la ligne ne contient pas de caractï¿½re "<,>"
		long first = System.currentTimeMillis();
		while ((line = r.readLine()) != null) {

			if (!line.contains("<")) {

				// on rï¿½cupï¿½re l'unicode de la lettre courante du mot rï¿½cupï¿½rï¿½
				String lettreCouranteUnicode = String.format("\\u%04x", (int)line.charAt(0));
				String lettreCouranteUtf8 = Converter.convertUnicodeToString(lettreCouranteUnicode);



				// cas particulier de la premiï¿½re lettre
				if (idMot == 0) {

					// on dï¿½finit la lettre tï¿½moin
					lettreTemoinUnicode = lettreCouranteUnicode;
					lettreTemoinUtf8 = lettreCouranteUtf8;
					// on crï¿½e une clï¿½ de map avec une liste vide
					listeMotParLettre.put(lettreCouranteUtf8, new ArrayList<Mot>());

				}

				// on incrï¿½mente l'id
				++idMot;

				// On dï¿½tecte si la premiï¿½re lettre du mot est diffï¿½rente de la premiï¿½re lettre du mot tï¿½moin.
				// si la listeDesLettres ne contient pas cette lettre on l'ajoute.


				if (!lettreTemoinUnicode.equals(lettreCouranteUnicode) && !listeMotParLettre.containsKey(lettreCouranteUtf8)) {

					// on crï¿½ï¿½ ï¿½galement une ligne avec une liste vide dans la hasmap.
					listeMotParLettre.put(lettreCouranteUtf8, new ArrayList<Mot>());

				}	
				
				
				Mot mot =  new Mot();
				mot.setFirstLetter(line.charAt(0));
				mot.setId(idMot);
				mot.setSize(line.length());
				mot.setTerme(line);
				mot.setEndByver(line.endsWith("ver"));

				// on ajoute la lettre courante sur labonne clé de la map

				listeMotParLettre.get(lettreCouranteUtf8).add(mot);

				// on réaffecte la lettre témoin

				lettreTemoinUnicode = lettreCouranteUnicode;

			}






		}

		long last = System.currentTimeMillis();
		Long time = last - first ;
		System.err.println("Time : " + time);

		System.out.println("Taille de l'alphabet récupéré : " + listeMotParLettre.size());

		int totalsize = 0;

		Iterator<Map.Entry<String,ArrayList<Mot>>> iterator = listeMotParLettre.entrySet().iterator();
		
		while (iterator.hasNext()) {
			
			Map.Entry<String,ArrayList<Mot>> entry = (Map.Entry<String,ArrayList<Mot>>) iterator.next();
			String key = (String) entry.getKey();
			ArrayList<Mot> value = (ArrayList<Mot>) entry.getValue();

		//	System.out.println(key + " = " + value);

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
