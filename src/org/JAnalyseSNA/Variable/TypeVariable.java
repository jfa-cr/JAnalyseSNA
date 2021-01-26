package org.JAnalyseSNA.Variable;

public class TypeVariable {

	public static final Integer TYPE_VARIABLE_ENTIER = 1;
	public static final Integer TYPE_VARIABLE_CHAINE = 2;
	public static final Integer TYPE_VARIABLE_REEL = 4;
	private static final String ENTIER = "ENTIER";
	private static final String CHAINE = "CHAINE";
	private static final String REEL   = "REEL  ";
	
	private Integer typeVariable = 0;
	
	public TypeVariable() {
		
	}
	
	public void setTypeVariableChaine() {
		this.typeVariable = TYPE_VARIABLE_CHAINE;
	}
	
	public void setTypeVariableEntier() {
		this.typeVariable = TYPE_VARIABLE_ENTIER;
	}
	
	public void setTypeVariableReel() {
		this.typeVariable = TYPE_VARIABLE_REEL;
	}
	
	public Integer getTypeVariable() {
		return this.typeVariable;
	}
	
	public String getNomTypeVariable() {
		
		String result = null;
		
		switch (this.getTypeVariable()) {
			case 1:
				result = ENTIER;
				break;
				
			case 2:
				result = CHAINE;
				break;				
				
			case 4:
				result = REEL;
				break;				
		}
		
		
		return result;
	}
	
	public Boolean isChaine() {
		return (this.typeVariable == TYPE_VARIABLE_CHAINE);
	}
	
	public Boolean isReel() {
		return (this.typeVariable == TYPE_VARIABLE_REEL);
	}
	
	public Boolean isEntier() {
		return (this.typeVariable == TYPE_VARIABLE_ENTIER);
	}
}
