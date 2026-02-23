package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PedidoDao {

    public void criarPedido(int clienteId, String dataPedido, Double volumeM3, Double peso, String status)throws SQLException{
        String command = """
                INSERT INTO pedido(
                cliente_id,
                data_pedido,
                volume_m3,
                peso_kg,
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

    public ArrayList<Pedido> listarPedido()throws SQLException{
        ArrayList<Pedido>pedido = new ArrayList<>();
        String command = """
                SELECT id,
                cliente_id,
                data_pedido,
                volume_m3,
                peso_kg,
                status
                FROM pedido
                """;
        try (Connection conn = Conexao.conectar()){
            PreparedStatement stmt = conn.prepareStatement(command);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                pedido.add(new Pedido(
                        rs.getInt("id"),
                        rs.getInt("cliente_id"),
                        rs.getString("data_pedido"),
                        rs.getDouble("volume_m3"),
                        rs.getDouble("peso_kg"),
                        rs.getString("status")
                ));
            }
            return pedido;
        }
    }
}
