package org.JAnalyseSNA;

import org.JAnalyseSNA.Variable.Tableau;
import org.JAnalyseSNA.Variable.Variable;

public class Analyse {

	private static final String ADRESSE_DEBUT_VARIABLE_POID_FAIBLE = "AE68";
	private static final String ADRESSE_DEBUT_VARIABLE_POID_FORT = "AE69";
	private static final String ADRESSE_FIN_VARIABLE_POID_FAIBLE = "AE6A";
	private static final String ADRESSE_FIN_VARIABLE_POID_FORT = "AE6B";
	
	private static final String ADRESSE_DEBUT_TABLEAU_POID_FAIBLE = "AE6A";
	private static final String ADRESSE_DEBUT_TABLEAU_POID_FORT = "AE6B";
	private static final String ADRESSE_FIN_TABLEAU_POID_FAIBLE = "AE6C";
	private static final String ADRESSE_FIN_TABLEAU_POID_FORT = "AE6D";
	
	private ZonesMemoire zm = null;
	private Integer idZoneMemoire = 0;
	
	public Analyse(Integer idZm, ZonesMemoire zm) {
		
		this.zm = zm;
		this.idZoneMemoire = idZm;
		
	}
	
	public void afficheVariable() {

		String debut = null;
		String fin = null;
		Boolean premierNomVaraibleAtteint = false;

		debut = this.zm.getValeur(this.idZoneMemoire, ADRESSE_DEBUT_VARIABLE_POID_FORT) 
				+ this.zm.getValeur(this.idZoneMemoire, ADRESSE_DEBUT_VARIABLE_POID_FAIBLE);
		
		
		int tmp = Outils.HexToDecInteger(this.zm.getValeur(this.idZoneMemoire, ADRESSE_FIN_VARIABLE_POID_FAIBLE));
		// Gestion d'une valeur que sur un demi-octet
		tmp--;
		if (tmp < 9) {
			fin = this.zm.getValeur(this.idZoneMemoire, ADRESSE_FIN_VARIABLE_POID_FORT) + "0" + Integer.toHexString(tmp);
		}
		else {
			fin = this.zm.getValeur(this.idZoneMemoire, ADRESSE_FIN_VARIABLE_POID_FORT) + Integer.toHexString(tmp);
		}
		Outils.afficheTexte("Variable(s) : ");
		Outils.afficheTexte("Zone d'adresse = &" + debut + " - &"+ fin);

		int idxDebut = Outils.HexToDecInteger(debut);
		int idxFin = Outils.HexToDecInteger(fin);
		String octetActuel = null;
		Variable var = new Variable(this.zm, this.idZoneMemoire);

		for (int idx= idxDebut ; idx<= idxFin ; idx++) {
			try {
				octetActuel =  this.zm.getValeur(this.idZoneMemoire, Integer.toHexString(idx));
                if (!premierNomVaraibleAtteint && "0".equals(octetActuel)) {
					continue;
				}
				if (octetActuel.length() == 1) {
					octetActuel = "0" + octetActuel;
				}
				//Outils.afficheTexte(idx + " - &" + Integer.toHexString(idx) + " - " + octetActuel);

				if (var.isGestionValeur()) {
					var.addValeur(octetActuel);

					if (!var.isGestionValeur()) {
						System.out.print(var.toString());
						var = new Variable(this.zm, this.idZoneMemoire);
					}
				}
				else if (var.isGestionTypeVariable()) {
					premierNomVaraibleAtteint = true;
					var.setTypeVariable(octetActuel);
				}
				else if (var.isGestionNomVariable()) {
					var.addLettreNomVariable(octetActuel);
				}
			}
			catch (Exception ex) {
				Outils.afficheErreur("afficheVariable - idx=" + Outils.getAdresse(idx) + 
						" - idxDebut=" + Outils.getAdresse(idxDebut) + 
						" - idxFin=" + Outils.getAdresse(idxFin));
				throw ex;
			}
		}
		Outils.afficheTexte(var.toString());
	}
	
	public void afficheTableau() {

		String debut = null;
		String fin = null;

		debut = this.zm.getValeur(this.idZoneMemoire, ADRESSE_DEBUT_TABLEAU_POID_FORT) 
				+ this.zm.getValeur(this.idZoneMemoire, ADRESSE_DEBUT_TABLEAU_POID_FAIBLE);
		int tmp = Outils.HexToDecInteger(this.zm.getValeur(this.idZoneMemoire, ADRESSE_FIN_TABLEAU_POID_FAIBLE));
		tmp--;
		if (tmp < 9) {
			fin = this.zm.getValeur(this.idZoneMemoire, ADRESSE_FIN_TABLEAU_POID_FORT) + "0" + Integer.toHexString(tmp);
		}
		else {
			fin = this.zm.getValeur(this.idZoneMemoire, ADRESSE_FIN_TABLEAU_POID_FORT) + Integer.toHexString(tmp);
		}
		Outils.afficheLigneVide();
		Outils.afficheTexte("Tableau(x) : ");
		Outils.afficheTexte("Zone d'adresse = &" + debut + " - &"+ fin);

		int idxDebut = Outils.HexToDecInteger(debut);
		int idxFin = Outils.HexToDecInteger(fin);
		String octetActuel = null;
		Tableau tab = new Tableau();

		for (int idx= idxDebut ; idx<= idxFin ; idx++) {
			try {
				octetActuel =  this.zm.getValeur(this.idZoneMemoire, Integer.toHexString(idx));
                if ("0".equals(octetActuel)) {
					continue;
				}
				if (octetActuel.length() == 1) {
					octetActuel = "0" + octetActuel;
				}
				//Outils.afficheTexte(idx + " - &" + Integer.toHexString(idx) + " - " + octetActuel);

//				if (tab.isGestionValeur()) {
//					tab.addValeur(octetActuel);
//
//					if (!tab.isGestionValeur()) {
//						//System.out.print(tab.toString());
//						tab = new Tableau();
//					}
//				}
//				else if (tab.isGestionTypeTableau()) {
//					tab.setTypeTableau(octetActuel);
//				}
//				else if (tab.isGestionNomTableu()) {
//					tab.addLettreNomTableau(octetActuel);
//				}
			}
			catch (Exception ex) {
				Outils.afficheErreur("afficheTableau - idx=" + Outils.getAdresse(idx) + 
						" - idxDebut=" + Outils.getAdresse(idxDebut) + 
						" - idxFin=" + Outils.getAdresse(idxFin));
				throw ex;
			}
		}
		Outils.afficheTexte(tab.toString());
	}
}
