package com.projeto.repository;

import com.projeto.model.Enfermeiro;
import com.projeto.model.Paciente;
import com.projeto.util.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EnfermeiroRepository implements BaseRepository<Enfermeiro, Long>{

    @Override
    public Enfermeiro insert(Enfermeiro enfermeiro) {

    	String sql = "INSERT INTO enfermeiro (usuario_id, coren) VALUES (?, ?)";

		try (Connection connection = DataBaseConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setLong(1, enfermeiro.getIdUsuario());
			stmt.setString(2, enfermeiro.getCoren());


			stmt.executeUpdate();
			System.out.println("Enfermeiro inserido com sucesso.");

			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                enfermeiro.setIdEnfermeiro(generatedKeys.getLong(1));
	            }
	        }

		} catch (SQLException e) {
			System.err.println("Erro ao inserir enfermeiro: " + e.getMessage());
		}
		return enfermeiro;
    }

    @Override
    public Enfermeiro findById(Long id) {
    	String sql = "SELECT * FROM enfermeiro WHERE id = ?";
        Enfermeiro enfermeiro = new Enfermeiro();

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1,id );
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
				enfermeiro.setIdEnfermeiro(rs.getLong("id"));
                enfermeiro.setIdUsuario(rs.getLong("usuario_id"));
                enfermeiro.setCoren(rs.getString("coren"));

                return enfermeiro;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar enfermeiro: " + e.getMessage());

        }
        return null;
    }

    @Override
    public List<Enfermeiro> findAll() {
    	 String sql = "SELECT * FROM enfermeiro";
	        Enfermeiro enfermeiro;
	        List<Enfermeiro> enfermeiros = new ArrayList<>();

	        try (Connection connection = DataBaseConnection.getConnection();
	             PreparedStatement stmt = connection.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {

	            while (rs.next()) {
	            	enfermeiro= new Enfermeiro();
	            	enfermeiro.setIdEnfermeiro(rs.getLong("id"));
	                enfermeiro.setIdUsuario(rs.getLong("usuario_id"));
	                enfermeiro.setCoren(rs.getString("coren"));
	                enfermeiros.add(enfermeiro);
	            }

	        } catch (SQLException e) {
	            System.err.println("Erro ao listar enfermeiros: " + e.getMessage());
	        }
	        return enfermeiros;
    }

    @Override
    public void update(Enfermeiro enfermeiro) {
    	 StringBuilder queryBuilder = new StringBuilder("UPDATE enfermeiro SET ");
	        boolean adicionouCampo = false;

	        if (enfermeiro.getIdUsuario() != null) {
	            queryBuilder.append("usuario_id = ?");
	            adicionouCampo = true;
	        }
	        if (enfermeiro.getCoren() != null) {
	            if (adicionouCampo) queryBuilder.append(", ");
	            queryBuilder.append("coren = ?");
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

	            if (enfermeiro.getIdUsuario() != null) {
	                preparedStatement.setLong(index++, enfermeiro.getIdUsuario());
	            }
	            if (enfermeiro.getCoren() != null) {
	                preparedStatement.setString(index++, enfermeiro.getCoren());
	            }


	            preparedStatement.setLong(index, enfermeiro.getIdEnfermeiro());
	            preparedStatement.executeUpdate();

	            System.out.println("Enfermeiro atualizado com sucesso.");

	        } catch (SQLException e) {
	            System.err.println("Erro ao atualizar enfermeiro: " + e.getMessage());
	        }
    }

    @Override
    public void delete(Long id) {
    	String sql = "DELETE FROM enfermeiro WHERE id = ?";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            stmt.executeUpdate();
            System.out.println("Enfermeiro deletado com sucesso.");

        } catch (SQLException e) {
            System.err.println("Erro ao deletar enfermeiro: " + e.getMessage());
        }
    }
}
