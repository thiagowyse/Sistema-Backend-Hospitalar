package com.projeto.service.medicoservice;

import com.projeto.model.Especialidade;
import com.projeto.model.Medico;
import com.projeto.model.MedicoEspecialidade;
import com.projeto.model.Usuario;
import com.projeto.repository.EspecialidadeRepository;
import com.projeto.repository.MedicoEspecialidadeRepository;
import com.projeto.repository.MedicoRepository;
import com.projeto.repository.UsuarioRepository;
import com.projeto.service.usuarioservice.UsuarioService;

import java.util.ArrayList;
import java.util.List;

public class MedicoService implements IMedicoService{

    private final MedicoRepository medicoRepository;
    private final MedicoEspecialidadeRepository medicoEspecialidadeRepository= new MedicoEspecialidadeRepository();
    private final EspecialidadeRepository especialidadeRepository=new EspecialidadeRepository();

    public MedicoService(MedicoRepository medicoRepository ) {
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
        Usuario usuario = usuarioService.buscarUsuarioPorId(medico.getIdUsuario());
        medico.setNome(usuario.getNome());
        medico.setLogin(usuario.getLogin());
        medico.setSenha(usuario.getSenha());
        medico.setPerfil(usuario.getPerfil());
        medico.setEmail(usuario.getEmail());
        medico.setCpf(usuario.getCpf());
        medico.setEspecialidades(getEspecialidades(id));
        return medico;
    }

    @Override
    public List<Medico> listarTodosMedicos() {
        List<Medico> medicos = medicoRepository.findAll();
        List<Medico> resposta = new ArrayList<>();
        for(Medico medico : medicos){
            Usuario usuario = usuarioService.buscarUsuarioPorId(medico.getIdUsuario());

            medico.setNome(usuario.getNome());
            medico.setLogin(usuario.getLogin());
            medico.setSenha(usuario.getSenha());
            medico.setPerfil(usuario.getPerfil());
            medico.setEmail(usuario.getEmail());
            medico.setCpf(usuario.getCpf());
            medico.setEspecialidades(getEspecialidades(medico.getIdMedico()));
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

    private List<Especialidade> getEspecialidades(Long medicoId){
        List<MedicoEspecialidade> medicoEspecialidades = medicoEspecialidadeRepository.findAllByMedicoId(medicoId);
        List <Especialidade> especialidades= new ArrayList<Especialidade>();
        for(MedicoEspecialidade medicoEspecialidade: medicoEspecialidades){
            Especialidade especialidade= especialidadeRepository.findById(medicoEspecialidade.getEspecialidade().getIdEspecialidade());
            especialidades.add(especialidade);
        }
        return especialidades;
    }

    public String encontrarAssinaturaPorId(Long id){
        return medicoRepository.findAssinaturaById(id);
    }
}
