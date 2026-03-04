package org.example.Classes;

import org.example.Enum.StatusPedido;

import java.sql.Timestamp;

public class Entrega {
    private int id;
    private int pedidoId;
    private int motoristaId;
    private Timestamp dataSaida;
    private String dataEntrega;
    private StatusPedido status;

    // Construtor Completo (para busca no banco)
    public Entrega(int id, int pedidoId, int motoristaId, Timestamp dataSaida, String dataEntrega, StatusPedido status) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.motoristaId = motoristaId;
        this.dataSaida = dataSaida;
        this.dataEntrega = dataEntrega;
        this.status = status;
    }

    // Construtor para novos registros (sem ID e sem data de entrega inicial)
    public Entrega(int pedidoId, int motoristaId,Timestamp dataSaida, String dataEntrega, StatusPedido status) {
        this.pedidoId = pedidoId;
        this.motoristaId = motoristaId;
        this.dataSaida = dataSaida;
        this.dataEntrega = dataEntrega;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public int getMotoristaId() {
        return motoristaId;
    }

    public void setMotoristaId(int motoristaId) {
        this.motoristaId = motoristaId;
    }

    public Timestamp getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Timestamp dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ID Entrega: " + getId() +
                "\nID Pedido: " + getPedidoId() +
                "\nID Motorista: " + getMotoristaId() +
                "\nData de Saída: " + getDataSaida() +
                "\nData de Entrega: " + (getDataEntrega() == null ? "Pendente" : getDataEntrega()) +
                "\nStatus: " + getStatus() +
                "\n--------------------------------------------";
    }
}