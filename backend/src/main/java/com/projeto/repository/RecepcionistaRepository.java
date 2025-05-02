package com.projeto.repository;


import com.projeto.model.Recepcionista;
import com.projeto.model.Usuario;
import com.projeto.util.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RecepcionistaRepository implements BaseRepository<Recepcionista, Long> {
    @Override
    public Recepcionista insert(Recepcionista recepcionista) {

    	String sql = "INSERT INTO recepcionista (usuario_id) VALUES (?)";

		try (Connection connection = DataBaseConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setLong(1, recepcionista.getUsuario().getIdUsuario());


			stmt.executeUpdate();
			System.out.println("Recepcionista adicionada com sucesso.");

			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                recepcionista.setIdRecepcionista(generatedKeys.getLong(1));
	            }
	        }

		} catch (SQLException e) {
			System.err.println("Erro ao adicionar recepcionista: " + e.getMessage());
		}
		return recepcionista;
    }

    @Override
    public Recepcionista findById(Long id) {
    	String sql = "SELECT * FROM recepcionista WHERE id = ?";
        Recepcionista recepcionista = new Recepcionista();

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1,id );
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                recepcionista = new Recepcionista();
                recepcionista.setIdRecepcionista(rs.getLong("id"));
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getLong("usuario_id"));
                recepcionista.setUsuario(usuario);

                return recepcionista;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar recepcionista: " + e.getMessage());

        }
        return recepcionista;
    }

    @Override
    public List<Recepcionista> findAll() {
    	String sql = "SELECT * FROM recepcionista";
        Recepcionista recepcionista;
        List<Recepcionista> recepcionistas = new ArrayList<>();

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
            	recepcionista = new Recepcionista();
            	recepcionista.setIdRecepcionista(rs.getLong("id"));

                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getLong("usuario_id"));
                recepcionista.setUsuario(usuario);
                recepcionistas.add(recepcionista);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar recepcionistas: " + e.getMessage());
        }
        return recepcionistas;

    }

    @Override
    public void update(Recepcionista recepcionista) {
    	 String sql = "UPDATE recepcionista SET usuario_id = ? WHERE id = ?";

         try (Connection connection = DataBaseConnection.getConnection();
              PreparedStatement stmt = connection.prepareStatement(sql)) {

             stmt.setLong(1, recepcionista.getUsuario().getIdUsuario());
             stmt.setLong(2, recepcionista.getIdRecepcionista());

             stmt.executeUpdate();
             System.out.println("Recepcionista atualizada com sucesso.");

         } catch (SQLException e) {
             System.err.println("Erro ao atualizar recepcionista: " + e.getMessage());
         }
    }

    @Override
    public void delete(Long id) {
    	 String sql = "DELETE FROM recepcionista WHERE id = ?";

	        try (Connection connection = DataBaseConnection.getConnection();
	             PreparedStatement stmt = connection.prepareStatement(sql)) {

	            stmt.setLong(1, id);

	            stmt.executeUpdate();
	            System.out.println("Recepcionista deletada com sucesso.");

	        } catch (SQLException e) {
	            System.err.println("Erro ao deletar Recepcionista: " + e.getMessage());
	        }
    }
}
