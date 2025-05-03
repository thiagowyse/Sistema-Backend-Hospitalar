package com.projeto.repository;

import com.projeto.model.Medicamento;
import com.projeto.model.Receita;
import com.projeto.model.ReceitaMedicamento;
import com.projeto.util.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceitaMedicamentoRepository implements BaseRepository<ReceitaMedicamento,Long> {

    @Override
    public ReceitaMedicamento insert(ReceitaMedicamento receitaMedicamento) {
        String query = "INSERT INTO receita_medicamento (receita_id, medicamento_id, quantidade) VALUES (?, ?, ?)";

        try (Connection connection = DataBaseConnection.getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, receitaMedicamento.getReceita().getIdReceita());
                preparedStatement.setLong(2, receitaMedicamento.getMedicamento().getIdMedicamento());
                preparedStatement.setLong(3, receitaMedicamento.getQuantidade());
                preparedStatement.executeUpdate();
                System.out.println("Receita Medicamento inserido com sucesso.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir Receita Medicamento: " + e.getMessage());
        }

        return receitaMedicamento;

    }

    @Override
    public ReceitaMedicamento findById(Long id) {
        throw new UnsupportedOperationException("A tabela Receita Medicamento não utiliza IDs como chave primária.");
    }

    public ReceitaMedicamento findChaveComposta(Long receitaId, Long medicamentoId){
        String query = "SELECT * FROM receita_medicamento WHERE receita_id = ? AND medicamento_id = ?";
        ReceitaMedicamento receitaMedicamento = null;

        try (Connection connection = DataBaseConnection.getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, receitaId);
                preparedStatement.setLong(2, medicamentoId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        receitaMedicamento = new ReceitaMedicamento();

                        Receita receita = new Receita();
                        receita.setIdReceita(resultSet.getLong("receita_id"));
                        receitaMedicamento.setReceita(receita);

                        Medicamento medicamento = new Medicamento();
                        medicamento.setIdMedicamento(resultSet.getLong("medicamento_id"));
                        receitaMedicamento.setMedicamento(medicamento);

                        receitaMedicamento.setQuantidade(resultSet.getLong("quantidade"));

                        return receitaMedicamento;

                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar Receita Medicamento pela chave composta: " + e.getMessage());
        }
        return receitaMedicamento;
    }

    @Override
    public List<ReceitaMedicamento> findAll() {
        String query = "SELECT * FROM receita_medicamento";
        List<ReceitaMedicamento> lista = new ArrayList<>();

        try (Connection connection = DataBaseConnection.getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        ReceitaMedicamento receitaMedicamento = new ReceitaMedicamento();

                        Receita receita = new Receita();
                        receita.setIdReceita(resultSet.getLong("receita_id"));
                        receitaMedicamento.setReceita(receita);

                        Medicamento medicamento = new Medicamento();
                        medicamento.setIdMedicamento(resultSet.getLong("medicamento_id"));
                        receitaMedicamento.setMedicamento(medicamento);

                        receitaMedicamento.setQuantidade(resultSet.getLong("quantidade"));

                        lista.add(receitaMedicamento);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar Receita Medicamento: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void update(ReceitaMedicamento receitaMedicamento) {
        String query = "UPDATE receita_medicamento SET quantidade = ? WHERE receita_id = ? AND medicamento_id = ?";

        try (Connection connection = DataBaseConnection.getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, receitaMedicamento.getQuantidade());
                preparedStatement.setLong(2, receitaMedicamento.getReceita().getIdReceita());
                preparedStatement.setLong(3, receitaMedicamento.getMedicamento().getIdMedicamento());
                preparedStatement.executeUpdate();

                System.out.println("Receita Medicamento atualizado com sucesso.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar Receita Medicamento: " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("A tabela Receita Medicamento não utiliza IDs como chave primária.");
    }

    public void deleteByChaveComposta(Long receitaId, Long medicamentoId) {
        String query = "DELETE FROM receita_medicamento WHERE receita_id = ? AND medicamento_id = ?";

        try (Connection connection = DataBaseConnection.getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, receitaId);
                preparedStatement.setLong(2, medicamentoId);
                preparedStatement.executeUpdate();

                System.out.println("Receita Medicamento deletado com sucesso.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar Receita Medicamento pela chave composta: " + e.getMessage());
        }
    }
}
