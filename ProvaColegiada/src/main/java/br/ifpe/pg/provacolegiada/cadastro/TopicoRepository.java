package br.ifpe.pg.provacolegiada.cadastro;

import org.springframework.data.jpa.repository.JpaRepository;
import br.ifpe.pg.provacolegiada.provacolegiada.Questao;

public interface TopicoRepository extends JpaRepository<Questao, Integer> {
}