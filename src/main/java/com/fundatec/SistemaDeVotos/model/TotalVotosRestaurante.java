package com.fundatec.SistemaDeVotos.model;


/**
 * Representa o resultado da contagem de votos de um restaurante.
 *
 * <p>É utilizado exclusivamente para exibir o nome do restaurante e a quantidade
 * total de votos recebidos no dia atual.</p>
 */
public class TotalVotosRestaurante {

    /**
     * Nome do restaurante votado.
     */
    private String nomeRestaurante;

    /**
     * Quantidade de votos recebidos.
     */
    private Long totalVotos;

    public TotalVotosRestaurante(String nomeRestaurante, Long totalVotos) {
        this.nomeRestaurante = nomeRestaurante;
        this.totalVotos = totalVotos;
    }

    public String getNomeRestaurante() { return nomeRestaurante; }

    public void setNomeRestaurante(String nomeRestaurante) { this.nomeRestaurante = nomeRestaurante; }

    public Long getTotalVotos() { return totalVotos; }

    public void setTotalVotos(Long totalVotos) { this.totalVotos = totalVotos; }

    @Override
    public String toString() {
        return String.format("Restaurante: %s | Votos: %d", nomeRestaurante, totalVotos);
    }
}
