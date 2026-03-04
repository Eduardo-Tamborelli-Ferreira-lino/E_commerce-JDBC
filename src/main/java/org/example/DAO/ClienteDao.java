package org.example.DAO;

import org.example.Classes.Cliente;
import org.example.Conexão.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public ArrayList<Cliente> listarCliente()throws SQLException{
        ArrayList<Cliente>clientes = new ArrayList<>();
        String command = """
                SELECT id,
                nome,
                cpf_cnpj,
                endereco,
                cidade,
                estado
                FROM cliente
                """;
        try (Connection conn = Conexao.conectar()){
            PreparedStatement stmt = conn.prepareStatement(command);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                clientes.add(new Cliente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf_cnpj"),
                        rs.getString("endereco"),
                        rs.getString("cidade"),
                        rs.getString("estado")
                ));
            }
            return clientes;
        }
    }
}
