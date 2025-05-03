package com.projeto.repository;

import com.projeto.model.*;
import com.projeto.util.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoEspecialidadeRepository implements BaseRepository<MedicoEspecialidade, Long> {

    @Override
    public MedicoEspecialidade insert(MedicoEspecialidade medicoEspecialidade) {
        String query = "INSERT INTO prontuario_exame (medico_id, especialidade_id) VALUES (?, ?)";

        try (Connection connection = DataBaseConnection.getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, medicoEspecialidade.getMedico().getIdMedico());
                preparedStatement.setLong(2, medicoEspecialidade.getEspecialidade().getIdEspecialidade());
                preparedStatement.executeUpdate();
                System.out.println("Médico especialidade inserido com sucesso.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir Médico Especialidade: " + e.getMessage());
        }

        return medicoEspecialidade;

    }

    @Override
    public MedicoEspecialidade findById(Long id) {
        throw new UnsupportedOperationException("A tabela Médico Especialidade não utiliza IDs como chave primária.");
    }

    public MedicoEspecialidade findChaveComposta(Long medico_id, Long especialidade_id){
        String query = "SELECT * FROM medico_especialidade WHERE medico_id = ? AND especialidade_id = ?";
        MedicoEspecialidade medicoEspecialidade = null;

        try (Connection connection = DataBaseConnection.getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, medico_id);
                preparedStatement.setLong(2, especialidade_id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        medicoEspecialidade = new MedicoEspecialidade();

                        Medico medico = new Medico();
                        medico.setIdMedico(resultSet.getLong("medico_id"));
                        medicoEspecialidade.setMedico(medico);

                        Especialidade especialidade = new Especialidade();
                        especialidade.setIdEspecialidade(resultSet.getLong("especialidade_id"));
                        medicoEspecialidade.setEspecialidade(especialidade);

                        return medicoEspecialidade;

                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar Médico Especialidade pela chave composta: " + e.getMessage());
        }
        return medicoEspecialidade;
    }

    @Override
    public List<MedicoEspecialidade> findAll() {
        String query = "SELECT * FROM medico_especialidade ";
        List<MedicoEspecialidade> lista = new ArrayList<>();

        try (Connection connection = DataBaseConnection.getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        MedicoEspecialidade medicoEspecialidade = new MedicoEspecialidade();

                        Medico medico = new Medico();
                        medico.setIdMedico(resultSet.getLong("medico_id"));
                        medicoEspecialidade.setMedico(medico);

                        Especialidade especialidade = new Especialidade();
                        especialidade.setIdEspecialidade(resultSet.getLong("especialidade_id"));
                        medicoEspecialidade.setEspecialidade(especialidade);

                        lista.add(medicoEspecialidade);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar Médico Especialidade: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void update(MedicoEspecialidade medicoEspecialidade) throws Exception {
        throw new Exception("A tabela Médico Especialidade não possuem campos para serem atualizados");
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("A tabela Médico Especialidade não utiliza IDs como chave primária.");
    }

    public void deleteByChaveComposta(Long medicoId, Long especialidadeId) {
        String query = "DELETE FROM medico_especialidade WHERE medico_id = ? AND especialidade_id = ?";

        try (Connection connection = DataBaseConnection.getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, medicoId);
                preparedStatement.setLong(2, especialidadeId);
                preparedStatement.executeUpdate();

                System.out.println("Médico Especialidade deletado com sucesso.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar Médico Especialidade pela chave composta: " + e.getMessage());
        }
    }
}
