package org.JAnalyseSNA.Variable;

import org.JAnalyseSNA.Outils;
import org.JAnalyseSNA.ZonesMemoire;

public class ValeurEntier extends Valeur {

	private static final Integer MULTIPLICATEUR_POID_FORT = 256;
	
	public ValeurEntier(ZonesMemoire zm, Integer idZm) {
		super(zm, idZm);
	}

	private String valeurEntier = null;
	private Integer valeur = 0;
	
	
	@Override
	public void addValeur(String octet) {
		
		this.nbOctetTraite++;
		
		switch (this.nbOctetTraite) {
		case 1: // Poid faible de la valeur
			this.valeur = Outils.HexToDecInteger(octet);
			break;
			
		case 2: // Poid fort de la valeur
			Integer tmp = Outils.HexToDecInteger(octet) * MULTIPLICATEUR_POID_FORT;
			this.valeurEntier = Integer.toString(this.valeur + tmp);
			break;
			
		case 3: // fill
			break;
			
		case 4: // fill
			this.finAddValeur = true;
			break;			
		}
		
	}

	@Override
	public String getValeur() {
		return this.valeurEntier;
	}

	@Override
	public Integer getLongueurChaine() {
		// Inutile pour ce type
		return null;
	}

	@Override
	public String getAdresseDebutChaine() {
		// Inutile pour ce type
		return null;
	}

	
}
