package org.JAnalyseSNA.Variable;

import org.JAnalyseSNA.ZonesMemoire;

abstract class Valeur {

	protected Integer nbOctetTraite = 0;
	protected Boolean finAddValeur = false;
	
	protected ZonesMemoire zm = null;
	protected Integer idZoneMemoire = 0;
	
	public Valeur(ZonesMemoire zm, Integer idZm) {
		
		this.zm = zm;
		this.idZoneMemoire = idZm;
		this.nbOctetTraite = 0;
	}
	
	/**
	 * Indique à l'appelant que le calcul de la valeur de la variable est terminé
	 * @return
	 */
	public Boolean isFinAddValeur() {
		return this.finAddValeur;
	}
	
	/**
	 * Cette méthode permet d'ajouter un octet qui vient d'être lû pour obtenir la valeur en mémoire
	 * Pour chaque type (Chaîne, Entier, Réel).
	 * Voir ce petit article de MicroStrad 1986 :
	 * https://www.cpc-power.com/index.php?page=detail&onglet=notices&num=14104
	 * Ensuite pour le calcul complexe d'un réel le livre "MA la bible du CPC6128" 
	 * Paragraphe "3.3.3 Le pointeur de variable '@'" pages 300 à 302
	 * https://www.cpc-power.com/cpcarchives/download/Livres/MA%20-%20le%20langage%20machine%20%5BFR%5D%20%281987%29.zip
	 * 
	 * Selon le type de variable la position de l'octet que l'on lit a une signification différente.
	 * Voir la variable nbOctetTraite 
	 * @param octet
	 */
	public abstract void addValeur(String octet);
	
	public abstract String getValeur(); 
	
	/**
	 * Cette méthode n'est utilisée que pour une variable de type Chaine
	 * @return
	 */
	public abstract Integer getLongueurChaine();
	
	/**
	 * Cette méthode n'est utilisée que pour une variable de type Chaine
	 * @return
	 */	
	public abstract String getAdresseDebutChaine();
}
