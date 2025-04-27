package com.projeto.service.atestadoservice;

import com.projeto.model.Atestado;

import java.util.List;

public interface IAtestadoService {

    Atestado inserirAtestado(Atestado atestado);

    Atestado buscarAtestadoPorId(Long id);

    List<Atestado> listarTodosAtestados();

    void atualizarAtestado(Atestado atestado);

    void removerAtestado(Long id);
}
