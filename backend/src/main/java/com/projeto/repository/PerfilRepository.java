package com.projeto.repository;

import com.projeto.model.Perfil;
import com.projeto.util.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PerfilRepository implements BaseRepository<Perfil, Long>{

    @Override
    public void insert(Perfil perfil) {
        String sql = "INSERT INTO perfil (id, nome) VALUES (?, ?)";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, perfil.getId());
            stmt.setString(2, perfil.getNome());

            stmt.executeUpdate();
            System.out.println("Perfil inserido com sucesso.");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir perfil: " + e.getMessage());
        }
    }

    @Override
    public Perfil findById(Long id) {

        String sql = "SELECT * FROM perfil WHERE id = ?";
        Perfil perfil = new Perfil();

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                perfil.setId(rs.getLong("id"));
                perfil.setNome(rs.getString("nome"));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar perfil: " + e.getMessage());
        }
        return perfil;
    }

    @Override
    public List<Perfil> findAll() {
        String sql = "SELECT * FROM perfil";
        Perfil perfil = new Perfil();
        List<Perfil> perfis = new ArrayList<>();

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                perfil.setId(rs.getLong("id"));
                perfil.setNome(rs.getString("nome"));
                perfis.add(perfil);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar perfis: " + e.getMessage());
        }
        return perfis;
    }

    @Override
    public void update(Perfil perfil) {
        String sql = "UPDATE perfil SET nome = ? WHERE id = ?";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, perfil.getNome());
            stmt.setLong(2, perfil.getId());

            stmt.executeUpdate();
            System.out.println("Perfil atualizado com sucesso.");

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar perfil: " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM perfil WHERE id = ?";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            stmt.executeUpdate();
            System.out.println("Perfil deletado com sucesso.");

        } catch (SQLException e) {
            System.err.println("Erro ao deletar perfil: " + e.getMessage());
        }
    }
}
