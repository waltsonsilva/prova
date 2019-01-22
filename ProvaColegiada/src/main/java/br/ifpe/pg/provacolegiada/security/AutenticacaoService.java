package br.ifpe.pg.provacolegiada.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
@Service
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = this.usuarioService.findUsuarioByEmail(username);
		if (usuario == null) {
			throw new UsernameNotFoundException("");
		}
		Collection<SimpleGrantedAuthority> listGrants = new ArrayList<>();
		if (usuario.getRole() != null) {
			listGrants.add(new SimpleGrantedAuthority(usuario.getRole().toString()));
		}
		System.out.println(usuario + "Grants: " + listGrants);
		return new User(usuario.getEmail(), usuario.getSenha(), usuario.isAtivo(), true, true, true, listGrants);
	}

}
