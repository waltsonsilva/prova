package br.ifpe.pg.provacolegiada.cadastro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
	public boolean existsByNome(String nome);


}
