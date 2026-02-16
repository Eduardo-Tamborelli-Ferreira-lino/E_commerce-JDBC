package org.example;

public class Cliente {
    private int id;
    private String nome;
    private String cpfCnpj;
    private String endereco;
    private String cidade;
    private String estado;

    public Cliente(int id, String nome, String cpfCnpj, String endereco, String cidade, String estado) {
        this.id = id;
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
    }

    public Cliente(String nome, String cpfCnpj, String endereco, String cidade, String estado) {
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "ID: " + getId() + "\nNome: " + getNome() + "\nCPF ou CNPJ: " + getCpfCnpj() +
                "\nEndereço: " + getEndereco() + "\nCidade: " + getCidade() + "\nEstado: " + getEstado() +
                "\n--------------------------------------------";
    }
}
