package org.example;

public class Historico{
    private int id;
    private int entregaId;
    private String dataEvento;
    private String descricao;

    // Construtor Completo
    public Historico(int id, int entregaId, String dataEvento, String descricao) {
        this.id = id;
        this.entregaId = entregaId;
        this.dataEvento = dataEvento;
        this.descricao = descricao;
    }

    // Construtor para novos registros
    public Historico(int entregaId, String descricao) {
        this.entregaId = entregaId;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEntregaId() {
        return entregaId;
    }

    public void setEntregaId(int entregaId) {
        this.entregaId = entregaId;
    }

    public String getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "ID Histórico: " + getId() +
                "\nID Entrega: " + getEntregaId() +
                "\nData do Evento: " + getDataEvento() +
                "\nDescrição: " + getDescricao() +
                "\n--------------------------------------------";
    }
}