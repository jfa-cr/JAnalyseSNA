package org.JAnalyseSNA.Variable;
/**
 * Cette classe gère l'analyse d'une variable dans l'ordre de stockage dans le fichier Snapshot :
 * + Nom de la variable	voir classe NomVariable
 * + Type de variable (Chaine, Entier, Réel) voir classe TypeVariable
 * + Valeur de la vairable voir la classe abstraite Valeur et les classes selon le type ValeurChaine, ValeurEntier ou ValeurReel
 */

import org.JAnalyseSNA.ZonesMemoire;

public class Variable {

	private TypeVariable typeVariable = null;
	private Boolean gestionNomVariable = true;
	private Boolean gestionTypeVariable = false;
	private Boolean gestionValeur = false;
	
	public Valeur valeur = null;
	
	private ZonesMemoire zm = null;
	private Integer idZoneMemoire = 0;
	
	private NomVariable nomVariable = null;
	
	/**
	 * 
	 * @param zm Classe contenant le(s) contenu(s) des fichiers snapshot
	 * @param idZm identifiant du snapshot avec lequel on veut travailler
	 */
	public Variable(ZonesMemoire zm, Integer idZm) {
		
		this.nomVariable = new NomVariable();
		this.typeVariable = new TypeVariable();
		this.gestionNomVariable = true;
		this.gestionTypeVariable = false;
		this.gestionValeur = false;
		
		this.zm = zm;
		this.idZoneMemoire = idZm;
	}
	
	/**
	 * Selon la valeur de l'octet on connait le type de variable
	 * @param octet
	 */
	public void setTypeVariable(String octet) {
		switch (octet) {
		case "01" : //Entier
			this.typeVariable.setTypeVariableEntier();
			this.valeur = new ValeurEntier(this.zm, this.idZoneMemoire);
			break;
		
		case "02" : //Chaine
			this.typeVariable.setTypeVariableChaine();
			this.valeur = new ValeurChaine(this.zm, this.idZoneMemoire);
			this.nomVariable.setChaine();
			break;
		
		case "04" : //Reel
			this.typeVariable.setTypeVariableReel();
			this.valeur = new ValeurReel(this.zm, this.idZoneMemoire);
			break;
		}
		this.gestionTypeVariable = false;
		this.gestionValeur = true;
	}
	
	/**
	 * Ajoute l'octet qui vient d'être lu afin de constituer le nom de la variable
	 * @param octet
	 */
	public void addLettreNomVariable(String octet) {
		this.nomVariable.addLettreNomVariable(octet);
		if (this.nomVariable.isFinNomVariable()) {
			this.gestionNomVariable = false;
			this.gestionTypeVariable = true;
		}
	}
	
	/**
	 * Ajoute l'octet qui vient d'être lu afin de constituer/calculer la valeur de la variable
	 * @param octet
	 */
	public void addValeur(String octet) {
		
		this.valeur.addValeur(octet);
		
		if (this.valeur.isFinAddValeur()) {
			this.gestionValeur = false;
		}

	}
	
	/**
	 * Indique à l'analyseur que l'octet qui vient d'être lu correspond au type de variable
	 * @return
	 */
	public Boolean isGestionTypeVariable() {
		return this.gestionTypeVariable;
	}
	
	/**
	 * Indique à l'analyseur que l'octet qui vient d'être lu correspond à une partie du nom de la variable
	 * @return
	 */	
	public Boolean isGestionNomVariable() {
		return this.gestionNomVariable;
	}
	
	/**
	 * Indique à l'analyseur que l'octet qui vient d'être lu correspond à une partie de la valeur de la variable
	 * @return
	 */
	public Boolean isGestionValeur() {
		return this.gestionValeur;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Nom variable : ").append(String.format("%-15s", this.nomVariable.getNomVariable()));
		sb.append(" - Type variable : ").append(this.typeVariable.getNomTypeVariable());
		
		switch (this.typeVariable.getTypeVariable()) {
		
		case 1: // Entier
			sb.append(" - Valeur=").append(this.valeur.getValeur()).append(System.lineSeparator());
			break;
		
		case 2: // Chaine 
			sb.append(" - Lg=").append(this.valeur.getLongueurChaine()).append(" - adresse de debut : &").append(this.valeur.getAdresseDebutChaine());
			sb.append(" - Valeur='").append(this.valeur.getValeur()).append("'").append(System.lineSeparator());
			break;
			
		case 4: // Reel
			sb.append(" - Valeur=").append(this.valeur.getValeur()).append(System.lineSeparator());
			break;
		}
		
		return sb.toString();
	}




}
