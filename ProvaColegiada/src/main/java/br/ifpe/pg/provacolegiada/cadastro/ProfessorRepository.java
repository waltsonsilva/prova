package br.ifpe.pg.provacolegiada.cadastro;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

	@Query("select p from Professor p where p.nome like %:nome% order by p.nome")
	public List<Professor> findByNome(String nome);
}
