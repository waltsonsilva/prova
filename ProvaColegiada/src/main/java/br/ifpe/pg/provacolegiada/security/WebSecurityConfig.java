package br.ifpe.pg.provacolegiada.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AutenticacaoService autenticacaoService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		String recursos[] = { "/login", "/logout", "/novo-usuario", "/criarUsuario" };
		// Para acessar qualquer pagina dessa aplicação, o usuário precisa estar
		// autenticado
		http.authorizeRequests().antMatchers(recursos).permitAll().anyRequest().authenticated();

		// Indicando a existencia de uma página de login própria e todos terão acesso a
		// esta página
		http.formLogin().loginPage("/login").permitAll();

		// Liberar o logout para todos
		http.logout().permitAll();
		
		http.exceptionHandling().accessDeniedPage("/acesso-negado");

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoService).passwordEncoder(getPasswordEncoder());
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public SpringSecurityDialect springSecurityDialect() {
	    return new SpringSecurityDialect();
	}
	
//	@Bean
//	public DaoAuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//		authProvider.setUserDetailsService(autenticacaoService);
//		authProvider.setPasswordEncoder(passwordEncoder());
//		return authProvider;
//	}
}