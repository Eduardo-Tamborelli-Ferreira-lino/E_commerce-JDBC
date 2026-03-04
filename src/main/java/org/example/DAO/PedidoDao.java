package org.example.DAO;

import org.example.Classes.Pedido;
import org.example.Conexão.Conexao;
import org.example.Enum.StatusPedido;

import java.sql.*;
import java.util.ArrayList;

public class PedidoDao {

    public void criarPedido(Pedido pedido)throws SQLException{
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
            stmt.setInt(1, pedido.getClienteId());
            stmt.setTimestamp(2, pedido.getDataPedido());
            stmt.setDouble(3, pedido.getVolumeM3());
            stmt.setDouble(4, pedido.getPeso());
            stmt.setString(5,pedido.getStatus().getNome());
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
                        rs.getTimestamp("data_pedido"),
                        rs.getDouble("volume_m3"),
                        rs.getDouble("peso_kg"),
                        StatusPedido.getDeliveryStatus(rs.getString("status"))
                ));
            }
            return pedido;
        }
    }
}
