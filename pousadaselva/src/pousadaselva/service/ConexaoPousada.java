package pousadaselva.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoPousada {
    // URL apontando para o banco jungle_lodge
    private static final String URL = "jdbc:mysql://localhost:3306/jungle_lodge";
    private static final String USUARIO = "root";
    private static final String SENHA = "root";

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar no banco da pousada: " + e.getMessage());
            return null;
        }
    }
}