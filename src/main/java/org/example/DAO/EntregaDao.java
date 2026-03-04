package org.example.DAO;

import org.example.Classes.Entrega;
import org.example.Conexão.Conexao;
import org.example.DTO.EntregaDetalhadaDTO;
import org.example.DTO.MaiorVolumeEntregueDTO;
import org.example.DTO.TotalEntregasMotoristaDTO;

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

    public ArrayList<TotalEntregasMotoristaDTO> totalEntregaMotorista()throws SQLException{
        ArrayList<TotalEntregasMotoristaDTO> dto = new ArrayList<>();
        String command = """
                SELECT
                    m.nome AS nome_motorista,
                    COUNT(e.id) AS total_entrega
                FROM motorista m
                INNER JOIN entrega e ON m.id = e.motorista_id
                GROUP BY m.nome
                ORDER BY total_entrega DESC
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(command)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                dto.add(new TotalEntregasMotoristaDTO(
                        rs.getString("nome_motorista"),
                        rs.getInt("total_entrega")
                ));
            }
        }
        return dto;
    }

    public ArrayList<MaiorVolumeEntregueDTO> MaiorVolumeEntregue()throws SQLException{
        ArrayList<MaiorVolumeEntregueDTO> dto = new ArrayList<>();
        String command = """
                SELECT
                    c.nome as nome_cliente,
                    c.id AS id_cliente,
                    SUM(p.volume_m3) AS valor
                FROM cliente c
                INNER JOIN pedido p ON c.id = p.cliente_id
                INNER JOIN entrega e ON p.id = e.pedido_id
                GROUP BY c.id, c.nome
                ORDER BY valor DESC
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(command)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                dto.add(new MaiorVolumeEntregueDTO(
                        rs.getString("nome_cliente"),
                        rs.getInt("id_cliente"),
                        rs.getDouble("valor")
                ));
            }
        }
        return dto;
    }
}
