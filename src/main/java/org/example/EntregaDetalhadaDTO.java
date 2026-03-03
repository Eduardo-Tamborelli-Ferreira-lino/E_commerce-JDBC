package org.example;

public record EntregaDetalhadaDTO(
        int idEntrega,
        String nomeCliente,
        String nomeMotorista,
        String status,
        String dataSaida) {

    @Override
    public String toString() {
        return  "ID Entrega: " + idEntrega +
                "\nNome Cliente: " + nomeCliente +
                "\nNome Motorista: " + nomeMotorista +
                "\nStatus: " + status +
                "\nData Saida: " + dataSaida +
                "\n------------------------------------";
    }
}
