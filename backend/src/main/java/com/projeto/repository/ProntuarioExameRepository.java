package com.projeto.repository;

import com.projeto.model.*;
import com.projeto.util.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProntuarioExameRepository implements BaseRepository<ProntuarioExame, Long> {

    @Override
    public ProntuarioExame insert(ProntuarioExame prontuarioExame) {
        String query = "INSERT INTO prontuario_exame (prontuario_id, exame_id) VALUES (?, ?)";

        try (Connection connection = DataBaseConnection.getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, prontuarioExame.getProntuario().getIdProntuario());
                preparedStatement.setLong(2, prontuarioExame.getExeme().getIdExame());
                preparedStatement.executeUpdate();
                System.out.println("Prontuário Exame inserido com sucesso.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir Prontuário Exame: " + e.getMessage());
        }

        return prontuarioExame;

    }

    @Override
    public ProntuarioExame findById(Long id) {
        throw new UnsupportedOperationException("A tabela Prontuário exame não utiliza IDs como chave primária.");
    }

    public ProntuarioExame findChaveComposta(Long prontuarioId, Long exame_id){
        String query = "SELECT * FROM receita_medicamento WHERE prontuario_id = ? AND exame_id = ?";
        ProntuarioExame prontuarioExame = null;

        try (Connection connection = DataBaseConnection.getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, prontuarioId);
                preparedStatement.setLong(2, exame_id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        prontuarioExame = new ProntuarioExame();

                        Prontuario prontuario = new Prontuario();
                        prontuario.setIdProntuario(resultSet.getLong("prontuario_id"));
                        prontuarioExame.setProntuario(prontuario);

                        Exame exame = new Exame();
                        exame.setIdExame(resultSet.getLong("exame_id"));
                        prontuarioExame.setExeme(exame);

                        return prontuarioExame;

                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar Prontuário Exame pela chave composta: " + e.getMessage());
        }
        return prontuarioExame;
    }

    @Override
    public List<ProntuarioExame> findAll() {
        String query = "SELECT * FROM prontuario_exame ";
        List<ProntuarioExame> lista = new ArrayList<>();

        try (Connection connection = DataBaseConnection.getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        ProntuarioExame prontuarioExame = new ProntuarioExame();

                        Prontuario prontuario = new Prontuario();
                        prontuario.setIdProntuario(resultSet.getLong("prontuario_id"));
                        prontuarioExame.setProntuario(prontuario);

                        Exame exame = new Exame();
                        exame.setIdExame(resultSet.getLong("exame_id"));
                        prontuarioExame.setExeme(exame);

                        lista.add(prontuarioExame);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar Prontuário Exame: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void update(ProntuarioExame prontuarioExame) throws Exception {
        throw new Exception("A tabela Prontuário Exame não possuem campos para serem atualizados");
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("A tabela Prontuário Exame não utiliza IDs como chave primária.");
    }

    public void deleteByChaveComposta(Long prontuarioId, Long exameId) {
        String query = "DELETE FROM prontuario_exame WHERE prontuario_id = ? AND exame_id = ?";

        try (Connection connection = DataBaseConnection.getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, prontuarioId);
                preparedStatement.setLong(2, exameId);
                preparedStatement.executeUpdate();

                System.out.println("Prontuário Exame deletado com sucesso.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar Prontuário Exame pela chave composta: " + e.getMessage());
        }
    }
}
