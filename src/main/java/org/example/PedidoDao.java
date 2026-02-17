package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PedidoDao {

    public void criarPedido(int clienteId, String dataPedido, Double volumeM3, Double peso, String status)throws SQLException{
        String command = """
                INSERT INTO pedido(
                cliente_id,
                data_pedido,
                volume_m3,
                peso,
                status)
                VALUES
                (?, ?, ?, ?, ?)
                """;
        try (Connection conn = Conexao.conectar()){
            PreparedStatement stmt = conn.prepareStatement(command);
            stmt.setInt(1, clienteId);
            stmt.setString(2, dataPedido);
            stmt.setDouble(3, volumeM3);
            stmt.setDouble(4, peso);
            stmt.setString(5,status);
            stmt.executeUpdate();
        }
    }
}
