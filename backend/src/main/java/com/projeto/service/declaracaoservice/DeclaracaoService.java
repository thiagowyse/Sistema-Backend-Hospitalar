package com.projeto.service.declaracaoservice;

import com.projeto.model.Declaracao;
import com.projeto.model.Medico;
import com.projeto.model.Paciente;
import com.projeto.repository.DeclaracaoRepository;
import com.projeto.repository.MedicoRepository;
import com.projeto.repository.PacienteRepository;
import com.projeto.service.medicoservice.MedicoService;
import com.projeto.service.pacienteservice.PacienteService;

import java.util.ArrayList;
import java.util.List;

public class DeclaracaoService implements IDeclaracaoService {

    private final DeclaracaoRepository declaracaoRepository;

    public DeclaracaoService(DeclaracaoRepository declaracaoRepository) {
        this.declaracaoRepository = declaracaoRepository;
    }

    private final PacienteRepository pacienteRepository = new PacienteRepository();
    private final PacienteService pacienteService = new PacienteService(pacienteRepository);

    private final MedicoRepository medicoRepository = new MedicoRepository();
    private final MedicoService medicoService = new MedicoService(medicoRepository);


    @Override
    public Declaracao inserirDeclaracao(Declaracao declaracao) {
        return declaracaoRepository.insert(declaracao);
    }

    @Override
    public Declaracao buscarDeclaracaoPorId(Long id) {
        Declaracao declaracao = declaracaoRepository.findById(id);

        Paciente paciente = pacienteService.buscarPacientePorId(declaracao.getPaciente().getIdPaciente());
        declaracao.setPaciente(paciente);

        Medico medico = medicoService.buscarMedicoPorId(declaracao.getMedico().getIdMedico());
        declaracao.setMedico(medico);

        return declaracao;
    }

    @Override
    public List<Declaracao> listarTodosDeclaracoes() {
        List<Declaracao> declaracaos = declaracaoRepository.findAll();
        List<Declaracao> resposta = new ArrayList<>();

        for(Declaracao declaracao : declaracaos){
            Paciente paciente = pacienteService.buscarPacientePorId(declaracao.getPaciente().getIdPaciente());
            declaracao.setPaciente(paciente);

            Medico medico = medicoService.buscarMedicoPorId(declaracao.getMedico().getIdMedico());
            declaracao.setMedico(medico);

            resposta.add(declaracao);
        }
        return resposta;
    }

    @Override
    public void atualizarDeclaracao(Declaracao declaracao) {
        declaracaoRepository.update(declaracao);
    }

    @Override
    public void removerDeclaracao(Long id) {
        declaracaoRepository.delete(id);
    }
}
