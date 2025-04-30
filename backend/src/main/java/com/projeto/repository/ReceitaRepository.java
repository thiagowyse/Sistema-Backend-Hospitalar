package com.projeto.repository;

import com.projeto.model.Receita;
import com.projeto.util.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReceitaRepository implements BaseRepository<Receita, Long> {


    @Override
    public Receita insert(Receita receita) {
    	String sql = "INSERT INTO receita (prontuario_id,data_receita,validade,descricao,status) VALUES (?, ?,?,?,?)";

		try (Connection connection = DataBaseConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setLong(1, receita.getProntuarioId());
			stmt.setDate(2, receita.getDataReceita());
			stmt.setDate(3, receita.getValidade());
			stmt.setString(4, receita.getDescricao());
			stmt.setString(5, receita.getStatus());

			stmt.executeUpdate();
			System.out.println("Receita inserida com sucesso.");

			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                receita.setIdReceita(generatedKeys.getLong(1));
	            }
	        }

		} catch (SQLException e) {
			System.err.println("Erro ao inserir receita: " + e.getMessage());
		}
		return receita;

    }

    @Override
    public Receita findById(Long id) {
    	String sql = "SELECT * FROM receita WHERE id = ?";
        Receita receita = new Receita();

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1,id );
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                receita.setIdReceita(rs.getLong("id"));
                receita.setProntuarioId(rs.getLong("prontuario_id"));
                receita.setDataReceita(rs.getDate("data_receita"));
                receita.setValidade(rs.getDate("validade"));
                receita.setDescricao(rs.getString("descricao"));
                receita.setStatus(rs.getString("status"));
                return receita;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar receita: " + e.getMessage());

        }
        return null;
    }

    @Override
    public List<Receita> findAll() {
    	String sql = "SELECT * FROM receita";
        Receita receita;
        List<Receita> receitas = new ArrayList<>();

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
            	receita= new Receita();
            	receita.setIdReceita(rs.getLong("id"));
                receita.setProntuarioId(rs.getLong("prontuario_id"));
                receita.setDataReceita(rs.getDate("data_receita"));
                receita.setValidade(rs.getDate("validade"));
                receita.setDescricao(rs.getString("descricao"));
                receita.setStatus(rs.getString("status"));
                receitas.add(receita);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar receitas: " + e.getMessage());
        }
        return receitas;
    }

    @Override
    public void update(Receita receita) {
    	 StringBuilder queryBuilder = new StringBuilder("UPDATE receita SET ");
	        boolean adicionouCampo = false;

	        if (receita.getProntuarioId() != null) {
	            queryBuilder.append("prontuario_id = ?");
	            adicionouCampo = true;
	        }
	        if (receita.getDataReceita() != null) {
	            if (adicionouCampo) queryBuilder.append(", ");
	            queryBuilder.append("data_receita = ?");
	            adicionouCampo = true;
	        }
	        if (receita.getValidade() != null) {
	            if (adicionouCampo) queryBuilder.append(", ");
	            queryBuilder.append("validade = ?");
	            adicionouCampo = true;
	        }

	        if(receita.getDescricao() != null){
	            if(adicionouCampo)queryBuilder.append(",");
	            queryBuilder.append("descricao = ?");
	            adicionouCampo = true;
	        }
	        if(receita.getStatus() != null){
	            if(adicionouCampo)queryBuilder.append(",");
	            queryBuilder.append("status = ?");
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

	            if (receita.getProntuarioId() != null) {
	                preparedStatement.setLong(index++, receita.getProntuarioId());
	            }
	            if (receita.getDataReceita() != null) {
	                preparedStatement.setDate(index++, receita.getDataReceita());
	            }
	            if (receita.getValidade() != null) {
	                preparedStatement.setDate(index++, receita.getValidade());
	            }
	            if (receita.getDescricao()!= null) {
	                preparedStatement.setString(index++, receita.getDescricao());
	            }
	            if (receita.getStatus()!= null) {
	                preparedStatement.setString(index++, receita.getStatus());
	            }

	            preparedStatement.setLong(index, receita.getIdReceita());
	            preparedStatement.executeUpdate();

	            System.out.println("Receita atualizado com sucesso.");

	        } catch (SQLException e) {
	            System.err.println("Erro ao atualizar receita: " + e.getMessage());
	        }
    }

    @Override
    public void delete(Long id) {
    	 String sql = "DELETE FROM receita WHERE id = ?";

	        try (Connection connection = DataBaseConnection.getConnection();
	             PreparedStatement stmt = connection.prepareStatement(sql)) {

	            stmt.setLong(1, id);

	            stmt.executeUpdate();
	            System.out.println("Receita deletada com sucesso.");

	        } catch (SQLException e) {
	            System.err.println("Erro ao deletar receita: " + e.getMessage());
	        }
    }
}
