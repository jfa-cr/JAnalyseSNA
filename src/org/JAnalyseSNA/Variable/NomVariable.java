package org.JAnalyseSNA.Variable;

import org.JAnalyseSNA.Outils;

public class NomVariable {

	private StringBuilder nomVariable = null;
	
	private Boolean finNomVariable = false;
	
	public NomVariable() {
		this.nomVariable = new StringBuilder();
	}
	
	/**
	 * Ajoute un octet pour constituer le nom de la variable
	 * Le dernier caractère du nom a le 8ème bit à 1
	 * @param octet
	 */
	public void addLettreNomVariable(String octet) {
		if (Outils.isBit8AUn(octet)) {
			octet = Outils.setZeroBit8(octet);
			this.finNomVariable = true;
		}
		this.nomVariable.append(Outils.HexToChar(octet));
	}
	
	/**
	 * Indique à l'appelant que l'octet correspondant au dernier caractère du nom a été détecté
	 * @return
	 */
	public Boolean isFinNomVariable() {
		return this.finNomVariable;
	}
	
	public String getNomVariable() {
		return this.nomVariable.toString();
	}
	
	// Si on détecte que c'est une chaine de caractère on ajoute le symbole $ à la fin du nom
	public void setChaine() {
		this.nomVariable.append("$");
	}
}
