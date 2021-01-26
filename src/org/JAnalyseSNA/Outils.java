package org.JAnalyseSNA;

import java.util.HashMap;
import java.util.Map;

public class Outils {

	private static Map<String,String> mapAscii = new HashMap<>();
	private static Map<String,String> mapBinaire = new HashMap<>();
	
	/**
	 * Alimente la map mapBinaire avec comme clé la valeur numérique d'un demi octet 
	 * et comme valeur la valeur binaire de la clé
	 */
	private static void alimenterMapBinaire() {
		mapBinaire.put("0", "0000");
		mapBinaire.put("1", "0001");
		mapBinaire.put("2", "0010");
		mapBinaire.put("3", "0011");
		mapBinaire.put("4", "0100");
		mapBinaire.put("5", "0101");
		mapBinaire.put("6", "0110");
		mapBinaire.put("7", "0111");
		mapBinaire.put("8", "1000");
		mapBinaire.put("9", "1001");
		mapBinaire.put("A", "1010");
		mapBinaire.put("B", "1011");
		mapBinaire.put("C", "1100");
		mapBinaire.put("D", "1101");
		mapBinaire.put("E", "1110");
		mapBinaire.put("F", "1111");
	}
	
	/**
	 * Alimente la map mapAscii avec comme clé le code hexadécimal et comme valeur le caractère de ce code hexadécimal.
	 * C'est la table ASCII de l'Amstrad CPC https://en.wikipedia.org/wiki/Amstrad_CPC_character_set
	 * Il n'y a que les caractères affichables qui sont mémorisés
	 */
	private static void alimenterMapAscii() {
		mapAscii.put("11", "1"); // De 11 a 19 c'est lorsque le bit8 est à 1
		mapAscii.put("12", "2"); // pour indiquer que c'est le dernier caractere
		mapAscii.put("13", "3"); // de la variable
		mapAscii.put("14", "4");
		mapAscii.put("15", "5");
		mapAscii.put("16", "6");
		mapAscii.put("17", "7");
		mapAscii.put("18", "8");
		mapAscii.put("19", "9");
		mapAscii.put("20", " ");
		mapAscii.put("21", "!");
		mapAscii.put("22", "\"");
		mapAscii.put("23", "#");
		mapAscii.put("24", "$");
		mapAscii.put("25", "%");
		mapAscii.put("26", "&");
		mapAscii.put("27", "'");
		mapAscii.put("28", "(");
		mapAscii.put("29", ")");
		mapAscii.put("2A", "*");
		mapAscii.put("2B", "+");
		mapAscii.put("2C", ",");
		mapAscii.put("2D", "-");
		mapAscii.put("2E", ".");
		mapAscii.put("2F", "/");
		mapAscii.put("30", "0");
		mapAscii.put("31", "1");
		mapAscii.put("32", "2");
		mapAscii.put("33", "3");
		mapAscii.put("34", "4");
		mapAscii.put("35", "5");
		mapAscii.put("36", "6");
		mapAscii.put("37", "7");
		mapAscii.put("38", "8");
		mapAscii.put("39", "9");
		mapAscii.put("3A", ":");
		mapAscii.put("3B", ";");
		mapAscii.put("3C", "<");
		mapAscii.put("3D", "=");
		mapAscii.put("3E", ">");
		mapAscii.put("3F", "?");
		mapAscii.put("40", "@");
		mapAscii.put("41", "A");
		mapAscii.put("42", "B");
		mapAscii.put("43", "C");
		mapAscii.put("44", "D");
		mapAscii.put("45", "E");
		mapAscii.put("46", "F");
		mapAscii.put("47", "G");
		mapAscii.put("48", "H");
		mapAscii.put("49", "I");
		mapAscii.put("4A", "J");
		mapAscii.put("4B", "K");
		mapAscii.put("4C", "L");
		mapAscii.put("4D", "M");
		mapAscii.put("4E", "N");
		mapAscii.put("4F", "O");
		mapAscii.put("50", "P");
		mapAscii.put("51", "Q");
		mapAscii.put("52", "R");
		mapAscii.put("53", "S");
		mapAscii.put("54", "T");
		mapAscii.put("55", "U");
		mapAscii.put("56", "V");
		mapAscii.put("57", "W");
		mapAscii.put("58", "X");
		mapAscii.put("59", "Y");
		mapAscii.put("5A", "Z");
		mapAscii.put("5B", "[");
		mapAscii.put("5C", "\\");
		mapAscii.put("5D", "]");
		mapAscii.put("5E", "^");
		mapAscii.put("5F", "_");
		mapAscii.put("60", "`");
		mapAscii.put("61", "a");
		mapAscii.put("62", "b");
		mapAscii.put("63", "c");
		mapAscii.put("64", "d");
		mapAscii.put("65", "e");
		mapAscii.put("66", "f");
		mapAscii.put("67", "g");
		mapAscii.put("68", "h");
		mapAscii.put("69", "i");
		mapAscii.put("6A", "j");
		mapAscii.put("6B", "k");
		mapAscii.put("6C", "l");
		mapAscii.put("6D", "m");
		mapAscii.put("6E", "n");
		mapAscii.put("6F", "o");
		mapAscii.put("70", "p");
		mapAscii.put("71", "q");
		mapAscii.put("72", "r");
		mapAscii.put("73", "s");
		mapAscii.put("74", "t");
		mapAscii.put("75", "u");
		mapAscii.put("76", "v");
		mapAscii.put("77", "w");
		mapAscii.put("78", "x");
		mapAscii.put("79", "y");
		mapAscii.put("7A", "z");
		mapAscii.put("7B", "{");
		mapAscii.put("7C", "|");
		mapAscii.put("7D", "}");
	}
	
	
	/**
	 * Met le 8ème bit de l'octet reçu à 0, cela correspond à soustraire 128 en décimal ou 80 en hexadécimal
	 * @param octet
	 * @return
	 */
	public static String setZeroBit8(String octet) {
		
		String tmp = HexToBin(octet);
		String tmp2 = "0" + tmp.substring(1);
		
		return BinToHex(tmp2);
		
	}
	
