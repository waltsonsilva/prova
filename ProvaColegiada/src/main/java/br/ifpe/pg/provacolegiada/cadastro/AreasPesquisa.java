package br.ifpe.pg.provacolegiada.cadastro;

public enum AreasPesquisa {
	
	HUMANA("Humanas"),
	EXATAS("Exatas"),
	SAÚDE("Saúde");

	    String text;

	    AreasPesquisa(String t) {
	        this.text = t;
	    }

	    public String getText() {
	        return this.text;
	    }

}
