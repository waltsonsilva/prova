package br.ifpe.pg.provacolegiada.cadastro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifpe.pg.provacolegiada.provacolegiada.Questao;

@Service
public class QuestaoService {

	@Autowired
    private QuestaoRepository repositorio;

    public List<Questao> listarTodas() {
        return repositorio.findAll();
    }

    public Questao buscarPorId(Integer id) {
        return null;
    }

    public Questao salvar(Questao questao) {
        return repositorio.saveAndFlush(questao);
    }

    public Questao removerPorId(Integer id) {
        Questao questao = buscarPorId(id);
        repositorio.deleteById(id);
        return questao;
    }


}

