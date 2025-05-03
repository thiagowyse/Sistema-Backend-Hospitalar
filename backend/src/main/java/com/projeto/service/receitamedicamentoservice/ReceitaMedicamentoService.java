package com.projeto.service.receitamedicamentoservice;

import com.projeto.model.Medicamento;
import com.projeto.model.Receita;
import com.projeto.model.ReceitaMedicamento;
import com.projeto.repository.MedicamentoRepository;
import com.projeto.repository.ReceitaMedicamentoRepository;
import com.projeto.repository.ReceitaRepository;
import com.projeto.service.medicamentoservice.MedicamentoService;
import com.projeto.service.receitaservice.ReceitaService;

import java.util.ArrayList;
import java.util.List;

public class ReceitaMedicamentoService implements IReceitaMedicamentoService {

    private final ReceitaMedicamentoRepository receitaMedicamentoRepository;

    public ReceitaMedicamentoService(ReceitaMedicamentoRepository receitaMedicamentoRepository){
        this.receitaMedicamentoRepository = receitaMedicamentoRepository;
    }

    private final ReceitaRepository receitaRepository = new ReceitaRepository();
    private final ReceitaService receitaService = new ReceitaService(receitaRepository);

    private final MedicamentoRepository medicamentoRepository = new MedicamentoRepository();
    private final MedicamentoService medicamentoService = new MedicamentoService(medicamentoRepository);

    @Override
    public ReceitaMedicamento inserirReceitaMedicamento(ReceitaMedicamento receitaMedicamento) {
        return receitaMedicamentoRepository.insert(receitaMedicamento);
    }

    @Override
    public ReceitaMedicamento buscarReceitaMedicamentoPorIdReceitaAndIdMedicamento(Long idReceita, Long idMedicamento) {
        ReceitaMedicamento receitaMedicamento = receitaMedicamentoRepository.findChaveComposta(idReceita, idMedicamento);

        Receita receita = receitaService.buscarReceitaPorId(receitaMedicamento.getReceita().getIdReceita());
        receitaMedicamento.setReceita(receita);

        Medicamento medicamento = medicamentoService.buscarMedicamentoPorId(receitaMedicamento.getMedicamento().getIdMedicamento());
        receitaMedicamento.setMedicamento(medicamento);

        return receitaMedicamento;
    }

    @Override
    public List<ReceitaMedicamento> listarTodasReceitasMedicamentos() {
        List<ReceitaMedicamento> receitaMedicamentos = receitaMedicamentoRepository.findAll();
        List<ReceitaMedicamento> resposta = new ArrayList<>();

        for(ReceitaMedicamento receitaMedicamento : receitaMedicamentos){
            Receita receita = receitaService.buscarReceitaPorId(receitaMedicamento.getReceita().getIdReceita());
            receitaMedicamento.setReceita(receita);
            Medicamento medicamento = medicamentoService.buscarMedicamentoPorId(receitaMedicamento.getMedicamento().getIdMedicamento());
            receitaMedicamento.setMedicamento(medicamento);
            resposta.add(receitaMedicamento);
        }
        return resposta;
    }

    @Override
    public void atualizarReceitaMedicamento(ReceitaMedicamento receitaMedicamento) {
        receitaMedicamentoRepository.update(receitaMedicamento);
    }

    @Override
    public void removerReceitaMedicamento(Long idReceita, Long idMedicamento) {
        receitaMedicamentoRepository.deleteByChaveComposta(idReceita, idMedicamento);
    }

}
