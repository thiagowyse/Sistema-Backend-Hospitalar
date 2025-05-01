package com.projeto.service.medicoservice;

import com.projeto.model.Medico;
import com.projeto.model.Usuario;
import com.projeto.repository.MedicoRepository;
import com.projeto.repository.UsuarioRepository;
import com.projeto.service.usuarioservice.UsuarioService;

import java.util.ArrayList;
import java.util.List;

public class MedicoService implements IMedicoService{

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    private final UsuarioRepository usuarioRepository = new UsuarioRepository();
    private final UsuarioService usuarioService = new UsuarioService(usuarioRepository);

    @Override
    public Medico inserirMedico(Medico medico) {
        return medicoRepository.insert(medico);
    }

    @Override
    public Medico buscarMedicoPorId(Long id) {
        Medico medico = medicoRepository.findById(id);
        Usuario usuario = usuarioService.buscarUsuarioPorId(medico.getUsuario().getIdUsuario());
        medico.setUsuario(usuario);
        return medico;
    }

    @Override
    public List<Medico> listarTodosMedicos() {
        List<Medico> medicos = medicoRepository.findAll();
        List<Medico> resposta = new ArrayList<>();
        for(Medico medico : medicos){
            Usuario usuario = usuarioService.buscarUsuarioPorId(medico.getIdMedico());
            medico.setUsuario(usuario);
            resposta.add(medico);
        }

        return resposta;
    }

    @Override
    public void atualizarMedico(Medico medico) {
        medicoRepository.update(medico);
    }

    @Override
    public void removerMedico(Long id) {
        medicoRepository.delete(id);
    }
}
