package br.ifpe.pg.provacolegiada.cadastro;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Professor {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String email;

	@Enumerated(EnumType.STRING)
	private AreasPesquisa areasPesquisa;
	
	public Professor() {
		super();
	}
	public Professor(Integer id, String nome, String email, AreasPesquisa areasPesquisa) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.areasPesquisa = areasPesquisa;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public AreasPesquisa getAreasPesquisa() {
		return areasPesquisa;
	}
	public void setAreasPesquisa(AreasPesquisa areasPesquisa) {
		this.areasPesquisa = areasPesquisa;
	}
	
}
