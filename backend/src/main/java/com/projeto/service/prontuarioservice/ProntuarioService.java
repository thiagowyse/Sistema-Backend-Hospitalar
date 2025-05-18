package com.projeto.service.prontuarioservice;

import com.projeto.model.*;
import com.projeto.repository.*;
import com.projeto.service.medicoservice.MedicoService;
import com.projeto.service.pacienteservice.PacienteService;

import java.util.ArrayList;
import java.util.List;

public class ProntuarioService implements IProntuarioService{

    private final ProntuarioRepository prontuarioRepository;

    public ProntuarioService(ProntuarioRepository prontuarioRepository) {
        this.prontuarioRepository = prontuarioRepository;
    }

    private final PacienteRepository pacienteRepository = new PacienteRepository();
    private final PacienteService pacienteService = new PacienteService(pacienteRepository);

    private final ProntuarioMedicamentoRepository prontuarioMedicamentoRepository =new ProntuarioMedicamentoRepository();
    private final MedicamentoRepository medicamentoRepository=new MedicamentoRepository();

    private final ProntuarioExameRepository prontuarioExameRepository =new ProntuarioExameRepository();
    private final ExameRepository exameRepository=new ExameRepository();

    private final MedicoRepository medicoRepository = new MedicoRepository();
    private final MedicoService medicoService = new MedicoService(medicoRepository);


    @Override
    public Prontuario inserirProntuario(Prontuario prontuario) {
        return prontuarioRepository.insert(prontuario);
    }

    @Override
    public Prontuario buscarProntuarioPorId(Long id) {
        Prontuario prontuario = prontuarioRepository.findById(id);

        Paciente paciente = pacienteService.buscarPacientePorId(prontuario.getPaciente().getIdPaciente());
        prontuario.setPaciente(paciente);

        Medico medico = medicoService.buscarMedicoPorId(prontuario.getMedico().getIdMedico());
        prontuario.setMedico(medico);

        prontuario.setExames(getExames(id));
        prontuario.setMedicamentos(getMedicamentos(id));
        prontuario.setMedicamentos(getMedicamentos(id));

        return prontuario;
    }

    @Override
    public List<Prontuario> listarTodosProntuarios() {
        List<Prontuario> prontuarios = prontuarioRepository.findAll();
        List<Prontuario> resposta = new ArrayList<>();
        for(Prontuario prontuario : prontuarios){

            Paciente paciente = pacienteService.buscarPacientePorId(prontuario.getPaciente().getIdPaciente());
            prontuario.setPaciente(paciente);

            Medico medico = medicoService.buscarMedicoPorId(prontuario.getMedico().getIdMedico());
            prontuario.setMedico(medico);

            prontuario.setExames(getExames(prontuario.getIdProntuario()));
            prontuario.setMedicamentos(getMedicamentos(prontuario.getIdProntuario()));

            resposta.add(prontuario);
        }

        return resposta;
    }

    @Override
    public void atualizarProntuario(Prontuario prontuario) {
        prontuarioRepository.update(prontuario);
    }

    @Override
    public void removerProntuario(Long id) {
        prontuarioRepository.delete(id);
    }

    private List<Medicamento> getMedicamentos(Long prontuarioId){
        List<ProntuarioMedicamento> prontuarioMedicamentos = prontuarioMedicamentoRepository.findAllByProntuarioId(prontuarioId);
        List <Medicamento> medicamentos= new ArrayList<Medicamento>();
        for(ProntuarioMedicamento prontuarioMedicamento: prontuarioMedicamentos){
            Medicamento medicamento= medicamentoRepository.findById(prontuarioMedicamento.getMedicamento().getIdMedicamento());
            medicamentos.add(medicamento);
        }
        return medicamentos;
    }
    private List<Exame> getExames(Long prontuarioId){
        List<ProntuarioExame> prontuarioExames = prontuarioExameRepository.findAllByProntuarioId(prontuarioId);
        List <Exame> exames= new ArrayList<Exame>();
        for(ProntuarioExame prontuarioExame: prontuarioExames){
            Exame exame= exameRepository.findById(prontuarioExame.getExeme().getIdExame());
            exames.add(exame);
        }
        return exames;
    }
}
