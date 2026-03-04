package org.example.Classes;

public class Pedido {
    private int id;
    private int clienteId;
    private String dataPedido;
    private Double volumeM3;
    private Double peso;
    private String status;

    public Pedido(int id, int clienteId, String dataPedido, Double volumeM3, Double peso, String status) {
        this.id = id;
        this.clienteId = clienteId;
        this.dataPedido = dataPedido;
        this.volumeM3 = volumeM3;
        this.peso = peso;
        this.status = status;
    }

    public Pedido(int clienteId, String dataPedido, Double volumeM3, Double peso, String status) {
        this.clienteId = clienteId;
        this.dataPedido = dataPedido;
        this.volumeM3 = volumeM3;
        this.peso = peso;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Double getVolumeM3() {
        return volumeM3;
    }

    public void setVolumeM3(Double volumeM3) {
        this.volumeM3 = volumeM3;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ID Pedido: " + getId() +
                "\nID Cliente: " + getClienteId() +
                "\nData do Pedido: " + getDataPedido() +
                "\nVolume (m³): " + getVolumeM3() +
                "\nPeso (kg): " + getPeso() +
                "\nStatus: " + getStatus() +
                "\n--------------------------------------------";
    }
}
