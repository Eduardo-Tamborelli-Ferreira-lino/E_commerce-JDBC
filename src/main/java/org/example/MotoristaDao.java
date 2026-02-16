package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MotoristaDao {

    public void cadastrarMotorista(String nome, String cnh, String veiculo, String cidadeBase)throws SQLException {
        String command = """
                INSERT INTO motorista(
                nome,
                cnh,
                veiculo,
                cidade_base)
                VALUES
                (?, ?, ?, ?)
                """;
        try (Connection conn = Conexao.conectar()){
            PreparedStatement stmt = conn.prepareStatement(command);
            stmt.setString(1, nome);
            stmt.setString(2, cnh);
            stmt.setString(3, veiculo);
            stmt.setString(4, cidadeBase);
            stmt.executeUpdate();
        }
    }
}
