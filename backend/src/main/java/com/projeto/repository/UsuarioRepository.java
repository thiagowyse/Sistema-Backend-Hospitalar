package com.projeto.repository;

import com.projeto.model.Usuario;
import com.projeto.util.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository implements BaseRepository<Usuario, Long> {

    @Override
    public Usuario insert(Usuario usuario) {
        String query = "INSERT INTO usuario (nome, username, email, senha, cpf, perfil_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getLogin());
            preparedStatement.setString(3, usuario.getEmail());

            preparedStatement.setString(4, usuario.getSenha());

            preparedStatement.setString(5, usuario.getCpf());
            if (usuario.getIdPerfil() != null) {
                preparedStatement.setLong(6, usuario.getIdPerfil());
            } else {
                preparedStatement.setNull(6, java.sql.Types.BIGINT);
            }

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Usuário inserido com sucesso.");
            } else {
                System.err.println("Nenhum registro foi inserido.");
            }

            return usuario;

        } catch (SQLException e) {
            System.err.println("Erro ao inserir usuário: " + e.getMessage());
        }

        return usuario;
    }

    @Override
    public Usuario findById(Long id) {

        String query = "SELECT id, nome, username, senha, email, cpf, perfil_id FROM usuario WHERE id = ?";
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
                usuario.setEmail(resultSet.getString("email"));
                usuario.setCpf(resultSet.getString("cpf"));
                usuario.setIdPerfil(resultSet.getLong("perfil_id"));

            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário: " + e.getMessage());
        }
        return usuario ;
    }

    @Override
    public List<Usuario> findAll() {
        String query = "SELECT id, nome, username, senha, email, cpf, perfil_id FROM usuario";
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
                usuario.setEmail(resultSet.getString("email"));
                usuario.setCpf(resultSet.getString("cpf"));
                usuario.setIdPerfil(resultSet.getLong("perfil_id"));

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

        if(usuario.getCpf() != null){
            if(adicionouCampo)queryBuilder.append(",");
            queryBuilder.append("cpf = ?");
            adicionouCampo = true;
        }

        if(usuario.getIdPerfil() != null){
            if(adicionouCampo)queryBuilder.append(",");
            queryBuilder.append("perfil_id = ?");
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
                preparedStatement.setString(index++, usuario.getSenha());
            }
            if (usuario.getCpf() != null) {
                preparedStatement.setString(index++, usuario.getCpf());
            }
            if (usuario.getIdPerfil() != null) {
                preparedStatement.setLong(index++, usuario.getIdPerfil());
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
