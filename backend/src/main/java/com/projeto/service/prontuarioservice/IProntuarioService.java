package com.projeto.service.prontuarioservice;

import com.projeto.model.Perfil;
import com.projeto.model.Prontuario;

import java.util.List;

public interface IProntuarioService {

    Prontuario inserirProntuario(Prontuario prontuario);

    Prontuario buscarProntuarioPorId(Long id);

    List<Prontuario> listarTodosProntuarios();

    void atualizarProntuario(Prontuario prontuario);

    void removerProntuario(Long id);
}
