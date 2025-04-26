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
    public void insert(Usuario usuario) {
        String query = "INSERT INTO usuario (id, nome, email, senha) VALUES (?, ?, ?, ?)";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, usuario.getIdUsuario());
            preparedStatement.setString(2, usuario.getNome());
            preparedStatement.setString(3, usuario.getEmail());

            // Criptografa a senha antes de salvar no banco
            String senhaCriptografada = BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt());
            preparedStatement.setString(4, senhaCriptografada);

            preparedStatement.executeUpdate();
            System.out.println("Usuário inserido com sucesso.");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir usuário: " + e.getMessage());
        }
    }

    @Override
    public Usuario findById(Long id) {

        String query = "SELECT * FROM usuario WHERE id = ?";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Usuario> findAll() {
        String query = "SELECT * FROM usuario";
        List<Usuario> listaUsuarios = new ArrayList<>();

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {

                listaUsuarios.add(null);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
        }
        return listaUsuarios;
    }

    @Override
    public void update(Usuario usuario) {
        String query = "UPDATE usuario SET nome = ?, email = ?, senha = ? WHERE id = ?";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getEmail());

            // Criptografa a senha antes de atualizar
            String senhaCriptografada = BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt());
            preparedStatement.setString(3, senhaCriptografada);
            preparedStatement.setLong(4, usuario.getIdUsuario());

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

    // Método para validar a senha de um usuário durante o login
    public boolean validarSenha(String senhaDigitada, String senhaCriptografada) {
        return BCrypt.checkpw(senhaDigitada, senhaCriptografada);
    }
}
