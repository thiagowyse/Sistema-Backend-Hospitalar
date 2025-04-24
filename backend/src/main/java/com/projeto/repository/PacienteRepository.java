package com.projeto.repository;

import com.projeto.model.Paciente;
import com.projeto.util.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PacienteRepository {

    public void adicionarPaciente(Paciente paciente) {
        String sql = "INSERT INTO pacientes (nome, endereco) VALUES (?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paciente.getNome());
            stmt.executeUpdate();

            System.out.println("Paciente " + paciente.getNome() + " adicionado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Paciente buscarPacientePorId(int id) {
        String sql = "SELECT * FROM pacientes WHERE id = ?";
        Paciente paciente = null;

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                paciente = new Paciente(
                        rs.getString("nome"),
                        rs.getInt("id")

                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return paciente;
    }
}
