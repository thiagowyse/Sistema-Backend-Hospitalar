package com.projeto.service.prontuariomedicamentoservice;

import com.projeto.model.ProntuarioMedicamento;

import java.util.List;

public interface IProntuarioMedicamentoService {

    ProntuarioMedicamento inserirProntuarioMedicamento(ProntuarioMedicamento prontuarioMedicamento);

    ProntuarioMedicamento buscarProntuarioMedicamentoPorIdProntuarioAndIdMedicamento(Long idProntuario, Long idMedicamento);

    List<ProntuarioMedicamento> listarTodosProntuariosExame();

    void removerProntuarioMedicamento(Long idProntuario, Long idMedicamento);
}
