package com.projeto.repository;

import com.projeto.model.Usuario;
import com.projeto.util.DataBaseConnection;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository implements BaseRepository<Usuario, Long> {

    @Override
    public Usuario insert(Usuario usuario) {
        String query = "INSERT INTO usuario (nome, username, email, senha, perfil_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getLogin());
            preparedStatement.setString(3, usuario.getEmail());

            String senhaCriptografada = BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt());
            preparedStatement.setString(4, senhaCriptografada);

            preparedStatement.setLong(5, usuario.getIdPerfil());

            preparedStatement.executeUpdate();
            System.out.println("Usuário inserido com sucesso.");

            return usuario;

        } catch (SQLException e) {
            System.err.println("Erro ao inserir usuário: " + e.getMessage());
        }
        return usuario;
    }

    @Override
    public Usuario findById(Long id) {

        String query = "SELECT * FROM usuario WHERE id = ?";
        Usuario usuario = new Usuario();

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                usuario.setIdUsuario(resultSet.getLong("id"));
                usuario.setNome(resultSet.getString("nome"));
                usuario.setLogin(resultSet.getString("username"));
                usuario.setSenha(resultSet.getString("senha"));
                usuario.setIdPerfil(resultSet.getLong("perfil_id"));
                usuario.setEmail(resultSet.getString("email"));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário: " + e.getMessage());
        }
        return usuario ;
    }

    @Override
    public List<Usuario> findAll() {
        String query = "SELECT * FROM usuario";
        List<Usuario> listaUsuarios = new ArrayList<>();

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(resultSet.getLong("id"));
                usuario.setNome(resultSet.getString("nome"));
                usuario.setLogin(resultSet.getString("username"));
                usuario.setSenha(resultSet.getString("senha"));
                usuario.setIdPerfil(resultSet.getLong("perfil_id"));
                usuario.setEmail(resultSet.getString("email"));

                listaUsuarios.add(usuario);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
        }
        return listaUsuarios;
    }

    @Override
    public void update(Usuario usuario) {
        StringBuilder queryBuilder = new StringBuilder("UPDATE usuario SET ");
        boolean adicionouCampo = false;

        // Monta a query dinamicamente
        if (usuario.getNome() != null) {
            queryBuilder.append("nome = ?");
            adicionouCampo = true;
        }
        if (usuario.getEmail() != null) {
            if (adicionouCampo) queryBuilder.append(", ");
            queryBuilder.append("email = ?");
            adicionouCampo = true;
        }
        if (usuario.getSenha() != null) {
            if (adicionouCampo) queryBuilder.append(", ");
            queryBuilder.append("senha = ?");
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

            if (usuario.getNome() != null) {
                preparedStatement.setString(index++, usuario.getNome());
            }
            if (usuario.getEmail() != null) {
                preparedStatement.setString(index++, usuario.getEmail());
            }
            if (usuario.getSenha() != null) {
                String senhaCriptografada = BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt());
                preparedStatement.setString(index++, senhaCriptografada);
            }

            preparedStatement.setLong(index, usuario.getIdUsuario());
            preparedStatement.executeUpdate();

            System.out.println("Usuário atualizado com sucesso.");

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM usuario WHERE id = ?";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
            System.out.println("Usuário deletado com sucesso.");

        } catch (SQLException e) {
            System.err.println("Erro ao deletar usuário: " + e.getMessage());
        }
    }

}
