package com.projeto.repository;

import com.projeto.model.LogUsuario;
import com.projeto.model.Paciente;
import com.projeto.util.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LogUsuarioRepository implements BaseRepository<LogUsuario,Long> {
    @Override
    public LogUsuario insert(LogUsuario logUsuario) {
    	String sql = "INSERT INTO log_usuario (usuario_id,acao,data_acao ) VALUES (?, ?,?)";

		try (Connection connection = DataBaseConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setLong(1, logUsuario.getIdUsuario());
			stmt.setString(2, logUsuario.getAcao());
			stmt.setDate(3, logUsuario.getDataHora());

			stmt.executeUpdate();
			System.out.println("Log de usuario inserido com sucesso.");

			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                logUsuario.setIdLogUsuario(generatedKeys.getLong(1));
	            }
	        }

		} catch (SQLException e) {
			System.err.println("Erro ao inserir log de usuario: " + e.getMessage());
		}
		return logUsuario;
    }

    @Override
    public LogUsuario findById(Long id) {
    	 String sql = "SELECT * FROM log_usuario WHERE id = ?";
	        LogUsuario logUsuario = new LogUsuario();

	        try (Connection connection = DataBaseConnection.getConnection();
	             PreparedStatement stmt = connection.prepareStatement(sql)) {

	            stmt.setLong(1,id );
	            ResultSet rs = stmt.executeQuery();

	            if (rs.next()) {
	                logUsuario.setIdLogUsuario(rs.getLong("id"));
	                logUsuario.setIdUsuario(rs.getLong("usuario_id"));
	                logUsuario.setAcao(rs.getString("acao"));
	                logUsuario.setDataHora(rs.getDate("data_acao"));
 	                return logUsuario;
	            }

	        } catch (SQLException e) {
	            System.err.println("Erro ao buscar o log de usuario: " + e.getMessage());

	        }
	        return null;
    }

    @Override
    public List<LogUsuario> findAll() {
    	  String sql = "SELECT * FROM log_usuario";
	        LogUsuario logUsuario;
	        List<LogUsuario> logUsuarios = new ArrayList<>();

	        try (Connection connection = DataBaseConnection.getConnection();
	             PreparedStatement stmt = connection.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {

	            while (rs.next()) {
	            	logUsuario=new LogUsuario();
	            	logUsuario.setIdLogUsuario(rs.getLong("id"));
	                logUsuario.setIdUsuario(rs.getLong("usuario_id"));
	                logUsuario.setAcao(rs.getString("acao"));
	                logUsuario.setDataHora(rs.getDate("data_acao"));
	                logUsuarios.add(logUsuario);
	            }

	        } catch (SQLException e) {
	            System.err.println("Erro ao listar os logs de usuario: " + e.getMessage());
	        }
	        return logUsuarios;
    }

    @Override
    public void update(LogUsuario logUsuario) {
    	 StringBuilder queryBuilder = new StringBuilder("UPDATE log_usuario SET ");
	        boolean adicionouCampo = false;

	        if (logUsuario.getIdUsuario() != null) {
	            queryBuilder.append("usuario_id = ?");
	            adicionouCampo = true;
	        }
	        if (logUsuario.getAcao() != null) {
	            if (adicionouCampo) queryBuilder.append(", ");
	            queryBuilder.append("acao = ?");
	            adicionouCampo = true;
	        }
	        if (logUsuario.getDataHora() != null) {
	            if (adicionouCampo) queryBuilder.append(", ");
	            queryBuilder.append("data_acao = ?");
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

	            if (logUsuario.getIdUsuario() != null) {
	                preparedStatement.setLong(index++,logUsuario.getIdUsuario());
	            }
	            if (logUsuario.getAcao() != null) {
	                preparedStatement.setString(index++, logUsuario.getAcao());
	            }
	            if (logUsuario.getDataHora() != null) {
	                preparedStatement.setDate(index++, logUsuario.getDataHora());
	            }


	            preparedStatement.setLong(index, logUsuario.getIdLogUsuario());
	            preparedStatement.executeUpdate();

	            System.out.println("Log de usuario atualizado com sucesso.");

	        } catch (SQLException e) {
	            System.err.println("Erro ao atualizar log de usuario: " + e.getMessage());
	        }
    }

    @Override
    public void delete(Long id) {
    	 String sql = "DELETE FROM log_usuario WHERE id = ?";

	        try (Connection connection = DataBaseConnection.getConnection();
	             PreparedStatement stmt = connection.prepareStatement(sql)) {

	            stmt.setLong(1, id);

	            stmt.executeUpdate();
	            System.out.println("Log de usuario deletado com sucesso.");

	        } catch (SQLException e) {
	            System.err.println("Erro ao deletar log de usuario: " + e.getMessage());
	        }
    }
}
