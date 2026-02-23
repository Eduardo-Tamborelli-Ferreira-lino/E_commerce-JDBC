package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public ArrayList<Motorista> listarMotorista()throws SQLException{
        ArrayList<Motorista>motoristas = new ArrayList<>();
        String command = """
                SELECT id,
                nome,
                cnh,
                veiculo,
                cidade_base
                FROM motorista
                """;
        try (Connection conn = Conexao.conectar()){
            PreparedStatement stmt = conn.prepareStatement(command);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                motoristas.add(new Motorista(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cnh"),
                        rs.getString("veiculo"),
                        rs.getString("cidade_base")
                ));
            }
            return motoristas;
        }
    }
}
