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

    public static StatusPedido getDeliveryStatus (String nome){
        for (StatusPedido deliveryStatus : StatusPedido.values()){
            if (deliveryStatus.getNome().equalsIgnoreCase(nome)){
                return deliveryStatus;
            }
        }
        return null;
    }
}
