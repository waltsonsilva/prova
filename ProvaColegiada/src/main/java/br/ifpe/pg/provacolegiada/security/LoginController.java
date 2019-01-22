package br.ifpe.pg.provacolegiada.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

	@Autowired
	private UsuarioService service;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/logout")
	public String logout() {
		return "login";
	}

	@GetMapping("/acesso-negado")
	public String acessoNegado() {
		return "acesso-negado";
	}

	@GetMapping("/novo-usuario")
	public String novoUsuario() {
		return "novo_usuario";
	}

	@RequestMapping("/criarUsuario")
	public String criarUsuario(@ModelAttribute Usuario usuario, Errors errors, RedirectAttributes ra) {
		if (errors.hasErrors()) {
			ra.addFlashAttribute("mensagemErro", "Não foi possível criar usuário: " + errors.getFieldErrors());
		} else {
			try {
				if (usuario.getRole() == null) {
					usuario.setRole(Role.ROLE_USER);
				}
				usuario.setAtivo(true);
				service.criarUsuario(usuario);
				ra.addFlashAttribute("mensagemSucesso", "Usuário criado com sucesso");
			} catch (Exception e) {
				ra.addFlashAttribute("mensagemErro", "Não foi possível criar usuário: " + e.getMessage());
			}
		}
		return "redirect:/novo-usuario";
	}
}
