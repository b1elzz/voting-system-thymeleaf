package com.fundatec.SistemaDeVotos.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO para validação de dados de entrada ao cadastrar um restaurante.
 */
public class RestauranteDTO {

    @NotBlank(message = "O nome do restaurante é obrigatório")
    private String nomeRestaurante;

    public String getNomeRestaurante() {
        return nomeRestaurante;
    }
    public void setNomeRestaurante(String nomeRestaurante) {
        this.nomeRestaurante = nomeRestaurante;
    }
}