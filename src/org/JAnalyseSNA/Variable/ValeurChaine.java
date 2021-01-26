package org.JAnalyseSNA.Variable;

import org.JAnalyseSNA.Outils;
import org.JAnalyseSNA.ZonesMemoire;

public class ValeurChaine extends Valeur {

	public ValeurChaine(ZonesMemoire zm, Integer idZm) {
		super(zm, idZm);
	}

	private String valeurChaine = null;
	private String adresseDebutChaine = null;
	private Integer longueurChaine = 0;
	
	
	public void addValeur(String octet) {
		
		this.nbOctetTraite++;
		
		switch (this.nbOctetTraite) {
		case 1: //Longueur de la chaîne de caractère
			this.longueurChaine = Outils.HexToDecInteger(octet);
			//Outils.afficheTexte("addValeurChaine 1 - lg="+this.longueurChaine);
			break;
			
		case 2: //Poid faible de l'adresse
			this.adresseDebutChaine = octet;
			break;
			
		case 3: //Poid fort de l'adresse
			this.adresseDebutChaine = octet + this.adresseDebutChaine;
			Integer min = Outils.HexToDecInteger(this.adresseDebutChaine);
			Integer max = min + this.longueurChaine;
			StringBuilder sb = new StringBuilder();
			for (int idx=min ; idx <max ; idx++) {
				try {
					sb.append(Outils.HexToChar(this.zm.getValeur(idZoneMemoire, Integer.toHexString(idx))));
				}
				catch (Exception ex) {
					Outils.afficheErreur("addValeurChaine - 3 - min="+Outils.getAdresse(min)+
							" - max="+Outils.getAdresse(max)+
							" - idx="+Outils.getAdresse(idx));
					throw ex;
				}
			}
			this.valeurChaine = sb.toString();
			break;	
			
		case 4: //fill
			break;
			
		case 5: //fill
			this.finAddValeur = true;
			break;
		}
	}
	
	public String getValeur() {
		return this.valeurChaine;
	}
	
	public Integer getLongueurChaine() {
		return this.longueurChaine;
	}
	
	public String getAdresseDebutChaine() {
		return this.adresseDebutChaine;
	}
}
