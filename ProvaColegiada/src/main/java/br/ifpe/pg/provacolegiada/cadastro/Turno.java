package br.ifpe.pg.provacolegiada.cadastro;

public enum Turno {

	MANHA("Manhã"), TARDE("Tarde"), NOITE("Noite");

	String text;

	Turno(String t) {
		this.text = t;
	}

	public String getText() {
		return this.text;
	}

}
