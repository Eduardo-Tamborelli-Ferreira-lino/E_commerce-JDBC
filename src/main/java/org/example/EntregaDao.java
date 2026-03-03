package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EntregaDao {

    public void gerarEntrega(int pedidoId, int motoristaId, String dataSaida, String dataEntraga, String status)throws SQLException {
        String command = """
                INSERT INTO entrega(
                pedido_id,
                motorista_id,
                data_saida,
                data_entrega,
                status)
                VALUES
                (?, ?, ?, ?, ?)
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setInt(1, pedidoId);
            stmt.setInt(2, motoristaId);
            stmt.setString(3, dataSaida);
            stmt.setString(4, dataEntraga);
            stmt.setString(5, status);
            stmt.executeUpdate();
        }
    }

    public ArrayList<Entrega> listarEntregas()throws SQLException{
        ArrayList<Entrega>entregas = new ArrayList<>();
        String command = """
                SELECT
                id,
                pedido_id,
                motorista_id,
                data_saida,
                data_entrega,
                status
                FROM entrega
                """;
        try (Connection conn = Conexao.conectar()){
            PreparedStatement stmt = conn.prepareStatement(command);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                entregas.add(new Entrega(
                        rs.getInt("id"),
                        rs.getInt("pedido_id"),
                        rs.getInt("motorista_id"),
                        rs.getString("data_saida"),
                        rs.getString("data_entrega"),
                        rs.getString("status")
                ));
            }
            return entregas;
        }
    }

    public void atualizarEntrega(String status, int id) throws SQLException{
        String command = """
                UPDATE entrega
                set status = ?
                WHERE id = ?
                """;
        try (Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setString(1, status);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    public ArrayList<EntregaDetalhadaDTO> listarECM ()throws SQLException{
        ArrayList<EntregaDetalhadaDTO> entregas = new ArrayList<>();
        String command = """
                SELECT
                e.id AS id,
                p.id AS pedido_id,
                c.nome AS nome_cliente,
                m.nome AS nome_motorista,
                e.data_saida,
                e.status
                FROM entrega e
                INNER JOIN pedido p ON e.pedido_id = p.id
                INNER JOIN cliente c ON p.cliente_id = c.id
                INNER JOIN motorista m ON e.motorista_id = m.id
                """;
        try (Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(command)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                EntregaDetalhadaDTO dto = new EntregaDetalhadaDTO(
                        rs.getInt("id"),
                        rs.getString("nome_cliente"),
                        rs.getString("nome_motorista"),
                        rs.getString("status"),
                        rs.getString("data_saida")
                   );
                entregas.add(dto);
            }
        }
        return entregas;
    }
}
