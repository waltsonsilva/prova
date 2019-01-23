 package br.ifpe.pg.provacolegiada.cadastro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TurmaService {

	@Autowired
	private TurmaRepository repositorio;
	
	@Autowired
	private CursoRepository cursoRepo;

	public List<Turma> listarTodas() {
		if(cursoRepo.findAll().isEmpty()) {
			try {
				throw new Exception("Não existi curso cadastrado");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return repositorio.findAll(Sort.by("curso.nome"));
	}

	public List<Turma> buscarPorCurso(Curso curso) {
		return repositorio.findByCurso(curso);
	}
	
	public Turma buscarPorId(Integer id) {
		return repositorio.findById(id).orElse(null);
	}

	public <S extends Turma> S salvar(S entity) throws Exception {
		if(entity.getCurso() == null) {
			throw new Exception("Não é possível criar turmas sem cursos no sistema.");
		}
		Turma turma = repositorio.findFirstByCursoNomeIgnoreCaseAndAnoAndEntrada(entity.getCurso().getNome(), entity.getAno(),entity.getEntrada());
		if(turma != null) {
			throw new Exception("Não poderão existir duas turmas com mesmo curso, período, turno e semestre letivo");

		}
		return repositorio.saveAndFlush(entity);
	}

	public void removerPorId(Integer id) {
		repositorio.deleteById(id);
	}
	
}
