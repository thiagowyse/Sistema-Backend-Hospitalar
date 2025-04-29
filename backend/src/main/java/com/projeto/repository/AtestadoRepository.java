package com.projeto.repository;

import com.projeto.model.Atestado;
import com.projeto.model.Paciente;
import com.projeto.util.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AtestadoRepository implements BaseRepository<Atestado, Long>{
    @Override
    public Atestado insert(Atestado atestado) {
    	String sql = "INSERT INTO atestado (paciente_id,medico_id,data_emissao,descricao,validade) VALUES (?, ?,?,?,?)";

		try (Connection connection = DataBaseConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setLong(1, atestado.getIdPaciente());
			stmt.setLong(2, atestado.getIdMedico());
			stmt.setDate(3, atestado.getDataEmissao());
			stmt.setString(4, atestado.getDescricao());
			stmt.setDate(5, atestado.getDataValidade());


			stmt.executeUpdate();
			System.out.println("Atestado inserido com sucesso.");
			
			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                atestado.setIdAtestado(generatedKeys.getLong(1));  
	            }
	        }

		} catch (SQLException e) {
			System.err.println("Erro ao inserir atestado: " + e.getMessage());
		}
		return atestado;
    }

    @Override
    public Atestado findById(Long id) {
    	String sql = "SELECT * FROM atestado WHERE id = ?";
        Atestado atestado = new Atestado();

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1,id );
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
            	atestado.setIdAtestado(rs.getLong("id"));
                atestado.setIdPaciente(rs.getLong("paciente_id"));
                atestado.setIdMedico(rs.getLong("medico_id"));
                atestado.setDataEmissao(rs.getDate("data_emissao"));
                atestado.setDescricao(rs.getString("descricao"));
                atestado.setDataValidade(rs.getDate("validade"));
                return atestado;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar atestado: " + e.getMessage());
            
        }
        return null;
    }

    @Override
    public List<Atestado> findAll() {
    	 String sql = "SELECT * FROM atestado";
	        Atestado atestado;
	        List<Atestado> atestados = new ArrayList<>();

	        try (Connection connection = DataBaseConnection.getConnection();
	             PreparedStatement stmt = connection.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {

	            while (rs.next()) {
	            	atestado = new Atestado();
	            	atestado.setIdAtestado(rs.getLong("id"));
	                atestado.setIdPaciente(rs.getLong("paciente_id"));
	                atestado.setIdMedico(rs.getLong("medico_id"));
	                atestado.setDataEmissao(rs.getDate("data_emissao"));
	                atestado.setDescricao(rs.getString("descricao"));
	                atestado.setDataValidade(rs.getDate("validade"));
	                atestados.add(atestado);
	            }

	        } catch (SQLException e) {
	            System.err.println("Erro ao listar atestados: " + e.getMessage());
	        }
	        return atestados;
    }

    @Override
    public void update(Atestado atestado) {
    	 StringBuilder queryBuilder = new StringBuilder("UPDATE atestado SET ");
	        boolean adicionouCampo = false;

	        if (atestado.getIdPaciente() != null) {
	            queryBuilder.append("paciente_id = ?");
	            adicionouCampo = true;
	        }
	        if (atestado.getIdMedico() != null) {
	            if (adicionouCampo) queryBuilder.append(", ");
	            queryBuilder.append("medico_id = ?");
	            adicionouCampo = true;
	        }
	        if (atestado.getDataEmissao() != null) {
	            if (adicionouCampo) queryBuilder.append(", ");
	            queryBuilder.append("data_emissao = ?");
	            adicionouCampo = true;
	        }

	        if(atestado.getDescricao() != null){
	            if(adicionouCampo)queryBuilder.append(",");
	            queryBuilder.append("descricao = ?");
	            adicionouCampo = true;
	        }
	        if(atestado.getDataValidade() != null){
	            if(adicionouCampo)queryBuilder.append(",");
	            queryBuilder.append("validade = ?");
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

	            if (atestado.getIdPaciente() != null) {
	                preparedStatement.setLong(index++, atestado.getIdPaciente());
	            }
	            if (atestado.getIdMedico() != null) {
	                preparedStatement.setLong(index++, atestado.getIdMedico());
	            }
	            if (atestado.getDataEmissao() != null) {
	                preparedStatement.setDate(index++, atestado.getDataEmissao());
	            }
	            if (atestado.getDescricao() != null) {
	                preparedStatement.setString(index++, atestado.getDescricao());
	            }
	            if (atestado.getDataValidade() != null) {
	                preparedStatement.setDate(index++, atestado.getDataValidade());
	            }
	            
	           

	            preparedStatement.setLong(index, atestado.getIdAtestado());
	            preparedStatement.executeUpdate();

	            System.out.println("Atestado atualizado com sucesso.");

	        } catch (SQLException e) {
	            System.err.println("Erro ao atualizar atestado: " + e.getMessage());
	        }
    }

    @Override
    public void delete(Long id) {
    	String sql = "DELETE FROM atestado WHERE id = ?";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            stmt.executeUpdate();
            System.out.println("Atestado deletado com sucesso.");

        } catch (SQLException e) {
            System.err.println("Erro ao deletar atestado: " + e.getMessage());
        }
    }
}
