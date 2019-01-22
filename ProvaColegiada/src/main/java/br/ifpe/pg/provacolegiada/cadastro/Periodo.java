package br.ifpe.pg.provacolegiada.cadastro;

public enum Periodo {

	PRIMEIRO("1° Período"),
    SEGUNDO("2° Período"),
    TERCEIRO("3° Período"),
    QUARTO("4° Período"),
    QUINTO("5° Período"),
    SEXTO("6° Período"),
    SETIMO("7° Período"),
    OITAVO("8° Período"),
    NONO("9° Período"),
    DECIMO("10° Período");

	  String text;

	    Periodo(String t) {
	        this.text = t;
	    }

	    public String getText() {
	        return this.text;
	    }
	
}