	/**
	 * Vérifie si le 8ème bit a pour valeur 1 de l'octet transmis
	 * @param octet
	 * @return
	 */
	public static boolean isBit8AUn(String octet) {
		
		String tmp = HexToBin(octet);
		
		if (tmp != null && tmp.trim().length() == 8) {
			if ("1".equals(tmp.substring(0, 1))) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Convertir un nombre binaire sur 8 bits (un octet) en sa valeur en hexadécimal 
	 * @param binaire
	 * @return
	 */
	public static String BinToHex(String binaire) {
		
		if (binaire == null || binaire.trim().length() == 0) {
			return null;
		}
		
		if (binaire.trim().length() > 8) {
			throw new IllegalArgumentException("Le nombre binaire doit être sur 8 caractères : >" + binaire + "<");
		}		
		
		if (mapBinaire.isEmpty()) {
			alimenterMapBinaire(); 
		}	
		
		String demiBinaire = binaire.substring(0, 4);
		StringBuilder result = new StringBuilder();
		
		Boolean trouve = false;
		for (Map.Entry<String, String> entry : mapBinaire.entrySet()) {
			if (demiBinaire.equals(entry.getValue())) {
				result.append(entry.getKey());
				trouve = true;
				break;
			}
		}
		
		if (!trouve) {
			throw new IllegalArgumentException("Valeur binaire non trouvé : >" + demiBinaire + "<");
		}
		
		demiBinaire = binaire.substring(4);
		trouve = false;
		for (Map.Entry<String, String> entry : mapBinaire.entrySet()) {
			if (demiBinaire.equals(entry.getValue())) {
				result.append(entry.getKey());
				trouve = true;
				break;
			}
		}
		
		if (!trouve) {
			throw new IllegalArgumentException("Valeur binaire non trouvé : >" + demiBinaire + "<");
		}
		
		return result.toString();
		
	}
	
	/**
	 * Convertir un octet en son nombre binaire
	 * @param octet
	 * @return
	 */
	public static String HexToBin(String octet) {
		
		if (octet == null || octet.trim().length() == 0) {
			return null;
		}
		
		if (octet.trim().length() > 2) {
			throw new IllegalArgumentException("L'octet en hexadécimal doit être sur 2 caractères : >" + octet + "<");
		}		
		
		if (mapBinaire.isEmpty()) {
			alimenterMapBinaire(); 
		}
		
		// On convertit le premier demi-octet
		String demiOctet = octet.substring(0, 1);
		StringBuilder result = new StringBuilder();
		
		if (!mapBinaire.containsKey(demiOctet.trim().toUpperCase())) {
			throw new IllegalArgumentException("Demi octet non geré : " + demiOctet);
		}
		result.append(mapBinaire.get(demiOctet.trim().toUpperCase()));

		//On convertit le deuxième octet
		demiOctet = octet.substring(1, 2);
		
		if (!mapBinaire.containsKey(demiOctet.trim().toUpperCase())) {
			throw new IllegalArgumentException("Demi octet non geré : " + demiOctet);
		}
		result.append(mapBinaire.get(demiOctet.trim().toUpperCase()));
		
		return result.toString();
	}
	
	
	/**
	 * Retourne la caractère ASCII à partir de son code hexadécimal.
	 * Voir table ASCII de l'Amstrad CPC https://en.wikipedia.org/wiki/Amstrad_CPC_character_set
	 * @param octet
	 * @return
	 */
	public static String HexToChar(String octet) {
		
		if (octet == null || octet.trim().length() == 0) {
			return null;
		}
		
		if (octet.trim().length() > 2) {
			throw new IllegalArgumentException("L'octet en hexadécimal doit être sur 2 caractères : >" + octet + "<");
		}
		
		if (mapAscii.isEmpty()) {
			alimenterMapAscii(); 
		}
		
		if (!mapAscii.containsKey(octet.trim().toUpperCase())) {
			throw new IllegalArgumentException("Octet non geré : " + octet);
		}
		
		return mapAscii.get(octet.trim().toUpperCase());
	}
	
	/**
	 * Convertir une valeur hexadémiale en entier
	 * @param hex
	 * @return
	 */
	public static Integer HexToDecInteger(String hex) {
		return Integer.decode("0x"+hex);
	}
	
	/**
	 * Convertir une valeur hexadécimale en un double
	 * @param hex
	 * @return
	 */
	public static Double HexToDecDouble(String hex) {
		return new Double(Integer.decode("0x"+hex));
	}
	
	/**
	 * Retourne une valeur décimal représentant une adresse suivie de la valeur hexadécimale
	 * En général utilisé pour l'affichage
	 * @param adresseDec
	 * @return
	 */
	public static String getAdresse(Integer adresseDec) {
		return adresseDec + "(&" + Integer.toHexString(adresseDec).toUpperCase() +")";
	}
	
	public static void afficheTexte(String texte) {
		System.out.println(texte);
	}
	
	public static void afficheLigneVide() {
		System.out.println();
	}
	
	public static void afficheErreur(String erreur) {
		System.err.println(erreur);
	}
}
