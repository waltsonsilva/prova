package br.ifpe.pg.provacolegiada.cadastro;


import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Disciplina {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	@ManyToOne
	private Curso curso;
	private int cargaHoraria;
	private String ementa;
	
	@OneToMany
	private Set<Disciplina> preRequisitos;

	@Enumerated(EnumType.STRING)
	private Periodo periodo;
	
	public Disciplina() {
		super();
	}
	public Disciplina(Integer id, String nome, Curso curso, int cargaHoraria, String ementa, Set<Disciplina> preRequisitos, Periodo periodo) {
		super();
		this.id = id;
		this.nome = nome;
		this.curso = curso;
		this.cargaHoraria = cargaHoraria;
		this.ementa = ementa;
		this.preRequisitos = preRequisitos;
		this.periodo = periodo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCargaHoraria() {
		return cargaHoraria;
	}
	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	public String getEmenta() {
		return ementa;
	}
	public void setEmenta(String ementa) {
		this.ementa = ementa;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public Set<Disciplina> getPreRequisitos() {
		return preRequisitos;
	}
	public void setPreRequisitos(Set<Disciplina> preRequisitos) {
		this.preRequisitos = preRequisitos;
	}
	public Periodo getPeriodo() {
		return periodo;
	}
	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	
}
