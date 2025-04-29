package com.projeto.repository;

import com.projeto.model.Declaracao;
import com.projeto.model.Paciente;
import com.projeto.util.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DeclaracaoRepository implements BaseRepository<Declaracao, Long> {
    @Override
    public Declaracao insert(Declaracao declaracao) {
    	
    	String sql = "INSERT INTO declaracao (paciente_id,medico_id,data_emissao,tipo_declaracao,descricao,validade) VALUES (?, ?,?,?,?,?)";

		try (Connection connection = DataBaseConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setLong(1, declaracao.getIdPaciente());
			stmt.setLong(2, declaracao.getIdMedico());
			stmt.setDate(3, declaracao.getDataEmissao());
			stmt.setString(4, declaracao.getTipoDeclaracao());
			stmt.setString(5, declaracao.getDescricao());
			stmt.setDate(6, declaracao.getDataValidade());

			stmt.executeUpdate();
			System.out.println("Declaracao inserida com sucesso.");
			
			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                declaracao.setIdDeclaracao(generatedKeys.getLong(1));  
	            }
	        }

		} catch (SQLException e) {
			System.err.println("Erro ao inserir declaracao: " + e.getMessage());
		}
		return declaracao;
    }

    @Override
    public Declaracao findById(Long id) {
    	 String sql = "SELECT * FROM declaracao WHERE id = ?";
	        Declaracao declaracao = new Declaracao();

	        try (Connection connection = DataBaseConnection.getConnection();
	             PreparedStatement stmt = connection.prepareStatement(sql)) {

	            stmt.setLong(1,id );
	            ResultSet rs = stmt.executeQuery();

	            if (rs.next()) {
	                declaracao.setIdDeclaracao(rs.getLong("id"));
	                declaracao.setIdPaciente(rs.getLong("paciente_id"));
	                declaracao.setIdMedico(rs.getLong("medico_id"));
	                declaracao.setDataEmissao(rs.getDate("data_emissao"));
	                declaracao.setTipoDeclaracao(rs.getString("tipo_declaracao"));
	                declaracao.setDescricao(rs.getString("descricao"));
	                declaracao.setDataValidade(rs.getDate("validade"));
	                return declaracao;
	            }

	        } catch (SQLException e) {
	            System.err.println("Erro ao buscar declaracao: " + e.getMessage());
	            
	        }
	        return null;
    }

    @Override
    public List<Declaracao> findAll() {
    	 String sql = "SELECT * FROM declaracao";
	        Declaracao declaracao;
	        List<Declaracao> declaracoes = new ArrayList<>();

	        try (Connection connection = DataBaseConnection.getConnection();
	             PreparedStatement stmt = connection.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {

	            while (rs.next()) {
	            	declaracao= new Declaracao();
	            	declaracao.setIdDeclaracao(rs.getLong("id"));
	                declaracao.setIdPaciente(rs.getLong("paciente_id"));
	                declaracao.setIdMedico(rs.getLong("medico_id"));
	                declaracao.setDataEmissao(rs.getDate("data_emissao"));
	                declaracao.setTipoDeclaracao(rs.getString("tipo_declaracao"));
	                declaracao.setDescricao(rs.getString("descricao"));
	                declaracao.setDataValidade(rs.getDate("validade"));
	                declaracoes.add(declaracao);
	            }

	        } catch (SQLException e) {
	            System.err.println("Erro ao listar declaracoes: " + e.getMessage());
	        }
	        return declaracoes;
    }

    @Override
    public void update(Declaracao declaracao) {
    	StringBuilder queryBuilder = new StringBuilder("UPDATE declaracao SET ");
        boolean adicionouCampo = false;

        if (declaracao.getIdPaciente() != null) {
            queryBuilder.append("paciente_id = ?");
            adicionouCampo = true;
        }
        if (declaracao.getIdMedico() != null) {
            if (adicionouCampo) queryBuilder.append(", ");
            queryBuilder.append("medico_id = ?");
            adicionouCampo = true;
        }
        if (declaracao.getDataEmissao() != null) {
            if (adicionouCampo) queryBuilder.append(", ");
            queryBuilder.append("data_emissao = ?");
            adicionouCampo = true;
        }

        if(declaracao.getTipoDeclaracao() != null){
            if(adicionouCampo)queryBuilder.append(",");
            queryBuilder.append("tipo_declaracao = ?");
            adicionouCampo = true;
        }
        if(declaracao.getDescricao() != null){
            if(adicionouCampo)queryBuilder.append(",");
            queryBuilder.append("descricao = ?");
            adicionouCampo = true;
        }
        if(declaracao.getDataValidade() != null){
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

            if (declaracao.getIdPaciente() != null) {
                preparedStatement.setLong(index++, declaracao.getIdPaciente());
            }
            if (declaracao.getIdMedico() != null) {
                preparedStatement.setLong(index++, declaracao.getIdMedico());
            }
            if (declaracao.getDataEmissao() != null) {
                preparedStatement.setDate(index++, declaracao.getDataEmissao());
            }
            if (declaracao.getTipoDeclaracao() != null) {
                preparedStatement.setString(index++, declaracao.getTipoDeclaracao());
            }
            if (declaracao.getDescricao() != null) {
                preparedStatement.setString(index++, declaracao.getDescricao());
            }
            if (declaracao.getDataValidade() != null) {
                preparedStatement.setDate(index++, declaracao.getDataValidade());
            }

            preparedStatement.setLong(index, declaracao.getIdDeclaracao());
            preparedStatement.executeUpdate();

            System.out.println("Declaracao atualizado com sucesso.");

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar declaracao: " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
    	 String sql = "DELETE FROM declaracao WHERE id = ?";

	        try (Connection connection = DataBaseConnection.getConnection();
	             PreparedStatement stmt = connection.prepareStatement(sql)) {

	            stmt.setLong(1, id);

	            stmt.executeUpdate();
	            System.out.println("Declaracao deletado com sucesso.");

	        } catch (SQLException e) {
	            System.err.println("Erro ao deletar declaracao: " + e.getMessage());
	        }
    }
}
