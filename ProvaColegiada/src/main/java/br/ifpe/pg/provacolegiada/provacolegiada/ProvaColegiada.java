package br.ifpe.pg.provacolegiada.provacolegiada;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import br.ifpe.pg.provacolegiada.cadastro.Topico;
import br.ifpe.pg.provacolegiada.cadastro.TurmaDisciplina;

@Entity
public class ProvaColegiada {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	private TurmaDisciplina turmaDisciplina;
	private LocalDate dataAplicacao;
	@ManyToMany
	private List<Topico> topicos;
	@ManyToMany
	private List<Questao> listaQuestoes;
	
	public ProvaColegiada() {
		super();
	}

	public ProvaColegiada(Integer id, TurmaDisciplina turmaDisciplina, LocalDate dataAplicacao, List<Topico> topicos,
			List<Questao> listaQuestoes) {
		super();
		this.id = id;
		this.turmaDisciplina = turmaDisciplina;
		this.dataAplicacao = dataAplicacao;
		this.topicos = topicos;
		this.listaQuestoes = listaQuestoes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TurmaDisciplina getTurmaDisciplina() {
		return turmaDisciplina;
	}

	public void setTurmaDisciplina(TurmaDisciplina turmaDisciplina) {
		this.turmaDisciplina = turmaDisciplina;
	}

	public LocalDate getDataAplicacao() {
		return dataAplicacao;
	}

	public void setDataAplicacao(LocalDate dataAplicacao) {
		this.dataAplicacao = dataAplicacao;
	}

	public List<Topico> getTopicos() {
		return topicos;
	}

	public void setTopicos(List<Topico> topicos) {
		this.topicos = topicos;
	}

	public List<Questao> getListaQuestoes() {
		return listaQuestoes;
	}

	public void setListaQuestoes(List<Questao> listaQuestoes) {
		this.listaQuestoes = listaQuestoes;
	}
	
	
	
}
