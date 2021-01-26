package org.JAnalyseSNA.Variable;

import org.JAnalyseSNA.Outils;
import org.JAnalyseSNA.ZonesMemoire;

public class ValeurReel extends Valeur {

	public ValeurReel(ZonesMemoire zm, Integer idZm) {
		super(zm, idZm);
	}

	private String mantisse1 = null;
	private String mantisse2 = null;
	private String mantisse3 = null;
	private String mantisse4 = null;
	private String exposant = null;
	private Double valeurReel = (double) 0;
	
	@Override
	public void addValeur(String octet) {
		
		this.nbOctetTraite++;
		
		switch (this.nbOctetTraite) {
		case 1: // Mantisse1
			this.mantisse1 = octet;
			break;
			
		case 2: // Mantisse2
			this.mantisse2 = octet;
			break;
			
		case 3: // Mantisse3
			this.mantisse3 = octet;
			break;
			
		case 4: // Mantisse4
			this.mantisse4 = octet;
			break; 
			
		case 5: // Mantisse5
			this.exposant = octet;
			this.valeurReel = this.calculValeurReel();
			break;
			
		case 6: // fill
			break;
			
		case 7: // fill
			this.finAddValeur = true;
			break;
		}
		
	}

	/**
	 * Calcul de la valeur d'un réel à partir des 5 octets qui viennent d'être lu
	 * 
	 * Voir MA la bible du CPC6128 pages 300 et 301
	 * Dans l'équation mX correspond aux variables de classe mantisseX
	 * (1-2*SGN(m4 AND 128))*2^(ex-129)*(1+((m4 AND 127)+(m3+(m2+m1/256)/256)/256)/128)
	 * 
	 * @return
	 */
	private Double calculValeurReel() {
		
		Double result = (double) 0;
		
		Double m1 = Outils.HexToDecDouble(this.mantisse1);
		Double m2 = Outils.HexToDecDouble(this.mantisse2);
		Double m3 = Outils.HexToDecDouble(this.mantisse3);
		Double ex =  Outils.HexToDecDouble(this.exposant);
		
		//Calcul du signe de mantisse4
		String tmp = Outils.HexToBin(this.mantisse4);
		int signeMantisse4 = 0;
		if ("1".equals(tmp.substring(0, 1))) {
			signeMantisse4 = 1;
		}
		
		Double mantisse4SansBit8 = Outils.HexToDecDouble(Outils.setZeroBit8(this.mantisse4));
		
		//Outils.afficheTexte("m1=" + m1 + " - m2=" + m2 + " - m3=" + m3 + " - m4=" + Outils.HexToDecDouble(this.mantisse4));
		//Outils.afficheTexte("ex=" + ex);
		//Outils.afficheTexte("signeMantisse4=" + signeMantisse4 + " - mantisse4SansBit8=" + mantisse4SansBit8);
		
		// Voir MA la bible du CPC6128 pages 300 et 301
		// Dans l'équation mX correspond aux variables de classe mantisseX
		// (1-2*SGN(m4 AND 128))*2^(ex-129)*(1+((m4 AND 127)+(m3+(m2+m1/256)/256)/256)/128)
		// p1                    p2         p3
		// La partie (m4 AND 127) corespond à la variable mantisse4SansBit8
		
		// Calcul partie p1
		result = (double) (1 - 2 * signeMantisse4); 
		//Outils.afficheTexte("result=" + result);
		
		// Calcul partie p2
		Integer tmpExp = (int) Math.pow(2, (ex -129));
		//Outils.afficheTexte("tmpExp=" + tmpExp);
		
		// Multiplication p1 * p2
		result = result * tmpExp; 
		//Outils.afficheTexte("result=" + result);
		
		// Multiplication de p3 à p1*p2
		result = result * ( 1 + ((mantisse4SansBit8) + (m3 + (m2 + m1 / 256) / 256 ) / 256) / 128);
		//Outils.afficheTexte("result=" + result);
		
		return result;
	}	
	@Override
	public String getValeur() {
		return this.valeurReel.toString();
	}

	@Override
	public Integer getLongueurChaine() {
		// inutile pour ce type de variable
		return null;
	}

	@Override
	public String getAdresseDebutChaine() {
		// inutile pour ce type de variable
		return null;
	}

}
