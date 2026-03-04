package org.example.DTO;

public record TotalEntregasMotoristaDTO(
        String nomeMotorista,
        int totalEntrega
) {

    @Override
    public String toString() {
        return  "Nome do Motorista: " + nomeMotorista +
                "\nTotal de Entregas: " + totalEntrega +
                "\n------------------------------------------";
    }
}
