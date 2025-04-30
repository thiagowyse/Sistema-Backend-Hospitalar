package com.projeto.repository;

import com.projeto.model.Exame;
import com.projeto.util.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ExameRepository implements BaseRepository<Exame,Long>{
    @Override
    public Exame insert(Exame exame) {
    	String sql = "INSERT INTO exame (nome,descricao) VALUES (?, ?)";

		try (Connection connection = DataBaseConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setString(1, exame.getNome());
			stmt.setString(2, exame.getDescricao());
			stmt.executeUpdate();
			System.out.println("Exame inserido com sucesso.");

			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                exame.setIdExame(generatedKeys.getLong(1)); // Supondo que o ID seja do tipo Long
	            }
	        }

		} catch (SQLException e) {
			System.err.println("Erro ao inserir exame: " + e.getMessage());
		}
		return exame;
    }

    @Override
    public Exame findById(Long id) {
    	 String sql = "SELECT * FROM exame WHERE id = ?";
	        Exame exame = new Exame();

	        try (Connection connection = DataBaseConnection.getConnection();
	             PreparedStatement stmt = connection.prepareStatement(sql)) {

	            stmt.setLong(1,id );
	            ResultSet rs = stmt.executeQuery();

	            if (rs.next()) {
	                exame.setIdExame(rs.getLong("id"));
	                exame.setNome(rs.getString("nome"));
	                exame.setDescricao(rs.getString("descricao"));


	                return exame;
	            }

	        } catch (SQLException e) {
	            System.err.println("Erro ao buscar exame: " + e.getMessage());

	        }
	        return null;
    }

    @Override
    public List<Exame> findAll() {
    	 String sql = "SELECT * FROM exame";
	        Exame exame = new Exame();
	        List<Exame> exames = new ArrayList<>();

	        try (Connection connection = DataBaseConnection.getConnection();
	             PreparedStatement stmt = connection.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {

	            while (rs.next()) {
	            	exame.setIdExame(rs.getLong("id"));
	                exame.setNome(rs.getString("nome"));
	                exame.setDescricao(rs.getString("descricao"));
	                exames.add(exame);
	            }

	        } catch (SQLException e) {
	            System.err.println("Erro ao listar exames: " + e.getMessage());
	        }
	        return exames;
    }

    @Override
    public void update(Exame exame) {
    	 StringBuilder queryBuilder = new StringBuilder("UPDATE exame SET ");
	        boolean adicionouCampo = false;

	        if (exame.getNome() != null) {
	            queryBuilder.append("nome = ?");
	            adicionouCampo = true;
	        }
	        if (exame.getDescricao() != null) {
	            if (adicionouCampo) queryBuilder.append(", ");
	            queryBuilder.append("descricao = ?");
	            adicionouCampo = true;
	        }

	        queryBuilder.append(" WHERE id = ?");

	        if (!adicionouCampo) {
	            System.out.println("Nenhum campo para atualizar.");
	            return;
	        }

	        try (Connection connection = DataBaseConnection.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString())) {

	            int index = 1;

	            if (exame.getNome() != null) {
	                preparedStatement.setString(index++, exame.getNome());
	            }
	            if (exame.getDescricao() != null) {
	                preparedStatement.setString(index++, exame.getDescricao());
	            }



	            preparedStatement.setLong(index, exame.getIdExame());
	            preparedStatement.executeUpdate();

	            System.out.println("Exame atualizado com sucesso.");

	        } catch (SQLException e) {
	            System.err.println("Erro ao atualizar exame: " + e.getMessage());
	        }
    }

    @Override
    public void delete(Long id) {
    	 String sql = "DELETE FROM exame WHERE id = ?";

	        try (Connection connection = DataBaseConnection.getConnection();
	             PreparedStatement stmt = connection.prepareStatement(sql)) {

	            stmt.setLong(1, id);

	            stmt.executeUpdate();
	            System.out.println("Exame deletado com sucesso.");

	        } catch (SQLException e) {
	            System.err.println("Erro ao deletar exame: " + e.getMessage());
	        }
    }
}
