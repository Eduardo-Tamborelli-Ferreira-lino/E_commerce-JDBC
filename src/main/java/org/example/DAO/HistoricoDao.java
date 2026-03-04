package org.example.DAO;

import org.example.Conexão.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class HistoricoDao {

    public void gerarHistorico(int entragaId, Timestamp dataEvento, String descricao)throws SQLException {
        String command = """
                INSERT INTO historico(
                entrega_id,
                data_entrega,
                descricao)
                VALUES
                (?, ?, ?)
                """;
        try (Connection conn = Conexao.conectar()){
            PreparedStatement stmt = conn.prepareStatement(command);
            stmt.setInt(1, entragaId);
            stmt.setTimestamp(2, dataEvento);
            stmt.setString(3, descricao);
            stmt.executeUpdate();
        }
    }
}
