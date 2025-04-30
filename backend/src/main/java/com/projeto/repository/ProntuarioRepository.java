package com.projeto.repository;

import com.projeto.model.Prontuario;
import com.projeto.util.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProntuarioRepository implements BaseRepository<Prontuario, Long> {
	@Override
	public Prontuario insert(Prontuario prontuario) {
		String sql = "INSERT INTO prontuario (paciente_id,medico_id,descricao,data_criacao) VALUES (?, ?,?,?)";

		try (Connection connection = DataBaseConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setLong(1, prontuario.getIdPaciente());
			stmt.setLong(2, prontuario.getIdMedico());
			stmt.setString(3, prontuario.getDescricao());
			stmt.setDate(4, prontuario.getDataCriacao());

			stmt.executeUpdate();
			System.out.println("Prontuario inserido com sucesso.");

			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					prontuario.setIdProntuario(generatedKeys.getLong(1));
				}
			}

		} catch (SQLException e) {
			System.err.println("Erro ao inserir prontuario: " + e.getMessage());
		}
		return prontuario;
	}

	@Override
	public Prontuario findById(Long id) {
		String sql = "SELECT * FROM prontuario WHERE id = ?";
		Prontuario prontuario = new Prontuario();

		try (Connection connection = DataBaseConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql)) {

			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				prontuario.setIdProntuario(rs.getLong("id"));
				prontuario.setIdPaciente(rs.getLong("paciente_id"));
				prontuario.setIdMedico(rs.getLong("medico_id"));
				prontuario.setDescricao(rs.getString("descricao"));
				prontuario.setDataCriacao(rs.getDate("data_criacao"));

				return prontuario;
			}

		} catch (SQLException e) {
			System.err.println("Erro ao buscar prontuario: " + e.getMessage());

		}
		return null;
	}

	@Override
	public List<Prontuario> findAll() {
		String sql = "SELECT * FROM prontuario";
		Prontuario prontuario;
		List<Prontuario> prontuarios = new ArrayList<>();

		try (Connection connection = DataBaseConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				prontuario = new Prontuario();
				prontuario.setIdProntuario(rs.getLong("id"));
				prontuario.setIdPaciente(rs.getLong("paciente_id"));
				prontuario.setIdMedico(rs.getLong("medico_id"));
				prontuario.setDescricao(rs.getString("descricao"));
				prontuario.setDataCriacao(rs.getDate("data_criacao"));
				prontuarios.add(prontuario);
			}

		} catch (SQLException e) {
			System.err.println("Erro ao listar prontuarios: " + e.getMessage());
		}
		return prontuarios;
	}

	@Override
	public void update(Prontuario prontuario) {
		StringBuilder queryBuilder = new StringBuilder("UPDATE prontuario SET ");
		boolean adicionouCampo = false;

		if (prontuario.getIdPaciente() != null) {
			queryBuilder.append("paciente_id = ?");
			adicionouCampo = true;
		}
		if (prontuario.getIdMedico() != null) {
			if (adicionouCampo)
				queryBuilder.append(", ");
			queryBuilder.append("medico_id = ?");
			adicionouCampo = true;
		}
		if (prontuario.getDescricao() != null) {
			if (adicionouCampo)
				queryBuilder.append(", ");
			queryBuilder.append("descricao = ?");
			adicionouCampo = true;
		}

		if (prontuario.getDataCriacao() != null) {
			if (adicionouCampo)
				queryBuilder.append(",");
			queryBuilder.append("data_criacao = ?");
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

			if (prontuario.getIdPaciente() != null) {
				preparedStatement.setLong(index++, prontuario.getIdPaciente());
			}
			if (prontuario.getIdMedico() != null) {
				preparedStatement.setLong(index++, prontuario.getIdMedico());
			}
			if (prontuario.getDescricao() != null) {
				preparedStatement.setString(index++, prontuario.getDescricao());
			}
			if (prontuario.getDataCriacao() != null) {
				preparedStatement.setDate(index++, prontuario.getDataCriacao());
			}

			preparedStatement.setLong(index, prontuario.getIdProntuario());
			preparedStatement.executeUpdate();

			System.out.println("Prontuario atualizado com sucesso.");

		} catch (SQLException e) {
			System.err.println("Erro ao atualizar prontuario: " + e.getMessage());
		}
	}

	@Override
	public void delete(Long id) {
		 String sql = "DELETE FROM prontuario WHERE id = ?";

	        try (Connection connection = DataBaseConnection.getConnection();
	             PreparedStatement stmt = connection.prepareStatement(sql)) {

	            stmt.setLong(1, id);

	            stmt.executeUpdate();
	            System.out.println("Prontuario deletado com sucesso.");

	        } catch (SQLException e) {
	            System.err.println("Erro ao deletar prontuario: " + e.getMessage());
	        }
	}
}
