package com.projeto.service.recepcionistaservice;

import com.projeto.model.Recepcionista;
import com.projeto.model.Usuario;
import com.projeto.repository.RecepcionistaRepository;
import com.projeto.repository.UsuarioRepository;
import com.projeto.service.usuarioservice.UsuarioService;
import com.projeto.util.CPFValidator;

import java.util.ArrayList;
import java.util.List;

public class RecepcionistaService implements IRecepcionistaService{

    private final RecepcionistaRepository recepcionistaRepository;

    private final UsuarioRepository usuarioRepository = new UsuarioRepository();
    private final UsuarioService usuarioService = new UsuarioService(usuarioRepository);

    public RecepcionistaService(RecepcionistaRepository recepcionistaRepository) {
        this.recepcionistaRepository = recepcionistaRepository;
    }

    @Override
    public Recepcionista inserirRecepcionista(Recepcionista recepcionista) {
        return recepcionistaRepository.insert(recepcionista);
    }

    @Override
    public Recepcionista buscarRecepcionistaPorId(Long id) {
        Recepcionista recepcionista = recepcionistaRepository.findById(id);
        Usuario usuario = usuarioService.buscarUsuarioPorId(recepcionista.getIdUsuario());
        if (!CPFValidator.validarCPF(usuario.getCpf())) {
            throw new IllegalArgumentException("CPF inválido para o usuário com ID: " + usuario.getIdUsuario());
        }
        recepcionista.setNome(usuario.getNome());
        recepcionista.setLogin(usuario.getLogin());
        recepcionista.setSenha(usuario.getSenha());
        recepcionista.setPerfil(usuario.getPerfil());
        recepcionista.setEmail(usuario.getEmail());
        recepcionista.setCpf(usuario.getCpf());
        return recepcionista;
    }

    @Override
    public List<Recepcionista> listarTodosRecepcionistas() {
        List<Recepcionista> recepcionistas = recepcionistaRepository.findAll();
        List<Recepcionista> resposta = new ArrayList<>();

        for(Recepcionista recepcionista : recepcionistas){
            Usuario usuario = usuarioService.buscarUsuarioPorId(recepcionista.getIdUsuario());
            if (!CPFValidator.validarCPF(usuario.getCpf())) {
                System.err.println("CPF inválido - Usuário ID: " + usuario.getIdUsuario());
                continue;
            }
            recepcionista.setNome(usuario.getNome());
            recepcionista.setLogin(usuario.getLogin());
            recepcionista.setSenha(usuario.getSenha());
            recepcionista.setPerfil(usuario.getPerfil());
            recepcionista.setEmail(usuario.getEmail());
            recepcionista.setCpf(usuario.getCpf());
            resposta.add(recepcionista);
        }
        return resposta;
    }

    @Override
    public void atualizarRecepcionista(Recepcionista recepcionista) {
        recepcionistaRepository.update(recepcionista);
    }

    @Override
    public void removerRecepcionista(Long id) {
        recepcionistaRepository.delete(id);
    }

    public String encontrarAssinaturaPorId(Long id){
        return recepcionistaRepository.findAssinaturaById(id);
    }
}
