package br.ifpe.pg.provacolegiada.cadastro;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
@RequestMapping("/disciplinas/")
public class DisciplinaController {

	@Autowired
	private DisciplinaService disciplinaService;
	@Autowired
	private CursoService cursoService;

	@RequestMapping(value="saveList", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView salvarPesquisarDisciplina(@Valid @ModelAttribute Disciplina disciplina, @RequestParam(value="action", 
		required=false) String action, Errors errors, RedirectAttributes ra) {
		
		if (action != null && action.equals("salvar")) {
			return salvar(disciplina, errors, ra);
		} else {
			return pesquisar(disciplina);
		}
	}
	
	@GetMapping("list")
	public ModelAndView pesquisar(Disciplina disciplina) {
		ModelAndView mv = new ModelAndView("cadastros/disciplinas-list");
		if (disciplina == null || disciplina.getNome() == null) {
			mv.addObject("lista", disciplinaService.listarTodas());	
		} else {
			mv.addObject("lista", disciplinaService.buscarPorNomeCurso(disciplina.getNome(), disciplina.getCurso().getId())); // .buscarPorExemplo(disciplina)); // .buscarPorNome(disciplina.getNome()));	
		}
		mv.addObject("listaCursos", cursoService.listarTodos());
		mv.addObject("listarPeriodo", Periodo.values());
		mv.addObject("disciplina", disciplina);
		return mv;
	}

	@Secured("ROLE_ADMIN")
	private ModelAndView salvar(@Valid @ModelAttribute Disciplina disciplina, Errors errors, RedirectAttributes ra) {
		if (errors.hasErrors()) {
			ra.addFlashAttribute("mensagemErro", "Não foi possível salvar disciplina: " + errors.getFieldErrors());
		} else {
			try {
				disciplinaService.salvar(disciplina);
				ra.addFlashAttribute("mensagemSucesso", "Disciplina salva com sucesso [" + disciplina.getNome() + "]");
			} catch (Exception e) {
				ra.addFlashAttribute("mensagemErro", "Não foi possível salvar disciplina: " + e.getMessage());
			}
		}
		return pesquisar(new Disciplina());
	}

	@GetMapping("edit/{id}")
	@Secured("ROLE_ADMIN")
	public ModelAndView exibirEdicao(@PathVariable("id") Integer id) {
		Disciplina disciplina = disciplinaService.buscarPorId(id);
		ModelAndView mv = new ModelAndView("cadastros/disciplinas-list");
		mv.addObject("lista", disciplinaService.listarTodas());	
		mv.addObject("listaCursos", cursoService.listarTodos());
		mv.addObject("listarPeriodo", Periodo.values());
		mv.addObject("disciplina", disciplina);
		return mv;
	}

	@GetMapping("/remover/{id}")	
	@Secured("ROLE_ADMIN")
	public String remover(@PathVariable("id") Integer id, RedirectAttributes ra) {
		disciplinaService.removerPorId(id);
		ra.addFlashAttribute("mensagemSucesso", "Disciplina removida com sucesso");
		return "redirect:/disciplinas/list";
	}

}
