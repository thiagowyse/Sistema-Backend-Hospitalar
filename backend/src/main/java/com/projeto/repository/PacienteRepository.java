package com.projeto.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.projeto.model.Paciente;
import com.projeto.model.Perfil;
import com.projeto.util.DataBaseConnection;

public class PacienteRepository implements BaseRepository<Paciente, Long> {

	@Override
	public Paciente insert(Paciente paciente) {
		String sql = "INSERT INTO paciente (nome,endereco,telefone,data_nascimento) VALUES (?, ?,?,?)";

		try (Connection connection = DataBaseConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setString(1, paciente.getNome());
			stmt.setString(2, paciente.getEndereco());
			stmt.setString(3, paciente.getTelefone());
			stmt.setDate(4, paciente.getDataNascimento());

			stmt.executeUpdate();
			System.out.println("Paciente inserido com sucesso.");

			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                paciente.setIdPaciente(generatedKeys.getLong(1));
	            }
	        }

		} catch (SQLException e) {
			System.err.println("Erro ao inserir paciente: " + e.getMessage());
		}
		return paciente;
	}

	@Override
	public Paciente findById(Long id) {
		 String sql = "SELECT * FROM paciente WHERE id = ?";
	        Paciente paciente = new Paciente();

	        try (Connection connection = DataBaseConnection.getConnection();
	             PreparedStatement stmt = connection.prepareStatement(sql)) {

	            stmt.setLong(1,id );
	            ResultSet rs = stmt.executeQuery();

	            if (rs.next()) {
	                paciente.setIdPaciente(rs.getLong("id"));
	                paciente.setNome(rs.getString("nome"));
	                paciente.setEndereco(rs.getString("endereco"));
	                paciente.setTelefone(rs.getString("telefone"));
	                paciente.setDataNascimento(rs.getDate("data_nascimento"));
	                return paciente;
	            }

	        } catch (SQLException e) {
	            System.err.println("Erro ao buscar paciente: " + e.getMessage());

	        }
	        return null;
	}

	@Override
	public List<Paciente> findAll() {
		  String sql = "SELECT * FROM paciente";
	        Paciente paciente = new Paciente();
	        List<Paciente> pacientes = new ArrayList<>();

	        try (Connection connection = DataBaseConnection.getConnection();
	             PreparedStatement stmt = connection.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {

	            while (rs.next()) {
	            	paciente.setIdPaciente(rs.getLong("id"));
	                paciente.setNome(rs.getString("nome"));
	                paciente.setEndereco(rs.getString("endereco"));
	                paciente.setTelefone(rs.getString("telefone"));
	                paciente.setDataNascimento(rs.getDate("data_nascimento"));
	                pacientes.add(paciente);
	            }

	        } catch (SQLException e) {
	            System.err.println("Erro ao listar pacientes: " + e.getMessage());
	        }
	        return pacientes;
	}

	@Override
	public void update(Paciente paciente) {
		 StringBuilder queryBuilder = new StringBuilder("UPDATE paciente SET ");
	        boolean adicionouCampo = false;

	        if (paciente.getNome() != null) {
	            queryBuilder.append("nome = ?");
	            adicionouCampo = true;
	        }
	        if (paciente.getEndereco() != null) {
	            if (adicionouCampo) queryBuilder.append(", ");
	            queryBuilder.append("endereco = ?");
	            adicionouCampo = true;
	        }
	        if (paciente.getTelefone() != null) {
	            if (adicionouCampo) queryBuilder.append(", ");
	            queryBuilder.append("telefone = ?");
	            adicionouCampo = true;
	        }

	        if(paciente.getDataNascimento() != null){
	            if(adicionouCampo)queryBuilder.append(",");
	            queryBuilder.append("data_nascimento = ?");
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

	            if (paciente.getNome() != null) {
	                preparedStatement.setString(index++, paciente.getNome());
	            }
	            if (paciente.getEndereco() != null) {
	                preparedStatement.setString(index++, paciente.getEndereco());
	            }
	            if (paciente.getTelefone() != null) {
	                preparedStatement.setString(index++, paciente.getTelefone());
	            }
	            if (paciente.getDataNascimento() != null) {
	                preparedStatement.setDate(index++, paciente.getDataNascimento());
	            }


	            preparedStatement.setLong(index, paciente.getIdPaciente());
	            preparedStatement.executeUpdate();

	            System.out.println("Paciente atualizado com sucesso.");

	        } catch (SQLException e) {
	            System.err.println("Erro ao atualizar paciente: " + e.getMessage());
	        }
	}

	@Override
	public void delete(Long id) {
		 String sql = "DELETE FROM paciente WHERE id = ?";

	        try (Connection connection = DataBaseConnection.getConnection();
	             PreparedStatement stmt = connection.prepareStatement(sql)) {

	            stmt.setLong(1, id);

	            stmt.executeUpdate();
	            System.out.println("Paciente deletado com sucesso.");

	        } catch (SQLException e) {
	            System.err.println("Erro ao deletar paciente: " + e.getMessage());
	        }
	}
}
