package br.ifpe.pg.provacolegiada.cadastro;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {

	@Query("select d from Disciplina d where d.nome like %:nome% and d.curso.id = :idCurso order by d.nome")
	public List<Disciplina> findByNomeCurso(String nome, Integer idCurso);
}
