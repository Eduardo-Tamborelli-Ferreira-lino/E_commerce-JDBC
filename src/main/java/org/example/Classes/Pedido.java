package org.example.Classes;

import org.example.Enum.StatusPedido;

import java.sql.Time;
import java.sql.Timestamp;

public class Pedido {
    private int id;
    private int clienteId;
    private Timestamp dataPedido;
    private Double volumeM3;
    private Double peso;
    private StatusPedido status;

    public Pedido(int id, int clienteId, Timestamp dataPedido, Double volumeM3, Double peso, StatusPedido status) {
        this.id = id;
        this.clienteId = clienteId;
        this.dataPedido = dataPedido;
        this.volumeM3 = volumeM3;
        this.peso = peso;
        this.status = status;
    }

    public Pedido(int clienteId, Timestamp dataPedido, Double volumeM3, Double peso, StatusPedido status) {
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

    public Timestamp getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Timestamp dataPedido) {
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

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
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
