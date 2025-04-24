package com.projeto.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/sistema_hospitalar";
    private static final String USER = "postgres"; // Substitua pelo seu usuário do PostgreSQL
    private static final String PASSWORD = "123"; // Substitua pela sua senha do PostgreSQL

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexão com o banco de dados PostgreSQL estabelecida com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }
}
