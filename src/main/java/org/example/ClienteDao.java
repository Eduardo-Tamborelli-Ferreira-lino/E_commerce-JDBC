package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDao {

    public void cadastrarCliente(String nome, String cpfCnpj, String endereco, String cidade, String estado)throws SQLException {
        String command = """
                INSERT INTO cliente
                (nome,
                cpf_cnpj,
                endereco,
                cidade,
                estado)
                VALUES
                (?, ?, ?, ?, ?)
                """;
        try (Connection conn = Conexao.conectar()){
            PreparedStatement stmt = conn.prepareStatement(command);
            stmt.setString(1,nome);
            stmt.setString(2,cpfCnpj);
            stmt.setString(3,endereco);
            stmt.setString(4,cidade);
            stmt.setString(5,estado);
            stmt.executeUpdate();
        }
    }
}
