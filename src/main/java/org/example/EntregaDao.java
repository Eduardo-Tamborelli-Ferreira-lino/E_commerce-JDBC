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
}
