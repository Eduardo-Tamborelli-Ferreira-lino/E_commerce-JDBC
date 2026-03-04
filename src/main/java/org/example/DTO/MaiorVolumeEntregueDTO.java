package org.example.DTO;

public record MaiorVolumeEntregueDTO(
        String nomeCliente,
        int idCliente,
        Double valor
) {

    @Override
    public String toString() {
        return  "Nome Cliente: " + nomeCliente +
                "\nId Cliente: " + idCliente +
                "\nVolume Total: " + valor +
                "\n----------------------------------------";
    }
}
