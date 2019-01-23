package br.ifpe.pg.provacolegiada.provacolegiada;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.ifpe.pg.provacolegiada.cadastro.Professor;
import br.ifpe.pg.provacolegiada.cadastro.Topico;

@Entity
public class Questao {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Enumerated(EnumType.STRING)
	private TipoQuestao tipoQuestao;
	private String enunciado;
	@ManyToOne
	private Topico topico;
	@Enumerated(EnumType.STRING)
	private Complexidade complexidade;
	@ManyToOne
	private Professor autor;
	
	public Questao() {
		super();
	}
	public Questao(Integer id, TipoQuestao tipoQuestao, String enunciado, Topico topico,
			Complexidade complexidade, Professor autor) {
		super();
		this.id = id;
		this.tipoQuestao = tipoQuestao;
		this.enunciado = enunciado;
		this.topico = topico;
		this.complexidade = complexidade;
		this.setAutor(autor);
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public TipoQuestao getTipoQuestao() {
		return tipoQuestao;
	}
	public void setTipoQuestao(TipoQuestao tipoQuestao) {
		this.tipoQuestao = tipoQuestao;
	}
	public String getEnunciado() {
		return enunciado;
	}
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
	public Topico getTopico() {
		return topico;
	}
	public void setTopico(Topico topico) {
		this.topico = topico;
	}
	public Complexidade getNivelComplexidade() {
		return complexidade;
	}
	public void setNivelComplexidade(Complexidade complexidade) {
		this.complexidade = complexidade;
	}
	public Professor getAutor() {
		return autor;
	}
	public void setAutor(Professor autor) {
		this.autor = autor;
	}
	
	
}
