	package br.ifpe.pg.provacolegiada.cadastro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DisciplinaService {

	@Autowired
	private DisciplinaRepository repositorio;

	public List<Disciplina> listarTodas() {
		return repositorio.findAll(Sort.by("curso.nome"));
	}

	public List<Disciplina> buscarPorNomeCurso(String nome, Integer idCurso) {
		return repositorio.findByNomeCurso(nome, idCurso);
	}
	
	public List<Disciplina> buscarPorExemplo(Disciplina disciplina){
		Example<Disciplina> exemplo = Example.of(disciplina);
		return repositorio.findAll(exemplo);
	}

	public Disciplina buscarPorId(Integer id) {
		return repositorio.findById(id).orElse(null);
	}

	public <S extends Disciplina> S salvar(S entity) {
		return repositorio.saveAndFlush(entity);
	}

	public void removerPorId(Integer id) {
		repositorio.deleteById(id);
	}
	
}
