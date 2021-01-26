package org.JAnalyseSNA;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ZonesMemoire {

	private Map<Integer, ArrayList<String>> zonesMemoire = new HashMap<Integer, ArrayList<String>>();
	
	private static final Integer TAILLE_HEADER = 256;  // 256d = 100h (taille du header d'un fichier SNA)
	

	public ZonesMemoire(Integer idZoneMemoire, String nomFichier) throws IOException {
		
		if (!new File(nomFichier).canRead()) {
			throw new IllegalArgumentException("Le fichier >" + nomFichier + "< ne peut etre lu !");
		}
		
		this.memorisation(idZoneMemoire, nomFichier);
	}
	
	/*
	 * Charge en mémoire dans la classe ZoneMemoire qui est un ArrayList le contenu du fichier donné
	 * en paramètre
	 */
	private void memorisation(Integer idZoneMemoire, String nomFichier) throws IOException {

		DataInputStream in = null;
		int nombre;

		try {
			
			in = new DataInputStream(
					new BufferedInputStream(
							new FileInputStream(nomFichier)));

			while (true) {
				nombre = in.readUnsignedByte();
				//Outils.afficheTexte(nombre + ", " + Integer.toHexString(nombre));
				this.addOctet(idZoneMemoire, Integer.toHexString(nombre));
			}
		} catch (EOFException eof) {
			// fin normale de la lecture
			Outils.afficheLigneVide();
		} catch (IOException ioe) {
			throw ioe;
		}
		finally {

			if (in != null) {
				in.close();
			}
		}
	}	
	
	/**
	 * Ajoute un octet dans la zone mémoire idZoneMemoire
	 * @param idZoneMemoire
	 * @param octet
	 */
	private void addOctet(Integer idZoneMemoire, String octet) {

		if (!this.zonesMemoire.containsKey(idZoneMemoire)) {
			ArrayList<String> tmp = new ArrayList<String>();
			tmp.add(octet);
			this.zonesMemoire.put(idZoneMemoire, tmp);
		}
		else {
			this.zonesMemoire.get(idZoneMemoire).add(octet);
		}
		
	}
	
	/**
	 * Retourne le contenu de l'adresse selon l'idZoneMemoire
	 * Comme chaque fichier snapshot est précédé d'un header de 256 octets (100h) il faut ajouter cette 
	 * valeur TAILLE_HEADER pour obtenir la valeur voulue 
	 * @param idZoneMemoire
	 * @param adresseHexa
	 * @return
	 */
	public String getValeur(Integer idZoneMemoire, String adresseHexa) {
		String result = null;
		
		if (!this.zonesMemoire.containsKey(idZoneMemoire)) {
			return null;
		}
		
		int adresseDec = Outils.HexToDecInteger(adresseHexa);
		
		adresseDec = adresseDec + TAILLE_HEADER;
		
		result = this.zonesMemoire.get(idZoneMemoire).get(adresseDec);
		
		//Outils.afficheTexte("getValeur - adresseHexa:" + adresseHexa + " - adresseDec:" + adresseDec + " ==> " + result);
		return result;
	}
	
}
