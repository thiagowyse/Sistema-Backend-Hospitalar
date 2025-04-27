package com.projeto.service.enfermeiroservice;

import com.projeto.model.Enfermeiro;

import java.util.List;

public interface IEnfermeiroService {

    Enfermeiro inserirEnfermeiro(Enfermeiro enfermeiro);

    Enfermeiro buscarEnfermeiroPorId(Long id);

    List<Enfermeiro> listarTodosEnfermeiros();

    void atualizarEnfermeiro(Enfermeiro enfermeiro);

    void removerEnfermeiro(Long id);
}
