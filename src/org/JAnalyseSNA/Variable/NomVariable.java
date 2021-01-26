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
	 * Le dernier caract�re du nom a le 8�me bit � 1
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
	 * Indique � l'appelant que l'octet correspondant au dernier caract�re du nom a �t� d�tect�
	 * @return
	 */
	public Boolean isFinNomVariable() {
		return this.finNomVariable;
	}
	
	public String getNomVariable() {
		return this.nomVariable.toString();
	}
	
	// Si on d�tecte que c'est une chaine de caract�re on ajoute le symbole $ � la fin du nom
	public void setChaine() {
		this.nomVariable.append("$");
	}
}
