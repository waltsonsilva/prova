package br.ifpe.pg.provacolegiada.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query("select u from Usuario u where u.email = :email")
	public Usuario findByEmail(String email);

	@Query("select u from Usuario u where u.nome like %:nome% or u.email like %:email%")
	public Usuario findByNomeEmailAprox(String nome, String email); 
}