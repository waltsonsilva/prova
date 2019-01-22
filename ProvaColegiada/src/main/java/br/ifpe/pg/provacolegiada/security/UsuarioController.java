package br.ifpe.pg.provacolegiada.security;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	@RequestMapping(value="saveList", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView salvarPesquisarUsuarios(@Valid @ModelAttribute Usuario usuario, @RequestParam(value="action", 
		required=false) String action, Errors errors, RedirectAttributes ra) {
		
		if (action != null && action.equals("salvar")) {
			return salvar(usuario, errors, ra);
		} else {
			return pesquisar(usuario);
		}
	}

	private ModelAndView salvar(@Valid @ModelAttribute Usuario usuario, Errors errors, RedirectAttributes ra) {
		if (errors.hasErrors()) {
			ra.addFlashAttribute("mensagemErro", "Não foi possível salvar usuário: " + errors.getFieldErrors());
		} else {
			try {
				service.save(usuario);
				ra.addFlashAttribute("mensagemSucesso", "Usuário salvo com sucesso");
			} catch (Exception e) {
				ra.addFlashAttribute("mensagemErro", "Não foi possível salvar usuário: " + e.getMessage());
			}
		}
		return pesquisar(new Usuario());
	}

	@GetMapping("list")
	public ModelAndView pesquisar(Usuario usuario) {
		ModelAndView mv = new ModelAndView("cadastros/usuarios-list");
		if (usuario == null || usuario.getId() == null) {
			mv.addObject("lista", service.listarTodos());	
		} else {
			mv.addObject("lista", service.findUsuarioByNomeEmailAprox(usuario.getNome(), usuario.getEmail())); 	
		}
		mv.addObject("usuario", usuario);
		return mv;
	}

	@GetMapping("edit/{id}")
	public ModelAndView exibirEdicao(@PathVariable("id") Integer id) {
		Usuario usuario = service.findById(id);
		ModelAndView mv = new ModelAndView("cadastros/usuarios-list");
		mv.addObject("lista", service.listarTodos());	
		mv.addObject("usuario", usuario);
		return mv;
	}

	@GetMapping("/desativar/{id}")
	public String desativar(@PathVariable("id") Integer id, RedirectAttributes ra) {
		Usuario usuario = service.findById(id);
		usuario.setAtivo(false);
		service.alterar(usuario);
		ra.addFlashAttribute("mensagemSucesso", "Usuário desativado com sucesso");
		return "redirect:/usuarios/list";
	}

	@GetMapping("/ativar/{id}")
	public String ativar(@PathVariable("id") Integer id, RedirectAttributes ra) {
		Usuario usuario = service.findById(id);
		usuario.setAtivo(true);
		service.alterar(usuario);
		ra.addFlashAttribute("mensagemSucesso", "Usuário ativado com sucesso");
		return "redirect:/usuarios/list";
	}

}