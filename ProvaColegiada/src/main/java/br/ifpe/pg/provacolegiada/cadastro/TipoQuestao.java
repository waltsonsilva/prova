package br.ifpe.pg.provacolegiada.cadastro;

public enum TipoQuestao {
	

		ABERTA("Aberta"),
		MULTIPLA_ESCOLHA("Multipla escolha"),
		VERDADEIRO_FALSO("Verdadeiro e falso");

		String text;

		TipoQuestao(String t) {
			this.text = t;
			
		}

		public String getText() {
			return this.text;
		}
}
