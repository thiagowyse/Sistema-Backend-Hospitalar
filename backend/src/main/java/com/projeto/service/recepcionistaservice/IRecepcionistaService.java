package com.projeto.service.recepcionistaservice;

import com.projeto.model.Exame;
import com.projeto.model.Recepcionista;

import java.util.List;

public interface IRecepcionistaService {

    Recepcionista inserirRecepcionista(Recepcionista recepcionista);

    Recepcionista buscarRecepcionistaPorId(Long id);

    List<Recepcionista> listarTodosRecepcionistas();

    void atualizarRecepcionista(Recepcionista recepcionista);

    void removerRecepcionista(Long id);
}
