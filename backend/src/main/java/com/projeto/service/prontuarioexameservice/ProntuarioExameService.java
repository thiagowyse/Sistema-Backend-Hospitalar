package com.projeto.service.prontuarioexameservice;

import com.projeto.model.Exame;
import com.projeto.model.Prontuario;
import com.projeto.model.ProntuarioExame;
import com.projeto.repository.ExameRepository;
import com.projeto.repository.ProntuarioExameRepository;
import com.projeto.repository.ProntuarioRepository;
import com.projeto.service.exameservice.ExameService;
import com.projeto.service.prontuarioservice.ProntuarioService;

import java.util.ArrayList;
import java.util.List;

public class ProntuarioExameService implements IProntuarioExameService {

    private final ProntuarioExameRepository prontuarioExameRepository;

    public ProntuarioExameService(ProntuarioExameRepository prontuarioExameRepository){
        this.prontuarioExameRepository = prontuarioExameRepository;
    }

    private final ProntuarioRepository prontuarioRepository = new ProntuarioRepository();
    private final ProntuarioService prontuarioService = new ProntuarioService(prontuarioRepository);

    private final ExameRepository exameRepository = new ExameRepository();
    private final ExameService exameService = new ExameService(exameRepository);


    @Override
    public ProntuarioExame inserirProntuarioExame(ProntuarioExame prontuarioExame) {
        return prontuarioExameRepository.insert(prontuarioExame);
    }

    @Override
    public ProntuarioExame buscarProntuarioExamePorIdProntuarioAndIdExame(Long idProntuario, Long idExame) {
        ProntuarioExame prontuarioExame = prontuarioExameRepository.findChaveComposta(idProntuario, idExame);

        Prontuario prontuario = prontuarioService.buscarProntuarioPorId(prontuarioExame.getProntuario().getIdProntuario());
        prontuarioExame.setProntuario(prontuario);

        Exame exame = exameService.buscarExamePorId(prontuarioExame.getExeme().getIdExame());
        prontuarioExame.setExeme(exame);

        return prontuarioExame;
    }

    @Override
    public List<ProntuarioExame> listarTodosProntuariosExame() {
        List<ProntuarioExame> prontuarioExames = prontuarioExameRepository.findAll();
        List<ProntuarioExame> resposta = new ArrayList<>();

        for(ProntuarioExame prontuarioExame : prontuarioExames){
            Prontuario prontuario = prontuarioService.buscarProntuarioPorId(prontuarioExame.getProntuario().getIdProntuario());
            prontuarioExame.setProntuario(prontuario);

            Exame exame = exameService.buscarExamePorId(prontuarioExame.getExeme().getIdExame());
            prontuarioExame.setExeme(exame);

            resposta.add(prontuarioExame);
        }
        return resposta;
    }

    @Override
    public void removerProntuarioExame(Long idProntuario, Long idExame) {
        prontuarioExameRepository.deleteByChaveComposta(idProntuario, idExame);
    }
}
