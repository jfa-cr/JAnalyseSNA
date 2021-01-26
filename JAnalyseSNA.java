package org.JAnalyseSNA;

import java.io.IOException;


public class JAnalyseSNA {

	private static final Integer ID_ZONE_MEMOIRE = 0; // Pour l'instant il n'y a qu'une zone mémoire

	private static final String VERSION = "0.1 alpha";
	private static final String DATE_VERSION = "2021-01-21";
	private static final String NOM_UTILITAIRE = "JAnalyseSNA";

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		String nf = null;

		try {

			Outils.afficheTexte(NOM_UTILITAIRE + " - Version : " + VERSION + " - Date version : " + DATE_VERSION);
			Outils.afficheLigneVide();

			nf = 
			"src/Test/SimpleVariables.sna";
			//"src/Test/Complexe.sna";
			//"src/Test/Tableaux.sna";

			if (nf == null) {
				if (args.length == 1) {
					nf = args[0];
				}
				else {
					throw new IllegalArgumentException("Cet utilitaire doit avoir un paramètre, le nom du fichier snapshot");
				}
			}

			new JAnalyseSNA(nf);

		} catch (Throwable t) {
			t.printStackTrace();
		}
		finally {
			Outils.afficheLigneVide();
			Outils.afficheTexte("Fin de " + NOM_UTILITAIRE);
		}
	}

	JAnalyseSNA(String nf) throws IOException {

		ZonesMemoire zm = new ZonesMemoire(ID_ZONE_MEMOIRE, nf);

		Analyse analyse = new Analyse(ID_ZONE_MEMOIRE, zm);
		analyse.afficheVariable();
		//analyse.afficheTableau();

	}

}
