package br.ifpe.pg.provacolegiada.cadastro;

public enum Modalidade {

	EAD("EAD"), 
	PRESENCIAL("Presencial");

	String text;

	Modalidade(String t) {
		this.text = t;
	}

	public String getText() {
		return this.text;
	}
}
