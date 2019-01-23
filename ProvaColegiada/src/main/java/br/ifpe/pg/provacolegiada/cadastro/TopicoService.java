 package br.ifpe.pg.provacolegiada.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    public void listarTodos() {
        topicoRepository.findAll();
    }

    public void buscarPorId(Integer id) {
        topicoRepository.findById(id).orElse(null);
    }

    public <S> void salvar(S entity) {
        topicoRepository.saveAndFlush(null);
    }
        public <S extends Topico> S salvar(S entity) {
            return topicoRepository.saveAndFlush(entity);
    }

    public void removerPorId(Integer id) {
        topicoRepository.deleteById(id);
    }

}
