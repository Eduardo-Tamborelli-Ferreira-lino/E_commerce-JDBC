package org.example.Enum;

public enum StatusPedido {
    PENDENTE ("Pendente"),
    ENTREGUE ("Entregue"),
    CANCELADO ( "Cancelado");

    private final String nome;

    StatusPedido(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
