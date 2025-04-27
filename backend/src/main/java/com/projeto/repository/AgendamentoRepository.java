package com.projeto.repository;

import com.projeto.model.Agendamento;
import com.projeto.model.Usuario;
import com.projeto.util.DataBaseConnection;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AgendamentoRepository implements BaseRepository<Agendamento, Long>{

    @Override
    public Agendamento insert(Agendamento agendamento) {

        String query = "INSERT INTO agendamento (paciente_id, medico_id, data_consulta, status) VALUES (?, ?, ?, ?)";

        try (Connection connection = DataBaseConnection.getConnection();
           PreparedStatement preparedStatement = connection.prepareStatement(query)) {
           preparedStatement.setLong(1, agendamento.getIdPaciente());
           preparedStatement.setLong(2,agendamento.getIdMedico());
           preparedStatement.setDate(3, agendamento.getDataConsulta());
           preparedStatement.setString(4, agendamento.getStatus());
           preparedStatement.executeUpdate();
           System.out.println("Agendamento inserido com sucesso.");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir Agendamento: " + e.getMessage());
        }
        return agendamento;
    }



    @Override
    public Agendamento findById(Long id) {
        return null;
    }

    @Override
    public List<Agendamento> findAll() {
        return List.of();
    }

    @Override
    public void update(Agendamento entity) {

    }

    @Override
    public void delete(Long aLong) {

    }
}
