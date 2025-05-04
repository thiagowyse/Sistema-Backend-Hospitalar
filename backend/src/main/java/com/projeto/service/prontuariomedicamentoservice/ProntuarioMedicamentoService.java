package com.projeto.service.prontuariomedicamentoservice;

import com.projeto.model.Medicamento;
import com.projeto.model.Prontuario;
import com.projeto.model.ProntuarioExame;
import com.projeto.model.ProntuarioMedicamento;
import com.projeto.repository.MedicamentoRepository;
import com.projeto.repository.ProntuarioMedicamentoRepository;
import com.projeto.repository.ProntuarioRepository;
import com.projeto.service.medicamentoservice.MedicamentoService;
import com.projeto.service.prontuarioservice.ProntuarioService;

import java.util.ArrayList;
import java.util.List;

public class ProntuarioMedicamentoService implements IProntuarioMedicamentoService{

    private final ProntuarioMedicamentoRepository prontuarioMedicamentoRepository;

    public ProntuarioMedicamentoService(ProntuarioMedicamentoRepository prontuarioMedicamentoRepository){
        this.prontuarioMedicamentoRepository = prontuarioMedicamentoRepository;
    }

    private final ProntuarioRepository prontuarioRepository = new ProntuarioRepository();
    private final ProntuarioService prontuarioService = new ProntuarioService(prontuarioRepository);

    private final MedicamentoRepository medicamentoRepository = new MedicamentoRepository();
    private final MedicamentoService medicamentoService = new MedicamentoService(medicamentoRepository);

    @Override
    public ProntuarioMedicamento inserirProntuarioMedicamento(ProntuarioMedicamento prontuarioMedicamento) {
        return prontuarioMedicamentoRepository.insert(prontuarioMedicamento);
    }

    @Override
    public ProntuarioMedicamento buscarProntuarioMedicamentoPorIdProntuarioAndIdMedicamento(Long idProntuario, Long idMedicamento) {
        ProntuarioMedicamento prontuarioMedicamento = prontuarioMedicamentoRepository.findChaveComposta(idProntuario, idMedicamento);

        Prontuario prontuario = prontuarioService.buscarProntuarioPorId(prontuarioMedicamento.getProntuario().getIdProntuario());
        prontuarioMedicamento.setProntuario(prontuario);

        Medicamento medicamento = medicamentoService.buscarMedicamentoPorId(prontuarioMedicamento.getMedicamento().getIdMedicamento());
        prontuarioMedicamento.setMedicamento(medicamento);

        return prontuarioMedicamento;
    }

    @Override
    public List<ProntuarioMedicamento> listarTodosProntuariosExame() {
        List<ProntuarioMedicamento> prontuarioMedicamentos = prontuarioMedicamentoRepository.findAll();
        List<ProntuarioMedicamento> resposta = new ArrayList<>();
        for(ProntuarioMedicamento prontuarioMedicamento : prontuarioMedicamentos){
            Prontuario prontuario = prontuarioService.buscarProntuarioPorId(prontuarioMedicamento.getProntuario().getIdProntuario());
            prontuarioMedicamento.setProntuario(prontuario);

            Medicamento medicamento = medicamentoService.buscarMedicamentoPorId(prontuarioMedicamento.getMedicamento().getIdMedicamento());
            prontuarioMedicamento.setMedicamento(medicamento);

            resposta.add(prontuarioMedicamento);
        }

        return resposta;
    }

    @Override
    public void removerProntuarioMedicamento(Long idProntuario, Long idMedicamento) {
        prontuarioMedicamentoRepository.deleteByChaveComposta(idProntuario, idMedicamento);

    }
}
