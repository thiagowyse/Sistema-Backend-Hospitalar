package com.projeto.service.declaracaoservice;

import com.projeto.model.Declaracao;
import com.projeto.repository.DeclaracaoRepository;

import java.util.List;

public class DeclaracaoService implements IDeclaracaoService {

    private final DeclaracaoRepository declaracaoRepository;

    public DeclaracaoService(DeclaracaoRepository declaracaoRepository) {
        this.declaracaoRepository = declaracaoRepository;
    }

    @Override
    public Declaracao inserirDeclaracao(Declaracao declaracao) {
        return declaracaoRepository.insert(declaracao);
    }

    @Override
    public Declaracao buscarDeclaracaoPorId(Long id) {
        return declaracaoRepository.findById(id);
    }

    @Override
    public List<Declaracao> listarTodosDeclaracoes() {
        return declaracaoRepository.findAll();
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
