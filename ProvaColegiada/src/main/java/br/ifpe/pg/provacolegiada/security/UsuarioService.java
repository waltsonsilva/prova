package br.ifpe.pg.provacolegiada.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public class UsuarioService {

	@Autowired
	private UsuarioRepository repositorio;
	@Autowired
    private PasswordEncoder passwordEncoder;

	public Usuario findUsuarioByEmail(String email) {
		return repositorio.findByEmail(email);
	}

	public Usuario save(Usuario novo) {
		Usuario usuarioAtual = findUsuarioByEmail(novo.getEmail());
		usuarioAtual.setNome(novo.getNome());
		usuarioAtual.setRole(novo.getRole());
		return repositorio.save(usuarioAtual);
	}

	public Usuario alterar(Usuario entity) {
		
		return repositorio.saveAndFlush(entity);
	}

	public Usuario findById(Integer id) {
		return repositorio.findById(id).orElse(null);
	}

	public List<Usuario> listarTodos() {
		return repositorio.findAll();
	}
	public boolean existsByEmail(String email) {
		return findUsuarioByEmail(email) != null;
	}
	
	public void criarUsuario(Usuario usuario) throws Exception {
		
	    if (existsByEmail(usuario.getEmail())) {
	        throw new EmailExistsException
	          ("Já existe usuário com este e-mail: " + usuario.getEmail());
	    }
	    if(!usuario.getSenha().equals(usuario.getSenhaRepetida())) {
	    	throw new Exception("Senhas não coincidem!");
	    }
	    usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
	 
		repositorio.save(usuario);
	}
	
	public void registrarUltimoLogin(Usuario usuario) {
		this.repositorio.saveAndFlush(usuario);
	}

	public Object findUsuarioByNomeEmailAprox(String nome, String email) {
		return repositorio.findByNomeEmailAprox(nome, email);
	}
	
	
	
}
