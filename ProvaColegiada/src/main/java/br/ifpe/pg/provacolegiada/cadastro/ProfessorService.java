package br.ifpe.pg.provacolegiada.cadastro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

	@Autowired
	private ProfessorRepository repositorio;

	public List<Professor> listarTodas() {
		return repositorio.findAll(Sort.by("nome"));
	}

	public List<Professor> buscarPorNome(String nome) {
		return repositorio.findByNome(nome);
	}
	
	public Professor buscarPorId(Integer id) {
		return repositorio.findById(id).orElse(null);
	}

	public <S extends Professor> S salvar(S entity) {
		return repositorio.saveAndFlush(entity);
	}

	public void removerPorId(Integer id) {
		repositorio.deleteById(id);
	}
	
}
