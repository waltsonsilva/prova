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
import br.ifpe.pg.provacolegiada.provacolegiada.Complexidade;
import br.ifpe.pg.provacolegiada.provacolegiada.Questao;

@Controller
@RequestMapping("/questoes/")
public class QuestaoController {

    @Autowired
    private QuestaoService questaoService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private TopicoService topicoService;

    @RequestMapping(value = "saveList", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView salvarPesquisarQuestao(@Valid @ModelAttribute Questao questao, @RequestParam(value = "action",
            required = false) String action, Errors errors, RedirectAttributes ra) {

        if (action != null && action.equals("salvar")) {
            return salvar(questao, errors, ra);
        } else {
            return pesquisar(questao, ra);
        }
    }

    @GetMapping("list")
    public ModelAndView pesquisar(Questao questao, RedirectAttributes ra) {
        ModelAndView mv = new ModelAndView("cadastros/questoes-list");

        mv.addObject("listaTodas", questaoService.listarTodas());
        mv.addObject("questao", questao);
        mv.addObject("listaTipoQuestao", TipoQuestao.values());
        mv.addObject("listaComplexidade", Complexidade.values());
        mv.addObject("listarTodosProfessores", professorService.listarTodas());
        mv.addObject("listaTodosTopicos", topicoService.listarTodos());

        mv.addObject("mensagemErro", ra.getFlashAttributes().get("mensagemErro"));
        mv.addObject("mensagemSucesso", ra.getFlashAttributes().get("mensagemSucesso"));

        return mv;
    }

    private ModelAndView salvar(@Valid @ModelAttribute Questao questao, Errors errors, RedirectAttributes ra) {
        if (errors.hasErrors()) {
            ra.addFlashAttribute("mensagemErro", "Não foi possível salvar questao: " + errors.getFieldErrors());
        } else {
            try {
                questaoService.salvar(questao);
                ra.addFlashAttribute("mensagemSucesso", "Questão salva com sucesso!");
            } catch (Exception e) {
                ra.addFlashAttribute("mensagemErro", "Não foi possível salvar questao: " + e.getMessage());
            }
        }
        return pesquisar(new Questao(), ra);
    }

    @GetMapping("edit/{id}")
    public ModelAndView exibirEdicao(@PathVariable("id") Integer id) {
        Questao questao = questaoService.buscarPorId(id);
        ModelAndView mv = new ModelAndView("cadastros/questoes-list");
        mv.addObject("listaTodas", questaoService.listarTodas());
        mv.addObject("questao", questao);
        return mv;
    }

    @GetMapping("/remover/{id}")
    public String remover(@PathVariable("id") Integer id, RedirectAttributes ra) {
        questaoService.removerPorId(id);
        ra.addFlashAttribute("mensagemSucesso", "Questão removida com sucesso");
        return "redirect:/questoes/list";
    }

}