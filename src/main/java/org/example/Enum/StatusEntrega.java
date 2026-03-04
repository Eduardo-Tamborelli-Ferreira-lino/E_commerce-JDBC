package org.example.Enum;

public enum StatusEntrega {
    EM_ROTA ("Em Rota"),
    ENTEGUE ("Entregue"),
    ATRASADO ("Atrasado");

    private final String nome;

    StatusEntrega(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
