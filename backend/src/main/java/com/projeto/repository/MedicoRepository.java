package com.projeto.repository;

import com.projeto.model.Medico;
import com.projeto.model.Usuario;
import com.projeto.util.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MedicoRepository implements BaseRepository<Medico, Long> {
	@Override
	public Medico insert(Medico medico) {
		String sql = "INSERT INTO medico (usuario_id,crm) VALUES (?, ?)";

		try (Connection connection = DataBaseConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setLong(1, medico.getUsuario().getIdUsuario());
			stmt.setString(2, medico.getCrm());

			stmt.executeUpdate();
			System.out.println("Medico inserido com sucesso.");

			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					medico.setIdMedico(generatedKeys.getLong(1));
				}
			}

		} catch (SQLException e) {
			System.err.println("Erro ao inserir medico: " + e.getMessage());
		}
		return medico;
	}

	@Override
	public Medico findById(Long id) {
		String sql = "SELECT * FROM medico WHERE id = ?";

		Medico medico = null;

		try (Connection connection = DataBaseConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql)) {

			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				medico = new Medico();
				medico.setIdMedico(rs.getLong("id"));

				Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getLong("usuario_id"));
				medico.setUsuario(usuario);

				medico.setCrm(rs.getString("crm"));

				return medico;
			}

		} catch (SQLException e) {
			System.err.println("Erro ao buscar medico: " + e.getMessage());

		}
		return medico;
	}

	@Override
	public List<Medico> findAll() {
		String sql = "SELECT * FROM medico";
		Medico medico;
		List<Medico> medicos = new ArrayList<>();

		try (Connection connection = DataBaseConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				medico = new Medico();
				medico.setIdMedico(rs.getLong("id"));

				Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getLong("usuario_id"));
				medico.setUsuario(usuario);

				medico.setCrm(rs.getString("crm"));
				medicos.add(medico);
			}

		} catch (SQLException e) {
			System.err.println("Erro ao listar medicos: " + e.getMessage());
		}
		return medicos;
	}

	@Override
	public void update(Medico medico) {
		 StringBuilder queryBuilder = new StringBuilder("UPDATE medico SET ");
	        boolean adicionouCampo = false;

			if(medico.getUsuario() != null){
				if(medico.getUsuario().getIdUsuario() != null){
					queryBuilder.append("usuario_id = ?");
					adicionouCampo = true;
				}
			}


	        if (medico.getCrm() != null) {
	            if (adicionouCampo) queryBuilder.append(", ");
	            queryBuilder.append("crm = ?");
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

				if(medico.getUsuario() != null){
					if(medico.getUsuario().getIdUsuario() != null){
						preparedStatement.setLong(index++, medico.getUsuario().getIdUsuario());
					}
				}


	            if (medico.getCrm() != null) {
	                preparedStatement.setString(index++, medico.getCrm());
	            }


	            preparedStatement.setLong(index, medico.getIdMedico());
	            preparedStatement.executeUpdate();

	            System.out.println("Medico atualizado com sucesso.");

	        } catch (SQLException e) {
	            System.err.println("Erro ao atualizar medico: " + e.getMessage());
	        }
	}

	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM medico WHERE id = ?";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            stmt.executeUpdate();
            System.out.println("Medico deletado com sucesso.");

        } catch (SQLException e) {
            System.err.println("Erro ao deletar medico: " + e.getMessage());
        }
	}
}
