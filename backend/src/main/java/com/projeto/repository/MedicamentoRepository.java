package com.projeto.repository;

import com.projeto.model.Medicamento;
import com.projeto.util.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoRepository implements BaseRepository<Medicamento, Long> {

    @Override
    public Medicamento insert(Medicamento medicamento) {
    	String sql = "INSERT INTO medicamento (nome,dosagem,via_administracao) VALUES (?, ?,?)";

		try (Connection connection = DataBaseConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setString(1, medicamento.getNome());
			stmt.setString(2, medicamento.getDosagem());
			stmt.setString(3, medicamento.getViaAdministracao());


			stmt.executeUpdate();
			System.out.println("Medicamento inserido com sucesso.");

			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                medicamento.setIdMedicamento(generatedKeys.getLong(1));
	            }
	        }

		} catch (SQLException e) {
			System.err.println("Erro ao inserir medicamento: " + e.getMessage());
		}
		return medicamento;

    }

    @Override
    public Medicamento findById(Long id) {
    	 String sql = "SELECT * FROM medicamento WHERE id = ?";
	        Medicamento medicamento = new Medicamento();

	        try (Connection connection = DataBaseConnection.getConnection();
	             PreparedStatement stmt = connection.prepareStatement(sql)) {

	            stmt.setLong(1,id );
	            ResultSet rs = stmt.executeQuery();

	            if (rs.next()) {
					medicamento = new Medicamento();
	                medicamento.setIdMedicamento(rs.getLong("id"));
	                medicamento.setNome(rs.getString("nome"));
	                medicamento.setDosagem(rs.getString("dosagem"));
	                medicamento.setViaAdministracao(rs.getString("via_administracao"));
 	                return medicamento;
	            }

	        } catch (SQLException e) {
	            System.err.println("Erro ao buscar medicamento: " + e.getMessage());

	        }
	        return medicamento;
    }

    @Override
    public List<Medicamento> findAll() {
    	String sql = "SELECT * FROM medicamento";
        Medicamento medicamento = new Medicamento();
        List<Medicamento> medicamentos = new ArrayList<>();

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
				medicamento = new Medicamento();
            	medicamento.setIdMedicamento(rs.getLong("id"));
                medicamento.setNome(rs.getString("nome"));
                medicamento.setDosagem(rs.getString("dosagem"));
                medicamento.setViaAdministracao(rs.getString("via_administracao"));
                medicamentos.add(medicamento);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar medicamentos: " + e.getMessage());
        }
        return medicamentos;
    }

    @Override
    public void update(Medicamento medicamento) {
    	 StringBuilder queryBuilder = new StringBuilder("UPDATE medicamento SET ");
	        boolean adicionouCampo = false;

	        if (medicamento.getNome() != null) {
	            queryBuilder.append("nome = ?");
	            adicionouCampo = true;
	        }
	        if (medicamento.getDosagem() != null) {
	            if (adicionouCampo) queryBuilder.append(", ");
	            queryBuilder.append("dosagem = ?");
	            adicionouCampo = true;
	        }
	        if (medicamento.getViaAdministracao() != null) {
	            if (adicionouCampo) queryBuilder.append(", ");
	            queryBuilder.append("via_administracao = ?");
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

	            if (medicamento.getNome() != null) {
	                preparedStatement.setString(index++, medicamento.getNome());
	            }
	            if (medicamento.getDosagem() != null) {
	                preparedStatement.setString(index++, medicamento.getDosagem());
	            }
	            if (medicamento.getViaAdministracao() != null) {
	                preparedStatement.setString(index++, medicamento.getViaAdministracao());
	            }



	            preparedStatement.setLong(index, medicamento.getIdMedicamento());
	            preparedStatement.executeUpdate();

	            System.out.println("Medicamento atualizado com sucesso.");

	        } catch (SQLException e) {
	            System.err.println("Erro ao atualizar medicamento: " + e.getMessage());
	        }
    }

    @Override
    public void delete(Long id) {
    	 String sql = "DELETE FROM medicamento WHERE id = ?";

	        try (Connection connection = DataBaseConnection.getConnection();
	             PreparedStatement stmt = connection.prepareStatement(sql)) {

	            stmt.setLong(1, id);

	            stmt.executeUpdate();
	            System.out.println("Medicamento deletado com sucesso.");

	        } catch (SQLException e) {
	            System.err.println("Erro ao deletar medicamento: " + e.getMessage());
	        }
    }
}
