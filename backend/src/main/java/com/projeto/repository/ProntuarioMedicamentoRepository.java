package com.projeto.repository;

import com.projeto.model.*;
import com.projeto.util.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProntuarioMedicamentoRepository implements BaseRepository<ProntuarioMedicamento, Long> {

    @Override
    public ProntuarioMedicamento insert(ProntuarioMedicamento prontuarioMedicamento) {
        String query = "INSERT INTO prontuario_medicamento (prontuario_id, medicamento_id) VALUES (?, ?)";

        try (Connection connection = DataBaseConnection.getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, prontuarioMedicamento.getProntuario().getIdProntuario());
                preparedStatement.setLong(2, prontuarioMedicamento.getMedicamento().getIdMedicamento());
                preparedStatement.executeUpdate();
                System.out.println("Prontuário Medicamento inserido com sucesso.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir Prontuário Medicamento: " + e.getMessage());
        }

        return prontuarioMedicamento;

    }

    @Override
    public ProntuarioMedicamento findById(Long id) {
        throw new UnsupportedOperationException("A tabela Prontuário Medicamento não utiliza IDs como chave primária.");
    }

    public ProntuarioMedicamento findChaveComposta(Long prontuarioId, Long medicamento_id){
        String query = "SELECT * FROM prontuario_medicamento WHERE prontuario_id = ? AND medicamento_id = ?";
        ProntuarioMedicamento prontuarioMedicamento = null;

        try (Connection connection = DataBaseConnection.getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, prontuarioId);
                preparedStatement.setLong(2, medicamento_id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        prontuarioMedicamento = new ProntuarioMedicamento();


                        Prontuario prontuario = new Prontuario();
                        prontuario.setIdProntuario(resultSet.getLong("prontuario_id"));
                        prontuarioMedicamento.setProntuario(prontuario);

                        Medicamento medicamento = new Medicamento();
                        medicamento.setIdMedicamento(resultSet.getLong("medicamento_id"));
                        prontuarioMedicamento.setMedicamento(medicamento);

                        return prontuarioMedicamento;

                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar Prontuário Medicamento pela chave composta: " + e.getMessage());
        }
        return prontuarioMedicamento;
    }

    @Override
    public List<ProntuarioMedicamento> findAll() {
        String query = "SELECT * FROM prontuario_medicamento ";
        List<ProntuarioMedicamento> lista = new ArrayList<>();

        try (Connection connection = DataBaseConnection.getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        ProntuarioMedicamento prontuarioMedicamento = new ProntuarioMedicamento();

                        Prontuario prontuario = new Prontuario();
                        prontuario.setIdProntuario(resultSet.getLong("prontuario_id"));
                        prontuarioMedicamento.setProntuario(prontuario);

                        Medicamento medicamento = new Medicamento();
                        medicamento.setIdMedicamento(resultSet.getLong("medicamento_id"));
                        prontuarioMedicamento.setMedicamento(medicamento);

                        lista.add(prontuarioMedicamento);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar Prontuário Medicamento: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void update(ProntuarioMedicamento prontuarioMedicamento) throws Exception {
        throw new Exception("A tabela Prontuário Medicamento não possuem campos para serem atualizados");
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("A tabela Prontuário Medicamento não utiliza IDs como chave primária.");
    }

    public void deleteByChaveComposta(Long prontuarioId, Long medicamentoId) {
        String query = "DELETE FROM prontuario_medicamento WHERE prontuario_id = ? AND medicamento_id = ?";

        try (Connection connection = DataBaseConnection.getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, prontuarioId);
                preparedStatement.setLong(2, medicamentoId);
                preparedStatement.executeUpdate();

                System.out.println("Prontuário Medicamento deletado com sucesso.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar Prontuário Medicamento pela chave composta: " + e.getMessage());
        }
    }
    public List<ProntuarioMedicamento> findAllByProntuarioId(Long prontuarioId){
        String query = "SELECT * FROM prontuario_medicamento WHERE prontuario_id=?";
        List<ProntuarioMedicamento> lista = new ArrayList<>();

        try (Connection connection = DataBaseConnection.getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1,prontuarioId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        ProntuarioMedicamento prontuarioMedicamento = new ProntuarioMedicamento();

                        Prontuario prontuario = new Prontuario();
                        prontuario.setIdProntuario(resultSet.getLong("prontuario_id"));
                        prontuarioMedicamento.setProntuario(prontuario);

                        Medicamento medicamento = new Medicamento();
                        medicamento.setIdMedicamento(resultSet.getLong("medicamento_id"));
                        prontuarioMedicamento.setMedicamento(medicamento);

                        lista.add(prontuarioMedicamento);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar Prontuário Medicamento: " + e.getMessage());
        }
        return lista;
     }
}
