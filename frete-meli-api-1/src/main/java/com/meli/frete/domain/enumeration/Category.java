package com.meli.frete.domain.enumeration;

public enum Category {
	FRAGIL(1,"Frágil"),
	OUTRA(2,"Outra");
	
	private int indice;
	private String description;
	
	private Category(int indice, String description) {
		this.indice = indice;
		this.description = description;
	}

	public int getIndice() {
		return indice;
	}

	public String getDescription() {
		return description;
	}
	
}
