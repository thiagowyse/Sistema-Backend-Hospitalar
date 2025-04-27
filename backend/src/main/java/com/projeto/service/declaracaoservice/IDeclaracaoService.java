package com.projeto.service.declaracaoservice;

import com.projeto.model.Declaracao;

import java.util.List;

public interface IDeclaracaoService {


    Declaracao inserirDeclaracao(Declaracao declaracao);

    Declaracao buscarDeclaracaoPorId(Long id);

    List<Declaracao> listarTodosDeclaracoes();

    void atualizarDeclaracao(Declaracao declaracao);

    void removerDeclaracao(Long id);
}
