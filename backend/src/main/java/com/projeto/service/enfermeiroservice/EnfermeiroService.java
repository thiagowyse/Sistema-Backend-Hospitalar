package com.projeto.service.enfermeiroservice;

import com.projeto.model.Enfermeiro;
import com.projeto.model.Usuario;
import com.projeto.repository.EnfermeiroRepository;
import com.projeto.repository.UsuarioRepository;
import com.projeto.service.usuarioservice.UsuarioService;


import java.util.ArrayList;
import java.util.List;

public class EnfermeiroService implements IEnfermeiroService{

    private final EnfermeiroRepository enfermeiroRepository;

    public EnfermeiroService(EnfermeiroRepository enfermeiroRepository) {
        this.enfermeiroRepository = enfermeiroRepository;
    }

    private final UsuarioRepository usuarioRepository = new UsuarioRepository();
    private final UsuarioService usuarioService = new UsuarioService(usuarioRepository);

    @Override
    public Enfermeiro inserirEnfermeiro(Enfermeiro enfermeiro) {

        return enfermeiroRepository.insert(enfermeiro);
    }

    @Override
    public Enfermeiro buscarEnfermeiroPorId(Long id) {
        Enfermeiro enfermeiro = enfermeiroRepository.findById(id);
        Usuario usuario = usuarioService.buscarUsuarioPorId(enfermeiro.getIdUsuario());

        enfermeiro.setNome(usuario.getNome());
        enfermeiro.setLogin(usuario.getLogin());
        enfermeiro.setSenha(usuario.getSenha());
        enfermeiro.setPerfil(usuario.getPerfil());
        enfermeiro.setEmail(usuario.getEmail());
        enfermeiro.setCpf(usuario.getCpf());
        return enfermeiro;
    }

    @Override
    public List<Enfermeiro> listarTodosEnfermeiros() {
        List<Enfermeiro> enfermeiros = enfermeiroRepository.findAll();
        List<Enfermeiro> resposta = new ArrayList<>();

        for (Enfermeiro enfermeiro : enfermeiros) {
            Usuario usuario = usuarioService.buscarUsuarioPorId(enfermeiro.getIdUsuario());

            enfermeiro.setNome(usuario.getNome());
            enfermeiro.setLogin(usuario.getLogin());
            enfermeiro.setSenha(usuario.getSenha());
            enfermeiro.setPerfil(usuario.getPerfil());
            enfermeiro.setEmail(usuario.getEmail());
            enfermeiro.setCpf(usuario.getCpf());
            resposta.add(enfermeiro);
        }

        return resposta;
    }

    @Override
    public void atualizarEnfermeiro(Enfermeiro enfermeiro) {
        enfermeiroRepository.update(enfermeiro);
    }

    @Override
    public void removerEnfermeiro(Long id) {
        enfermeiroRepository.delete(id);
    }

    public String encontrarAssinaturaPorId(Long id){
        return enfermeiroRepository.findAssinaturaById(id);
    }
}
