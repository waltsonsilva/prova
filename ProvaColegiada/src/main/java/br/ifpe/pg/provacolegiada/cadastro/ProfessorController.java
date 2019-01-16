package br.ifpe.pg.provacolegiada.cadastro;

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
@RequestMapping("/professores/")
public class ProfessorController {

	@Autowired
	private ProfessorService professorService;

	@RequestMapping(value = "saveList", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView salvarPesquisarProfessor(@Valid @ModelAttribute Professor professor,
			@RequestParam(value = "action", required = false) String action, Errors errors, RedirectAttributes ra) {

		if (action != null && action.equals("salvar")) {
			return salvar(professor, errors, ra);
		} else {
			return pesquisar(professor);
		}
	}

	@GetMapping("list")
	public ModelAndView pesquisar(Professor professor) {
		ModelAndView mv = new ModelAndView("cadastros/professores-list");
		if (professor == null || professor.getNome() == null) {
			mv.addObject("lista", professorService.listarTodas());
		} else {
			mv.addObject("lista", professorService.buscarPorNome(professor.getNome()));
		}
		mv.addObject("professor", professor);
		return mv;
	}

	private ModelAndView salvar(@Valid @ModelAttribute Professor professor, Errors errors, RedirectAttributes ra) {
		if (errors.hasErrors()) {
			ra.addFlashAttribute("mensagemErro", "Não foi possível salvar professor: " + errors.getFieldErrors());
		} else {
			try {
				professorService.salvar(professor);
				ra.addFlashAttribute("mensagemSucesso", "Professor salva com sucesso [" + professor.getNome() + "]");
			} catch (Exception e) {
				ra.addFlashAttribute("mensagemErro", "Não foi possível salvar professor: " + e.getMessage());
			}
		}
		return pesquisar(new Professor());
	}

	@GetMapping("edit/{id}")
	public ModelAndView exibirEdicao(@PathVariable("id") Integer id) {
		Professor professor = professorService.buscarPorId(id);
		ModelAndView mv = new ModelAndView("cadastros/professores-list");
		mv.addObject("lista", professorService.listarTodas());
		mv.addObject("professor", professor);
		return mv;
	}

	@GetMapping("/remover/{id}")
	public String remover(@PathVariable("id") Integer id, RedirectAttributes ra) {
		professorService.removerPorId(id);
		ra.addFlashAttribute("mensagemSucesso", "Professor removida com sucesso");
		return "redirect:/professores/list";
	}

}
