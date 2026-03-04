package org.example.Classes;

public class Entrega {
    private int id;
    private int pedidoId;
    private int motoristaId;
    private String dataSaida;
    private String dataEntrega;
    private String status;

    // Construtor Completo (para busca no banco)
    public Entrega(int id, int pedidoId, int motoristaId, String dataSaida, String dataEntrega, String status) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.motoristaId = motoristaId;
        this.dataSaida = dataSaida;
        this.dataEntrega = dataEntrega;
        this.status = status;
    }

    // Construtor para novos registros (sem ID e sem data de entrega inicial)
    public Entrega(int pedidoId, int motoristaId, String status) {
        this.pedidoId = pedidoId;
        this.motoristaId = motoristaId;
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

    public String getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(String dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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