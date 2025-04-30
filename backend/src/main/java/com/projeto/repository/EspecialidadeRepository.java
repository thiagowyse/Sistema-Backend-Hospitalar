package com.projeto.repository;

import com.projeto.model.Especialidade;
import com.projeto.model.Paciente;
import com.projeto.util.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadeRepository implements BaseRepository<Especialidade, Long>{
    @Override
    public Especialidade insert(Especialidade especialidade) {


    		String sql = "INSERT INTO especialidade (nome) VALUES (?)";

    		try (Connection connection = DataBaseConnection.getConnection();
    				PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

    			stmt.setString(1, especialidade.getNome());


    			stmt.executeUpdate();
    			System.out.println("Especialidade inserida com sucesso.");

    			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
    	            if (generatedKeys.next()) {
    	                especialidade.setIdEspecialidade(generatedKeys.getLong(1));
    	            }
    	        }

    		} catch (SQLException e) {
    			System.err.println("Erro ao inserir especialidade: " + e.getMessage());
    		}
    		return especialidade;
    }

    @Override
    public Especialidade findById(Long id) {
    	String sql = "SELECT * FROM especialidade WHERE id = ?";
        Especialidade especialidade = new Especialidade();

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1,id );
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
            	especialidade.setIdEspecialidade(rs.getLong("id"));
            	especialidade.setNome(rs.getString("nome"));
            	return especialidade;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar especialidade: " + e.getMessage());

        }
        return null;
    }

    @Override
    public List<Especialidade> findAll() {
    	 String sql = "SELECT * FROM especialidade";
	        Especialidade especialidade;
	        List<Especialidade> especialidades = new ArrayList<>();

	        try (Connection connection = DataBaseConnection.getConnection();
	             PreparedStatement stmt = connection.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {

	            while (rs.next()) {
	            	especialidade=  new Especialidade();
	            	especialidade.setIdEspecialidade(rs.getLong("id"));
	            	especialidade.setNome(rs.getString("nome"));
	            	especialidades.add(especialidade);
	            }

	        } catch (SQLException e) {
	            System.err.println("Erro ao listar especialidades: " + e.getMessage());
	        }
	        return especialidades;
    }

    @Override
    public void update(Especialidade especialidade) {
    	String sql = "UPDATE especialidade SET nome = ? WHERE id = ?";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, especialidade.getNome());
            stmt.setLong(2, especialidade.getIdEspecialidade());

            stmt.executeUpdate();
            System.out.println("Especialidade atualizada com sucesso.");

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar especialidade: " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
    	 String sql = "DELETE FROM especialidade WHERE id = ?";

	        try (Connection connection = DataBaseConnection.getConnection();
	             PreparedStatement stmt = connection.prepareStatement(sql)) {

	            stmt.setLong(1, id);

	            stmt.executeUpdate();
	            System.out.println("Especialidade deletada com sucesso.");

	        } catch (SQLException e) {
	            System.err.println("Erro ao deletar especialdade: " + e.getMessage());
	        }
    }
}
