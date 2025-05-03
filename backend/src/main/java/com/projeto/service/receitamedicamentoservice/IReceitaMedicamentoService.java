package com.projeto.service.receitamedicamentoservice;

import com.projeto.model.ReceitaMedicamento;

import java.util.List;

public interface IReceitaMedicamentoService {

    ReceitaMedicamento inserirReceitaMedicamento(ReceitaMedicamento receitaMedicamento);

    ReceitaMedicamento buscarReceitaMedicamentoPorIdReceitaAndIdMedicamento(Long idReceita, Long idMedicamento);

    List<ReceitaMedicamento> listarTodasReceitasMedicamentos();

    void atualizarReceitaMedicamento(ReceitaMedicamento receitaMedicamento);

    void removerReceitaMedicamento(Long idReceita, Long idMedicamento);
}
